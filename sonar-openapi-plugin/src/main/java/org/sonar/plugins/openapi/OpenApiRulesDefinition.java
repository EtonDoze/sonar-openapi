/*
 * SonarQube OpenAPI Plugin
 * Copyright (C) 2018-2019 Societe Generale
 * vincent.girard-reydet AT socgen DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.openapi;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.openapi.checks.CheckList;
import org.sonar.plugins.openapi.api.OpenApiCustomRuleRepository;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenApiRulesDefinition implements RulesDefinition, OpenApiCustomRuleRepository {
  private static final String REPOSITORY_NAME = "SonarAnalyzer";
  private static final String RESOURCE_FOLDER = "org.sonar/l10n/openapi/rules/openapi";
  private static final Set<String> TEMPLATE_RULE_KEYS = new HashSet<>();

  private static RuleMetadataLoader getRuleMetadataLoader() {
    return new RuleMetadataLoader(RESOURCE_FOLDER);
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context
      .createRepository(repositoryKey(), OpenApi.KEY)
      .setName(REPOSITORY_NAME);

    getRuleMetadataLoader().addRulesByAnnotatedClass(repository, checkClasses());

    repository.rules().stream()
      .filter(rule -> TEMPLATE_RULE_KEYS.contains(rule.key()))
      .forEach(rule -> rule.setTemplate(true));

    repository.done();
  }

  @Override
  public String repositoryKey() {
    return CheckList.REPOSITORY_KEY;
  }

  @Override
  public List<Class> checkClasses() {
    return CheckList.getChecks();
  }
}
