package com.anthonymendez.datagenerator;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.init.ModRecipes;
import java.util.concurrent.CompletableFuture;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;

public class ModRecipeProvider extends RecipeProvider {
  public ModRecipeProvider(
      DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
    super(output, registryLookupFuture);
  }

  @Override
  public void generate(RecipeExporter exporter) {
    ToekneeSimpleTools.LOGGER.info("Generating recipes.");
    ModRecipes.ALL_RECIPES.forEach(recipe -> recipe.offerTo(exporter));
  }

  /** Returns a string formatted as {@code "has_item"}. */
  public static String hasItem(Item item) {
    return hasItem((ItemConvertible) item);
  }

  /** Returns a {@link AdvancementCriterion} for building crafting recipes. */
  public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItem(
      Item item) {
    return conditionsFromItem((ItemConvertible) item);
  }
}
