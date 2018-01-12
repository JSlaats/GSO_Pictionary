package ServerManager.data_access_layer.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSQLConnection
{
    protected static Connection connect() throws SQLException{
        return DriverManager.getConnection(
            "jdbc:mysql://studmysql01.fhict.local:3306/dbi315016",//String URL
            "dbi315016",//String Username
            "hoi123");//String Password
    }
}
