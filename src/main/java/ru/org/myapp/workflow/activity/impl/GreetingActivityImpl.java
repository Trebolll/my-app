package ru.org.myapp.workflow.activity.impl;

import ru.org.myapp.workflow.activity.GreetingActivity;

public class GreetingActivityImpl implements GreetingActivity {
    @Override
    public String composeGreeting(String name) {
        return "Processed: " + name.toUpperCase();
    }
}
