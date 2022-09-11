package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTConfiguration extends ConfigurationCreator {

	protected UCTConfiguration(File file, PAFExtension pPlugin) throws IOException {
		super(file, pPlugin, true);
		readFile();
		loadDefaultValues();
		saveFile();
		process();
	}

	private void loadDefaultValues() {
		set("Messages.ClanTagDoesAlreadyExist", "&7There is already a clan with that clan tag.");
	}
}
