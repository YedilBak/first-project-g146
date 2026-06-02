package kz.first_project.project.service.impl;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model.City;
import kz.first_project.project.repository.BankUserRepository;
import kz.first_project.project.repository.CityRepository;
import kz.first_project.project.service.BankUserService;
import kz.first_project.project.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
