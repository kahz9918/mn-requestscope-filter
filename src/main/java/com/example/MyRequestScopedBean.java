package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.http.scope.RequestScope;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@RequestScope
public class MyRequestScopedBean implements IMyRequestScopedBean {
    private String randomAttrib;
}
