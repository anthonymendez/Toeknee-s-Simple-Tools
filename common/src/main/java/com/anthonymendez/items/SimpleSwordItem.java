package com.anthonymendez.items;

import com.anthonymendez.datagenerator.ModRecipeProvider;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.text.Text;

public class SimpleSwordItem extends SwordItem implements ISimpleTool {
  public static final float BASE_ATTACK_DAMAGE = 4.0F;
  public static final float BASE_ATTACK_SPEED = 1.6F;

  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimpleSwordItem} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimpleSwordItem(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromSwordItem(
            this, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    this.attackDamage = attackDamageAndSpeed.getFirst();
    this.attackSpeed = attackDamageAndSpeed.getSecond();
  }

  /** Returns this current instance as an {@link ItemStack}. */
  @Override
  public ItemStack toItemStack() {
    return new ItemStack(this);
  }

  /**
   * Appends damage and speed to the {@link net.minecraft.item.Item.TooltipContext} of the item.
   * Stats are shown based on default Minecraft standards.
   *
   * <p>TODO(Anthony Mendez): Figure out if we can just pull the default Minecraft mining tool item
   * tooltip.
   */
  @Override
  public void appendTooltip(
      ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
    super.appendTooltip(stack, context, tooltip, type);
    SimpleToolUtils.AppendDefaultMinecraftMiningItemTooltip(tooltip, attackDamage, attackSpeed);
  }

  /** Overrides attack damage and returns the base damage calculated in the constructor. */
  @Override
  public float getBonusAttackDamage(
      Entity target, float baseAttackDamage, DamageSource damageSource) {
    return attackDamage;
  }

  @Override
  public List<CraftingRecipeJsonBuilder> createCraftingRecipe() {
    Item recipeItem =
        SimpleToolUtils.getSingleItemFromIngredient(getMaterial().getRepairIngredient());

    return ImmutableList.of(
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, this)
            .pattern(" i ")
            .pattern(" i ")
            .pattern(" s ")
            .input('i', recipeItem)
            .input('s', Items.STICK)
            .criterion(
                ModRecipeProvider.hasItem(recipeItem),
                ModRecipeProvider.conditionsFromItem(recipeItem))
            .criterion(
                ModRecipeProvider.hasItem(Items.STICK),
                ModRecipeProvider.conditionsFromItem(Items.STICK)));
  }
}
