package com.FaceEntity;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.entity.Player;

public class ChannelListener implements PluginMessageListener {
	private FaceEntity parent;
	
	public ChannelListener(FaceEntity f) {
		super();
		this.parent = f;
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		this.parent.getLogger().info("Received message on channel: " + channel);
		if (message != null && message.length > 0) {
			try {
				this.parent.getLogger().info("Loaded mods: " + (new String(message, "UTF-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
