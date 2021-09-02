import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    String referenceName,username,password;
    public void greetNewUser() {
        System.out.println("""

                             _                                 _                          _\s
                            | |                               | |                        | |
                 _ _ _ _____| | ____ ___  ____  _____    _____| |__   ___  _____  ____ __| |
                | | | | ___ | |/ ___/ _ \\|    \\| ___ |  (____ |  _ \\ / _ \\(____ |/ ___/ _  |
                | | | | ____| ( (__| |_| | | | | ____|  / ___ | |_) | |_| / ___ | |  ( (_| |
                 \\___/|_____)\\_\\____\\___/|_|_|_|_____)  \\_____|____/ \\___/\\_____|_|   \\____|
                                                                                           \s
                """);
    }

    public void showMenu() {

        System.out.println("--------------------------");
        System.out.println("1 --> Enter reference name and/or username, password.");
        System.out.println("3 --> Search for password.");
        System.out.println("4 --> Exit.");
        System.out.println("--------------------------");
    }

    public void executeMenu() throws SQLException {
        System.out.print("Choose from the menu above: ");
        Scanner sc = new Scanner(System.in);
        int in = 0;

        try {
            in = sc.nextInt();
            sc.nextLine();
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Enter a number please ╯︿╰\n");
            executeMenu();
        }


        switch (in) {
            case 1 -> {
                Database db = new Database();

                System.out.print("Enter a reference name to the password: ");
                referenceName = sc.nextLine();
                System.out.print("Enter username: ");
                username = sc.nextLine();
                System.out.print("Enter password: ");
                password = sc.nextLine();
                db.insertLoginData();
                executeMenu();
            }
            case 3 -> {
                System.out.print("Search: ");
                sc.nextLine();
                executeMenu();
            }

            case 4 -> {
                System.exit(0);
            }

            default -> throw new IllegalStateException("Well that was unexpected: " + in);
        }
        sc.close();
    }
}
