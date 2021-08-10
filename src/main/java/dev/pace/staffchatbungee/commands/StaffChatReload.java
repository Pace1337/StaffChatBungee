package dev.pace.staffchatbungee.commands;

import dev.pace.staffchatbungee.StaffChatBungee;
import dev.pace.staffchatbungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.logging.Level;

public class StaffChatReload extends Command {

    public StaffChatReload() {
        super("screload");
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("staffchatbungee.admin")) {
            sender.sendMessage(StaffChatBungee.instance.config.getString("no-permission"));
            return;
        }
        try {
            StaffChatBungee.instance.loadConfig();
        } catch (Exception ex) {
            sender.sendMessage(Utils.color(StaffChatBungee.instance.config.getString("prefix") + " &cAn error occurred while loading the configuration. Check console for more information."));
            StaffChatBungee.instance.getLogger().log(Level.SEVERE, "There was an error loading the configuration.", ex);
        } finally {
            sender.sendMessage(Utils.color(StaffChatBungee.instance.config.getString("prefix") + " &aConfiguration reloaded."));
        }
    }
}