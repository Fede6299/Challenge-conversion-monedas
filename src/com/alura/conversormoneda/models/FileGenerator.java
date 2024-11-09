package com.alura.conversormoneda.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public void saveJSON (Currency currency) throws IOException {
        FileWriter fw = new FileWriter(currency.base_code() + ".json");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        fw.write(gson.toJson(currency));
        fw.close();
    }
}
