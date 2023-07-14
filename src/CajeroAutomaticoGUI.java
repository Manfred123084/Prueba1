
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jafet
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CajeroAutomaticoGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CajeroAutomaticoGUI frame = new CajeroAutomaticoGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CajeroAutomaticoGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setBounds(10, 10, 70, 14);
        contentPane.add(lblBalance);
        
        JLabel lblAmount = new JLabel("Ingrese la cantidad:");
        lblAmount.setBounds(10, 50, 120, 14);
        contentPane.add(lblAmount);
        
        textField = new JTextField();
        textField.setBounds(140, 47, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnWithdraw = new JButton("Retirar");
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountText = textField.getText();
                double amount = Double.parseDouble(amountText);
                // Lógica para retirar dinero de la cuenta
                textField.setText("");
            }
        });
        btnWithdraw.setBounds(10, 90, 89, 23);
        contentPane.add(btnWithdraw);
        
        JButton btnDeposit = new JButton("Depositar");
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountText = textField.getText();
                double amount = Double.parseDouble(amountText);
                // Lógica para depositar dinero en la cuenta
                textField.setText("");
            }
        });
        btnDeposit.setBounds(120, 90, 89, 23);
        contentPane.add(btnDeposit);
    }
}



