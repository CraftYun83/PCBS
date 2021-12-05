package com.fourmc.computers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.fourmc.computers.commands.CPUShopCommands;
import com.fourmc.computers.commands.GPUShopCommands;
import com.fourmc.computers.commands.PCShopCommands;
import com.fourmc.computers.commands.PSUShopCommands;
import com.fourmc.computers.commands.RAMShopCommands;
import com.fourmc.computers.commands.SSDShopCommands;
import com.fourmc.computers.functions.PCCraftingRecipe;
import com.fourmc.computers.listeners.PlayerInventoryClickListener;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	
	private Economy econ;
    private Permission perms;
    private Chat chat;
    public File gpuYML;
    public FileConfiguration gpuConfig;
    public File pcshopYML;
    public FileConfiguration pcshopConfig;
    public File ramYML;
    public FileConfiguration ramConfig;
    public File cpuYML;
    public FileConfiguration cpuConfig;
    public File ssdYML;
    public FileConfiguration ssdConfig;
    public File psuYML;
    public FileConfiguration psuConfig;
	
	public void onEnable(){
		
		if (!setupEconomy()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
		
        this.setupPermissions();
        this.setupChat();
		createGPUConfig();
		createPCShopConfig();
		createRAMConfig();
		createCPUConfig();
		createSSDConfig();
		createPSUConfig();
		
		new PCCraftingRecipe(this);
		new GPUShopCommands(this);
		new PCShopCommands(this);
		new RAMShopCommands(this);
		new CPUShopCommands(this);
		new SSDShopCommands(this);
		new PSUShopCommands(this);
		new PlayerInventoryClickListener(this);
		
	}
	
	private void createGPUConfig() {
		
		gpuYML = new File(getDataFolder(), "gpu.yml");
        if (!gpuYML.exists()) {
        	
        	gpuYML.getParentFile().mkdirs();
            saveResource("gpu.yml", false);
            
         }

        gpuConfig= new YamlConfiguration();
        
        try {
        	
        	gpuConfig.load(gpuYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void saveGPUConfig() {
    	
    	try {
    		
    		gpuConfig.save(gpuYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getGPU() {
    	
    	return gpuConfig;
    	
    }
	
	private void createPCShopConfig() {
		
		pcshopYML = new File(getDataFolder(), "pcshop.yml");
        if (!pcshopYML.exists()) {
        	
        	pcshopYML.getParentFile().mkdirs();
            saveResource("pcshop.yml", false);
            
         }

        pcshopConfig = new YamlConfiguration();
        
        try {
        	
        	pcshopConfig.load(pcshopYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void savePCShopConfig() {
    	
    	try {
    		
    		pcshopConfig.save(pcshopYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getPCShop() {
    	
    	return pcshopConfig;
    	
    }
	
	private void createRAMConfig() {
		
		ramYML = new File(getDataFolder(), "ram.yml");
        if (!ramYML.exists()) {
        	
        	ramYML.getParentFile().mkdirs();
            saveResource("ram.yml", false);
            
         }

        ramConfig = new YamlConfiguration();
        
        try {
        	
        	ramConfig.load(ramYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void saveRAMConfig() {
    	
    	try {
    		
    		ramConfig.save(ramYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getRAMShop() {
    	
    	return ramConfig;
    	
    }
	
	private void createCPUConfig() {
		
		cpuYML = new File(getDataFolder(), "cpu.yml");
        if (!cpuYML.exists()) {
        	
        	cpuYML.getParentFile().mkdirs();
            saveResource("cpu.yml", false);
            
         }

        cpuConfig = new YamlConfiguration();
        
        try {
        	
        	cpuConfig.load(cpuYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void saveCPUConfig() {
    	
    	try {
    		
    		cpuConfig.save(cpuYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getCPUShop() {
    	
    	return cpuConfig;
    	
    }
	
	private void createSSDConfig() {
		
		ssdYML = new File(getDataFolder(), "ssd.yml");
        if (!ssdYML.exists()) {
        	
        	ssdYML.getParentFile().mkdirs();
            saveResource("ssd.yml", false);
            
         }

        ssdConfig = new YamlConfiguration();
        
        try {
        	
        	ssdConfig.load(ssdYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void saveSSDConfig() {
    	
    	try {
    		
    		ssdConfig.save(ssdYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getSSDShop() {
    	
    	return ssdConfig;
    	
    }
	
	private void createPSUConfig() {
		
		psuYML = new File(getDataFolder(), "psu.yml");
        if (!psuYML.exists()) {
        	
        	psuYML.getParentFile().mkdirs();
            saveResource("psu.yml", false);
            
         }

        psuConfig = new YamlConfiguration();
        
        try {
        	
        	psuConfig.load(psuYML);
        	
        } catch (IOException | InvalidConfigurationException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	
	public void savePSUConfig() {
    	
    	try {
    		
    		psuConfig.save(psuYML);
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
	
	public FileConfiguration getPSUShop() {
    	
    	return psuConfig;
    	
    }
	
	private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public Permission getPermissions() {
        return perms;
    }

    public Chat getChat() {
        return chat;
    }
	
}
