/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iescomercio.eed.batallabarcos;

import java.util.ArrayList;

/**
 *
 * @author Rober
 */
public class BatallaBarcos {

    private boolean[][] tableroJugador; //Tablero donde el jugador pondrá sus barcos
    private boolean[][] tableroMaquina; //Tablero donde la máquina pondrá sus barcos
    private boolean[][] tableroContrincanteJugador; //Tablero donde el jugador apuntará a los barcos de la máquina
    private boolean[][] tableroContrincanteMaquina; //Tablero donde la máquina apuntará a los barcos del jugador

    private int turnos; //Número de turnos, por defecto será el máximo número de elementos del tablero
    private int tamañoTablero; //Número de lados del tablero, por defecto 8
    private int numBarcos; //Número de barcos, por defecto 10 (1 casilla por barco)

    private byte turno; //Para determinar a quién le toca: 0 jugador y 1 máquina
    private int turnosJugador; //Número de turnos que lleva el jugador
    private int turnosMaquina; //Número de turnos que lleva la máquina

    private int barcosHundidosJugador; //Guardamos los aciertos que lleva el jugador
    private int barcosHundidosMaquina; //Guardamos los aciertos que lleva la máquina

    private ArrayList coordenadasAleatoriasTiradasMaquina; //guardamos las coordenadas aleatorias para comprobar si ya ha salido en tiradaMaquina
    private ArrayList coordenadasAleatoriasTableroMaquina; //guardamos las coordenadas aleatorias para generar el tablero de la máquina
    
    private int[] xEyAleatorioMaquina; //array de dos posiciones con las coordenadas x e y para uso en la gui

    //Juego personalizado
    public BatallaBarcos(int turnos, int tamañoTablero, int numBarcos) {
        this.turnos = turnos;
        this.tamañoTablero = tamañoTablero;
        this.numBarcos = numBarcos;
        this.tableroJugador = new boolean[tamañoTablero][tamañoTablero];
        this.tableroMaquina = new boolean[tamañoTablero][tamañoTablero];
        this.tableroContrincanteJugador = new boolean[tamañoTablero][tamañoTablero];
        this.tableroContrincanteMaquina = new boolean[tamañoTablero][tamañoTablero];
        this.turnosJugador = 0;
        this.turnosMaquina = 0;
        coordenadasAleatoriasTiradasMaquina = new ArrayList();
        coordenadasAleatoriasTableroMaquina = new ArrayList();
        xEyAleatorioMaquina = new int[2];
    }

    //Juego por defecto
    public BatallaBarcos() {
        this.tamañoTablero = 8;
        this.numBarcos = 10;
        this.turnos = 64;
        this.tableroJugador = new boolean[tamañoTablero][tamañoTablero];
        this.tableroMaquina = new boolean[tamañoTablero][tamañoTablero];
        this.tableroContrincanteJugador = new boolean[tamañoTablero][tamañoTablero];
        this.tableroContrincanteMaquina = new boolean[tamañoTablero][tamañoTablero];
        this.turnosJugador = 0;
        this.turnosMaquina = 0;
        coordenadasAleatoriasTiradasMaquina = new ArrayList();
        coordenadasAleatoriasTableroMaquina = new ArrayList();
        xEyAleatorioMaquina = new int[2];
    }

    public void setTableroJugador(boolean[][] tableroJugador) {
        this.tableroJugador = tableroJugador;
    }

    private void setTableroMaquina(boolean[][] tableroMaquina) {
        this.tableroMaquina = tableroMaquina;
    }

    public boolean[][] getTableroJugador() {
        return tableroJugador;
    }

    public boolean[][] getTableroMaquina() {
        return tableroMaquina;
    }

    public int getTurnos() {
        return turnos;
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }

    public int getNumBarcos() {
        return numBarcos;
    }

    private void setTableroContrincanteJugador(boolean[][] tableroContrincanteJugador) {
        this.tableroContrincanteJugador = tableroContrincanteJugador;
    }

