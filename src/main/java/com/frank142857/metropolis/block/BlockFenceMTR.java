package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockFenceMTR extends BlockFence implements IHasModel {
    public BlockFenceMTR(String name, float hardness, float resistance){
        super(Material.ROCK, Material.ROCK.getMaterialMapColor());
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.useNeighborBrightness = true;
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
