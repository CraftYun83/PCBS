package com.fourmc.computers.functions;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CaseConfigurationGUI {
	
	public CaseConfigurationGUI(Player p) {
		
		Inventory gui = Bukkit.createInventory(null, 45, ChatColor.GREEN+"Configure Case");
		
		ArrayList<Integer> listOfEmpty = new ArrayList<Integer>();
		for (int i = 0; i < 45; i++) {listOfEmpty.add(i);}
		for (int i : listOfEmpty) {gui.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));}
		ItemStack confirmitem = new ItemStack(Material.LIME_CONCRETE);
		ItemMeta confirmitemmeta = confirmitem.getItemMeta();
		confirmitemmeta.setDisplayName(ChatColor.GREEN+"Confirm");
		confirmitem.setItemMeta(confirmitemmeta);
		gui.setItem(22, confirmitem);
		ItemStack pccaseslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemMeta pccaseslotmeta = pccaseslot.getItemMeta();
		pccaseslotmeta.setDisplayName(ChatColor.GREEN+"Click on your PC case to add it to the slot!");
		pccaseslot.setItemMeta(pccaseslotmeta);
		gui.setItem(19, pccaseslot);
		ItemStack pcpartslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemMeta pcpartslotmeta = pcpartslot.getItemMeta();
		pcpartslotmeta.setDisplayName(ChatColor.GREEN+"Click on your PSU or MOBO to add it to the slot!");
		pcpartslot.setItemMeta(pcpartslotmeta);
		gui.setItem(25, pcpartslot);
		p.openInventory(gui);
				
	}

}
