package io.github.simplycmd.skyfabrication;

import io.github.simplycmd.skyfabrication.registry.BlockRegistry;
import io.github.simplycmd.skyfabrication.registry.ItemRegistry;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "skyfabrication";
    public static final String MOD_NAME = "SkyFabrication";

    public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID + ":resource_pack");

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        RRPCallback.EVENT.register(a -> a.add(RESOURCE_PACK));
        WelcomeQuote();
        BlockRegistry.register();
        ItemRegistry.register();
    }

    public static void WelcomeQuote() {
        String[] quotes = { "Help me, Obi-Steve Kenobi. You’re my only hope.",
                "I'm just a simple man trying to make my way in the dimension-verse.",
                "Power! Unlimited power!", "Some men just want to watch the overworld burn.",
                "What doesn't kill you makes you blockier.", "I can do this all day.",
                "Doth Alex know you weareth her drapes?", "You feel an evil presence watching you...",
                "You feel vibrations from deep below...", "You feel a quaking from deep underground...",
                "This is going to be a terrible night...", "The air is getting colder around you...",
                "You feel the air getting colder around you...", "Shut up and Dig Gaiden!",
                "Sand is Overpowered", "A Creeper's Tale",
                "Small Blocks, Not for Children Under the Age of 5", "There is No Cow Layer",
                "Digger T' Blocks", "The Grass is Greener on This Side",
                "Minecraft Part 3: The Return of the Steve", "Zombassic Park", "Press alt-f4",
                "Dividing by zero", "Now in 1D", "May the blocks be with you", "Hold my Pho" };
        log(Level.INFO, quotes[(int) RandomNumber(0, quotes.length)]);
    }

    public static double RandomNumber(double max, double min) {
        return Math.random() * (max - min) + min;
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}