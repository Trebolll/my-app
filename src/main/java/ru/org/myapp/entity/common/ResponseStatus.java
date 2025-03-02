package ru.org.myapp.entity.common;

import lombok.Builder;

@Builder
public record ResponseStatus(Integer code, String description) {
}
