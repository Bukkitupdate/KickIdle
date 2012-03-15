package org.blockface.kickidle;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Chatty
{
	private static String prefix;
	private static Plugin plugin;
	private static Logger logger;

	public static void Initialize(Plugin p)
	{
		logger = Logger.getLogger("minecraft");
		plugin = p;
		prefix = ChatColor.AQUA + "[" + plugin.getDescription().getName() + "] " + ChatColor.WHITE;
	}

	public static void LogInfo(String message)
	{
		logger.info(message);
	}

	public static void SendError(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + ChatColor.RED + message);
	}

	public static void SendSuccess(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + ChatColor.GREEN + message);
	}
}