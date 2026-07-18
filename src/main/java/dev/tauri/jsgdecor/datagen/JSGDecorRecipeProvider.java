package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsg.core.common.registry.tag.CoreItemTags;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.block.StoneBasedDecorationBlock;
import dev.tauri.jsgdecor.common.block.WoodBlock;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
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

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.STONE_BASED_DECORATION_BLOCKS;
import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.WOOD_BLOCKS;


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

        //Wood
        for (WoodBlock.Material material : WoodBlock.Material.values()) {

            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem(), 4)
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

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, WOOD_BLOCKS.get(material.getMaterial() + "_slab").get().asItem(), 6)
                    .group("wooden_slab")
                    .pattern("###")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, WOOD_BLOCKS.get(material.getMaterial() + "_stairs").get().asItem(), 4)
                    .group("wooden_stairs")
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WOOD_BLOCKS.get(material.getMaterial() + "_fence").get().asItem(), 3)
                    .group("wooden_fence")
                    .pattern("#W#")
                    .pattern("#W#")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .define('W', net.minecraft.world.item.Items.STICK)
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_fence_gate").get().asItem(), 1)
                    .group("wooden_fence_gate")
                    .pattern("W#W")
                    .pattern("W#W")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .define('W', net.minecraft.world.item.Items.STICK)
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_button").get().asItem(), 1)
                    .group("wooden_button")
                    .requires(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_pressure_plate").get().asItem(), 1)
                    .group("wooden_pressure_plate")
                    .pattern("##")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_door").get().asItem(), 3)
                    .group("wooden_door")
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, WOOD_BLOCKS.get(material.getMaterial() + "_trapdoor").get().asItem(), 2)
                    .group("wooden_trapdoor")
                    .pattern("###")
                    .pattern("###")
                    .define('#', WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem())
                    .unlockedBy("has_planks", has(WOOD_BLOCKS.get(material.getMaterial() + "_planks").get().asItem()))
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
    }
