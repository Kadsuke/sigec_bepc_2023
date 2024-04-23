/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class Factory {
    private String dbname;
    private Database database;
    
    public Factory(String dbname) throws SQLException{
        this.setDbname(dbname);
        this.database = new Database(this);
    }
    public static Factory getInstance(String dbname) throws SQLException{
        return new Factory(dbname);
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
    
    public String getUrl(){
        return "jdbc:sqlite:"+this.getDbname()+".db";
    }
    public Connection connect() throws SQLException{
        Connection conn = DriverManager.getConnection(this.getUrl());
        return conn;
    }

    public void close() throws SQLException {
        this.connect().close();
    }

    public Boolean isclose() throws SQLException {
        return  this.connect().isClosed();
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
    
    
}
