package com.fourmc.computers.functions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.fourmc.computers.Main;

public class PCCraftingRecipe {
	
	@SuppressWarnings("deprecation")
	public PCCraftingRecipe(Main plugin) {
		
		ItemStack pccase = new ItemStack(Material.DISPENSER, 1);
		ItemMeta pccasemeta = pccase.getItemMeta();
		pccasemeta.setDisplayName(ChatColor.AQUA+"Empty PC Case");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN+"Use the Case Configurer to add parts to this PC Case!");
		pccasemeta.setLore(lore);
		pccase.setItemMeta(pccasemeta);
		
		ShapedRecipe PCCase = new ShapedRecipe(pccase);
		
		PCCase.shape("***","*%*","***");
		PCCase.setIngredient('*', Material.IRON_BLOCK);
		PCCase.setIngredient('%', Material.AIR);
		
		plugin.getServer().addRecipe(PCCase);
		
	}

}
