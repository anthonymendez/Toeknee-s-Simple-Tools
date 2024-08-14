package com.anthonymendez.neoforge.datageneration;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.datagenerator.ModRecipeProvider;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ToekneeSimpleTools.MOD_ID)
public class NeoForgeDataGenerator {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent gatherDataEvent) {
    ToekneeSimpleTools.LOGGER.info("Running Data Generator Init");
    gatherDataEvent
        .getGenerator()
        .addProvider(
            gatherDataEvent.includeServer(),
            (DataProvider.Factory<ModRecipeProvider>)
                output ->
                    new ModRecipeProvider(
                        gatherDataEvent.getGenerator().getPackOutput(),
                        gatherDataEvent.getLookupProvider()));
  }
}
