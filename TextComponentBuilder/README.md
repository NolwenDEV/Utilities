<h1 align="center">
  TextComponentBuilder
</h1>

<div align="center">

[![License](https://img.shields.io/badge/License-GNU%20General%20Public%20License%20v3.0-blue?style=for-the-badge)](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)
</div>

Create complex clickable message using BungeeCord TextComponent API with just one line !  
With this little builder, you can create clickable message with just one line by combining multiple TextComponent into a single one.

## Contents

- [Usage Example](#usage-example)
  - [Simple Clickable Message](#simple-clickable-message)
  - [Complex Clickable Message](#complex-clickable-message)
- [Licensing](#licensing)

## Usage Example

Here's a few examples to help you understand how this builder works !

### Simple Clickable Message

The simple way to do clickable message allows you to make your whole message clickable :D  
Refer to [Complex Clickable Message](#complex-clickable-message) if you want to make only specific part of your message clickable !

```Java
player.spigot().sendMessage(
    new TextComponentBuilder()
        .setMessage("§7Put your mouse on me then click !")
        .setHoverEvent(HoverEvent.Action.SHOW_TEXT, String.format("§fHi~.. §e%s §f👉🏻👈🏻 ! 💞", player.getName()))
        .setClickAction(ClickEvent.Action.RUN_COMMAND, "/say Again !!!")
        .build()
);
```

### Complex Clickable Message

The complex way to do clickable message allows you to select which part of your message should be clickable / hoverrable :D  
(It's called "Complex" but.. it's easy, I promise !)

```Java
player.spigot().sendMessage(
    new TextComponentBuilder()
        .compose(
            new TextComponentBuilder().setMessage("§7Put your mouse on me then click !")
                .setHoverEvent(HoverEvent.Action.SHOW_TEXT, String.format("§fOhh~.. you again, §e%s §f👉🏻👈🏻 ! 💞💞", player.getName()))
                .setClickAction(ClickEvent.Action.RUN_COMMAND, "/say You're so naughty~ !!!")
                .build(),
                                                    
            new TextComponentBuilder().setMessage(" §7My turn !!!")
                .setHoverEvent(HoverEvent.Action.SHOW_TEXT, String.format("§fI'm just hoverrable 😔"))
                .build(),

            new TextComponentBuilder().setMessage(" §7I'm neither hoverrable or clickable.. 😭").build()
        ).build()
);
```

## Licensing

TextComponentBuilder is under the "[GNU General Public License v3.0](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)".
