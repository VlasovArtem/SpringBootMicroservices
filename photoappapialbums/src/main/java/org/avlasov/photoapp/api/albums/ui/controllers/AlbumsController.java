package org.avlasov.photoapp.api.albums.ui.controllers;

import org.avlasov.photoapp.api.albums.data.AlbumEntity;
import org.avlasov.photoapp.api.albums.service.AlbumsService;
import org.avlasov.photoapp.api.albums.ui.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users/{id}/albums")
public class AlbumsController {

    private final AlbumsService albumsService;
    private final ModelMapper modelMapper;
    private final Type userAlbumsType;

    public AlbumsController(AlbumsService albumsService, ModelMapper modelMapper) {
        this.albumsService = albumsService;
        this.modelMapper = modelMapper;
        userAlbumsType = new TypeToken<List<AlbumResponseModel>>(){}.getType();
    }

    @GetMapping
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if(albumsEntities == null || albumsEntities.isEmpty()) {
            return returnValue;
        }

        returnValue = modelMapper.map(albumsEntities, userAlbumsType);

        return returnValue;
    }

}
