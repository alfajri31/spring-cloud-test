package org.group.authbackend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AuthProperties {

    AuthProperties() {
    }

    @Value("${auth.login}")
    private String login;

    @Value("${auth.csrf}")
    private String csrf;

    public String getLogin() {
        return login;
    }
    public String getCsrf() {
        return csrf;
    }

}
