package ru.org.myapp.entity.common;

import lombok.Builder;

@Builder
public record Response<T>(ResponseStatus status, T data) {
}
