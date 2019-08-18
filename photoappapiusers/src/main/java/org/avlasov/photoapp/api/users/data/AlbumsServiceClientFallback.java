package org.avlasov.photoapp.api.users.data;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.avlasov.photoapp.api.users.ui.model.AlbumResponseModel;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Slf4j
public class AlbumsServiceClientFallback implements AlbumsServiceClient {

    private final Throwable cause;

    public AlbumsServiceClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String userId) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == HttpStatus.NOT_FOUND.value()) {
            log.error("404 error took place when getAlbums was called with userId: " + userId + ". Error message: " + cause.getLocalizedMessage());
        } else {
            log.error("Other error took place: " + cause.getLocalizedMessage());
        }
        return Collections.emptyList();
    }

}
