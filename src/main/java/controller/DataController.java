package main.java.controller;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.db.SqliteDatabaseType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.model.*;

import java.sql.SQLException;
import java.util.List;

public class DataController {
    //Attributes
    private String url = "jdbc:sqlite:file:data.db";
    private ConnectionSource connectionSource;


    public DataController()  {
        try{
            initDb();
        }catch (SQLException sqle){
            System.err.println("[DATABASE] -> Failed to initialise the database!");
        }
    }


    //Methods

    public void initDb() throws SQLException {
        // Init
        connect();

        TableUtils.createTableIfNotExists( getConnectionSource(), Transaction.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), PaymentMethod.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), Receiver.class );
        TableUtils.createTableIfNotExists( getConnectionSource(), TransactionCategory.class );


        var test = (TransactionCategory) selectById(TransactionCategory.class, 1L);
        //create the standard Categories only once
        if (test == null){
            //TODO: provide translation

            String[] standardIncomeCategories = { "Lohn/Gehalt", "Kindergeld / Unterhalt", "Rente / Pflegegeld",
                    "Kapitalmarkt", "Sonstiges"};

            String[] standardCategories = {"", "Miete", "Nebenkosten", "Strom/Wasser/Gas", "Telefon/Internet/Mobil/TV",
                    "Rundfunkgebühr", "Abonnement", "Versicherung","Nahrungsmittel","Drogerie", "Gesundheit",
                    "Sparen/Investieren", "KFZ", "Mobilität/ÖPNV", "Geschenke", "Mittag (Arbeit)", "Bäcker","Süßkram",
                    "Schreibwaren / Büro", "Friseur", "Technik", "Sport", "Reparaturen","Taschengeld", "Schuldentilgung",
                    "Sonstiges"};

            for (String standardIncomeCategory : standardIncomeCategories) {
                TransactionCategory transactionIncCategory = new TransactionCategory(standardIncomeCategory);
                this.persist(transactionIncCategory);
            }
            for (String standardCategory : standardCategories) {
                TransactionCategory transactionCategory = new TransactionCategory(standardCategory);
                this.persist(transactionCategory);
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
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
    }


    public void persist(Persistable object){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), (Class<Object>) object.getThisClass());
            if (!object.isEmpty()){
                dao.createIfNotExists(object);
            }
            //close connection
            close();
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public Object selectById(Class<?> classObject , Long id){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), (Class<Object>) classObject);
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

    public ObservableList<Object> selectAll(Class classObject){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), classObject );

            ObservableList<Object> observableList = FXCollections.observableArrayList(dao.queryForAll());

            //close connection
            close();
            return observableList;
        }
        catch (SQLException e ) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Object> selectAllByField(Class classObject, String field, Object object){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), classObject);
            List<Object> queryResult = dao.queryForEq( field, object );
            //close connection
            close();
            return queryResult;
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }

        return null;
    }

    public Object findByIdentifier(Class classObject, String fieldname, String searchterm){
        try {
            // Database Access Object (DAO) for the Contact class
            Dao<Object, String> dao = DaoManager.createDao( getConnectionSource(), classObject);
            var queryResults = dao.queryBuilder().where().eq(fieldname, searchterm).query();
            //close connection
            close();
            if (queryResults.size() > 0 ){
                return queryResults.get(0);
            }
            return null;
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
