package com.anthonymendez.items;

import com.anthonymendez.ToekneeSimpleTools;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.function.Supplier;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.item.Item;

/** Class to handle creating and retrieving newly created tools. */
public class SimpleToolset {

  public final SimpleToolMaterials material;
  public final String toolNamePrefix;
  public final Supplier<Item> pickaxeTool;
  public final Supplier<Item> axeTool;
  public final Supplier<Item> shovelTool;
  public final Supplier<Item> swordItem;
  public final Supplier<Item> hoeItem;
  public final ImmutableList<Supplier<Item>> allTools;
  public ImmutableList<CraftingRecipeJsonBuilder> allRecipes;

  public SimpleToolset(SimpleToolMaterials material, String toolNamePrefix) {
    this.material = material;
    this.toolNamePrefix = toolNamePrefix;
    pickaxeTool = Suppliers.memoize(() -> new SimplePickaxeTool(material));
    axeTool = Suppliers.memoize(() -> new SimpleAxeTool(material));
    shovelTool = Suppliers.memoize(() -> new SimpleShovelTool(material));
    swordItem = Suppliers.memoize(() -> new SimpleSwordItem(material));
    hoeItem = Suppliers.memoize(() -> new SimpleHoeItem(material));
    allTools =
        ImmutableList.<Supplier<Item>>builder()
            .add(pickaxeTool)
            .add(axeTool)
            .add(shovelTool)
            .add(swordItem)
            .add(hoeItem)
            .build();
  }

  /** Only call once the toolset has been registered. */
  public void setRecipes() {
    if (!allRecipes.isEmpty()) {
      ToekneeSimpleTools.LOGGER.warn("Set Recipes has already been called!");
      return;
    }

    allRecipes =
        allTools.stream()
            .map(supplier -> (ISimpleTool) supplier.get())
            .map(ISimpleTool::createCraftingRecipe)
            .flatMap(Collection::stream)
            .collect(ImmutableList.toImmutableList());
  }
}
