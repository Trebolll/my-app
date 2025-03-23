package ru.org.myapp.cadence.samples.activity.impl;

import ru.org.myapp.cadence.samples.activity.GreetingActivity;

public class GreetingActivityImpl implements GreetingActivity {
    @Override
    public String composeGreeting(String name) {
        return "Processed: " + name.toUpperCase();
    }
}
