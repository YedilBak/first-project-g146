package kz.first_project.project.service.impl;

import kz.first_project.project.model.City;
import kz.first_project.project.model.PhoneNumber;
import kz.first_project.project.repository.CityRepository;
import kz.first_project.project.repository.PhoneNumberRepository;
import kz.first_project.project.service.CityService;
import kz.first_project.project.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NumberServiceImpl implements NumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    @Override
    public List<PhoneNumber> getAllPhones() {
        return phoneNumberRepository.findAll();
    }
}
