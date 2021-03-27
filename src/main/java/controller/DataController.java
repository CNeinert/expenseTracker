package main.java.controller;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.db.SqliteDatabaseType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import main.java.model.*;

import java.io.IOException;
import java.sql.SQLException;

public class DataController {
    //Attributes
    private String url = "jdbc:sqlite:file:data.db";
    private ConnectionSource connectionSource;


    public DataController() throws SQLException {
        initDb();
    }


    //Methods

    public void initDb() throws SQLException {
        // Init
        connect();

        TableUtils.createTableIfNotExists( getConnectionSource(), Transaction.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), Product.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), JoinTransactionProduct.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), Location.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), TransactionCategory.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), LocationCategory.class );


    }

    private void connect() throws SQLException {
        //Connection to the sqlite db
        setConnectionSource(new JdbcConnectionSource( getUrl(), "", "", new SqliteDatabaseType() ));
    }



    public void open(){
        try {
            connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(){
        try {
            connectionSource.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public void persist(Object object){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), (Class<Object>) object.getClass());
            dao.create(object);
            //close connection
            close();
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public Object selectById(Class classObject , Long id){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), classObject );
            Object object = dao.queryForId( id.toString() );
            //close connection
            close();
            return object;
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }

        return null;
    }

    //Getter and Setter
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

}
