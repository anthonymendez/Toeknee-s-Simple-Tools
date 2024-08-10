package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleTools;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

public final class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ToekneeSimpleTools.MOD_ID, RegistryKeys.ITEM);

  public static RegistrySupplier<Item> register(Item item, String name) {
    return ITEMS.register(name, () -> item);
  }

  public static final RegistrySupplier<Item> EMERALD_PICKAXE_SUPPLIER =
      register(SimpleTools.EMERALD_PICKAXE, "emerald_pickaxe");

  public static final RegistrySupplier<Item> NETHER_QUARTZ_PICKAXE =
      register(SimpleTools.NETHER_QUARTZ_PICKAXE, "nether_quartz_pickaxe");
}
