package org.Rofu.mythicEventMenager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {

    private MythicEventMenager plugin;

    public TimerCommand(MythicEventMenager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Event currentEvent = plugin.getCurrentEvent();
            if (currentEvent != null) {
                long timeLeft = plugin.getEventStartTime() - System.currentTimeMillis();
                if (timeLeft > 0) {
                    sender.sendMessage("До начала ивента " + currentEvent.getName() + ": " + timeLeft / 1000 + " секунд.");
                } else {
                    sender.sendMessage("Ивент " + currentEvent.getName() + " уже запущен.");
                }
            } else {
                sender.sendMessage("До выбора следующего ивента: " + plugin.getConfigManager().getTimer() + " минут.");
            }
            return true;
        }
        return false;
    }
}
