package ru.org.myapp.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.org.myapp.dto.WeatherDto
import ru.org.myapp.dto.WeatherForecastDto
import ru.org.myapp.entity.common.Response

@Tag(name = "Weather API", description = "API для работы с погодой")
@RequestMapping("/api/v1")
interface WeatherRestApi {
    @Operation(
        summary = "Получить текущую погоду",
        description = "Возвращает текущую погоду для указанного города",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Успешный запрос"
        ), ApiResponse(responseCode = "404", description = "Город не найден")]
    )
    @GetMapping("/weather")
  open fun getWeather(
        @Parameter(description = "Название города", example = "Novosibirsk") @RequestParam(value = "city") city: String?
    ): ResponseEntity<Response<WeatherDto?>?>?

    @Operation(
        summary = "Получить прогноз погоды",
        description = "Возвращает прогноз погоды на 5 дней с интервалом 3 часа для указанного города",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Успешный запрос"
        ), ApiResponse(responseCode = "404", description = "Город не найден")]
    )
    @GetMapping("/forecast")
    open fun getForecast(
        @Parameter(description = "Название города", example = "Novosibirsk") @RequestParam(value = "city") city: String?
    ): ResponseEntity<Response<List<WeatherForecastDto?>?>?>?
}

