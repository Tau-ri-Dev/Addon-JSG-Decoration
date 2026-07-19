package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsg.core.common.registry.tag.CoreItemTags;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.*;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

@SuppressWarnings("DataFlowIssue")
public class JSGDecorRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public JSGDecorRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @ParametersAreNonnullByDefault

    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // Braziers

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrazierType.ABYDOS.block().get())
                .group("jsg_decor:braziers")
                .pattern(" F ")
                .pattern("TTT")
                .pattern("SBS")
                .define('F', Blocks.CAMPFIRE)
                .define('T', CoreItemTags.STORAGE_BLOCK_TITANIUM)
                .define('S', Blocks.POLISHED_DEEPSLATE_SLAB)
                .define('B', Blocks.POLISHED_DEEPSLATE)
                .unlockedBy("has_campfire", has(Blocks.CAMPFIRE))
                .unlockedBy("has_titanium_block", has(CoreItemTags.STORAGE_BLOCK_TITANIUM))
                .unlockedBy("has_deepslate_slab", has(Blocks.POLISHED_DEEPSLATE_SLAB))
                .unlockedBy("has_deepslate", has(Blocks.POLISHED_DEEPSLATE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrazierType.HATAK.block().get())
                .group("jsg_decor:braziers")
                .pattern(" F ")
                .pattern("GGG")
                .pattern("SBS")
                .define('F', Blocks.CAMPFIRE)
                .define('G', Tags.Items.STORAGE_BLOCKS_GOLD)
                .define('S', Blocks.POLISHED_DEEPSLATE_SLAB)
                .define('B', Blocks.POLISHED_DEEPSLATE)
                .unlockedBy("has_campfire", has(Blocks.CAMPFIRE))
                .unlockedBy("has_golden_block", has(Tags.Items.STORAGE_BLOCKS_GOLD))
                .unlockedBy("has_deepslate_slab", has(Blocks.POLISHED_DEEPSLATE_SLAB))
                .unlockedBy("has_deepslate", has(Blocks.POLISHED_DEEPSLATE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrazierType.ANUBIS.block().get())
                .group("jsg_decor:braziers")
                .pattern(" F ")
                .pattern("SBS")
                .pattern("#B#")
                .define('F', Blocks.CAMPFIRE)
                .define('S', Blocks.DEEPSLATE_TILE_SLAB)
                .define('B', Blocks.DEEPSLATE_TILES)
                .define('#', CoreItemTags.STORAGE_BLOCK_TITANIUM)
                .unlockedBy("has_campfire", has(Blocks.CAMPFIRE))
                .unlockedBy("has_slab", has(Blocks.DEEPSLATE_TILE_SLAB))
                .unlockedBy("has_block", has(Blocks.DEEPSLATE_TILES))
                .unlockedBy("has_material", has(CoreItemTags.STORAGE_BLOCK_TITANIUM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrazierType.GOAULD.block().get())
                .group("jsg_decor:braziers")
                .pattern(" F ")
                .pattern("SBS")
                .pattern("#B#")
                .define('F', Blocks.CAMPFIRE)
                .define('S', Ingredient.of(Blocks.CUT_COPPER_SLAB, Blocks.WAXED_CUT_COPPER_SLAB))
                .define('B', Ingredient.of(Blocks.CUT_COPPER, Blocks.WAXED_CUT_COPPER))
                .define('#', Blocks.DEEPSLATE_TILES)
                .unlockedBy("has_campfire", has(Blocks.CAMPFIRE))
                .unlockedBy("has_slab", has(Blocks.CUT_COPPER_SLAB))
                .unlockedBy("has_waxed_slab", has(Blocks.WAXED_CUT_COPPER_SLAB))
                .unlockedBy("has_block", has(Blocks.CUT_COPPER))
                .unlockedBy("has_waxed_block", has(Blocks.WAXED_CUT_COPPER))
                .unlockedBy("has_material", has(Blocks.DEEPSLATE_TILES))
                .save(pWriter);

        //Common Blocks manually created recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COMMON_BLOCKS.get("blue_atlantis_wall_block").get().asItem(), 8)
                .group("blue_atlantis_wall")
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .define('#', Blocks.LIGHT_BLUE_CONCRETE)
                .define('D', Tags.Items.DYES_CYAN)
                .unlockedBy("has_concrete", has(Blocks.LIGHT_BLUE_CONCRETE))
                .unlockedBy("has_dye", has(Tags.Items.DYES_CYAN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COMMON_BLOCKS.get("gray_atlantis_wall_block").get().asItem(), 2)
                .group("gray_atlantis_wall")
                .pattern("TIT")
                .pattern("I#I")
                .pattern("TIT")
                .define('#', Blocks.LIGHT_GRAY_CONCRETE)
                .define('T', Tags.Items.NUGGETS_IRON)
                .define('I', CoreItemTags.NUGGET_TITANIUM)
                .unlockedBy("has_concrete", has(Blocks.LIGHT_GRAY_CONCRETE))
                .unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
                .unlockedBy("has_titanium_nugget", has(CoreItemTags.NUGGET_TITANIUM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COMMON_BLOCKS.get("brown_atlantis_wall_block").get().asItem())
                .group("brown_atlantis_wall")
                .pattern(" I ")
                .pattern("I#I")
                .pattern(" I ")
                .define('#', Blocks.BROWN_TERRACOTTA)
                .define('I', Tags.Items.NUGGETS_IRON)
                .unlockedBy("has_concrete", has(Blocks.BROWN_TERRACOTTA))
                .unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COMMON_BLOCKS.get("white_block").get().asItem())
                .group("white_block")
                .pattern("#D")
                .pattern("D#")
                .define('#', Blocks.TERRACOTTA)
                .define('D', Tags.Items.DYES_WHITE)
                .unlockedBy("has_terracotta", has(Blocks.TERRACOTTA))
                .unlockedBy("has_dye", has(Tags.Items.DYES_WHITE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COMMON_BLOCKS.get("atlantis_blue_block").get().asItem(),9)
                .group("atlantis_blue_block")
                .pattern("###")
                .pattern("#W#")
                .pattern("###")
                .define('#', COMMON_BLOCKS.get("blue_atlantis_wall_block").get())
                .define('W', COMMON_BLOCKS.get("white_block").get())
                .unlockedBy("has_atlatis_blue_wall", has(COMMON_BLOCKS.get("blue_atlantis_wall_block").get()))
                .unlockedBy("has_white_block", has(COMMON_BLOCKS.get("white_block").get()))
                .save(pWriter);

        //Wood
        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            Item planksItem = WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem();

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planksItem, 4)
                    .group("planks")
                    .requires(JSGDecorItemTags.LEMON_LOGS)
                    .unlockedBy("has_log", has(JSGDecorItemTags.LEMON_LOGS))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, WOOD_BLOCKS.get(material.getMaterial() + "_wood").get().asItem(), 3)
                    .group("bark")
                    .pattern("##")
                    .pattern("##")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_log").get().asItem())
                    .unlockedBy("has_log", has(WOOD_BLOCKS.get(material.getMaterial() + "_log").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_wood").get().asItem(), 3)
                    .group("bark")
                    .pattern("##")
                    .pattern("##")
                    .define('#', WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_log").get().asItem())
                    .unlockedBy("has_log", has(WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_log").get().asItem()))
                    .save(pWriter);

            Item slabItem = WOOD_BLOCKS.get(material.getMaterial() + "_slab").get().asItem();
            Item stairsItem = WOOD_BLOCKS.get(material.getMaterial() + "_stairs").get().asItem();

            createSlabAndStairsRecipes(pWriter, planksItem, slabItem, stairsItem, "wood", true, false);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WOOD_BLOCKS.get(material.getMaterial() + "_fence").get().asItem(), 3)
                    .group("wooden_fence")
                    .pattern("#W#")
                    .pattern("#W#")
                    .define('#', planksItem)
                    .define('W', Tags.Items.RODS_WOODEN)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_fence_gate").get().asItem(), 1)
                    .group("wooden_fence_gate")
                    .pattern("W#W")
                    .pattern("W#W")
                    .define('#', planksItem)
                    .define('W', Tags.Items.RODS_WOODEN)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_button").get().asItem(), 1)
                    .group("wooden_button")
                    .requires(planksItem)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_pressure_plate").get().asItem(), 1)
                    .group("wooden_pressure_plate")
                    .pattern("##")
                    .define('#', planksItem)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_door").get().asItem(), 3)
                    .group("wooden_door")
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .define('#', planksItem)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_trapdoor").get().asItem(), 2)
                    .group("wooden_trapdoor")
                    .pattern("###")
                    .pattern("###")
                    .define('#', planksItem)
                    .unlockedBy("has_planks", has(planksItem))
                    .save(pWriter);
        }

        //Boats
        for (var type : BoatTypes.values()) {
            var boat = type.getDrop(false);
            ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat)
                    .group("boat")
                    .pattern("# #")
                    .pattern("###")
                    .define('#', type.getMaterial())
                    .unlockedBy("has_planks", has(type.getMaterial()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, type.getDrop(true))
                    .group("chest_boat")
                    .requires(boat)
                    .requires(Tags.Items.CHESTS_WOODEN)
                    .unlockedBy("has_boat", has(boat))
                    .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
                    .save(pWriter);
        }

        //Stone Based Blocks
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            Item CobbledBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.COBBLED, StoneBasedDecorationBlock.Shape.BLOCK);
            Item petrifiedBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.PETRIFIED, StoneBasedDecorationBlock.Shape.BLOCK);
            Item SmoothBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.SMOOTH, StoneBasedDecorationBlock.Shape.BLOCK);

            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {
                Item craftedBlock = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.BLOCK);
                Item craftedSlab = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.SLAB);
                Item craftedStairs = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.STAIRS);

                if (variant != StoneBasedDecorationBlock.Variant.PETRIFIED) {
                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 1)
                            .group(variant.getVariant() + "_block")
                            .unlockedBy("has_petrified", has(petrifiedBlock))
                            .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, getStoneName(material, variant) + "_stonecutting"));
                }

                createSlabAndStairsRecipes(pWriter, craftedBlock, craftedSlab, craftedStairs, variant.getVariant(), false, true);
                if (variant == StoneBasedDecorationBlock.Variant.COBBLED) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedBlock, 8)
                            .group("cobbled_block")
                            .pattern("###")
                            .pattern("N#N")
                            .pattern("###")
                            .define('#', Blocks.COBBLESTONE)
                            .define('N', material.getNugget())
                            .unlockedBy("has_nugget", has(material.getNugget()))
                            .save(pWriter);

                } else if (variant == StoneBasedDecorationBlock.Variant.PETRIFIED) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedBlock, 8)
                            .group("petrified_block")
                            .pattern("###")
                            .pattern("N#N")
                            .pattern("###")
                            .define('#', Blocks.STONE)
                            .define('N', material.getNugget())
                            .unlockedBy("has_nugget", has(material.getNugget()))
                            .save(pWriter);

                    blockSmelting(pWriter, List.of(CobbledBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 200, "jsg_decor_petrified");
                    blockBlasting(pWriter, List.of(CobbledBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 100, "jsg_decor_petrified");

                } else if (variant == StoneBasedDecorationBlock.Variant.LARGE_BRICKS) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedBlock, 4)
                            .group("large_bricks_block")
                            .pattern("##")
                            .pattern("##")
                            .define('#', petrifiedBlock)
                            .unlockedBy("has_petrified_block", has(petrifiedBlock))
                            .save(pWriter);

                } else if (variant == StoneBasedDecorationBlock.Variant.SMOOTH) {
                    blockSmelting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 200, "jsg_decor_smooth");
                    blockBlasting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 100, "jsg_decor_smooth");

                } else if (variant == StoneBasedDecorationBlock.Variant.MONOLITHIC) {
                    blockSmelting(pWriter, List.of(SmoothBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 200, "jsg_decor_monolithic");
                    blockBlasting(pWriter, List.of(SmoothBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 0.1f, 100, "jsg_decor_monolithic");
                }
            }
        }

        //Overlay Blocks
        for (BlockWithOverlay.Material material : BlockWithOverlay.Material.values()) {
            Item craftedBlock = OVERLAY_BLOCKS.get(material.getMaterial() + "_block").get().asItem();
            Item craftedSlab = OVERLAY_BLOCKS.get(material.getMaterial() + "_slab").get().asItem();
            Item craftedStairs = OVERLAY_BLOCKS.get(material.getMaterial() + "_stairs").get().asItem();

            SingleItemRecipeBuilder.stonecutting(Ingredient.of(material.getBaseBlock()), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 1)
                    .group(material.getMaterial() + "_block")
                    .unlockedBy("has_terracotta", has(material.getBaseBlock()))
                    .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, ITEMS.getKey(craftedBlock).getPath() + "_from_stonecutting"));

            createSlabAndStairsRecipes(pWriter, craftedBlock, craftedSlab, craftedStairs, material.getMaterial(), false, true);
        }

        //Common Blocks automatic recipes
        for (CommonBlock.Material material : CommonBlock.Material.values()) {
            Item parentBlock = COMMON_BLOCKS.get(material.getMaterial() + "_block").get().asItem();

            CommonBlock.Variant[] generateVariants = material.hasVariants()
                    ? CommonBlock.Variant.values()
                    : new CommonBlock.Variant[]{CommonBlock.Variant.DEFAULT};

            for (CommonBlock.Variant var : generateVariants) {
                String baseName = (var == CommonBlock.Variant.DEFAULT) ? material.getMaterial() : var.getVariant() + "_" + material.getMaterial();

                Item baseBlock = COMMON_BLOCKS.get(baseName + "_block").get().asItem();
                Item slab = COMMON_BLOCKS.get(baseName + "_slab").get().asItem();
                Item stairs = COMMON_BLOCKS.get(baseName + "_stairs").get().asItem();

                if (var != CommonBlock.Variant.DEFAULT) {
                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(parentBlock), RecipeCategory.BUILDING_BLOCKS, baseBlock, 1)
                            .group(var.getVariant() + "_block")
                            .unlockedBy("has_parent_block", has(parentBlock))
                            .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, baseName + "_block_stonecutting"));

                    if (material.hasLampVariant()) {
                        SingleItemRecipeBuilder.stonecutting(Ingredient.of(COMMON_BLOCKS.get(material.getMaterial() + "_lamp_block").get().asItem()), RecipeCategory.REDSTONE, COMMON_BLOCKS.get(baseName + "_lamp_block").get().asItem(), 1)
                                .group(var.getVariant() + "_lamp_block")
                                .unlockedBy("has_parent_lamp", has(COMMON_BLOCKS.get(material.getMaterial() + "_lamp_block").get().asItem()))
                                .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, baseName + "_lamp_block_stonecutting"));
                    }
                }

                createSlabAndStairsRecipes(pWriter, baseBlock, slab, stairs, var.getVariant(), false, true);

                if (material.hasLampVariant()) {
                    Item lampBlock = COMMON_BLOCKS.get(baseName + "_lamp_block").get().asItem();
                    Item lampSlab = COMMON_BLOCKS.get(baseName + "_lamp_slab").get().asItem();
                    Item lampStairs = COMMON_BLOCKS.get(baseName + "_lamp_stairs").get().asItem();

                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, lampBlock, 8)
                            .group("common_lamp_block")
                            .pattern("###")
                            .pattern("#G#")
                            .pattern("###")
                            .define('#', baseBlock)
                            .define('G', Blocks.GLOWSTONE) //
                            .unlockedBy("has_glowstone", has(Blocks.GLOWSTONE))
                            .save(pWriter);

                    createSlabAndStairsRecipes(pWriter, lampBlock, lampSlab, lampStairs, var.getVariant() + "_lamp", false, true);
                }
            }
        }

        //Glass Block
        for (GlassBlock.Material material : GlassBlock.Material.values()) {
            Item vanillaSourceGlass = getVanillaGlassSource(material.getDyeColor());

            for (GlassBlock.Shape shape : GlassBlock.Shape.values()) {
                Item CraftedItem = GLASS_BLOCKS.get(material.getMaterial() + "_" + shape.getShape()).get().asItem();

                if (shape == GlassBlock.Shape.GLASS_BLOCK) {
                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(vanillaSourceGlass), RecipeCategory.BUILDING_BLOCKS, CraftedItem, 1)
                            .group((material.getDyeColor() == null) ? "clear_glass_block" : material.getDyeColor().getName() + "_glass_block")
                            .unlockedBy("has_vanilla_glass", has(vanillaSourceGlass))
                            .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, material.getMaterial() + "_" + shape.getShape() + "_stonecutting"));

                } else if (shape == GlassBlock.Shape.GLASS_PANE) {
                    Item glassForCrafting = GLASS_BLOCKS.get(material.getMaterial() + "_" + GlassBlock.Shape.GLASS_BLOCK.getShape()).get().asItem();

                    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CraftedItem, 16)
                            .group("glass_pane")
                            .pattern("###")
                            .pattern("###")
                            .define('#', glassForCrafting)
                            .unlockedBy("has_glass_block", has(glassForCrafting))
                            .save(pWriter);
                }
            }
        }
    }

    private Item getStoneBlock(StoneBasedDecorationBlock.Material mat, StoneBasedDecorationBlock.Variant var, StoneBasedDecorationBlock.Shape shape) {
        String name = var.shouldSwapOrder()
                ? var.getVariant() + "_" + mat.getMaterial() + "_" + shape.getShape()
                : mat.getMaterial() + "_" + var.getVariant() + "_" + shape.getShape();
        return STONE_BASED_DECORATION_BLOCKS.get(name).get().asItem();
    }

    private String getStoneName(StoneBasedDecorationBlock.Material mat, StoneBasedDecorationBlock.Variant var) {
        return var.shouldSwapOrder()
                ? var.getVariant() + "_" + mat.getMaterial() + "_block"
                :  mat.getMaterial() + "_" + var.getVariant() + "_block";
    }

    private void createSlabAndStairsRecipes(Consumer<FinishedRecipe> pWriter, Item craftingBlock, Item slab, Item stairs, String group, Boolean isWoodRecipe, Boolean stoneCutterRecipes) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                .group(isWoodRecipe ? "wooden_stairs" : group + "_stairs")
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', craftingBlock)
                .unlockedBy("has_block", has(craftingBlock))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
                .group(isWoodRecipe? "wooden_slab" : group + "_slab")
                .pattern("###")
                .define('#', craftingBlock)
                .unlockedBy("has_block", has(craftingBlock))
                .save(pWriter);

        if (stoneCutterRecipes) {
            String stairsPath = ITEMS.getKey(stairs).getPath();
            String slabPath = ITEMS.getKey(slab).getPath();

            SingleItemRecipeBuilder.stonecutting(Ingredient.of(craftingBlock), RecipeCategory.BUILDING_BLOCKS, stairs, 1)
                    .group(group + "_stairs")
                    .unlockedBy("has_block", has(craftingBlock))
                    .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, stairsPath + "_stonecutting"));

            SingleItemRecipeBuilder.stonecutting(Ingredient.of(craftingBlock), RecipeCategory.BUILDING_BLOCKS, slab, 2)
                    .group(group + "_slab")
                    .unlockedBy("has_block", has(craftingBlock))
                    .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, slabPath + "_stonecutting"));
        }
    }

    @Nullable
    private Item getVanillaGlassSource(DyeColor dyeColor) {
        if (dyeColor == null) {
            return Items.GLASS;
        }
        return switch (dyeColor) {
            case WHITE -> Items.WHITE_STAINED_GLASS;
            case ORANGE -> Items.ORANGE_STAINED_GLASS;
            case MAGENTA -> Items.MAGENTA_STAINED_GLASS;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_STAINED_GLASS;
            case YELLOW -> Items.YELLOW_STAINED_GLASS;
            case LIME -> Items.LIME_STAINED_GLASS;
            case PINK -> Items.PINK_STAINED_GLASS;
            case GRAY -> Items.GRAY_STAINED_GLASS;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_STAINED_GLASS;
            case CYAN -> Items.CYAN_STAINED_GLASS;
            case PURPLE -> Items.PURPLE_STAINED_GLASS;
            case BLUE -> Items.BLUE_STAINED_GLASS;
            case BROWN -> Items.BROWN_STAINED_GLASS;
            case GREEN -> Items.GREEN_STAINED_GLASS;
            case RED -> Items.RED_STAINED_GLASS;
            case BLACK -> Items.BLACK_STAINED_GLASS;
        };
    }

    @ParametersAreNonnullByDefault
    @SuppressWarnings("SameParameterValue")
    protected static void blockSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        blockCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    @ParametersAreNonnullByDefault
    @SuppressWarnings("SameParameterValue")
    protected static void blockBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        blockCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }


    @ParametersAreNonnullByDefault
    protected static void blockCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, JSGDecor.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}