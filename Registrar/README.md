<h1 align="center">
  Registrar
</h1>

<div align="center">

[![License](https://img.shields.io/badge/License-GNU%20General%20Public%20License%20v3.0-blue?style=for-the-badge)](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)
</div>

Register all your commands and listeners in two lines instead of many !

## Contents

- [Usage Example](#usage-example)
- [Licensing](#licensing)

## Usage Example

Here's an example to help you understand how this annotation works !  

```Java
@Command(NAME = "mybeautifulcommand")
public class MyBeautifulCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        sender.sendMessage("Oh wow.. it works !! :3");
        return false;
    }

}
```

`Main` Class Example :

```
public class Main extends JavaPlugin {

    private Registrar registrar;

    @Override
    public void onEnable() {
        registrar = new Registrar();

        registrar.registerCommands("my.beautiful.plugin.commands");
        registrar.registerListeners("my.beautiful.plugin.listeners");
    }

}
```

`plugin.yml` Example :

```Yaml
name: MyBeautifulPlugin
version: 1.0.0

author: AnAwesomeDeveloper
description: My Beautiful Plugin is beautiful, you should use it ! :3

main: my.beautiful.plugin.Main
api-version: 1.20

commands:
    mybeautifulcommand:
        description: I'm beautiful !
        usage: "/mybeautifulcommand"
```

## Licensing

ConfigAnnotation is under the "[GNU General Public License v3.0](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)".
