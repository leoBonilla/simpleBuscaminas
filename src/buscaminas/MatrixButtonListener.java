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
import java.util.ArrayList;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Leo
 */
public class MatrixButtonListener implements ActionListener ,MouseListener {

        private Jugable listener;
        private boolean marcado;
        
        MatrixButtonListener(Jugable listener) {
            this.listener = listener;  
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Casillero casillero = (Casillero) e.getSource();
            JToggleButton miBoton = (JToggleButton) e.getSource();
            listener.onClick();
            if (casillero.getEstaMinado()) {
                listener.onPerder();
            } else{
                  System.out.println("TE SALVASTE");
                    if (((Casillero)miBoton).getMinasAlrededor()>0)
                    miBoton.setText(((Casillero)miBoton).getMinasAlrededor()+"");
                    ArrayList<Casillero> listaDeCasillasAMirar = new ArrayList();
                    //añado el botón que ha sido pulsado
                    listaDeCasillasAMirar.add((Casillero) miBoton);

                    while (listaDeCasillasAMirar.size() > 0) {
                        Casillero b = listaDeCasillasAMirar.get(0);
                        for (int k = -1; k < 2; k++) {
                            for (int m = -1; m < 2; m++) {
                                if ((b.getI() + k >= 0) && (b.getJ() + m >= 0)&& (b.getI() + k < Game.NUM_FILAS) && (b.getJ() + m < Game.NUM_COLUMNAS)) {
                                //si el boton de esa posición está habilitado 
                                    //es que no lo he chequeado todavia
                                   if (((JToggleButton)Game.getCasilla((b.getI()+ k),(b.getJ() + m ))).isEnabled()) {
                                       if((Game.getCasilla((b.getI()+ k),(b.getJ() + m ))).getMinasAlrededor() == 0){
                                            Game.getCasilla((b.getI()+ k),(b.getJ() + m )).setEnabled(false);
                                            listaDeCasillasAMirar.add(Game.getCasilla((b.getI()+ k),(b.getJ() + m )));
                                        }
                                    } 
                                }
                            }
                        }
                        listaDeCasillasAMirar.remove(b);
                    }

                }
                //si no, verificamos la casilla 
                miBoton.setFocusPainted(false);
                    
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
    


