package net.coms.curiosabilityaddon;

import net.coms.abilitysgorwmod.api.event.AbilityApiEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;

public final class AbilityChanceHandler {
    private static final String[] COMBAT_ABILITIES = {
            "auto_counterattack", "damage_dodge", "critical_strike"
    };
    private static final String[] HARVEST_ABILITIES = {
            "crop_growth", "growth_on_plant", "grass_mineral"
    };
    private static final String[] TREASURE_ABILITIES = {
            "double_mob_drops", "free_enchant"
    };

    @SubscribeEvent
    public void onAbilityChance(AbilityApiEvents.AbilityChanceEvent event) {
        Player player = event.getPlayer();
        if (player == null || player.level().isClientSide) return;

        CuriosApi.getCuriosInventory(player).ifPresent(inventory -> {
            if (inventory.isEquipped(CuriosAbilityAddon.PROBABILITY_LENS.get())) {
                event.addChance(0.30F);
            }
            if (inventory.isEquipped(CuriosAbilityAddon.COMBAT_CHARM.get())
                    && matches(event.getAbilityId(), COMBAT_ABILITIES)) {
                event.addChance(0.20F);
            }
            if (inventory.isEquipped(CuriosAbilityAddon.HARVEST_CHARM.get())
                    && matches(event.getAbilityId(), HARVEST_ABILITIES)) {
                event.addChance(0.25F);
            }
            if (inventory.isEquipped(CuriosAbilityAddon.TREASURE_CHARM.get())
                    && matches(event.getAbilityId(), TREASURE_ABILITIES)) {
                event.addChance(0.25F);
            }
        });
    }

    private static boolean matches(String abilityId, String[] values) {
        for (String value : values) {
            if (value.equals(abilityId)) return true;
        }
        return false;
    }
}