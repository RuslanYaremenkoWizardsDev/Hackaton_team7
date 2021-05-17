package utils.database;

import java.sql.PreparedStatement;

public class CRUD {
    public boolean deleteUser(String login) {
        try {
            PreparedStatement statement = null;
            statement = ConnectionProvider.getConnectionToDb().prepareStatement("DELETE FROM d75f64jp5hgdqd.public.user WHERE login='" + login + "';");
            return statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
