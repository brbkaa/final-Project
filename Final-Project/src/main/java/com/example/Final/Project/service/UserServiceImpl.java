package com.example.Final.Project.service;

import com.example.Final.Project.domain.User;
import com.example.Final.Project.model.UserDto;
import com.example.Final.Project.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final Repository userRepository;
    private final YoutubeAPI ytApi;
    @Override
    //finding user by id
    public UserDto getUser(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional <User> res = userRepository.findById(id);
        UserDto resDto = UserDto.builder()
                .userId(res.get().getUserId())
                .userName(res.get().getUserName())
                .youtubeVideo(res.get().getYoutubeVideo())
                .isAdmin(res.get().getIsAdmin())
                .viewsCount(res.get().getViewsCount())
                .build();

        return resDto;
    }

    @Override
    //return list of all users
    public List<UserDto> getUsers(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!userOpt.get().getIsAdmin().equals("y")){  //only admins can see the full list
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Iterable<User> users = userRepository.findAll();
        List<UserDto> res = new ArrayList<>();
        for (User u : users) {
            UserDto user = UserDto.builder()
                    .userId(u.getUserId())
                    .userName(u.getUserName())
                    .youtubeVideo(u.getYoutubeVideo())
                    .isAdmin(u.getIsAdmin())
                    .build();
            res.add(user);
        }
        return res;
    }

    @Override
    //creating user
    public void createUser(UserDto userDto, Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!userRepository.findById(id).get().getIsAdmin().equals("y")) {  //only admins can create users
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        User newUser= User.builder()
                .userName(userDto.getUserName())
                .youtubeVideo(userDto.getYoutubeVideo())
                .isAdmin("n")
                .viewsCount(Integer.valueOf(ytApi.viewsCount(userDto.getYoutubeVideo())))
                .build();
        userRepository.save(newUser);
    }

    @Override
    public void updateAsAdmin(Integer id, Integer reqId) { //only admins can update user as admin
        if(userRepository.findById(id).isEmpty() || userRepository.findById(reqId).isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!userRepository.findById(id).get().getIsAdmin().equals("y")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        userRepository.findById(reqId).get().setIsAdmin("y");
        userRepository.save(userRepository.findById(reqId).get());
    }

    @Override
    //updating user information
    public void updateUser(Integer id, Integer reqId, UserDto userDto) {
        Optional<User> userOpt = userRepository.findById(id);
        Optional<User> reqUserOpt = userRepository.findById(reqId);
        if(userOpt.isEmpty() || reqUserOpt.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(id != reqId && !userOpt.get().getIsAdmin().equals("y")){ //users also can update their data
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        User user = userOpt.get();
        User reqUser = reqUserOpt.get();
        userRepository.save(User.builder()
                        .userId(reqId)
                        .userName(userDto.getUserName())
                        .youtubeVideo(userDto.getYoutubeVideo())
                        .isAdmin(reqUser.getIsAdmin())
                            .build());
    }

    @Override
    //deleteing users
    public void deleteUser(Integer id, Integer reqId) {
        Optional<User> userOpt = userRepository.findById(id);
        Optional<User> reqUserOpt = userRepository.findById(reqId);
        if(userOpt.isEmpty() || reqUserOpt.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(!userOpt.get().getIsAdmin().equals("y")){ //only admins can delete user as expected :))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        userRepository.deleteById(reqId);
    }


}
