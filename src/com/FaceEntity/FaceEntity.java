package com.FaceEntity;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.PacketContainer;

public class FaceEntity extends JavaPlugin implements Listener {
	private ChannelListener channelListener;
	private final String CHANNEL_NAME = "faceentity";
	
	private ProtocolManager protocolManager;
	
	@Override
	public void onLoad() {
		protocolManager = ProtocolLibrary.getProtocolManager();
	}
	
	@Override
	public void onEnable() {
		this.channelListener = new ChannelListener(this);
		
		getServer().getPluginManager().registerEvents(this, this);
		
		getLogger().info("Registering packet channels.");
		getServer().getMessenger().registerIncomingPluginChannel(this, this.CHANNEL_NAME, this.channelListener);
	}
	
	@Override
	public void onDisable() {
		getServer().getMessenger().unregisterIncomingPluginChannel(this, this.CHANNEL_NAME, this.channelListener);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player playerJoined = e.getPlayer();
		if (playerJoined != null && playerJoined.isOnline()) { // Do I need these checks? I don't know...
			this.sendCustomQueryPacket(playerJoined);
		}
	}
	
	private void sendCustomQueryPacket(Player p) {
		PacketContainer queryPacket = this.protocolManager.createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
		queryPacket.getSpecificModifier(String.class).write(0, this.CHANNEL_NAME);
		
		try {
			this.protocolManager.sendServerPacket(p, queryPacket);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