    private void setTableroContrincanteMaquina(boolean[][] tableroContrincanteMaquina) {
        this.tableroContrincanteMaquina = tableroContrincanteMaquina;
    }

    public boolean[][] getTableroContrincanteJugador() {
        return tableroContrincanteJugador;
    }

    public boolean[][] getTableroContrincanteMaquina() {
        return tableroContrincanteMaquina;
    }

    private void setTurno(byte turno) {
        this.turno = turno;
    }

    public byte getTurno() {
        return turno;
    }

    public String getTurnoString() {
        if (this.getTurno() == 0) {
            return "Jugador";
        } else if (this.getTurno() == 1) {
            return "Maquina";
        } else {
            this.elegirTurno();
            return this.getTurnoString();
        }
    }

    public int getTurnosJugador() {
        return turnosJugador;
    }

    private void setTurnosJugador(int turnosJugador) {
        this.turnosJugador = turnosJugador;
    }

    public int getTurnosMaquina() {
        return turnosMaquina;
    }

    private void setTurnosMaquina(int turnosMaquina) {
        this.turnosMaquina = turnosMaquina;
    }

    public int getBarcosHundidosJugador() {
        return barcosHundidosJugador;
    }

    private void setBarcosHundidosJugador(int barcosHundidosJugador) {
        this.barcosHundidosJugador = barcosHundidosJugador;
    }

    public int getBarcosHundidosMaquina() {
        return barcosHundidosMaquina;
    }

    private void setBarcosHundidosMaquina(int barcosHundidosMaquina) {
        this.barcosHundidosMaquina = barcosHundidosMaquina;
    }

    public int[] getxEyAleatorioMaquina() {
        return xEyAleatorioMaquina;
    }

    private void setxEyAleatorioMaquina(int[] xEyAleatorioMaquina) {
        this.xEyAleatorioMaquina = xEyAleatorioMaquina;
    }

    public void empezarJuego(boolean[][] tableroJugador) {
        //Asignamos el tablero puesto por el jugador a su tablero
        this.setTableroJugador(tableroJugador);
        //La máquina pone aleatoriamente su tablero
        this.setTableroMaquina(this.tableroAleatorio(this.getTamañoTablero(), this.getNumBarcos()));
        this.setTableroContrincanteJugador(this.getTableroMaquina());
        //elegimos turno aleatoriamente
        this.elegirTurno();
    }

    //Elige el primer turno aleatoriamente
    private void elegirTurno() {
        int t = (int) Math.floor(Math.random() * 2);
        this.setTurno((byte) t);
    }

