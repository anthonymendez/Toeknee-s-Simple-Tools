package com.anthonymendez.neoforge;

import com.anthonymendez.ToekneeSimpleTools;
import net.neoforged.fml.common.Mod;

@Mod(ToekneeSimpleTools.MOD_ID)
public final class ToekneeSimpleToolsNeoForge {

  public ToekneeSimpleToolsNeoForge() {
    // Run our common setup.
    ToekneeSimpleTools.LOGGER.info("NeoForge Init for Toeknee's Simple Tools.");
    ToekneeSimpleTools.init();
  }
}
