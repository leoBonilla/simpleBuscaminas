/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JToggleButton;

/**
 *
 * @author Leo
 */
public class Casillero extends JToggleButton{
    private boolean estaMinado;
    private int minasAlrededor;
    private int i,j;
    

    public Casillero() {
    }
    
    public Casillero(int i, int j){
        this.i = i;
        this.j = j;
    }

    public boolean getEstaMinado() {
        return estaMinado;
    }

    public void setEstaMinado(boolean estaMinado) {
        this.estaMinado = estaMinado;
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    
    
    
}
