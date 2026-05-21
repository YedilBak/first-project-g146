package kz.first_project.project.repository;
import jakarta.transaction.Transactional;
import kz.first_project.project.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Integer> {
}
