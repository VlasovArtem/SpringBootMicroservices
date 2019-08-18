package org.avlasov.photoapp.api.albums.service;

import org.avlasov.photoapp.api.albums.data.AlbumEntity;

import java.util.List;

public interface AlbumsService {

    List<AlbumEntity> getAlbums(String userId);

}
