package com.anthonymendez;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ToekneeSimpleTools {
  public static final String MOD_ID = "toeknees_simple_tools";

  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  // 1.19.4 and above
  public static final Supplier<RegistrarManager> MANAGER =
      Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

  /** Initializes the mod. */
  public static void init() {
    LOGGER.info("Initializing Toeknee's Simple Tools :)");

    registerItems();
  }

  /** Registers the items from this mod. */
  public static void registerItems() {
    LOGGER.info("Registering items.");

    Registrar<Item> items = MANAGER.get().get(RegistryKeys.ITEM);
    // Register items.
    RegistrySupplier<Item> exampleItem =
        items.register(Identifier.of(MOD_ID, "example_item"), () -> new Item(new Item.Settings()));

    LOGGER.info("Finished registering items.");
  }
}
