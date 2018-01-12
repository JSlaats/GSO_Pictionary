package ServerManager.data_access_layer.utilities;


import Interfaces.IPlayer;
import java.sql.SQLException;

public class RepoUser implements ILogin {

    private UserSQL userSql = new UserSQL();

    public IPlayer login(String username, String password) throws SQLException {
        return userSql.login(username, password);
    }

    @Override
    public boolean register(String username, String password) throws SQLException {
        return userSql.register(username,password);
    }

}
