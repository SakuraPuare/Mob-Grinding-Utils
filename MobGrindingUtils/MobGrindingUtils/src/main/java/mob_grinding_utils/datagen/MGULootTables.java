package mob_grinding_utils.datagen;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;

import mob_grinding_utils.ModBlocks;
import mob_grinding_utils.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MGULootTables extends LootTableProvider {
    public MGULootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }
    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }
        private static class Blocks extends BlockLoot {
            @Override
            protected void addTables() {
            dropSelf(ModBlocks.ABSORPTION_HOPPER.getBlock());
            dropSelf(ModBlocks.DARK_OAK_STONE.getBlock());
            dropSelf(ModBlocks.DRAGON_MUFFLER.getBlock());
            dropSelf(ModBlocks.WITHER_MUFFLER.getBlock());
            dropSelf(ModBlocks.ENTITY_CONVEYOR.getBlock());
            dropSelf(ModBlocks.FAN.getBlock());
            dropSelf(ModBlocks.SAW.getBlock());
            dropSelf(ModBlocks.SPIKES.getBlock());
            dropSelf(ModBlocks.XP_TAP.getBlock());
            dropSelf(ModBlocks.ENDER_INHIBITOR_ON.getBlock());
            dropOther(ModBlocks.ENDER_INHIBITOR_OFF.getBlock(), ModBlocks.ENDER_INHIBITOR_ON.getItem());
            dropSelf(ModBlocks.TINTED_GLASS.getBlock());
            add(ModBlocks.DREADFUL_DIRT.getBlock(), (block) -> createSingleItemTableWithSilkTouch(block, Items.DIRT));
            add(ModBlocks.DELIGHTFUL_DIRT.getBlock(), (block) -> createSingleItemTableWithSilkTouch(block, Items.DIRT));
            dropSelf(ModBlocks.SOLID_XP_BLOCK.getBlock());
            dropSelf(ModBlocks.ENTITY_SPAWNER.getBlock());
        }

        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            Set<Block> ignoreList = ImmutableSet.of(
                ModBlocks.TANK.getBlock(),
                ModBlocks.TANK_SINK.getBlock(),
                ModBlocks.JUMBO_TANK.getBlock(),
                ModBlocks.XPSOLIDIFIER.getBlock(),
                ModBlocks.FLUID_XP_BLOCK.get()
            );

            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(e -> !ignoreList.contains(e)).collect(Collectors.toList());
        }
    }
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationtracker) {
        map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
    }
}
