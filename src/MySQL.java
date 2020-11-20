import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    // MySQL Daten - entweder per config oder hartkodiert ermitteln
    public String username = "yourUsername";
    public String password = "yourPassword";
    public String database = "yourDatabase";
    public String host = "yourHost";
    public String port = "yourPort";
    public Connection connection;

    // Connect Methode - Verbinden zur MySQL Datenbank
    public void connect() {
        try {
            // Die Connection wird aufgebaut - URL: jdbc:mysql://host:port/database | username, passwort als Login übergeben
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            System.out.println("successfully connected to the mysql database");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // Disconnect Methode - Verbindung zur MySQL Datenbank unterbrechen
    public void disconnect() {
        if (isConnected()) {
            try {
                // connection schließen
                connection.close();
                System.out.println("successfully disconnected from the mysql database");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    // IsConnected Methode - Prüfe, ob die Verbindung zur MySQL Datenbank besteht
    public boolean isConnected() {
        return connection != null;
    }

    // CreateTables Methode - Erstelle Tabellen, nicht zwingend in dieser Klasse notwendig
    public void createTables() {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS dummy (ARG1 VARCHAR(100), ARG2 VARCHAR(100));");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // ExecuteQuery Methode - Update Werte in der Datenbank
    public void executeQuery(String qry) {
        if (isConnected()) {
            try {
                connection.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // GetResult Methode - Erhalte Werte aus der Datenbank
    public ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // GetConnection Methode - Bekomme die Connection als Variable (z.B. für PreparedStatements)
    public Connection getConnection() {
        return connection;
    }
}
