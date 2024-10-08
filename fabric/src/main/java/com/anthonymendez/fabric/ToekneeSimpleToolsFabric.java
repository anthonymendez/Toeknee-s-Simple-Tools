package com.anthonymendez.fabric;

import com.anthonymendez.ToekneeSimpleTools;
import net.fabricmc.api.ModInitializer;

public final class ToekneeSimpleToolsFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    // Run our common setup.
    ToekneeSimpleTools.LOGGER.debug("Fabric Init for Toeknee's Simple Tools.");
    ToekneeSimpleTools.init();
  }
}
