package com.fourmc.computers.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fourmc.computers.Main;
import com.fourmc.computers.functions.CaseConfigurationGUI;
import com.fourmc.computers.functions.MotherboardConfigurationGUI;
import com.fourmc.computers.functions.RandomCollection;
import com.fourmc.computers.functions.ShopGeneration;

public class PlayerInventoryClickListener implements Listener {
	
	private Main plugin;
	
	public PlayerInventoryClickListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (ChatColor.stripColor(e.getView().getTitle()).equals("PC Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
					
			} else {
				
				try {
					
					Material mobomaterial = Material.matchMaterial(plugin.getPCShop().getString("Motherboard.Item"));
					Material gpushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("GPUShop.Item"));
					Material ramshopmaterial = Material.matchMaterial(plugin.getPCShop().getString("RAMShop.Item"));
					Material cpushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("CPUShop.Item"));
					Material ssdshopmaterial = Material.matchMaterial(plugin.getPCShop().getString("SSDShop.Item"));
					Material psushopmaterial = Material.matchMaterial(plugin.getPCShop().getString("PSUShop.Item"));
					Material moboconfigmaterial = Material.matchMaterial(plugin.getPCShop().getString("MOBOConfig.Item"));
					Material caseconfigmaterial = Material.matchMaterial(plugin.getPCShop().getString("CASEConfig.Item"));
					
					if (e.getCurrentItem().getType() == mobomaterial && ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equals("Generic Motherboard")) {
						
						ItemStack item = e.getCurrentItem();
						int price = plugin.getPCShop().getInt("Motherboard.Price");
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							
							ItemMeta productMeta = product.getItemMeta();
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(1);
							lore.remove(2);
							RandomCollection<Object> rc = new RandomCollection<>();
							for(String s : plugin.getPCShop().getConfigurationSection("MOBO Brands").getKeys(false)) {	
								rc.add(plugin.getPCShop().getInt("MOBO Brands."+s+".rarity"), s);
							}
							String brand = (String) rc.next();
							String cc = ChatColor.translateAlternateColorCodes('&', plugin.getPCShop().getString("MOBO Brands."+brand+".cc"));
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: "+cc+brand);
							productMeta.setDisplayName(cc+brand+" "+product.getItemMeta().getDisplayName());
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a(n) "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else if (e.getCurrentItem().getType() == gpushopmaterial) {
						
						ShopGeneration sg = new ShopGeneration();
						
						sg.GPUShopGeneration(p, plugin.getGPU());
						
					} else if (e.getCurrentItem().getType() == ramshopmaterial) {
						
						ShopGeneration sg = new ShopGeneration();
						
						sg.RamShopGeneration(p, plugin);
						
					} else if (e.getCurrentItem().getType() == cpushopmaterial) {
						
						ShopGeneration sg = new ShopGeneration();
						
						sg.CPUShopGeneration(p, plugin.getCPUShop());
						
					} else if (e.getCurrentItem().getType() == ssdshopmaterial) {
						
						ShopGeneration sg = new ShopGeneration();
						
						sg.SSDShopGeneration(p, plugin);
						
					} else if (e.getCurrentItem().getType() == psushopmaterial) {
						
						ShopGeneration sg = new ShopGeneration();
						
						sg.PSUShopGeneration(p, plugin);
						
					} else if (e.getCurrentItem().getType() == moboconfigmaterial) {
						
						new MotherboardConfigurationGUI(p);
						
					} else if (e.getCurrentItem().getType() == caseconfigmaterial) {
						
						new CaseConfigurationGUI(p);
						
					} else {
						
						e.setCancelled(true);;
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("GPU Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				try {
					
					if (e.getCurrentItem().getType() != Material.AIR && (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).contains("GTX") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).contains("RTX"))) {
						
						ItemStack item = e.getCurrentItem();
						int price = Integer.valueOf(ChatColor.stripColor(item.getItemMeta().getLore().get(2)).replace("Price: $", ""));
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							RandomCollection<Object> rc = new RandomCollection<>();
							for(String s : plugin.getGPU().getConfigurationSection("Brands").getKeys(false)) {	
								rc.add(plugin.getGPU().getInt("Brands."+s+".rarity"), s);
							}
							String brand = (String) rc.next();
							String cc = ChatColor.translateAlternateColorCodes('&', plugin.getGPU().getString("Brands."+brand+".cc"));
							ItemMeta productMeta = product.getItemMeta();
							productMeta.setDisplayName(cc+brand+" "+product.getItemMeta().getDisplayName());
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(2);
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: "+cc+brand);
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else {
						
						p.closeInventory();
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("CPU Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				try {
					
					if (e.getCurrentItem().getType() == Material.REDSTONE) {
						
						ItemStack item = e.getCurrentItem();
						int price = Integer.valueOf(ChatColor.stripColor(item.getItemMeta().getLore().get(2)).replace("Price: $", ""));
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							ItemMeta productMeta = product.getItemMeta();
							productMeta.setDisplayName(product.getItemMeta().getDisplayName());
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(2);
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: AMD");
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else {
						
						p.closeInventory();
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("RAM Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				try {
					
					Material ram1material = Material.matchMaterial(plugin.getRAMShop().getString("8GB.Item"));
					Material ram2material = Material.matchMaterial(plugin.getRAMShop().getString("16GB.Item"));
					
					if ((e.getCurrentItem().getType() == ram1material || e.getCurrentItem().getType() == ram2material)) {
						
						ItemStack item = e.getCurrentItem();
						int price = Integer.valueOf(ChatColor.stripColor(item.getItemMeta().getLore().get(2)).replace("Price $", ""));
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							RandomCollection<Object> rc = new RandomCollection<>();
							for(String s : plugin.getRAMShop().getConfigurationSection("Brands").getKeys(false)) {	
								rc.add(plugin.getRAMShop().getInt("Brands."+s+".rarity"), s);
							}
							String brand = (String) rc.next();
							String cc = ChatColor.translateAlternateColorCodes('&', plugin.getRAMShop().getString("Brands."+brand+".cc"));
							ItemMeta productMeta = product.getItemMeta();
							productMeta.setDisplayName(cc+brand+" "+product.getItemMeta().getDisplayName());
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(2);
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: "+cc+brand);
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else {
						
						p.closeInventory();
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("SSD Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				try {
					
					Material ssd1material = Material.matchMaterial(plugin.getSSDShop().getString("250GB.Item"));
					Material ssd2material = Material.matchMaterial(plugin.getSSDShop().getString("500GB.Item"));
					Material ssd3material = Material.matchMaterial(plugin.getSSDShop().getString("1000GB.Item"));
					Material ssd4material = Material.matchMaterial(plugin.getSSDShop().getString("2000GB.Item"));
					
					if ((e.getCurrentItem().getType() == ssd1material || e.getCurrentItem().getType() == ssd2material || e.getCurrentItem().getType() == ssd3material || e.getCurrentItem().getType() == ssd4material)) {
						
						ItemStack item = e.getCurrentItem();
						int price = Integer.valueOf(ChatColor.stripColor(item.getItemMeta().getLore().get(2)).replace("Price $", ""));
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							RandomCollection<Object> rc = new RandomCollection<>();
							for(String s : plugin.getSSDShop().getConfigurationSection("Brands").getKeys(false)) {	
								rc.add(plugin.getSSDShop().getInt("Brands."+s+".rarity"), s);
							}
							String brand = (String) rc.next();
							String cc = ChatColor.translateAlternateColorCodes('&', plugin.getSSDShop().getString("Brands."+brand+".cc"));
							ItemMeta productMeta = product.getItemMeta();
							productMeta.setDisplayName(cc+brand+" "+product.getItemMeta().getDisplayName());
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(2);
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: "+cc+brand);
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else {
						
						p.closeInventory();
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("PSU Shop") && e.getView().getBottomInventory() != e.getClickedInventory()) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				try {
					
					Material ssd1material = Material.matchMaterial(plugin.getPSUShop().getString("4000W.Item"));
					Material ssd2material = Material.matchMaterial(plugin.getPSUShop().getString("6500W.Item"));
					Material ssd3material = Material.matchMaterial(plugin.getPSUShop().getString("7500W.Item"));
					Material ssd4material = Material.matchMaterial(plugin.getPSUShop().getString("8000W.Item"));
					
					if ((e.getCurrentItem().getType() == ssd1material || e.getCurrentItem().getType() == ssd2material || e.getCurrentItem().getType() == ssd3material || e.getCurrentItem().getType() == ssd4material)) {
						
						ItemStack item = e.getCurrentItem();
						int price = Integer.valueOf(ChatColor.stripColor(item.getItemMeta().getLore().get(1)).replace("Price $", ""));
						
						if (plugin.getEconomy().getBalance(p) >= price) {
							
							plugin.getEconomy().withdrawPlayer(p, price);
							ItemStack product = item.clone();
							RandomCollection<Object> rc = new RandomCollection<>();
							for(String s : plugin.getPSUShop().getConfigurationSection("Brands").getKeys(false)) {	
								rc.add(plugin.getPSUShop().getInt("Brands."+s+".rarity"), s);
							}
							String brand = (String) rc.next();
							String cc = ChatColor.translateAlternateColorCodes('&', plugin.getPSUShop().getString("Brands."+brand+".cc"));
							ItemMeta productMeta = product.getItemMeta();
							productMeta.setDisplayName(cc+brand+" "+product.getItemMeta().getDisplayName());
							List<String> lore = item.clone().getItemMeta().getLore();
							lore.remove(1);
							lore.add(ChatColor.ITALIC+""+ChatColor.GOLD+"Manufacturer: "+cc+brand);
							productMeta.setLore(lore);
							product.setItemMeta(productMeta);
							p.sendMessage(ChatColor.GREEN+"You have succesfully purchased a "+product.getItemMeta().getDisplayName()+ChatColor.GREEN+" for $"+price);
							p.getInventory().addItem(product);
							
							p.closeInventory();
							
						} else {
							
							p.sendMessage(ChatColor.RED+"You have insufficient funds!");
							p.closeInventory();
							
						}
					
					} else {
						
						p.closeInventory();
						
					}
					
				} catch (NullPointerException exc) {
					
					p.closeInventory();
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("Configure Motherboard")) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				Material motherboarditem = Material.getMaterial(plugin.getPCShop().getString("Motherboard.Item"));
				
				if (e.getCurrentItem() != null) {
					
					if (e.getCurrentItem().getType() == Material.LIME_CONCRETE && e.getView().getBottomInventory() != e.getClickedInventory()) {
						
						ItemStack motherboardslot = e.getClickedInventory().getItem(19);
						ItemStack pcpartslot = e.getClickedInventory().getItem(25);
						
						if (motherboardslot.getType() == motherboarditem && pcpartslot.getType() != Material.YELLOW_STAINED_GLASS_PANE) {
							
							ItemMeta motherboardslotMeta = motherboardslot.getItemMeta();
							List<String> motherboardslotlore = motherboardslotMeta.getLore();
							
							ArrayList<String> has = new ArrayList<String>();
							for (String i : motherboardslotlore) {
								
								if (i.contains("TX")) {
									
									has.add("GPU");
									
								} else if (i.contains("AMD")) {
									
									has.add("CPU");
									
								} else if (i.contains("Ram")) {
									
									has.add("RAM");
									
								} else if (i.contains("SSD")) {
									
									has.add("SSD");
									
								}
								
							}
							
							String type = ChatColor.stripColor(pcpartslot.getItemMeta().getLore().get(2)).replace("Type: ", "");
							if (has.contains(type)) {
								
								e.setCancelled(true);
								p.sendMessage(ChatColor.RED+"This motherboard has "+type+" in it already.");
								
							} else {
								
								try {
									
									int mobopower = Integer.valueOf(ChatColor.stripColor(motherboardslotlore.get(1)).replace("Power: ", ""));
									int pcpartpower = Integer.valueOf(ChatColor.stripColor(pcpartslot.getItemMeta().getLore().get(1)).replace("Power: ", ""));
									int mobonewpower = pcpartpower + mobopower;
									motherboardslotlore.set(1, ChatColor.ITALIC+""+ChatColor.GOLD+"Power: "+mobonewpower);
									
								} catch (Exception exc) {;}
								motherboardslotlore.add(ChatColor.GREEN+"+ "+pcpartslot.getItemMeta().getDisplayName());
								motherboardslotMeta.setLore(motherboardslotlore);
								motherboardslot.setItemMeta(motherboardslotMeta);
								p.getInventory().addItem(motherboardslot);
								p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 3.0F, 0.5F);
								p.closeInventory();
								
							}
							
						} else {
							
							p.sendMessage(ChatColor.RED+"Please include both your motherboard and your pc part!");
							e.setCancelled(true);
							
						}
						
					} else if (e.getCurrentItem().getType() == motherboarditem) {
						
						if (e.getView().getBottomInventory() == e.getClickedInventory()) {
							
							if (e.getCurrentItem().hasItemMeta()) {
								
								try {
									
									if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: MOBO")) {
										
										if (e.getView().getTopInventory().getItem(19).getType() != Material.YELLOW_STAINED_GLASS_PANE) {
											
											p.getInventory().addItem(e.getView().getTopInventory().getItem(19));
											e.setCancelled(true);
											
										}
										
										e.getView().getTopInventory().setItem(19, e.getCurrentItem());
										p.getInventory().remove(e.getCurrentItem());
										
									} else {
										
										p.sendMessage(ChatColor.RED+"That is not a motherboard!");
										e.setCancelled(true);
										
									}
									
								} catch (Exception exc) {
									
									p.sendMessage(ChatColor.RED+"That is not a motherboard!");
									e.setCancelled(true);
									
								}
								
							} else {
								
								p.sendMessage(ChatColor.RED+"That is not a motherboard!");
								e.setCancelled(true);
								
							}
							
						} else {
							
							p.getInventory().addItem(e.getView().getTopInventory().getItem(19));
							e.getView().getTopInventory().setItem(19, e.getCurrentItem());
							ItemStack motherboardslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
							ItemMeta motherboardslotmeta = motherboardslot.getItemMeta();
							motherboardslotmeta.setDisplayName(ChatColor.GREEN+"Click on your motherboard to add it to the slot!");
							motherboardslot.setItemMeta(motherboardslotmeta);
							e.getView().getTopInventory().setItem(19, motherboardslot);
							e.setCancelled(true);
							
						}
						
					} else if (e.getCurrentItem().getType() != Material.YELLOW_STAINED_GLASS_PANE && e.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
						
						if (e.getView().getBottomInventory() == e.getClickedInventory()) {
							
							if (e.getCurrentItem().hasItemMeta()) {
								
								try {
									
									if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: CPU") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: SSD") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: RAM") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: GPU")) {
										
										if (e.getView().getTopInventory().getItem(25).getType() != Material.YELLOW_STAINED_GLASS_PANE) {
											
											p.getInventory().addItem(e.getView().getTopInventory().getItem(25));
											e.setCancelled(true);
											
										}
										
										e.getView().getTopInventory().setItem(25, e.getCurrentItem());
										p.getInventory().remove(e.getCurrentItem());
										
									} else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(1)).equals("Type: PSU")){
										
										p.sendMessage(ChatColor.RED+"You cannot put a PSU on a motherboard!");
										e.setCancelled(true);
										
									} else {
										
										p.sendMessage(ChatColor.RED+"That is not a PC Part!");
										e.setCancelled(true);
										
									}
									
								} catch (Exception exc) {
									
									exc.printStackTrace();
									
									p.sendMessage(ChatColor.RED+"That is not a PC Part!");
									e.setCancelled(true);
									
								}
								
							} else {
								
								p.sendMessage(ChatColor.RED+"That is not a PC Part!");
								e.setCancelled(true);
								
							}
							
						} else {
							
							p.getInventory().addItem(e.getView().getTopInventory().getItem(25));
							ItemStack pcpartslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
							ItemMeta pcpartslotmeta = pcpartslot.getItemMeta();
							pcpartslotmeta.setDisplayName(ChatColor.GREEN+"Click on your PC Part to add it to the slot!");
							pcpartslot.setItemMeta(pcpartslotmeta);
							e.getView().getTopInventory().setItem(25, pcpartslot);
							e.setCancelled(true);
							
						}
						
					} else {
						
						e.setCancelled(true);
						
					}
					
				} else {
					
					e.setCancelled(true);
					
				}
				
			}
			
		} else if (ChatColor.stripColor(e.getView().getTitle()).equals("Configure Case")) {
			
			if (e.getClick().isShiftClick()) {
				
				e.setCancelled(true);
				
			} else {
				
				Material motherboarditem = Material.getMaterial(plugin.getPCShop().getString("Motherboard.Item"));
				
				Material psu1 = Material.getMaterial(plugin.getPSUShop().getString("4000W.Item"));
				Material psu2 = Material.getMaterial(plugin.getPSUShop().getString("6500W.Item"));
				Material psu3 = Material.getMaterial(plugin.getPSUShop().getString("7500W.Item"));
				Material psu4 = Material.getMaterial(plugin.getPSUShop().getString("8000W.Item"));
				
				Material caseitem = Material.getMaterial(plugin.getPCShop().getString("CASEConfig.Item"));
				
				if (e.getCurrentItem() != null) {
					
					if (e.getCurrentItem().getType() == Material.LIME_CONCRETE && e.getView().getBottomInventory() != e.getClickedInventory()) {
						
						ItemStack caseslot = e.getClickedInventory().getItem(19);
						ItemStack pcpartslot = e.getClickedInventory().getItem(25);
						
						if (caseslot.getType() == caseitem && pcpartslot.getType() != Material.YELLOW_STAINED_GLASS_PANE) {
							
							if (ChatColor.stripColor(pcpartslot.getItemMeta().getLore().get(1)).equals("Type: PSU")) {
								
								ItemMeta caseslotmeta = caseslot.getItemMeta();
								List<String> caselore = caseslotmeta.getLore();
								caselore.add(ChatColor.GREEN+"+ "+pcpartslot.getItemMeta().getDisplayName());
								caseslotmeta.setLore(caselore);
								if (ChatColor.stripColor(caseslotmeta.getDisplayName()).equals("Incomplete Gaming Rig")) {
									
									int power = Integer.valueOf(ChatColor.stripColor(caselore.get(0)).replace("Power: ", ""));
									
									if ((power < 8000 && power >= 7500) || power > 8000) {
										
										caseslotmeta.setDisplayName(ChatColor.BLACK+"BEAST Gaming Rig");
										
									} else if (power < 7500 && power >= 6500) {
										
										caseslotmeta.setDisplayName(ChatColor.GOLD+"Amazing Gaming Rig");
										
									} else if (power < 6500 && power >= 4000) {
										
										caseslotmeta.setDisplayName(ChatColor.RED+"Nice Gaming Rig");
										
									} else if (power < 4000 && power >= 2000) {
										
										caseslotmeta.setDisplayName(ChatColor.GREEN+"Standard Gaming Rig");
										
									} else if (power < 2000) {
										
										caseslotmeta.setDisplayName(ChatColor.YELLOW+"Amateur Gaming Rig");
										
									}
									
									caseslot.setItemMeta(caseslotmeta);
									
									p.getInventory().addItem(caseslot);
									p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 3.0F, 0.5F);
									p.closeInventory();
									
								} else if (ChatColor.stripColor(caseslotmeta.getDisplayName()).equals("Empty PC Case")) {
									
									caseslotmeta.setDisplayName(ChatColor.YELLOW+"Incomplete Gaming Rig");
									
									caseslot.setItemMeta(caseslotmeta);
									
									p.getInventory().addItem(caseslot);
									p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 3.0F, 0.5F);
									p.closeInventory();
									
								} else {
									
									p.sendMessage(ChatColor.RED+"This rig is already complete, you cannot add anything on to it now!");
									
								}
								
							} else {
								
								ItemMeta caseslotmeta = caseslot.getItemMeta();
								List<String> caselore = caseslotmeta.getLore();
								caselore.set(0, pcpartslot.getItemMeta().getLore().get(1));
								caselore.add(ChatColor.GREEN+"+ "+pcpartslot.getItemMeta().getDisplayName());
								List<String> orglore = pcpartslot.getItemMeta().getLore();
								List<String> lore = new ArrayList<String>(orglore);
								int comp = 0;
								for (String i : lore) {
									
									if (ChatColor.stripColor(i).contains("+")) {
										
										comp++;
										
									}
									
								}
								plugin.getLogger().info(comp+"");
								if (comp >= 4) {
									
									for (String i : lore) {
										
										if (ChatColor.stripColor(i).contains("+")) {
											
											if (ChatColor.stripColor(i).contains("+")) {
												
												caselore.add(i);
												
											}
											
										}
										
									}
									
									caseslotmeta.setLore(caselore);
									
									if (ChatColor.stripColor(caseslotmeta.getDisplayName()).equals("Incomplete Gaming Rig")) {
										
										int power = Integer.valueOf(ChatColor.stripColor(caselore.get(0)).replace("Power: ", ""));
										
										if ((power < 8000 && power >= 7500) || power > 8000) {
											
											caseslotmeta.setDisplayName(ChatColor.BLACK+"BEAST Gaming Rig");
											
										} else if (power < 7500 && power >= 6500) {
											
											caseslotmeta.setDisplayName(ChatColor.GOLD+"Amazing Gaming Rig");
											
										} else if (power < 6500 && power >= 4000) {
											
											caseslotmeta.setDisplayName(ChatColor.RED+"Nice Gaming Rig");
											
										} else if (power < 4000 && power >= 2000) {
											
											caseslotmeta.setDisplayName(ChatColor.GREEN+"Standard Gaming Rig");
											
										} else if (power < 2000) {
											
											caseslotmeta.setDisplayName(ChatColor.YELLOW+"Amateur Gaming Rig");
											
										}
										
										caseslot.setItemMeta(caseslotmeta);
										p.getInventory().addItem(caseslot);
										p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 3.0F, 0.5F);
										p.closeInventory();
										
									} else if (ChatColor.stripColor(caseslotmeta.getDisplayName()).equals("Empty PC Case")) {
										
										caseslotmeta.setDisplayName(ChatColor.YELLOW+"Incomplete Gaming Rig");
										
										caseslot.setItemMeta(caseslotmeta);
										p.getInventory().addItem(caseslot);
										p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 3.0F, 0.5F);
										p.closeInventory();
										
									} else {
										
										p.sendMessage(ChatColor.RED+"This rig is already complete, you cannot add anything on to it now!");
										
									}
									
								} else {
									
									p.sendMessage(ChatColor.RED+"Your motherboard does not have all neccesary components!");
									e.setCancelled(true);
									
								}
								
							}
							
								
						} else {
							
							p.sendMessage(ChatColor.RED+"Please include both your case and your pc part!");
							e.setCancelled(true);
							
						}
						
					} else if (e.getCurrentItem().getType() == motherboarditem || e.getCurrentItem().getType() == psu1 || e.getCurrentItem().getType() == psu2 || e.getCurrentItem().getType() == psu3 || e.getCurrentItem().getType() == psu4) {
						
						if (e.getView().getBottomInventory() == e.getClickedInventory()) {
							
							if (e.getCurrentItem().hasItemMeta()) {
								
								try {
									
									if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(2)).equals("Type: MOBO") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(1)).equals("Type: PSU")) {
										
										if (e.getView().getTopInventory().getItem(25).getType() != Material.YELLOW_STAINED_GLASS_PANE) {
											
											p.getInventory().addItem(e.getView().getTopInventory().getItem(25));
											e.setCancelled(true);
											
										}
										e.getView().getTopInventory().setItem(25, e.getCurrentItem());
										p.getInventory().remove(e.getCurrentItem());
										
									} else {
										
										p.sendMessage(ChatColor.RED+"That is not a compatible pc part!");
										e.setCancelled(true);
										
									}
									
								} catch (Exception exc) {
									
									p.sendMessage(ChatColor.RED+"That is not a compatible pc part!");
									e.setCancelled(true);
									
								}
								
							} else {
								
								p.sendMessage(ChatColor.RED+"That is not a compatible pc part!");
								e.setCancelled(true);
								
							}
							
						} else {
							
							p.getInventory().addItem(e.getView().getTopInventory().getItem(25));
							ItemStack motherboardslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
							ItemMeta motherboardslotmeta = motherboardslot.getItemMeta();
							motherboardslotmeta.setDisplayName(ChatColor.GREEN+"Click on your pc part to add it to the slot!");
							motherboardslot.setItemMeta(motherboardslotmeta);
							e.getView().getTopInventory().setItem(25, motherboardslot);
							e.setCancelled(true);
							
						}
						
					} else if (e.getCurrentItem().getType() != Material.YELLOW_STAINED_GLASS_PANE && e.getCurrentItem().getType() != Material.GRAY_STAINED_GLASS_PANE) {
						
						if (e.getView().getBottomInventory() == e.getClickedInventory()) {
							
							if (e.getCurrentItem().hasItemMeta()) {
								
								try {
									
									if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).contains("Empty PC Case") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).contains("Incomplete Gaming Rig")) {
										
										if (e.getView().getTopInventory().getItem(19).getType() != Material.YELLOW_STAINED_GLASS_PANE) {
											
											p.getInventory().addItem(e.getView().getTopInventory().getItem(19));
											e.setCancelled(true);
											
										}
										
										e.getView().getTopInventory().setItem(19, e.getCurrentItem());
										p.getInventory().remove(e.getCurrentItem());
										
									} else {
										
										p.sendMessage(ChatColor.RED+"That is not a PC Case!");
										e.setCancelled(true);
										
									}
									
								} catch (Exception exc) {
									
									p.sendMessage(ChatColor.RED+"That is not a PC Part!");
									e.setCancelled(true);
									
								}
								
							} else {
								
								p.sendMessage(ChatColor.RED+"That is not a PC Part!");
								e.setCancelled(true);
								
							}
							
						} else {
							
							p.getInventory().addItem(e.getView().getTopInventory().getItem(19));
							ItemStack pcpartslot = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
							ItemMeta pcpartslotmeta = pcpartslot.getItemMeta();
							pcpartslotmeta.setDisplayName(ChatColor.GREEN+"Click on your PC Part to add it to the slot!");
							pcpartslot.setItemMeta(pcpartslotmeta);
							e.getView().getTopInventory().setItem(25, pcpartslot);
							e.setCancelled(true);
							
						}
						
					} else {
						
						e.setCancelled(true);
						
					}
					
				} else {
					
					e.setCancelled(true);
					
				}
				
			}
			
		}
		
	}

}
