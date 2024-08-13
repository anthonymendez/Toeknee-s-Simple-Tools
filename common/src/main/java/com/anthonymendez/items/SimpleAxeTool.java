package com.anthonymendez.items;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/** Extension of {@link AxeItem} to allow for more customization and behaviour in the future. */
public class SimpleAxeTool extends AxeItem implements ISimpleTool {
  public static final float BASE_ATTACK_DAMAGE = 7.0F;
  public static final float BASE_ATTACK_SPEED = 0.8F;

  private final float attackDamage;
  private final float attackSpeed;

  /**
   * Creates new {@link SimpleAxeTool} with the given {@link ToolMaterial}. A default {@link
   * Settings} is used.
   */
  public SimpleAxeTool(ToolMaterial material) {
    super(material, new Settings());
    Pair<Float, Float> attackDamageAndSpeed =
        SimpleToolUtils.getAttackDamageAndSpeedFromAxeItem(
            this, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    // Manually setting attack damage to match other axes.
    this.attackDamage =
        (switch (material) {
          case SimpleToolMaterials.COPPER,
                  SimpleToolMaterials.EMERALD,
                  SimpleToolMaterials.QUARTZ ->
              9.0F;
          default -> BASE_ATTACK_DAMAGE;
        });
    this.attackSpeed = attackDamageAndSpeed.getSecond() + getBonusAttackSpeed();
  }

  /** Returns this current instance as an {@link ItemStack}. */
  @Override
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

  /**
   * Returns the equivalent vanilla Axe damage for the tool.
   *
   * <p>Axe damage seems to be done manually so we won't mess with it.
   */
  @Override
  public float getBonusAttackDamage(
      Entity target, float baseAttackDamage, DamageSource damageSource) {
    return attackDamage;
  }

  /** Returns the bonus attack speed based on the {@link SimpleToolMaterials}. */
  public float getBonusAttackSpeed() {
    return switch (getMaterial()) {
      case SimpleToolMaterials.EMERALD, SimpleToolMaterials.QUARTZ -> 0.2F;
      case SimpleToolMaterials.COPPER -> 0.1F;
      default -> 0.0F;
    };
  }
}
