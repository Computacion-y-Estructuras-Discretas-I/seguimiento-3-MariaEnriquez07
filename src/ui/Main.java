package ui;

import java.util.Scanner;
import structures.PilaGenerica;
import structures.TablasHash;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public void ejecutar() throws Exception {
        while (true) {
            System.out.println("\nSeleccione la opcion:");
            System.out.println("1. Punto 1, Verificar balanceo de expresión");
            System.out.println("2. Punto 2, Encontrar pares con suma objetivo");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese expresion a verificar:");
                    String expresion = sc.nextLine();
                    boolean resultado = verificarBalanceo(expresion);
                    System.out.println("Resultado: " + (resultado ? "TRUE" : "FALSE"));
                    break;

                case 2:
                    System.out.println("Ingrese numeros separados por espacio: ");
                    String lineaNumeros = sc.nextLine();
                    System.out.println("Ingrese suma objetivo: ");
                    int objetivo = Integer.parseInt(sc.nextLine());

                    String[] partes = lineaNumeros.trim().split("\\s+");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        numeros[i] = Integer.parseInt(partes[i]);
                    }

                    encontrarParesConSuma(numeros, objetivo);
                    break;

                case 3:
                    System.out.println("Chao");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no permitida");
            }
        }
    }


    /**
     * Verifica si la expresion esta balanceada usando PilaGenerica
     * @param s expresion a verificar
     * @return true si esta balanceada, false si no
     */
    public boolean verificarBalanceo(String s) {
    boolean verify = false;
    PilaGenerica<String> pilaEntrada = new PilaGenerica<>(s.length());


    char[] arregloCaracteres = s.toCharArray();
    for (int j = 0; j < arregloCaracteres.length; j++) {
        pilaEntrada.Push(String.valueOf(arregloCaracteres[j]));
    }

    for (int index = s.length() - 1; index >= 0; index--) {
        String c = pilaEntrada.getArreglo()[index];

        if (c.equals("}") || c.equals("]") || c.equals(")")) {

            // Asegurarse de que la pila no esté vacía antes de usar getTop()

        int topIndex = pilaEntrada.getTop();
        String tope = pilaEntrada.getArreglo()[topIndex];

                if ((c.equals("}") && tope.equals("{")) ||
                    (c.equals("]") && tope.equals("[")) ||
                    (c.equals(")") && tope.equals("("))) {

                    String temp = pilaEntrada.Pop();

                    if (temp.equals(tope)) {
                        verify = true;
                        continue; // ir al siguiente caracter
                    } else {
                        return false;
                    }
                
                } else {
                pilaEntrada.Pop();
            }
        } else {
            return false;
        }
    }

    return verify;
}

    /**
     * Encuentra y muestra todos los pares unicos de numeros que sumen objetivo usando TablasHash.
     * @param numeros arreglo de numeros enteros
     * @param objetivo suma objetivo
     * @throws Exception 
     */
    public void encontrarParesConSuma(int[] numeros, int objetivo) throws Exception {
        TablasHash tablaPrincipal = new TablasHash(numeros.length);
        for (int i = 0; i < numeros.length; i++) {
            tablaPrincipal.insert(numeros[i], numeros[i]); // insertar valor en la tabla
        }

        


    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.ejecutar();
    }
}
