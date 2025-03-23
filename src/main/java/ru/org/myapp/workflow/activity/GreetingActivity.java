package ru.org.myapp.workflow.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface GreetingActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10)
    String composeGreeting(String name);
}
