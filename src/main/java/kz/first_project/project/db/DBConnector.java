package kz.first_project.project.db;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model.City;

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_users b " +
                    "INNER JOIN cities c " +
                    "ON b.city_id=c.id ORDER BY b.id ASC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                BankUser bankUser = new BankUser();
                bankUser.setId(resultSet.getInt("id"));
                bankUser.setIin(resultSet.getLong("iin"));
                bankUser.setRating(resultSet.getDouble("rating"));
                bankUser.setFullName(resultSet.getString("full_name"));

                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                city.setCode(resultSet.getString("code"));
                city.setName(resultSet.getString("name"));
                city.setCountPeople(resultSet.getLong("count_people"));
                city.setDescription(resultSet.getString("description"));

                bankUser.setCity(city);

                users.add(bankUser);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    public static List<City> getAllCities(){

        List<City> cities = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank.cities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCode(resultSet.getString("code"));
                city.setName(resultSet.getString("name"));
                city.setDescription(resultSet.getString("description"));
                city.setCountPeople(resultSet.getLong("count_people"));

                cities.add(city);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cities;
    }

    public static BankUser findBankUserById(int id){

        BankUser user = new BankUser();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_users b " +
                    "INNER JOIN cities c " +
                    "ON b.city_id=c.id WHERE b.id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setIin(resultSet.getLong("iin"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRating(resultSet.getDouble("rating"));

                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                city.setCode(resultSet.getString("code"));
                city.setName(resultSet.getString("name"));
                city.setCountPeople(resultSet.getLong("count_people"));
                city.setDescription(resultSet.getString("description"));

                user.setCity(city);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

    public static void addUserToBase(BankUser bankUser){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO bank.bank_users(full_name "+
                    ", rating, iin, city_id) VALUES (?, ?, ?, ?)");

            statement.setString(1, bankUser.getFullName());
            statement.setDouble(2, bankUser.getRating());
            statement.setLong(3, bankUser.getIin());
            statement.setInt(4, bankUser.getCity().getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUser(BankUser bankUser){

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE bank.bank_users SET " +
                    "full_name=?, city_id=?, rating=?, iin=? WHERE id=?");

            statement.setString(1, bankUser.getFullName());
            statement.setInt(2, bankUser.getCity().getId());
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
