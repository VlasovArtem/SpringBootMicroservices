package org.avlasov.photoapp.api.users.ui.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must not be less than two characters")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must not be less than two characters")
    private String lastName;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must equal or greater than 8 characters and less then 16 characters")
    private String password;
    @Email
    private String email;


}
