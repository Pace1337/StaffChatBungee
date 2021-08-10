package dev.pace.staffchatbungee.commands;

import dev.pace.staffchatbungee.StaffChatBungee;
import dev.pace.staffchatbungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;

public class StaffChatCommand extends Command {

    private final StaffChatBungee staffchatbungee;

    public StaffChatCommand() {
        super("sc", null, "staffchat");
        this.staffchatbungee = StaffChatBungee.instance;
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("staffchatbungee.command")) {
            sender.sendMessage(this.staffchatbungee.getMsg("no-permission"));
        } else if (args.length == 0 || args[0] == null) {
            sender.sendMessage(this.staffchatbungee.getMsg("no-message"));
        } else {
            StringBuilder staffchat = new StringBuilder();
            {
                staffchat.append(String.join(" ", args));
            }

            final HashMap<String, String> sc = new HashMap<>();
            sc.put("%prefix%", StaffChatBungee.instance.config.getString("prefix"));
            sc.put("%author%", sender.getName());
            sc.put("%msg%", escape(staffchat.toString()));

            if (sender instanceof ProxiedPlayer) {
                sc.put("%server%", ((ProxiedPlayer) sender).getServer().getInfo().getName());
            } else {
                sc.put("%server%", "");
            }

            final String msg = Utils.color(Utils.replacePlaceholders(this.staffchatbungee.config.getString("staffchat-format"), sc));
            for (final ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                if (all.hasPermission("staffchatbungee.command")) {
                    all.sendMessage(msg);
                }
            }
        }
    }

    // Fix for IllegalArgumentException.
    public static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f")
                .replace("\'", "\\'")
                .replace("\"", "\\\"");
    }
}
