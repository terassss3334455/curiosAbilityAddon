package net.coms.curiosabilityaddon;

import net.coms.curiosabilityaddon.item.AbilityEnhancerItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(CuriosAbilityAddon.MODID)
public final class CuriosAbilityAddon {
    public static final String MODID = "curiosabilityaddon";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> PROBABILITY_LENS = register(
            "probability_lens", AbilityEnhancerItem.Type.PROBABILITY);
    public static final RegistryObject<Item> COMBAT_CHARM = register(
            "combat_charm", AbilityEnhancerItem.Type.COMBAT);
    public static final RegistryObject<Item> HARVEST_CHARM = register(
            "harvest_charm", AbilityEnhancerItem.Type.HARVEST);
    public static final RegistryObject<Item> TREASURE_CHARM = register(
            "treasure_charm", AbilityEnhancerItem.Type.TREASURE);
    public static final RegistryObject<Item> VITALITY_CHARM = register(
            "vitality_charm", AbilityEnhancerItem.Type.VITALITY);
    public static final RegistryObject<Item> AGILITY_CHARM = register(
            "agility_charm", AbilityEnhancerItem.Type.AGILITY);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_TABS.register(
            "curios_ability_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.curiosabilityaddon"))
                    .icon(() -> PROBABILITY_LENS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> ITEMS.getEntries()
                            .forEach(item -> output.accept(item.get())))
                    .build());

    private static RegistryObject<Item> register(String id, AbilityEnhancerItem.Type type) {
        return ITEMS.register(id, () -> new AbilityEnhancerItem(type,
                new Item.Properties().stacksTo(1)));
    }

    public CuriosAbilityAddon() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modBus);
        CREATIVE_TABS.register(modBus);
        MinecraftForge.EVENT_BUS.register(new AbilityChanceHandler());
        MinecraftForge.EVENT_BUS.register(new AddonLootHandler());
    }
}