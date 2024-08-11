package com.anthonymendez.init;

import com.anthonymendez.ToekneeSimpleTools;
import com.anthonymendez.items.SimpleTools;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
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

  public static final Supplier<ItemGroup> SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER =
      Suppliers.memoize(
          () ->
              new ItemGroup.Builder(null, -1)
                  .displayName(Text.of(CREATIVE_TAB_DISPLAY_NAME))
                  .icon(() -> new ItemStack(ModItems.EMERALD_PICKAXE_SUPPLIER.get()))
                  .entries(
                      (displayContext, entries) -> {
                        for (Supplier<Item> s : SimpleTools.ALL_TOOLS) {
                          entries.add(s.get());
                        }
                      })
                  .build());

  public static final RegistrySupplier<ItemGroup> SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER_REGISTERED =
      register(SIMPLE_TOOLS_ITEM_GROUP_SUPPLIER, ITEM_GROUP_NAME);
}
