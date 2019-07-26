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

public class ItemsTest extends BaseNodeTest<OpenApi2Grammar> {
  @Test
  public void can_parse_min_length_description() {
    JsonNode model = parseResource(OpenApi2Grammar.ITEMS, "/models/v2/items/min-length.yaml");

    assertEquals("string", model, "/type");
    assertEquals("2", model, "/minLength");
  }

  @Test
  public void can_parse_array_of_arrays() {
    JsonNode model = parseResource(OpenApi2Grammar.ITEMS, "/models/v2/items/array-of-arrays.yaml");

    assertEquals("array", model, "/type");
    assertEquals("integer", model, "/items/type");
    assertEquals("0", model, "/items/minimum");
    assertEquals("63", model, "/items/maximum");
  }
}
