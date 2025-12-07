import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static final String url = "jdbc:postgresql://localhost:5432/hospitaldb";
    private static final String username = "postgres";
    private static final String password = "yash3035";

    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);


            Patients patients = new Patients(connection,scanner);
            Doctors doctors = new Doctors(connection);

            while (true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patients");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. View All Appointments");
                System.out.println("6. Cancel Appointment");
                System.out.println("7. Exit");

                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1 :
                        // Add patients
                        patients.addPatients();
                        break;
                    case 2 :
                        // View Patients
                        patients.viewPatient();
                        break;
                    case 3 :
                        // View Doctors
                        doctors.viewDoctors();
                        break;
                    case 4 :
                        // book appointment
                        Bookings.BookAppointment(patients,doctors,connection,scanner);
                        break;
                    case 5 :
                        // View All Appointments
                        Bookings.ViewAllAppointments(connection);
                        break;
                    case 6 :
                        // cancel appointment
                        Bookings.CancelAppointment(connection,scanner);
                        break;
                    case 7 :
                        System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM 🙏");
                        return;
                    default:
                        System.out.println("Enter the valid choice");

                }
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}