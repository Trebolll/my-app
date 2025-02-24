package ru.org.myapp;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class MyAppApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMainWhenApplicationStartsThenSuccess() {
        ApplicationContextRunner contextRunner = new ApplicationContextRunner().withUserConfiguration(MyAppApplication.class);
        contextRunner.run(context -> {
            assertThat(context).isNotNull();
            assertThat(context).isInstanceOf(ApplicationContext.class);
        });
    }
}
