import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class responsible for creating the Sign-In frame for the authentication application.
 * This frame contains input fields for username, password, and phone number,
 * along with submit and cancel buttons.
 */
public class AuthAppSignInFrame {

    // Variable to store the password input
    private String password = "";

    /**
     * Retrieves the current password stored in the object.
     * @return The password as a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the provided value.
     * @param password The new password to be stored.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // JFrame representing the main Sign-In window
    JFrame SignInFrame;

    // Labels for UI components
    JLabel userNameLabel, passwordLabel, phoneNumberLabel, appLabel, signInLabel;
    
    // Input fields for user data
    JTextField fieldPhoneNumber, fieldUserName;
    JPasswordField fieldPassword;

    // Buttons for user actions
    JButton submiButton, cancelButton;
    
    /**
     * Constructor initializes and sets up the Sign-In frame.
     */
    AuthAppSignInFrame() {
        SignInFrame();
    }
    
    /**
     * Initializes and configures the layout of the Sign-In frame.
     * Loads and adds all UI components to the frame.
     */
    void SignInFrame() {
        
        // Create and configure the Sign-In frame
        SignInFrame = new JFrame("MiniAuthApp");
        SignInFrame.setLayout(null);
        SignInFrame.setSize(242, 350);
        SignInFrame.setResizable(false);
        SignInFrame.setVisible(true);

        // Create and configure the application label
        appLabel = new JLabel("SignIn");
        appLabel.setOpaque(true); // Enable background visibility
        appLabel.setBounds(15, 5, 35, 20);
        appLabel.setBackground(Color.GREEN);
        SignInFrame.add(appLabel);
    
        // Load UI components
        userNameComponent();
        passwordComponent();
        phoneNumberComponent();
        submitButton();
        cancelButton();
        
        // Refresh UI
        SignInFrame.revalidate();
        SignInFrame.repaint();
    }
  
    /**
     * Creates and configures the username input components.
     * Includes a label and a text field for user input.
     */
    void userNameComponent() {
        userNameLabel = new JLabel("UserName");
        userNameLabel.setBounds(15, 15, 100, 50);
        SignInFrame.add(userNameLabel);
        
        fieldUserName = new JTextField();
        fieldUserName.setBounds(15, 55, 200, 25);
        SignInFrame.add(fieldUserName);
    }
    
    /**
     * Creates and configures the password input components.
     * Includes a label and a password field for user input.
     */
    void passwordComponent() {     
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(15, 75, 100, 50);
        SignInFrame.add(passwordLabel);
        
        fieldPassword = new JPasswordField();
        fieldPassword.setBounds(15, 115, 200, 25);     
        SignInFrame.add(fieldPassword);
    }
  
    /**
     * Creates and configures the phone number input components.
     * Includes a label and a text field for user input.
     */
    void phoneNumberComponent() {     
        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(15, 140, 100, 50);
        SignInFrame.add(phoneNumberLabel);

        fieldPhoneNumber = new JTextField();
        fieldPhoneNumber.setBounds(15, 180, 200, 25);
        SignInFrame.add(fieldPhoneNumber);
    }

    /**
     * Creates and configures the submit button.
     * This button triggers validation and submission of user input data.
     */
    void submitButton() {
        submiButton = new JButton("Submit");
        submiButton.setBounds(15, 225, 200, 25);
        SignInFrame.add(submiButton);
        submiButton.addActionListener(al -> AuthAppBackend.allFieldValidationSignInFrame(
                fieldUserName.getText(), fieldPassword.getText(), fieldPhoneNumber.getText()
        ));
    }

    /**
     * Creates and configures the cancel button.
     * This button clears all input fields when clicked.
     */
    void cancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(35, 255, 160, 20);
        SignInFrame.add(cancelButton);
        cancelButton.addActionListener(al -> AuthAppBackend.clearAllFileds(
                fieldUserName, fieldPassword, fieldPhoneNumber
        ));
    }
 
    /**
     * Main method to create an instance of 'AuthAppSignInFrame' and display the Sign-In frame.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new AuthAppSignInFrame();
    }
    
    // Set the Look and Feel to match the system's native appearance
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
