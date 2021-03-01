package com.frank142857.metropolis.item;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class Ingots extends Item implements IHasModel {

    private final String name = "metal_ingot";

    public Ingots(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHasSubtypes(true);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel(){
        for(int i = 0; i < EnumType.values().length; i++){
            Metropolis.proxy.registerItemRenderer(this, i, "metal_ingot_" + EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> stacks) {
        for(EnumType variant : EnumType.values()){
            if (tabs == this.getCreativeTab()){
                stacks.add(new ItemStack(this, 1, variant.getMeta()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        for(int i = 0; i < EnumType.values().length; i++){
            if(stack.getItemDamage() == i){
                return this.getUnlocalizedName() + "_" + EnumType.values()[i].getName();
            }
        }
        return this.getUnlocalizedName() + "_" + EnumType.STEEL.getName();
    }

    public static enum EnumType implements IStringSerializable {
        STEEL(0, "steel", "steel"),
        BLUE_STEEL(1, "blue_steel", "blue_steel"),
        ARGENTUM(2, "argentum", "argentum"),
        DYNAMITE(3, "dynamite", "dynamite");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, nameU;

        private EnumType(int meta, String name){
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String nameU){
            this.meta = meta;
            this.name = name;
            this.nameU = nameU;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMeta() {
            return this.meta;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static EnumType byMetaData(int meta){
            return META_LOOKUP[meta];
        }

        static {
            for(EnumType enumType : values()){
                META_LOOKUP[enumType.getMeta()] = enumType;
            }
        }
    }
}
