// package authapp;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handles all file-related backend functionalities for the application.
 * It manages reading and writing user data from/to a file.
 */
public class AuthAppBackendFileHandling {

    /**
     * A static ArrayList that holds user data retrieved from the file.
     */
    static ArrayList<User> allUsersFromFile = new ArrayList<>();

    /**
     * ObjectInputStream instance for reading objects from the file.
     */
    static ObjectInputStream objectInputStreamObj1;

    @SuppressWarnings("unchecked")
    /**
     * Reads user data from the file and loads it into an ArrayList.
     * 
     * @return An ArrayList containing all users stored in the file.
     */
    static ArrayList<User> dataFromFile() {
        try {
            objectInputStreamObj1 = new ObjectInputStream(new FileInputStream("./userInfoInFile.txt"));

            try {
                // Read the user data from the file and cast it to an ArrayList<User>
                allUsersFromFile = (ArrayList<User>) objectInputStreamObj1.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStreamObj1 != null) {
                    objectInputStreamObj1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return allUsersFromFile;
    }

    /**
     * Checks if the user data file is empty.
     * 
     * @return true if the file contains no user data, false otherwise.
     */
    static boolean isFileEmpty() {
        return dataFromFile().isEmpty();
    }

    /**
     * Writes a new user object to the file after verifying that the user does not already exist.
     * 
     * @param newUser The user object to be added to the file.
     */
    static void writeDataToFile(User newUser) {
        allUsersFromFile = dataFromFile(); // Load existing user data

        try (ObjectOutputStream objectOutputStreamObj1 = new ObjectOutputStream(
                new FileOutputStream("./userInfoInFile.txt"))) {
            allUsersFromFile.add(newUser); // Add new user to the list
            objectOutputStreamObj1.writeObject(allUsersFromFile); // Save updated list to file
            System.out.println("User data successfully written to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a given user already exists in the file.
     * 
     * @param newUser The user object to check.
     * @return true if the user already exists, false otherwise.
     */
    static boolean isObjectPresentInFile(User newUser) {
        allUsersFromFile = dataFromFile();
        return allUsersFromFile.contains(newUser);
    }

}
