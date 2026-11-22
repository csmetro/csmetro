package org.csmetro.data;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum TicketBarrierOpen implements StringRepresentable {

    CLOSED("closed"), OPEN("open"), OPEN_CONCESSIONARY("open_concessionary");
    private final String name;

    TicketBarrierOpen(String nameIn) {
        name = nameIn;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    public boolean isOpen() {
        return this != CLOSED;
    }
}