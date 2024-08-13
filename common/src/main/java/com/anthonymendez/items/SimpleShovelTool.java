package com.anthonymendez.items;

import com.mojang.datafixers.util.Pair;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/** Extension of {@link ShovelItem} to allow for more customization and behaviour in the future. */
public class SimpleShovelTool extends ShovelItem implements ISimpleTool {
  public static final float BASE_ATTACK_DAMAGE = 2.0F;
  public static final float BASE_ATTACK_SPEED = 1.0F;

  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimpleShovelTool} with the given {@link ToolMaterial}. A default {@link
   * Settings} is used.
   */
  public SimpleShovelTool(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromShovelItem(
            this, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    this.attackDamage = attackDamageAndSpeed.getFirst();
    this.attackSpeed = attackDamageAndSpeed.getSecond();
  }

  /** Returns this current instance as an {@link ItemStack}. */
  public ItemStack toItemStack() {
    return new ItemStack(this);
  }

  /**
   * Appends damage and speed to the {@link TooltipContext} of the item. Stats are shown based on
   * default Minecraft standards.
   *
   * <p>TODO(Anthony Mendez): Figure out if we can just pull the default Minecraft mining tool item
   * tooltip.
   */
  @Override
  public void appendTooltip(
      ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
    super.appendTooltip(stack, context, tooltip, type);
    SimpleToolUtils.AppendDefaultMinecraftMiningItemTooltip(tooltip, attackDamage, attackSpeed);
  }

  /** Overrides attack damage and returns the base damage calculated in the constructor. */
  @Override
  public float getBonusAttackDamage(
      Entity target, float baseAttackDamage, DamageSource damageSource) {
    return attackDamage;
  }
}
