package kz.first_project.project.repository;

import kz.first_project.project.model.BankUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankUserCustomRepository {

    List<BankUser> findBankUsersByRatingGreaterThan(double rating);

    List<BankUser> findBankUsersByRatingAndFullName(Double rating, String fullName);

    List<BankUser> findBankUsersByRatingSort();

}
