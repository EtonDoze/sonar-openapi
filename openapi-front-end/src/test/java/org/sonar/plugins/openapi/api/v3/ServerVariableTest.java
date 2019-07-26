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
package org.sonar.plugins.openapi.api.v3;

import org.junit.Test;
import org.sonar.openapi.BaseNodeTest;
import org.sonar.sslr.yaml.grammar.JsonNode;

public class ServerVariableTest extends BaseNodeTest<OpenApi3Grammar> {
  @Test
  public void can_parse_full_definition() {
    JsonNode node = parseResource(OpenApi3Grammar.SERVER_VARIABLE, "/models/v3/server-variable.yaml");

    assertEquals("some default value", node, "/default");
    assertEquals("some text helping to understand the variable", node, "/description");
    assertElements(node, "/enum").containsExactly("VALUE1", "VALUE2", "VALUE3");
  }
}
