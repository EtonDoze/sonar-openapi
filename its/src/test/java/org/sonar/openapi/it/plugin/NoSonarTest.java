/*
 * SonarQube OpenAPI Plugin
 * Copyright (C) 2019-2019 Societe Generale
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
package org.sonar.openapi.it.plugin;

import com.sonar.orchestrator.Orchestrator;
import com.sonar.orchestrator.build.SonarScanner;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.openapi.it.plugin.Tests.getMeasureAsInt;

public class NoSonarTest {

  private static final String PROJECT_KEY = "nosonar";

  @ClassRule
  public static Orchestrator orchestrator = Tests.ORCHESTRATOR;

  @BeforeClass
  public static void startServer() {
    orchestrator.getServer().provisionProject(PROJECT_KEY, PROJECT_KEY);
    orchestrator.getServer().associateProjectToQualityProfile(PROJECT_KEY, "openapi", "two_rules");
    SonarScanner build = SonarScanner.create()
      .setProjectDir(new File("projects", PROJECT_KEY))
      .setProjectKey(PROJECT_KEY)
      .setProjectName(PROJECT_KEY)
      .setProjectVersion("1.0-SNAPSHOT")
      .setSourceDirs("src")
      .setProperties("sonar.openapi.path.v2","src/openapi/v2/**");
    orchestrator.executeBuild(build);
  }

  @Test
  public void test_nosonar() {
    // DocumentedTag should match, but ProvideOpSummary should be ignored
    assertThat(Tests.getProjectMeasure(PROJECT_KEY, "violations")).isEqualTo(1);
  }
}
