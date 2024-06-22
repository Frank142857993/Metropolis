package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSilverBookshelf extends Block implements IHasModel {
    private final String name = "silver_bookshelf";

    public BlockSilverBookshelf(){
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHardness(4.5F);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public int quantityDropped(Random random)
    {
        return 3; //TODO Change quantityDropped after GUI implemented
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.BOOK;
    }

    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 1.0F;
        //TODO 3 books = 1.0F; 6 books = 2.0F
    }
}
