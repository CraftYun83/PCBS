package com.fourmc.computers.functions;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fourmc.computers.Main;

public class ShopGeneration {
	
	public void GPUShopGeneration(Player p, FileConfiguration config){
		
		Inventory gui = Bukkit.createInventory(null, 36,ChatColor.RED + "GPU Shop");
		
		int i = 0;
		
		for(String s : config.getKeys(false)) {
			
			if (s.equalsIgnoreCase("Brands")) {
				
				;
				
			} else {
				
				String price = Integer.toString(config.getInt(s+".price"));
				String power = Integer.toString(config.getInt(s+".power"));
				
				if (s.contains("980")) {
					
					ItemStack item = new ItemStack (Material.BRICK);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(ChatColor.RED+s);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.RED+"Model name: "+s);
					lore.add(ChatColor.RED+"Power: "+power);
					lore.add(ChatColor.GREEN+"Price: $"+price);
					lore.add(ChatColor.GREEN+"Type: GPU");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					gui.setItem(i, item);
					
				} else if (s.contains("10")) {
					
					ItemStack item = new ItemStack (Material.IRON_INGOT);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GRAY+s);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.GRAY+"Model name: "+s);
					lore.add(ChatColor.RED+"Power: "+power);
					lore.add(ChatColor.GREEN+"Price: $"+price);
					lore.add(ChatColor.GREEN+"Type: GPU");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					gui.setItem(i, item);
					
				} else if (s.contains("16")) {
					
					ItemStack item = new ItemStack (Material.LAPIS_LAZULI);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(ChatColor.BLUE+s);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.BLUE+"Model name: "+s);
					lore.add(ChatColor.RED+"Power: "+power);
					lore.add(ChatColor.GREEN+"Price: $"+price);
					lore.add(ChatColor.GREEN+"Type: GPU");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					gui.setItem(i, item);
					
				} else if (s.contains("20")) {
					
					ItemStack item = new ItemStack (Material.GOLD_INGOT);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GOLD+s);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.GOLD+"Model name: "+s);
					lore.add(ChatColor.RED+"Power: "+power);
					lore.add(ChatColor.GREEN+"Price: $"+price);
					lore.add(ChatColor.GREEN+"Type: GPU");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					gui.setItem(i, item);
					
				} else if (s.contains("30")) {
					
					ItemStack item = new ItemStack (Material.NETHERITE_INGOT);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(ChatColor.DARK_GRAY+s);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.DARK_GRAY+"Model name: "+s);
					lore.add(ChatColor.RED+"Power: "+power);
					lore.add(ChatColor.GREEN+"Price: $"+price);
					lore.add(ChatColor.GREEN+"Type: GPU");
					itemMeta.setLore(lore);
					item.setItemMeta(itemMeta);
					gui.setItem(i, item);
					
				}
				
				i++;
				
			}
			
		}
		
		p.openInventory(gui);
		
	}
	
	public void CPUShopGeneration(Player p, FileConfiguration config){
		
		Inventory gui = Bukkit.createInventory(null, 36, ChatColor.BLUE + "CPU Shop");
		
		int i = 0;
		
		for(String s : config.getKeys(false)) {
			
			String price = Integer.toString(config.getInt(s+".price"));
			String power = Integer.toString(config.getInt(s+".power"));
			
			ItemStack item = new ItemStack (Material.REDSTONE);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(ChatColor.RED+s);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.RED+"Model name: "+s);
			lore.add(ChatColor.RED+"Power: "+power);
			lore.add(ChatColor.GREEN+"Price: $"+price);
			lore.add(ChatColor.GREEN+"Type: CPU");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			gui.setItem(i, item);	
				
			i++;
			
		}
		
		p.openInventory(gui);
		
	}
	
	public void PCShopGeneration(Player p, Main plugin) {
		
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.GREEN + "PC Shop");
		
		int gpushopslot = plugin.getPCShop().getInt("GPUShop.Slot");
		Material gpushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("GPUShop.Item"));
		
		ItemStack gpuitem = new ItemStack(gpushopmaterial);
		ItemMeta gpuitemMeta = gpuitem.getItemMeta();
		gpuitemMeta.setDisplayName(ChatColor.DARK_GRAY+"GPU Shop");
		ArrayList<String> gpulore = new ArrayList<String>();
		gpulore.add(ChatColor.GREEN+"Access the GPU shop!");
		gpuitemMeta.setLore(gpulore);
		gpuitem.setItemMeta(gpuitemMeta);
		gui.setItem(gpushopslot, gpuitem);
		
		int ramshopslot = plugin.getPCShop().getInt("RAMShop.Slot");
		Material ramshopmaterial = Material.matchMaterial(plugin.getPCShop().getString("RAMShop.Item"));
		
		ItemStack ramitem = new ItemStack(ramshopmaterial);
		ItemMeta ramitemMeta = ramitem.getItemMeta();
		ramitemMeta.setDisplayName(ChatColor.YELLOW+"RAM Shop");
		ArrayList<String> ramlore = new ArrayList<String>();
		ramlore.add(ChatColor.GREEN+"Access the ram shop!");
		ramitemMeta.setLore(ramlore);
		ramitem.setItemMeta(ramitemMeta);
		gui.setItem(ramshopslot, ramitem);
		
		int ssdshopslot = plugin.getPCShop().getInt("SSDShop.Slot");
		Material ssdshopmaterial = Material.matchMaterial(plugin.getPCShop().getString("SSDShop.Item"));
		
		ItemStack ssditem = new ItemStack(ssdshopmaterial);
		ItemMeta ssditemMeta = ssditem.getItemMeta();
		ssditemMeta.setDisplayName(ChatColor.YELLOW+"SSD Shop");
		ArrayList<String> ssdlore = new ArrayList<String>();
		ssdlore.add(ChatColor.GREEN+"Access the SSD shop!");
		ssditemMeta.setLore(ssdlore);
		ssditem.setItemMeta(ssditemMeta);
		gui.setItem(ssdshopslot, ssditem);
		
		int psushopslot = plugin.getPCShop().getInt("PSUShop.Slot");
		Material psushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("PSUShop.Item"));
		
		ItemStack psuitem = new ItemStack(psushopmaterial);
		ItemMeta psuitemMeta = psuitem.getItemMeta();
		psuitemMeta.setDisplayName(ChatColor.YELLOW+"PSU Shop");
		ArrayList<String> psulore = new ArrayList<String>();
		psulore.add(ChatColor.GREEN+"Access the PSU shop!");
		psuitemMeta.setLore(psulore);
		psuitem.setItemMeta(psuitemMeta);
		gui.setItem(psushopslot, psuitem);
		
		int cpushopslot = plugin.getPCShop().getInt("CPUShop.Slot");
		Material cpushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("CPUShop.Item"));
		
		ItemStack cpuitem = new ItemStack(cpushopmaterial);
		ItemMeta cpuitemMeta = cpuitem.getItemMeta();
		cpuitemMeta.setDisplayName(ChatColor.YELLOW+"CPU Shop");
		ArrayList<String> cpulore = new ArrayList<String>();
		cpulore.add(ChatColor.GREEN+"Access the CPU shop!");
		cpuitemMeta.setLore(cpulore);
		cpuitem.setItemMeta(cpuitemMeta);
		gui.setItem(cpushopslot, cpuitem);
		
		int pcarenaslot = plugin.getPCShop().getInt("PCArena.Slot");
		Material pcarenamaterial = Material.matchMaterial(plugin.getPCShop().getString("PCArena.Item"));
		
		ItemStack pcarena = new ItemStack(pcarenamaterial);
		ItemMeta pcarenaMeta = pcarena.getItemMeta();
		pcarenaMeta.setDisplayName(ChatColor.DARK_RED+"PVP"+ChatColor.DARK_PURPLE+" ARENA");
		ArrayList<String> pcarenalore = new ArrayList<String>();
		pcarenalore.add(ChatColor.ITALIC+""+ChatColor.YELLOW+"COMING SOON!");
		pcarenaMeta.setLore(pcarenalore);
		pcarena.setItemMeta(pcarenaMeta);
		gui.setItem(pcarenaslot, pcarena);
		
		int moboconfigslot = plugin.getPCShop().getInt("MOBOConfig.Slot");
		Material moboconfigmaterial = Material.matchMaterial(plugin.getPCShop().getString("MOBOConfig.Item"));
		
		ItemStack moboconfig = new ItemStack(moboconfigmaterial);
		ItemMeta moboconfigMeta = moboconfig.getItemMeta();
		moboconfigMeta.setDisplayName(ChatColor.YELLOW+"Configure Your Motherboard");
		moboconfig.setItemMeta(moboconfigMeta);
		gui.setItem(moboconfigslot, moboconfig);
		
		int caseconfigslot = plugin.getPCShop().getInt("CASEConfig.Slot");
		Material caseconfigmaterial = Material.matchMaterial(plugin.getPCShop().getString("CASEConfig.Item"));
		
		ItemStack caseconfig = new ItemStack(caseconfigmaterial);
		ItemMeta caseconfigMeta = caseconfig.getItemMeta();
		caseconfigMeta.setDisplayName(ChatColor.YELLOW+"Configure Your Case");
		caseconfig.setItemMeta(caseconfigMeta);
		gui.setItem(caseconfigslot, caseconfig);
		
		int moboslot = plugin.getPCShop().getInt("Motherboard.Slot");
		int moboprice = plugin.getPCShop().getInt("Motherboard.Price");
		Material mobomaterial = Material.matchMaterial(plugin.getPCShop().getString("Motherboard.Item"));
		
		ItemStack motherboard = new ItemStack(mobomaterial);
		ItemMeta motherboardMeta = motherboard.getItemMeta();
		motherboardMeta.setDisplayName(ChatColor.GREEN+"Generic Motherboard");
		ArrayList<String> motherboardlore = new ArrayList<String>();
		motherboardlore.add(ChatColor.GREEN+"A generic motherboard");
		motherboardlore.add(ChatColor.GREEN+"It's got everything you need");
		motherboardlore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Power: 200");
		motherboardlore.add(ChatColor.GREEN+"Price $"+moboprice);
		motherboardlore.add(ChatColor.GREEN+"Type: MOBO");
		motherboardMeta.setLore(motherboardlore);
		motherboard.setItemMeta(motherboardMeta);
		gui.setItem(moboslot, motherboard);
		
		p.openInventory(gui);
		
	}
	
	public void RamShopGeneration(Player p, Main plugin) {
		
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "RAM Shop");
		
		int ram1slot = plugin.getRAMShop().getInt("8GB.Slot");
		int ram1price = plugin.getRAMShop().getInt("8GB.Price");
		int ram1power = plugin.getRAMShop().getInt("8GB.Power");
		Material ram1material = Material.matchMaterial(plugin.getRAMShop().getString("8GB.Item"));
		
		ItemStack ram1 = new ItemStack(ram1material);
		ItemMeta ram1meta = ram1.getItemMeta();
		ram1meta.setDisplayName(ChatColor.GREEN+"8GB Ram Stick");
		ArrayList<String> ram1lore = new ArrayList<String>();
		ram1lore.add(ChatColor.GREEN+"(1 x 8GB) Ram Sticks");
		ram1lore.add(ChatColor.GREEN+"Power: "+ram1power);
		ram1lore.add(ChatColor.GREEN+"Price $"+ram1price);
		ram1lore.add(ChatColor.GREEN+"Type: RAM");
		ram1meta.setLore(ram1lore);
		ram1.setItemMeta(ram1meta);
		gui.setItem(ram1slot, ram1);
		
		int ram2slot = plugin.getRAMShop().getInt("16GB.Slot");
		int ram2price = plugin.getRAMShop().getInt("16GB.Price");
		int ram2power = plugin.getRAMShop().getInt("16GB.Power");
		Material ram2material = Material.matchMaterial(plugin.getRAMShop().getString("16GB.Item"));
		
		ItemStack ram2 = new ItemStack(ram2material);
		ItemMeta ram2meta = ram2.getItemMeta();
		ram2meta.setDisplayName(ChatColor.GREEN+"16GB Ram Stick");
		ArrayList<String> ram2lore = new ArrayList<String>();
		ram2lore.add(ChatColor.GREEN+"(1 x 16GB) Ram Sticks");
		ram2lore.add(ChatColor.GREEN+"Power: "+ram2power);
		ram2lore.add(ChatColor.GREEN+"Price $"+ram2price);
		ram2lore.add(ChatColor.GREEN+"Type: RAM");
		ram2meta.setLore(ram2lore);
		ram2.setItemMeta(ram2meta);
		gui.setItem(ram2slot, ram2);
		
		p.openInventory(gui);
		
	}
	
	public void SSDShopGeneration(Player p, Main plugin) {
		
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "SSD Shop");
		
		int ssd1slot = plugin.getSSDShop().getInt("250GB.Slot");
		int ssd1price = plugin.getSSDShop().getInt("250GB.Price");
		int ssd1power = plugin.getSSDShop().getInt("250GB.Power");
		Material ssd1material = Material.getMaterial(plugin.getSSDShop().getString("250GB.Item"));
		
		ItemStack ssd1 = new ItemStack(ssd1material);
		ItemMeta ssd1meta = ssd1.getItemMeta();
		ssd1meta.setDisplayName(ChatColor.GREEN+"250GB NVME SSD");
		ArrayList<String> ssd1lore = new ArrayList<String>();
		ssd1lore.add(ChatColor.GREEN+"250GB NVME SSD");
		ssd1lore.add(ChatColor.GREEN+"Power: "+ssd1power);
		ssd1lore.add(ChatColor.GREEN+"Price $"+ssd1price);
		ssd1lore.add(ChatColor.GREEN+"Type: SSD");
		ssd1meta.setLore(ssd1lore);
		ssd1.setItemMeta(ssd1meta);
		gui.setItem(ssd1slot, ssd1);
		
		int ssd2slot = plugin.getSSDShop().getInt("500GB.Slot");
		int ssd2price = plugin.getSSDShop().getInt("500GB.Price");
		int ssd2power = plugin.getSSDShop().getInt("500GB.Power");
		Material ssd2material = Material.matchMaterial(plugin.getSSDShop().getString("500GB.Item"));
		
		ItemStack ssd2 = new ItemStack(ssd2material);
		ItemMeta ssd2meta = ssd2.getItemMeta();
		ssd2meta.setDisplayName(ChatColor.GREEN+"500GB NVME SSD");
		ArrayList<String> ssd2lore = new ArrayList<String>();
		ssd2lore.add(ChatColor.GREEN+"500GB NVME SSD");
		ssd2lore.add(ChatColor.GREEN+"Power: "+ssd2power);
		ssd2lore.add(ChatColor.GREEN+"Price $"+ssd2price);
		ssd2lore.add(ChatColor.GREEN+"Type: SSD");
		ssd2meta.setLore(ssd2lore);
		ssd2.setItemMeta(ssd2meta);
		gui.setItem(ssd2slot, ssd2);
		
		int ssd3slot = plugin.getSSDShop().getInt("1000GB.Slot");
		int ssd3price = plugin.getSSDShop().getInt("1000GB.Price");
		int ssd3power = plugin.getSSDShop().getInt("1000GB.Power");
		Material ssd3material = Material.matchMaterial(plugin.getSSDShop().getString("1000GB.Item"));
		
		ItemStack ssd3 = new ItemStack(ssd3material);
		ItemMeta ssd3meta = ssd3.getItemMeta();
		ssd3meta.setDisplayName(ChatColor.GREEN+"1000GB NVME SSD");
		ArrayList<String> ssd3lore = new ArrayList<String>();
		ssd3lore.add(ChatColor.GREEN+"1000GB NVME SSD");
		ssd3lore.add(ChatColor.GREEN+"Power: "+ssd3power);
		ssd3lore.add(ChatColor.GREEN+"Price $"+ssd3price);
		ssd3lore.add(ChatColor.GREEN+"Type: SSD");
		ssd3meta.setLore(ssd3lore);
		ssd3.setItemMeta(ssd3meta);
		gui.setItem(ssd3slot, ssd3);
		
		int ssd4slot = plugin.getSSDShop().getInt("2000GB.Slot");
		int ssd4price = plugin.getSSDShop().getInt("2000GB.Price");
		int ssd4power = plugin.getSSDShop().getInt("2000GB.Power");
		Material ssd4material = Material.matchMaterial(plugin.getSSDShop().getString("2000GB.Item"));
		
		ItemStack ssd4 = new ItemStack(ssd4material);
		ItemMeta ssd4meta = ssd4.getItemMeta();
		ssd4meta.setDisplayName(ChatColor.GREEN+"2000GB NVME SSD");
		ArrayList<String> ssd4lore = new ArrayList<String>();
		ssd4lore.add(ChatColor.GREEN+"2000GB NVME SSD");
		ssd4lore.add(ChatColor.GREEN+"Power: "+ssd4power);
		ssd4lore.add(ChatColor.GREEN+"Price $"+ssd4price);
		ssd4lore.add(ChatColor.GREEN+"Type: SSD");
		ssd4meta.setLore(ssd4lore);
		ssd4.setItemMeta(ssd4meta);
		gui.setItem(ssd4slot, ssd4);
		
		p.openInventory(gui);
		
	}
	
	public void PSUShopGeneration(Player p, Main plugin) {
		
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "PSU Shop");
		
		int psu1slot = plugin.getPSUShop().getInt("4000W.Slot");
		int psu1price = plugin.getPSUShop().getInt("4000W.Price");
		Material psu1material = Material.getMaterial(plugin.getPSUShop().getString("4000W.Item"));
		
		ItemStack psu1 = new ItemStack(psu1material);
		ItemMeta psu1meta = psu1.getItemMeta();
		psu1meta.setDisplayName(ChatColor.GREEN+"4000W PSU");
		ArrayList<String> psu1lore = new ArrayList<String>();
		psu1lore.add(ChatColor.GREEN+"4000W PSU");
		psu1lore.add(ChatColor.GREEN+"Price $"+psu1price);
		psu1lore.add(ChatColor.GREEN+"Type: PSU");
		psu1meta.setLore(psu1lore);
		psu1.setItemMeta(psu1meta);
		gui.setItem(psu1slot, psu1);
		
		int psu2slot = plugin.getPSUShop().getInt("6500W.Slot");
		int psu2price = plugin.getPSUShop().getInt("6500W.Price");
		Material psu2material = Material.matchMaterial(plugin.getPSUShop().getString("6500W.Item"));
		
		ItemStack psu2 = new ItemStack(psu2material);
		ItemMeta psu2meta = psu1.getItemMeta();
		psu2meta.setDisplayName(ChatColor.GREEN+"6500W PSU");
		ArrayList<String> psu2lore = new ArrayList<String>();
		psu2lore.add(ChatColor.GREEN+"6500W PSU");
		psu2lore.add(ChatColor.GREEN+"Price $"+psu2price);
		psu2lore.add(ChatColor.GREEN+"Type: PSU");
		psu2meta.setLore(psu2lore);
		psu2.setItemMeta(psu2meta);
		gui.setItem(psu2slot, psu2);
		
		int psu3slot = plugin.getPSUShop().getInt("7500W.Slot");
		int psu3price = plugin.getPSUShop().getInt("7500W.Price");
		Material psu3material = Material.matchMaterial(plugin.getPSUShop().getString("6500W.Item"));
		
		ItemStack psu3 = new ItemStack(psu3material);
		ItemMeta psu3meta = psu3.getItemMeta();
		psu3meta.setDisplayName(ChatColor.GREEN+"7500W PSU");
		ArrayList<String> psu3lore = new ArrayList<String>();
		psu3lore.add(ChatColor.GREEN+"7500W PSU");
		psu3lore.add(ChatColor.GREEN+"Price $"+psu3price);
		psu3lore.add(ChatColor.GREEN+"Type: PSU");
		psu3meta.setLore(psu3lore);
		psu3.setItemMeta(psu3meta);
		gui.setItem(psu3slot, psu3);
		
		int psu4slot = plugin.getPSUShop().getInt("8000W.Slot");
		int psu4price = plugin.getPSUShop().getInt("8000W.Price");
		Material psu4material = Material.matchMaterial(plugin.getPSUShop().getString("8000W.Item"));
		
		ItemStack psu4 = new ItemStack(psu4material);
		ItemMeta psu4meta = psu4.getItemMeta();
		psu4meta.setDisplayName(ChatColor.GREEN+"8000W PSU");
		ArrayList<String> psu4lore = new ArrayList<String>();
		psu4lore.add(ChatColor.GREEN+"8000W PSU");
		psu4lore.add(ChatColor.GREEN+"Price $"+psu4price);
		psu4lore.add(ChatColor.GREEN+"Type: PSU");
		psu4meta.setLore(psu4lore);
		psu4.setItemMeta(psu4meta);
		gui.setItem(psu4slot, psu4);
		
		p.openInventory(gui);
		
	}

}
