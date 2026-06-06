package kz.first_project.project.mapper;

import kz.first_project.project.dto.CityDto;
import kz.first_project.project.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(source = "name", target = "nameCity")
    @Mapping(source = "code", target = "codeCity")
    CityDto toDto(City city);

    @Mapping(source = "nameCity", target = "name")
    @Mapping(source = "codeCity", target = "code")
    City toModel(CityDto cityDto);

    List<CityDto> toDtosList(List<City> cities);
    List<City> toModelList(List<CityDto> cityDtoList);
}
