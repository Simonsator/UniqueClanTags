package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.api.events.ClanTagSetEvent;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.pool.PoolData;
import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTMain extends PAFExtension implements Listener {
	private String message;
	private UCTMySQLConnection con;

	@Override
	public void onEnable() {
		try {
			PoolData poolData = new PoolData(Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.MinPoolSize"),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.MaxPoolSize"),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.InitialPoolSize"), Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.IdleConnectionTestPeriod"), Main.getInstance().getGeneralConfig().getBoolean("MySQL.Pool.TestConnectionOnCheckin"));
			con = new UCTMySQLConnection(new MySQLData(Main.getInstance().getGeneralConfig().get("MySQL.Host").toString(),
					Main.getInstance().getGeneralConfig().get("MySQL.Username").toString(), Main.getInstance().getGeneralConfig().get("MySQL.Password").toString(),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Port"), Main.getInstance().getGeneralConfig().get("MySQL.Database").toString(),
					Main.getInstance().getGeneralConfig().get("MySQL.TablePrefix").toString(), Main.getInstance().getGeneralConfig().getBoolean("MySQL.UseSSL"), Main.getInstance().getGeneralConfig().getBoolean("MySQL.Cache")), poolData);
			message = ClanCommands.getInstance().getPrefix() + (new UCTConfiguration(new File(getConfigFolder(), "config.yml"), this)).getString("Messages.ClanTagDoesAlreadyExist");
			getAdapter().registerListener(this, this);
			registerAsExtension();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onClanTagSet(ClanTagSetEvent pEvent) {
		if (con.doesClanTagExist(pEvent.getNewClanTag())) {
			pEvent.getPlayer().sendMessage(message);
			pEvent.setCancelled(true);
		}
	}
}
