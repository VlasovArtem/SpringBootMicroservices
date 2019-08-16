package org.avlasov.photoapp.api.users.ui.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestModel {

    @NotNull
    private String email;
    @NotNull
    private String password;

}
