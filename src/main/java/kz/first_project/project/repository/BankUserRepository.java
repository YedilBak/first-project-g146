package kz.first_project.project.repository;

import kz.first_project.project.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Integer> {

    @Query("SELECT u FROM BankUser u WHERE u.fullName ilike concat('%', :w, '%') " +
            "OR u.city.name ilike concat('%', :w, '%')")
    List<BankUser> searchByWord(String w);
}
