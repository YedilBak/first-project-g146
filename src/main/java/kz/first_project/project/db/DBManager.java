package kz.first_project.project.db;

import kz.first_project.project.model.BankUser;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class DBManager {

    @Getter
    private static List<BankUser> users = Arrays.asList(
            new BankUser(1, "Serik Serikov", "Almaty", 8, 9301231239L),
            new BankUser(2, "Berik Berikov", "Astana", 7, 9301231239L),
            new BankUser(3, "Merik Merikov", "Shymkent", 6, 671231233L),
            new BankUser(4, "Jerik Jerikov", "Almaty", 10, 9501231239L),
            new BankUser(5, "Kerik Kerikov", "Taraz", 2, 9801231239L),
            new BankUser(6, "Lerik Lerikov", "Atirau", 1, 7301231239L));


}
