package uk.me.dwilson.powercommand;

import org.bukkit.plugin.java.JavaPlugin;
import uk.me.dwilson.powercommand.commands.CommandManager;
import uk.me.dwilson.powercommand.utils.CommandsFileManager;

/**
 * Created by dwils on 17/03/2017.
 */
public class Main extends JavaPlugin {
    private CommandsFileManager commandsFileManager;

    @Override
    public void onEnable() {
        getLogger().info("Loading PowerCommand...");
        commandsFileManager = new CommandsFileManager(this);
        getCommand("powercommand").setExecutor(new CommandManager(this));
    }

    public CommandsFileManager getCommandsFileManager() {
        return commandsFileManager;
    }
}
