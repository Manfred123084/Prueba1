
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jafet
 */

    public class Cajero {
    public double saldo;

    private static String contra_usuario;
    private static String contrasena = "";

    public static String menu = "¿Que deseas realizar?\n\n" +
                                "    1.- Realizar un deposito.\n" +
                                "    2.- Realizar un retiro.\n" +
                                "    3.- Revisar tu saldo.\n" +
                                "    4.- Salir.";


    public Cajero() {
        saldo = 5000.0;
    }


    public Cajero(double saldo) {
        this.saldo = saldo;
    }


    static boolean pedirNip() {
        contra_usuario = JOptionPane.showInputDialog(
            null,
            "De su nip de 4 digitos:"
        );
        if (contra_usuario.equals("")) {
            return(true);
        } else {
            return(false);
        }
    }


    static double saldoInicial() {
        double saldo = Double.parseDouble(
            JOptionPane.showInputDialog(
                null,
                "¿Con que cantidad desea abrir su cuenta?"
            )
        );
        return saldo;
    }


    public void realizarDeposito() {
        double abono = Double.parseDouble(
            JOptionPane.showInputDialog(
                null,
                "¿Cuanto deaseas abonar?"
            )
        );
        saldo += abono;
    }


    public void realizarRetiro() {
        double retiro = Double.parseDouble(
            JOptionPane.showInputDialog(
                null,
                "¿Cuanto deseas retirar?"
            )
        );
        if ((saldo - retiro) >= 0) {
            saldo -= retiro;
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
        }
    }


    public void mostrarSaldo() {
        JOptionPane.showMessageDialog(null, "El saldo actual es: $" + saldo);
    }


    public static void main(String[] args) {
        while (true) {
            String opcion_1 = JOptionPane.showInputDialog(
                null,
                "Bienvenido al cajero!\n\n" +
                "Ya tiene usted una cuenta?\n" +
                "si\n" +
                "no"
            );

            if (opcion_1.equals("si")) {
                if (pedirNip() == true) {
                    Cajero cajero = new Cajero();

                    int opcion_2 = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null,
                            menu
                        )
                    );

                    switch (opcion_2) {
                        case 1:
                            cajero.realizarDeposito();
                            cajero.mostrarSaldo();
                            break;

                        case 2:
                            cajero.realizarRetiro();
                            cajero.mostrarSaldo();
                            break;

                        case 3:
                            cajero.mostrarSaldo();
                            break;

                        case 4:
                            JOptionPane.showMessageDialog(
                                null,
                                "Gracias por hacer uso del cajero."
                            );
                            System.exit(0);
                            break;

                        default:
                            JOptionPane.showMessageDialog(
                                null,
                                "Opcion incorrecta, " +
                                "intentelo de nuevo."
                            );
                            break;
                    }

                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Su nip es incorrecto."
                    );
                }

            } else if (opcion_1.equals("no")) {
                double saldo = saldoInicial();
                Cajero cajero = new Cajero(saldo);

                int opcion_2 = Integer.parseInt(
                    JOptionPane.showInputDialog(
                        null,
                        menu
                    )
                );


                switch (opcion_2) {
                    case 1:
                        cajero.realizarDeposito();
                        cajero.mostrarSaldo();
                        break;

                    case 2:
                        cajero.realizarRetiro();
                        cajero.mostrarSaldo();
                        break;

                    case 3:
                        cajero.mostrarSaldo();
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(
                            null,
                            "Gracias por hacer uso del cajero."
                        );
                        System.exit(0);
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                            null,
                            "Opcion incorrecta, " +
                            "intentelo de nuevo."
                        );
                        break;
                }

            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Opcion invalida, intente de nuevo!"
                );
            }
        }
    }
}

