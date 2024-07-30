import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
// import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
// import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

class AIRLINE_RESERVATION extends Exception {

    public AIRLINE_RESERVATION(String message) {
        super(message);
    }

    public AIRLINE_RESERVATION() {
    }

    private static final String dburl = "jdbc:mysql://localhost:3306/airline_db1";
    private static final String dbuser = "root";
    private static final String dbpass = "";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) throws Exception {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found: " + e.getMessage());
            return; // Terminate the code execution
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return; // Terminate the code execution
        }
        try {
            if (con != null) {
                // System.out.println("connection Successfull");
            }
        } catch (Exception e) {
            System.err.println("Error closing database connection: " + e.getMessage());

        }
        if (con != null) {

            System.out.println("                                        THE FASTEST WAY");
            System.out.println("                                        FLIGHT SEARCH");

            System.out.println();
            System.out.println();

            System.out.print("                      W      W   EEEEEE  L        CCCC    OOO     M     M   EEEEEE\n");
            System.out.print("                      W  W   W   E       L       C       O   O    M M M M   E    \n");
            System.out.print("                      W  W W W   EEEE    L      C       O     O   M  M  M   EEEE \n");
            System.out.print("                      W W  W W   E       L       C       O   O    M     M   E    \n");
            System.out.print("                      W      W   EEEEEE  LLLLLL   CCCCC   OOO     M     M   EEEEEE\n");

            Scanner sc = new Scanner(System.in);

            int choice = 0;
            do {
                try {
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            choice = sc.nextInt();
                            validInput = true; // Set to true if input is valid to exit the loop
                        } catch (InputMismatchException e) {
                            System.out.println("EXCEPTION: Enter a valid choice");
                            sc.nextLine(); // Clear the invalid input from the scanner
                            choice = 0;     // Reset choice to a default value
                        }
                    }
                    switch (choice) {
                        case 1:
                            registerUser(con, sc); // to register-user
                            break;
                        case 2:
                            loginUser(con, sc); // to login-user.
                            break;
                        case 3:
                            System.out.print(
                                    "                      TTTTTT   H    H      A       N   N   K  K      Y   Y   OOO    U   U\n");
                            System.out.print(
                                    "                         T     H    H     A A      NN  N   K K        Y Y   O   O   U   U\n");
                            System.out.print(
                                    "                         T     HHHHHH    AAAAAA    N N N   KK          Y    O   O   U   U\n");
                            System.out.print(
                                    "                         T     H    H   A      A   N  NN   K K         Y    O   O   U   U\n");
                            System.out.print(
                                    "                         T     H    H  A        A  N   N   K  K        Y     OOO     UUU \n");
                            System.out.println();
                            System.out.println();
                            System.out.println("                                           Thank's for visiting us  ");

                            System.exit(0);
                            // return;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Exit due to CTRL + C");
                    break;
                }
            } while (choice != 3);
        } else {
            System.out.println("Connection Failed");
        }
    }

    private static void registerUser(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter username: ");
        String username = sc.next();
        int user_name = 0;
        Boolean Valid_Name = false;
        while ((user_name == 0)) {
            char chr[] = username.toCharArray();
            for (int i = 0; i < chr.length; i++) {
                if ((((chr[i] >= 65) && (chr[i] <= 90)) || ((chr[i] >= 97) && (chr[i] <= 122)))) {
                    user_name = 1;
                    Valid_Name = true;
                } else {
                    System.out.println("In-Valid Name. Enter Valid Name!!");
                    username = sc.next();
                    chr = username.toCharArray();
                }
            }
        } // Validated User-Name.
        System.out.println();
        String pass = "";

        System.out.println();
        boolean flag = false;
        do {
            System.out.println(" INSTRUCTION  FOR  PASSWORD  ");
            System.out.println("1. At least one Upper case");
            System.out.println("2. At least one Lower case");
            System.out.println("3. At least one digit");
            System.out.println("4. At least 8 to 15 character");

            System.out.print("Enter Password : ");
            // sc.nextLine();
            pass = sc.next();
            int lower_case = 0;
            int uper_case = 0;
            int digit_case = 0;
            int cnt = 0;

            for (char j = 0; j < pass.length(); j++) {
                for (char i = 65; i <= 90; i++) {

                    if (pass.charAt(j) == i) {
                        uper_case = 1;
                        break;
                    }
                }
            }
            if (uper_case == 1) {
                cnt++;
            }

            for (char j = 0; j < pass.length(); j++) {
                for (char i = 97; i <= 122; i++) {

                    if (pass.charAt(j) == i) {
                        lower_case = 1;
                        break;
                    }
                }
            }

            if (lower_case == 1) {
                cnt++;
            }

            for (char j = 0; j < pass.length(); j++) {
                for (char i = 48; i <= 57; i++) {

                    if (pass.charAt(j) == i) {
                        digit_case = 1;
                        break;
                    }
                }
            }

            if (digit_case == 1) {
                cnt++;
            }

            if (pass.length() >= 8 && pass.length() <= 15) {
                cnt++;
            }
            if (cnt == 4) {
                flag = true;
            } else {
                System.out.println("Retry");
            }
        } while (flag != true); // Validated Password

        // int trial=0;
        flag = false;
        do {
            System.out.print("Confirm Password : ");
            String con_pass = sc.next();

            if (pass.equals(con_pass)) {
                System.out.println();
                flag = true;
                System.out.print("You have successfuly logged in ");
                System.out.println();
            } else {
                System.out.println("Invalid CONFIRM Password");
                System.out.println("Retry");
            }
        } while (flag != true);

        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)"; // insert details in users
        PreparedStatement statement = con.prepareStatement(insertQuery);
        statement.setString(1, username);
        statement.setString(2, pass);
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            // System.out.println("Registration successful.");
            String insertQuery1 = "INSERT INTO user_profiles (username, First_Name, Last_Name,E_mail,Phone_Number,Age) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement1 = con.prepareStatement(insertQuery1);
            System.out.println("------------------------------------------");

            System.out.println("Enter First_Name : ");
            String firstName = sc.next();
            // ;
            int flagFirst_Name = 0;
            Boolean Valid_Name1 = false;
            while ((flagFirst_Name == 0)) {
                char chr[] = firstName.toCharArray();
                for (int i = 0; i < chr.length; i++) {
                    if ((((chr[i] >= 65) && (chr[i] <= 90)) || ((chr[i] >= 97) && (chr[i] <= 122)))) {
                        flagFirst_Name = 1;
                        Valid_Name1 = true;
                    } else {
                        System.out.println("In-Valid Name. Enter Valid Name!!");
                        firstName = sc.next();
                        chr = firstName.toCharArray();
                    }
                }
            } // Validated First-Name.

            System.out.println("Enter Last Name : ");
            String LastName = sc.next();
            int flagLast_Name = 0;
            Boolean Valid_Last_Name = false;
            while ((flagLast_Name == 0)) {
                char chr[] = LastName.toCharArray();
                for (int i = 0; i < chr.length; i++) {
                    if ((((chr[i] >= 65) && (chr[i] <= 90)) || ((chr[i] >= 97) && (chr[i] <= 122)))) {
                        flagLast_Name = 1;
                        Valid_Last_Name = true;
                    } else {
                        System.out.println("In-Valid Last Name. Enter Valid Name!!");
                        LastName = sc.next();
                        chr = LastName.toCharArray();
                    }
                }
            } // Validated Last-Name.

            System.out.println("Enter E-mail : ");
            sc.nextLine();
            String eMail = sc.nextLine();
            Boolean email_validate = false;
            while (email_validate != true) {
                if (eMail == null || eMail.isEmpty()) {
                    System.out.println("Invalid email address: " + eMail);
                    System.out.println("Enter Again.");
                    eMail = sc.nextLine();
                } else {
                    int atIndex = eMail.indexOf('@');
                    int dotIndex = eMail.lastIndexOf('.');
                    // Check if "@" and "." are present and in the correct order
                    if (atIndex > 0 && dotIndex > atIndex && dotIndex < eMail.length() - 1) {
                        // System.out.println("Valid email address: " + eMail);
                        email_validate = true;
                    } else {
                        System.out.println("Invalid email address: " + eMail);
                        System.out.println("Enter Again.");
                        eMail = sc.nextLine();
                    }
                }
            } // Validated E-Mail.

            System.out.println("Enter Phone Number : ");
            String phNo;
            // String phone = sc.next();
            int ph_flag1 = 0;
            while (true) {
                phNo = sc.nextLine();
                for (int i = 0; i < phNo.length(); i++) {
                    if (phNo.charAt(i) >= '0' && phNo.charAt(i) <= '9') {
                        if (phNo.length() == 10) {
                            if (phNo.charAt(0) == '9' || phNo.charAt(0) == '8'
                                    || phNo.charAt(0) == '7'
                                    || phNo.charAt(0) == '6') {
                                ph_flag1 = 1;
                                break;
                            } else {
                                ph_flag1 = 0;
                            }
                        } else {
                            ph_flag1 = 0;
                        }
                    }
                }
                if (ph_flag1 == 1) {
                    break;
                } else if (ph_flag1 == 0) {
                    System.out.print("Enter valid number ");
                    System.out.println(
                            "Instructions : The number should start from 9,8,7 or 6\nThe number must have atleast 10 digits");
                }
            }
            // Validated Phone Number.

            System.out.println("Enter Age : ");
            int age = 0;
            Boolean flag_Age = true;

            boolean validInput = false;
            while (!validInput) {
                try {
                    age = sc.nextInt();
                    while (flag_Age) {
                        if (age <= 0 || age >= 125) {
                            System.out.println("Enter Valid Age");
                            age = sc.nextInt();
                        } else {
                            flag_Age = false;
                            break;
                        }
                    }
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION : Enter Valid age");
                    sc.next();
                }
            }
            // Validated Age.

            System.out.println("------------------------------------------");
            statement1.setString(1, username);
            statement1.setString(2, firstName);
            statement1.setString(3, LastName);
            statement1.setString(4, eMail);
            statement1.setString(5, phNo);
            statement1.setInt(6, age);

            int rowsInserted1 = statement1.executeUpdate();

            if (rowsInserted1 > 0) {
                System.out.println("User profile data added.");
            } else {
                System.out.println("Failed to add user profile data.");
            }
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static void loginUser(Connection con, Scanner sc) throws SQLException, IOException {
        boolean flag_correct_pass = false;
        String username = "";
        String password = "";
        int attemptsLeft = 3;
        // String reset = "";
        System.out.print("Enter username: ");
        username = sc.next();
        if (isUsernameExists(con, username)) {

            while (flag_correct_pass != true && attemptsLeft > 0) {
                System.out.print("Enter password: ");
                password = sc.next();

                String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement statement = con.prepareStatement(selectQuery);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login successful.");
                    flag_correct_pass = true;
                    while (true) {
                        // System.out.println("Logged in successfully.");
                        System.out.println("1. View My Profile");
                        System.out.println("2. Update My Profile");
                        System.out.println("3. Search Flights");
                        System.out.println("4. Cancel Booking");
                        System.out.println("5. Log Out");
                        System.out.print("Enter your choice: ");
                        int choice = 0;
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                choice = sc.nextInt();
                                validInput = true;
                            } catch (Exception e) {
                                System.out.println("EXCEPTION : Enter Valid choice");
                                sc.next();

                            }
                        }
                        switch (choice) {
                            case 1:
                                fetchAndDisplayUserProfile(con, username);
                                break;
                            case 2:
                                updateUserProfile(con, sc, username);
                                break;
                            case 3:
                                searchFlights(con, sc);
                                break;
                            case 4:
                                bookingCancellation(con, sc);
                                break;
                            case 5:
                                System.out.println("Logged out successfully.");
                                return;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                } else {
                    attemptsLeft--;
                    System.out.println("Login failed. Invalid credentials.");
                    System.out.println("Enter Again.");
                }
                if (attemptsLeft == 0) {
                    System.out.println("You Have Reached Maximum Attempts.\n Please try again Later.\n");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("UserName not found!!");
        }

    }

    private static boolean isUsernameExists(Connection connection, String username) throws SQLException {
        String selectQuery = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private static void fetchAndDisplayUserProfile(Connection connection, String username) throws SQLException {
        String selectQuery = "SELECT * FROM user_profiles WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // String firstName = resultSet.getString("first_name");
            System.out.println("Profile Information:");
            System.out.println("First Name: " + resultSet.getString("First_Name"));
            System.out.println("Last Name: " + resultSet.getString("Last_Name"));
            System.out.println("E-mail: " + resultSet.getString("E_mail"));
            System.out.println("Phone Number: " + resultSet.getString("Phone_Number"));
            System.out.println("Age: " + resultSet.getString("Age"));

            // ... Display other profile data
        } else {
            System.out.println("Profile data not found.");
        }
    }

    private static void bookingCancellation(Connection connection, Scanner sc) throws SQLException {
        System.out.println(
                "25% of your Ticket Price would be deducted as penalty. Would you like to continue? (yes or no)");
        sc.nextLine();
        String confString = sc.nextLine();
        if (confString.equalsIgnoreCase("Yes")) {

            System.out.println("Enter Flight Number : ");
            // sc.nextLine();
            String flightNumber = sc.nextLine();
            System.out.println("Enter passanger id :");
            int pass_id = sc.nextInt();
            CallableStatement getBookid = connection.prepareCall("{call GetBookingId(?,?,?)}");
            getBookid.setString(1, flightNumber);
            getBookid.setInt(2, pass_id);
            getBookid.executeQuery();
            int booking_id = getBookid.getInt(3);
            if (booking_id > 0) {
                String deleteQuery = "Delete from booking where booking_id = ?"; // Delete from Booking.
                String deleteTicket = "Delete from Ticket where booking_id = ?"; // Delete from ticket
                PreparedStatement preparedeleteStatement = connection.prepareStatement(deleteQuery);
                PreparedStatement preparedeleteTicket = connection.prepareStatement(deleteTicket);
                preparedeleteStatement.setInt(1, booking_id);
                preparedeleteTicket.setInt(1, booking_id);
                int checkerBooking = preparedeleteStatement.executeUpdate();
                int checkerTicket = preparedeleteTicket.executeUpdate();
                if (checkerBooking > 0 && checkerTicket > 0) {
                    int seatBeforeDelete = getAvailableSeats(connection, flightNumber);
                    String updateSeat = "Update flights set available_seats = ? where flight_number=?"; // Update Seats
                    PreparedStatement setSeats = connection.prepareStatement(updateSeat);
                    setSeats.setInt(1, seatBeforeDelete + 1);
                    setSeats.setString(2, flightNumber);
                    setSeats.executeUpdate();
                    String getAccNo = "Select Account_number,payment_amount from Payment1 where booking_id = ?"; // fetch
                                                                                                                 // account_number
                    PreparedStatement getAccountNo = connection.prepareStatement(getAccNo);
                    getAccountNo.setInt(1, booking_id);
                    ResultSet acc_no = getAccountNo.executeQuery();
                    if (acc_no.next()) {
                        String accountNo = acc_no.getString(1);
                        double payment = acc_no.getDouble(2);
                        double amountDeducted = (25.0 / 100.0) * payment; // 25% amount deducted
                        double refund = payment - amountDeducted;
                        System.out.println("Successfully Cancelled Booking");
                        System.out.println("25% of " + payment + " i.e Rs. " + amountDeducted + " has been deducted.");
                        System.out.println("Transfer of Rs. " + refund
                                + " has been done successfully on account number " + accountNo + ".");
                    } else {
                        System.out.println("Payment Failed!!");
                    }
                } else {
                    System.out.println("Booking Cancellation Failed.");
                }
            } else {
                System.out.println("No Passenger Found");
            }
        } else {
            System.out.println("Have A Safe Journey!!");
        }

    }

    private static void updateUserProfile(Connection connection, Scanner sc, String username) throws SQLException {
        String checkUserQuery = "SELECT * FROM user_profiles WHERE username = ?"; // fetc all details from
                                                                                  // user_profiles.
        PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery);
        checkUserStatement.setString(1, username);
        if (checkUserStatement.executeQuery().next()) {

            System.out.println("Enter new profile information (Enter 0 to skip updating):");
            System.out.print("First Name: ");
            sc.nextLine();

            String newFirstName = sc.nextLine();
            int flagFirst_Name = 0;
            boolean validName = false;

            while (flagFirst_Name == 0) {
                char[] chr = newFirstName.toCharArray();

                if (chr.length == 1 && chr[0] == '0') {
                    validName = true; // Skip updating
                    break;
                }

                for (int i = 0; i < chr.length; i++) {
                    if (((chr[i] >= 65) && (chr[i] <= 90)) || ((chr[i] >= 97) && (chr[i] <= 122))) {
                        flagFirst_Name = 1;
                        validName = true;
                    } else {
                        System.out.println("Invalid Name. Enter a Valid Name!!");
                        newFirstName = sc.nextLine();
                        chr = newFirstName.toCharArray();
                    }
                }
            } // Validated First-Name.

            System.out.print("Last Name: ");
            String newLastName = sc.nextLine();

            int flagLast_Name = 0;
            Boolean Valid_Last_Name = false;
            while ((flagLast_Name == 0)) {
                char chr[] = newLastName.toCharArray();
                if (chr.length == 1 && chr[0] == '0') {
                    Valid_Last_Name = true; // Skip updating
                    break;
                }
                for (int i = 0; i < chr.length; i++) {
                    if ((((chr[i] >= 65) && (chr[i] <= 90)) || ((chr[i] >= 97) && (chr[i] <= 122)))) {
                        flagLast_Name = 1;
                        Valid_Last_Name = true;
                    } else {
                        System.out.println("In-Valid Last Name. Enter Valid Name!!");
                        newLastName = sc.next();
                        chr = newLastName.toCharArray();
                    }
                }
            } // Validated Last-Name.
            System.out.print("Email: ");
            String newEmail = sc.nextLine();
            int email_flag = 0;
            boolean email_validate = false;

            while (email_flag == 0) {
                if (newEmail.length() == 1 && newEmail.equals("0")) {
                    email_validate = true;
                    break;
                }
                if (newEmail == null || newEmail.isEmpty()) {
                    System.out.println("Invalid email address: " + newEmail);
                    System.out.println("Enter Again.");
                    newEmail = sc.nextLine();
                } else {
                    int atIndex = newEmail.indexOf('@');
                    int dotIndex = newEmail.lastIndexOf('.');
                    // Check if "@" and "." are present and in the correct order
                    if (atIndex > 0 && dotIndex > atIndex && dotIndex < newEmail.length() - 1) {
                        email_flag = 1;
                        email_validate = true;
                    } else {
                        System.out.println("Invalid email address: " + newEmail);
                        System.out.println("Enter Again.");
                        newEmail = sc.nextLine();
                    }
                }
            } // Validated E-Mail.

            System.out.println("Enter Phone Number (Enter 0 to skip updating): ");
            String newPhoneNumber = sc.nextLine();
            int ph_flag1 = 0;

            while (true) {
                if (newPhoneNumber.equals("0")) {
                    break; // Skip updating
                }

                for (int i = 0; i < newPhoneNumber.length(); i++) {
                    if (newPhoneNumber.charAt(i) >= '0' && newPhoneNumber.charAt(i) <= '9') {
                        if (newPhoneNumber.length() == 10) {
                            char firstDigit = newPhoneNumber.charAt(0);
                            if (firstDigit == '9' || firstDigit == '8' || firstDigit == '7' || firstDigit == '6') {
                                ph_flag1 = 1;
                                break;
                            } else {
                                ph_flag1 = 0;
                            }
                        } else {
                            ph_flag1 = 0;
                        }
                    }
                }

                if (ph_flag1 == 1) {
                    break; // Valid phone number
                } else if (ph_flag1 == 0) {
                    System.out.println("Invalid phone number.");
                    System.out.println("Instructions: The number should start from 9, 8, 7, or 6");
                    System.out.println("The number must have at least 10 digits.");
                    System.out.print("Enter a valid number or 0 to skip updating: ");
                    newPhoneNumber = sc.nextLine();
                }
            }
            // Validated Phone Number.

            System.out.println("Enter Age (Enter 0 to skip updating): ");
            int newAge = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    String ageInput = sc.nextLine();

                    if (ageInput.equals("0")) {
                        break; // Skip updating
                    }

                    newAge = Integer.parseInt(ageInput);

                    if (newAge > 0 && newAge < 125) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid Age. Enter a valid age or 0 to skip.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a valid age or 0 to skip.");
                }
            } // Validate age

            // Check if the user wants to update each field (0 indicates no update)
            if (!newFirstName.equals("0")) {
                String updateFirstNameQuery = "UPDATE user_profiles SET first_name = ? WHERE username = ?";
                PreparedStatement updateFirstNameStatement = connection.prepareStatement(updateFirstNameQuery);
                updateFirstNameStatement.setString(1, newFirstName);
                updateFirstNameStatement.setString(2, username);
                updateFirstNameStatement.executeUpdate();
            }

            if (!newLastName.equals("0")) {
                String updateLastNameQuery = "UPDATE user_profiles SET last_name = ? WHERE username = ?";
                PreparedStatement updateLastNameStatement = connection.prepareStatement(updateLastNameQuery);
                updateLastNameStatement.setString(1, newLastName);
                updateLastNameStatement.setString(2, username);
                updateLastNameStatement.executeUpdate();
            }

            if (!newEmail.equals("0")) {
                String updateEmailQuery = "UPDATE user_profiles SET e_mail = ? WHERE username = ?";
                PreparedStatement updateEmailStatement = connection.prepareStatement(updateEmailQuery);
                updateEmailStatement.setString(1, newEmail);
                updateEmailStatement.setString(2, username);
                updateEmailStatement.executeUpdate();
            }
            if (!newPhoneNumber.equals("0")) {
                String updatePhoneNumQuery = "UPDATE user_profiles SET Phone_Number = ? WHERE username = ?";
                PreparedStatement updatePhoneNumStatement = connection.prepareStatement(updatePhoneNumQuery);
                updatePhoneNumStatement.setString(1, newPhoneNumber);
                updatePhoneNumStatement.setString(2, username);
                updatePhoneNumStatement.executeUpdate();
            }

            if (newAge != 0) {
                String updateAgeQuery = "UPDATE user_profiles SET age = ? WHERE username = ?";
                PreparedStatement updateAgeStatement = connection.prepareStatement(updateAgeQuery);
                updateAgeStatement.setInt(1, newAge);
                updateAgeStatement.setString(2, username);
                updateAgeStatement.executeUpdate();
            }

            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void searchFlights(Connection con, Scanner sc) throws SQLException, IOException {
        do {
            System.out.println("1. Display All Flights");
            System.out.println("2. Filter by Departure Place");
            System.out.println("3. Filter by Departure Place and Arrival Place");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = sc.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION : Enter Valid choice");
                    sc.next();
                }
            }
            switch (choice) {
                case 1:
                    displayAllFlights(con, sc); // Display All Flights
                    break;
                case 2:
                    sortFlightsByDeparturePlace(con, sc); // Sort by Departure Place
                    break;
                case 3:
                    sortFlightsByDepartureAndArrival(con, sc); // Sort by Departure and Arrival.
                    break;
                case 4:
                    return; // Return to main menu
            }
        } while (true);
    }

    private static void displayAllFlights(Connection con, Scanner sc) throws SQLException, IOException {
        String query = "SELECT * FROM flights"; // Fetch all details from flights
        try (Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            List<Flight> flights = new ArrayList<>(); // ArrayList which store
            while (resultSet.next()) {
                String departure_place = resultSet.getString("departure_city");
                String arrival_place = resultSet.getString("arrival_city");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("AirlineName");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");
                double ticketPrice = resultSet.getDouble("ticket_price");
                int availableSeats = resultSet.getInt("available_seats");

                AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
                Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                        ticketPrice, availableSeats, airlineName);

                flights.add(flight);
            }
            displayFlightResults(flights); // display all flights
            System.out.println("1. Book Flight.");
            System.out.println("2. Filter by Airline Name.");
            System.out.println("3. Filter by Date(Flights on or after that date).");
            System.out.println("4. Back to Previous Menu.");
            System.out.println("Enter Valid Choice: ");
            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = sc.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION: Enter Valid choice");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    bookFlight(con, sc); // Book a Flight
                    break;
                case 2:
                    filterByAirline(con, sc); // Filter by Airline Method.
                    break;
                case 3:
                    filterByDate(con, sc); // Filter By date method.
                    break;
                case 4:
                    return;

                default:
                    System.out.println("Enter Valid Choice.");
                    break;
            }

        }
    }

    private static void filterByAirline(Connection con, Scanner sc) // Filter by airline
            throws SQLException, IOException {
        System.out.print("Enter preferred airline name: ");
        sc.nextLine();
        String preferredAirline = sc.nextLine();

        String query = "SELECT * FROM flights WHERE AirlineName = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, preferredAirline);
        ResultSet resultSet = statement.executeQuery();

        List<Flight> filteredFlightsByAirline = new ArrayList<>();
        while (resultSet.next()) {
            String departure_place = resultSet.getString("departure_city");
            String arrival_place = resultSet.getString("arrival_city");
            String flightNumber = resultSet.getString("flight_number");
            String airlineName = resultSet.getString("AirlineName");
            String departureTime = resultSet.getString("departure_time");
            String arrivalTime = resultSet.getString("arrival_time");
            double ticketPrice = resultSet.getDouble("ticket_price");
            int availableSeats = resultSet.getInt("available_seats");

            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                    ticketPrice, availableSeats, airlineName); // object of inner class.

            filteredFlightsByAirline.add(flight);
        }

        if (filteredFlightsByAirline.isEmpty()) {
            System.out.println("No flights available for airline " + preferredAirline + ".");
        } else {
            displayFlightResults(filteredFlightsByAirline);
            bookFlight(con, sc);
        }
    }

    private static void filterByDate(Connection con, Scanner sc) // Filter by date.
            throws SQLException, IOException {
        System.out.print("Enter preferred departure date (yyyy-MM-dd): ");
        String preferredDepartureDate = sc.next();
        if (isValidDate(preferredDepartureDate)) {
            String query = "SELECT * FROM flights WHERE DATE(departure_time) >= ?"; // get all flights details for a
                                                                                    // specific date range
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, preferredDepartureDate);
            ResultSet resultSet = statement.executeQuery();

            List<Flight> filteredFlightsByDate = new ArrayList<>();
            while (resultSet.next()) {
                String departure_place = resultSet.getString("departure_city");
                String arrival_place = resultSet.getString("arrival_city");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("AirlineName");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");
                double ticketPrice = resultSet.getDouble("ticket_price");
                int availableSeats = resultSet.getInt("available_seats");

                AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
                Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                        ticketPrice, availableSeats, airlineName);

                filteredFlightsByDate.add(flight); // Adding it into a ArrayList.
            }

            if (filteredFlightsByDate.isEmpty()) {
                System.out.println(
                        "No flights available on or after " + preferredDepartureDate + ".");
            } else {
                displayFlightResults(filteredFlightsByDate);
                bookFlight(con, sc);
            }
        } else {
            System.out.println("Invalid Date");
        }

    }

    private static void sortFlightsByDeparturePlace(Connection con, Scanner sc) throws SQLException, IOException {
        sc.nextLine();
        System.out.print("Enter departure place: ");
        String departurePlace = sc.nextLine();

        String query = "SELECT * FROM flights WHERE departure_city = ?"; // fetch all flights by departure_city
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, departurePlace);
        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            String departure_place = resultSet.getString("departure_city");
            String arrival_place = resultSet.getString("arrival_city");
            String flightNumber = resultSet.getString("flight_number");
            String airlineName = resultSet.getString("AirlineName");
            String departureTime = resultSet.getString("departure_time");
            String arrivalTime = resultSet.getString("arrival_time");
            double ticketPrice = resultSet.getDouble("ticket_price");
            int availableSeats = resultSet.getInt("available_seats");

            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                    ticketPrice, availableSeats, airlineName);

            flights.add(flight);
        }

        if (flights.isEmpty()) {
            System.out.println("No flights available from " + departurePlace);
        } else {
            displayFlightResults(flights);
            System.out.println("1. Book Flight.");
            System.out.println("2. Filter by Airline Name.");
            System.out.println("3. Filter by Date(Flights on or after that date).");
            System.out.println("4. Back to Previous Menu.");
            System.out.println("Enter Valid Choice: ");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = sc.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION: Enter Valid choice");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    bookFlight(con, sc); // book a flight
                    break;
                case 2:
                    filterFlightsByAirline(con, sc, departurePlace); // filter by airline name
                    break;
                case 3:
                    filterFlightsByDate(con, sc, departurePlace); // filter by date.
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter Valid Choice.");
                    break;
            }
        }
    }

    private static void filterFlightsByDate(Connection con, Scanner sc, String departurePlace) // filter by date.
            throws SQLException, IOException {
        System.out.print("Enter preferred departure date (yyyy-MM-dd): ");
        String preferredDepartureDate = sc.next();
        if (isValidDate(preferredDepartureDate)) {
            String query = "SELECT * FROM flights WHERE departure_city = ? AND DATE(departure_time) >= ?"; // get all
                                                                                                           // flights
                                                                                                           // details on
                                                                                                           // basis of
                                                                                                           // departure_city
                                                                                                           // and date
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, departurePlace);
            statement.setString(2, preferredDepartureDate);
            ResultSet resultSet = statement.executeQuery();

            List<Flight> filteredFlightsByDate = new ArrayList<>();
            while (resultSet.next()) {
                String departure_place = resultSet.getString("departure_city");
                String arrival_place = resultSet.getString("arrival_city");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("AirlineName");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");
                double ticketPrice = resultSet.getDouble("ticket_price");
                int availableSeats = resultSet.getInt("available_seats");

                AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
                Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                        ticketPrice, availableSeats, airlineName);

                filteredFlightsByDate.add(flight);
            }

            if (filteredFlightsByDate.isEmpty()) {
                System.out.println(
                        "No flights available on or after " + preferredDepartureDate + " from " + departurePlace);
            } else {
                displayFlightResults(filteredFlightsByDate);
                bookFlight(con, sc);
            }
        } else {
            System.out.println("Invalid Date");
        }

    }

    private static boolean isValidDate(String dateStr) {
        if (dateStr.length() != 10) {
            return false; // Date must have exactly 10 characters (yyyy-MM-dd)
        }

        String[] parts = dateStr.split("-");
        if (parts.length != 3) {
            return false; // Date must have three parts (yyyy, MM, dd)
        }

        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Check year, month, and day ranges
            if (year < 1000 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) {
                return false;
            }

            // Additional logic to check for valid days in each month (e.g., no more than
            // 30/31 days)
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                return false;
            }
            if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    if (day > 29) {
                        return false;
                    }
                } else if (day > 28) {
                    return false;
                }
            }

            // If all checks pass, the date is valid
            return true;
        } catch (NumberFormatException e) {
            return false; // Parts are not valid integers
        }
    }

    private static void filterFlightsByAirline(Connection con, Scanner sc, String departurePlace)
            throws SQLException, IOException {
        System.out.print("Enter preferred airline name: ");
        sc.nextLine();
        String preferredAirline = sc.nextLine();

        String query = "SELECT * FROM flights WHERE departure_city = ? AND AirlineName = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, departurePlace);
        statement.setString(2, preferredAirline);
        ResultSet resultSet = statement.executeQuery();

        List<Flight> filteredFlightsByAirline = new ArrayList<>();
        while (resultSet.next()) {
            String departure_place = resultSet.getString("departure_city");
            String arrival_place = resultSet.getString("arrival_city");
            String flightNumber = resultSet.getString("flight_number");
            String airlineName = resultSet.getString("AirlineName");
            String departureTime = resultSet.getString("departure_time");
            String arrivalTime = resultSet.getString("arrival_time");
            double ticketPrice = resultSet.getDouble("ticket_price");
            int availableSeats = resultSet.getInt("available_seats");

            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                    ticketPrice, availableSeats, airlineName);

            filteredFlightsByAirline.add(flight);
        }

        if (filteredFlightsByAirline.isEmpty()) {
            System.out.println("No flights available for airline " + preferredAirline + " from " + departurePlace);
        } else {
            displayFlightResults(filteredFlightsByAirline);
            bookFlight(con, sc);
        }
    }

    static List<Flight> flights = null;

    private static void sortFlightsByDepartureAndArrival(Connection con, Scanner sc) throws SQLException, IOException {
        sc.nextLine();
        System.out.print("Enter departure place: ");
        String departurePlace = sc.nextLine();
        System.out.print("Enter arrival place: ");
        String arrivalPlace = sc.nextLine();

        String query = "SELECT * FROM flights WHERE departure_city = ? AND arrival_city = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, departurePlace);
        statement.setString(2, arrivalPlace);
        ResultSet resultSet = statement.executeQuery();

        flights = new ArrayList<>();
        while (resultSet.next()) {
            String departure_place = resultSet.getString("departure_city");
            String arrival_place = resultSet.getString("arrival_city");
            String flightNumber = resultSet.getString("flight_number");
            String airlineName = resultSet.getString("AirlineName");
            String departureTime = resultSet.getString("departure_time");
            String arrivalTime = resultSet.getString("arrival_time");
            double ticketPrice = resultSet.getDouble("ticket_price");
            int availableSeats = resultSet.getInt("available_seats");
            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                    ticketPrice, availableSeats, airlineName);
            flights.add(flight);

        }

        if (flights.isEmpty()) {
            System.out.println("No flights available between " + departurePlace + " and " + arrivalPlace);
        } else {
            displayFlightResults(flights);
            System.out.println("1. Book Flight.");
            System.out.println("2. Filter by Airline Name.");
            System.out.println("3. Filter by Date(Flights on or after that date).");
            System.out.println("4. Back to Previous Menu.");
            System.out.println("Enter Valid Choice: ");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = sc.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION: Enter Valid choice");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    bookFlight(con, sc);
                    break;
                case 2:
                    filterFlightsByAirlineDepartureAndArrival(con, sc, departurePlace, arrivalPlace);
                    break;
                case 3:
                    filterFlightsByDateDepartureArrival(con, sc, departurePlace, arrivalPlace);
                case 4:
                    return;

                default:
                    System.out.println("Enter Valid Choice.");
                    break;
            }
        }
    }

    private static void filterFlightsByDateDepartureArrival(Connection con, Scanner sc, String departurePlace,
            String arrivalPlace)
            throws SQLException, IOException {
        System.out.print("Enter preferred departure date (yyyy-MM-dd): ");
        String preferredDepartureDate = sc.next();
        if (isValidDate(preferredDepartureDate)) {
            String query = "SELECT * FROM flights WHERE departure_city = ? AND arrival_city=? AND DATE(departure_time) >= ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, departurePlace);
            statement.setString(2, arrivalPlace);
            statement.setString(3, preferredDepartureDate);
            ResultSet resultSet = statement.executeQuery();

            List<Flight> filteredFlightsByDate = new ArrayList<>();
            while (resultSet.next()) {
                String departure_place = resultSet.getString("departure_city");
                String arrival_place = resultSet.getString("arrival_city");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("AirlineName");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");
                double ticketPrice = resultSet.getDouble("ticket_price");
                int availableSeats = resultSet.getInt("available_seats");

                AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
                Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                        ticketPrice, availableSeats, airlineName);

                filteredFlightsByDate.add(flight);
            }

            if (filteredFlightsByDate.isEmpty()) {
                System.out.println(
                        "No flights available on or after " + preferredDepartureDate + " from " + departurePlace
                                + " to " + arrivalPlace);
            } else {
                displayFlightResults(filteredFlightsByDate);
                bookFlight(con, sc);
            }
        } else {
            System.out.println("Invalid Date");
        }

    }

    private static void filterFlightsByAirlineDepartureAndArrival(Connection con, Scanner sc, String departurePlace,
            String arrivalPlace)
            throws SQLException, IOException {
        System.out.print("Enter preferred airline name: ");
        sc.nextLine();
        String preferredAirline = sc.nextLine();

        String query = "SELECT * FROM flights WHERE departure_city = ? AND arrival_city = ? AND AirlineName = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, departurePlace);
        statement.setString(2, arrivalPlace);
        statement.setString(3, preferredAirline);

        ResultSet resultSet = statement.executeQuery();

        List<Flight> filteredFlightsByAirline = new ArrayList<>();
        while (resultSet.next()) {
            String departure_place = resultSet.getString("departure_city");
            String arrival_place = resultSet.getString("arrival_city");
            String flightNumber = resultSet.getString("flight_number");
            String airlineName = resultSet.getString("AirlineName");
            String departureTime = resultSet.getString("departure_time");
            String arrivalTime = resultSet.getString("arrival_time");
            double ticketPrice = resultSet.getDouble("ticket_price");
            int availableSeats = resultSet.getInt("available_seats");

            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            Flight flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime, arrivalTime,
                    ticketPrice, availableSeats, airlineName);

            filteredFlightsByAirline.add(flight);
        }

        if (filteredFlightsByAirline.isEmpty()) {
            System.out.println("No flights available for airline " + preferredAirline + " from " + departurePlace);
        } else {
            displayFlightResults(filteredFlightsByAirline);
            bookFlight(con, sc);
        }
    }

    private static void displayFlightResults(List<Flight> flights) { // Display Flight
        int count = 1;
        System.out.println("-----------------------------------------------------------------");
        for (Flight flight : flights) {
            System.out.println(count + ". Departure City: " + flight.getDeparture_place());
            System.out.println("   Arrival City: " + flight.getArrival_place());
            System.out.println("   Flight Number: " + flight.getFlightNumber());
            System.out.println("   AirLine Name : " + flight.getAirlineName());
            System.out.println("   Departure Time: " + flight.getDepartureTime());
            System.out.println("   Arrival Time: " + flight.getArrivalTime());
            System.out.println("   Ticket Price: " + flight.getTicketPrice());
            System.out.println("   Available Seats: " + flight.getAvailableSeats());
            System.out.println("-----------------------------------------------------------------");
            count++;
        }
    }

    private static synchronized void bookFlight(Connection con, Scanner scanner) throws SQLException, IOException {
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.next();

        // Check if the flight number is valid
        if (!isFlightValid(con, flightNumber)) {
            System.out.println("Invalid flight number.");
            return;
        }

        System.out.print("Enter the total number of passengers: ");

        int totalPassengers = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                totalPassengers = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("EXCEPTION : Enter Valid input");
                scanner.next();
            }
        }
        double flight_price = 0;

        int availableSeats = getAvailableSeats(con, flightNumber);
        if (totalPassengers > availableSeats) {
            System.out.println("Not enough seats available for all passengers.");
            return;
        }
        String firstName;
        String lastName;
        String email;
        String Phone_Number;
        int age;
        // int bookingId = 0;
        passangers pass_details_ob = null;
        passangers pass_details[] = new passangers[totalPassengers];
        for (int i = 0; i <= totalPassengers - 1; i++) {
            System.out.println("Enter details for passenger " + (i + 1));
            System.out.println("Enter First_Name : ");
            firstName = scanner.next();
            int flagFirst_Name = 0;
            Boolean Valid_Name = false;
            while ((flagFirst_Name == 0)) {
                char chr[] = firstName.toCharArray();
                for (int j = 0; j < chr.length; j++) {
                    if ((((chr[j] >= 65) && (chr[j] <= 90)) || ((chr[j] >= 97) && (chr[j] <= 122)))) {
                        flagFirst_Name = 1;
                        Valid_Name = true;
                    } else {
                        System.out.println("In-Valid Name. Enter Valid Name!!");
                        firstName = scanner.next();
                        chr = firstName.toCharArray();
                    }
                }
            } // Validated First-Name.

            System.out.println("Enter Last Name : ");
            lastName = scanner.next();
            int flagLast_Name = 0;
            Boolean Valid_Last_Name = false;
            while ((flagLast_Name == 0)) {
                char chr[] = lastName.toCharArray();
                for (int j = 0; j < chr.length; j++) {
                    if ((((chr[j] >= 65) && (chr[j] <= 90)) || ((chr[j] >= 97) && (chr[j] <= 122)))) {
                        flagLast_Name = 1;
                        Valid_Last_Name = true;
                    } else {
                        System.out.println("In-Valid Last Name. Enter Valid Name!!");
                        lastName = scanner.next();
                        chr = lastName.toCharArray();
                    }
                }
            } // Validated Last-Name.

            System.out.println("Enter E-mail : ");
            scanner.nextLine();
            email = scanner.nextLine();
            Boolean email_validate = false;
            while (email_validate != true) {
                if (email.isEmpty()) {
                    System.out.println("Invalid email address: " + email);
                    System.out.println("Enter Again.");
                    email = scanner.nextLine();
                } else {
                    int atIndex = email.indexOf('@');
                    int dotIndex = email.lastIndexOf('.');
                    // Check if "@" and "." are present and in the correct order
                    if (atIndex > 0 && dotIndex > atIndex && dotIndex < email.length() - 1) {
                        // System.out.println("Valid email address: " + eMail);
                        email_validate = true;
                    } else {
                        System.out.println("Invalid email address: " + email);
                        System.out.println("Enter Again.");
                        email = scanner.nextLine();
                    }
                }
            } // Validated E-Mail.

            System.out.println("Enter Phone Number : ");
            // scanner.nextLine();
            // String phone = sc.next();

            int ph_flag1 = 0;
            while (true) {
                Phone_Number = scanner.nextLine();
                for (int j = 0; j < Phone_Number.length(); j++) {
                    if (Phone_Number.charAt(j) >= '0' && Phone_Number.charAt(j) <= '9') {
                        if (Phone_Number.length() == 10) {
                            if (Phone_Number.charAt(0) == '9' || Phone_Number.charAt(0) == '8'
                                    || Phone_Number.charAt(0) == '7'
                                    || Phone_Number.charAt(0) == '6') {
                                ph_flag1 = 1;
                                break;
                            } else {
                                ph_flag1 = 0;
                            }
                        } else {
                            ph_flag1 = 0;
                        }
                    }
                }
                if (ph_flag1 == 1) {
                    break;
                } else if (ph_flag1 == 0) {
                    System.out.print("Enter valid number ");
                    System.out.println(
                            "Instructions : The number should start from 9,8,7 or 6\nThe number must have atleast 10 digits");
                }
            } // Validated Phone Number.

            System.out.println("Enter Age : ");
            age = 0;
            Boolean flag_Age = true;
            validInput = false;
            while (!validInput) {
                try {
                    age = scanner.nextInt();
                    while (flag_Age) {
                        if (age <= 0 || age >= 125) {
                            System.out.println("Enter Valid Age");
                            age = scanner.nextInt();
                        } else {
                            flag_Age = false;
                            break;
                        }
                    }
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("EXCEPTION : Enter Valid age");
                    scanner.next();
                    // Clear the invalid input
                }
            }
            // Validated Age.
            scanner.nextLine();

            System.out.println("---------------------------------------------------");
            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
            pass_details_ob = a.new passangers(firstName, lastName, email, Phone_Number, age);
            pass_details[i] = pass_details_ob;

        }

        do {
            System.out.print("Enter payment method (Credit_Card / Debit_Card): ");
            // scanner.nextLine();
            String paymentMethod = scanner.next();
            if ("Credit_card".equalsIgnoreCase(paymentMethod) || "Debit_card".equalsIgnoreCase(paymentMethod)) {
                System.out.print("Enter 12-digit account number: ");
                String accountNumber = scanner.next();
                System.out.print("Enter 3-digit CVV: ");
                String cvv = scanner.next();
                int Passanger_id =0;
                if (validatePaymentInfo(accountNumber, cvv)) {
                    System.out.println("Payment successful!");

                    for (int i = 0; i < totalPassengers; i++) {
                        storePassengerDetails(con, flightNumber, pass_details[i].getFirst_name(),
                                pass_details[i].getLast_name(), pass_details[i].getE_mail(),
                                pass_details[i].getPhone_Number(), pass_details[i].getAge());
                        int updatedAvailableSeats = availableSeats - totalPassengers;
                        updateAvailableSeatsInDatabase(con, flightNumber, updatedAvailableSeats);

                        CallableStatement callP_id = con.prepareCall("{call GetPassangerId(?,?,?,?)}");
                        callP_id.setString(1, pass_details[i].getFirst_name());
                        callP_id.setString(2, pass_details[i].getLast_name());
                        callP_id.setString(3, pass_details[i].getPhone_Number());
                        callP_id.executeQuery();
                        Passanger_id = callP_id.getInt(4);
                        String InsertBooking = "Insert into booking(Flight_Number,Passanger_id,Payment_Method) values(?,?,?)";
                        PreparedStatement st1 = con.prepareStatement(InsertBooking);
                        st1.setString(1, flightNumber);
                        st1.setInt(2, Passanger_id);
                        st1.setString(3, paymentMethod);
                        st1.executeUpdate();
                        // Get Booking_id.
                        CallableStatement callB_id = con.prepareCall("{call GetBookingId(?,?,?)}");
                        callB_id.setString(1, flightNumber);
                        callB_id.setInt(2, Passanger_id);
                        callB_id.executeQuery();
                        int book_id = callB_id.getInt(3);
                        // Get Flight Amount.
                        String getFlightAmount = "Select ticket_price from flights where flight_number = ?";
                        PreparedStatement flight_amt = con.prepareStatement(getFlightAmount);
                        flight_amt.setString(1, flightNumber);
                        // flight_amt.executeQuery();
                        ResultSet rs = flight_amt.executeQuery();
                        while (rs.next()) {
                            flight_price = rs.getInt(1);
                        }

                        // Insert data in payment.
                        String InsertPayment = "Insert into payment1(booking_id,payment_date,payment_amount,payment_method,Account_Number) values(?,?,?,?,?)";
                        PreparedStatement insertPaymentQuery = con.prepareStatement(InsertPayment);
                        // Date d = new java.sql.Date(System.currentTimeMillis());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // Get the current date and time as a LocalDateTime object
                        LocalDateTime currentDateTime = LocalDateTime.now();

                        // Format the LocalDateTime object as a string
                        String dateString = currentDateTime.format(formatter);

                        insertPaymentQuery.setInt(1, book_id);
                        insertPaymentQuery.setString(2, dateString);
                        insertPaymentQuery.setDouble(3, flight_price);
                        insertPaymentQuery.setString(4, paymentMethod);
                        insertPaymentQuery.setString(5, accountNumber);
                        insertPaymentQuery.executeUpdate();
                        BufferedWriter ticketWriter = new BufferedWriter(
                                new FileWriter(pass_details[i].getFirst_name() + "_Ticket.txt"));
                        ticketWriter.write("Welcome to Flight " + flightNumber + " .");
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.write("|----------------Passengers Details-----------------|");
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("Passenger First Name : " + pass_details[i].getFirst_name());
                        ticketWriter.newLine();
                        ticketWriter.write("Passenger Last Name : " + pass_details[i].getLast_name());
                        ticketWriter.newLine();
                        ticketWriter.write("Passenger Email : " + pass_details[i].getE_mail());
                        ticketWriter.newLine();
                        ticketWriter.write("Passenger Mobile Number : " + pass_details[i].getPhone_Number());
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("Passanger Id : "+ Passanger_id);
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.write("|-------------------Flight Details-------------------|");
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        String query = "SELECT * FROM flights WHERE flight_number = ?";
                        PreparedStatement statement = con.prepareStatement(query);
                        statement.setString(1, flightNumber);
                        ResultSet resultSet = statement.executeQuery();

                        List<Flight> flights = new ArrayList<>();
                        Flight flight = null;
                        while (resultSet.next()) {
                            String departure_place = resultSet.getString("departure_city");
                            String arrival_place = resultSet.getString("arrival_city");
                            String departureTime = resultSet.getString("departure_time");
                            String airlineName = resultSet.getString("AirlineName");
                            String arrivalTime = resultSet.getString("arrival_time");
                            double ticketPrice = resultSet.getDouble("ticket_price");
                            AIRLINE_RESERVATION a = new AIRLINE_RESERVATION();
                            flight = a.new Flight(departure_place, arrival_place, flightNumber, departureTime,
                                    arrivalTime,
                                    ticketPrice, availableSeats, airlineName);
                            // Populate flight object
                            flights.add(flight);
                        }
                        ticketWriter.write("Departure City : " + flight.getDeparture_place());
                        ticketWriter.newLine();
                        ticketWriter.write("Arrival City : " + flight.getArrival_place());
                        ticketWriter.newLine();
                        ticketWriter.write("Departure Time : " + flight.getDepartureTime());
                        ticketWriter.newLine();
                        ticketWriter.write("Arrival Time : " + flight.getArrivalTime());
                        ticketWriter.newLine();
                        ticketWriter.write("Ticket Price : " + flight.getTicketPrice() + " - PAID.");
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.write("|------------------Payment Details------------------|");
                        ticketWriter.newLine();
                        ticketWriter.write("#####################################################");
                        ticketWriter.newLine();
                        ticketWriter.newLine();
                        ticketWriter.write("Account Number : " + accountNumber);
                        ticketWriter.newLine();
                        ticketWriter.write("Payment Method : " + paymentMethod);
                        ticketWriter.newLine();
                        ticketWriter.write("Thanks For Visiting. Have a Safe and Happy Journey");
                        ticketWriter.flush();
                        ticketWriter.close();
                        String insertTicket = "Insert into ticket(booking_id,passanger_id,ticket) values(?,?,?)";
                        PreparedStatement preparedStatementTicket = con.prepareStatement(insertTicket);
                        preparedStatementTicket.setInt(1, book_id);
                        preparedStatementTicket.setInt(2, Passanger_id);
                        File f = new File(pass_details[i].getFirst_name() + "_Ticket.txt");
                        FileReader read_Ticket = new FileReader(f);
                        preparedStatementTicket.setCharacterStream(3, read_Ticket);
                        preparedStatementTicket.executeUpdate();
                    }
                    System.out.println("Total Payment Done of Rs. : " + (flight_price * totalPassengers) + " Of Total "
                            + totalPassengers + " Passengers");
                    break;
                } else {
                    System.out.println("Invalid payment information. Payment failed.");
                }
            } else {
                System.out.println("Invalid payment method");
            }
        } while (true);
    }

    private static boolean isFlightValid(Connection con, String flightNumber) throws SQLException {
        String query = "SELECT flight_number FROM flights WHERE flight_number = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, flightNumber);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If there's a matching flight number
        }
    }

    private static int getAvailableSeats(Connection con, String flightNumber) throws SQLException {
        String query = "SELECT available_seats FROM flights WHERE flight_number = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, flightNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("available_seats");
            }
            return 0;
        }
    }

    private static void storePassengerDetails(Connection con, String flightNumber, String firstName, String lastName,
            String email, String Phone_Number, int age) throws SQLException {
        String insertQuery = "INSERT INTO passanger (first_name, last_name, e_mail,phone_number,age) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(insertQuery)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, Phone_Number);
            statement.setInt(5, age);
            statement.executeUpdate();
        }
        int currentAvailableSeats = getAvailableSeats(con, flightNumber);
        updateAvailableSeatsInDatabase(con, flightNumber, currentAvailableSeats - 1);
    }

    private static boolean validatePaymentInfo(String accountNumber, String cvv) {
        boolean ans = true;
        for (char c : cvv.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        for (char c : accountNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        ans = accountNumber.length() == 12 && cvv.length() == 3;
        return ans;
    }

    private static void updateAvailableSeatsInDatabase(Connection con, String flightNumber, int updatedAvailableSeats)
            throws SQLException {
        String updateQuery = "UPDATE flights SET available_seats = ? WHERE flight_number = ?";
        try (PreparedStatement statement = con.prepareStatement(updateQuery)) {
            statement.setInt(1, updatedAvailableSeats);
            statement.setString(2, flightNumber);
            statement.executeUpdate();
        }
    }

    public class Flight {
        @Override
        public String toString() {
            return "Flight [departure_place=" + departure_place + ", arrival_place=" + arrival_place + ", flightNumber="
                    + flightNumber + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
                    + ", ticketPrice="
                    + ticketPrice + ", availableSeats=" + availableSeats + "]";
        }

        private String airlineName;

        public String getAirlineName() {
            return airlineName;
        }

        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }

        private String departure_place;
        private String arrival_place;
        private String flightNumber;
        private String departureTime;
        private String arrivalTime;
        private double ticketPrice;
        private int availableSeats;

        public Flight(String departure_place, String arrival_place, String flightNumber, String departureTime,
                String arrivalTime, double ticketPrice, int availableSeats, String airlineName) {
            this.departure_place = departure_place;
            this.arrival_place = arrival_place;
            this.flightNumber = flightNumber;
            this.airlineName = airlineName;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.ticketPrice = ticketPrice;
            this.availableSeats = availableSeats;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public double getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }

        public void setAvailableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
        }

        public String getDeparture_place() {
            return departure_place;
        }

        public void setDeparture_place(String departure_place) {
            this.departure_place = departure_place;
        }

        public String getArrival_place() {
            return arrival_place;
        }

        public void setArrival_place(String arrival_place) {
            this.arrival_place = arrival_place;
        }

        // Constructors, getters, setters
    }

    public class passangers {
        String first_name;
        String last_name;
        String E_mail;
        String Phone_Number;
        int age;

        public passangers(String first_name, String last_name, String e_mail, String phone_Number, int age) {
            this.first_name = first_name;
            this.last_name = last_name;
            E_mail = e_mail;
            Phone_Number = phone_Number;
            this.age = age;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getE_mail() {
            return E_mail;
        }

        public void setE_mail(String e_mail) {
            E_mail = e_mail;
        }

        public String getPhone_Number() {
            return Phone_Number;
        }

        public void setPhone_Number(String phone_Number) {
            Phone_Number = phone_Number;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "passangers [first_name=" + first_name + ", last_name=" + last_name + ", E_mail=" + E_mail
                    + ", Phone_Number=" + Phone_Number + ", age=" + age + "]";
        }
    }

}