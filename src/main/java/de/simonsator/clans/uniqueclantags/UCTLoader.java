package de.simonsator.clans.uniqueclantags;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;

import java.nio.file.Path;

@Plugin(id = "UniqueClanTags", name = "uniqueclantags", version = "1.0.3-RELEASE",
		url = "https://www.spigotmc.org/resources/unique-clan-tags-extension-for-clans.33904/",
		description = "Makes clan tags unique."
		, authors = {"JT122406", "Simonsator"}, dependencies = {@Dependency(id = "partyandfriends"), @Dependency(id = "clans")})
public class UCTLoader {
	private final Path folder;

	@Inject
	public UCTLoader(@DataDirectory final Path pFolder) {
		folder = pFolder;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		PAFPlugin.loadExtension(new VelocityExtensionLoadingInfo(new UCTMain(folder),
				"uniqueclantags",
				"UniqueClanTags",
				"1.0.3-RELEASE", "JT122406, Simonsator"));
	}

}
