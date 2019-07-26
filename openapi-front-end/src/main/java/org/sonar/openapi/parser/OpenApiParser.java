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
package org.sonar.openapi.parser;

import org.sonar.openapi.OpenApiConfiguration;
import org.sonar.plugins.openapi.api.v2.OpenApi2Grammar;
import org.sonar.plugins.openapi.api.v3.OpenApi3Grammar;
import org.sonar.sslr.yaml.grammar.YamlParser;

public abstract class OpenApiParser {
  private OpenApiParser() {
    // Hidden utility class constructor
  }

  public static YamlParser createV2(OpenApiConfiguration configuration) {
    return YamlParser.builder().withCharset(configuration.getCharset()).withGrammar(OpenApi2Grammar.create()).withStrictValidation(configuration.isStrict()).build();
  }

  public static YamlParser createV3(OpenApiConfiguration configuration) {
    return YamlParser.builder().withCharset(configuration.getCharset()).withGrammar(OpenApi3Grammar.create()).withStrictValidation(configuration.isStrict()).build();
  }
}
