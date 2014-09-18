package com.stabilishednetwork.tatanpoker09.cob;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class Map {
	static List<Map> mapsList = new ArrayList<Map>();
	private String name;
	private File mapFolder;
	private String author;
	private YamlConfiguration yml;

	public Map(String name, File mapFolder, String author, YamlConfiguration yml){
		this.setName(name);
		this.setMapFolder(mapFolder);
		this.setAuthor(author);
		this.setYml(yml);
	}
	
	public static void addMap(Map map){
		mapsList.add(map);
	}
	
	public static List<Map> getMaps() {
		return mapsList;
	}

	public Location loadCenter() {
		return null;
	}

	public static void loadMaps() {
		File mapsDir = Main.getMapsDirectory();
		for(File map : mapsDir.listFiles()){
			if(map.isDirectory()){
				if(new File(map, "level.dat").exists() && new File(map, "region").exists() && new File(map, "map.yml").exists()){
					YamlConfiguration mapYml = YamlConfiguration.loadConfiguration(new File(map, "map.yml"));
					String name = mapYml.getString("name");
					String author = mapYml.getString("author");
					addMap(new Map(name, map, author, mapYml));
					System.out.println("[COB] Found Map! "+name);
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getMapFolder() {
		return mapFolder;
	}

	public void setMapFolder(File mapFolder) {
		this.mapFolder = mapFolder;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public YamlConfiguration getYml() {
		return yml;
	}

	public void setYml(YamlConfiguration yml) {
		this.yml = yml;
	}

}
