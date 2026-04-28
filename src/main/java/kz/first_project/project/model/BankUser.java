package kz.first_project.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BankUser {

    private int id;
    private String fullName;
    private String city;
    private double rating;
    private long iin;

}
