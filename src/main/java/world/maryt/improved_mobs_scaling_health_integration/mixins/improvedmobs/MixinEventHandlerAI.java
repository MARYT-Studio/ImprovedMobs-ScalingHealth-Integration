package world.maryt.improved_mobs_scaling_health_integration.mixins.improvedmobs;

import com.flemmli97.improvedmobs.handler.EventHandlerAI;
import com.flemmli97.improvedmobs.handler.config.ConfigHandler;
import com.flemmli97.improvedmobs.handler.helper.GeneralHelperMethods;
import com.flemmli97.improvedmobs.handler.helper.IMAttributes;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EventHandlerAI.class, remap = false)
public abstract class MixinEventHandlerAI {
    /**
     * @author RisingInIris2017
     * @reason ScalingHealth won't modify mobs' attributes when this mod is present,
     *         so caring about `useScalingHealthMod` is not needed.
     */
    @Overwrite
    private void applyAttributesAndItems(EntityLiving living) {
        if (living.getEntityData().hasKey("improvedmobs:InitArmor") && !living.getEntityData().getBoolean("improvedmobs:InitArmor")) {
            GeneralHelperMethods.equipArmor(living);
            living.getEntityData().setBoolean("improvedmobs:InitArmor", true);
        }

        if (living.getEntityData().hasKey("improvedmobs:InitHeld") && !living.getEntityData().getBoolean("improvedmobs:InitHeld")) {
            GeneralHelperMethods.equipHeld(living);
            living.getEntityData().setBoolean("improvedmobs:InitHeld", true);
        }

        if (ConfigHandler.baseEnchantChance != 0.0F && !living.getEntityData().hasKey("improvedmobs:Enchanted")) {
            GeneralHelperMethods.enchantGear(living);
            living.getEntityData().setBoolean("improvedmobs:Enchanted", true);
        }

        if (living.getEntityData().hasKey("improvedmobs:InitAttr") && !living.getEntityData().getBoolean("improvedmobs:InitAttr")) {
            if (ConfigHandler.healthIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, SharedMonsterAttributes.MAX_HEALTH, 0.016D * ConfigHandler.healthIncrease, ConfigHandler.healthMax, true);
                living.setHealth(living.getMaxHealth());
            }

            if (ConfigHandler.damageIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, SharedMonsterAttributes.ATTACK_DAMAGE, 0.008 * ConfigHandler.damageIncrease, ConfigHandler.damageMax, true);
            }

            if (ConfigHandler.speedIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, SharedMonsterAttributes.MOVEMENT_SPEED, 8.0E-4 * ConfigHandler.speedIncrease, ConfigHandler.speedMax, false);
            }

            if (ConfigHandler.knockbackIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 0.002 * ConfigHandler.knockbackIncrease, ConfigHandler.knockbackMax, false);
            }

            if (ConfigHandler.magicResIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, IMAttributes.MAGIC_RES, 0.0016 * ConfigHandler.magicResIncrease, ConfigHandler.magicResMax, false);
            }

            if (ConfigHandler.projectileIncrease != 0.0F) {
                GeneralHelperMethods.modifyAttr(living, IMAttributes.PROJ_BOOST, 0.008 * ConfigHandler.projectileIncrease, ConfigHandler.projectileMax, false);
            }

            living.getEntityData().setBoolean("improvedmobs:InitAttr", true);
        }

    }

}
