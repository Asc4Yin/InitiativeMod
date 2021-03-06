/*
    Copyright (C) 2018-2019 Init Studios. All rights reserved.

    This document is the property of Init Studios.
    It is considered confidential and proprietary.

    This document may not be reproduced or transmitted in any form,
    in whole or in part, without the express written permission of
    Init Studios.
 */

package com.initstudios.initiative.common.items;

import com.initstudios.initiative.Main;
import com.initstudios.initiative.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModItems {
    public static List<Item> ITEMS = new ArrayList<Item>();
    public static ArmorMaterial ARMOR_MATERIAL_STEEL_INGOT;
    public static Item OW_BH_DUST, OW_BH_STABLE, OW_BH_UNSTABLE, OW_BH_CRYSTAL;
    public static Item LB_LONG_FALL_BOOTS;
    public static Item LB_GEL_SPEED_BALL;
    public static void init() {
        ARMOR_MATERIAL_STEEL_INGOT = EnumHelper.addArmorMaterial("armor_material_steel_ingot", Reference.MOD_ID + ":ow_steel_ingot", 20, new int[]{5, 10, 12, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);
        OW_BH_UNSTABLE = registerItem(new ItemBase(), "ow_bh_unstable", Main.items);
        OW_BH_DUST = registerItem(new ItemBase(), "ow_bh_dust", Main.items);
        OW_BH_STABLE = registerItem(new ItemBase(), "ow_bh_stable", Main.items);
        OW_BH_CRYSTAL = registerItem(new ItemBase(), "ow_bh_crystal", Main.items);
        LB_LONG_FALL_BOOTS = registerItem( new ArmorBase(ARMOR_MATERIAL_STEEL_INGOT, 1, EntityEquipmentSlot.FEET), "lb_long_fall_boots");
        LB_GEL_SPEED_BALL = registerItem(new ItemSpeedGelBall(), "lb_gel_speed_ball", Main.items);
    }

    public static <T extends Item> T registerItem(T item, String name) {
        item.setTranslationKey(name);
        item.setRegistryName(name);
        ITEMS.add(item);
        return item;
    }


    public static <T extends Item> T registerItem(T item, String name, CreativeTabs tab) {
        item.setCreativeTab(tab);
        return registerItem(item, name);
    }

    public static void registerRenders() {
        for (Item i : ITEMS) {
            registerItemRenderer(i);
        }
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    private static void registerItemRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}