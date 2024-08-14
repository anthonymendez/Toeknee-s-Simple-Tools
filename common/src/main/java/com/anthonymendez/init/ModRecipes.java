package com.anthonymendez.init;

import com.anthonymendez.items.ISimpleTool;
import com.anthonymendez.items.SimpleTools;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.function.Supplier;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;

public class ModRecipes {
  /**
   * Returns a list of all {@link CraftingRecipeJsonBuilder} from all {@link SimpleTools} that have
   * been registered.
   */
  public static final ImmutableList<CraftingRecipeJsonBuilder> ALL_RECIPES =
      ModItems.ALL_TOOLSET_SUPPLIER_LIST.stream()
          .map(Supplier::get)
          .map(item -> (ISimpleTool) item)
          .map(ISimpleTool::createCraftingRecipe)
          .flatMap(Collection::stream)
          .collect(ImmutableList.toImmutableList());
}
