package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//TODO lösch funktionen schreiben
// video von dem alles kopiert ist https://www.youtube.com/watch?v=JPsWaI5Z3gs

public class Database {

    private Connection con;
    private boolean hasData = false;

    public Database() throws ClassNotFoundException, SQLException {
        getConnection();
    }

    private void initialise() throws SQLException {
        if (!hasData)
            hasData = true;
        Statement state = con.createStatement();
        ResultSet result = state
                .executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name = 'Transactions';");
        System.out.println("SQL RESULT: " + result.toString());
        if (!result.next()) {
            buildTables();
        }
    }

    public Transaction insertTransaction(Transaction transaction) throws SQLException {

        PreparedStatement buildState = con.prepareStatement("INSERT INTO Transactions "
                + "(date, location, product_name, category, single_price,amount, total_price, ) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?);");

        buildState.setString(1, transaction.getDate());
        buildState.setString(2, transaction.getLocation());
        buildState.setString(3, transaction.getProduct_name());
        buildState.setInt(4, transaction.getCategory().getCategory_id());
        buildState.setDouble(5, transaction.getSingle_price());
        buildState.setDouble(6, transaction.getAmount());
        buildState.setDouble(7, transaction.getTotal_price());
        buildState.execute();

        return transaction;
    }


    public Transaction getTransaction(int id) throws SQLException {
        PreparedStatement buildState = con.prepareStatement(
                "SELECT date, location, product_name, category, single_price, amount, total_price "
                        + "FROM Transactions " + "WHERE id = ?"
                        + ";");
        buildState.setInt(1, id);
        ResultSet result = buildState.executeQuery();
        result.next();

        Transaction transaction = new Transaction();

        transaction.setDate(result.getString("date"));
        transaction.setLocation(result.getString("location"));
        transaction.setProduct_name(result.getString("product_name"));
        transaction.setCategory(new Category(result.getInt("category")));
        transaction.setSingle_price(result.getDouble("single_price"));
        transaction.setAmount(result.getDouble("amount"));
        transaction.setTotal_price(result.getDouble("total_price"));

        return transaction;
    }

    public Transaction[] getTransactions() throws SQLException {

        PreparedStatement buildState = con.prepareStatement(
                "SELECT date, location, product_name, category, single_price, amount, total_price "
                        + "FROM Transactions;");
        ResultSet result = buildState.executeQuery();

        Transaction[] transactions = new Transaction[0];
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        int i = 0;
        while (result.next()) {
            Transaction transaction = new Transaction();

            transaction.setDate(result.getString("date"));
            transaction.setLocation(result.getString("location"));
            transaction.setProduct_name(result.getString("product_name"));
            transaction.setCategory(new Category(result.getInt("category")));
            transaction.setSingle_price(result.getDouble("single_price"));
            transaction.setAmount(result.getDouble("amount"));
            transaction.setTotal_price(result.getDouble("total_price"));



            transactionList.add(transaction);
            transactions = transactionList.toArray(transactions);

            i++;
        }

        return transactions;
    }

    public Category insertCategory(Category category) throws SQLException {
        PreparedStatement buildState = con.prepareStatement("INSERT INTO Categories "
                + "(category, related_to ) VALUES "
                + "(?, ?);");

        buildState.setString(1, category.getCategory());
        buildState.setString(2, category.getRelated_to());

        buildState.execute();

        return category;

    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:expense-tracker-database.db");
        initialise();
    }

    private void buildTables() throws SQLException {
        System.out.println("Building the necessary tables");
        Statement buildState = con.createStatement();
        buildState.execute("CREATE TABLE Users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "salt TEXT NOT NULL, "
                + "CONSTRAINT unique_username UNIQUE (username)"
                + ");");
        buildState.execute("CREATE TABLE Transactions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "date TEXT default datetime('now'), "
                + "location TEXT, "
                + "product_name TEXT NOT NULL, "
                + "category INTEGER NOT NULL, "
                + "single_price REAL NOT NULL, "
                + "amount REAL NOT NULL, "
                + "total_price REAL NOT NULL"
                + ");");
        buildState.execute("CREATE TABLE Categories ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "category TEXT NOT NULL, "
                + "related_to TEXT, "
                + ");");
    }
}

