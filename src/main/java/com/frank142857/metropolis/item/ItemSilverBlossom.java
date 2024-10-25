package com.frank142857.metropolis.item;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemSilverBlossom extends Item implements IHasModel {
    private final String name = "silver_blossom";

    public ItemSilverBlossom(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
