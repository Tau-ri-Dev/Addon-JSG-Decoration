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
        //TODO: Smelting recipes Cobbled -> Petrified -> Smooth -> Monolithic
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            Item cobbledBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.COBBLED, StoneBasedDecorationBlock.Shape.BLOCK);
            Item petrifiedBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.PETRIFIED, StoneBasedDecorationBlock.Shape.BLOCK);
            Item bricksBlock = getStoneBlock(material, StoneBasedDecorationBlock.Variant.LARGE_BRICKS, StoneBasedDecorationBlock.Shape.BLOCK);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cobbledBlock, 8)
                    .group("cobbled_block")
                    .pattern("###")
                    .pattern("N#N")
                    .pattern("###")
                    .define('#', Blocks.COBBLESTONE)
                    .define('N', material.getNugget())
                    .unlockedBy("has_nugget", has(material.getNugget()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, petrifiedBlock, 8)
                    .group("petrified_block")
                    .pattern("###")
                    .pattern("N#N")
                    .pattern("###")
                    .define('#', Blocks.STONE)
                    .define('N', material.getNugget())
                    .unlockedBy("has_nugget", has(material.getNugget()))
                    .save(pWriter);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksBlock, 4)
                    .group("large_bricks_block")
                    .pattern("##")
                    .pattern("##")
                    .define('#', petrifiedBlock)
                    .unlockedBy("has_petrified_block", has(petrifiedBlock))
                    .save(pWriter);

            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {
                Item craftedBlock = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.BLOCK);
                Item craftedSlab = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.SLAB);
                Item craftedStairs = getStoneBlock(material, variant, StoneBasedDecorationBlock.Shape.STAIRS);

                String blockName = getStoneName(material, variant, StoneBasedDecorationBlock.Shape.BLOCK);
                String slabName = getStoneName(material, variant, StoneBasedDecorationBlock.Shape.SLAB);
                String stairsName = getStoneName(material, variant, StoneBasedDecorationBlock.Shape.STAIRS);

                if (variant != StoneBasedDecorationBlock.Variant.PETRIFIED) {
                    SingleItemRecipeBuilder.stonecutting(Ingredient.of(petrifiedBlock), RecipeCategory.BUILDING_BLOCKS, craftedBlock, 1)
                            .group(variant.getVariant() + "_block")
                            .unlockedBy("has_petrified", has(petrifiedBlock))
                            .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, blockName + "_stonecutting"));
                }

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedStairs, 4)
                        .group(variant.getVariant() + "_stairs")
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', craftedBlock)
                        .unlockedBy("has_block", has(craftedBlock))
                        .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, craftedSlab, 6)
                        .group(variant.getVariant() + "_slab")
                        .pattern("###")
                        .define('#', craftedBlock)
                        .unlockedBy("has_block", has(craftedBlock))
                        .save(pWriter);

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(craftedBlock), RecipeCategory.BUILDING_BLOCKS, craftedStairs, 1)
                        .group(variant.getVariant() + "_stairs")
                        .unlockedBy("has_block", has(craftedBlock))
                        .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, stairsName + "_stonecutting"));

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(craftedBlock), RecipeCategory.BUILDING_BLOCKS, craftedSlab, 2)
                        .group(variant.getVariant() + "_slab")
                        .unlockedBy("has_block", has(craftedBlock))
                        .save(pWriter, JSGMapping.rl(JSGDecor.MOD_ID, slabName + "_stonecutting"));
            }
        }

    }

    private Item getStoneBlock(StoneBasedDecorationBlock.Material mat, StoneBasedDecorationBlock.Variant var, StoneBasedDecorationBlock.Shape shape) {
        String name = var.shouldSwapOrder()
                ? var.getVariant() + "_" + mat.getMaterial() + "_" + shape.getShape()
                : mat.getMaterial() + "_" + var.getVariant() + "_" + shape.getShape();
        return STONE_BASED_DECORATION_BLOCKS.get(name).get().asItem();
    }

    private String getStoneName(StoneBasedDecorationBlock.Material mat, StoneBasedDecorationBlock.Variant var, StoneBasedDecorationBlock.Shape shape) {
        return var.shouldSwapOrder()
                ? var.getVariant() + "_" + mat.getMaterial() + "_" + shape.getShape()
                :  mat.getMaterial() + "_" + var.getVariant() + "_" + shape.getShape();
    }
/*
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
