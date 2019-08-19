package org.avlasov.photoapp.api.users.service;

import lombok.extern.slf4j.Slf4j;
import org.avlasov.photoapp.api.users.data.AlbumsServiceClient;
import org.avlasov.photoapp.api.users.data.UserEntity;
import org.avlasov.photoapp.api.users.data.UsersRepository;
import org.avlasov.photoapp.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AlbumsServiceClient albumsServiceClient;
    private final RestTemplate restTemplate;
    private final Environment env;

    public UsersServiceImpl(ModelMapper modelMapper, UsersRepository usersRepository,
                            BCryptPasswordEncoder passwordEncoder,
                            AlbumsServiceClient albumsServiceClient,
                            RestTemplate restTemplate, Environment env) {
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.albumsServiceClient = albumsServiceClient;
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));

        usersRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findUser(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);

        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

//        String albumsUrl = String.format(env.getProperty("api.url.albums"), userId);
//
//        ResponseEntity<List<AlbumResponseModel>> exchange = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//        });

        log.info("Before calling albums Microservice");

        userDto.setAlbums(albumsServiceClient.getAlbums(userId));

        log.info("After calling albums Microservice");

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(username, userEntity.getEncryptedPassword(), Collections.emptyList());
    }
}
