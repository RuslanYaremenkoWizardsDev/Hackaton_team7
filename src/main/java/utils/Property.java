package utils;

import java.io.InputStream;
import java.util.Properties;

public class Property {
    private Properties property;
    private InputStream inputStream;

    public Property() {
        property = new Properties();
        inputStream = Property.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            property.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getChromeDriver() {
        return property.getProperty("driver.chrome");
    }

    public String getMainPageUrl() {
        return property.getProperty("app.mainPageUrl");
    }

    public String getAuthorizationPageUrl() {
        return property.getProperty("app.authorizationPageUrl");
    }

    public String getRegistrationPageUrl() {
        return property.getProperty("app.registrationPageUrl");
    }

    public String getAuthorizationRequestUrl() {
        return property.getProperty("api.authorization");
    }

    public String getRegistrationRequestUrl() {
        return property.getProperty("api.registration");
    }

    public String getTournamentsListRequestUrl() {
        return property.getProperty("api.getTournamentsList");
    }

    public String getCreateTournamentRequestUrl() {
        return property.getProperty("api.createTournament");
    }

    public String getJoinTournamentRequestUrl() {
        return property.getProperty("api.joinTournament");
    }

    public String getDbUrl() {
        return property.getProperty("db.url");
    }

    public String getDbLogin() {
        return property.getProperty("db.login");
    }

    public String getDbPassword() {
        return property.getProperty("db.password");
    }
}
