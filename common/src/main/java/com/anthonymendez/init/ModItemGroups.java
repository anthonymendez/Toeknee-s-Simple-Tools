package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleTools;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public final class ModItemGroups {
  public static final String ITEM_GROUP_NAME = "simple_tools";
  public static final String CREATIVE_TAB_DISPLAY_NAME = "Simple Tools";

  public static final DeferredRegister<ItemGroup> ITEM_GROUPS =
      DeferredRegister.create(ToekneeSimpleTools.MOD_ID, RegistryKeys.ITEM_GROUP);

  public static RegistrySupplier<ItemGroup> register(ItemGroup itemGroup, String name) {
    return ITEM_GROUPS.register(name, () -> itemGroup);
  }

  public static final ItemGroup SIMPLE_TOOLS_ITEM_GROUP =
      new ItemGroup.Builder(null, -1)
          .displayName(Text.of(CREATIVE_TAB_DISPLAY_NAME))
          .icon(() -> new ItemStack(SimpleTools.EMERALD_PICKAXE))
          .entries(
              (displayContext, entries) -> {
                for (Item item : SimpleTools.ALL_TOOLS) {
                  entries.add(item);
                }
              })
          .build();

  public static final RegistrySupplier<ItemGroup> SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER =
      register(SIMPLE_TOOLS_ITEM_GROUP, ITEM_GROUP_NAME);
}
