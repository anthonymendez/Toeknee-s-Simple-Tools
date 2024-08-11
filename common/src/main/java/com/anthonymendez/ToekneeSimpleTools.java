package com.anthonymendez;

import com.anthonymendez.init.ModItemGroups;
import com.anthonymendez.init.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ToekneeSimpleTools {
  public static final String MOD_ID = "toeknees_simple_tools";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  /** Initializes the mod. */
  public static void init() {
    LOGGER.info("Initializing Toeknee's Simple Tools :)");

    ModItems.ITEMS.register();
    ModItemGroups.ITEM_GROUPS.register();
  }
}
