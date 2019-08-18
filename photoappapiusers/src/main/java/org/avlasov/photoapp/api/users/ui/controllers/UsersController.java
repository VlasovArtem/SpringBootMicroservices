package org.avlasov.photoapp.api.users.ui.controllers;

import org.avlasov.photoapp.api.users.service.UsersService;
import org.avlasov.photoapp.api.users.shared.UserDto;
import org.avlasov.photoapp.api.users.ui.model.CreateUserRequestModel;
import org.avlasov.photoapp.api.users.ui.model.CreateUserResponseModel;
import org.avlasov.photoapp.api.users.ui.model.UserResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final Environment env;

    public UsersController(UsersService usersService, ModelMapper modelMapper, Environment env) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.env = env;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + env.getProperty("local.server.port") + ", with token = " + env.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = usersService.createUser(userDto);

        CreateUserResponseModel userResponseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = usersService.getUserByUserId(userId);

        UserResponseModel responseModel = modelMapper.map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }

}
