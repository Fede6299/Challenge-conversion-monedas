package com.alura.conversormoneda.models;

import java.util.Map;

public record Currency(String base_code, Map<String, Double> conversion_rates) {
}
