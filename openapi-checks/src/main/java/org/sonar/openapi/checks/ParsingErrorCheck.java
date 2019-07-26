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

import com.sonar.sslr.api.RecognitionException;
import java.util.List;
import org.sonar.check.Rule;
import org.sonar.plugins.openapi.api.OpenApiCheck;
import org.sonar.plugins.openapi.api.OpenApiVisitorContext;
import org.sonar.sslr.yaml.grammar.ValidationException;
import org.sonar.sslr.yaml.grammar.ValidationIssue;

@Rule(key = ParsingErrorCheck.CHECK_KEY)
public class ParsingErrorCheck extends OpenApiCheck {

  public static final String CHECK_KEY = "ParsingError";

  @Override
  public void scanFile(OpenApiVisitorContext context) {
    super.scanFile(context);
    RecognitionException parsingException = context.parsingException();
    if (parsingException instanceof ValidationException) {
      for (ValidationException issue : ((ValidationException) parsingException).getCauses()) {
        addIssue(issue.formatMessage(), issue.getNode());
      }
    } else if (parsingException != null) {
      addLineIssue(parsingException.getMessage(), parsingException.getLine());
    } else {
      List<ValidationIssue> issues = context.getIssues();
      for (ValidationIssue issue : issues) {
        addIssue(issue.formatMessage(), issue.getNode());
      }
    }
  }
}
