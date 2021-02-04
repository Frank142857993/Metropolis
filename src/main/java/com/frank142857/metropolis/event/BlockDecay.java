package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.DimensionInit;
import com.frank142857.metropolis.util.handlers.ConfigHandler;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class BlockDecay {
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
                /*
                TODO add marble to decaying blocks
                BlockRegistryHandler.BLOCK_MARBLE.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK.getDefaultState().withProperty(BlockMarbleBrick.VARIANT, BlockMarbleBrick.EnumType.PATTERN_B),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK.getDefaultState().withProperty(BlockMarbleBrick.VARIANT, BlockMarbleBrick.EnumType.PATTERN_C),
                BlockRegistryHandler.BLOCK_MARBLE_CRACKED.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState().withProperty(BlockMarbleBrickCracked.VARIANT, BlockMarbleBrickCracked.EnumType.PATTERN_B),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState().withProperty(BlockMarbleBrickCracked.VARIANT, BlockMarbleBrickCracked.EnumType.PATTERN_C),

                 */

        };

        IBlockState[] after = new IBlockState[]{
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED),
                Blocks.AIR.getDefaultState(),
                /*
                BlockRegistryHandler.BLOCK_MARBLE_CRACKED.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState(),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState().withProperty(BlockMarbleBrickCracked.VARIANT, BlockMarbleBrickCracked.EnumType.PATTERN_B),
                BlockRegistryHandler.BLOCK_MARBLE_BRICK_CRACKED.getDefaultState().withProperty(BlockMarbleBrickCracked.VARIANT, BlockMarbleBrickCracked.EnumType.PATTERN_C),
                Blocks.AIR.getDefaultState(),
                Blocks.AIR.getDefaultState(),
                Blocks.AIR.getDefaultState(),
                Blocks.AIR.getDefaultState()

                 */
        };

        //int[] chances = new int[]{250000, 10000, 5000, 400000, 300000, 299999, 299998, 8000, 6000, 5999, 5998};
        int[] chances = new int[]{250000, 10000, 5000};

        boolean state = false;

        switch(ConfigHandler.BLOCK_DECAY){
            case 0:
                return;
            case 1:
                state = entity.world.provider.getDimension() == DimensionInit.metropolis.getId();
                break;
            case 2:
                state = true;
                break;
        }

        for(int i = 0; i < before.length; i++){

            for(int x = pos.getX() - 2; x <= pos.getX() + 2; x++){
                for(int y = pos.getY(); y <= pos.getY() + 2; y++){
                    for(int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++){
                        if (entity.world.getBlockState(new BlockPos(x, y, z)) == before[i] && state){
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
