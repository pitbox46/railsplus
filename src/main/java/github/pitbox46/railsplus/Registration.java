package github.pitbox46.railsplus;

import github.pitbox46.railsplus.blocks.RedstoneLever;
import github.pitbox46.railsplus.blocks.rails.CrossRail;
import github.pitbox46.railsplus.blocks.rails.TurnRail;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final ItemGroup ITEM_GROUP = new ItemGroup("railsplus") {
        public ItemStack createIcon() {
            return new ItemStack(CROSS_RAIL.get());
        }
    };

    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "railsplus");
    static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "railsplus");

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
    }

    public static final RegistryObject<TurnRail> LEFT_TURN_RAIL = BLOCKS.register("left_turn_rail", () -> new TurnRail(true));
    public static final RegistryObject<TurnRail> RIGHT_TURN_RAIL = BLOCKS.register("right_turn_rail", () -> new TurnRail(false));
    public static final RegistryObject<CrossRail> CROSS_RAIL = BLOCKS.register("cross_rail", CrossRail::new);
    public static final RegistryObject<RedstoneLever> REDSTONE_LEVER = BLOCKS.register("redstone_lever", RedstoneLever::new);

    public static final RegistryObject<Item> LEFT_TURN_RAIL_ITEM = ITEMS.register("left_turn_rail", () -> new BlockItem(LEFT_TURN_RAIL.get(), new Item.Properties().group(ITEM_GROUP)));
    public static final RegistryObject<Item> RIGHT_TURN_RAIL_ITEM = ITEMS.register("right_turn_rail", () -> new BlockItem(RIGHT_TURN_RAIL.get(), new Item.Properties().group(ITEM_GROUP)));
    public static final RegistryObject<Item> CROSS_RAIL_ITEM = ITEMS.register("cross_rail", () -> new BlockItem(CROSS_RAIL.get(), new Item.Properties().group(ITEM_GROUP)));
    public static final RegistryObject<Item> REDSTONE_LEVER_ITEM = ITEMS.register("redstone_lever", () -> new BlockItem(REDSTONE_LEVER.get(), new Item.Properties().group(ITEM_GROUP)));
}
