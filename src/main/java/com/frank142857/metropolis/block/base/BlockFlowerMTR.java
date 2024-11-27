package com.frank142857.metropolis.block.base;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockFlowerMTR extends BlockBush implements IHasModel {
    public BlockFlowerMTR(String name, MapColor color){
        super(Material.GRASS, color);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
