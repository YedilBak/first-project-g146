package kz.first_project.project.db;

import kz.first_project.project.model.BankUser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static int id = 7;

    @Getter
    private static List<BankUser> users = new ArrayList<>();

    static {
        users.add(new BankUser(2, "Berik Berikov", "Astana", 7, 9301231239L));
        users.add(new BankUser(3, "Merik Merikov", "Shymkent", 6, 671231233L));
        users.add(new BankUser(4, "Jerik Jerikov", "Almaty", 10, 9501231239L));
        users.add(new BankUser(5, "Kerik Kerikov", "Taraz", 2, 9801231239L));
        users.add(new BankUser(6, "Lerik Lerikov", "Atirau", 1, 7301231239L));
    }

    public static void addUser(BankUser user) {
        user.setId(id++);
        users.add(user);
    }

    public static BankUser getUserByID(int id) {

        return users.stream().filter(user -> user.getId()==id).findFirst().orElseThrow();
    }

    public static void updateUser(BankUser bankUser) {

        BankUser userFromBase = getUserByID(bankUser.getId());

        if(userFromBase!=null){
               userFromBase.setFullName(bankUser.getFullName());
               userFromBase.setIin(bankUser.getIin());
               userFromBase.setCity(bankUser.getCity());
               userFromBase.setRating(bankUser.getRating());

        }
    }

    public static void deleteUser(int id) {

        users.removeIf(user-> user.getId()==id);

    }
}
