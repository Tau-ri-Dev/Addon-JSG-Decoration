package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.datagen.loot.JSGDecorLootTableProvider;
import dev.tauri.jsgdecor.datagen.tag.JSGDecorBiomeTagGenerator;
import dev.tauri.jsgdecor.datagen.tag.JSGDecorBlockTagGenerator;
import dev.tauri.jsgdecor.datagen.tag.JSGDecorItemTagGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = JSGDecor.MOD_ID)
public class JSGDecorDataGenerators {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var exFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), JSGDecorLootTableProvider.create(output, lookupProvider));

        generator.addProvider(event.includeServer(), new JSGDecorRecipeProvider(output, lookupProvider));

        var blockTagGenerator = generator.addProvider(event.includeServer(), new JSGDecorBlockTagGenerator(output, lookupProvider, exFileHelper));
        generator.addProvider(event.includeServer(), new JSGDecorItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), exFileHelper));

        generator.addProvider(event.includeServer(), new JSGDecorBiomeTagGenerator(output, lookupProvider, exFileHelper));

        generator.addProvider(event.includeServer(), new JSGDecorWorldGenProvider(output, lookupProvider));

        generator.addProvider(event.includeClient(), new JSGDecorBlockStateProvider(output, exFileHelper));

        generator.addProvider(event.includeClient(), new JSGDecorItemModelProvider(output, exFileHelper));

        generator.addProvider(event.includeClient(), new JSGDecorLanguageProvider(output, "en_us"));
    }
}
