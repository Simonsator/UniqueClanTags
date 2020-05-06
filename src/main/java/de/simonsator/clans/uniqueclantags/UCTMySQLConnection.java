package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.*;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTMySQLConnection extends SQLCommunication {
	private final String TABLE_PREFIX;

	public UCTMySQLConnection(MySQLData pMySQLData) {
		super(pMySQLData);
		this.TABLE_PREFIX = pMySQLData.TABLE_PREFIX;
	}

	public boolean doesClanTagExist(String pClanTag) {
		Connection con = getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement prepStmt = null;
		try {
			prepStmt = con
					.prepareStatement("select id from " + DATABASE + ".`" + TABLE_PREFIX + "clan` WHERE clan_tag=? LIMIT 1");
			prepStmt.setString(1, pClanTag);
			rs = prepStmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, prepStmt);
		}
		return false;
	}

}
