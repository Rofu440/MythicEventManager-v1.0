package org.Rofu.mythicEventMenager;

public class Event {
    private String name;
    private String startCommand;
    private int chance;

    public Event(String name, String startCommand, int chance) {
        this.name = name;
        this.startCommand = startCommand;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public String getStartCommand() {
        return startCommand;
    }

    public int getChance() {
        return chance;
    }
}
