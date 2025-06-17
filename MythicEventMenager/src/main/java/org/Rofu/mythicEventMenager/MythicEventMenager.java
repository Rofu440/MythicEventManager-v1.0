package org.Rofu.mythicEventMenager;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MythicEventMenager extends JavaPlugin {

    private ConfigManager configManager;
    private EventTask eventTask;
    private Event currentEvent = null;
    private long eventStartTime;

    @Override
    public void onDisable() {
        getServer().getLogger().info("[MythicEventManager] выключен");
    }

    @Override
    public void onEnable() {

        getServer().getLogger().info("[MythicEventManager] включён");

        configManager = new ConfigManager(this);
        configManager.loadConfig();

        eventTask = new EventTask(this);
        // Запуск основного таймера (каждые timer минут)
        getServer().getScheduler().runTaskTimer(this, eventTask, 1, configManager.getTimer() * 20 * 60);

        // Регистрация команды /event
        getCommand("event").setExecutor(new TimerCommand(this));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
        this.eventStartTime = System.currentTimeMillis() + (configManager.getDelayTimer() * 1000);
    }

    public long getEventStartTime() {
        return eventStartTime;
    }
}

