/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robertovicentepujades.batallabarcos;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Rober
 */
public class InterfazV1 extends JFrame {

    //Paneles
    private JPanel jPanelCabecera; //Panel cabecera, aquí estarán las opciones y el estado del juego
    private JPanel jPanelTableroJugador; //Panel para el tablero del jugador
    private JPanel jPanelTableroContrincante; //Panel para el tablero del contrincante o máquina
    //Etiquetas Labels
    private JLabel jLabelOpciones; //Etiqueta para las opciones
    private JLabel jLabelTamaño; //Etiqueta para la caja de texto del tamaño
    private JLabel jLabelNumTurnos; //Etiqueta para la caja de texto del número de turnos
    private JLabel jLabelNumBarcos; //Etiqueta para la caja de texto del número de barcos
    private JLabel jLabelEstado; //Etiqueta para el área de texto del estado
    private JLabel jLabelTuTablero; //Etiqueta para el tablero del jugador
    private JLabel jLabelTableroContrincante; //Etiqueta para el tablero del contrincante o máquina
    //Campos de texto jSpinner, TextArea y ScrollPanel
    private JTextArea jTextAreaEstado; //Campo para el estado
    private JScrollPane jScrollPaneEstado; //Scroll para el estado
    private JSpinner jSpinnerTamaño;
    private JSpinner jSpinnerNumTurnos;
    private JSpinner jSpinnerNumBarcos;

    //Botones Buttons ToggleButtons
    private JButton jButtonEstablecerOpciones; //Botón para guardar las opciones
    private JButton jButtonEmpezar; //Botón para empezar la partida
    private JButton jButtonCancelar; //Botón para cancelar la partida
    private JToggleButton[][] jToggleButtonJugador; //Botones para el tablero del jugador
    private JToggleButton[][] jToggleButtonContrincante; //Botones para el tablero del contrincante o máquina
    //Clase del juego
    private BatallaBarcos bb;
    //Configuración grillas
    private final GridBagConstraints gbcFrame = new GridBagConstraints();
    private final GridBagConstraints gbcCabecera = new GridBagConstraints();
    private final GridBagConstraints gbcTableroJugador = new GridBagConstraints();
    private final GridBagConstraints gbcTableroContrincante = new GridBagConstraints();

    public InterfazV1() {
        //Titulo del frame
        super("Batalla de barcos -- BattleShip -- Roberto Vicente");
        //Icono
        this.setIconImage(new ImageIcon(getClass().getResource("/com/robertovicentepujades/batallabarcos/resources/icono2.png")).getImage());
        //Operación por defecto del botón de cerrar
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Inicializar y posicionar controles
        inicializarControlesInicio();
        //Configuración de los controles
        configurarInterfaz();        

    }

