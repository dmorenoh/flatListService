package io.flatservice.service;

import lombok.Value;

@Value(staticConstructor = "of")
public class Token {
    String value;
}
