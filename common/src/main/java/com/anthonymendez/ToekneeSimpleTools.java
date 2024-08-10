package com.anthonymendez;

import com.anthonymendez.init.ModItemGroups;
import com.anthonymendez.init.ModItems;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ToekneeSimpleTools {
  public static final String MOD_ID = "toeknees_simple_tools";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  /** Registry manager for handling anything relating to the Minecraft registry. */
  public static final Supplier<RegistrarManager> MANAGER =
      Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

  /** Initializes the mod. */
  public static void init() {
    LOGGER.info("Initializing Toeknee's Simple Tools :)");

    ModItems.ITEMS.register();
    ModItemGroups.ITEM_GROUPS.register();
  }
}