    private void inicializarControlesInicio() {
        //Establezco el layout al frame
        this.getContentPane().setLayout(new GridBagLayout());

        //Inicializo el panel de la cabecera y lo pongo en la grilla del frame
        jPanelCabecera = new JPanel();
        jPanelCabecera.setLayout(new GridBagLayout());
        gbcFrame.gridx = 0; //posición columna
        gbcFrame.gridy = 0; //posición fila
        gbcFrame.gridwidth = 2; //ocupa columnas
        gbcFrame.gridheight = 1; //ocupa fila
        gbcFrame.weightx = 1.0; //estirar fila
        gbcFrame.fill = GridBagConstraints.BOTH; // estirar
        gbcFrame.insets = new java.awt.Insets(5, 5, 5, 5); //espaciado
        this.getContentPane().add(jPanelCabecera, gbcFrame); //añadir al panel

        //Inicializo los label y los pongo en la grilla del panel de la cabecera
        jLabelOpciones = new JLabel();
        jLabelOpciones.setText("Opciones del juego: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 2; //ocupa columnas
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelOpciones, gbcCabecera);

        jLabelTamaño = new JLabel();
        jLabelTamaño.setText("Tamaño: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelTamaño, gbcCabecera);

        jLabelNumTurnos = new JLabel();
        jLabelNumTurnos.setText("Número de turnos: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 2; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelNumTurnos, gbcCabecera);

        jLabelNumBarcos = new JLabel();
        jLabelNumBarcos.setText("Número de barcos: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 3; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelNumBarcos, gbcCabecera);

        //Inicializo los campos de texto y los añado a la grilla del panel de cabecera
        jSpinnerTamaño = new JSpinner();
        jSpinnerTamaño.setModel(new SpinnerNumberModel(4, 4, 15, 1));
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jSpinnerTamaño, gbcCabecera);

        jSpinnerNumTurnos = new JSpinner();
        jSpinnerNumTurnos.setModel(new SpinnerNumberModel(5, 5, 100, 5));
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 2; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jSpinnerNumTurnos, gbcCabecera);

        jSpinnerNumBarcos = new JSpinner();
        jSpinnerNumBarcos.setModel(new SpinnerNumberModel(5, 5, 30, 1));
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 3; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jSpinnerNumBarcos, gbcCabecera);

        //Inicializo los botones, les añado el listener para la acción y los pongo en la grilla
        jButtonEstablecerOpciones = new JButton();
        jButtonEstablecerOpciones.setText("Establecer opciones");
        jButtonEstablecerOpciones.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstablecerOpciones();
            }
        });
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 4; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jButtonEstablecerOpciones, gbcCabecera);

        jButtonEmpezar = new JButton();
        jButtonEmpezar.setText("Empezar partida");
        jButtonEmpezar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpezar();
            }
        });
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 4; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jButtonEmpezar, gbcCabecera);
        
        jButtonCancelar = new JButton();
        jButtonCancelar.setText("Cancelar partida");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelar();
            }
        });
        gbcCabecera.gridx = 2; //posicion columna
        gbcCabecera.gridy = 4; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jButtonCancelar, gbcCabecera);

        //Inicializo y pongo la etiqueta y el área de texto en la grilla de la cabecera
        jLabelEstado = new JLabel();
        jLabelEstado.setText("Estado de la partida: ");
        gbcCabecera.gridx = 2; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelEstado, gbcCabecera);

        jTextAreaEstado = new JTextArea();
        jTextAreaEstado.setText("Partida no iniciada");
        jScrollPaneEstado = new JScrollPane();

        jTextAreaEstado.setColumns(20);
        jTextAreaEstado.setRows(5);
        jScrollPaneEstado.setViewportView(jTextAreaEstado); //Poner el área de texto en el scroll

        gbcCabecera.gridx = 2; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 3; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.BOTH;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jScrollPaneEstado, gbcCabecera); // y aquí añadir el scroll

        
        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
    }

    //Aquí inicializo y pongo en la grilla los tableros, después de que el usuario introduzca las opciones
    private void inicializarTableros() {

        jPanelTableroJugador = new JPanel();
        jPanelTableroContrincante = new JPanel();

        jPanelTableroJugador.setLayout(new GridBagLayout());
        jPanelTableroContrincante.setLayout(new GridBagLayout());

        //Si vuelve a crear un juego quita los componentes que puede que estén en los paneles
        this.jPanelTableroJugador.removeAll();
        this.jPanelTableroContrincante.removeAll();

        jLabelTuTablero = new JLabel();
        jLabelTuTablero.setText("Tu tablero, pon aquí tus barcos: ");
        gbcTableroJugador.gridx = 0; //posicion columna
        gbcTableroJugador.gridy = 0; //posicion fila
        gbcTableroJugador.gridwidth = bb.getTamañoTablero(); //ocupa columna
        gbcTableroJugador.gridheight = 1; //ocupa fila
        gbcTableroJugador.weightx = 1.0; //estirar fila;
        gbcTableroJugador.fill = GridBagConstraints.HORIZONTAL;
        jPanelTableroJugador.add(jLabelTuTablero, gbcTableroJugador);

        jLabelTableroContrincante = new JLabel();
        jLabelTableroContrincante.setText("Tablero de la máquina: ");
        gbcTableroContrincante.gridx = 0; //posicion columna
        gbcTableroContrincante.gridy = 0; //posicion fila
        gbcTableroContrincante.gridwidth = bb.getTamañoTablero(); //ocupa columna
        gbcTableroContrincante.gridheight = 1; //ocupa fila
        gbcTableroContrincante.weightx = 1.0; //estirar fila;
        gbcTableroContrincante.fill = GridBagConstraints.HORIZONTAL;
        jPanelTableroContrincante.add(jLabelTableroContrincante, gbcTableroContrincante);

        //Alineación horizontal
        this.jLabelTuTablero.setHorizontalAlignment(SwingConstants.CENTER);
        this.jLabelTableroContrincante.setHorizontalAlignment(SwingConstants.CENTER);

        //Creo los arrays bidimensionales de togglebuttons del tamaño indicado en el juego
        jToggleButtonJugador = new JToggleButton[bb.getTamañoTablero()][bb.getTamañoTablero()];
        jToggleButtonContrincante = new JToggleButton[bb.getTamañoTablero()][bb.getTamañoTablero()];

        //Recorro los arrays, voy creando los botones y los pongo en su sitio
        for (int i = 0; i < bb.getTamañoTablero(); i++) {
            for (int j = 0; j < bb.getTamañoTablero(); j++) {
                //Creo botones jugador
                jToggleButtonJugador[i][j] = new JToggleButton();
                jToggleButtonJugador[i][j].setText("");
                //Con esto (putClientProperty) guardamos en esa propiedad del botón el índice de la fila y columna
                //para que cuando lo obtenga en el actionPerformed/botonTableroJugador sepa cual es
                jToggleButtonJugador[i][j].putClientProperty("fila", i);
                jToggleButtonJugador[i][j].putClientProperty("columna", j);
                jToggleButtonJugador[i][j].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        botonTableroJugador(evt);
                    }
                });
                //Pongo botones jugador
                gbcTableroJugador.gridx = j; //posición columna
                gbcTableroJugador.gridy = i + 1; //posición fila (+1 por que la primera fila o fila 0 es para el label)
                gbcTableroJugador.gridwidth = 1; //ocupa columnas
                gbcTableroJugador.gridheight = 1; //ocupa columna
                gbcTableroJugador.weightx = 1.0; //estirar fila;
                gbcTableroJugador.weighty = 1.0; //estirar columna;
                gbcTableroJugador.fill = GridBagConstraints.BOTH;
                gbcTableroJugador.anchor = GridBagConstraints.SOUTHWEST;
                //gbcTableroJugador.insets = new java.awt.Insets(1, 1, 1, 1);
                this.jPanelTableroJugador.add(jToggleButtonJugador[i][j], gbcTableroJugador);

                //Creo botones tablero contrincante o máquina               
                jToggleButtonContrincante[i][j] = new JToggleButton();
                jToggleButtonContrincante[i][j].setText("");
                //Con esto (putClientProperty) guardamos en esa propiedad del botón el índice de la fila y columna
                //para que cuando lo obtenga en el actionPerformed/botonTableroJugador sepa cual es
                jToggleButtonContrincante[i][j].putClientProperty("fila", i);
                jToggleButtonContrincante[i][j].putClientProperty("columna", j);
                jToggleButtonContrincante[i][j].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        botonTableroContrincante(evt);
                    }
                });
                //Pongo botones de tablero contrincante o máquina
                gbcTableroContrincante.gridx = j; //posición columna
                gbcTableroContrincante.gridy = i + 1; //posición fila (+1 por que la primera fila o fila 0 es para el label)
                gbcTableroContrincante.gridwidth = 1; //ocupa columnas
                gbcTableroContrincante.gridheight = 1; //ocupa columna
                gbcTableroContrincante.weightx = 1.0; //estirar fila;
                gbcTableroContrincante.weighty = 1.0; //estirar columna;
                gbcTableroContrincante.fill = GridBagConstraints.BOTH;
                gbcTableroContrincante.anchor = GridBagConstraints.SOUTHWEST;
                //gbcTableroContrincante.insets = new java.awt.Insets(1, 1, 1, 1);
                this.jPanelTableroContrincante.add(jToggleButtonContrincante[i][j], gbcTableroContrincante);
            }
        }

        //Pongo el panel del tablero del jugador en el panel del frame
        gbcFrame.gridx = 0; //posición columna
        gbcFrame.gridy = 1; //posición fila
        gbcFrame.gridwidth = 1; //ocupa columnas
        gbcFrame.gridheight = 1; //ocupa columna
        gbcFrame.weightx = 1.0; //estirar fila;
        gbcFrame.weighty = 1.0; //estirar columna;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.anchor = GridBagConstraints.SOUTHWEST;
        gbcFrame.insets = new java.awt.Insets(5, 5, 5, 5);
        this.getContentPane().add(jPanelTableroJugador, gbcFrame);
        //Pongo el panel del tablero del contrincante o máquina en el del frame
        gbcFrame.gridx = 1; //posición columna
        gbcFrame.gridy = 1; //posición fila
        gbcFrame.gridwidth = 1; //ocupa columnas
        gbcFrame.gridheight = 1; //ocupa columna
        gbcFrame.weightx = 1.0; //estirar fila;
        gbcFrame.weighty = 1.0; //estirar columna;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.anchor = GridBagConstraints.SOUTHWEST;
        gbcFrame.insets = new java.awt.Insets(5, 5, 5, 5);
        this.getContentPane().add(jPanelTableroContrincante, gbcFrame);

        //Actualizo la interfaz de los paneles
        this.jPanelTableroContrincante.updateUI();
        this.jPanelTableroJugador.updateUI();

        //al final
        pack();
        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
    }

    //Configuración inicial de la gui
    private void configurarInterfaz() {
        //Tamaño mínimo una vez iniciada la aplición
        this.setMinimumSize(new Dimension(800, 200));

        //Configuro la alineación de los labels
        this.jLabelOpciones.setHorizontalAlignment(SwingConstants.CENTER);
        this.jLabelTamaño.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jLabelNumTurnos.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jLabelNumBarcos.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jLabelEstado.setHorizontalAlignment(SwingConstants.CENTER);

        //Deshabilito el botón de empezar el juego, hasta que establezca las opciones
        this.jButtonEmpezar.setEnabled(false);
        this.jButtonCancelar.setEnabled(false);

        //Estado editable
        this.jTextAreaEstado.setEditable(false);

        //labels
        this.jLabelOpciones.setOpaque(true);
        this.jLabelOpciones.setBackground(Color.lightGray);
        this.jLabelOpciones.setFont(new Font("Tahoma", 1, 15));

        this.jLabelEstado.setOpaque(true);
        this.jLabelEstado.setBackground(Color.lightGray);
        this.jLabelEstado.setFont(new Font("Tahoma", 1, 15));

        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
    }

    //Acción del botón de establecer opciones
    private void botonEstablecerOpciones() {

        //Inicializo la clase del juego con las opciones definidas por el usuario
        bb = new BatallaBarcos((int) this.jSpinnerTamaño.getValue(), (int) this.jSpinnerNumBarcos.getValue());

        //Inicializo, configuro y pongo los tableros
        this.inicializarTableros();
        //color toggles
        this.cambiarColorInicialToggles();

        //Modifico el tamaño mínimo del formulario para que no haya opción de tapar los tableros
        this.setMinimumSize(new Dimension(800, 600));

        //Una vez establecidas las opciones deshabilito y habilito
        this.jButtonEmpezar.setEnabled(true);
        this.jButtonEstablecerOpciones.setEnabled(false);
        this.jSpinnerNumBarcos.setEnabled(false);
        this.jSpinnerNumTurnos.setEnabled(false);
        this.jSpinnerTamaño.setEnabled(false);
        this.habilitarTableros("Maquina", false);
        this.jPanelTableroContrincante.setVisible(true);
        this.jPanelTableroJugador.setVisible(true);

        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
        
        //Indico al usuario de que introduzca la posición de sus barcos
        JOptionPane.showMessageDialog(this, "Ahora presiona en la posición donde estarán tus barcos y después dale a empezar");

        
    }

    //Acción del botón de empezar juego
    private void botonEmpezar() {

        switch (this.todosLosBarcosSeleccionados()) {
            case -1:
                JOptionPane.showMessageDialog(this, "Todavía tienes barcos sin posicionar");
                break;
            case 0:
                //deshabilitamos el tablero del jugador y habilitamos el de la máquina
                this.habilitarTableros("Jugador", false);
                this.habilitarTableros("Maquina", true);
                this.jButtonEmpezar.setEnabled(false);

                //Empezamos juego y pasamos el tablero del jugador a la clase del juego
                bb.empezarJuego(this.pasarTogglesABooleans(jToggleButtonJugador));

                //establezco el estado de la partida
                this.jTextAreaEstado.setText(bb.toString());

                //Lanzamos el primer turno
                this.lanzarTurno(bb.getTurnoString());

                //habilitamos el botón de cancelar partida
                this.jButtonCancelar.setEnabled(true);
                break;
        }
    }
    
    //acción del boton cancelar
    private void botonCancelar(){
        this.juegoTerminado("Maquina");
    }

    private void lanzarTurno(String quien) {
        int[] xy; //guardo las coordenadas de la tirada de la máquina para modificar la posicion correspondiente

        switch (quien) {
            case "Jugador":
                if (!bb.todosHundidos(quien)) {
                    JOptionPane.showMessageDialog(this, "Es tu turno, inserta una posición en el tablero de tu contrincante");
                    break;
                } else {
                    this.juegoTerminado(quien);
                    break;
                }
                

            case "Maquina":
                JOptionPane.showMessageDialog(this, "Es el turno de la máquina");

                //tiramos y comprobamos si ha acertado
                if (bb.tiradaMaquina()) {
                    JOptionPane.showMessageDialog(this, "La máquina ha acertado");
                    xy = bb.getxEyAleatorioMaquina();
                    this.jToggleButtonJugador[xy[0]][xy[1]].setBackground(new java.awt.Color(0, 255, 0));
                    this.jToggleButtonJugador[xy[0]][xy[1]].setSelected(false);

                    if (!bb.todosHundidos(quien)) {
                        this.jTextAreaEstado.setText(bb.toString());
                        this.lanzarTurno("Jugador");
                        break;
                    } else {
                        this.jTextAreaEstado.setText(bb.toString());
                        this.juegoTerminado(quien);
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La máquina no ha acertado");
                    xy = bb.getxEyAleatorioMaquina();
                    this.jToggleButtonJugador[xy[0]][xy[1]].setBackground(new java.awt.Color(255, 0, 0));
                    this.jToggleButtonJugador[xy[0]][xy[1]].setSelected(false);
                    this.jTextAreaEstado.setText(bb.toString());
                    this.lanzarTurno("Jugador");
                    break;
                }
        }

    }

    private void juegoTerminado(String quien) {
        JOptionPane.showMessageDialog(this, "Ha ganado: " + quien);

        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {
                    
                    habilitarTableros("Maquina", false);
                    jPanelTableroJugador.setVisible(false);
                    jPanelTableroContrincante.setVisible(false);
                    jButtonEstablecerOpciones.setEnabled(true);
                    jButtonCancelar.setEnabled(false);
                    jSpinnerNumBarcos.setEnabled(true);
                    jSpinnerNumTurnos.setEnabled(true);
                    jSpinnerTamaño.setEnabled(true);
                    jTextAreaEstado.setText("Partida no iniciada".toString());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        Thread hilo = new Thread(r);
        hilo.start();

        this.setMinimumSize(new Dimension(800, 200));
        this.setSize(new Dimension(800, 200));
        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
    }

    private void botonTableroJugador(java.awt.event.ActionEvent evt) {
        JToggleButton btn = (JToggleButton) evt.getSource();
        int fila, columna;

        //Recojo los datos del botón
        fila = (int) btn.getClientProperty("fila");
        columna = (int) btn.getClientProperty("columna");

        switch (this.todosLosBarcosSeleccionados()) {
            case 1:
                jToggleButtonJugador[fila][columna].setSelected(false);
            case 0:
                JOptionPane.showMessageDialog(this, "Ya has posicionado todos tus barcos, ahora puedes comenzar el juego. Si quieres, puedes quitar algún otro barco y cambiarlo de posición");
                break;
            case -1:

        }

    }

    private void botonTableroContrincante(java.awt.event.ActionEvent evt) {
        JToggleButton btn = (JToggleButton) evt.getSource();
        int fila, columna;

        //Recojo los datos del botón
        fila = (int) btn.getClientProperty("fila");
        columna = (int) btn.getClientProperty("columna");

        if (bb.tiradaJugador(fila, columna)) {
            JOptionPane.showMessageDialog(this, "Has acertado");
            this.jToggleButtonContrincante[fila][columna].setBackground(new java.awt.Color(0, 255, 0));
            if (bb.todosHundidos("Jugador")) {
                this.juegoTerminado("Jugador");
            } else {
                this.jToggleButtonContrincante[fila][columna].setEnabled(false);
                this.jToggleButtonContrincante[fila][columna].setSelected(false);
                this.lanzarTurno("Maquina");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No has acertado");
            this.jToggleButtonContrincante[fila][columna].setBackground(new java.awt.Color(255, 0, 0));
            this.jToggleButtonContrincante[fila][columna].setEnabled(false);
            this.jToggleButtonContrincante[fila][columna].setSelected(false);
            this.lanzarTurno("Maquina");
        }

        this.jTextAreaEstado.setText(bb.toString());

    }

    private void habilitarTableros(String tablero, boolean habilitado) {
        switch (tablero) {
            case "Jugador":
                for (int i = 0; i < this.jToggleButtonJugador.length; i++) {
                    for (int j = 0; j < this.jToggleButtonJugador[i].length; j++) {
                        this.jToggleButtonJugador[i][j].setEnabled(habilitado);
                    }
                }
                break;
            case "Maquina":
                for (int i = 0; i < this.jToggleButtonContrincante.length; i++) {
                    for (int j = 0; j < this.jToggleButtonContrincante[i].length; j++) {
                        this.jToggleButtonContrincante[i][j].setEnabled(habilitado);
                    }
                }
                break;
        }
    }

    //Compruebo si el jugador ha presionado los barcos establecidos en las opciones
    private int todosLosBarcosSeleccionados() {
        int contador = 0;

        for (int i = 0; i < this.jToggleButtonJugador.length; i++) {
            for (int j = 0; j < this.jToggleButtonJugador[i].length; j++) {
                if (this.jToggleButtonJugador[i][j].isSelected()) {
                    contador += 1;
                }
            }
        }

        if (contador < bb.getNumBarcos()) {
            return -1;
        } else if (contador == bb.getNumBarcos()) {
            return 0;
        } else {
            return 1;
        }

    }

    //metodo que convierte el array de togglebuttons en array de booleans
    private boolean[][] pasarTogglesABooleans(JToggleButton[][] botones) {
        boolean[][] aux = new boolean[bb.getTamañoTablero()][bb.getTamañoTablero()];

        for (int i = 0; i < bb.getTableroJugador().length; i++) {
            for (int j = 0; j < bb.getTableroJugador()[i].length; j++) {
                if (this.jToggleButtonJugador[i][j].isSelected()) {
                    aux[i][j] = true;
                }
            }
        }

        return aux;
    }

    //poner de azul los botones
    private void cambiarColorInicialToggles() {

        for (int i = 0; i < this.jToggleButtonJugador.length; i++) {
            for (int j = 0; j < this.jToggleButtonJugador[i].length; j++) {
                this.jToggleButtonJugador[i][j].setBackground(new java.awt.Color(0, 0, 255));
            }
        }

        for (int i = 0; i < this.jToggleButtonContrincante.length; i++) {
            for (int j = 0; j < this.jToggleButtonContrincante[i].length; j++) {
                this.jToggleButtonContrincante[i][j].setBackground(new java.awt.Color(0, 0, 255));
            }
        }

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazV1().setVisible(true);
            }
        });
    }
}
