package com.holovanovmax.springboot_security_api.rest_api.model.userInformation

import java.security.Principal

class TestPrincipal implements Principal{

    private String name

    TestPrincipal(String name) {
        this.name = name
    }

    @Override
    String getName() {
        return this.name
    }
}
