package de.simonsator.clans.uniqueclantags.listeners;

import com.velocitypowered.api.event.Subscribe;
import de.simonsator.clans.uniqueclantags.UCTMySQLConnection;
import de.simonsator.partyandfriends.velocity.clan.api.events.ClanTagSetEvent;
import net.kyori.adventure.text.TextComponent;


public class UCTBungeeListener extends UCTGeneralListener {
	public UCTBungeeListener(UCTMySQLConnection connection, TextComponent pMessage) {
		super(connection, pMessage);
	}

	@Subscribe
	public void onClanTagSet(ClanTagSetEvent pEvent) {
		super.onClanTagSet(pEvent);
	}
}

