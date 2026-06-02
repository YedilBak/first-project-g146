package kz.first_project.project.service.impl;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model2.User;
import kz.first_project.project.repository.BankUserRepository;
import kz.first_project.project.repository2.UserRepository;
import kz.first_project.project.service.BankUserService;
import kz.first_project.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUserToBase(User user) {
        userRepository.save(user);
    }
}
