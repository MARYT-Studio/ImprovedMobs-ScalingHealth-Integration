package world.maryt.improved_mobs_scaling_health_integration.mixins.improvedmobs;

import com.flemmli97.improvedmobs.handler.DifficultyData;
import com.flemmli97.improvedmobs.handler.config.ConfigHandler;
import com.flemmli97.improvedmobs.handler.config.EquipmentList;
import com.flemmli97.improvedmobs.handler.helper.GeneralHelperMethods;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GeneralHelperMethods.class, remap = false)
public abstract class MixinGeneralHelperMethods {
    /**
     * @author RisingInIris2017
     * @reason Mark mob's equipments with an NBT tag, if shouldDropEquip is false.
     */
    @Overwrite
    public static void equipArmor(EntityLiving living) {
        if (ConfigHandler.baseEquipChance != 0.0F) {
            float time = DifficultyData.getDifficulty(living.world, living) * ConfigHandler.diffEquipAdd * 0.01F;
            if (living.getRNG().nextFloat() < ConfigHandler.baseEquipChance + time) {
                EntityEquipmentSlot[] var2 = EntityEquipmentSlot.values();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    EntityEquipmentSlot slot = var2[var4];
                    if (slot.getSlotType() != EntityEquipmentSlot.Type.HAND) {
                        boolean shouldAdd = slot == EntityEquipmentSlot.HEAD || ConfigHandler.baseEquipChanceAdd != 0.0F && living.getRNG().nextFloat() < ConfigHandler.baseEquipChanceAdd + time;
                        if (shouldAdd && living.getItemStackFromSlot(slot).isEmpty()) {
                            ItemStack equip = EquipmentList.getEquip(living, slot);
                            if (!equip.isEmpty()) {
                                if (!ConfigHandler.shouldDropEquip) {
                                    equip.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                                    equip.getTagCompound().setBoolean("isMobEquipment", true);
                                }

                                living.setItemStackToSlot(slot, equip);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * @author RisingInIris2017
     * @reason Mark mob's equipments with an NBT tag, if shouldDropEquip is false.
     */
    @Overwrite
    public static void equipHeld(EntityLiving living) {
        float add = DifficultyData.getDifficulty(living.world, living) * ConfigHandler.diffWeaponChance * 0.01F;
        ItemStack stack;
        if (ConfigHandler.baseWeaponChance != 0.0F && living.getRNG().nextFloat() < ConfigHandler.baseWeaponChance + add && living.getHeldItemMainhand().isEmpty()) {
            stack = EquipmentList.getEquip(living, EntityEquipmentSlot.MAINHAND);
            if (!ConfigHandler.shouldDropEquip) {
                stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                stack.getTagCompound().setBoolean("isMobEquipment", true);
            }

            living.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
        }

        add = DifficultyData.getDifficulty(living.world, living) * ConfigHandler.diffItemChanceAdd * 0.01F;
        if (ConfigHandler.baseItemChance != 0.0F && living.getRNG().nextFloat() < ConfigHandler.baseItemChance + add && living.getHeldItemOffhand().isEmpty()) {
            stack = EquipmentList.getEquip(living, EntityEquipmentSlot.OFFHAND);
            if (!ConfigHandler.shouldDropEquip) {
                stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                stack.getTagCompound().setBoolean("isMobEquipment", true);
            }

            living.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, stack);
        }

    }
}
