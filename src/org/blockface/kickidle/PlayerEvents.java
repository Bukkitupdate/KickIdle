package org.blockface.kickidle;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener{
	private HashMap<String, Integer> tasks;
	KickIdle plugin;
	
	public PlayerEvents(KickIdle plugin)
	{
		this.plugin = plugin;
		this.tasks = new HashMap<String, Integer>();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (event.getPlayer().hasPermission("kickidle.exempt")) return;
		if (this.tasks.containsKey(event.getPlayer().getName())) return;
		int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(KickIdle.getInstance(), new UpdateTask(event.getPlayer()), 20L * plugin.configmanager.getTime(), 20L * plugin.configmanager.getTime());
		this.tasks.put(event.getPlayer().getName(), Integer.valueOf(id));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if (!this.tasks.containsKey(event.getPlayer().getName())) return;
		Bukkit.getScheduler().cancelTask(((Integer)this.tasks.get(event.getPlayer().getName())).intValue());
		this.tasks.remove(event.getPlayer().getName());
	}
	public class UpdateTask implements Runnable {
		private Player player;
		private Location lastLocation;

		public UpdateTask(Player player) {
			this.player = player;
			updatePlayer();
		}

		public void run()
		{
			if (this.player.getLocation().equals(this.lastLocation))
			{
				kickPlayer();
				return;
			}
			updatePlayer();
		}

		private void kickPlayer()
		{
			this.player.kickPlayer(plugin.configmanager.getMessage());
		}

		private void updatePlayer()
		{
			this.lastLocation = this.player.getLocation();
		}
	}
}