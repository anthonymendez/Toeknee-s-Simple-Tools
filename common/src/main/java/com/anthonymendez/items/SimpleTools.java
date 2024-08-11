package com.anthonymendez.items;

import com.google.common.collect.ImmutableList;
import java.util.List;

public class SimpleTools {

  public static final SimpleToolset EMERALD_TOOLSET =
      new SimpleToolset(SimpleToolMaterials.EMERALD, "emerald");
  public static final SimpleToolset QUARTZ_TOOLSET =
      new SimpleToolset(SimpleToolMaterials.QUARTZ, "quartz");
  public static final SimpleToolset COPPER_TOOLSET =
      new SimpleToolset(SimpleToolMaterials.COPPER, "copper");

  /** Contains a list of all {@link SimpleTools}. */
  public static final List<SimpleToolset> ALL_TOOLS =
      ImmutableList.<SimpleToolset>builder()
          .add(EMERALD_TOOLSET)
          .add(QUARTZ_TOOLSET)
          .add(COPPER_TOOLSET)
          .build();
}
