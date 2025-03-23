package ru.org.myapp.cadence.samples.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface GreetingActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10)
    String composeGreeting(String name);
}
