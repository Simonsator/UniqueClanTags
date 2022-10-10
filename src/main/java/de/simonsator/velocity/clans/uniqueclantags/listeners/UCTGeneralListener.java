package de.simonsator.velocity.clans.uniqueclantags.listeners;

import de.simonsator.velocity.clans.uniqueclantags.UCTMySQLConnection;
import de.simonsator.partyandfriends.velocity.clan.api.events.ClanTagSetEvent;
import net.kyori.adventure.text.TextComponent;

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
