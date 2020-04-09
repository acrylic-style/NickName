package xyz.acrylicstyle.nick.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acrylicstyle.craftbukkit.CraftPlayer;

public class Nick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be invoked from console.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/nick <nick> [player]");
            return true;
        }
        if (args.length == 1) {
            Player player = (Player) sender;
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', args[0]));
            new CraftPlayer(player).getProfile().setField("name", args[0]);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(p.getUniqueId())) continue;
                p.hidePlayer(player);
                p.showPlayer(player);
            }
            sender.sendMessage(ChatColor.GREEN + "ニックネームを" + args[0] + "に設定しました。");
            return true;
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayerExact(args[1]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません。");
                return true;
            }
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', args[0]));
            new CraftPlayer(player).getProfile().setField("name", args[0]);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(p.getUniqueId())) continue;
                p.hidePlayer(player);
                p.showPlayer(player);
            }
            sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "のニックネームを" + args[0] + "に設定しました。");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "/nick <nick> [player]");
        return true;
    }
}
