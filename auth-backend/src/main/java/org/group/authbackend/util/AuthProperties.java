package org.group.authbackend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class AuthProperties {

    @Value("${auth.login}")
    private String login;

    @Value("${auth.csrf}")
    private String csrf;
}
