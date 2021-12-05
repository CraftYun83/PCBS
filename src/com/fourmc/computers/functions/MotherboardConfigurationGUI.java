package com.fourmc.computers.functions;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MotherboardConfigurationGUI {
	
	public MotherboardConfigurationGUI(Player p) {
		
		Inventory gui = Bukkit.createInventory(null, 45, ChatColor.GREEN+"Configure Motherboard");
		
		ArrayList<Integer> listOfEmpty = new ArrayList<Integer>();
		for (int i = 0; i < 45; i++) {listOfEmpty.add(i);}
		for (int i : listOfEmpty) {gui.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));}
		ItemStack confirmitem = new ItemStack(Material.LIME_CONCRETE);
		ItemMeta confirmitemmeta = confirmitem.getItemMeta();
		confirmitemmeta.setDisplayName(ChatColor.GREEN+"Confirm");
		confirmitem.setItemMeta(confirmitemmeta);
		gui.setItem(22, confirmitem);
		ItemStack motherboardslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemMeta motherboardslotmeta = motherboardslot.getItemMeta();
		motherboardslotmeta.setDisplayName(ChatColor.GREEN+"Click on your motherboard to add it to the slot!");
		motherboardslot.setItemMeta(motherboardslotmeta);
		gui.setItem(19, motherboardslot);
		ItemStack pcpartslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemMeta pcpartslotmeta = pcpartslot.getItemMeta();
		pcpartslotmeta.setDisplayName(ChatColor.GREEN+"Click on your PC Part to add it to the slot!");
		pcpartslot.setItemMeta(pcpartslotmeta);
		gui.setItem(25, pcpartslot);
		p.openInventory(gui);
				
	}

}
