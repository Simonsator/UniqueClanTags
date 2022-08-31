package de.simonsator.clans.uniqueclantags.listeners;

import de.simonsator.clans.uniqueclantags.UCTMySQLConnection;
import de.simonsator.partyandfriends.clan.api.events.ClanTagSetEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class UTCBungeeListener extends UTCGeneralListener implements Listener {
	public UTCBungeeListener(UCTMySQLConnection connection, TextComponent pMessage) {
		super(connection, pMessage);
	}

	@EventHandler
	public void onClanTagSet(ClanTagSetEvent pEvent) {
		super.onClanTagSet(pEvent);
	}
}

