package com.anthonymendez.items;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.item.Item;

public class SimpleTools {
  public static final Supplier<Item> EMERALD_PICKAXE =
      Suppliers.memoize(() -> new SimplePickaxeTool(SimpleToolMaterials.EMERALD));

  public static final Supplier<Item> QUARTZ_PICKAXE =
      Suppliers.memoize(() -> new SimplePickaxeTool(SimpleToolMaterials.QUARTZ));

  /** Contains a list of all {@link SimpleTools}. */
  public static final List<Supplier<Item>> ALL_TOOLS =
      ImmutableList.<Supplier<Item>>builder()
          .add(EMERALD_PICKAXE)
          .add(QUARTZ_PICKAXE)
          .build();
}
