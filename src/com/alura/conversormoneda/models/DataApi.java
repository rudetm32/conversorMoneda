package com.alura.conversormoneda.models;


public record DataApi(
        String base_code,
        String target_code,
        double conversion_rate,
        String time_last_update_utc) {

    public double multiplicarPorConversionRate(double cantidad) {
        double resultado = cantidad * conversion_rate;
        double resultadoRedondeado = Math.round(resultado * 100.0) / 100.0;
        return resultadoRedondeado;
    }
}

