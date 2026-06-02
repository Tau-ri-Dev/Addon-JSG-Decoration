package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsg.core.common.registry.tag.CoreItemTags;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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

public class JSGDecorRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public JSGDecorRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //TODO: Při přepisu projít a nastavit správný tagy!!

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

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, BoatTypes.LEMON.getDrop(false))
                .group("boat")
                .pattern("# #")
                .pattern("###")
                .define('#', JSGDecorBlocks.LEMON_PLANKS.get())
                .unlockedBy("has_planks", has(JSGDecorBlocks.LEMON_PLANKS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, BoatTypes.LEMON.getDrop(true))
                .group("chest_boat")
                .requires(BoatTypes.LEMON.getDrop(false))
                .requires(Tags.Items.CHESTS_WOODEN)
                .unlockedBy("has_boat", has(BoatTypes.LEMON.getDrop(false)))
                .unlockedBy("has_chest", has(Tags.Items.CHESTS_WOODEN))
                .save(pWriter);

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

        //Atlantis decoration blocks

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get(), 8)
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
                .define('C', JSGDecorBlocks.CLEAR_WHITE_BLOCK.get())
                .unlockedBy("has_blue_block", has(JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get()))
                .unlockedBy("has_white_block", has(Blocks.WHITE_CONCRETE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.CLEAR_WHITE_BLOCK.get(), 8)
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
                .unlockedBy("has_glow_ink", has(Items.INK_SAC))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, JSGDecorBlocks.LIGHT_WALL_BLOCK.get())
                .group("jsg_decor:atlantis_solid")
                .pattern("TIT")
                .pattern("IRI")
                .pattern("TIT")
                //.define('#', )
                .define('#', Blocks.BROWN_TERRACOTTA)
                .define('#', Blocks.BROWN_TERRACOTTA)
                .unlockedBy("has_white_block", has(Blocks.BROWN_TERRACOTTA))
                .unlockedBy("has_glow_ink", has(Items.GLOW_INK_SAC))
                .unlockedBy("has_glow_ink", has(Items.INK_SAC))
                .save(pWriter);


    }

    @ParametersAreNonnullByDefault
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    @ParametersAreNonnullByDefault
    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    @ParametersAreNonnullByDefault
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, JSGDecor.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
