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

package ru.org.myapp.config.cadenceuber;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.WorkerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.org.myapp.config.cadenceuber.date.CadenceDataConverter;
import ru.org.myapp.mapper.WeatherForecastMapper;
import ru.org.myapp.service.ServiceOpenWeatherApi;
import ru.org.myapp.service.WeatherForecastService;
import ru.org.myapp.cadence.weather.activity.impl.ForecastActivityImpl;
import ru.org.myapp.cadence.weather.activity.impl.ForecastActivitySaveImpl;
import ru.org.myapp.cadence.samples.activity.impl.GreetingActivityImpl;
import ru.org.myapp.cadence.weather.activity.impl.WeatherActivityImpl;
import ru.org.myapp.cadence.samples.impl.*;
import ru.org.myapp.cadence.weather.workflow.impl.ForecastWorkflowImpl;
import ru.org.myapp.cadence.weather.workflow.impl.WeatherWorkflowImpl;

import static ru.org.myapp.util.Constant.DOMAIN;
import static ru.org.myapp.util.Constant.DOMAIN_BATCHER;
import static ru.org.myapp.util.Constant.TASK_LIST;
import static ru.org.myapp.util.Constant.TASK_LIST_WEATHER_TRACKER;


@Configuration
@Slf4j
public class CadenceAutoConfiguration {

    @Bean
    public WorkflowClient workflowClient() {
        return WorkflowClient.newInstance(
                new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                WorkflowClientOptions
                        .newBuilder()
                        .setDataConverter(CadenceDataConverter.cadenceJsonDataConverter())
                        .setDomain(DOMAIN).build());
    }

    @Bean
    @Qualifier("workflowClientWeather")
    @Primary
    public WorkflowClient workflowClientWeather() {
        return WorkflowClient.newInstance(
                new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                WorkflowClientOptions
                        .newBuilder()
                        .setDataConverter(CadenceDataConverter.cadenceJsonDataConverter())
                        .setDomain(DOMAIN_BATCHER)
                        .build());
    }

    @Bean
    public WorkerFactory startWorker(ObjectProvider<WorkflowClient> workflowClientProvider) {
        log.info("Starting workers Hello World");

        var workflowClient = workflowClientProvider.getIfAvailable();
        var factory = WorkerFactory.newInstance(workflowClient);
        var worker = factory.newWorker(TASK_LIST);

        worker.registerWorkflowImplementationTypes(
                HelloWorldWorkflowImpl.class,
                SignalWorkflowImpl.class,
                ParentWorkflowImpl.class,
                ChildWorkflowImpl.class);
        worker.registerActivitiesImplementations(new GreetingActivityImpl());
        factory.start();
        return factory;
    }

    @Bean
    public WorkerFactory workerWeather(ObjectProvider<WorkflowClient> workflowClientProvider,
                                       ObjectProvider<ServiceOpenWeatherApi> serviceOpenWeatherApiProvider,
                                       ObjectProvider<WeatherForecastService> weatherForecastServices,
                                       ObjectProvider<WeatherForecastMapper> weatherForecastMapper) {

        var workflowClient = workflowClientProvider.getIfAvailable();
        var serviceOpenWeatherApi = serviceOpenWeatherApiProvider.getIfAvailable();
        var weatherForecastService = weatherForecastServices.getIfAvailable();
        var factory = WorkerFactory.newInstance(workflowClient);
        var worker = factory.newWorker(TASK_LIST_WEATHER_TRACKER);
        var forecastMapper = weatherForecastMapper.getIfAvailable();

        worker.registerWorkflowImplementationTypes(
                WeatherWorkflowImpl.class);
        worker.addWorkflowImplementationFactory(ForecastWorkflowImpl.class, () ->
                new ForecastWorkflowImpl(forecastMapper)
        );
        worker.registerActivitiesImplementations(
                new WeatherActivityImpl(serviceOpenWeatherApi),
                new ForecastActivityImpl(serviceOpenWeatherApi),
                new ForecastActivitySaveImpl(weatherForecastService)
        );

        factory.start();
        log.info("Workers for Weather/Forecast started");
        return factory;
    }
}
