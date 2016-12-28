package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.clan.api.events.ClanTagSetEvent;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTMain extends Plugin implements Listener {
	private TextComponent message;
	private UCTMySQLConnection con;

	@Override
	public void onEnable() {
		try {
			con = new UCTMySQLConnection(new MySQLData(Main.getInstance().getConfig().getString("MySQL.Host"),
					Main.getInstance().getConfig().getString("MySQL.Username"), Main.getInstance().getConfig().getString("MySQL.Password"),
					Main.getInstance().getConfig().getInt("MySQL.Port"), Main.getInstance().getConfig().getString("MySQL.Database"),
					Main.getInstance().getConfig().getString("MySQL.TablePrefix")));
			message = new TextComponent(ClanCommands.getInstance().getPrefix() + (new UCTConfiguration(new File(getDataFolder(), "config.yml"))).getCreatedConfiguration().getString("Messages.ClanTagDoesAlreadyExist"));
			ProxyServer.getInstance().getPluginManager().registerListener(this, this);
		} catch (IOException e) {
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
