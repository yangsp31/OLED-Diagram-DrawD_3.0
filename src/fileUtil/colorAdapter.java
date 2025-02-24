package fileUtil;

import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;

// Adapters for using Java's Color objects in Gson
public class colorAdapter implements JsonSerializer<Color>, JsonDeserializer<Color> {
    @Override
    public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("r", src.getRed());
        jsonObject.addProperty("g", src.getGreen());
        jsonObject.addProperty("b", src.getBlue());

        return jsonObject;
    }

    @Override
    public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int r = jsonObject.get("r").getAsInt();
        int g = jsonObject.get("g").getAsInt();
        int b = jsonObject.get("b").getAsInt();

        return new Color(r, g, b);
    }
}
