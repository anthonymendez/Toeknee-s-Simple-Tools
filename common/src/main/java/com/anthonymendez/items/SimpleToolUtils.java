package com.anthonymendez.items;

import com.mojang.datafixers.util.Pair;
import java.text.DecimalFormat;
import java.util.List;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class SimpleToolUtils {

  private static final DecimalFormat TOOLTIP_FORMAT = new DecimalFormat("#0.#");

  /**
   * Retrieves the attack damage and speed from a {@link MiningToolItem} in a {@link Pair} whereby
   * the order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromMiningToolItem(
      MiningToolItem miningToolItem, float baseAttackDamage, float baseAttackSpeed) {
    List<AttributeModifiersComponent.Entry> modifiers =
        MiningToolItem.createAttributeModifiers(
                miningToolItem.getMaterial(), baseAttackDamage, baseAttackSpeed)
            .modifiers();
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(modifiers);
  }

  /**
   * Retrieves the attack damage and speed from a {@link MiningToolItem} in a {@link Pair} whereby
   * the order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromSwordItem(
      SwordItem swordItem, float baseAttackDamage, float baseAttackSpeed) {
    List<AttributeModifiersComponent.Entry> modifiers =
        SwordItem.createAttributeModifiers(
                swordItem.getMaterial(), (int) baseAttackDamage, baseAttackSpeed)
            .modifiers();
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(modifiers);
  }

  /**
   * Retrieves the attack damage and speed from a {@link ShovelItem} in a {@link Pair} whereby the
   * order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromShovelItem(
      ShovelItem shovelItem, float baseAttackDamage, float baseAttackSpeed) {
    List<AttributeModifiersComponent.Entry> modifiers =
        ShovelItem.createAttributeModifiers(
                shovelItem.getMaterial(), (int) baseAttackDamage, baseAttackSpeed)
            .modifiers();
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(modifiers);
  }

  /**
   * Retrieves the attack damage and speed from a {@link HoeItem} in a {@link Pair} whereby the
   * order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromHoeItem(
      HoeItem hoeItem, float baseAttackDamage, float baseAttackSpeed) {
    List<AttributeModifiersComponent.Entry> modifiers =
        HoeItem.createAttributeModifiers(
                hoeItem.getMaterial(), (int) baseAttackDamage, baseAttackSpeed)
            .modifiers();
    // TODO(Anthony Mendez): Scale hoe speed manually  Shere as well.
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(modifiers);
  }

  /**
   * Retrieves the attack damage and speed from a {@link AxeItem} in a {@link Pair} whereby the
   * order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromAxeItem(
      AxeItem axeItem, float baseAttackDamage, float baseAttackSpeed) {
    List<AttributeModifiersComponent.Entry> modifiers =
        AxeItem.createAttributeModifiers(
                axeItem.getMaterial(), (int) baseAttackDamage, baseAttackSpeed)
            .modifiers();
    // Manually set damage here as Axe doesn't seem to scale like swords.
    // TODO(Anthony Mendez): Create damage table.
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(modifiers);
  }

  /**
   * Retrieves the attack damage and speed from a {@link MiningToolItem} in a {@link Pair} whereby
   * the order is:
   *
   * <p>* {@code Pair.of(attackDamage, attackSpeed)}
   *
   * <p>Base attack damage and speed need to be provided as it's dependent off the weapon.
   */
  public static Pair<Float, Float> getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(
      List<AttributeModifiersComponent.Entry> modifiers) {
    float attackDamage =
        getValueOfIdentifierFromAttributeModifiersComponentEntryList(
            modifiers, MiningToolItem.BASE_ATTACK_DAMAGE_MODIFIER_ID);
    float attackSpeed =
        getValueOfIdentifierFromAttributeModifiersComponentEntryList(
            modifiers, MiningToolItem.BASE_ATTACK_SPEED_MODIFIER_ID);
    return Pair.of(attackDamage, attackSpeed);
  }

  /**
   * Retrieves value of an {@link Identifier} from a {@link List} of {@link
   * AttributeModifiersComponent.Entry}.
   */
  public static float getValueOfIdentifierFromAttributeModifiersComponentEntryList(
      List<AttributeModifiersComponent.Entry> entryList, Identifier identifier) {
    return (float)
        entryList.stream()
            .map(AttributeModifiersComponent.Entry::modifier)
            .filter(modifier -> modifier.idMatches(identifier))
            .findFirst()
            .orElseThrow()
            .value();
  }

  /**
   * Retrieves the {@link EntityAttributeModifier.Operation} of a {@link Identifier} from a {@link
   * List} of {@link AttributeModifiersComponent.Entry}.
   */
  public static EntityAttributeModifier.Operation
      getOperationOfIdentifierFromAttributeModifiersComponentEntryList(
          List<AttributeModifiersComponent.Entry> entryList, Identifier identifier) {
    return entryList.stream()
        .map(AttributeModifiersComponent.Entry::modifier)
        .filter(modifier -> modifier.idMatches(identifier))
        .findFirst()
        .orElseThrow()
        .operation();
  }

  /**
   * Appends damage and speed to the {@link net.minecraft.item.Item.TooltipContext} of the item.
   * Stats are shown based on default Minecraft standards.
   */
  public static void AppendDefaultMinecraftMiningItemTooltip(
      List<Text> tooltip, float attackDamage, float attackSpeed) {
    tooltip.add(Text.of(""));
    tooltip.add(Text.translatable("When in Main Hand:").formatted(Formatting.GRAY));
    tooltip.add(
        Text.translatable(String.format(" %s Attack Damage", TOOLTIP_FORMAT.format(attackDamage)))
            .formatted(Formatting.DARK_GREEN));
    tooltip.add(
        Text.translatable(String.format(" %s Attack Speed", TOOLTIP_FORMAT.format(attackSpeed)))
            .formatted(Formatting.DARK_GREEN));
  }
}
