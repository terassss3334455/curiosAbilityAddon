package net.coms.curiosabilityaddon.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public final class AbilityEnhancerItem extends Item implements ICurioItem {
    public enum Type {
        PROBABILITY("probability_lens", new EffectBonus[0]),
        COMBAT("combat_charm", new EffectBonus[]{new EffectBonus(MobEffects.DAMAGE_BOOST, 0)}),
        HARVEST("harvest_charm", new EffectBonus[]{
                new EffectBonus(MobEffects.DIG_SPEED, 0), new EffectBonus(MobEffects.LUCK, 0)}),
        TREASURE("treasure_charm", new EffectBonus[]{new EffectBonus(MobEffects.LUCK, 1)}),
        VITALITY("vitality_charm", new EffectBonus[]{
                new EffectBonus(MobEffects.REGENERATION, 0),
                new EffectBonus(MobEffects.DAMAGE_RESISTANCE, 0)}),
        AGILITY("agility_charm", new EffectBonus[]{
                new EffectBonus(MobEffects.MOVEMENT_SPEED, 1), new EffectBonus(MobEffects.JUMP, 0)});

        private final String id;
        private final EffectBonus[] effects;

        Type(String id, EffectBonus[] effects) {
            this.id = id;
            this.effects = effects;
        }
    }

    private record EffectBonus(MobEffect effect, int amplifier) {}

    private final Type type;

    public AbilityEnhancerItem(Type type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();
        if (!(wearer instanceof Player player) || player.level().isClientSide
                || player.tickCount % 20 != 0) return;
        for (EffectBonus bonus : type.effects) {
            keepEffect(player, bonus.effect(), bonus.amplifier());
        }
    }

    private static void keepEffect(Player player, MobEffect effect, int amplifier) {
        MobEffectInstance current = player.getEffect(effect);
        if (current == null || current.getAmplifier() < amplifier || current.getDuration() <= 40) {
            player.addEffect(new MobEffectInstance(effect, 60, amplifier, true, false, true));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip,
                                TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.curiosabilityaddon." + type.id));
    }
}