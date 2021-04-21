package io.github.simplycmd.skyfabrication.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum BaitType implements StringIdentifiable {
    COW("cow"),
    PIG("pig"),
    SHEEP("sheep"),
    CHICKEN("chicken");

    private final String name;

    private BaitType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}