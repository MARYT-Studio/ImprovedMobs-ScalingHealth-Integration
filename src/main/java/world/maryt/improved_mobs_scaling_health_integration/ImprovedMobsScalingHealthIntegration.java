package world.maryt.improved_mobs_scaling_health_integration;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = ImprovedMobsScalingHealthIntegration.MOD_ID, name = ImprovedMobsScalingHealthIntegration.NAME, version = ImprovedMobsScalingHealthIntegration.VERSION, dependencies = ImprovedMobsScalingHealthIntegration.DEPENDENCIES)
public class ImprovedMobsScalingHealthIntegration {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "ImprovedMobs ScalingHealth Integration";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:improvedmobs;required-after:scalinghealth;required-after:mixinbooter";
}
