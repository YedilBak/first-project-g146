package kz.first_project.project.db;

import kz.first_project.project.model.BankUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private static Connection connection;
    private static String login = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5433/G146";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static List<BankUser> getAllUsersFromBase(){

        ArrayList<BankUser> users = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_users");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                BankUser bankUser = new BankUser();

                bankUser.setId(resultSet.getInt("id"));
                bankUser.setIin(resultSet.getLong("iin"));
                bankUser.setRating(resultSet.getDouble("rating"));
                bankUser.setFullName(resultSet.getString("full_name"));
                bankUser.setCity(resultSet.getString("city"));

                users.add(bankUser);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    public static BankUser findBankUserById(int id){

        BankUser user = new BankUser();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_users WHERE id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setIin(resultSet.getLong("iin"));
                user.setFullName(resultSet.getString("full_name"));
                user.setCity(resultSet.getString("city"));
                user.setRating(resultSet.getDouble("rating"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

}
