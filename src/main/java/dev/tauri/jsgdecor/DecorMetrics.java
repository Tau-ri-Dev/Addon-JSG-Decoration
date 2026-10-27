package dev.tauri.jsgdecor;

import dev.faststats.ErrorTracker;
import dev.faststats.Metrics;
import dev.faststats.neoforge.NeoForgeContext;

import java.util.Arrays;

public class DecorMetrics {
    public static final ErrorTracker ERROR_TRACKER = ErrorTracker.contextAware()
            .addExclusionRule((thread, error) -> Arrays.stream(error.getStackTrace())
                    .noneMatch(element -> element.getClassName().startsWith(DecorMetrics.class.getPackageName() + ".")));


    private static final NeoForgeContext CONTEXT = new NeoForgeContext.Factory(JSGDecor.MOD_ID, "87301859979033b5697a49a4f1ddbc3a")
            .requireConsent()
            .errorTrackerService(ERROR_TRACKER)
            .metrics(Metrics.Factory::create)
            .create();

    public static void load() {
    }
}
