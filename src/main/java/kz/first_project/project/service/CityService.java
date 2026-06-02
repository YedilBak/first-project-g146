package kz.first_project.project.service;

import kz.first_project.project.model.City;
import kz.first_project.project.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CityService {

    List<City> getAllCities();
}
