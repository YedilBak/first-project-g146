package kz.first_project.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private Integer id;
    private String nameCity;
    private String codeCity;
    private String description;
    private long countPeople;


}
