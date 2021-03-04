package main.java.controller;

import java.sql.SQLException;
import main.java.model.Database;
import main.java.model.Transaction;

public class DataController {

    private static Database db;

    static {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveTransaction(Transaction transaction) {
        try {
            db.insertTransaction(transaction);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
    public static Server[] getAllServers() {
        try {
            return db.getServers();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void SaveProgram(Program program, Server server) {
        try {
            db.insertProgramm(program, server);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void SaveProgramsFromServer(Program[] programs, Server server) {
        for (Program program : programs) {
            try {
                db.insertProgramm(program, server);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static Program[] getProgramsFromServer(Server server) {
        try {
            return db.getProgramsFromServer(server);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static Server[] getServerFromProgram(Program program) {
        try {
            return db.getServersFromProgram(program);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static void SaveNewUser(User user) {
        try {
            db.insertUser(user);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static User getUser(String username) {
        try {
            return db.getUser(username);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static Server updateServer(Server server) {
        try {
            return db.updateServer(server);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

*/
}
