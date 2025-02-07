import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class 'AuthAppLogInFrame' is responsible for creating the 'LogInFrame'
 * (LogIn window). It sets up all UI components and user interaction
 * functionalities required for user authentication.
 */
public class AuthAppLogInFrame {

    /** Variable representing the main LogIn frame */
    JFrame LogInFrame;

    /** Variables representing various labels on the LogIn frame */
    JLabel phoneNumberLabel, passwordLabel, appLabel, LogInLabel;

    /** Variables representing input fields for phone number and password */
    JTextField fieldphoneNumber;
    JPasswordField fieldsPassword;

    /** Variables representing buttons in the LogIn frame */
    JButton submiButton, cancelButton;

    /**
     * Constructor for 'AuthAppLogInFrame' initializes and sets up the LogIn
     * frame.
     */
    AuthAppLogInFrame() {
        LogInFrame();
    }

    /**
     * Method to:
     * 1. Create and configure the layout of the LogIn frame.
     * 2. Load and set up all corresponding UI components.
     */
    void LogInFrame() {
        LogInFrame = new JFrame("MiniAuthApp");
        LogInFrame.setLayout(null);
        LogInFrame.setSize(242, 300);
        LogInFrame.setResizable(false);
        LogInFrame.setVisible(true);

        appLabel = new JLabel("LogIn");
        appLabel.setOpaque(true);
        appLabel.setBounds(15, 5, 35, 20);
        appLabel.setBackground(Color.GREEN);
        LogInFrame.add(appLabel);

        phoneNumberComponent();
        passwordComponent();
        submitButton();
        cancelButton();
        LogInlabel();

        LogInFrame.revalidate();
        LogInFrame.repaint();
    }

    /**
     * Creates and configures the phone number input components, consisting of a
     * label and text field.
     */
    void phoneNumberComponent() {
        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(15, 15, 100, 50);
        LogInFrame.add(phoneNumberLabel);

        fieldphoneNumber = new JTextField();
        fieldphoneNumber.setBounds(15, 55, 200, 25);
        LogInFrame.add(fieldphoneNumber);
    }

    /**
     * Creates and configures the password input components, consisting of a label
     * and password field.
     */
    void passwordComponent() {
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(15, 75, 100, 50);
        LogInFrame.add(passwordLabel);

        fieldsPassword = new JPasswordField();
        fieldsPassword.setBounds(15, 115, 200, 25);
        LogInFrame.add(fieldsPassword);
    }

    /**
     * Creates and configures the submit button, which triggers the login validation
     * process.
     */
    void submitButton() {
        submiButton = new JButton("Submit");
        submiButton.setBounds(15, 165, 200, 25);
        LogInFrame.add(submiButton);
        submiButton.addActionListener(al -> AuthAppBackend.allFieldValidationLogInFrame(
                fieldphoneNumber.getText(), fieldsPassword.getText()));
    }

    /**
     * Creates and configures the cancel button, which clears input fields.
     */
    void cancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(35, 195, 160, 20);
        LogInFrame.add(cancelButton);
        cancelButton.addActionListener(al -> AuthAppBackend.clearAllFields(fieldphoneNumber, fieldsPassword));
    }

    /**
     * Creates a label that allows users to navigate to the Sign-In frame if they
     * do not have an account.
     */
    void LogInlabel() {
        LogInLabel = new JLabel("I don't have an account...!");
        LogInLabel.setBounds(50, 230, 150, 20);
        LogInFrame.add(LogInLabel);
        LogInLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AuthAppSignInFrame();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * Sets the UI look and feel to match the system's native appearance.
     */
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
