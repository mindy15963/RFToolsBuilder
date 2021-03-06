package mcjty.rftoolsbuilder;

import mcjty.lib.base.ModBase;
import mcjty.rftoolsbuilder.setup.Config;
import mcjty.rftoolsbuilder.modules.builder.BuilderSetup;
import mcjty.rftoolsbuilder.modules.shield.ShieldSetup;
import mcjty.rftoolsbuilder.setup.ModSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(RFToolsBuilder.MODID)
public class RFToolsBuilder implements ModBase {

    public static final String MODID = "rftoolsbuilder";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public static RFToolsBuilder instance;

    public RFToolsBuilder() {
        instance = this;

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        BuilderSetup.register();
        ShieldSetup.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLCommonSetupEvent event) -> setup.init(event));
        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLClientSetupEvent event) -> setup.initClient(event));

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rftoolsbuilder-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rftoolsbuilder-common.toml"));
    }

    @Override
    public String getModId() {
        return MODID;
    }

    public static final String SHIFT_MESSAGE = "<Press Shift>";


    @Override
    public void openManual(PlayerEntity player, int bookIndex, String page) {
    }

    public static Item.Properties createStandardProperties() {
        return new Item.Properties().group(setup.getTab());
    }

}
