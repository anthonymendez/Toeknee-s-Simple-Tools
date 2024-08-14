package com.anthonymendez.fabric.datageneration;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.datagenerator.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;

public class FabricDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(
      net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator fabricDataGenerator) {
    ToekneeSimpleTools.LOGGER.info("Running Data Generator Init");
    net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack pack =
        fabricDataGenerator.createPack();
    pack.addProvider(ModRecipeProvider::new);
  }
}
