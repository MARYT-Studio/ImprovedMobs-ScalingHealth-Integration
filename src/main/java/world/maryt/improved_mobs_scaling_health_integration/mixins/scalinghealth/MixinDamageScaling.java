package world.maryt.improved_mobs_scaling_health_integration.mixins.scalinghealth;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.silentchaos512.scalinghealth.event.DamageScaling;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DamageScaling.class, remap = false)
public abstract class MixinDamageScaling {

    /**
     * @author RisingInIris2017
     * @reason Remove damage scaling, but save the other mechanics in this class.
     */
    @Overwrite
    @SubscribeEvent(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerHurt(LivingAttackEvent event) {}
}
