package ServerManager.data_access_layer.utilities;

import Interfaces.IPlayer;
import ServerManager.data_access_layer.components.MsSQLConnection;
import domain.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL extends MsSQLConnection implements ILogin {
	@Override
	public IPlayer login(String username, String password) throws SQLException {
		String query = "SELECT Username, Password FROM gso_login WHERE Username = ? AND Password = ?";
		Player player = null;
		try (Connection conn = connect()) {
			try(PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, username);
				ps.setString(2, password);

				try(ResultSet result = ps.executeQuery()) {
					while (result.next()) {
						player = new Player(username);
					}
				}
			}
		}
		return player;
	}

	@Override
	public boolean register(String username, String password) throws SQLException {
		String query = "Insert INTO gso_login (Username, Password) VALUES (?,?)";

		int rowCount = -1;
		try (Connection conn = connect()) {
			try(PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, username);
				ps.setString(2, password);
				rowCount = ps.executeUpdate(); // rowcount or 0
			}
		}
		return rowCount > 0;
	}
}
