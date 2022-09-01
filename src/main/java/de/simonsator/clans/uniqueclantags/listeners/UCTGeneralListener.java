package de.simonsator.clans.uniqueclantags.listeners;

import de.simonsator.clans.uniqueclantags.UCTMySQLConnection;
import de.simonsator.partyandfriends.clan.api.events.ClanTagSetEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UCTGeneralListener {
	private final UCTMySQLConnection CONNECTION;
	private final TextComponent MESSAGE;

	protected UCTGeneralListener(UCTMySQLConnection connection, TextComponent pMessage) {
		CONNECTION = connection;
		MESSAGE = pMessage;
	}

	protected void onClanTagSet(ClanTagSetEvent pEvent) {
		if (CONNECTION.doesClanTagExist(pEvent.getNewClanTag())) {
			pEvent.getPlayer().sendMessage(MESSAGE);
			pEvent.setCancelled(true);
		}
	}
}
