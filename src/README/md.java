package README;

🏥 Hospital Management System

Java | JDBC | PostgreSQL

A simple yet powerful Hospital Management System built using Core Java, JDBC, and PostgreSQL.
This project allows hospitals to efficiently manage Doctors, Patients, and Appointments with full CRUD operations.


🚀 Features :-

👨‍⚕️ Doctor Management.
1 .Add new doctor
2 .View doctor list
3 .Update doctor details



🧑‍⚕️ Patient Management.
1 .Add new patient
2 .View patient list
3 .Update patient details



📅 Appointment Management.
1 .Book appointments
2 .View all appointments
3 .Automatic validation of IDs


***Tech Stack***

1 .Java (Core Java, OOP, Exception Handling)
2 .JDBC PostgreSQL
3 .DAO Pattern
4 .IntelliJ IDEA

-------------------------------------------------------------------------------------------------------------------------------------

###️ Database Schema (PostgreSQL)


    1 . PATIENT'S

Table "public.patient"
Column  |          Type          | Collation | Nullable |               Default
---------+------------------------+-----------+----------+-------------------------------------
id      | integer                |           | not null | nextval('patient_id_seq'::regclass)
name    | character varying(100) |           | not null |
age     | smallint               |           | not null |
gender  | character varying(20)  |           |          |
address | text                   |           |          |
Indexes:
        "patient_pkey" PRIMARY KEY, btree (id)
Referenced by:
TABLE "appointments" CONSTRAINT "appointments_patient_id_fkey" FOREIGN KEY (patient_id) REFERENCES patient(id)



    2 . DOCTOR'S

Table "public.doctor"
Column     |          Type          | Collation | Nullable |              Default
----------------+------------------------+-----------+----------+------------------------------------
id             | integer                |           | not null | nextval('doctor_id_seq'::regclass)
name           | character varying(100) |           | not null |
specialization | character varying(50)  |           |          |
Indexes:
        "doctor_pkey" PRIMARY KEY, btree (id)
Referenced by:
TABLE "appointments" CONSTRAINT "appointments_doctor_id_fkey" FOREIGN KEY (doctor_id) REFERENCES doctor(id)


    3 . APPOINTMENT'S


hospitaldb=# \d appointments;
Table "public.appointments"
Column      |  Type   | Collation | Nullable |                 Default
------------------+---------+-----------+----------+------------------------------------------
id               | integer |           | not null | nextval('appointments_id_seq'::regclass)
patient_id       | bigint  |           | not null |
doctor_id        | bigint  |           | not null |
appointment_date | date    |           | not null |
Indexes:
        "appointments_pkey" PRIMARY KEY, btree (id)
Foreign-key constraints:
        "appointments_doctor_id_fkey" FOREIGN KEY (doctor_id) REFERENCES doctor(id)
        "appointments_patient_id_fkey" FOREIGN KEY (patient_id) REFERENCES patient(id)

-------------------------------------------------------------------------------------------------------------------------------------

** How to Run the Project **

1 . Clone this repository to your local machine
    git clone https://github.com/your-username/HospitalManagementSystem.git

2 . Configure your MySQL database settings in the HotelReservationSystem.java file
    private static final String url = "jdbc:postgresql://localhost:5432/hospitaldb";
    private static final String username = "your_username";
    private static final String password = "your_password";

3 . Run the Application
    * Use IDE :-
    javac Main.java
    java Main

-------------------------------------------------------------------------------------------------------------------------------------


🧑‍💻Future Enhancements
        1 . Add login/authentication
        2 . Convert to web app using Spring Boot

-> Contributing 🤝
    Contributions are welcome! Feel free to open issues and pull requests for bug fixes, enhancements, or new features.

❤️ Support
    If this project helped you, ⭐ Star the repo!




❤️‍🩹🙏