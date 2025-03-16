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

package ru.org.myapp.workflows;


import com.uber.cadence.workflow.WorkflowMethod;
import ru.org.myapp.entity.common.SampleMessage;

import static ru.org.myapp.util.Constant.TASK_LIST;

public interface ParentWorkflow {
  @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = TASK_LIST)
  String getGreetingInParent(SampleMessage sampleMessage);
}
