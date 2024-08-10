package com.anthonymendez.neoforge;

import com.anthonymendez.ToekneeSimpleTools;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.IModBusEvent;

@Mod(ToekneeSimpleTools.MOD_ID)
public final class ToekneeSimpleToolsNeoForge {

  public ToekneeSimpleToolsNeoForge() {
    // Run our common setup.
    ToekneeSimpleTools.init();

  }
}
