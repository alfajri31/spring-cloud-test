package org.group.authbackend.model.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsrfTokenDto {
    public String token;
    public String headerName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String parameterName;

}
