package com.anthonymendez.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

/** Extension of {@link PickaxeItem} to allow for more customization and behaviour in the future. */
public class SimplePickaxeTool extends PickaxeItem {
  /**
   * Creates new {@link SimplePickaxeTool} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimplePickaxeTool(ToolMaterial material) {
    super(material, new Settings());
  }

  /**
   * Creates new {@link SimplePickaxeTool} with the given {@link ToolMaterial} and {@link
   * net.minecraft.item.Item.Settings}.
   */
  public SimplePickaxeTool(ToolMaterial material, Settings settings) {
    super(material, settings);
  }

  /** Returns this current instance as an {@link ItemStack}. */
  public ItemStack toItemStack() {
    return new ItemStack(this);
  }
}
