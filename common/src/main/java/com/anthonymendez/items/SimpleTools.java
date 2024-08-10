package com.anthonymendez.items;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class SimpleTools {
  public static final SimplePickaxeTool EMERALD_PICKAXE =
      new SimplePickaxeTool(SimpleToolMaterials.EMERALD);
  public static final SimplePickaxeTool NETHER_QUARTZ_PICKAXE =
      new SimplePickaxeTool(SimpleToolMaterials.NETHER_QUARTZ);

  /** Contains a list of all {@link SimpleTools}. */
  public static final Item[] ALL_TOOLS = {EMERALD_PICKAXE, NETHER_QUARTZ_PICKAXE};

}
