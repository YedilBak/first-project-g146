package kz.first_project.project.repository;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
}