/*
        // Core Blocks
        //todo: refractor - methods + add condition to recipe book as petrified block or petrified slabs are in same category - you need to right click to see it and it cycle by default
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {

                String ingredientName = variant.shouldSwapOrder() ? variant.getVariant() + "_" + material.getMaterial() + "_block" : material.getMaterial() + "_" + variant.getVariant() + "_block";
                Block ingredientBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(ingredientName).get();

                for (StoneBasedDecorationBlock.Shape shape : StoneBasedDecorationBlock.Shape.values()) {

                    String craftedBlockName = variant.shouldSwapOrder() ? variant.getVariant() + "_" + material.getMaterial() + "_" + shape.getShape() : material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();
                    Block craftedBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(craftedBlockName).get();

                    switch (shape) {
                        case SLAB -> {
                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedBlock, 6)
                                    .pattern("###")
                                    .define('#', ingredientBlock)
                                    .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredientBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 2)
                                    .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName + "_stonecutting"));
                        }
                        case STAIRS -> {
                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedBlock, 4)
                                    .pattern("#  ")
                                    .pattern("## ")
                                    .pattern("###")
                                    .define('#', ingredientBlock)
                                    .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredientBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 1)
                                    .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName + "_stonecutting"));
                        }
                        case BLOCK -> {
                            Item nugget = material.getNugget();

                            if (variant == StoneBasedDecorationBlock.Variant.PETRIFIED) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 8)
                                        .pattern("###")
                                        .pattern("x#x")
                                        .pattern("###")
                                        .define('#', Blocks.STONE)
                                        .define('x', nugget)
                                        .unlockedBy("has_" + ingredientName + "_nugget", has(nugget))
                                        .save(pWriter, locationCorrection(ingredientName + "_crafting"));

                                for (StoneBasedDecorationBlock.Variant variants : StoneBasedDecorationBlock.Variant.values()) {
                                    if (variants == StoneBasedDecorationBlock.Variant.PETRIFIED) continue;

                                    String targetBlockName = variants.shouldSwapOrder()
                                            ? variants.getVariant() + "_" + material.getMaterial() + "_block"
                                            : material.getMaterial() + "_" + variants.getVariant() + "_block";
                                    Block targetBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(targetBlockName).get();

                                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredientBlock), RecipeCategory.BUILDING_BLOCKS, targetBlock, 1)
                                            .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                            .save(pWriter, locationCorrection(targetBlockName + "_from_petrified_stonecutting"));
                                }

                                StoneBasedDecorationBlock.Variant cobbledVar = StoneBasedDecorationBlock.Variant.COBBLED;
                                String cobbledName = cobbledVar.shouldSwapOrder()
                                        ? cobbledVar.getVariant() + "_" + material.getMaterial() + "_block"
                                        : material.getMaterial() + "_" + cobbledVar.getVariant() + "_block";
                                Block cobbledBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(cobbledName).get();

                                blockSmelting(pWriter, List.of(cobbledBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 200, "jsg_decor_petrified");
                                blockBlasting(pWriter, List.of(cobbledBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 100, "jsg_decor_petrified");

                            } else if (variant == StoneBasedDecorationBlock.Variant.COBBLED) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 8)
                                        .pattern("###")
                                        .pattern("x#x")
                                        .pattern("###")
                                        .define('#', Blocks.COBBLESTONE)
                                        .define('x', nugget)
                                        .unlockedBy("has_" + ingredientName + "_nugget", has(nugget))
                                        .save(pWriter, locationCorrection(ingredientName + "_crafting"));

                            } else if (variant == StoneBasedDecorationBlock.Variant.BIG_BRICKS) {
                                StoneBasedDecorationBlock.Variant petrifiedVar = StoneBasedDecorationBlock.Variant.PETRIFIED;
                                String petrifiedName = petrifiedVar.shouldSwapOrder()
                                        ? petrifiedVar.getVariant() + "_" + material.getMaterial() + "_block"
                                        : material.getMaterial() + "_" + petrifiedVar.getVariant() + "_block";
                                Block petrifiedBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(petrifiedName).get();

                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 4)
                                        .pattern("##")
                                        .pattern("##")
                                        .define('#', petrifiedBlock)
                                        .unlockedBy("has_" + ingredientName, has(ingredientBlock))
                                        .save(pWriter, locationCorrection(ingredientName + "_crafting"));

                            } else if (variant == StoneBasedDecorationBlock.Variant.SMOOTH) {
                                StoneBasedDecorationBlock.Variant petrifiedVar = StoneBasedDecorationBlock.Variant.PETRIFIED;
                                String petrifiedName = petrifiedVar.shouldSwapOrder()
                                        ? petrifiedVar.getVariant() + "_" + material.getMaterial() + "_block"
                                        : material.getMaterial() + "_" + petrifiedVar.getVariant() + "_block";
                                Block petrifiedBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(petrifiedName).get();

                                blockSmelting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 200, "jsg_decor_smooth");
                                blockBlasting(pWriter, List.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 100, "jsg_decor_smooth");

                            } else if (variant == StoneBasedDecorationBlock.Variant.MONOLITHIC) {
                                StoneBasedDecorationBlock.Variant smoothVar = StoneBasedDecorationBlock.Variant.SMOOTH;
                                String smoothName = smoothVar.shouldSwapOrder()
                                        ? smoothVar.getVariant() + "_" + material.getMaterial() + "_block"
                                        : material.getMaterial() + "_" + smoothVar.getVariant() + "_block";
                                Block smoothBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(smoothName).get();

                                blockSmelting(pWriter, List.of(smoothBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 200, "jsg_decor_monolithic");
                                blockBlasting(pWriter, List.of(smoothBlock), RecipeCategory.BUILDING_BLOCKS, ingredientBlock, 0.1f, 100, "jsg_decor_monolithic");
                            }
                        }
                    }
                }
            }
        }

        //Atlantis decoration blocks

        for (AtlantisDecorationBlocks.Material material : AtlantisDecorationBlocks.Material.values()) {
            for (AtlantisDecorationBlocks.Variant variant : AtlantisDecorationBlocks.Variant.values()) {
                if (!material.hasVariants() && variant != AtlantisDecorationBlocks.Variant.DEFAULT) { continue; }

                String mainShape = material.isGlass() ? "_glass_block" : "_block";
                String inputBlockName = (variant == AtlantisDecorationBlocks.Variant.DEFAULT) ? material.getMaterial() + mainShape : variant.getVariant() + "_" + material.getMaterial() + mainShape;

                Block inputBlock = JSGDecorBlocks.ATLANTIS_BLOCKS.get(inputBlockName).get();

                for (AtlantisDecorationBlocks.Shape shape : AtlantisDecorationBlocks.Shape.values()) {

                    String craftedBlockName = (variant == AtlantisDecorationBlocks.Variant.DEFAULT) ? material.getMaterial() + "_" + shape.getShape() : variant.getVariant() + "_" + material.getMaterial() + "_" + shape.getShape();

                    switch (shape) {
                        case GLASS_BLOCK -> {
                            if (!material.isGlass()) continue;
                            Ingredient inputIngredient = getGlassInputIngredient(material.getDyeColor());

                            SingleItemRecipeBuilder.stonecutting(inputIngredient, RecipeCategory.BUILDING_BLOCKS, inputBlock)
                                    .unlockedBy("has_glass", has(Items.GLASS))
                                    .save(pWriter, locationCorrection(craftedBlockName + "_stonecutting"));
                        }

                        case GLASS_PANE -> {
                            if (!material.isGlass()) continue;
                            Block craftedPane = JSGDecorBlocks.ATLANTIS_BLOCKS.get(craftedBlockName).get();

                            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, craftedPane, 16)
                                    .pattern("###")
                                    .pattern("###")
                                    .define('#', inputBlock)
                                    .unlockedBy("has_" + inputBlockName, has(inputBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName));
                        }

                        case SLAB -> {
                            if (material.isGlass()) continue;

                            Block craftedSlab = JSGDecorBlocks.ATLANTIS_BLOCKS.get(craftedBlockName).get();

                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedSlab, 6)
                                    .pattern("###")
                                    .define('#', inputBlock)
                                    .unlockedBy("has_" + inputBlockName, has(inputBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(inputBlock), RecipeCategory.BUILDING_BLOCKS, craftedSlab, 2)
                                    .unlockedBy("has_" + inputBlockName, has(inputBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName + "_stonecutting"));
                        }

                        case STAIRS -> {

                            if (material.isGlass()) continue;

                            Block craftedStairs = JSGDecorBlocks.ATLANTIS_BLOCKS.get(craftedBlockName).get();

                            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedStairs, 4)
                                    .pattern("#  ")
                                    .pattern("## ")
                                    .pattern("###")
                                    .define('#', inputBlock)
                                    .unlockedBy("has_" + inputBlockName, has(inputBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName));

                            SingleItemRecipeBuilder.stonecutting(Ingredient.of(inputBlock), RecipeCategory.BUILDING_BLOCKS, craftedStairs, 1)
                                    .unlockedBy("has_" + inputBlockName, has(inputBlock))
                                    .save(pWriter, locationCorrection(craftedBlockName + "_stonecutting"));
                        }

                        case BLOCK -> {
                            if (material.isGlass()) continue;

                            // Sem přijdou speciální recepty pro základní bloky z terracotty nebo jiných surovin
                            // Příklad pro tvé hnědé popsané bloky, pokud se mají craftit z obyčejné hnědé terracotty:
                            if (material == AtlantisDecorationBlocks.Material.BROWN_SPARSELY_WRITTEN_BLOCK) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, inputBlock, 4)
                                        .pattern("##")
                                        .pattern("##")
                                        .define('#', Items.BROWN_TERRACOTTA)
                                        .unlockedBy("has_brown_terracotta", has(Items.BROWN_TERRACOTTA))
                                        .save(pWriter, locationCorrection(craftedBlockName));
                            }
                            //todo: this:
                            /**
                             * takže zde jsou recepty bloků které je třeba ještě napsat! je to vždy cílový blok: metoda, crafting recept
                             *
                             * atlantis blue wall: crafting, light blue dokola u uvnitř cyan barvivo, 8
                             * atlantis blue wall varianty: stonecutter z hlavního bloku
                             * atlantis lamp off: crafting, atlantis blue wall kolem dokola a white dye, 8
                             * atlantis lamp on: crafting stejně jako off lamp, ale ve středu je glowstone, 9
                             * white lamp off: stonecutter z white concrete
                             * white lamp on: crafting, dokola white lamp off a doprostřed glowstone, 9
                             * brown wall a written bloky: stonecutter s brown terracoty
                             * atlantis gray wall: crafting do kříže gray concrete a do rohu titanium nuggety, 5
                             * atlantis gray wall varianty: stonecutter, z cisteho gray wallu
                             * zjistit co se dojebe na starých světech a udělat datafix
                        }
                    }
                }
            }
        }*/

    private Ingredient getGlassInputIngredient(DyeColor color) {
        if (color == null) { return Ingredient.of(Tags.Items.GLASS_COLORLESS); }

        TagKey<Item> forgeTag = switch (color) {
            case LIME -> Tags.Items.GLASS_LIME;
            case RED -> Tags.Items.GLASS_RED;
            case BLUE -> Tags.Items.GLASS_BLUE;
            default -> Tags.Items.GLASS_COLORLESS;
        };
        return Ingredient.of(forgeTag);
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
