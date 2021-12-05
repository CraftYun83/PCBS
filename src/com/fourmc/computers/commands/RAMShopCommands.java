package com.fourmc.computers.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fourmc.computers.Main;
import com.fourmc.computers.functions.ShopGeneration;

public class RAMShopCommands implements CommandExecutor {
	
	private Main plugin;
	
	public RAMShopCommands(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginCommand("buyram").setExecutor(this);;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(ChatColor.RED+"Im sorry, This command is only available for players!");
			
		} else {
			
			Player p = (Player) sender;
			
			ShopGeneration sg = new ShopGeneration();
			sg.RamShopGeneration(p, plugin);
			
		}
		
		return false;
	}

}
