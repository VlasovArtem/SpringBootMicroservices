package org.avlasov.photoapp.api.albums.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumEntity implements Serializable {

    private long id;
    private String albumId;
    private String userId;
    private String name;
    private String description;

}
