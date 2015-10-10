/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author Leo
 */
public interface Jugable {
    void onPerder();
    void onAcertarMina();
    void onMinaVacia();
    void onClick();
    void onMarcarMina();
    void onDesmarcarMina();
        
}