    public boolean tiradaJugador(int x, int y) {
        //añadimos un turno al jugador
        this.setTurnosJugador(this.getTurnosJugador() + 1);

        //Tirada del jugador, si las coordenadas están activadas en el tablero de la máquina añadimos la coordenada
        //en el tablero y devolvemos que la tirada ha acertado
        if (this.getTableroMaquina()[x][y]) {
            this.setTableroContrincanteJugador(this.añadirTiradaJugador(x, y));
            this.setBarcosHundidosJugador(this.getBarcosHundidosJugador() + 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean tiradaMaquina() {
        //Tirada de la máquina, sacamos coordenada aleatoria
        int coordenadaAleatoria;
        int[] xy = new int[2];
        int x = -1, y = -1, contador = 0;

        coordenadaAleatoria = this.generarAleatorioDistinto(this.coordenadasAleatoriasTiradasMaquina);

        //si ya ha generado todos los aleatorios no quedan turnos, devuelvo false
        if (coordenadaAleatoria == -1) {
            return false;
        }

        //paso el aleatorio a x e y
        for (int i = 0; i < this.getTableroJugador().length; i++) {
            for (int j = 0; j < this.getTableroJugador()[i].length; j++) {
                contador = contador + 1;
                if (contador == coordenadaAleatoria) {
                    x = i;
                    y = j;
                }

            }
        }
        
        //guardamos las coordenadas para posible uso de la GUI
        xy[0]=x;
        xy[1]=y;
        this.setxEyAleatorioMaquina(xy);
        

        //añadimos un turno
        this.setTurnosMaquina(this.getTurnosMaquina() + 1);

        //Comprobamos si ha acertado la tirada
        if (this.getTableroJugador()[xy[0]][xy[1]]) {
            this.setTableroContrincanteMaquina(this.añadirTiradaMaquina(xy[0], xy[1]));
            this.setBarcosHundidosMaquina(this.getBarcosHundidosMaquina() + 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean[][] añadirTiradaJugador(int x, int y) {
        boolean[][] aux = this.getTableroContrincanteJugador();
        aux[x][y] = true;
        return aux;
    }

    private boolean[][] añadirTiradaMaquina(int x, int y) {
        boolean[][] aux = this.getTableroContrincanteMaquina();
        aux[x][y] = true;
        return aux;
    }

    private boolean[][] tableroAleatorio(int tamaño, int numBarcos) {
        boolean[][] tablero = new boolean[tamaño][tamaño]; //tablero que devolveremos relleno con aleatorios
        int[] aleatorios = new int[numBarcos]; //array con las posiciones aleatorias
        int contador = 0; //para saber la celda correspondiente a la posición

        //Genero posiciones aleatorias y las añado a la lista
        for (int i = 0; i < numBarcos; i++) {
            aleatorios[i]=this.generarAleatorioDistinto(coordenadasAleatoriasTableroMaquina);
        }

        //Recorro el tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                //Recorro los aleatorios
                contador = contador + 1;//comienzo en 1
                for (int k = 0; k < aleatorios.length; k++) {
                    //si la posición actual del tablero es igual a la posición aleatoria
                    //lo pongo a true
                    if (contador == aleatorios[k]) {
                        tablero[i][j] = true;
                    }
                }
            }
        }
        return tablero;
    }

    private int generarAleatorioDistinto(ArrayList lista) {
        double aleatorio;
        int coordenadaAleatoria;

        //Si todavía quedan tiradas
        //if (lista.size() < this.getTurnos()) {
            aleatorio = Math.random() * (this.getTamañoTablero() * this.getTamañoTablero()) + 1;
            coordenadaAleatoria = (int) aleatorio;
            //Si la lista está vacía  (es la primera tirada)
            if (lista.isEmpty()) {
                lista.add(coordenadaAleatoria);
                return coordenadaAleatoria;
            } else { //si no está vacia comprobamos si ya está en la lista
                if (lista.contains(coordenadaAleatoria)) {
                    //si está volvemos a generar otro recursivamente
                    return generarAleatorioDistinto(lista);
                } else {//si no lo añadimos a la lista y lo devolvemos
                    lista.add(coordenadaAleatoria);
                    return coordenadaAleatoria;
                }
            }
        //} else { //si no quedan tiradas devolvemos -1
            //return -1;
        //}
    }

    public boolean todosHundidos(String jugador) {
        boolean hundidos = false;

        switch (jugador) {
            case "Jugador":

                if (this.getNumBarcos() == this.getBarcosHundidosJugador()) {
                    hundidos = true;
                }

                break;
            case "Maquina":

                if (this.getNumBarcos() == this.getBarcosHundidosMaquina()) {
                    hundidos = true;
                }

                break;
        }

        return hundidos;
    }

    public boolean turnosGastados(String jugador){
        boolean gastados = false;
        
        switch (jugador) {
            case "Jugador":

                if (this.getTurnos() == this.getTurnosJugador()) {
                    gastados = true;
                }

                break;
            case "Maquina":

                if (this.getTurnos() == this.getTurnosMaquina()) {
                    gastados = true;
                }

                break;
        }
        
        return gastados;
    }
    //Estado actual de la partida
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Turnos del Jugador: " + this.getTurnosJugador());
        sb.append("\nTurnos de la Máquina: " + this.getTurnosMaquina());
        sb.append("\nAciertos del Jugador: " + this.getBarcosHundidosJugador());
        sb.append("\nAciertos de la Máquina: " + this.getBarcosHundidosMaquina());
        return sb.toString();
    }

}
