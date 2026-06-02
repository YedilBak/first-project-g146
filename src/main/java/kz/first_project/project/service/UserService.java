package kz.first_project.project.service;

import kz.first_project.project.model2.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addUserToBase(User user);
}
