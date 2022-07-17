package mob_grinding_utils;


import mob_grinding_utils.blocks.*;
import mob_grinding_utils.client.render.TileSawStackItemRenderer;
import mob_grinding_utils.client.render.TileTankStackItemRenderer;
import mob_grinding_utils.client.render.TileXPSolidifierStackItemRenderer;
import mob_grinding_utils.itemblocks.BlockItemTank;
import mob_grinding_utils.itemblocks.BlockItemTankJumbo;
import mob_grinding_utils.itemblocks.BlockItemTankSink;
import mob_grinding_utils.itemblocks.MGUBlockItem;
import mob_grinding_utils.tile.*;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.*;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class ModBlocks {
	public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);
	public static DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Reference.MOD_ID);
	public static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

	public static MGUBlockReg<BlockFan, MGUBlockItem, TileEntityFan> FAN = new MGUBlockReg<>("fan",
		() -> new BlockFan(Block.Properties.of(Material.METAL, MaterialColor.STONE).strength(10.0F, 2000.0F).sound(SoundType.METAL)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)), TileEntityFan::new);

	public static MGUBlockReg<BlockSaw, MGUBlockItem, TileEntitySaw> SAW = new MGUBlockReg<>("saw",
		() -> new BlockSaw(Block.Properties.of(Material.METAL, MaterialColor.STONE).strength(10.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)) {
			@Override
			public void initializeClient(Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new TileSawStackItemRenderer(null, null);
					}
				});
			}
		}, TileEntitySaw::new);

	public static MGUBlockReg<BlockAbsorptionHopper, MGUBlockItem, TileEntityAbsorptionHopper> ABSORPTION_HOPPER = new MGUBlockReg<>("absorption_hopper",
		() -> new BlockAbsorptionHopper(Block.Properties.of(Material.METAL, MaterialColor.STONE).strength(10.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)), TileEntityAbsorptionHopper::new);

	public static MGUBlockReg<BlockSpikes, MGUBlockItem, ?> SPIKES = new MGUBlockReg<>("spikes",
		() -> new BlockSpikes(Block.Properties.of(Material.METAL, MaterialColor.STONE).strength(5.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockTank, BlockItemTank, TileEntityTank> TANK = new MGUBlockReg<>("tank",
		() -> new BlockTank(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion()),
		(b) -> new BlockItemTank(b, new Item.Properties().tab(MobGrindingUtils.TAB)) {
			@Override
			public void initializeClient(Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new TileTankStackItemRenderer(null, null);
					}
				});
			}
		}, TileEntityTank::new);

	public static MGUBlockReg<BlockTankSink, BlockItemTankSink, TileEntitySinkTank> TANK_SINK = new MGUBlockReg<>("tank_sink",
		() -> new BlockTankSink(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion()),
		(b) -> new BlockItemTankSink(b, new Item.Properties().tab(MobGrindingUtils.TAB)){
			@Override
			public void initializeClient(Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new TileTankStackItemRenderer(null, null);
					}
				});
			}
		}, TileEntitySinkTank::new);

	public static MGUBlockReg<BlockXPTap, MGUBlockItem, TileEntityXPTap> XP_TAP = new MGUBlockReg<>("xp_tap",
		() -> new BlockXPTap(Block.Properties.of(Material.BUILDABLE_GLASS, MaterialColor.STONE).strength(1.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)), TileEntityXPTap::new);

	public static MGUBlockReg<BlockWitherMuffler, MGUBlockItem, ?> WITHER_MUFFLER = new MGUBlockReg<>("wither_muffler",
		() -> new BlockWitherMuffler(Block.Properties.of(Material.WOOL, MaterialColor.STONE).strength(0.5F, 2000F).sound(SoundType.WOOL)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockDragonMuffler, MGUBlockItem, ?> DRAGON_MUFFLER = new MGUBlockReg<>("dragon_muffler",
		() -> new BlockDragonMuffler(Block.Properties.of(Material.WOOL, MaterialColor.STONE).strength(0.5F, 2000F).sound(SoundType.WOOL)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockDarkOakStone, MGUBlockItem, ?> DARK_OAK_STONE = new MGUBlockReg<>("dark_oak_stone",
		() -> new BlockDarkOakStone(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 10F).sound(SoundType.STONE).lightLevel(bState -> 7)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockEntityConveyor, MGUBlockItem, ?> ENTITY_CONVEYOR = new MGUBlockReg<>("entity_conveyor",
		() -> new BlockEntityConveyor(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(0.5F, 2000.0F).sound(SoundType.STONE).isValidSpawn((state, reader, pos, entitytype) -> true)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockEnderInhibitorOn, MGUBlockItem, ?> ENDER_INHIBITOR_ON = new MGUBlockReg<>("ender_inhibitor_on",
		() -> new BlockEnderInhibitorOn(Block.Properties.of(Material.BUILDABLE_GLASS, MaterialColor.STONE).strength(0.2F, 2000F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockEnderInhibitorOff, MGUBlockItem, ?> ENDER_INHIBITOR_OFF = new MGUBlockReg<>("ender_inhibitor_off",
		() -> new BlockEnderInhibitorOff(Block.Properties.of(Material.BUILDABLE_GLASS, MaterialColor.STONE).strength(0.2F, 2000F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockTintedGlass, MGUBlockItem, ?> TINTED_GLASS = new MGUBlockReg<>("tinted_glass",
		() -> new BlockTintedGlass(Block.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockTankJumbo, BlockItemTankJumbo, TileEntityJumboTank> JUMBO_TANK = new MGUBlockReg<>("jumbo_tank",
		() -> new BlockTankJumbo(Block.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(1.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new BlockItemTankJumbo(b, new Item.Properties().tab(MobGrindingUtils.TAB)){
			@Override
			public void initializeClient(Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new TileTankStackItemRenderer(null, null);
					}
				});
			}
		}, TileEntityJumboTank::new);

	public static MGUBlockReg<BlockXPSolidifier, MGUBlockItem, TileEntityXPSolidifier> XPSOLIDIFIER = new MGUBlockReg<>("xpsolidifier",
		() -> new BlockXPSolidifier(Block.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(1.0F, 2000.0F).sound(SoundType.METAL).noOcclusion()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)){
			@Override
			public void initializeClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
				consumer.accept(new IClientItemExtensions() {
					@Override
					public BlockEntityWithoutLevelRenderer getCustomRenderer() {
						return new TileXPSolidifierStackItemRenderer(null, null);
					}
				});
			}
		}, TileEntityXPSolidifier::new);

	public static Material MATERIAL_DREADFUL_DIRT = new Material(MaterialColor.DIRT, false, true, false, true, true, false, PushReaction.NORMAL);
	public static MGUBlockReg<BlockDreadfulDirt, MGUBlockItem, ?> DREADFUL_DIRT = new MGUBlockReg<>("dreadful_dirt",
		() -> new BlockDreadfulDirt(Block.Properties.of(MATERIAL_DREADFUL_DIRT, MaterialColor.COLOR_PURPLE).strength(1.0F, 2000.0F).sound(SoundType.GRAVEL).randomTicks()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockSolidXP, MGUBlockItem, ?> SOLID_XP_BLOCK = new MGUBlockReg<>("solid_xp_block",
		() -> new BlockSolidXP(Block.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(ModSounds.SOLID_XP_BLOCK).noOcclusion().strength(1.5F, 10F)),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockDelightfulDirt, MGUBlockItem, ?> DELIGHTFUL_DIRT = new MGUBlockReg<>("delightful_dirt",
		() -> new BlockDelightfulDirt(Block.Properties.of(Material.DIRT, MaterialColor.COLOR_PURPLE).strength(1.0F, 2000.0F).sound(SoundType.GRAVEL).randomTicks()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)));

	public static MGUBlockReg<BlockEntitySpawner, MGUBlockItem, TileEntityMGUSpawner> ENTITY_SPAWNER = new MGUBlockReg<>("entity_spawner",
		() -> new BlockEntitySpawner(Block.Properties.of(Material.METAL, MaterialColor.STONE).strength(10.0F, 2000.0F).sound(SoundType.METAL).noOcclusion().randomTicks()),
		(b) -> new MGUBlockItem(b, new Item.Properties().tab(MobGrindingUtils.TAB)), TileEntityMGUSpawner::new);

	public static RegistryObject<FluidType> XPTYPE = FLUID_TYPES.register("fluid_xp", () -> new FluidType(FluidType.Properties.create()
			.temperature(300)
			.lightLevel(10)
			.viscosity(1500)
			.density(800)
			.canConvertToSource(false)
			.canDrown(false)
			.canSwim(true)
			.descriptionId("mob_grinding_utils.fluid_xp")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.EXPERIENCE_ORB_PICKUP)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.PLAYER_LEVELUP))
			{
				ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_xp");
				@Override
				public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
					consumer.accept(new IClientFluidTypeExtensions() {
						@Override
						public ResourceLocation getStillTexture() {
							return texture;
						}

						@Override
						public ResourceLocation getFlowingTexture(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
							return texture;
						}

						@Override
						public int getTintColor(FluidStack stack) {
							return IClientFluidTypeExtensions.super.getTintColor(stack);
						}


					});
				}

				@Override
				public ItemStack getBucket(FluidStack stack) {
					return new ItemStack(ModItems.FLUID_XP_BUCKET.get());
				}
			});
	public static RegistryObject<ForgeFlowingFluid> FLUID_XP = FLUIDS.register("fluid_xp",
		() -> new ForgeFlowingFluid.Source(ModBlocks.xp_properties) );
	public static RegistryObject<ForgeFlowingFluid> FLUID_XP_FLOWING = FLUIDS.register("fluid_xp_flowing",
		() -> new ForgeFlowingFluid.Flowing(ModBlocks.xp_properties) );
	public static RegistryObject<LiquidBlock> FLUID_XP_BLOCK = BLOCKS.register("fluid_xp",
		() -> new MGUFlowingFluidBlock(FLUID_XP,Block.Properties.of(Material.WATER)));

	private static final ForgeFlowingFluid.Properties xp_properties = new ForgeFlowingFluid.Properties(() -> XPTYPE.get(), () -> FLUID_XP.get(), () -> FLUID_XP_FLOWING.get())
			.block(() -> FLUID_XP_BLOCK.get())
			.bucket(() -> ModItems.FLUID_XP_BUCKET.get());

	public static void init(IEventBus evt) {
		BLOCKS.register(evt);
		TILE_ENTITIES.register(evt);
		FLUIDS.register(evt);
		FLUID_TYPES.register(evt);
	}
}