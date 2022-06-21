package com.example.Final.Project.controller;

import com.example.Final.Project.model.UserDto;
import com.example.Final.Project.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}/getUser")
    public ResponseEntity<UserDto> getUser(@NonNull @PathVariable(value = "id") Integer id){
        UserDto result = userService.getUser(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}/getUsers")
    public ResponseEntity< List<UserDto> > getUsers(@NonNull @PathVariable(value = "id") Integer id){
        List<UserDto> result = userService.getUsers(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/{id}/create")
    public ResponseEntity<Void> createUser(@NonNull @RequestBody UserDto userDto,
                                           @NonNull @PathVariable(value = "id") Integer id){
        userService.createUser(userDto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/makeAdmin/{reqId}")
    public ResponseEntity<Void> updateAsAdmin(@NonNull @PathVariable(value = "id") Integer id,
                                          @NonNull @PathVariable(value = "reqId") Integer reqId){
        userService.updateAsAdmin(id, reqId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/updateUser/{reqId}")
    public ResponseEntity<Void> updateUser(@NonNull @PathVariable(value = "id") Integer id,
                                           @NonNull @PathVariable(value = "reqId") Integer reqId,
                                           @NonNull @RequestBody UserDto userDto){
        userService.updateUser(id, reqId, userDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/deleteUser/{reqId}")
    public ResponseEntity<Void> deleteUser(@NonNull @PathVariable(value = "id") Integer id,
                                           @NonNull @PathVariable(value = "reqId") Integer reqId){
        userService.deleteUser(id, reqId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
