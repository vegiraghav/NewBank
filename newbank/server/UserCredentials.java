package newbank.server;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.ArrayList;
import java.util.List;

public class UserCredentials {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String SECRET_KEY = "ThisIsASecretKey";
    private static final String CREDENTIALS_FILE = "credentials.dat";

    public static void storeCredentials(String userId, String password) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());

            FileOutputStream fos = new FileOutputStream(CREDENTIALS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userId);
            oos.writeObject(encryptedPassword);
            oos.close();
            fos.close();

            System.out.println("User credentials stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validateCredentials(String userId, String password) {
        try {
            FileInputStream fis = new FileInputStream(CREDENTIALS_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String storedUserId = (String) ois.readObject();
            byte[] encryptedPassword = (byte[]) ois.readObject();
            ois.close();
            fis.close();

            if (!userId.equals(storedUserId)) {
                System.out.println("Invalid user ID.");
                return false;
            }

            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedPassword = cipher.doFinal(encryptedPassword);

            if (password.equals(new String(decryptedPassword))) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Incorrect password.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<String> getAllUsers() {
        List<String> users = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(CREDENTIALS_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                String userId = (String) ois.readObject();
                ois.readObject(); // Skip encrypted password
                users.add(userId);
            }

            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

  
}
