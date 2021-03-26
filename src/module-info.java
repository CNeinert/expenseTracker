module expenseTracker {
    requires ormlite.core;
    requires ormlite.jdbc;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    exports main.java.run;
}