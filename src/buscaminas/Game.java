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
public final class Game {

    public static final int BASICO = 8;
    public static final int NORMAL = 16;
    public static final int AVANZADO = 15;
    public static final boolean ES_VACIO = false;
    public static final boolean ES_BOMBA = true;
    public static int NUM_FILAS = 0;
    public static int NUM_COLUMNAS = 0;
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
                casillero = new Casillero(i,j);
                casillero.setEstaMinado(false);
                casillero.setText("");
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

    private void minasAlrededor(){
         int minas = 0;

        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_COLUMNAS; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int m = -1; m < 2; m++) {
                        if ((i + k >= 0) && (j + m >= 0) && (i + k < NUM_FILAS) && (j + m < NUM_COLUMNAS)) {
                            if(getCasilla(i+k,j+m).getEstaMinado()){
                             minas++;
                            }
                        }
                    }
                }
                matriz[i][j].setMinasAlrededor(minas);
                minas = 0;
              /*  if ((arrayBotones[i][j].numeroMinasAlrededor > 0)
                        && (arrayBotones[i][j].bomba == 0)) {
                    arrayBotones[i][j].setText("");
                } */
            }
        }

    
    }

    private int minasEn(int i, int j) {
        return (matriz[i][j].getEstaMinado()) ? 1 : 0;
    }

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
