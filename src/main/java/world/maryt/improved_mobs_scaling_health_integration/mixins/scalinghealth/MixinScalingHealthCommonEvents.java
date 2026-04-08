package world.maryt.improved_mobs_scaling_health_integration.mixins.scalinghealth;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.silentchaos512.scalinghealth.event.ScalingHealthCommonEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.silentchaos512.scalinghealth.config.Config.Client.Difficulty.sleepMessageOverride;
import static net.silentchaos512.scalinghealth.config.Config.Client.Difficulty.warnWhenSleeping;
import static net.silentchaos512.scalinghealth.config.Config.Difficulty.forSleeping;

@Mixin(value = ScalingHealthCommonEvents.class, remap = false)
public abstract class MixinScalingHealthCommonEvents {

    /**
     * @author RisingInIris2017
     * @reason Only send message when SleepResult is OK
     */
    @Overwrite
    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if (!player.world.isRemote && warnWhenSleeping && forSleeping != 0.0F && event.getResultStatus().equals(EntityPlayer.SleepResult.OK)) {
            String override = sleepMessageOverride;
            if (!override.isEmpty()) {
                player.sendMessage(new TextComponentString(override));
            } else {
                player.sendMessage(new TextComponentTranslation("misc.scalinghealth.sleepWarning"));
            }
        }
    }
}
