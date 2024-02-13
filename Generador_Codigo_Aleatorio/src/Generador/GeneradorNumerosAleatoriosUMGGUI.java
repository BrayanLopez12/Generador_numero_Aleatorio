package Generador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorNumerosAleatoriosUMGGUI extends JFrame implements ActionListener {
    private JButton btnGenerar;
    private JButton btnOrdenarAscendente;
    private JButton btnOrdenarDescendente;
    private JButton btnSalir;
    
    public GeneradorNumerosAleatoriosUMGGUI() {
        setTitle("Generador de Números Aleatorios UMG");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        btnGenerar = new JButton("Generar Números Aleatorios");
        btnOrdenarAscendente = new JButton("Ordenar Ascendente");
        btnOrdenarDescendente = new JButton("Ordenar Descendente");
        btnSalir = new JButton("Salir");

        btnGenerar.addActionListener(this);
        btnOrdenarAscendente.addActionListener(this);
        btnOrdenarDescendente.addActionListener(this);
        btnSalir.addActionListener(this);

        panel.add(btnGenerar);
        panel.add(btnOrdenarAscendente);
        panel.add(btnOrdenarDescendente);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GeneradorNumerosAleatoriosUMGGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerar) {
            generarNumerosAleatorios(1000000, -10000000, 10000000, "numeros_aleatorios.txt");
            JOptionPane.showMessageDialog(this, "Se han generado los números aleatorios.");
        } else if (e.getSource() == btnOrdenarAscendente) {
            ordenarNumerosAleatorios("numeros_aleatorios.txt", "numeros_ordenados_ascendente.txt", true);
            JOptionPane.showMessageDialog(this, "Números ordenados ascendente almacenados en 'numeros_ordenados_ascendente.txt'");
        } else if (e.getSource() == btnOrdenarDescendente) {
            ordenarNumerosAleatorios("numeros_aleatorios.txt", "numeros_ordenados_descendente.txt", false);
            JOptionPane.showMessageDialog(this, "Números ordenados descendente almacenados en 'numeros_ordenados_descendente.txt'");
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public static void generarNumerosAleatorios(int cantidad, int min, int max, String archivoSalida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            Random random = new Random();
            for (int i = 0; i < cantidad; i++) {
                int numeroAleatorio = random.nextInt(max - min + 1) + min;
                writer.write(Integer.toString(numeroAleatorio));
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    public static void ordenarNumerosAleatorios(String archivoEntrada, String archivoSalida, boolean ascendente) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                numeros.add(Integer.parseInt(linea));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Ordenar los números
        if (ascendente) {
            Collections.sort(numeros);
        } else {
            Collections.sort(numeros, Collections.reverseOrder());
        }

        // Guardar los números ordenados en un nuevo archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (int numero : numeros) {
                writer.write(Integer.toString(numero));
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo de salida: " + ex.getMessage());
        }
    }
}
