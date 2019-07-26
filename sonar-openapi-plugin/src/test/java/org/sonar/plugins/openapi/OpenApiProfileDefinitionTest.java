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

import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition.Context;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition.NewBuiltInQualityProfile;
import org.sonar.openapi.checks.CheckList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.sonar.plugins.openapi.OpenApiProfileDefinition.SONAR_WAY_PROFILE;

public class OpenApiProfileDefinitionTest {
  private static Context context(NewBuiltInQualityProfile profile) {
    Context context = mock(Context.class);
    when(context.createBuiltInQualityProfile(anyString(), anyString())).thenReturn(profile);
    return context;
  }

  @Test
  public void should_create_sonar_way_profile() {
    OpenApiProfileDefinition definition = new OpenApiProfileDefinition();
    NewBuiltInQualityProfile profile = mock(NewBuiltInQualityProfile.class);
    Context context = context(profile);

    definition.define(context);
    ;

    verify(context).createBuiltInQualityProfile(SONAR_WAY_PROFILE, OpenApi.KEY);
    verify(profile).setDefault(true);
    verify(profile, Mockito.atLeast(2)).activateRule(eq(CheckList.REPOSITORY_KEY), anyString());
  }
}
