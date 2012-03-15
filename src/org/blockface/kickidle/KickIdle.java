package org.blockface.kickidle;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class KickIdle extends JavaPlugin
{
	public ConfigManager configmanager;
	private static Plugin instance;

	public void onDisable()
	{
	}

	public void onEnable()
	{
		configmanager = new ConfigManager(this);
		instance = this;
		Chatty.Initialize(this);
		PlayerEvents pe = new PlayerEvents(this);
		getServer().getPluginManager().registerEvents(pe, this);
	}

	public static Plugin getInstance()
	{
		return instance;
	}
}