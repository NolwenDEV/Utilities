import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;

public class Registrar {
	
	public void registerCommands(String packageName) {
		try {
			int counter = 0;
			for(Class<?> instance : getClasses(packageName)) {
				if(!CommandExecutor.class.isAssignableFrom(instance)) continue;
				if(instance.getAnnotation(CommandRegistrar.class) == null) continue;
				
				CommandRegistrar command = instance.getAnnotation(CommandRegistrar.class);
				CommandExecutor commandExecutor = (CommandExecutor) instance.getDeclaredConstructor().newInstance();
				PluginCommand pluginCommand = Main.getInstance().getCommand(command.NAME());
				
				if(pluginCommand == null) {
					Main.getInstance().getLogger().warning(String.format("⚠️ | Command '/%s' not found in plugin.yml !", command.NAME()));
					continue;
				}
				
				pluginCommand.setExecutor(commandExecutor);
				counter++;
			}
			
			Main.getInstance().getLogger().info(String.format("✅ | A total of %s commands has been successfully registered !", counter));
		} catch(Exception exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occured while registering commands : %s", exception.getMessage()));
		}
	}
	
	public void registerListeners(String packageName) {
		try {
			int counter = 0;
			for(Class<?> instance : getClasses(packageName)) {
				if(!Listener.class.isAssignableFrom(instance)) continue;

				counter++;
				Main.getInstance().getServer().getPluginManager().registerEvents((Listener) instance.getDeclaredConstructor().newInstance(), Main.getInstance());
			}
			
			Main.getInstance().getLogger().info(String.format("✅ | A total of %s listeners has been successfully registered !", counter));
		} catch(Exception exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occured while registering listeners : %s", exception.getMessage()));
		}
	}
	
		// ---------------------------------------- \\
	
	private Set<Class<?>> getClasses(String packageName) throws Exception {
	    Set<Class<?>> classes = new HashSet<>();

	    String path = packageName.replace('.', '/');
	    try (java.util.jar.JarFile jarFile = new java.util.jar.JarFile(Main.getInstance().getPluginFile())) {
	        var entries = jarFile.entries();

	        while (entries.hasMoreElements()) {
	            var entry = entries.nextElement();
	            String name = entry.getName();

	            if (name.startsWith(path) && name.endsWith(".class") && !entry.isDirectory()) {
	                String className = name.replace('/', '.').replace(".class", "");
	                classes.add(Class.forName(className));
	            }
	        }
	    }

	    return classes;
	}


}
