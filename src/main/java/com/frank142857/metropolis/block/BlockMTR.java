package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockMTR extends Block implements IHasModel {
    public BlockMTR(String name, Material material, MapColor color, String harvestTool, int harvestLevel, float hardness, float lightValue){
        super(material, color);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setHardness(hardness);
        this.setLightLevel(lightValue);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
