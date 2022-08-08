package com.frank142857.metropolis.init;

import com.frank142857.metropolis.item.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> REGISTER_ITEMS = new ArrayList<Item>();

    public static final IngotsMTR DYNAMITE_INGOT = new IngotsMTR("dynamite_ingot");

    public static final ItemTeleportationRod TELEPORTATION_ROD = new ItemTeleportationRod();
}
