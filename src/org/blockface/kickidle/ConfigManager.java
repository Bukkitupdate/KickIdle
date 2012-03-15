package org.blockface.kickidle;

public class ConfigManager
{
	KickIdle plugin;

	public ConfigManager(KickIdle plugin)
	{
		this.plugin = plugin;
		getTime();
		getMessage();
		//config.save();
	}

	public int getTime(){
		return plugin.getConfig().getInt("time", 30);
	}

	public String getMessage(){
		return plugin.getConfig().getString("message", "You are idle, come back soon!");
	}
}