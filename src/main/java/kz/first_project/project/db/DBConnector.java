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
    private static String url = "jdbc:postgresql://localhost:5436/G146?currentSchema=bank";

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_users ORDER BY id ASC");

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

    public static void addUserToBase(BankUser bankUser){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO bank.bank_users(full_name, city "+
                    ", rating, iin) VALUES (?, ?, ?, ?)");

            statement.setString(1, bankUser.getFullName());
            statement.setString(2, bankUser.getCity());
            statement.setDouble(3, bankUser.getRating());
            statement.setLong(4, bankUser.getIin());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUser(BankUser bankUser){

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE bank.bank_users SET " +
                    "full_name=?, city=?, rating=?, iin=? WHERE id=?");

            statement.setString(1, bankUser.getFullName());
            statement.setString(2, bankUser.getCity());
            statement.setDouble(3, bankUser.getRating());
            statement.setLong(4, bankUser.getIin());
            statement.setInt(5, bankUser.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteUser(int id){

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM bank.bank_users WHERE id=?");

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
