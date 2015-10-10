/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Random;

/**
 *
 * @author Leo
 */
public class Game {

    public static final int BASICO = 8;
    public static final int NORMAL = 16;
    public static final int AVANZADO = 15;
    public static final boolean ES_VACIO = false;
    public static final boolean ES_BOMBA = true;
    public static int NUM_FILAS = 0;
    public static int NUM_COLUMNAS = 0;
    private int modo;
    public static int NUM_BOMBAS;
    private static Casillero matriz[][] = new Casillero['h']['h'];

    public Game() {
        this(Game.NORMAL);
    }

    public Game(int modo) {
        NUM_COLUMNAS = modo;
        NUM_FILAS = modo;
        switch (modo) {
            case NORMAL:
                this.NUM_BOMBAS = 40;
                break;
            case BASICO:
                this.NUM_BOMBAS = 10;
                break;
            case AVANZADO:
                this.NUM_BOMBAS = 99;
                NUM_COLUMNAS = 16;
                NUM_FILAS = 31;
                break;
        }
        initalizeGame();
    }

    public Game(int filas, int columnas, int bombas) {
        this.NUM_COLUMNAS = columnas;
        this.NUM_FILAS = filas;
        this.NUM_BOMBAS = bombas;
        initalizeGame();

    }

    public void initalizeGame() {
        llenarMatriz();
        asignarMinas();
        minasAlrededor();
    }

    void llenarMatriz() { //llena la matriz con casilleros
        Casillero casillero;
        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_COLUMNAS; j++) {
                casillero = new Casillero();
                casillero.setEstaMinado(false);
                matriz[i][j] = casillero;
            }

        }
    }

    void asignarMinas() {
        int contadorBombas = 0;
        boolean bombasCompletas = false;
        do {
            for (int i = 0; (i < NUM_FILAS) && !bombasCompletas; i++) {
                for (int j = 0; (j < NUM_COLUMNAS) && !bombasCompletas; j++) {
                    boolean minado = getRandomBoolean();
                    if (minado && !matriz[i][j].getEstaMinado()) {
                        matriz[i][j].setEstaMinado(true);
                        contadorBombas++;
                    }
                    bombasCompletas = (contadorBombas >= NUM_BOMBAS);
                }
            }
        } while (!bombasCompletas);
    }

    void minasAlrededor() {
        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_COLUMNAS; j++) {
                if (matriz[i][j].getEstaMinado() == false) {
                    if (i == 0 || i == NUM_FILAS - 1) {
                        if (i == 0) {
                            if (j == 0 || j == NUM_COLUMNAS - 1) {
                                if (j == 0) {
                                    matriz[i][j].setMinasAlrededor(minasEn(i, j + 1) + minasEn(i + 1, j) + minasEn(i + 1, j + 1));
                                }
                                if (j == NUM_COLUMNAS - 1) {
                                    matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i + 1, j - 1) + minasEn(i + 1, j));
                                }
                            }else{
                                matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i , j + 1) + minasEn(i+1, j-1) + minasEn(i+1, j+1)+ minasEn(i+1, j));
                            }
                        } else {
                            if (i == NUM_FILAS - 1) {
                                if (j == 0 || j == NUM_COLUMNAS - 1) {
                                    if (j == 0) {
                                        matriz[i][j].setMinasAlrededor(minasEn(i, j + 1) + minasEn(i - 1, j) + minasEn(i - 1, j + 1));
                                    }
                                    if (j == NUM_COLUMNAS - 1) {
                                        matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i - 1, j - 1) + minasEn(i - 1, j));
                                    }

                                }else{
                                       matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i , j + 1) + minasEn(i-1, j-1) + minasEn(i-1, j+1)+ minasEn(i-1, j));
                            }
                            }
                        }

                    }     else{
                    
                        if(j==0 || j== NUM_COLUMNAS -1){
                          if(j == 0){
                          matriz[i][j].setMinasAlrededor(minasEn(i, j + 1) + minasEn(i - 1, j) + minasEn(i - 1, j + 1) + minasEn(i + 1, j)+ minasEn(i + 1, j + 1));
                             
                          }else{
                          matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i - 1, j) + minasEn(i - 1, j - 1) + minasEn(i + 1, j)+ minasEn(i + 1, j - 1));
                          }
                        }else{
                           matriz[i][j].setMinasAlrededor(minasEn(i, j - 1) + minasEn(i - 1, j) + minasEn(i - 1, j - 1) + minasEn(i + 1, j) + minasEn(i + 1, j-1)+ minasEn(i -1 , j + 1) + minasEn(i , j + 1) + minasEn(i +1 , j + 1));
                        }
                    }               
                }
            }
        }

    }

    private int minasEn(int i, int j) {
        return (matriz[i][j].getEstaMinado()) ? 1 : 0;
    }

    void imprimeMatriz() {

        System.out.println("Nummero filas =" + this.NUM_FILAS);
        System.out.println("Nummero columnas =" + this.NUM_COLUMNAS);
        System.out.println("Nummero bombas =" + this.NUM_BOMBAS);
        for (int i = 0; i < this.NUM_COLUMNAS; i++) {
            for (int j = 0; j < this.NUM_FILAS; j++) {
                //System.out.print(matriz[i][j]);
                if (matriz[i][j].getEstaMinado() == true) {
                    System.out.print("x");
                } else {
                    System.out.print(matriz[i][j].getMinasAlrededor());
                }
            }
            System.out.println("");
        }
    }

//     private int getRandomInt(int min,int max){
//         Random rdm = new Random();
//         return rdm.nextInt(max-min + 1) + min;
//     }
    private boolean getRandomBoolean() {
        int min = 1;
        int max = 8;
        Random rdm = new Random();
        return ((rdm.nextInt(max - min + 1) + min) == 1);
    }

    public boolean buscarPosicion(int row, int column) {
        return matriz[row][column].getEstaMinado();
    }
    
    public static Casillero getCasilla(int i,int j) {
       return matriz[i][j];
    }

}
