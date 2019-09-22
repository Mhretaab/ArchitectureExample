package com.example.architectureexample.common.db.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

//TODO: create parameterized TypeConverter
public class ListConverter<T> implements Serializable {

    @TypeConverter
    public String fromList(List<T> list) {
        if (list == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<T>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }

    @TypeConverter
    public List<T> toList(String listString) {
        if (listString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = gson.fromJson(listString, type);
        return list;
    }

    public static class NoteConverter extends ListConverter<NoteConverter>{}
}