package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.pool.PoolData;
import de.simonsator.partyandfriends.communication.sql.pool.PoolSQLCommunication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTMySQLConnection extends PoolSQLCommunication {
	private final String TABLE_PREFIX;

	public UCTMySQLConnection(MySQLData pMySQLData, PoolData pPooldata) throws SQLException {
		super(pMySQLData, pPooldata);
		this.TABLE_PREFIX = pMySQLData.TABLE_PREFIX;
	}

	public boolean doesClanTagExist(String pClanTag) {
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		try {
			prepStmt = con
					.prepareStatement("select id from `" + TABLE_PREFIX + "clan` WHERE clan_tag=? LIMIT 1");
			prepStmt.setString(1, pClanTag);
			rs = prepStmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, prepStmt);
		}
		return false;
	}

}
