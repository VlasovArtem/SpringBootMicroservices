package org.avlasov.photoapp.api.users.data;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumsFallbackFactory implements FallbackFactory<AlbumsServiceClient> {

    @Override
    public AlbumsServiceClient create(Throwable cause) {
        return new AlbumsServiceClientFallback(cause);
    }

}
