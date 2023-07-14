/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jafet
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CajeroAutomatico {
    private static final int MAX_CUENTAS = 5;
    private static final String[] NUMEROS_DE_CUENTA = {"1234", "5678", "9012", "3456", "7890"};
    private static final String[] PINES = {"1111", "2222", "3333", "4444", "5555"};
    private static final double[] SALDOS = {1000.0, 2000.0, 3000.0, 4000.0, 5000.0};
    private static final double[] SALDOS_DOLARES = {0.0, 0.0, 0.0, 0.0, 0.0};
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean usuarioAutenticado = false;
        int indiceCuentaAutenticada = -1;
        boolean primeraSesion = false;

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
        
        if (esPrimerInicioSesion(indiceCuentaAutenticada)) {
            actualizarPIN(indiceCuentaAutenticada);
            primeraSesion = true;
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
                    comprarOVenderDolares(indiceCuentaAutenticada);
                    break;
                case 5:
                    System.out.println("Gracias por utilizar el cajero automático. Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.\n");
                    break;
            }
            
            if (!primeraSesion) {
                mostrarMenuPrincipal();
            }
        } while (opcion != 5);
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
    
    private static boolean esPrimerInicioSesion(int indiceCuenta) {
        String pin = PINES[indiceCuenta];
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(pin);
        return matcher.matches();
    }
    
    private static void actualizarPIN(int indiceCuenta) {
        String nuevaContraseña = "";
        boolean contraseñaValida = false;
        
        while (!contraseñaValida) {
            System.out.print("Introduce una nueva contraseña alfanumérica (al menos 8 caracteres, una mayúscula, una minúscula y 4 números): ");
            nuevaContraseña = scanner.nextLine();
            contraseñaValida = validarContraseña(nuevaContraseña);
            
            if (!contraseñaValida) {
                System.out.println("La contraseña no cumple con los requisitos de seguridad. Inténtalo de nuevo.\n");
            }
        }
        
        PINES[indiceCuenta] = nuevaContraseña;
    }
    
    private static boolean validarContraseña(String contraseña) {
        if (contraseña.length() < 8) {
            return false;
        }
        
        if (!contraseña.matches(".*[A-Z].*")) {
            return false;
        }
        
        if (!contraseña.matches(".*[a-z].*")) {
            return false;
        }
        
        if (!contraseña.matches(".*\\d{4}.*")) {
            return false;
        }
        
        if (contraseña.matches(".*[a-zA-Z0-9]{3}.*")) {
            return false;
        }
        
        return true;
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\nMenú Principal:");
        System.out.println("1. Ver balance");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Depositar dinero");
        System.out.println("4. Comprar o vender dólares");
        System.out.println("5. Salir\n");
    }
    
    private static int obtenerOpcionMenu() {
        System.out.print("Introduce una opción: ");
        return scanner.nextInt();
    }
    
    private static void mostrarBalance(int indiceCuenta) {
        double saldo = SALDOS[indiceCuenta];
        double saldoDolares = SALDOS_DOLARES[indiceCuenta];
        
        System.out.println("\nBalance en cuentas:");
        System.out.println("Cuenta en pesos: $" + saldo);
        System.out.println("Cuenta en dólares: $" + saldoDolares + "\n");
    }
    
    private static void retirarDinero(int indiceCuenta) {
        System.out.println("\nOpciones de retiro:");
        System.out.println("1. $100");
        System.out.println("2. $200");
        System.out.println("3. $500");
        System.out.println("4. $1000");
        System.out.println("5. Cancelar");
        
        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();
        
        double cantidad = 0.0;
        
        switch (opcion) {
            case 1:
                cantidad = 100.0;
                break;
            case 2:
                cantidad = 200.0;
                break;
            case 3:
                cantidad = 500.0;
                break;
            case 4:
                cantidad = 1000.0;
                break;
            case 5:
                System.out.println("Operación cancelada.\n");
                return;
            default:
                System.out.println("Opción inválida. Operación cancelada.\n");
                return;
        }
        
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
        System.out.println("\nOpciones de depósito:");
        System.out.println("1. $100");
        System.out.println("2. $200");
        System.out.println("3. $500");
        System.out.println("4. $1000");
        System.out.println("5. Cancelar");
        
        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();
        
        double cantidad = 0.0;
        
        switch (opcion) {
            case 1:
                cantidad = 100.0;
                break;
            case 2:
                cantidad = 200.0;
                break;
            case 3:
                cantidad = 500.0;
                break;
            case 4:
                cantidad = 1000.0;
                break;
            case 5:
                System.out.println("Operación cancelada.\n");
                return;
            default:
                System.out.println("Opción inválida. Operación cancelada.\n");
                return;
        }
        
        double saldo = SALDOS[indiceCuenta];
        saldo += cantidad;
        SALDOS[indiceCuenta] = saldo;
        System.out.println("Depositaste $" + cantidad + " en tu cuenta. Nuevo saldo: $" + saldo + "\n");
    }
    
    private static void comprarOVenderDolares(int indiceCuenta) {
        double saldoDolares = SALDOS_DOLARES[indiceCuenta];
        
        if (saldoDolares == 0.0) {
            crearCuentaDolares(indiceCuenta);
        }
        
        System.out.println("\nOperaciones en dólares:");
        System.out.println("1. Comprar dólares");
        System.out.println("2. Vender dólares");
        System.out.println("3. Cancelar");
        
        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();
        
        switch (opcion) {
            case 1:
                comprarDolares(indiceCuenta);
                break;
            case 2:
                venderDolares(indiceCuenta);
                break;
            case 3:
                System.out.println("Operación cancelada.\n");
                break;
            default:
                System.out.println("Opción inválida. Operación cancelada.\n");
                break;
        }
    }
    
    private static void crearCuentaDolares(int indiceCuenta) {
        System.out.println("\nNo tienes una cuenta en dólares asociada. Se creará una nueva cuenta.");
        System.out.print("Ingresa la cantidad de dólares que deseas depositar: ");
        double cantidadDolares = scanner.nextDouble();
        
        SALDOS_DOLARES[indiceCuenta] = cantidadDolares;
        System.out.println("Se ha creado una cuenta en dólares asociada a tu cuenta en pesos.\n");
    }
    
    private static void comprarDolares(int indiceCuenta) {
        System.out.print("Ingresa la cantidad de dólares que deseas comprar: ");
        double cantidadDolares = scanner.nextDouble();
        
        double saldoDolares = SALDOS_DOLARES[indiceCuenta];
        saldoDolares += cantidadDolares;
        SALDOS_DOLARES[indiceCuenta] = saldoDolares;
        
        System.out.println("Has comprado $" + cantidadDolares + " en dólares. Nuevo saldo en dólares: $" + saldoDolares + "\n");
    }
    
    private static void venderDolares(int indiceCuenta) {
        System.out.print("Ingresa la cantidad de dólares que deseas vender: ");
        double cantidadDolares = scanner.nextDouble();
        
        double saldoDolares = SALDOS_DOLARES[indiceCuenta];
        
        if (cantidadDolares <= saldoDolares) {
            saldoDolares -= cantidadDolares;
            SALDOS_DOLARES[indiceCuenta] = saldoDolares;
            System.out.println("Has vendido $" + cantidadDolares + " en dólares. Nuevo saldo en dólares: $" + saldoDolares + "\n");
        } else {
            System.out.println("Saldo insuficiente en dólares. No se pudo completar la transacción.\n");
        }
    }
}
