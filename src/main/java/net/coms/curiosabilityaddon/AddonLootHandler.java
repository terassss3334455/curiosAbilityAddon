package net.coms.curiosabilityaddon;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class AddonLootHandler {
    private static final ResourceLocation END_CITY = new ResourceLocation(
            "minecraft", "chests/end_city_treasure");
    private static final ResourceLocation DESERT_PYRAMID = new ResourceLocation(
            "minecraft", "chests/desert_pyramid");

    @SubscribeEvent
    public void addEnhancersToStructureLoot(LootTableLoadEvent event) {
        if (!END_CITY.equals(event.getName()) && !DESERT_PYRAMID.equals(event.getName())) return;

        LootPool pool = LootPool.lootPool()
                .name("curiosabilityaddon_enhancers")
                .setRolls(ConstantValue.exactly(1.0F))
                .add(EmptyLootItem.emptyItem().setWeight(18))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.PROBABILITY_LENS.get()).setWeight(2))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.COMBAT_CHARM.get()).setWeight(2))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.HARVEST_CHARM.get()).setWeight(2))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.TREASURE_CHARM.get()).setWeight(2))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.VITALITY_CHARM.get()).setWeight(2))
                .add(LootItem.lootTableItem(CuriosAbilityAddon.AGILITY_CHARM.get()).setWeight(2))
                .build();
        event.getTable().addPool(pool);
    }
}