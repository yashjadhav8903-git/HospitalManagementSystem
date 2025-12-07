import java.sql.*;
import java.util.Scanner;

public class Bookings {

    public static void BookAppointment(Patients patients, Doctors doctors, Connection connection, Scanner scanner){
        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.println("Enter Appointment Date (yyyy-mm-dd):");
        String appointmentDate = scanner.next();

        if(patients.CheckPatientById(patientId) && doctors.viewDoctorsById(doctorId)){
            if(CheckDoctorAvailability(doctorId,appointmentDate,connection)){
                String query = "insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
                try{
                    PreparedStatement preparedStatement =connection.prepareStatement(query);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setDate(3, java.sql.Date.valueOf(appointmentDate)); // ✅ fix here too!

                    int AffectedRow = preparedStatement.executeUpdate();
                    if(AffectedRow > 0 ){
                        System.out.println("Appointment Booked !");
                    } else {
                        System.out.println("Appointment Not Booked !");
                    }
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Doctor Not Available On this Date");
            }
        } else {
            System.out.println("Either Doctors or Patient doesn't exits");
        }
    }


    public static boolean CheckDoctorAvailability(int doctorId,String appointmentDate,Connection connection){
        String query = "select count (*) from appointments where doctor_id = ? and appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(appointmentDate)); // ✅ fix here too!


            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                return count == 0;  // true if doctor free
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void ViewAllAppointments(Connection connection){
        String query = "SELECT a.id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date " +
                "FROM appointments a " +
                "JOIN patient p ON a.patient_id = p.id " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "ORDER BY a.appointment_date";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("View All Appointments : ");
            System.out.println("+------+----------------------------+------------------------------+-----------------+");
            System.out.println("| Id   | Patient Name               | Doctor Name                  | Date            |");
            System.out.println("+------+----------------------------+------------------------------+-----------------+");

            boolean hasData = false;
            while (resultSet.next()){
                hasData = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                Date date = resultSet.getDate("appointment_date");
                System.out.printf("| %-5s| %-27s| %-29s| %-16s \n",id,patientName,doctorName,date);
                System.out.println("+------+----------------------------+------------------------------+-----------------+");
            }
            if(!hasData)
                System.out.println("No Appointments Found ❌");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void CancelAppointment(Connection connection, Scanner scanner){
        System.out.println("Enter Appointment ID :");
        int AppointmentDate = scanner.nextInt();

        String query = "delete from appointments where id = ? ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,AppointmentDate);
            int Row = preparedStatement.executeUpdate();

            if(Row > 0){
                System.out.println("✅ Appointment Cancelled Successfully!");
            } else{
                System.out.println("⚠️ Appointment ID Not Found!");

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
