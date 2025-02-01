package ru.org.myapp.controller;

import com.example.config.annotation.AuditExecution;
import com.example.config.annotation.LogExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.org.myapp.service.TemperatureService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TemperatureController {
    private final TemperatureService temperatureService;
    //http://localhost:8081/api/v1/temp?city=Vladivostok
    @AuditExecution
    @LogExecution
    @GetMapping("/temp")
    public ResponseEntity<String> getTemperature(@RequestParam String city) {
        return new ResponseEntity<>(temperatureService.getTemperature(city), HttpStatus.OK);
    }
}
