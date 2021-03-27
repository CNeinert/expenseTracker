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

        var test = (TransactionCategory) selectById(TransactionCategory.class, 1L);
        //create the standard Categories only once
        if (test == null){
            //TODO: provide translation
            String[] standardCategories = {"Miete", "Nebenkosten", "Strom/Wasser/Gas", "Telefon/Internet/Mobil/TV",
                    "Rundfunkgebühr", "Abonnement", "Versicherung", "Sparen/Investieren", "KFZ", "Mobilität/ÖPNV",
                    "Taschengeld", "Schuldentilgung", "Sonstiges"};
            String[] standardIncomeCategories = { "Lohn/Gehalt", "ALG I / II", "Wohngeld", "Kindergeld", "Unterhaltszahlung",
                    "Rente", "Pflegegeld", "Dividenden", "Sonstiges"};
            String[] standardLocationCategories = {"Supermarkt", "Drogerie", "Baumarkt", "Online", "Imbis/Restaurant", "Apotheke"};


            for (int i = 0; i < standardCategories.length; i++) {
                TransactionCategory transactionCategory = new TransactionCategory();
                transactionCategory.setCategory_id(i+1);
                transactionCategory.setCategory(standardCategories[i]);
                transactionCategory.setIncome(false);
                this.persist(transactionCategory);
            }
            for (int i = 0; i < standardIncomeCategories.length; i++) {
                TransactionCategory transactionIncCategory = new TransactionCategory();
                transactionIncCategory.setCategory_id(i+1);
                transactionIncCategory.setCategory(standardIncomeCategories[i]);
                transactionIncCategory.setIncome(true);
                this.persist(transactionIncCategory);
            }


            for (int i = 0; i < standardLocationCategories.length; i++) {
                LocationCategory lc = new LocationCategory();
                lc.setCategory_id(i+1);
                lc.setLocationCategory(standardLocationCategories[i]);
                this.persist(lc);
            }
            System.out.println(" - New categories added! - ");
        }else{
            System.out.println(" - No new categories added! - ");
        }




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
            dao.createIfNotExists(object);
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
            return null;
        }

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
