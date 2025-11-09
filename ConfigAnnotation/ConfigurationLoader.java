import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationLoader {
	
	private final Map<String, FileConfiguration> loadedFiles = new HashMap<>();
	
		// ---------------------------------------- \\
	
	public void load(Object instance) {
		for(Field field : instance.getClass().getDeclaredFields()) {
			if(field.getAnnotation(Configuration.class) == null) continue;
			Configuration annotation = field.getAnnotation(Configuration.class);
			
			FileConfiguration configuration = getConfigurationFile(annotation.FILE());
			Object value = configuration.get(annotation.KEY());
			
			if(value == null) {
				try { value = field.get(instance); } catch(IllegalAccessException exception) {}
				configuration.set(annotation.KEY(), value);
			}
			if(value instanceof String string && string.contains("&")) { value = string.replace("&", "§"); }
			
			try { field.set(instance, value); } catch(IllegalAccessException exception) {
				Main.getInstance().getLogger().severe(String.format("Field '%s' couldn't be defined : %s", field.getName(), exception.getMessage()));
			}
		}
		
		save();
	}
	
	private FileConfiguration getConfigurationFile(String fileName) {
		if(!fileName.endsWith(".yml")) fileName += ".yml";
		if(loadedFiles.containsKey(fileName)) return loadedFiles.get(fileName);
		
		File file = new File(Main.getInstance().getDataFolder(), fileName);
		if(!file.exists()) {
			try(InputStream input = Main.getInstance().getResource(String.format("configuration/%s", fileName))) {
				if(input != null) {
					Main.getInstance().getDataFolder().mkdirs();
					try(OutputStream output = new FileOutputStream(file)) {
						input.transferTo(output);
						Main.getInstance().getLogger().info(String.format("Default configuration sucessfully copied into '%s' !", file.getName()));
					}
				} else {
					file.createNewFile();
					Main.getInstance().getLogger().info(String.format("Empty configuration sucessfully created for '%s' !", file.getName()));
				}
			} catch(IOException exception) {
				Main.getInstance().getLogger().severe(String.format("File '%s' couldn't be created : %s", file.getName(), exception.getMessage()));
			}
		}
		
		loadedFiles.put(fileName, YamlConfiguration.loadConfiguration(file));
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		for(Map.Entry<String, FileConfiguration> entry : loadedFiles.entrySet()) {
			File file = new File(Main.getInstance().getDataFolder(), entry.getKey());
			
			try {
				entry.getValue().save(file);
				Main.getInstance().getLogger().info(String.format("File '%s' has been successfully saved !", file.getName()));
			} catch(IOException exception) {
				Main.getInstance().getLogger().severe(String.format("File '%s' couldn't be saved : %s", file, exception.getMessage()));
			}
		}
	}

}
