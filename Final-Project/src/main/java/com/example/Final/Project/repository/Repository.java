package com.example.Final.Project.repository;

import com.example.Final.Project.domain.User;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<User, Integer> {

}
