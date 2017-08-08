/**
 * Copyright 2016 AppDynamics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appdynamics.extensions.process.configuration;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ConfigProcessor {

    private static final Logger logger = Logger.getLogger(ConfigProcessor.class);

    public List<Instance> processConfig(Map<String, ?> config) {
        List<Map> configuredProcesses = (List) config.get("instances");
        List<Instance> instances = Lists.newArrayList();
        for (Map configuredProcess : configuredProcesses) {
            Instance instance = new Instance();
            String displayName = (String) configuredProcess.get("displayName");
            String regex = (String) configuredProcess.get("regex");
            Integer pid = (Integer) configuredProcess.get("pid");

            if (!Strings.isNullOrEmpty(displayName)) {
                instance.setDisplayName(displayName);
            } else {
                logger.error("displayName null or empty, skipping ");
                break;
            }
            instance.setRegex(regex);
            instance.setPid(pid);

            instances.add(instance);
        }
        return instances;
    }

}
