<h1 align="center">
  ConfigAnnotation
</h1>

<div align="center">

[![License](https://img.shields.io/badge/License-GNU%20General%20Public%20License%20v3.0-blue?style=for-the-badge)](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)
</div>

Manage multiple configuration files easily !

## Contents

- [Usage Example](#usage-example)
- [Licensing](#licensing)

## Usage Example

Here's an example to help you understand how this annotation works !  
My annotation automatically convert `&` into `§` to put color in your message and automatically define default values based on the file in your project :D
(You're a very lazy developer.. don't you ? Then, thanks me later :3)

```Java
@Configuration(FILE = "Configuration", KEY = "MY.BEAUTIFUL.MESSAGE")
private String beautifulMessage;

public String getBeautifulMessage() { return beautifulMessage; }
```

`Configuration.yml` Example :

```Yaml
MY:
    BEAUTIFUL:
        MESSAGE: "&7awwwnn.. I'm pretty~ 👉🏻👈🏻 ? 💞"
```

## Licensing

ConfigAnnotation is under the "[GNU General Public License v3.0](https://github.com/NolwenDEV/Utilities/blob/Main/LICENSE)".
