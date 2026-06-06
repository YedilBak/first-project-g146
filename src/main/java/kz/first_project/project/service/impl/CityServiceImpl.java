package kz.first_project.project.service.impl;

import kz.first_project.project.dto.CityDto;
import kz.first_project.project.model.BankUser;
import kz.first_project.project.model.City;
import kz.first_project.project.repository.BankUserRepository;
import kz.first_project.project.repository.CityRepository;
import kz.first_project.project.service.BankUserService;
import kz.first_project.project.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City updatePatch(String name, int id) {

        City city = getCity(id);

        if(city!=null){
            city.setName(name);
            addCity(city);
            return city;
        }

        return null;
    }

    @Override
    public City getCity(int id) {
        return cityRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityDto toDto(City city) {

        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setCodeCity(city.getCode());
        cityDto.setNameCity(city.getName());
        cityDto.setDescription(city.getDescription());
        cityDto.setCountPeople(city.getCountPeople());

        return cityDto;
    }

    @Override
    public List<CityDto> toDtosList(List<City> cities) {

        List<CityDto> cityDtoList = new ArrayList<>();

        for(City city: cities){
            cityDtoList.add(toDto(city));
        }

        return cityDtoList;
    }
}
