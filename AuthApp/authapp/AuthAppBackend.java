import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * The {@code AuthAppBackend} class is responsible for handling the backend logic of the authentication application.
 * This includes user validation during the sign-in and log-in processes, as well as managing user data persistence
 * in a file. It handles input validation for fields like username, password, and phone number, and provides methods 
 * for clearing input fields and checking whether a user exists.
 */
public class AuthAppBackend {

    /**
     * Validates the input fields in the sign-in frame. It checks if the fields are non-empty, validates the username,
     * password, and phone number format, and ensures the password meets certain criteria.
     * Displays appropriate error messages for invalid input.
     *
     * @param userName The username input by the user
     * @param password The password input by the user
     * @param phoneNumber The phone number input by the user
     */
    static void allFieldValidationSignInFrame(String userName, String password, String phoneNumber) {

        // Check if any fields are empty
        if (userName.equals("") || password.equals("") || phoneNumber.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are compulsory..!", "compulsory field",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Ensure username starts with an alphabet
        if (userName.charAt(0) >= '0' && userName.charAt(0) <= '9') {
            JOptionPane.showMessageDialog(null, "User name first character must be an alphabet..!", "invalid userName",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Ensure username is at least two characters long
        if (userName.length() < 2) {
            JOptionPane.showMessageDialog(null, "User name must contain at least two characters..!",
                    "invalid userName", JOptionPane.OK_OPTION);
            return;
        }

        // Ensure password is at least 6 characters long
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password length must be 6 or greater", "invalid password",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Validate password contains at least one uppercase, lowercase, digit, and special character
        boolean upperCaseCharacter = false, lowerCaseCharacter = false, specialCharacter = false, digits = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                lowerCaseCharacter = true;
            } else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                upperCaseCharacter = true;
            } else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                digits = true;
            } else {
                specialCharacter = true;
            }
        }

        // Ensure password meets all character requirements
        if (!(upperCaseCharacter && lowerCaseCharacter && specialCharacter && digits)) {
            JOptionPane.showMessageDialog(null,
                    "Password should contain at least 1 upper case, lower case, digit, and special symbol..!",
                    "invalid password",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Ensure phone number length is 10 characters
        if (!(phoneNumber.length() == 10)) {
            JOptionPane.showMessageDialog(null, "Number length should be 10..!", "invalid phoneNumber",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Ensure phone number starts with 7, 8, or 9
        if (!(phoneNumber.charAt(0) == '7' || phoneNumber.charAt(0) == '8' || phoneNumber.charAt(0) == '9')) {
            JOptionPane.showMessageDialog(null, "Number's first digit should be 7, 8, or 9", "invalid phoneNumber",
                    JOptionPane.OK_OPTION);
            return;
        }

        // Ensure all characters in phone number are digits
        for (int i = 0; i < phoneNumber.length(); i++) {
            if ((phoneNumber.charAt(i) < '0' && phoneNumber.charAt(i) > '9')) {
                JOptionPane.showMessageDialog(null, "All characters must be numbers", "invalid phoneNumber",
                        JOptionPane.OK_OPTION);
                return;
            }
        }

        // Check if the user already exists, and if not, register the user
        userAlreadyExist(new User(userName, phoneNumber, password));
    }

    /**
     * Clears all input fields in the sign-in frame.
     *
     * @param userName The JTextField for the username input
     * @param password The JPasswordField for the password input
     * @param phoneNumber The JTextField for the phone number input
     */
    static void clearAllFields(JTextField userName, JPasswordField password, JTextField phoneNumber) {
        userName.setText("");
        password.setText("");
        phoneNumber.setText("");
    }

    /**
     * Clears all input fields in the log-in frame.
     *
     * @param phoneNumber The JTextField for the phone number input
     * @param password The JPasswordField for the password input
     */
    static void clearAllFields(JTextField phoneNumber, JPasswordField password) {
        password.setText("");
        phoneNumber.setText("");
    }

    /**
     * Checks if the user already exists in the file. If the user does not exist, the user is added to the file.
     *
     * @param u1 The {@code User} object containing the user's details
     */
    static void userAlreadyExist(User u1) {

        // If the file is empty, add the user and show a success message
        if (AuthAppBackendFileHandling.isFileEmpty()) {
            AuthAppBackendFileHandling.writeDataToFile(u1);
            JOptionPane.showMessageDialog(null, "Successfully registered..!", "Success", JOptionPane.OK_OPTION);
        } else {
            // If the user exists, show a failure message
            if (AuthAppBackendFileHandling.isObjectPresentInFile(u1)) {
                JOptionPane.showMessageDialog(null, "User already exists..!", "Failure", JOptionPane.OK_OPTION);
            } else {
                // If the user doesn't exist, add them to the file and show a success message
                AuthAppBackendFileHandling.writeDataToFile(u1);
                JOptionPane.showMessageDialog(null, "Successfully registered..!", "Success", JOptionPane.OK_OPTION);
            }
        }
    }

    /**
     * Validates the user credentials during the log-in process.
     * It checks if the account exists, and if the provided phone number and password match the stored data.
     * Displays success or failure messages based on the login attempt.
     *
     * @param phoneNumber The phone number input by the user
     * @param password The password input by the user
     */
    static void allFieldValidationLogInFrame(String phoneNumber, String password) {
        
        // If the file is empty, display a message indicating no accounts exist
        if (AuthAppBackendFileHandling.isFileEmpty()) {
            JOptionPane.showMessageDialog(null, "Account does not exist..!", "Fail", JOptionPane.OK_OPTION);
        } else {

            // Retrieves all users from the file and attempts to find the user with the matching phone number
            ArrayList<User> allUsers = AuthAppBackendFileHandling.dataFromFile();
            System.out.println("--------------");
            allUsers.forEach(System.out::println);
            System.out.println("--------------");

            try {
                // Find the user matching the phone number
                User userFound = allUsers.stream()
                        .filter(u1 -> (u1.getPhoneNumber().equals(phoneNumber)))
                        .collect(Collectors.toList()).get(0);

                System.out.println(userFound);
                
                // If the password matches, log the user in successfully
                if (userFound.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(null, "Logged in successfully..!", "Success", JOptionPane.OK_OPTION);
                } else {
                    // If the password is incorrect, show a login failure message
                    JOptionPane.showMessageDialog(null, "Wrong credentials..!", "Log in fail", JOptionPane.OK_OPTION);
                }
                
            } catch (IndexOutOfBoundsException iobe) {
                // If the user is not found in the file, show a message that the account does not exist
                JOptionPane.showMessageDialog(null, "Account does not exist..!", "Fail", JOptionPane.OK_OPTION);
            }
        }
    }
}
