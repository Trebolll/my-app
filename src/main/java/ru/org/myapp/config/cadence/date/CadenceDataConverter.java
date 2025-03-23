/*
 *  Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *  Modifications copyright (C) 2017 Uber Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"). You may not
 *  use this file except in compliance with the License. A copy of the License is
 *  located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 *  or in the "license" file accompanying this file. This file is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package ru.org.myapp.config.cadence.date;


import com.uber.cadence.converter.JsonDataConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

// This singleton JsonDataConverter should be universally used in all components that require a
// JsonDataConverter. Specifically in Cadence's case, WorkflowClient uses Json serialize function
// and Worker uses deserialize function, therefore they have to match with one another.
public class CadenceDataConverter {
    public static JsonDataConverter cadenceJsonDataConverter() {
        return new JsonDataConverter(
                gsonBuilder -> {
                    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
                    gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDatetimeConverter());
                    gsonBuilder.registerTypeAdapter(ZoneOffset.class, new ZoneOffsetConverter());
                    return gsonBuilder;
                });
    }
}
