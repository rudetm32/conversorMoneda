package com.alura.conversormoneda.utils;

import com.alura.conversormoneda.models.DataApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GuardarConsultaApi {
    public void archivoJson(DataApi dataApi) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File archivo = new File("conversorMoneda.json");

        List<DataApi> resultado = new ArrayList<>();
        if (archivo.exists()) {
            try
                    (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                DataApi[] resultadosExistentes = gson.fromJson(reader, DataApi[].class);
                for (DataApi resultadoExistente : resultadosExistentes) {
                    resultado.add(resultadoExistente);
                }
            }
        }
        resultado.add(dataApi);
        try (FileWriter escritura = new FileWriter(archivo)) {
            gson.toJson(resultado, escritura);
        }
    }
}
