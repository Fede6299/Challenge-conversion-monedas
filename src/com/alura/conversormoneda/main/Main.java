package com.alura.conversormoneda.main;

import com.alura.conversormoneda.models.Currency;
import com.alura.conversormoneda.models.FileGenerator;

import com.alura.conversormoneda.models.RequestAPI;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option;
        double value;
        boolean exit = true;
        while (exit) {
            System.out.println("""
                    ***************************************************
                    Sea Bienvenido/a al Conversor de Moneda =]
                    
                    1) Dólar ==> Peso Argentino
                    2) Peso Argentina ==> Dólar
                    3) Dólar ==> Real Brasileño
                    4) Real Brasileño ==> Dólar
                    5) Salir
                    
                    Elija una opción válida:
                    ***************************************************
                    """);
            option = sc.nextLine();
            if (option.equalsIgnoreCase("salir")) {
                System.out.println("Programa Finalizado, gracias por usar el conversor de monedas.");
                exit = false;
            }
            else {
                System.out.println("Ingrese el valor que desea convertir:");
                value = Double.parseDouble(sc.nextLine());

                switch (Integer.parseInt(option)) {
                    case 1:
                        calculate_currency("USD", "ARS", value);
                        break;
                    case 2:
                       calculate_currency("ARS", "USD", value);
                        break;
                    case 3:
                        calculate_currency("USD", "BRL", value);
                        break;
                    case 4:
                        calculate_currency("BRL", "USD", value);
                        break;
                    default:
                        System.out.println("Opción equivocada. Se finaliza el programa");
                        exit = false;
                        break;
                }
            }
        }
        sc.close();
    }

    private static void calculate_currency(String input_currency, String output_currency, double value){
        // Crear una instancia de RequestAPI
        RequestAPI api = new RequestAPI();
        try {
            DecimalFormat df = new DecimalFormat("#.###");
            // Obtener los datos de la API
            Currency currency = api.searchCurrency(input_currency);
            System.out.println(
                    "El valor " +
                            df.format(value) + " [" + input_currency  + "] " + " corresponde el valor final de ==> " +
                            df.format(currency.conversion_rates().get(output_currency) * value) + " [" + output_currency + "]");
            System.out.println();
            FileGenerator generator = new FileGenerator();
            generator.saveJSON(currency);
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consulte(Currency currency) {
        System.out.println("Base Code: " + currency.base_code());
        for (Map.Entry<String, Double> entry : currency.conversion_rates().entrySet()) {
            System.out.println("Moneda: " + entry.getKey() + ", Tasa: " + entry.getValue()); }
    }
}