import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    byte[] digestedPassword = {};
    String generatedPassword;


    // change password to --> byte[] digestPassword
    private void digestPassword (String password){
        byte[] bytePassword = password.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            digestedPassword = md.digest(bytePassword); // change password to byte array
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    // takes byte[] digestedPassword to it's hashed version
    private String hashPassword (byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }


    String convertPwdToHash(String masterPass){
        digestPassword(masterPass);
        return hashPassword(digestedPassword);
    }

}
