package org.avlasov.photoapp.api.users.shared;

import lombok.Data;
import org.avlasov.photoapp.api.users.ui.model.AlbumResponseModel;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;

}
