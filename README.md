GoodBlocker
A powerful and flexible Minecraft plugin to block commands on your server.

📋 Description
GoodBlocker is a Spigot/Paper plugin that allows server administrators to block specific commands from being used by players. It features a flexible configuration system, wildcard support, and an intuitive command interface.

✨ Features
Block any command - Prevent players from using specific commands
Wildcard support - Use * to block groups of commands (e.g., minecraft:*)
Permission system - Bypass blocks with permissions
Dynamic management - Add/remove blocked commands without editing files
Customizable messages - Full color code support
Logging system - Track blocked command attempts
Tab completion - Easy command usage
Lightweight - Minimal performance impact
🔧 Installation
Download the latest GoodBlocker.jar from releases
Place the file in your server's plugins/ folder
Restart your server
Configure the plugin in plugins/GoodBlocker/config.yml
Use /gb reload to apply changes
📦 Requirements
Minecraft 1.17 or higher
Spigot, Paper, or any compatible server software
Java 16 or higher
🎮 Commands
Command Description Permission
/gb reload Reload the configuration goodblocker.reload
/gb list List all blocked commands goodblocker.admin
/gb add <command> Add a command to the block list goodblocker.admin
/gb remove <command> Remove a command from the block list goodblocker.admin
Aliases: /goodblocker, /gb, /blocker

🔐 Permissions
Permission Description Default
goodblocker.admin Full access to the plugin OP
goodblocker.bypass Bypass all command blocks OP
goodblocker.reload Reload the configuration OP
⚙️ Configuration
# List of blocked commands
blocked-commands:
  - "op"
  - "deop"
  - "stop"
  - "reload"
  - "minecraft:give"
  - "plugins"
  - "pl"
  - "version"
  - "ver"

# Plugin messages
messages:
  blocked-command: "&c&lAccess Denied! &7This command is blocked."
  no-permission: "&cYou don't have permission to use this command."
  reload-success: "&aConfiguration reloaded successfully!"
  command-added: "&aCommand added to the block list."
  command-removed: "&aCommand removed from the block list."

# Enable logging of blocked command attempts
enable-logging: true

🎯 Usage Examples
Block a single command
/gb add gamemode

Block all commands from a namespace
/gb add minecraft:*

Remove a blocked command
/gb remove gamemode

View all blocked commands
/gb list

Reload configuration
/gb reload

🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

📝 License
This project is licensed under the MIT License.

👤 Author
Vnxsitoow

GitHub: @Vnxsitoow
🐛 Bug Reports
If you find a bug, please open an issue on GitHub with:

Minecraft version
Server software and version
Plugin version
Steps to reproduce
Error logs (if any)
💡 Support
For support, please open an issue on GitHub or contact the author.

Made with ❤️ for the Minecraft community
