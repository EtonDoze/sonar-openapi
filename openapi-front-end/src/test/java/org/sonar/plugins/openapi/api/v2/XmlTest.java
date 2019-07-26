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
package org.sonar.plugins.openapi.api.v2;

import org.junit.Test;
import org.sonar.openapi.BaseNodeTest;
import org.sonar.sslr.yaml.grammar.JsonNode;

public class XmlTest extends BaseNodeTest<OpenApi2Grammar> {
  @Test
  public void can_parse_example() {
    JsonNode model = parseResource(OpenApi2Grammar.XML, "/models/v2/xml.yaml");

    assertEquals("http://swagger.io/schema/sample", model, "/namespace");
    assertEquals("sample", model, "/prefix");
    assertTrue(model, "/attribute");
    assertEquals("Some value", model, "/x-extension");
  }
}
