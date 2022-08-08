package com.frank142857.metropolis.creativetab;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabMetropolis extends CreativeTabs {
    public TabMetropolis(){
        super("metropolis");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(BlockInit.BLOCK_MTR_PORTAL));
    }
}
