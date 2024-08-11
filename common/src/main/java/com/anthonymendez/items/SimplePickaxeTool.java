package com.anthonymendez.items;

import java.util.List;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/** Extension of {@link PickaxeItem} to allow for more customization and behaviour in the future. */
public class SimplePickaxeTool extends PickaxeItem {
  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimplePickaxeTool} with the given {@link ToolMaterial}. A default {@link
   * net.minecraft.item.Item.Settings} is used.
   */
  public SimplePickaxeTool(ToolMaterial material) {
    super(material, new Settings());

    List<AttributeModifiersComponent.Entry> modifiers =
        createAttributeModifiers(material, 2.0F, 1.2F).modifiers();
    this.attackDamage =
        (float)
            modifiers.stream()
                .map(AttributeModifiersComponent.Entry::modifier)
                .filter(modifier -> modifier.idMatches(BASE_ATTACK_DAMAGE_MODIFIER_ID))
                .findFirst()
                .orElseThrow()
                .value();
    this.attackSpeed =
        (float)
            modifiers.stream()
                .map(AttributeModifiersComponent.Entry::modifier)
                .filter(modifier -> modifier.idMatches(BASE_ATTACK_SPEED_MODIFIER_ID))
                .findFirst()
                .orElseThrow()
                .value();
  }

  /** Returns this current instance as an {@link ItemStack}. */
  public ItemStack toItemStack() {
    return new ItemStack(this);
  }

  /** */
  @Override
  public void appendTooltip(
      ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
    super.appendTooltip(stack, context, tooltip, type);
    tooltip.add(Text.of(""));
    tooltip.add(Text.translatable("When in Main Hand:").formatted(Formatting.GRAY));
    tooltip.add(
        Text.translatable(String.format(" %.1f Attack Damage", this.attackDamage))
            .formatted(Formatting.DARK_GREEN));
    tooltip.add(
        Text.translatable(String.format(" %.1f Attack Speed", this.attackSpeed))
            .formatted(Formatting.DARK_GREEN));
  }
}
