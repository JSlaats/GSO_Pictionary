package ServerManager.data_access_layer.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSQLConnection
{

    protected static Connection connect() throws SQLException{
        return DriverManager.getConnection(
            "jdbc:mysql://sql11.freemysqlhosting.net/sql11215442",//String URL
            "sql11215442",//String Username
            "GsSTwi53ha");//String Password
    }

}
