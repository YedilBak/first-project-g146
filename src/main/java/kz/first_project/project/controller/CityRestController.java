package kz.first_project.project.controller;

import kz.first_project.project.dto.CityDto;
import kz.first_project.project.mapper.CityMapper;
import kz.first_project.project.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city-api")
@RequiredArgsConstructor
public class CityRestController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping(value = "/")
    public List<CityDto> getAllCities(){
        return cityMapper.toDtosList(cityService.getAllCities());
    }

    @PostMapping(value = "/add")
    public CityDto addCity(@RequestBody CityDto city){
        return cityMapper.toDto(cityService.addCity(cityMapper.toModel(city)));
    }

    @PutMapping(value = "/update")
    public CityDto updateCity(@RequestBody CityDto city){
        return cityMapper.toDto(cityService.updateCity(cityMapper.toModel(city)));
    }


    @GetMapping(value = "/get-city/{id}")
    public CityDto getCity(@PathVariable int id){
        return cityMapper.toDto(cityService.getCity(id));
    }


    @PatchMapping(value = "/update-patch")
    public CityDto updatePatchCity(@RequestParam String name,
                                @RequestParam int id){

        return cityMapper.toDto(cityService.updatePatch(name, id));

    }

    @DeleteMapping(value = "/delete")
    public void deleteCity(@RequestParam int id){

        cityService.deleteCity(id);
    }

}
