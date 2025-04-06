/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper<T> {
    private final String filename;
    private final Type type;
    private final Gson gson;

    public JsonHelper(String filename, Type type) {
        this.filename = filename;
        this.type = type;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<T> loadList() {
        try (FileReader reader = new FileReader(filename)) {
            List<T> list = gson.fromJson(reader, type);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveList(List<T> list) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(list, type, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
