package com.example;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestService {
    @Inject
    private IMyRequestScopedBean requestScopedBean;

    public String testBean() {
        return requestScopedBean.getRandomAttrib();
    }
}
