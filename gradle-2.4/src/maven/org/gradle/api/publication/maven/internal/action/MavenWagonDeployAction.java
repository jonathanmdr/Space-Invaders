/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.publication.maven.internal.action;

import org.codehaus.plexus.PlexusContainerException;

import java.io.File;

/**
 * A deploy action that uses the baked in Maven wagon implementations, or a custom user-provided wagon implemented.
 */
public class MavenWagonDeployAction extends MavenDeployAction {
    public MavenWagonDeployAction(File pomFile) {
        super(pomFile);
    }

    public void addWagonJar(File jar) {
        try {
            getContainer().addJarResource(jar);
        } catch (PlexusContainerException e) {
            throw new RuntimeException(e);
        }
    }
}
