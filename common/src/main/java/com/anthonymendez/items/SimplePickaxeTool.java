package com.anthonymendez.items;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/** Extension of {@link PickaxeItem} to allow for more customization and behaviour in the future. */
public class SimplePickaxeTool extends PickaxeItem implements ISimpleTool {
  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimplePickaxeTool} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimplePickaxeTool(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromMiningToolItem(this, 2.0F, 1.2F);
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
}
