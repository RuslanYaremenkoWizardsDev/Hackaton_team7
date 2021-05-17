package utils.database;

import utils.Property;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    private static Connection connection;

    private ConnectionProvider() {

    }

    public static Connection getConnectionToDb() {
        if(connection == null) {
            Property property = new Property();
            try {
                connection = DriverManager.getConnection(property.getDbUrl(), property.getDbLogin(), property.getDbPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
