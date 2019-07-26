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
package org.sonar.openapi.checks;

import com.google.common.collect.Sets;
import com.sonar.sslr.api.AstNodeType;
import java.util.Set;
import org.sonar.check.Rule;
import org.sonar.plugins.openapi.api.OpenApiCheck;
import org.sonar.plugins.openapi.api.v2.OpenApi2Grammar;
import org.sonar.plugins.openapi.api.v3.OpenApi3Grammar;
import org.sonar.sslr.yaml.grammar.impl.MissingNode;
import org.sonar.sslr.yaml.grammar.JsonNode;

@Rule(key = DefaultResponseCheck.CHECK_KEY)
public class DefaultResponseCheck extends OpenApiCheck {
    protected static final String CHECK_KEY = "DefaultResponse";
    private static final String MESSAGE_NO_DEFAULT = "Define a default response for this operation.";

    @Override
    public Set<AstNodeType> subscribedKinds() {
        return Sets.newHashSet(OpenApi2Grammar.RESPONSES, OpenApi3Grammar.RESPONSES);
    }

    @Override
    protected void visitNode(JsonNode node) {
        JsonNode defaultResponse = node.at("/default");
        if (defaultResponse == MissingNode.MISSING) {
            addIssue(MESSAGE_NO_DEFAULT, node.key());
        }
    }
}
