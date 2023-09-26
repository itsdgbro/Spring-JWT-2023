package com.jwt.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Component
public class CustomErrorResponse {
    private String error;
    private String message;
}
