import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {

    private  Connection connection;
    private  Scanner scanner;

    public Patients(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;

    }

    public void addPatients(){
        System.out.println("Enter the Patient Name : ");
        String name = scanner.next();


        System.out.println("Enter the Patients Age : ");
        int age = scanner.nextInt();

        System.out.println("Enter the Patients Gender : ");
        String gender = scanner.next();


        System.out.println("Enter the Patients Address : ");
        String address = scanner.next();


        try{
            String query = "insert into patient (name , age, gender, address) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,address);

            int AffectedRow = preparedStatement.executeUpdate();

            if(AffectedRow > 0 ){
                System.out.println("Patient Added Successfully");
            } else {
                System.out.println("Failed to Add Patient");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void viewPatient(){
        String query = "select * from patient";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultset = preparedStatement.executeQuery();

            System.out.println("Patients : ");
            System.out.println("+-------------+--------------------+-------+----------+------------------------+");
            System.out.println("| Patients id | Name               | Age   | Gender   | Address                |");
            System.out.println("+-------------+--------------------+-------+----------+------------------------+");

            while (resultset.next()){
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                int age = resultset.getInt("age");
                String gender = resultset.getString("gender");
                String address = resultset.getString("address");
                System.out.printf("| %-12s| %-19s| %-6s| %-9s| %-23s|\n",id,name,age,gender,address);
                System.out.println("+-------------+--------------------+-------+----------+------------------------+");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean CheckPatientById(int id){
        String query = "select * from patient where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            } else {
                return  false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  false;
    }
}
