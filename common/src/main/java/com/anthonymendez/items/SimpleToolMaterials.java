package com.anthonymendez.items;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Enum describing {@link ToolMaterial}s. Similar in structure to {@link
 * net.minecraft.item.ToolMaterials}.
 */
public enum SimpleToolMaterials implements ToolMaterial {
  // Stone variants, same as cobblestone :)
  // Harder than iron, but about +150 more durability.
  EMERALD(
      BlockTags.INCORRECT_FOR_IRON_TOOL,
      400,
      14F,
      3.75F,
      25,
      createRepairIngredientSupplier(Items.EMERALD)),
  // Faster and more durable than iron.
  NETHER_QUARTZ(
      BlockTags.INCORRECT_FOR_IRON_TOOL,
      750,
      7F,
      3.5F,
      22,
      createRepairIngredientSupplier(Items.QUARTZ_BLOCK));

  private final TagKey<Block> inverseTag;
  private final int itemDurability;
  private final float miningSpeed;
  private final float attackDamage;
  private final int enchantability;
  private final Supplier<Ingredient> repairIngredient;

  private SimpleToolMaterials(
      final TagKey<Block> inverseTag,
      final int itemDurability,
      final float miningSpeed,
      final float attackDamage,
      final int enchantability,
      final Supplier<Ingredient> repairIngredient) {
    this.inverseTag = inverseTag;
    this.itemDurability = itemDurability;
    this.miningSpeed = miningSpeed;
    this.attackDamage = attackDamage;
    this.enchantability = enchantability;
    Objects.requireNonNull(repairIngredient);
    this.repairIngredient = Suppliers.memoize(repairIngredient::get);
  }

  /** Provides {@link Supplier} for {@link Ingredient} when provided the list of {@link Item}. */
  public static Supplier<Ingredient> createRepairIngredientSupplier(Item... items) {
    return () -> {
      return Ingredient.ofItems(
          Arrays.stream(items).map(item -> (ItemConvertible) item).toArray(ItemConvertible[]::new));
    };
  }

  public int getDurability() {
    return this.itemDurability;
  }

  public float getMiningSpeedMultiplier() {
    return this.miningSpeed;
  }

  public float getAttackDamage() {
    return this.attackDamage;
  }

  public TagKey<Block> getInverseTag() {
    return this.inverseTag;
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public Ingredient getRepairIngredient() {
    return (Ingredient) this.repairIngredient.get();
  }
}
