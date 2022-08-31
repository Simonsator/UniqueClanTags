package de.simonsator.clans.uniqueclantags;

import de.simonsator.clans.uniqueclantags.listeners.UTCBungeeListener;
import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.pool.PoolData;
import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.api.chat.TextComponent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UCTMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			PoolData poolData = new PoolData(Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.MinPoolSize"),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.MaxPoolSize"),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.InitialPoolSize"), Main.getInstance().getGeneralConfig().getInt("MySQL.Pool.IdleConnectionTestPeriod"), Main.getInstance().getGeneralConfig().getBoolean("MySQL.Pool.TestConnectionOnCheckin"));
			MySQLData mySQLData = new MySQLData(Main.getInstance().getGeneralConfig().getString("MySQL.Host"),
					Main.getInstance().getGeneralConfig().getString("MySQL.Username"), Main.getInstance().getGeneralConfig().get("MySQL.Password").toString(),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Port"), Main.getInstance().getGeneralConfig().getString("MySQL.Database"),
					Main.getInstance().getGeneralConfig().getString("MySQL.TablePrefix"), Main.getInstance().getGeneralConfig().getBoolean("MySQL.UseSSL"));
			UCTMySQLConnection con = new UCTMySQLConnection(mySQLData, poolData);
			TextComponent message = new TextComponent(TextComponent.fromLegacyText(ClanCommands.getInstance().getPrefix() + (new UCTConfiguration(new File(getConfigFolder(), "config.yml"), this)).getString("Messages.ClanTagDoesAlreadyExist")));
			Object listener;
			switch (getAdapter().getServerSoftware()) {
				case BUNGEECORD:
					listener = new UTCBungeeListener(con, message);
					break;
				case SPIGOT:
					throw new IllegalArgumentException(getAdapter().getServerSoftware() + " is not supported!");
				default:
					throw new IllegalArgumentException("Unknown server software: " + getAdapter().getServerSoftware());
			}
			getAdapter().registerListener(listener, this);
			registerAsExtension();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
