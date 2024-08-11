package com.anthonymendez.items;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class SimpleToolUtils {

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
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(
        modifiers, baseAttackDamage, baseAttackSpeed);
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
                swordItem.getMaterial(), (int)baseAttackDamage, baseAttackSpeed)
            .modifiers();
    return getAttackDamageAndSpeedFromAttributeModifiersComponentEntryList(
        modifiers, baseAttackDamage, baseAttackSpeed);
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
      List<AttributeModifiersComponent.Entry> modifiers,
      float baseAttackDamage,
      float baseAttackSpeed) {
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
        Text.translatable(String.format(" %.1f Attack Damage", attackDamage))
            .formatted(Formatting.DARK_GREEN));
    tooltip.add(
        Text.translatable(String.format(" %.1f Attack Speed", attackSpeed))
            .formatted(Formatting.DARK_GREEN));
  }
}
