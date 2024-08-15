package com.anthonymendez.neoforge.datageneration;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.datagenerator.ModRecipeProvider;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ToekneeSimpleTools.MOD_ID)
public class NeoForgeDataGenerator {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent gatherDataEvent) throws IOException {
    ToekneeSimpleTools.LOGGER.info("Running Data Generator Init");

    DataGenerator dataGenerator = gatherDataEvent.getGenerator();
    DataOutput dataOutput = dataGenerator.getPackOutput();
    CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider =
        gatherDataEvent.getLookupProvider();

    dataGenerator.addProvider(
        gatherDataEvent.includeServer(), new ModRecipeProvider(dataOutput, lookupProvider));
    dataGenerator.run();
  }
}
