package kz.first_project.project.controller;

import kz.first_project.project.model.City;
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

    @GetMapping(value = "/")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @PostMapping(value = "/add")
    public City addCity(@RequestBody City city){
        return cityService.addCity(city);
    }

    @PutMapping(value = "/update")
    public City updateCity(@RequestBody City city){
        return cityService.updateCity(city);
    }


    @GetMapping(value = "/get-city/{id}")
    public City getCity(@PathVariable int id){
        return cityService.getCity(id);
    }


    @PatchMapping(value = "/update-patch")
    public City updatePatchCity(@RequestParam String name,
                                @RequestParam int id){

        return cityService.updatePatch(name, id);

    }

    @DeleteMapping(value = "/delete")
    public void deleteCity(@RequestParam int id){

        cityService.deleteCity(id);
    }

}
