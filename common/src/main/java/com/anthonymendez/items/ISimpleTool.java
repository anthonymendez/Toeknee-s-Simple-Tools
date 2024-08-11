package com.anthonymendez.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public interface ISimpleTool {

  /** Returns this current instance as an {@link ItemStack}. */
  public ItemStack toItemStack();

  /**
   * Appends damage and speed to the {@link net.minecraft.item.Item.TooltipContext} of the item.
   * Stats are shown based on default Minecraft standards.
   *
   * <p>TODO(Anthony Mendez): Figure out if we can just pull the default Minecraft mining tool item
   * tooltip.
   */
  public void appendTooltip(
      ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type);
}
