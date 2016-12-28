package de.simonsator.clans.uniqueclantags;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 28.12.2016
 */
public class UCTConfiguration extends ConfigurationCreator {

	protected UCTConfiguration(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Messages.ClanTagDoesAlreadyExist", "&7There is already a clan with that clan tag.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new UCTConfiguration(FILE)).getCreatedConfiguration();
	}
}
