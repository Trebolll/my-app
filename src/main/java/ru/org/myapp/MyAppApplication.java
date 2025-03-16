package ru.org.myapp;

import com.uber.cadence.client.WorkflowClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.org.myapp.entity.common.SampleMessage;
import ru.org.myapp.workflows.HelloWorldWorkflow;

@SpringBootApplication
@EntityScan(basePackages = "ru.org.myapp.entity")
@EnableJpaRepositories(basePackages = "ru.org.myapp.repository")
@ComponentScan({"ru.org.myapp", "ru.org.myapp.config.cadence"})
@EnableCaching
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner startHelloWorkflow(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Start one synchronous HelloWorld workflow");
//
//            WorkflowClient workflowClient = ctx.getBean(WorkflowClient.class);
//            HelloWorldWorkflow stub = workflowClient.newWorkflowStub(HelloWorldWorkflow.class);
//            stub.sayHello(new SampleMessage("hello"));
//
//            System.out.println("Synchronous HelloWorld workflow finished");
//          //  System.exit(SpringApplication.exit(ctx, () -> 0));
//        };
//    }
}
