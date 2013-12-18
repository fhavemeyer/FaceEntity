package com.FaceEntity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;


public class FaceEntity extends JavaPlugin {
	private final ChannelListener chanListener = new ChannelListener();
	private final String CHANNEL_NAME = "faceentity";
	
	@Override
	public void onEnable() {
		getLogger().info("Registering packet channels.");
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, this.CHANNEL_NAME);
		Bukkit.getMessenger().registerIncomingPluginChannel(this, this.CHANNEL_NAME, this.chanListener);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onPlayerJoinEvent(final Player playerJoined, final String joinMessage) {
		if (playerJoined != null) {
			String data = "thisdoesntmatter";
			playerJoined.sendPluginMessage(this, this.CHANNEL_NAME, data.getBytes());
			getLogger().info("Sent message on channel " + this.CHANNEL_NAME);
		}
	}
}
