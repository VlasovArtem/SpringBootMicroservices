package org.avlasov.photoapp.api.users.data;

import org.avlasov.photoapp.api.users.ui.model.AlbumResponseModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AlbumsFallback implements AlbumsServiceClient {

    @Override
    public List<AlbumResponseModel> getAlbums(String userId) {
        return Collections.emptyList();
    }

}
