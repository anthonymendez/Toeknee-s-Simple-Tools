package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleTools;
import com.anthonymendez.items.SimpleToolset;
import com.google.common.collect.ImmutableList;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

public final class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ToekneeSimpleTools.MOD_ID, RegistryKeys.ITEM);

  /** Registers the given {@link Item} to the deferred mod registry. */
  public static RegistrySupplier<Item> registerItem(Supplier<Item> itemSupplier, String name) {
    return ITEMS.register(name, itemSupplier);
  }

  /** Registers each {@link Item} in a {@link SimpleToolset} to the deferred mod registry. */
  public static ImmutableList<RegistrySupplier<Item>> registerToolset(SimpleToolset toolset) {
    return ImmutableList.<RegistrySupplier<Item>>builder()
        .add(registerItem(toolset.pickaxeTool, String.format("%s_pickaxe", toolset.toolNamePrefix)))
        .add(registerItem(toolset.axeTool, String.format("%s_axe", toolset.toolNamePrefix)))
        .add(registerItem(toolset.shovelTool, String.format("%s_shovel", toolset.toolNamePrefix)))
        .add(registerItem(toolset.swordItem, String.format("%s_sword", toolset.toolNamePrefix)))
        .add(registerItem(toolset.hoeItem, String.format("%s_hoe", toolset.toolNamePrefix)))
        .build();
  }

  public static final ImmutableList<RegistrySupplier<Item>> EMERALD_TOOLSET_SUPPLIER_LIST =
      registerToolset(SimpleTools.EMERALD_TOOLSET);
  public static final ImmutableList<RegistrySupplier<Item>> QUARTZ_TOOLSET_SUPPLIER_LIST =
      registerToolset(SimpleTools.QUARTZ_TOOLSET);
  public static final ImmutableList<RegistrySupplier<Item>> COPPER_TOOLSET_SUPPLIER_LIST =
      registerToolset(SimpleTools.COPPER_TOOLSET);
  /** Contains a RegistrySupplier list of all toolsets created. */
  public static final ImmutableList<RegistrySupplier<Item>> ALL_TOOLSET_SUPPLIER_LIST =
      ImmutableList.<RegistrySupplier<Item>>builder()
          .addAll(EMERALD_TOOLSET_SUPPLIER_LIST)
          .addAll(QUARTZ_TOOLSET_SUPPLIER_LIST)
          .addAll(COPPER_TOOLSET_SUPPLIER_LIST)
          .build();
}
