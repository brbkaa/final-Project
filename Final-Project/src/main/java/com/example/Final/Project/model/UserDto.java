package com.example.Final.Project.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Integer userId;
    private String userName;
    private String youtubeVideo;
    private String isAdmin;
    private Integer viewsCount;
}
