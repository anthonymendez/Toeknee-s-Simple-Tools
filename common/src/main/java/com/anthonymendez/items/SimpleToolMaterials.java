package com.anthonymendez.items;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

/**
 * Enum describing {@link ToolMaterial}s. Similar in structure to {@link
 * net.minecraft.item.ToolMaterials}.
 */
public enum SimpleToolMaterials implements ToolMaterial {
  // Stone variants, same as cobblestone :)
  // Copper is slightly better than stone.
  COPPER(
      BlockTags.INCORRECT_FOR_STONE_TOOL,
      200,
      5.0F,
      1.5F,
      14,
      createRepairIngredientSupplier(Items.COPPER_INGOT)),
  // Harder than iron, but about +150 more durability.
  EMERALD(
      BlockTags.INCORRECT_FOR_IRON_TOOL,
      400,
      14F,
      2.75F,
      25,
      createRepairIngredientSupplier(Items.EMERALD)),
  // Faster and more durable than iron.
  QUARTZ(
      BlockTags.INCORRECT_FOR_IRON_TOOL,
      750,
      7F,
      2.5F,
      22,
      createRepairIngredientSupplier(Items.QUARTZ_BLOCK));

  private final TagKey<Block> inverseTag;
  private final int itemDurability;
  private final float miningSpeed;
  private final float attackDamage;
  private final int enchantability;
  private final Supplier<Ingredient> repairIngredient;

  SimpleToolMaterials(
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

  /** All {@link SimpleToolMaterials} in an {@link ImmutableList}. */
  public static final ImmutableList<SimpleToolMaterials> ALL_SIMPLE_TOOL_MATERIALS =
      ImmutableList.<SimpleToolMaterials>builder().add(COPPER).add(EMERALD).add(QUARTZ).build();

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
    return this.repairIngredient.get();
  }
}
