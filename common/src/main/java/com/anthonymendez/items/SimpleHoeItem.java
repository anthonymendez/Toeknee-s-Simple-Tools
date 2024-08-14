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

public class SimpleHoeItem extends HoeItem implements ISimpleTool {
  private final float attackDamage;
  private final float attackSpeed;

  public static final float BASE_ATTACK_DAMAGE = 1.0F;
  public static final float BASE_ATTACK_SPEED = 1.0F;

  /**
   * Creates new {@link SimpleHoeItem} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimpleHoeItem(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromHoeItem(
            this, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    this.attackDamage = BASE_ATTACK_DAMAGE;
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

  /**
   * Returns the bonus attack speed based on the {@link SimpleToolMaterials}.
   *
   * <p>TODO(Anthony Mendez): Figure out what the actual attack speed of the hoe is. Too fast to be
   * 1.0F.
   */
  //  public float getBonusAttackSpeed() {
  //    return switch (getMaterial()) {
  //      case SimpleToolMaterials.EMERALD, SimpleToolMaterials.QUARTZ -> 2.0F;
  //      case SimpleToolMaterials.COPPER -> 1.0F;
  //      default -> 0.0F;
  //    };
  //  }

  @Override
  public List<CraftingRecipeJsonBuilder> createCraftingRecipe() {
    Item recipeItem =
        SimpleToolUtils.getSingleItemFromIngredient(getMaterial().getRepairIngredient());

    return ImmutableList.of(
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, this)
            .pattern("ii ")
            .pattern(" s ")
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
