/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jafet
 */
import java.util.Scanner;

public class CajeroAutomaticoP {
    private static final int MAX_CUENTAS = 5;
    private static final String[] NUMEROS_DE_CUENTA = {"1234", "5678", "9012", "3456", "7890"};
    private static final String[] PINES = {"1111", "2222", "3333", "4444", "5555"};
    private static final double[] SALDOS = {1000.0, 2000.0, 3000.0, 4000.0, 5000.0};

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean usuarioAutenticado = false;
        int indiceCuentaAutenticada = -1;

        mostrarMensajeBienvenida();

        while (!usuarioAutenticado) {
            System.out.print("Introduce el número de cuenta: ");
            String numeroCuenta = scanner.nextLine();

            System.out.print("Introduce el PIN: ");
            String pin = scanner.nextLine();

            indiceCuentaAutenticada = autenticarUsuario(numeroCuenta, pin);

            if (indiceCuentaAutenticada != -1) {
                usuarioAutenticado = true;
                mostrarMenuPrincipal();
            } else {
                System.out.println("Número de cuenta o PIN incorrecto. Inténtalo de nuevo.\n");
            }
        }

        int opcion;
        do {
            opcion = obtenerOpcionMenu();
            switch (opcion) {
                case 1:
                    mostrarBalance(indiceCuentaAutenticada);
                    break;
                case 2:
                    retirarDinero(indiceCuentaAutenticada);
                    break;
                case 3:
                    depositarDinero(indiceCuentaAutenticada);
                    break;
                case 4:
                    System.out.println("Gracias por utilizar el cajero automático. Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.\n");
                    break;
            }
        } while (opcion != 4);
    }

    private static void mostrarMensajeBienvenida() {
        System.out.println("Bienvenido al Cajero Automático");
        System.out.println("--------------------------------");
    }

    private static int autenticarUsuario(String numeroCuenta, String pin) {
        for (int i = 0; i < MAX_CUENTAS; i++) {
            if (NUMEROS_DE_CUENTA[i].equals(numeroCuenta) && PINES[i].equals(pin)) {
                return i;
            }
        }
        return -1;
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\nMenú Principal:");
        System.out.println("1. Ver balance");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Depositar dinero");
        System.out.println("4. Salir\n");
    }

    private static int obtenerOpcionMenu() {
        System.out.print("Introduce una opción: ");
        return scanner.nextInt();
    }

    private static void mostrarBalance(int indiceCuenta) {
        double saldo = SALDOS[indiceCuenta];
        System.out.println("\nBalance: $" + saldo + "\n");
    }

    private static void retirarDinero(int indiceCuenta) {
        System.out.print("Introduce la cantidad a retirar: ");
        double cantidad = scanner.nextDouble();

        double saldo = SALDOS[indiceCuenta];
        if (cantidad <= saldo) {
            saldo -= cantidad;
            SALDOS[indiceCuenta] = saldo;
            System.out.println("Retiraste $" + cantidad + " de tu cuenta. Nuevo saldo: $" + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente. No se pudo completar la transacción.\n");
        }
    }

    private static void depositarDinero(int indiceCuenta) {
        System.out.print("Introduce la cantidad a depositar: ");
        double cantidad = scanner.nextDouble();

        double saldo = SALDOS[indiceCuenta];
        saldo += cantidad;
        SALDOS[indiceCuenta] = saldo;
        System.out.println("Depositaste $" + cantidad + " en tu cuenta. Nuevo saldo: $" + saldo + "\n");
    }
}
