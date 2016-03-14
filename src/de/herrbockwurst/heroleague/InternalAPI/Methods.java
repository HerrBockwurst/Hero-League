package de.herrbockwurst.heroleague.InternalAPI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import de.herrbockwurst.heroleague.Main;

public class Methods {
	
	public static String getPluginName(Boolean form) {
		if(form == false) {
			return Main.thisclass.getDescription().getName();
		} else {
			return ChatColor.DARK_GREEN + "[" + Main.thisclass.getDescription().getName() + "]" + ChatColor.WHITE;
		}		
	}
	//Einfaches Item hinzufügen
	public static void InvAddItem(Material material, Inventory inv, int Slot, String name, String[] lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> Lore = new ArrayList<String>();
		for(String clore : lore) {
			Lore.add(clore);
		}
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}
	//Team Banner hinzufügen
	public static void InvAddBanner(Inventory inv, int Slot, String name, String[] lore, DyeColor color) {
		ItemStack item = new ItemStack(Material.BANNER);
		BannerMeta meta = (BannerMeta) item.getItemMeta();
		meta.setDisplayName(name);
		meta.setBaseColor(color);
		List<String> Lore = new ArrayList<String>();
		for(String clore : lore) {
			Lore.add(clore);
		}
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}
	//Heldenauswahl
	
	 public static ItemStack getSkull (String texture) {
	        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	        PropertyMap propertyMap = profile.getProperties();
	        if (propertyMap == null) {
	            throw new IllegalStateException("Profile doesn't contain a property map");
	        }
	        String b64texture = Base64Coder.encodeString("{textures:{SKIN:{url:\""+texture+"\"}}}");
	        propertyMap.put("textures", new Property("textures", b64texture));
	        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	        ItemMeta headMeta = head.getItemMeta();
	        Class<?> headMetaClass = headMeta.getClass();
	        try {
	            getField(headMetaClass, "profile", GameProfile.class, 0).set(headMeta, profile);
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	        head.setItemMeta(headMeta);
	        return head;
	    }
	   
	 private static <T> Field getField(Class<?> target, String name, Class<T> fieldType, int index) {
	        for (final Field field : target.getDeclaredFields()) {
	            if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
	                field.setAccessible(true);
	                return field;
	            }
	        }

	        // Search in parent classes
	        if (target.getSuperclass() != null)
	            return getField(target.getSuperclass(), name, fieldType, index);
	        throw new IllegalArgumentException("Cannot find field with type " + fieldType);
	    }
	
	public static ItemStack InvAddSkullName(ItemStack item, String name) {
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
		
	}
	
	public static ItemStack AddSkullLore(ItemStack skull, List<String> lore) {
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setLore(lore);
		skull.setItemMeta(meta);
		return skull;
	}
	


}
