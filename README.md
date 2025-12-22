# 📢 RoleChat

**RoleChat** is a **CraftBukkit 1.4.6 plugin** that customizes chat messages based on player **roles/groups**. It allows server owners to assign colors and display roles in chat dynamically. Compatible with **bPermissions**.

---

## 📌 Features

- 🎨 **Colored Role Tags:** Each role/group has a custom color.  
- 💬 **Custom Chat Format:** Define how messages appear in chat, including role, player name, and message.  
- 🛠️ **Permission-Based Roles:** Detect player roles via `group.<role>` permissions.  
- ⚙️ **Default Role Fallback:** Players without a role get a default group.  

---

## 📦 Installation

1. Download **RoleChat.jar** and place it in your server’s `plugins/` folder.  
2. Start or restart your server.  
3. A `config.yml` will be generated automatically.  
4. Edit `config.yml` to customize chat formatting and role colors.

---

## ⚙️ Configuration

`config.yml` example:

```yaml
# RoleChat configuration

# Chat format: %group% = role, %player% = player name, %message% = chat message
chat-format: "&7[%group%] &f%player%: %message%"

# Default role/group for players without a group
default-group: "default"

# Role colors (keys = group names, values = color codes)
role-colors:
  admin: "&c"       # red
  moderator: "&b"   # light blue
  vip: "&6"         # gold
  member: "&9"      # dark blue
  default: "&7"     # gray

