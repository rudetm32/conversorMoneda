package com.alura.conversormoneda.data;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    String codeBase;
    String codeTarget;


    public ConsultaApi(String codeBase, String codeTarget) {
        this.codeBase = codeBase;
        this.codeTarget = codeTarget;
    }

    public String getCodeBase() {
        return codeBase;
    }

    public String getCodeTarget() {
        return codeTarget;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public void setCodeTarget(String codeTarget) {
        this.codeTarget = codeTarget;
    }

    public  DataApi consultaDivisa (String codeBase, String codeTarget){

            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/be578294e20cbc92066ad4d1/pair/" + codeBase + "/" + codeTarget) ;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(direccion)
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), DataApi.class);

            } catch (Exception e) {
                throw new RuntimeException(e);

            }
    }

}
