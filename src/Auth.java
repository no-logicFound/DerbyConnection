import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;


public class Auth {

    String createMasterPass() throws IOException {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please provide a secure master password that you will not forget: ");
        String firstInput = sc.readLine();

        System.out.print("Now re-enter the master password for verification: ");
        String secondInput = sc.readLine();


        if(firstInput.equals(secondInput)){System.out.println("You're all set!!");}
        else {
            System.out.println("Nah, try again.");
            createMasterPass();
        }
        return secondInput;
    }

    boolean isFirstTime(){
        boolean firstTime = false;
        File file = new File("test.txt");

        if(!file.exists()){firstTime = true;
            try{file.createNewFile();}
            catch (IOException e){e.printStackTrace();}
        }
        file.deleteOnExit();
        return firstTime;
    }

    boolean isValidMasterPassword() throws SQLException {
        int numOfTries = 3;
        while (numOfTries>0){
            System.out.print("Please enter your master password: ");
            String userInput = new Scanner(System.in).nextLine(); // TODO: replace with console.readPassword
            String givenPassword = new Encryption().convertPwdToHash(userInput);
            String storedPassword = new Database().retrieveMasterPassword();

            // compare given password to the stored masterPassword
            numOfTries--;
            if(givenPassword.equals(storedPassword)){
                return true;}
            System.out.println("You have " + numOfTries + " tries left." );
        }
        ;
        return false;
    }
}
