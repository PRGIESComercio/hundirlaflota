/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iescomercio.eed.batallabarcos;

import java.awt.Graphics;

/**
 *
 * @author Rober
 */
public class PanelImagen extends javax.swing.JPanel {

    public PanelImagen() {
        this.setSize(800,600);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        java.awt.Dimension tamaño = this.getSize();
        javax.swing.ImageIcon imagenFondo = new javax.swing.ImageIcon(this.getClass().getResource("/com/iescomercio/eed/batallabarcos/resources/INTERFAZ.png"));
        g.drawImage(imagenFondo.getImage(),0,0,tamaño.width,tamaño.height,null);
        this.setOpaque(false);
        super.paintComponent(g);
    }
}
