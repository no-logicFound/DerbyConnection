import java.io.IOException;
import java.sql.*;

public class TestConnection {




    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String loginTable = "create table " + Database.LOGIN_TABLE_NAME +
                            "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                            " referenceName varchar(60), " +
                            " username varchar(60), " +
                            " password varchar(60), " +
                            " primary key( id ))";

        String masterPasswordTable = "create table " + Database.MASTER_PASS_TABLE_NAME +
                                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                                    " masterPassword VARCHAR(128), " +
                                    " PRIMARY KEY ( id ))";


        Database db = new Database();

        db.deleteDatabase();
        db.createDatabase();
        db.createTable(loginTable);
        db.createTable(masterPasswordTable);
        db.insertLoginData("facebook", "brad", "pass");
        db.insertMasterPassword("password");
    }
}
