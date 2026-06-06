package kz.first_project.project.service;

import kz.first_project.project.dto.CityDto;
import kz.first_project.project.model.City;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CityService {

    List<City> getAllCities();

    City addCity(City city);

    City updateCity(City city);

    City updatePatch(String name, int id);

    City getCity(int id);

    void deleteCity(int id);

    CityDto toDto(City city);

    List<CityDto> toDtosList(List<City> cities);
}
