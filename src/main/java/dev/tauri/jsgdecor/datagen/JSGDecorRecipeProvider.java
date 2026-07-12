package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsg.core.common.registry.tag.CoreItemTags;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.block.CoreDecorationBlocks;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;


public class JSGDecorRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public JSGDecorRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //Lemon wood and all related to it
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEMON_WOOD.get(), 3)
                .group("bark")
                .pattern("##")
                .pattern("##")
                .define('#', JSGDecorBlocks.LEMON_LOG.get())
                .unlockedBy("has_log", has(JSGDecorBlocks.LEMON_LOG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEMON_WOOD_STRIPPED.get(), 3)
                .group("bark")
                .pattern("##")
                .pattern("##")
                .define('#', JSGDecorBlocks.LEMON_LOG_STRIPPED.get())
                .unlockedBy("has_stripped_log", has(JSGDecorBlocks.LEMON_LOG_STRIPPED.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEMON_PLANKS.get(), 4)
                .group("planks")
                .requires(JSGDecorBlocks.LEMON_LOG.get())
                .unlockedBy("has_wood", has(JSGDecorBlocks.LEMON_LOG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEMON_STAIRS.get(), 4)
                .group("wooden_stairs")
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEMON_SLAB.get(), 6)
                .group("wooden_slab")
                .pattern("###")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, JSGDecorBlocks.LEMON_FENCE.get(), 3)
                .group("wooden_fence")
                .pattern("#S#")
                .pattern("#S#")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, JSGDecorBlocks.LEMON_GATE.get())
                .group("wooden_fence_gate")
                .pattern("#S#")
                .pattern("#S#")
                .define('#', Tags.Items.RODS_WOODEN)
                .define('S', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, JSGDecorBlocks.LEMON_DOOR.get(), 3)
                .group("wooden_door")
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, JSGDecorBlocks.LEMON_TRAPDOOR.get(), 2)
                .group("wooden_trapdoor")
                .pattern("###")
                .pattern("###")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, JSGDecorBlocks.LEMON_PRESSURE_PLATE.get())
                .group("wooden_pressure_plate")
                .pattern("##")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, JSGDecorBlocks.LEMON_BUTTON.get())
                .group("wooden_button")
                .requires(JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);


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

        // Core Blocks

        for (CoreDecorationBlocks.Material material : CoreDecorationBlocks.Material.values()) {
            for (CoreDecorationBlocks.Variant variant : CoreDecorationBlocks.Variant.values()) {

                String modifiedMaterial = material.getMaterial() + "_" + variant.getVariant();
                Block block = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_" + variant.getVariant() + "_block").get();

                for (CoreDecorationBlocks.Shape shape : CoreDecorationBlocks.Shape.values()) {
                    Block completedBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(modifiedMaterial + "_" + shape.getShape()).get();

                    switch (shape) {
                        case SLAB -> {
                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, completedBlock, 6)
                                    .pattern("###")
                                    .define('#', block)
                                    .unlockedBy("has_" + block, has(block))
                                    .save(pWriter, locationCorrection(modifiedMaterial + "_slab"));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, completedBlock, 2)
                                    .unlockedBy("has_" + modifiedMaterial + "_block", has(block))
                                    .save(pWriter, locationCorrection(modifiedMaterial + "_slab_stonecutting"));
                        }
                        case STAIRS -> {
                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, completedBlock, 4)
                                    .pattern("#  ")
                                    .pattern("## ")
                                    .pattern("###")
                                    .define('#', block)
                                    .unlockedBy("has_" + modifiedMaterial + "_block", has(block))
                                    .save(pWriter, locationCorrection(modifiedMaterial + "_stairs"));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, completedBlock, 1)
                                    .unlockedBy("has_" + block, has(block))
                                    .save(pWriter, locationCorrection(modifiedMaterial + "_stairs_stonecutting"));
                        }
                        case BLOCK -> {
                            Item nugget = material.getNugget();

                            if (variant == CoreDecorationBlocks.Variant.PETRIFIED) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block, 8)
                                        .pattern("###")
                                        .pattern("x#x")
                                        .pattern("###")
                                        .define('#', Blocks.STONE)
                                        .define('x', nugget)
                                        .unlockedBy("has_" + modifiedMaterial + "_nugget", has(nugget))
                                        .save(pWriter, locationCorrection(modifiedMaterial + "block_crafting"));

                                for (CoreDecorationBlocks.Variant variants : CoreDecorationBlocks.Variant.values()) {
                                    if (variants == CoreDecorationBlocks.Variant.PETRIFIED) continue;

                                    Block targetBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_" + variants.getVariant() + "_block").get();

                                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, targetBlock, 1)
                                            .unlockedBy("has_" + modifiedMaterial + "_block", has(block))
                                            .save(pWriter, locationCorrection(material.getMaterial() + "_" + variants.getVariant() + "_block_from_petrified_stonecutting"));
                                }

                                Block cobbledBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_cobbled_block").get();
                                blockSmelting(pWriter, List.of(cobbledBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 200, "jsg_decor_petrified");
                                blockBlasting(pWriter, List.of(cobbledBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 100, "jsg_decor_petrified");

                            } else if (variant == CoreDecorationBlocks.Variant.COBBLED) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block, 8)
                                        .pattern("###")
                                        .pattern("x#x")
                                        .pattern("###")
                                        .define('#', Blocks.COBBLESTONE)
                                        .define('x', nugget)
                                        .unlockedBy("has_" + modifiedMaterial + "_nugget", has(nugget))
                                        .save(pWriter, locationCorrection(modifiedMaterial + "block_crafting"));
                            } else if (variant == CoreDecorationBlocks.Variant.BIG_BRICKS) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block, 4)
                                        .pattern("##")
                                        .pattern("##")
                                        .define('#', JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_petrified_block").get())
                                        .unlockedBy("has_" + modifiedMaterial + "_block", has(block))
                                        .save(pWriter, locationCorrection(modifiedMaterial + "block_crafting"));
                            }
                            else if (variant == CoreDecorationBlocks.Variant.SMOOTH) {
                                Block petrifiedBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_petrified_block").get();
                                blockSmelting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 200, "jsg_decor_smooth");
                                blockBlasting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 100, "jsg_decor_smooth");
                            }
                            else if (variant == CoreDecorationBlocks.Variant.MONOLITHIC) {
                                Block smoothBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(material.getMaterial() + "_smooth_block").get();
                                blockSmelting(pWriter, List.of(smoothBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 200, "jsg_decor_monolithic");
                                blockBlasting(pWriter, List.of(smoothBlock), RecipeCategory.BUILDING_BLOCKS, block, 0.1f, 100, "jsg_decor_monolithic");
                            }
                        }
                    }
                }
            }
        }

        //Atlantis decoration blocks

        /*ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get(), 8)
                .group("jsg_decor:atlantis_solid")
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', Blocks.LIGHT_BLUE_CONCRETE)
                .define('C', Blocks.WHITE_CONCRETE)
                .unlockedBy("has_blue_block", has(Blocks.LIGHT_BLUE_CONCRETE))
                .unlockedBy("has_white_block", has(Blocks.WHITE_CONCRETE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.BLUE_ATLANTIS_LAMP_BLOCK.get(), 8)
                .group("jsg_decor:atlantis_solid")
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get())
                .define('C', JSGDecorBlocks.WHITE_LAMP_BLOCK.get())
                .unlockedBy("has_blue_block", has(JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get()))
                .unlockedBy("has_white_block", has(JSGDecorBlocks.WHITE_LAMP_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.WHITE_LAMP_BLOCK.get(), 8)
                .group("jsg_decor:atlantis_solid")
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', Blocks.WHITE_CONCRETE)
                .define('C', Blocks.GLOWSTONE)
                .unlockedBy("has_white_block", has(Blocks.WHITE_CONCRETE))
                .unlockedBy("has_glowstone", has(Blocks.GLOWSTONE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.BROWN_WALL_BLOCK.get(), 8)
                .group("jsg_decor:atlantis_solid")
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .define('#', Blocks.BROWN_TERRACOTTA)
                .define('C', Ingredient.of(Items.GLOW_INK_SAC, Items.INK_SAC))
                .unlockedBy("has_white_block", has(Blocks.BROWN_TERRACOTTA))
                .unlockedBy("has_glow_ink", has(Items.GLOW_INK_SAC))
                .unlockedBy("has_ink", has(Items.INK_SAC))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.ATLANTIS_WALL_BLOCK.get())
                .group("jsg_decor:atlantis_solid")
                .pattern("TIT")
                .pattern("IRI")
                .pattern("TIT")
                .define('T', CoreItemTags.INGOT_TITANIUM)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('R', CoreItemTags.INGOT_TRINIUM)
                .unlockedBy("has_titanium", has(CoreItemTags.INGOT_TITANIUM))
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_trinium", has(CoreItemTags.INGOT_TRINIUM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.AGED_ATLANTIS_WALL_BLOCK.get())
                .group("jsg_decor:atlantis_solid")
                .pattern("NTN")
                .pattern("TRT")
                .pattern("NTN")
                .define('T', CoreItemTags.INGOT_TITANIUM)
                .define('N', CoreItemTags.INGOT_NAQUADAH_ALLOY)
                .define('R', CoreItemTags.INGOT_TRINIUM)
                .unlockedBy("has_titanium", has(CoreItemTags.INGOT_TITANIUM))
                .unlockedBy("has_naquadah", has(CoreItemTags.INGOT_NAQUADAH_ALLOY))
                .unlockedBy("has_trinium", has(CoreItemTags.INGOT_TRINIUM))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.FLOODED_ATLANTIS_WALL_BLOCK.get())
                .group("jsg_decor:atlantis_solid")
                .pattern("ITI")
                .pattern("TRT")
                .pattern("ITI")
                .define('T', CoreItemTags.INGOT_TITANIUM)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('R', CoreItemTags.INGOT_TRINIUM)
                .unlockedBy("has_titanium", has(CoreItemTags.INGOT_TITANIUM))
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_trinium", has(CoreItemTags.INGOT_TRINIUM))
                .save(pWriter);*/

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.SPARSELY_WRITTEN_BLOCK.get(), 8)
                .group("jsg_decor:atlantis_solid")
                .pattern("###")
                .pattern("#S#")
                .pattern("###")
                .define('#', Blocks.TERRACOTTA)
                .define('S', ItemTags.SIGNS)
                .unlockedBy("has_brown_terracota", has(Blocks.TERRACOTTA))
                .unlockedBy("has_sign", has(ItemTags.SIGNS))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.DENSELY_WRITTEN_BLOCK.get())
                .requires(JSGDecorBlocks.SPARSELY_WRITTEN_BLOCK.get())
                .requires(ItemTags.SIGNS)
                .unlockedBy("has_brown_terracota", has(Blocks.TERRACOTTA))
                .unlockedBy("has_sign", has(ItemTags.SIGNS))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.GREEN_GLASS_BLOCK.get(), 9)
                .group("jsg_decor:atlantis_transparent")
                .pattern("PST")
                .pattern("PTS")
                .pattern("PST")
                .define('P', Tags.Items.GLASS_LIME)
                .define('S', Tags.Items.GLASS_GREEN)
                .define('T', Tags.Items.GLASS_CYAN)
                .unlockedBy("has_primary_glass", has(Tags.Items.GLASS_LIME))
                .unlockedBy("has_secoundary_glass", has(Tags.Items.GLASS_GREEN))
                .unlockedBy("has_tertiary_glass", has(Tags.Items.GLASS_CYAN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get(), 9)
                .group("jsg_decor:atlantis_transparent")
                .pattern("PPS")
                .pattern("PPS")
                .pattern("PPS")
                .define('P', Tags.Items.GLASS_LIME)
                .define('S', Tags.Items.GLASS_GREEN)
                .unlockedBy("has_primary_glass", has(Tags.Items.GLASS_LIME))
                .unlockedBy("has_secoundary_glass", has(Tags.Items.GLASS_GREEN))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEFT_GREEN_GLASS_BLOCK.get())
                .requires(JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get())
                .unlockedBy("has_right_glass", has(JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get(), 9)
                .group("jsg_decor:atlantis_transparent")
                .pattern("PPS")
                .pattern("PPS")
                .pattern("PPS")
                .define('P', Tags.Items.GLASS_RED)
                .define('S', Tags.Items.GLASS_ORANGE)
                .unlockedBy("has_primary_glass", has(Tags.Items.GLASS_RED))
                .unlockedBy("has_secoundary_glass", has(Tags.Items.GLASS_ORANGE))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEFT_RED_GLASS_BLOCK.get())
                .requires(JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get())
                .unlockedBy("has_right_glass", has(JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get(), 9)
                .group("jsg_decor:atlantis_transparent")
                .pattern("PPS")
                .pattern("PPS")
                .pattern("PPS")
                .define('P', Tags.Items.GLASS_LIGHT_BLUE)
                .define('S', Tags.Items.GLASS_BLUE)
                .unlockedBy("has_primary_glass", has(Tags.Items.GLASS_LIGHT_BLUE))
                .unlockedBy("has_secoundary_glass", has(Tags.Items.GLASS_BLUE))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LEFT_BLUE_GLASS_BLOCK.get())
                .requires(JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get())
                .unlockedBy("has_right_glass", has(JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get()))
                .save(pWriter);
    }


    private ResourceLocation locationCorrection(String name) {
        return JSGMapping.rl(JSGDecor.MOD_ID, name);
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
