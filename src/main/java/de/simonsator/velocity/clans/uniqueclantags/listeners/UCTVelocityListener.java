package de.simonsator.velocity.clans.uniqueclantags.listeners;

import com.velocitypowered.api.event.Subscribe;
import de.simonsator.velocity.clans.uniqueclantags.UCTMySQLConnection;
import de.simonsator.partyandfriends.velocity.clan.api.events.ClanTagSetEvent;
import net.kyori.adventure.text.TextComponent;


public class UCTVelocityListener extends UCTGeneralListener {
	public UCTVelocityListener(UCTMySQLConnection connection, TextComponent pMessage) {
		super(connection, pMessage);
	}

	@Subscribe
	public void onClanTagSet(ClanTagSetEvent pEvent) {
		super.onClanTagSet(pEvent);
	}
}

