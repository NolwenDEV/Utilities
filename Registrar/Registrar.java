import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;

public class Registrar {
	
	public void registerCommands(String packageName) {
		try {
			for(Class<?> instance : getClasses(packageName)) {
				if(!CommandExecutor.class.isAssignableFrom(instance)) continue;
				if(instance.getAnnotation(Command.class) == null) continue;
				
				Command command = instance.getAnnotation(Command.class);
				CommandExecutor commandExecutor = (CommandExecutor) instance.getDeclaredConstructor().newInstance();
				PluginCommand pluginCommand = Main.getInstance().getCommand(command.NAME());
				
				if(pluginCommand == null) {
					Main.getInstance().getLogger().warning(String.format("⚠️ | Command '/%s' not found in plugin.yml !", command.NAME()));
					continue;
				}
				
				pluginCommand.setExecutor(commandExecutor);
				Main.getInstance().getLogger().info(String.format("✅ | Command '/%s' (%s) successfully registered !", command.NAME(), instance.getSimpleName()));
			}
		} catch(Exception exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occured while registering commands : %s", exception.getMessage()));
		}
	}
	
	public void registerListeners(String packageName) {
		try {
			for(Class<?> instance : getClasses(packageName)) {
				if(!Listener.class.isAssignableFrom(instance)) continue;
				
				Main.getInstance().getServer().getPluginManager().registerEvents((Listener) instance.getDeclaredConstructor().newInstance(), Main.getInstance());
				Main.getInstance().getLogger().info(String.format("✅ | Listener '%s' successfully registered !", instance.getSimpleName()));
			}
		} catch(Exception exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occured while registering listeners : %s", exception.getMessage()));
		}
	}
	
		// ---------------------------------------- \\
	
	private Set<Class<?>> getClasses(String packageName) throws Exception {
		if(getClass().getClassLoader().getResource(packageName.replace(".", "/")) == null) return Collections.emptySet();
		if(!(new File(getClass().getClassLoader().getResource(packageName.replace(".", "/")).toURI())).exists()) return Collections.emptySet();
		
		Set<Class<?>> classes = new HashSet<>();
		File directory = new File(getClass().getClassLoader().getResource(packageName.replace(".", "/")).toURI());
		
		for(File file : Objects.requireNonNull(directory.listFiles())) {
			if(file.isDirectory()) {
				classes.addAll(getClasses(String.format("%s.%s", packageName, file.getName())));
			} else if(file.getName().endsWith(".class")) {
				classes.add(Class.forName(String.format("%s.%s", packageName, file.getName().replace(".class", ""))));
			}
		}
		
		return classes;
	}

}
