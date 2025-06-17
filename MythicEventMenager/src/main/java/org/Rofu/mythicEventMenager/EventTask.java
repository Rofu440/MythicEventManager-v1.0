package org.Rofu.mythicEventMenager;

import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;
import java.util.Random;

public class EventTask extends BukkitRunnable {

    private MythicEventMenager plugin;

    public EventTask(MythicEventMenager plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        List<Event> events = plugin.getConfigManager().getEvents();
        Random random = new Random();
        int totalChance = events.stream().mapToInt(Event::getChance).sum();
        int randomNumber = random.nextInt(totalChance);
        int currentChance = 0;

        for (Event event : events) {
            currentChance += event.getChance();
            if (randomNumber < currentChance) {
                plugin.setCurrentEvent(event);
                plugin.getServer().broadcastMessage(" ");
                plugin.getServer().broadcastMessage("Ивент " + plugin.getConfigManager().getName());
                plugin.getServer().broadcastMessage("Начнётся через: " + plugin.getConfigManager().getDelayTimer() + " секунд");
                plugin.getServer().broadcastMessage(" ");
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {

                    String command = event.getStartCommand();
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command);
                    plugin.setCurrentEvent(null);
                }, plugin.getConfigManager().getDelayTimer() * 20);
                break;
            }
        }
    }
}

