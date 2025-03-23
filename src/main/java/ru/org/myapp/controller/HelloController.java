package ru.org.myapp.controller;

import com.example.config.annotation.Audit;
import com.example.config.annotation.Log;
import com.uber.cadence.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.entity.common.SampleMessage;
import ru.org.myapp.workflow.HelloWorldWorkflow;


@RestController
@RequiredArgsConstructor
public class HelloController {
    private final WorkflowClient workflowClient;

    @Audit
    @Log
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(defaultValue = "World") String name) {
        var stub = workflowClient.newWorkflowStub(HelloWorldWorkflow.class);
        var greeting = stub.sayHello(new SampleMessage(name));
        return ResponseEntity.ok(greeting);
    }
}
