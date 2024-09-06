package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCloudGlassPane extends BlockPane implements IHasModel {
    private final String name = "cloud_glass_pane";

    public BlockCloudGlassPane(){
        super(Material.GLASS, false);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(0.3F);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setSoundType(SoundType.GLASS);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
