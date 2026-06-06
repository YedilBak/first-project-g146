package kz.first_project.project.service;

import kz.first_project.project.dto.BankUserDto;
import kz.first_project.project.model.BankUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankUserService {

    List<BankUser> getAllUsersFromBase();

    List<BankUser> getAllUsersByWord(String word);

    void addUserToBase(BankUser bankUser);

    BankUser getUserById(int id);

    void deleteUserById(int id);

    List<BankUser> findBankUsersByRatingGreaterThan(double rating);

    List<BankUser> findBankUsersByRatingAndFullName(Double rating, String fullName);

    List<BankUser> findBankUsersByRatingSort();

    Page<BankUser> findAll(Pageable pageable);

    Page<BankUser> findByRatingGreaterThan(double rating, Pageable pageable);

    BankUserDto toDto(BankUser bankUser);

    List<BankUserDto> toDtoList(List<BankUser> bankUsers);


}
