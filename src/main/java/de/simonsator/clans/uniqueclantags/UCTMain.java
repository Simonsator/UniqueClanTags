package de.simonsator.clans.uniqueclantags;

import de.simonsator.clans.uniqueclantags.listeners.UCTBungeeListener;
import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.velocity.communication.sql.MySQLData;
import de.simonsator.partyandfriends.velocity.communication.sql.pool.PoolData;
import de.simonsator.partyandfriends.velocity.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

public class UCTMain extends PAFExtension {

	public UCTMain(Path folder) {
		super(folder);
	}

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
			TextComponent message = Component.text(ClanCommands.getInstance().getPrefix() + (new UCTConfiguration(new File(getConfigFolder(), "config.yml"), this)).getString("Messages.ClanTagDoesAlreadyExist"));
			Object listener;
			switch (getAdapter().getServerSoftware()) {
				case BUNGEECORD:
					listener = new UCTBungeeListener(con, message);
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

	@Override
	public String getName() {
		return "UniqueClanTags";
	}

}
