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

package ru.org.myapp.workflows.impl;


import com.uber.cadence.workflow.Workflow;
import org.slf4j.Logger;
import ru.org.myapp.entity.common.SampleMessage;
import ru.org.myapp.workflows.HelloWorldWorkflow;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {
  private final Logger logger = Workflow.getLogger(HelloWorldWorkflowImpl.class);

  @Override
  public String sayHello(SampleMessage message) {
    logger.info("executing HelloWorldWorkflow::sayHello");

    String result = "Hello, " + message.GetMessage();
    logger.info("output: " + result);
    return result;
  }
}
