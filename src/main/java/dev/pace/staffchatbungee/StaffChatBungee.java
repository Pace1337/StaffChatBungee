package dev.pace.staffchatbungee;

import dev.pace.staffchatbungee.commands.StaffChatCommand;
import dev.pace.staffchatbungee.commands.StaffChatReload;
import dev.pace.staffchatbungee.utils.Utils;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.file.*;
import java.util.logging.Level;

public final class StaffChatBungee extends Plugin {

    public Configuration config;
    public static StaffChatBungee instance;

    public void onLoad() {
        StaffChatBungee.instance = this;
        try {
            this.loadConfig();
        }
        catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, "An error occurred while loading the configuration", ex);
        }
    }

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerCommand(this, new StaffChatReload());
        this.getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
    }

    public String getMsg(final String name) {
        return Utils.color(config.getString(name));
    }

    public void loadConfig() throws IOException {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        final File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            final InputStream in = this.getResourceAsStream("config.yml");
            Files.copy(in, file.toPath(), new CopyOption[0]);
        }
        this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
    }
    public static StaffChatBungee getInstance() {
        return instance;
    }
}
