package com.vlkodlak.rolechat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.java.JavaPlugin;

public class RoleChat extends JavaPlugin implements Listener {

    private String chatFormat;
    private String defaultGroup;
    private Map<String, String> roleColors;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("RoleChat enabled");
    }

    private void loadConfig() {
        chatFormat = ChatColor.translateAlternateColorCodes(
                '&',
                getConfig().getString("chat-format", "&7[%group%] &f%player%: %message%")
        );

        defaultGroup = getConfig().getString("default-group", "default");

        roleColors = new HashMap<String, String>();
        if (getConfig().isConfigurationSection("role-colors")) {
            Set<String> keys = getConfig().getConfigurationSection("role-colors").getKeys(false);
            for (String key : keys) {
                roleColors.put(key, getConfig().getString("role-colors." + key, "&7"));
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String group = defaultGroup;

        Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
        for (PermissionAttachmentInfo perm : perms) {
            if (perm.getPermission().startsWith("group.")) {
                group = perm.getPermission().substring("group.".length());
                break;
            }
        }

        // fallback to default if role is not in config
        if (!roleColors.containsKey(group)) {
            group = defaultGroup;
        }

        String colorCode = ChatColor.translateAlternateColorCodes('&', roleColors.getOrDefault(group, "&7"));

        String message = chatFormat
                .replace("%group%", colorCode + capitalize(group) + ChatColor.RESET)
                .replace("%player%", player.getName())
                .replace("%message%", event.getMessage());

        event.setFormat(message);
    }

    private String capitalize(String text) {
        if (text == null || text.length() == 0) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}

