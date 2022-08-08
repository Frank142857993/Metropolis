package com.frank142857.metropolis.item;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.*;
import net.minecraft.item.Item;

public class IngotsMTR extends Item implements IHasModel {

    public IngotsMTR(String name){
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
