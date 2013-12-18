package com.FaceEntity;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.entity.Player;

public class ChannelListener implements PluginMessageListener {
	private static final Logger log_ = Logger.getLogger("FaceEntity");
	
	public static void info(String message) {
		log_.info("[FaceEntity] " + message);
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		info("Received message on channel: " + channel);
	}
}
