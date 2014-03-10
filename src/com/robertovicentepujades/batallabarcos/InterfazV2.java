package com.robertovicentepujades.batallabarcos;

import java.awt.*;
import javax.swing.*;

/**
 * InterfazV2 gráfica para el juego BatallaBarcos
 *
 * @author Roberto Vicente Pujades
 * @version 2.0
 */
public class InterfazV2 extends JFrame {

    //Paneles
    private JPanel jPanelCabecera; //Panel cabecera, aquí estarán las opciones y el estado del juego
    private JPanel jPanelCabeceraOpciones; //Panel para las opciones dentro de cabecera
    private JPanel jPanelCabeceraEstado; //Panel para el estado de la partida dentro de cabecera
    private JPanel jPanelTableroJugador; //Panel para el tablero del jugador
    private JPanel jPanelTableroContrincante; //Panel para el tablero del contrincante o máquina

    //Etiquetas Labels
    private JLabel jLabelTamaño; //Etiqueta para la caja de texto del tamaño
    private JLabel jLabelNumBarcos; //Etiqueta para la caja de texto del número de barcos
    private JLabel jLabelEstadoJugador; //Etiqueta para mostrar el estado del jugador
    private JLabel jLabelEstadoMaquina; //Etiqueta para mostrar el estado de la máquina
    private JLabel jLabelTuTablero; //Etiqueta para el tablero del jugador
    private JLabel jLabelTableroContrincante; //Etiqueta para el tablero del contrincante o máquina
    private JLabel jLabelPantalla; //Etiqueta para mostrar la información de la partida

    //Campos jSpinner
    private JSpinner jSpinnerTamaño;
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

    private String nombreJugador;

