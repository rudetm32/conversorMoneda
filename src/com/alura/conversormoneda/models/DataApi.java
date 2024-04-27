package com.alura.conversormoneda.data;

public record DataApi(
        String base_code,
        String target_code,
        double conversion_rate,
        String time_last_update_utc) {
    }

