package com.rafael.fernandes.data.database.repositories;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.rafael.fernandes.domain.model.Owner;

public class Converters {
    @TypeConverter
    public static Owner fromString(String value) {
        return new Gson().fromJson(value, Owner.class);
    }

    @TypeConverter
    public static String fromOwner(Owner owner) {
        Gson gson = new Gson();
        String json = gson.toJson(owner);
        return json;
    }
}
