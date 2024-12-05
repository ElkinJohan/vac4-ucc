package com.ucc.vacCauca.domain.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    public LoginForm() {
    }

    public LoginForm(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
