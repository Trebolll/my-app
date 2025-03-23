package ru.org.myapp.config.cadenceuber.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.ZoneOffset;

public class ZoneOffsetConverter implements JsonSerializer<ZoneOffset>, JsonDeserializer<ZoneOffset> {

    @Override
    public JsonElement serialize(ZoneOffset src, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(src.getId());
    }

    @Override
    public ZoneOffset deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        return ZoneOffset.of(json.getAsString());
    }
}