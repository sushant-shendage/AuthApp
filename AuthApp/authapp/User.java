// package authapp;

import java.io.Serializable;

/**
 * Represents a User entity with userName, phoneNumber, and password attributes.
 */
public class User implements Serializable{
    private String userName;
    private String phoneNumber;
    private String password;

    /**
     * Constructs a User with the specified userName, phoneNumber, and password.
     *
     * @param userName    the user's name
     * @param phoneNumber the user's phone number
     * @param password    the user's password
     */
    public User(String userName, String phoneNumber, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    /**
     * Gets the user's name.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     *
     * @param userName the new userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber the new phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return a string containing userName, phoneNumber, and password
     */
    @Override
    public String toString() {
        return "User [userName=" + userName + ", phoneNumber=" + phoneNumber + ", password=" + password + "]";
    }

    /**
     * Generates a hash code based on the phoneNumber.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return (int) Long.parseLong(this.phoneNumber);
    }

    /**
     * Compares this User with another object for equality based on phoneNumber.
     *
     * @param obj the object to compare
     * @return true if phoneNumbers are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return this.phoneNumber.equals(((User) obj).phoneNumber);
    }
     
}
