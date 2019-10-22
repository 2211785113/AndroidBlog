package com.example.ruru.sophiesblog.android.network.volley.gson;

import com.example.ruru.sophiesblog.android.network.volley.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class GsonUtilNew {
    private static GsonUtilNew sInstance = new GsonUtilNew();

    private GsonBuilder builder;

    private GsonUtilNew() {

        builder = new GsonBuilder();
        //gson解析时间：Date的类型转换器
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        //        builder.registerTypeAdapter(Double.class, new DoubleDefault0Adapter());
    }

    //gson：double类型转换器
    /*public class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为double类型,如果后台返回""或者null,则返回0.00
                    return 0.00;
                }
            } catch (Exception ignore) {
            }
            try {
                return json.getAsDouble();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }*/

    public static GsonUtilNew init() {
        return sInstance;
    }

    public Result result(String response, Type type) {
        Gson gson = builder.create();
        return gson.fromJson(response, type);
    }
}
