import org.apache.commons.io.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {
    static final String DATABASE_NAME = "testDB";
    static final String LOGIN_TABLE_NAME = "login_info";
    static final String MASTER_PASS_TABLE_NAME = "master_password_holder";
    static final String DATABASE_URL = "jdbc:derby:" + DATABASE_NAME + ";create=true";

    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    Menu menu = new Menu();
    String referenceName = menu.referenceName;
    String username = menu.username;
    String password = menu.password;

    String insertLoginData = "insert into login_info(referenceName, username, password)values" +
                            "('" + referenceName + "', " + "'" + username + "', " + "'" + password + "')";

    public String queryInsertMasterPass = "insert into " + MASTER_PASS_TABLE_NAME + "(masterPassword)values('"+ hashMasterPass +"')";

//    String queryUpdateMasterPass = "update " + MASTER_PASS_TABLE_NAME + "masterPassword = " + newMasterPass + " where id=1";

    String queryRetrieveMasterPass = "select * from" + MASTER_PASS_TABLE_NAME;


    // Get connection to database
    public void connection() throws SQLException {
        con = DriverManager.getConnection(DATABASE_URL);
    }

    //create statement for executable query
    public void createStatement(String sql) throws SQLException {
        con.createStatement().execute(sql);
    }

    // create database name testDB
    public void createDatabase() throws SQLException {
        // connection() method will create database
        connection();
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
        connection();
        createStatement(sql);
        System.out.println("table created");
        con.close();
    }

    // insert login info into table
    public void  insertLoginData () throws SQLException {
        connection();
        createStatement(insertLoginData);
    }

    // insert master password into masterPass table
    public void insertMasterPassword () throws SQLException {
        connection();
        createStatement(queryInsertMasterPass);
    }

//    public void updateMasterPassword(String sql) throws SQLException {
//        connection();
//        createStatement(queryUpdateMasterPass);
//    }

    public String retrieveMasterPassword() throws SQLException {
        connection();
        resultSet(queryRetrieveMasterPass);

        String masterPassword = "";
        while (rs.next()) {
            masterPassword = rs.getString("masterPassword");
        }
        return masterPassword;
    }

    public void resultSet(String sql)throws SQLException{
        stmt = con.createStatement();
        rs=stmt.executeQuery(sql);
    }
}





