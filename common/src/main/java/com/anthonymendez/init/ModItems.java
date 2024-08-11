package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleTools;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

public final class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ToekneeSimpleTools.MOD_ID, RegistryKeys.ITEM);

  /** Registers the given {@link Item} to the deferred mod registry. */
  public static RegistrySupplier<Item> register(Supplier<Item> itemSupplier, String name) {
    return ITEMS.register(name, itemSupplier);
  }

  public static final RegistrySupplier<Item> EMERALD_PICKAXE_SUPPLIER =
      register(SimpleTools.EMERALD_PICKAXE, "emerald_pickaxe");

  public static final RegistrySupplier<Item> QUARTZ_PICKAXE_SUPPLIER =
      register(SimpleTools.QUARTZ_PICKAXE, "quartz_pickaxe");
}
