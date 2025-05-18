package org.group.authbackend.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CsrfTokenDto {
    public String token;
    public String headerName;
    public String parameterName;
    private String sessionId;

}
