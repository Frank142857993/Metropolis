package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IBlockDecay;
import com.frank142857.metropolis.init.DimensionInit;
import com.frank142857.metropolis.util.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class BlockDecay {

    public BlockDecay(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onPlayerStandOnBlocks(LivingEvent.LivingUpdateEvent event){

        EntityLivingBase entity = event.getEntityLiving();
        BlockPos pos = new BlockPos(entity.posX, entity.posY - 0.2, entity.posZ);
        Random random = new Random();

        if(entity.world.isRemote){
            return;
        }

        IBlockState[] before = new IBlockState[]{
                Blocks.STONEBRICK.getDefaultState(),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED),
                Blocks.IRON_BLOCK.getDefaultState()
        };

        IBlockState[] after = new IBlockState[]{
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED),
                Blocks.AIR.getDefaultState(),
                BlockInit.SLIGHTLY_RUSTED_IRON_BLOCK.getDefaultState()
        };

        int[] chances = new int[]{3000, 1500, 1000, 9000};

        boolean state = false;

        switch(ConfigHandler.BLOCK_DECAY){
            case 0:
                return;
            case 2:
                state = true;
                break;
            default:
                state = entity.world.provider.getDimension() == DimensionInit.metropolis.getId();
                break;

        }

        for(int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
            for (int y = pos.getY(); y <= pos.getY() + 2; y++) {
                for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {

                    IBlockState target = entity.world.getBlockState(new BlockPos(x, y, z));

                    if (target.getBlock() instanceof IBlockDecay && state){

                        IBlockDecay targetBlock = ((IBlockDecay)target.getBlock());

                        int i1 = random.nextInt(targetBlock.getChance(target.getBlock().getMetaFromState(target)));
                        if (i1 == 0){
                            entity.world.setBlockState(new BlockPos(x, y, z), (targetBlock.getAfter(target.getBlock().getMetaFromState(target))));
                        }
                    } else {
                        for(int i = 0; i < before.length; i++){
                            if (target == before[i] && state){
                                int i1 = random.nextInt(chances[i]);
                                if (i1 == 0){
                                    entity.world.setBlockState(new BlockPos(x, y, z), after[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
