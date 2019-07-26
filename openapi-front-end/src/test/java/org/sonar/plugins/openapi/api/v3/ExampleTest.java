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

public class ExampleTest extends BaseNodeTest<OpenApi3Grammar> {
  @Test
  public void can_parse_complex_object() {
    JsonNode node = parseResource(OpenApi3Grammar.EXAMPLE, "/models/v3/example/as-object.yaml");

    assertEquals("A multiline\ndescription\n", node, "/description");
    assertEquals("A foo example", node, "/summary");
    JsonNode valueNode = node.at("/value");

    assertPropertyKeys(valueNode).containsExactly("foo", "nested");
    assertPropertyKeys(valueNode, "/nested").containsExactly("other");
  }

  @Test
  public void can_parse_url_example() {
    JsonNode node = parseResource(OpenApi3Grammar.EXAMPLE, "/models/v3/example/as-url.yaml");

    assertEquals("http://foo.bar/examples/address-example.txt", node, "/externalValue");
    assertEquals("A foo example", node, "/summary");
  }

}
