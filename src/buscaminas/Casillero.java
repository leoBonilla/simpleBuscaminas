/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JLabel;

/**
 *
 * @author Leo
 */
public class Casillero extends JLabel{
    private boolean estaMinado;
    private int minasAlrededor;

    public Casillero() {
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
    
    
    
}
