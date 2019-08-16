package org.avlasov.photoapp.api.users.service;

import org.avlasov.photoapp.api.users.data.UserEntity;
import org.avlasov.photoapp.api.users.data.UsersRepository;
import org.avlasov.photoapp.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsersServiceImpl(ModelMapper modelMapper, UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(username, userEntity.getEncryptedPassword(), Collections.emptyList());
    }
}
