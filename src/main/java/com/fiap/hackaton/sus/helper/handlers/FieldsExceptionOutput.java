package com.fiap.hackaton.sus.helper.handlers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FieldsExceptionOutput {

    private String name;
    private String message;
}
