package user11681.scale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class ScaleConfig {
    public static final ScaleConfig INSTANCE = new ScaleConfig("scale.json");

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(ScaleConfig.class, new ScaleConfigSerializer()).create();
    private static final JsonParser PARSER = new JsonParser();

    private final File file;

    public boolean enabled;

    public ScaleConfig(final String path) {
        this.file = new File(FabricLoader.getInstance().getConfigDirectory(), path);
        this.enabled = true;
    }

    public void write() throws Throwable {
        ((OutputStream) new FileOutputStream(this.file)).write(GSON.toJson(this).getBytes());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void read() throws Throwable {
        if (this.file.createNewFile()) {
            this.write();
        } else {
            final InputStream input = new FileInputStream(this.file);
            final byte[] content = new byte[input.available()];

            while (input.read(content) > -1);

            final JsonObject element = (JsonObject) PARSER.parse(new String(content));

            this.enabled = element.get("enabled").getAsBoolean();
        }
    }

    public static class ScaleConfigSerializer implements JsonSerializer<ScaleConfig> {
        @Override
        public JsonElement serialize(final ScaleConfig src, final Type typeOfSrc, final JsonSerializationContext context) {
            final JsonObject object = new JsonObject();

            object.addProperty("enabled", src.enabled);

            return object;
        }
    }
}