    public InterfazV2() {
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
        nombreJugador = "Jugador";

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
        gbcFrame.weighty = 0.0; //estirar columna
        gbcFrame.fill = GridBagConstraints.BOTH; // estirar
        gbcFrame.insets = new java.awt.Insets(5, 5, 5, 5); //espaciado
        this.getContentPane().add(jPanelCabecera, gbcFrame); //añadir al panel

        //Inicializo los botones, les añado el listener para la acción y los pongo en la cabecera
        jButtonEstablecerOpciones = new JButton();
        jButtonEstablecerOpciones.setText("Establecer opciones");
        jButtonEstablecerOpciones.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstablecerOpciones();
            }
        });
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
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
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
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
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jButtonCancelar, gbcCabecera);

        //inicializo el panel de opciones y lo añado a la cabecera
        jPanelCabeceraOpciones = new JPanel();
        jPanelCabeceraOpciones.setLayout(new GridBagLayout());
        gbcCabecera.gridx = 0; //posición columna
        gbcCabecera.gridy = 1; //posición fila
        gbcCabecera.gridwidth = 1; //ocupa columnas
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila
        gbcCabecera.weighty = 1.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.BOTH; // estirar
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5); //espaciado
        jPanelCabecera.add(jPanelCabeceraOpciones, gbcCabecera); //añadir al panel

        //inicializo los controles del panel de opciones y los añado al panel de opciones
        jLabelTamaño = new JLabel();
        jLabelTamaño.setText("Tamaño: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraOpciones.add(jLabelTamaño, gbcCabecera);

        jLabelNumBarcos = new JLabel();
        jLabelNumBarcos.setText("Barcos: ");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 0.0; //estirar fila
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraOpciones.add(jLabelNumBarcos, gbcCabecera);

        jSpinnerTamaño = new JSpinner();
        jSpinnerTamaño.setModel(new SpinnerNumberModel(4, 4, 15, 1));
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraOpciones.add(jSpinnerTamaño, gbcCabecera);

        jSpinnerNumBarcos = new JSpinner();
        jSpinnerNumBarcos.setModel(new SpinnerNumberModel(5, 5, 30, 1));
        gbcCabecera.gridx = 1; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraOpciones.add(jSpinnerNumBarcos, gbcCabecera);

        //inicializo el panel de estado y lo añado a la cabecera
        jPanelCabeceraEstado = new JPanel();
        jPanelCabeceraEstado.setLayout(new GridBagLayout());
        gbcCabecera.gridx = 1; //posición columna
        gbcCabecera.gridy = 1; //posición fila
        gbcCabecera.gridwidth = 2; //ocupa columnas
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila
        gbcCabecera.weighty = 1.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.BOTH; // estirar
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5); //espaciado
        jPanelCabecera.add(jPanelCabeceraEstado, gbcCabecera); //añadir al panel

        //inicializo los labels de los estados y los añado al panel de estado
        jLabelEstadoJugador = new JLabel();
        jLabelEstadoJugador.setText("");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 0; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraEstado.add(jLabelEstadoJugador, gbcCabecera);

        jLabelEstadoMaquina = new JLabel();
        jLabelEstadoMaquina.setText("");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 1; //posicion fila
        gbcCabecera.gridwidth = 1; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabeceraEstado.add(jLabelEstadoMaquina, gbcCabecera);

        //Añado la etiqueta de la pantalla y lo añado a la cabecera
        jLabelPantalla = new JLabel();
        jLabelPantalla.setText("");
        gbcCabecera.gridx = 0; //posicion columna
        gbcCabecera.gridy = 2; //posicion fila
        gbcCabecera.gridwidth = 3; //ocupa columna
        gbcCabecera.gridheight = 1; //ocupa fila
        gbcCabecera.weightx = 1.0; //estirar fila;
        gbcCabecera.weighty = 0.0; //estirar columna
        gbcCabecera.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecera.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCabecera.add(jLabelPantalla, gbcCabecera);

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
        jLabelTuTablero.setText("Tu tablero: ");
        if (!nombreJugador.equals("Jugador")) {
            jLabelTuTablero.setText("Tablero de " + nombreJugador + ":");
        }        
        gbcTableroJugador.gridx = 0; //posicion columna
        gbcTableroJugador.gridy = 0; //posicion fila
        gbcTableroJugador.gridwidth = bb.getTamañoTablero(); //ocupa columna
        gbcTableroJugador.gridheight = 1; //ocupa fila
        gbcTableroJugador.weightx = 1.0; //estirar fila;
        gbcTableroJugador.weighty = 0.0; //estirar columna;
        gbcTableroJugador.fill = GridBagConstraints.HORIZONTAL;
        jPanelTableroJugador.add(jLabelTuTablero, gbcTableroJugador);

        jLabelTableroContrincante = new JLabel();
        jLabelTableroContrincante.setText("Tablero de la máquina: ");
        gbcTableroContrincante.gridx = 0; //posicion columna
        gbcTableroContrincante.gridy = 0; //posicion fila
        gbcTableroContrincante.gridwidth = bb.getTamañoTablero(); //ocupa columna
        gbcTableroContrincante.gridheight = 1; //ocupa fila
        gbcTableroContrincante.weightx = 1.0; //estirar fila;
        gbcTableroContrincante.weighty = 0.0; //estirar columna;
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
                //gbcTableroJugador.anchor = GridBagConstraints.SOUTHWEST;
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
                //gbcTableroContrincante.anchor = GridBagConstraints.SOUTHWEST;
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
        //gbcFrame.anchor = GridBagConstraints.SOUTHWEST;
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
        //gbcFrame.anchor = GridBagConstraints.SOUTHWEST;
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

        //titulos de los paneles de opciones y estado
        this.jPanelCabeceraOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        this.jPanelCabeceraEstado.setBorder(BorderFactory.createTitledBorder("Estado"));

        //Configuro la alineación de los labels
        this.jLabelTamaño.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jLabelNumBarcos.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jLabelEstadoJugador.setHorizontalAlignment(SwingConstants.CENTER);
        this.jLabelEstadoMaquina.setHorizontalAlignment(SwingConstants.CENTER);
        this.jLabelPantalla.setHorizontalAlignment(SwingConstants.CENTER);

        //Deshabilito el botón de empezar el juego, hasta que establezca las opciones
        this.jButtonEmpezar.setEnabled(false);
        this.jButtonCancelar.setEnabled(false);

        //labels
        this.jLabelEstadoJugador.setText("Partida no iniciada");
        this.jLabelEstadoJugador.setOpaque(true);
        //this.jLabelEstadoJugador.setBackground(Color.lightGray);
        this.jLabelEstadoJugador.setFont(new Font("Tahoma", 1, 15));
        this.jLabelEstadoJugador.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        this.jLabelEstadoMaquina.setText("Opciones no establecidas");
        this.jLabelEstadoMaquina.setOpaque(true);
        //this.jLabelEstadoMaquina.setBackground(Color.lightGray);
        this.jLabelEstadoMaquina.setFont(new Font("Tahoma", 1, 15));
        this.jLabelEstadoMaquina.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        this.jLabelPantalla.setOpaque(true);
        this.jLabelPantalla.setBackground(Color.white);
        this.jLabelPantalla.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.jLabelPantalla.setForeground(Color.red);
        
        nombreJugador = JOptionPane.showInputDialog(this, "¡Hola! ¿Cómo te llamas?", "Escribe tu nombre", JOptionPane.QUESTION_MESSAGE);
        if (nombreJugador==null || nombreJugador.equals("")){
            nombreJugador="Jugador";
        }
        
        
        //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
        this.setLocationRelativeTo(null);
    }

    //Acción del botón de establecer opciones
    private void botonEstablecerOpciones() {
        int tamaño = (int) this.jSpinnerTamaño.getValue();
        int barcos = (int) this.jSpinnerNumBarcos.getValue();

        //Compruebo que el número de barcos establecido es mayor que el tamaño del tablero establecido
        if (barcos > (tamaño * tamaño)) {
            this.jLabelPantalla.setText("¡El número de barcos supera el tamaño del tablero!");
        } else {

            //Inicializo la clase del juego con las opciones definidas por el usuario
            bb = new BatallaBarcos(tamaño, barcos);

            //Inicializo, configuro y pongo los tableros
            this.inicializarTableros();

            //color toggles
            this.cambiarColorInicialToggles();

            //Una vez establecidas las opciones deshabilito y habilito
            this.habilitarOpciones(false);
            this.jButtonEmpezar.setEnabled(true);
            this.jButtonEstablecerOpciones.setEnabled(false);
            this.habilitarTableros("Maquina", false);
            this.jPanelTableroContrincante.setVisible(true);
            this.jPanelTableroJugador.setVisible(true);

            //Indico al usuario de que introduzca la posición de sus barcos
            this.jLabelPantalla.setText("Introduce la posición de tus barcos en tu tablero y presiona el botón 'Empezar'");

            //Estado de la partida
            this.jLabelEstadoJugador.setText("Partida no iniciada");
            this.jLabelEstadoMaquina.setText("Barcos no posicionados");

            //Modifico el tamaño mínimo del formulario para que no haya opción de tapar los tableros
            this.setMinimumSize(new Dimension(800, 600));
            this.setExtendedState(InterfazV2.MAXIMIZED_BOTH); //lo maximizo
            //Centrado del from, al final para que no lo centre antes de crear y posicionar los controles
            //this.setLocationRelativeTo(null);
        }

    }

    //Acción del botón de empezar juego
    private void botonEmpezar() {

        switch (this.todosLosBarcosSeleccionados()) {
            case -1:
                this.jLabelPantalla.setText("Tienes que introducir " + this.bb.getNumBarcos() + " barcos y despues presionar el botón 'Empezar'");
                break;
            case 0:
                //deshabilitamos el tablero del jugador y habilitamos el de la máquina
                this.habilitarTableros("Jugador", false);
                this.habilitarTableros("Maquina", true);
                this.jButtonEmpezar.setEnabled(false);
                this.jButtonCancelar.setEnabled(true);
                //Empezamos juego y pasamos el tablero del jugador a la clase del juego
                bb.empezarJuego(this.pasarTogglesABooleans());
                //Lanzamos el primer turno
                this.lanzarTurno(bb.getTurnoString());

                break;
        }
    }

    //acción del boton cancelar
    private void botonCancelar() {
        this.juegoTerminado("Maquina");
    }

    private void lanzarTurno(String quien) {
        int[] xy; //guardo las coordenadas de la tirada de la máquina para modificar la posicion correspondiente

        switch (quien) {
            case "Jugador":

                this.jLabelEstadoJugador.setText(this.bb.estadoJugador(nombreJugador));
                this.jLabelEstadoMaquina.setText(this.bb.estadoJugador("Maquina"));

                if (!bb.todosHundidos(quien)) {
                    this.jLabelPantalla.setText("¡Es tu turno! Introduce una posición");
                    break;
                } else {
                    this.juegoTerminado(quien);
                    break;
                }

            case "Maquina":
                this.jLabelPantalla.setText("Es el turno de la máquina");
                this.jLabelEstadoJugador.setText(this.bb.estadoJugador(nombreJugador));
                //tiramos y comprobamos si ha acertado
                if (bb.tiradaMaquina()) {
                    this.jLabelPantalla.setText("¡La máquina ha hundido tu barco!");
                    xy = bb.getxEyAleatorioMaquina();
                    this.jToggleButtonJugador[xy[0]][xy[1]].setBackground(new java.awt.Color(0, 255, 0));
                    this.jToggleButtonJugador[xy[0]][xy[1]].setSelected(false);
                    this.jLabelEstadoMaquina.setText(this.bb.estadoJugador("Maquina"));
                    if (!bb.todosHundidos(quien)) {
                        this.lanzarTurno("Jugador");
                        break;
                    } else {
                        this.juegoTerminado(quien);
                        break;
                    }
                } else {
                    this.jLabelPantalla.setText("¡La máquina ha fallado!");
                    xy = bb.getxEyAleatorioMaquina();
                    this.jToggleButtonJugador[xy[0]][xy[1]].setBackground(new java.awt.Color(255, 0, 0));
                    this.jToggleButtonJugador[xy[0]][xy[1]].setSelected(false);
                    this.jLabelEstadoMaquina.setText(this.bb.estadoJugador("Maquina"));
                    this.lanzarTurno("Jugador");
                    break;
                }

        }

    }

    private void juegoTerminado(String quien) {
        if (quien.equals("Maquina")){
            this.jLabelPantalla.setText("Ha ganado: " + quien);
        }else{
            this.jLabelPantalla.setText("Has ganado " + nombreJugador);
        }

 
        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {

                    habilitarTableros("Maquina", false);
                    jPanelTableroJugador.setVisible(false);
                    jPanelTableroContrincante.setVisible(false);
                    jButtonEstablecerOpciones.setEnabled(true);
                    jButtonCancelar.setEnabled(false);
                    habilitarOpciones(true);

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
                this.jLabelPantalla.setText("Ya has posicionado todos tus barcos pero puedes cambiarlos de posición");
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
            this.jLabelPantalla.setText("¡Has hundido uno de sus barcos!");
            this.jLabelEstadoJugador.setText(this.bb.estadoJugador(nombreJugador));
            this.jToggleButtonContrincante[fila][columna].setBackground(new java.awt.Color(0, 255, 0));
            if (bb.todosHundidos("Jugador")) {
                this.juegoTerminado("Jugador");
            } else {
                this.jToggleButtonContrincante[fila][columna].setEnabled(false);
                this.jToggleButtonContrincante[fila][columna].setSelected(false);
                this.lanzarTurno("Maquina");
            }
        } else {
            this.jLabelPantalla.setText("¡No has acertado!");
            this.jLabelEstadoJugador.setText(this.bb.estadoJugador(nombreJugador));
            this.jToggleButtonContrincante[fila][columna].setBackground(new java.awt.Color(255, 0, 0));
            this.jToggleButtonContrincante[fila][columna].setEnabled(false);
            this.jToggleButtonContrincante[fila][columna].setSelected(false);
            this.lanzarTurno("Maquina");
        }

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
    private boolean[][] pasarTogglesABooleans() {
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

    private void habilitarOpciones(boolean habilitado) {

        this.jPanelCabeceraOpciones.setEnabled(habilitado);
        this.jSpinnerNumBarcos.setEnabled(habilitado);
        this.jSpinnerTamaño.setEnabled(habilitado);
        this.jLabelNumBarcos.setEnabled(habilitado);
        this.jLabelTamaño.setEnabled(habilitado);

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazV2().setVisible(true);
            }
        });
    }
}
