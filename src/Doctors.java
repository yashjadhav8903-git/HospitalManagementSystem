import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctors {
    private Connection connection;

    public Doctors(Connection connection){
        this.connection = connection;
    }

    public void viewDoctors(){
        String query = "select * from doctor";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors");
            System.out.println("+-------------+-----------------------+------------------------------------+");
            System.out.println("| Doctors Id  | Name                  | Specialization                     |");
            System.out.println("+-------------+-----------------------+------------------------------------+");

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-12s| %-22s| %-35s|\n",id,name,specialization);
                System.out.println("+-------------+-----------------------+------------------------------------+");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Boolean viewDoctorsById(int id){
        String query = "select * from doctor where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
