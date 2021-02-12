package io.github.simplycmd.skyfabrication;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "skyfabrication";
    public static final String MOD_NAME = "SkyFabrication";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        Blocks.RegisterBlocks();
        Items.RegisterItems();
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}