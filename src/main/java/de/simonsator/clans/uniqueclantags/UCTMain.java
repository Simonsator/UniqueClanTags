package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.api.events.ClanTagSetEvent;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;

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
			con = new UCTMySQLConnection(new MySQLData(Main.getInstance().getGeneralConfig().getString("MySQL.Host"),
					Main.getInstance().getGeneralConfig().getString("MySQL.Username"), Main.getInstance().getGeneralConfig().getString("MySQL.Password"),
					Main.getInstance().getGeneralConfig().getInt("MySQL.Port"), Main.getInstance().getGeneralConfig().getString("MySQL.Database"),
					Main.getInstance().getGeneralConfig().getString("MySQL.TablePrefix")));
			message = ClanCommands.getInstance().getPrefix() + (new UCTConfiguration(new File(getConfigFolder(), "config.yml"), this)).getString("Messages.ClanTagDoesAlreadyExist");
			getAdapter().registerListener(this, this);
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
