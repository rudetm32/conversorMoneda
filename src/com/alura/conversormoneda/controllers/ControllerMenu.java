package com.alura.conversormoneda.controllers;

import com.alura.conversormoneda.utils.ConsultaApi;
import com.alura.conversormoneda.models.DataApi;
import com.alura.conversormoneda.utils.GuardarConsultaApi;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ControllerMenu {
    private final Scanner scanner = new Scanner(System.in);

    ConsultaApi consultaTipoCambio;

    public void mostrarMenu() {
        try {
            int opcionMenu;
            do {
                System.out.println("\nConveror de monedas");
                System.out.println("*****************************");
                System.out.println("1. Dólar Estadounidense --> Peso Argentino");
                System.out.println("2. Dólar Estadounidense --> Peso Colombiano");
                System.out.println("3. Dólar Estadounidense --> Real Brasileño ");
                System.out.println("4. Dólar Estadounidense --> Peso Chileno");
                System.out.println("5. Dólar Estadounidense --> Peso Mexicano");
                System.out.println("6. Dólar Estadounidense --> Euro Unión Europea");
                System.out.println("7. Salir");
                System.out.println("*****************************\n");
                System.out.print("Elige una opción (1-7): ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingresa un número válido.");
                    scanner.next();
                }
                opcionMenu = scanner.nextInt();
                switch (opcionMenu) {

                    case 1, 2, 3, 4, 5, 6:
                        double cantidadAConvertir;
                        System.out.println("Ingrese la cantidad a convertir:");
                        while (true) {
                            if (scanner.hasNextInt()) {
                                cantidadAConvertir = scanner.nextInt();
                                break;
                            } else {
                                System.out.println("Por favor, ingrese un número entero válido.");
                                scanner.next();
                            }
                        }

                        String codeBase;
                        String codeTarget;

                        switch (opcionMenu) {
                            case 1:
                                codeBase = "USD";
                                codeTarget = "ARS";
                                break;
                            case 2:
                                codeBase = "USD";
                                codeTarget = "COP";
                                break;
                            case 3:
                                codeBase = "USD";
                                codeTarget = "BRL";
                                break;
                            case 4:
                                codeBase = "USD";
                                codeTarget = "CLP";
                                break;
                            case 5:
                                codeBase = "USD";
                                codeTarget = "MXN";
                                break;
                            case 6:
                                codeBase = "USD";
                                codeTarget = "EUR";
                                break;
                            default:
                                throw new IllegalArgumentException("Opción no válida.");
                        }

                        consultaTipoCambio = new ConsultaApi(codeBase, codeTarget);

                        DataApi resultado = consultaTipoCambio.consultaDivisa(codeBase, codeTarget);
                        double resultadoConversor = resultado.multiplicarPorConversionRate(cantidadAConvertir);
                        System.out.println(cantidadAConvertir+ " " + codeBase + " Equivale a un total de : " + resultadoConversor + " " + codeTarget);

                        GuardarConsultaApi guardarConsultaApi = new GuardarConsultaApi();
                        try {
                            guardarConsultaApi.archivoJson(resultado);
                        } catch (IOException e) {
                            System.out.println("Error al guardar los datos en el archivo JSON: " + e.getMessage());
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción del 1 al 6.");
                }
            } while (opcionMenu != 7);

        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingresa un número válido.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
