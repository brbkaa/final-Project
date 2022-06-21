package com.example.Final.Project.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "NEWUSERSTABLE")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "GENERATEID", sequenceName = "GENERATEID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATEID")

    @Column(name = "USERID")
    private Integer userId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "YOUTUBEVIDEO")
    private String youtubeVideo;

    @Column(name = "ISADMIN")
    private String isAdmin;

    @Column(name = "VIEWSCOUNT")
    private Integer viewsCount;
}
