package ServerManager;

import Interfaces.ILogin;
import Interfaces.IPlayer;
import ServerManager.data_access_layer.utilities.RepoUser;

import java.io.Serializable;
import java.sql.SQLException;

public class Login implements ILogin, Serializable {
    private static RepoUser repo = new RepoUser();

    public IPlayer login(String username, String password) throws SQLException {
        return repo.login(username,password);
    }

    public boolean register(String username, String password) throws SQLException {
        return repo.register(username,password);
    }
}
