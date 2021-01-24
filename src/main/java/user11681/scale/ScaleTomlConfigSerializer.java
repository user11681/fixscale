package user11681.scale;

import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class ScaleTomlConfigSerializer extends Toml4jConfigSerializer<Scale> {
    public ScaleTomlConfigSerializer(Config definition, Class<Scale> configClass) {
        super(definition, configClass);
    }

    @Override
    public void serialize(Scale config) throws SerializationException {
        super.serialize(config);

        config.reload();
    }
}
