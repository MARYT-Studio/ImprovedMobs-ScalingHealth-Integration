package world.maryt.improved_mobs_scaling_health_integration.mixins.scalinghealth;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import net.silentchaos512.scalinghealth.utils.ModifierHandler;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ModifierHandler.class, remap = false)
public abstract class MixinModifierHandler {
    /**
     * @author RisingInIris2017
     * @reason Remove health setting.
     */
    @Overwrite
    public static void setMaxHealth(EntityLivingBase entity, double amount, int op) {}

    /**
     * @author RisingInIris2017
     * @reason Remove attack damage setting.
     */
    @Overwrite
    public static void addAttackDamage(EntityLivingBase entity, double amount, int op) {}
}
