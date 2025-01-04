package world.maryt.improved_mobs_scaling_health_integration.mixins.improvedmobs;

import com.flemmli97.improvedmobs.handler.DifficultyHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DifficultyHandler.class, remap = false)
public abstract class MixinDifficultyHandler {
    /**
     * @author RisingInIris2017
     * @reason Remove the unused ImprovedMobs difficulty display
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void showDifficulty(RenderGameOverlayEvent.Post e) {}
}
