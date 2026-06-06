package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.datagen.tag.JSGDecorBlockTagGenerator;
import dev.tauri.jsgdecor.datagen.tag.JSGDecorItemTagGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JSGDecor.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDGDecorDataGenerators {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var exFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new JSGDecorRecipeProvider(output));

        var blockTagGenerator = generator.addProvider(event.includeServer(), new JSGDecorBlockTagGenerator(output, lookupProvider, exFileHelper));
        generator.addProvider(event.includeServer(), new JSGDecorItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), exFileHelper));


    }
}
