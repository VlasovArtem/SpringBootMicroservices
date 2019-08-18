package org.avlasov.photoapp.api.users.ui.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseModel {

    private String firstName;
    private String lastName;
    private String userId;
    private String email;
    private List<AlbumResponseModel> albums;

}
