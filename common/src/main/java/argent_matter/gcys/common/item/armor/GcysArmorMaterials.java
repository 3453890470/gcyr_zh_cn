package argent_matter.gcys.common.item.armor;

import argent_matter.gcys.common.data.GcysItems;
import lombok.Getter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum GcysArmorMaterials implements ArmorMaterial {
    SPACE("gcys:space", 5, new int[]{3, 6, 8, 3}, 5, SoundEvents.ARMOR_EQUIP_DIAMOND, 0, 0, () -> Ingredient.of(GcysItems.SPACE_FABRIC.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    @Getter
    private final String name;
    @Getter
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    @Getter
    private final int enchantmentValue;
    @Getter
    private final SoundEvent equipSound;
    @Getter
    private final float toughness;
    @Getter
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    GcysArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}