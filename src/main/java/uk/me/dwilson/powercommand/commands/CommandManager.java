package uk.me.dwilson.powercommand.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import uk.me.dwilson.powercommand.Main;

/**
 * Created by dwils on 19/03/2017.
 */
public class CommandManager implements CommandExecutor {
    private Main main;

    public CommandManager(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (arguments.length == 0) {
            commandSender.sendMessage(ChatColor.GOLD + "PowerCommand by dwilson5817");
            commandSender.sendMessage(ChatColor.GOLD + "Use /powercommand run to execute commands");

            return true;
        }

        if (arguments[0].equals("run")) {
            if (commandSender.hasPermission("powercommand.run")) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (String commandForExecution : main.getCommandsFileManager().getCommandList()) {
                            main.getServer().dispatchCommand(main.getServer().getConsoleSender(), commandForExecution);
                        }
                    }
                }.runTaskAsynchronously(main);
            } else {
                commandSender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            }

            return true;
        }

        return false;
    }
}
