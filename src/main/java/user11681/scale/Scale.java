package user11681.scale;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.BoundedDiscrete;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Excluded;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.options.GameOptions;

@Config(name = "scale")
public class Scale implements ConfigData, ClientModInitializer, ModMenuApi {
    @Excluded
    public static transient Scale instance;

    @BoundedDiscrete(min = 1, max = 6)
    public int minimum = 2;

    @BoundedDiscrete(min = 1, max = 6)
    public int maximum = 4;

    @Override
    public void onInitializeClient() {
        instance = AutoConfig.register(Scale.class, ScaleTomlConfigSerializer::new).get();
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (Screen parent) -> AutoConfig.getConfigScreen(Scale.class, parent).get();
    }

    public void reload() {
        reload(MinecraftClient.getInstance().options);
    }

    public void reload(GameOptions options) {
        if (options != null) {
            if (options.guiScale == 0) {
                options.guiScale = instance.minimum;
            } else if (options.guiScale > instance.maximum) {
                options.guiScale = instance.maximum;
            }
        }
    }
}
