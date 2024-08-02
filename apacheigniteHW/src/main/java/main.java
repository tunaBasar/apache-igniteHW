import org.apache.ignite.client.ClientConnectionException;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

    private static final Logger logger = LoggerFactory.getLogger(main.class);

    public static void main(String[] args) {
        IgniteConfiguration igniteCfg = new IgniteConfiguration();
        igniteCfg.setWorkDirectory("C:\\Users\\ymanb\\OneDrive\\Desktop\\İnternshipHW\\apacheigniteHW\\apacheigniteHW");
        String jdbcURL = "jdbc:ignite:thin://127.0.0.1";

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            Connection connection = DriverManager.getConnection(jdbcURL);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SUBSCRIBER");

            while (resultSet.next()){
                int subscriberId = resultSet.getInt("SUBSC_ID");
                String subscriberName = resultSet.getString("SUBSC_NAME");
                String subscriberLastname = resultSet.getString("SUBSC_SURNAME");

                logger.info("{} {} {}", subscriberId, subscriberName, subscriberLastname);
            }

        }catch(ClientConnectionException | ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}