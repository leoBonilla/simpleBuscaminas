/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Leo
 */
public class MatrixButtonListener implements ActionListener ,MouseListener {

        private final int i;
        private final int j;
        private Jugable listener;
        private boolean marcado;
        MatrixButtonListener(int i, int j,Jugable listener) {
            this.i = i;
            this.j = j;
            this.listener = listener;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Casillero casillero = Game.getCasilla(i, j);
            JToggleButton boton = (JToggleButton) e.getSource();
            listener.onClick();
            if (casillero.getEstaMinado()) {
                listener.onPerder();
            } else {
                boton.setIcon(null);
                boton.setText("" + casillero.getMinasAlrededor());
                boton.setForeground(Color.BLUE);
                boton.setEnabled(false);
            }
        }

    @Override
    public void mouseClicked(MouseEvent e) {
     }

    @Override
    public void mousePressed(MouseEvent e) {
     }

    @Override
    public void mouseReleased(MouseEvent e) {
        marcado = !marcado;
        
        JToggleButton boton = (JToggleButton) e.getSource();
            if(SwingUtilities.isRightMouseButton(e)){
                String icono = (marcado) ? "pin.png": "background.png"; 
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+icono)));
                listener.onClick();
                 if(marcado){
            listener.onMarcarMina();
            }else{
            listener.onDesmarcarMina();
            }
        }   
           
      }

    @Override
    public void mouseEntered(MouseEvent e) {
      }

    @Override
    public void mouseExited(MouseEvent e) {
        }

        
        

    }
    


