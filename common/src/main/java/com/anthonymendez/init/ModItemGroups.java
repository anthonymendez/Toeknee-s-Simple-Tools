package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleSwordItem;
import com.anthonymendez.items.SimpleTools;
import com.anthonymendez.items.SimpleToolset;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

import java.util.List;
import java.util.function.Supplier;
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

  /** Registers the given {@link ItemGroup} to the Simple Tools tab. */
  public static RegistrySupplier<ItemGroup> register(
      Supplier<ItemGroup> itemGroupSupplier, String name) {
    return ITEM_GROUPS.register(name, itemGroupSupplier);
  }

  /** Registers all the created tools into our creative tab group. */
  public static final Supplier<ItemGroup> SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER =
      Suppliers.memoize(
          () ->
              new ItemGroup.Builder(null, -1)
                  .displayName(Text.of(CREATIVE_TAB_DISPLAY_NAME))
                  .icon(
                      () -> new ItemStack(ModItems.EMERALD_TOOLSET_SUPPLIER_LIST.getFirst().get()))
                  .entries(
                      (displayContext, entries) -> {
                        // Get all of our tools as single Supplier list.
                        List<Supplier<Item>> supplierList =
                            SimpleTools.ALL_TOOLS.stream()
                                .map(toolset -> toolset.allTools)
                                .flatMap(List::stream)
                                .toList();
                        // Go through and add it to the Simple Tools creative tab.
                        for (Supplier<Item> s : supplierList) {
                          entries.add(s.get());
                        }
                      })
                  .build());

  /** Registered ItemGroup. */
  public static final RegistrySupplier<ItemGroup> SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER_REGISTERED =
      register(SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER, ITEM_GROUP_NAME);
}
