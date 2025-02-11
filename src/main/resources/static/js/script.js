$(document).ready(function() {
    let forecastLoaded = false;
    let forecastVisible = false;

    $('#search-button').on('click', function() {
        var city = $('#city-input').val();
        if (city) {
            clearPreviousResults();
            fetchWeather(city);
        }
    });

    $('#forecast-button').on('click', function() {
        var city = $('#city-input').val();
        var forecastResults = $('#forecast-results');
        if (forecastLoaded) {
            if (forecastVisible) {
                forecastResults.slideUp();
            } else {
                forecastResults.slideDown();
            }
            forecastVisible = !forecastVisible;
        } else {
            fetchForecast(city);
        }
    });

    $(document).on('click', '.btn-close', function() {
        clearPreviousResults();
        $('#search-form').slideDown();
    });

    $('#clear-input-button').on('click', function() {
        $('#city-input').val('');
    });

    function fetchWeather(city) {
        $.ajax({
            url: '/api/v1/weather',
            method: 'GET',
            data: { city: city },
            success: function(weather) {
                console.log('Weather data received:', weather);
                displayWeather(weather);
                $('#forecast-button').fadeIn();
            },
            error: function(xhr, status, error) {
                console.error('Error retrieving weather data:', status, error);
                alert('Error retrieving weather data');
            }
        });
    }

    function fetchForecast(city) {
        $.ajax({
            url: '/api/v1/forecast',
            method: 'GET',
            data: { city: city },
            success: function(forecast) {
                console.log('Forecast data received:', forecast);
                displayForecast(forecast);
                $('#forecast-results').slideDown();
                forecastLoaded = true;
                forecastVisible = true;
            },
            error: function(xhr, status, error) {
                console.error('Error retrieving forecast data:', status, error);
                alert('Error retrieving forecast data');
            }
        });
    }

    function displayWeather(weather) {
        var weatherHtml = `
            <div class="card h-100 position-relative weather-card fade-in">
                <div class="position-absolute weather-card-delete-form">
                    <button class="btn-close" aria-label="Delete"></button>
                </div>
                <img class="card-img-top img-fluid"
                     src="${weather.weatherStateDto.weatherIconUrl}"
                     alt="Weather icon">
                <div class="card-body d-flex flex-column">
                    <h1 class="card-text">${weather.temperatureDto.value}°C</h1>
                    <h3 class="card-title">${weather.locationDto.name}, ${weather.locationDto.countryCode}</h3>
                    <p class="card-text mb-1">Feels like <span>${weather.temperatureDto.feelsLike}</span>°C.
                        <span>${weather.weatherStateDto.description}</span>
                    </p>
                    <p class="card-text mb-1">Humidity: ${weather.humidityDto.value}%</p>
                    <p class="card-text mb-1">Pressure: ${weather.atmosphericPressureDto.value} ${weather.atmosphericPressureDto.unit}</p>
                    <p class="card-text mb-1">Wind: ${weather.windDto.speed} ${weather.windDto.unit} at ${weather.windDto.degrees}°</p>
                    <p class="card-text mb-1">Cloudiness: ${weather.cloudsDto.value}${weather.cloudsDto.unit}</p>
                    <p class="card-text mb-1">Rain (last 3 hours): ${weather.rainDto ? weather.rainDto.threeHourLevel : 'N/A'} ${weather.rainDto ? weather.rainDto.unit : ''}</p>
                    <p class="card-text mb-1">Snow (last 3 hours): ${weather.snowDto ? weather.snowDto.threeHourLevel : 'N/A'} ${weather.snowDto ? weather.snowDto.unit : ''}</p>
                    <p class="card-text mb-1">Sunrise: ${new Date(weather.locationDto.sunriseTime).toLocaleTimeString()}</p>
                    <p class="card-text mb-1">Sunset: ${new Date(weather.locationDto.sunsetTime).toLocaleTimeString()}</p>
                </div>
            </div>
        `;
        $('#weather-results').html(weatherHtml).show().addClass('fade-in');
        $('#search-form').slideUp();
    }

    function displayForecast(forecast) {
        var forecastHtml = forecast.map(function(forecastItem) {
            return `
                <div class="card mb-3 fade-in">
                    <div class="card-body">
                        <h5 class="card-title">${new Date(forecastItem.forecastTime).toLocaleString()}</h5>
                        <img src="${forecastItem.weatherState.weatherIconUrl}" alt="Weather icon" class="weather-icon">
                        <p class="card-text">Temperature: ${forecastItem.temperature.value}°C (Feels like: ${forecastItem.temperature.feelsLike}°C)</p>
                        <p class="card-text">Weather: ${forecastItem.weatherState.description}</p>
                        <p class="card-text">Humidity: ${forecastItem.humidity.value}%</p>
                        <p class="card-text">Pressure: ${forecastItem.atmosphericPressure.value} ${forecastItem.atmosphericPressure.unit}</p>
                        <p class="card-text">Wind: ${forecastItem.wind.speed} ${forecastItem.wind.unit} at ${forecastItem.wind.degrees}°</p>
                        <p class="card-text">Cloudiness: ${forecastItem.clouds.value}${forecastItem.clouds.unit}</p>
                        <p class="card-text">Rain (last 3 hours): ${forecastItem.rain ? forecastItem.rain.threeHourLevel : 'N/A'} ${forecastItem.rain ? forecastItem.rain.unit : ''}</p>
                        <p class="card-text">Snow (last 3 hours): ${forecastItem.snow ? forecastItem.snow.threeHourLevel : 'N/A'} ${forecastItem.snow ? forecastItem.snow.unit : ''}</p>
                    </div>
                </div>
            `;
        }).join('');
        $('#forecast-results').html(forecastHtml).show().addClass('fade-in');
    }

    function clearPreviousResults() {
        $('#weather-results').hide().empty();
        $('#forecast-results').hide().empty();
        forecastLoaded = false;
        forecastVisible = false;
        $('#forecast-button').fadeOut();
    }

    function clearInput() {
        $('#city-input').val('');
    }
});