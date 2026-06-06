package kz.first_project.project.dto;

import kz.first_project.project.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {

    private Integer id;

    private String nameSurname;

    private double rating;

    private long iin;

    private City city;

}
