package com.anthonymendez.items;

import com.anthonymendez.datagenerator.ModRecipeProvider;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.text.Text;

/** Extension of {@link PickaxeItem} to allow for more customization and behaviour in the future. */
public class SimplePickaxeTool extends PickaxeItem implements ISimpleTool {
  public static final float BASE_ATTACK_DAMAGE = 2.0F;
  public static final float BASE_ATTACK_SPEED = 1.6F;

  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimplePickaxeTool} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimplePickaxeTool(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromMiningToolItem(
            this, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    this.attackDamage = attackDamageAndSpeed.getFirst();
    this.attackSpeed = attackDamageAndSpeed.getSecond();
  }

  /** Returns this current instance as an {@link ItemStack}. */
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
    SimpleToolUtils.AppendDefaultMinecraftMiningItemTooltip(
        tooltip, this.attackDamage, this.attackSpeed);
  }

  @Override
  public List<CraftingRecipeJsonBuilder> createCraftingRecipe() {
    Item recipeItem =
        SimpleToolUtils.getSingleItemFromIngredient(getMaterial().getRepairIngredient());

    return ImmutableList.of(
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, this)
            .pattern("iii")
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
