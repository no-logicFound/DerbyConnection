import org.apache.commons.io.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {
    static final String DATABASE_NAME = "testDB";
    static final String LOGIN_TABLE_NAME = "login_info";
    static final String MASTER_PASS_TABLE_NAME = "master_password_holder";
    static final String DATABASE_URL = "jdbc:derby:" + DATABASE_NAME;


    // create database name testDB
    public void createDatabase() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL + ";create=true");
        System.out.println("Connected to Derby database.");
        con.close();
    }

    // delete database
    public void deleteDatabase() throws IOException {
        File file = new File("testdb");
        FileUtils.deleteDirectory(file);
        System.out.println("Database deleted.");
    }

    // create table based on the query passed in
    public void createTable (String sql) throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        Statement s = con.createStatement();
        s.execute(sql);
        System.out.println("table created");
        con.close();
    }

    // insert login info into table
    public void insertLoginData (String referenceName, String username, String password) throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String sql = "insert into " + LOGIN_TABLE_NAME + "values(?)";
        PreparedStatement prepStatement = con.prepareStatement(sql);
        prepStatement.setString(1, referenceName);
        prepStatement.setString(2, username);
        prepStatement.setString(3, password);
        prepStatement.execute();
    }

    // insert master password into masterPass table
    public void insertMasterPassword(String hashMasterPass) throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL);
        String sql = "insert into " + MASTER_PASS_TABLE_NAME + "(masterPassword) " +
                "values(?);";
        PreparedStatement prepStatement = con.prepareStatement(sql);
        prepStatement.setString(1, hashMasterPass);
        prepStatement.execute();
    }

}





