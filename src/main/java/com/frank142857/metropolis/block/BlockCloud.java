package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.init.MaterialInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockCloud extends Block implements IHasModel {
    private final String name = "cloud";

    public BlockCloud(){
        super(MaterialInit.CLOUD);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(0.8F);
        this.setResistance(0.8F);
        this.setSoundType(SoundType.CLOTH);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
