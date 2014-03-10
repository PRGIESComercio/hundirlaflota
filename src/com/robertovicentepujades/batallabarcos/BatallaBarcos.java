
package com.robertovicentepujades.batallabarcos;

import java.util.ArrayList;

/**
 * Clase para el típico juego de los barcos, pero simplificado. 
 * Los barcos son de una posición.
 * Los tableros son un array de dos dimensiones de booleanos, en la posición que haya un barco estará a true.
 * @author Roberto Vicente Pujades
 * @version 2.0
 */
public class BatallaBarcos {

    private boolean[][] tableroJugador; //Tablero donde el jugador pondrá sus barcos
    private boolean[][] tableroMaquina; //Tablero donde la máquina pondrá sus barcos
    private boolean[][] tableroContrincanteJugador; //Tablero donde el jugador apuntará a los barcos de la máquina
    private boolean[][] tableroContrincanteMaquina; //Tablero donde la máquina apuntará a los barcos del jugador

    private final int tamañoTablero; //Número de lados del tablero, por defecto 8
    private final int numBarcos; //Número de barcos, por defecto 10 (1 casilla por barco)

    private byte turno; //Para determinar a quién le toca: 0 jugador y 1 máquina
    private int turnosJugador; //Número de turnos que lleva el jugador
    private int turnosMaquina; //Número de turnos que lleva la máquina

    private int barcosHundidosJugador; //Guardamos los aciertos que lleva el jugador
    private int barcosHundidosMaquina; //Guardamos los aciertos que lleva la máquina

    private ArrayList coordenadasAleatoriasTiradasMaquina; //guardamos las coordenadas aleatorias para comprobar si ya ha salido en tiradaMaquina
    private ArrayList coordenadasAleatoriasTableroMaquina; //guardamos las coordenadas aleatorias para generar el tablero de la máquina

    private int[] xEyAleatorioMaquina; //array de dos posiciones con las coordenadas x e y para uso en la gui

    /**
     * Constructor para un juego personalizado
     * @param tamañoTablero Entero para indicar el lado del tablero, el tamaño será el lado*lado
     * @param numBarcos Entero para indicar el número de barcos en el tablero
     */
    public BatallaBarcos(int tamañoTablero, int numBarcos) {
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

    /**
     * Constructor para el juego por defecto. Por defecto el tamaño del tablero será de 8x8 y el número de barcos será de 10
     */
    public BatallaBarcos() {
        this.tamañoTablero = 8;
        this.numBarcos = 10;
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

    /**
     * Método que establece el tablero del jugador
     * @param tableroJugador Tablero del jugador con las posiciones de sus barcos
     */
    public void setTableroJugador(boolean[][] tableroJugador) {
        this.tableroJugador = tableroJugador;
    }

    private void setTableroMaquina(boolean[][] tableroMaquina) {
        this.tableroMaquina = tableroMaquina;
    }

    /**
     * Método que devuelve el tablero del jugador
     * @return Tablero del jugador
     */
    public boolean[][] getTableroJugador() {
        return tableroJugador;
    }

    private boolean[][] getTableroMaquina() {
        return tableroMaquina;
    }

    /**
     * Método que devuelve el tamaño de un lado del tablero
     * @return int Entero con el tamaño de un lado del tablero
     */
    public int getTamañoTablero() {
        return tamañoTablero;
    }

    /**
     * Método que devuelve el número de barcos que tendrá cada jugador en la partida
     * @return int Entero con el número de barcos
     */
    public int getNumBarcos() {
        return numBarcos;
    }

    private void setTableroContrincanteJugador(boolean[][] tableroContrincanteJugador) {
        this.tableroContrincanteJugador = tableroContrincanteJugador;
    }

    private void setTableroContrincanteMaquina(boolean[][] tableroContrincanteMaquina) {
        this.tableroContrincanteMaquina = tableroContrincanteMaquina;
    }

    private boolean[][] getTableroContrincanteJugador() {
        return tableroContrincanteJugador;
    }

    private boolean[][] getTableroContrincanteMaquina() {
        return tableroContrincanteMaquina;
    }

    private void setTurno(byte turno) {
        this.turno = turno;
    }

    /**
     * Método que retorna a quién le toca
     * @return byte Entero con un 0 si le toca al Jugador o un 1 si le toca a la Máquina
     */
    public byte getTurno() {
        return turno;
    }

    /**
     * Método que retorna a quién le toca
     * @return String Cadena con Jugador o Maquina, dependiendo a quién le toque
     */
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

    /**
     * Método para que comience el juego, se le pasa el tablero del jugador, establece el tablero aleatorio de la máquina y el turno aleatorio
     * @param tableroJugador Con el tablero del jugador
     */
    public void empezarJuego(boolean[][] tableroJugador) {
        //Asignamos el tablero puesto por el jugador a su tablero
        this.setTableroJugador(tableroJugador);
        //La máquina pone aleatoriamente su tablero
        this.setTableroMaquina(this.tableroAleatorio(this.getTamañoTablero(), this.getNumBarcos()));
        this.setTableroContrincanteJugador(this.getTableroMaquina());
        //elegimos turno aleatoriamente
        this.elegirTurno();
    }

    /**
     * Método privado que elige el primer turno aleatoriamente y lo guarda en la propiedad turno.
     * Si turno tiene un 0 el turno es del Jugador
     * Si turno tiene un 1 el turno es de la Máquina
     */
    private void elegirTurno() {
        int t = (int) Math.floor(Math.random() * 2);
        this.setTurno((byte) t);
    }
    
    /**
     * Método que lanza la tirada del jugador
     * @param x La posición de la fila
     * @param y La posición de la columna
     * @return boolean Devuelve true si ha acertado la tirada y false si ha fallado
     */
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

    /**
     * Método que lanza la tirada de la máquina.
     * Genera el número aleatorio distinto y comprueba si está en el tablero de su contrincante
     * @return boolean Devuelve true si ha acertado la tirada y false si ha fallado
     */
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
        xy[0] = x;
        xy[1] = y;
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
    
    /**
     * Método privado que añade la tirada del jugador
     * @param x Posición de la fila
     * @param y Posición de la columna
     * @return boolean[][] Con el nuevo tablero del contrincante del jugador
     */
    private boolean[][] añadirTiradaJugador(int x, int y) {
        boolean[][] aux = this.getTableroContrincanteJugador();
        aux[x][y] = true;
        return aux;
    }

    /**
     * Método privado que añade la tirada de la máquina
     * @param x Posición de la fila
     * @param y Posición de la columna
     * @return boolean[][] Con el nuevo tablero del contrincante de la máquina
     */
    private boolean[][] añadirTiradaMaquina(int x, int y) {
        boolean[][] aux = this.getTableroContrincanteMaquina();
        aux[x][y] = true;
        return aux;
    }

    /**
     * Método privado que genera el tablero de la máquina aleatoriamente
     * @param tamaño Con el tamaño del tablero a generar
     * @param numBarcos Con el número de barcos a posicionar
     * @return boolean[][] Con el tablero generado y los barcos posicionados
     */
    private boolean[][] tableroAleatorio(int tamaño, int numBarcos) {
        boolean[][] tablero = new boolean[tamaño][tamaño]; //tablero que devolveremos relleno con aleatorios
        int[] aleatorios = new int[numBarcos]; //array con las posiciones aleatorias
        int contador = 0; //para saber la celda correspondiente a la posición

        //Genero posiciones aleatorias y las añado a la lista
        for (int i = 0; i < numBarcos; i++) {
            aleatorios[i] = this.generarAleatorioDistinto(coordenadasAleatoriasTableroMaquina);
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

    /**
     * Método privado que genera un número aleatorio comprendido entre 1 y el tamaño del tablero al cuadrado. 
     * Este aleatorio es distinto y los guarda en la lista pasada por parámetro
     * @param lista Se le envia la lista del tiro de la máquina o la lista para el tablero aleatorio de la máquina
     * @return int Entero con el aleatorio distinto generado
     */
    private int generarAleatorioDistinto(ArrayList lista) {
        double aleatorio;
        int coordenadaAleatoria;

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

    }

    /**
     * Comprueba si ha hundido todos los barcos del contrincante
     *
     * @param jugador 'Maquina' o 'Jugador'
     * @return boolean Con verdadero si ha hundido todos los barcos o false si
     * no los ha hundido.
     */
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
    
    /**
     * Estado actual de la partida de los jugadores por separado
     * @param nombre El nombre del jugador o 'Maquina' para el estado de la máquina
     * @return Cadena con el estado del jugador indicado en los parámetros
     */
    public String estadoJugador(String nombre){
        String s;
        if (nombre.equals("Maquina")){
            s="La máquina ha tirado " + this.getTurnosMaquina() + " veces y ha hundido " + this.getBarcosHundidosMaquina() + " barcos";
        }else{
            s=nombre + " ha tirado " + this.getTurnosJugador() + " veces y ha hundido " + this.getBarcosHundidosJugador() + " barcos";
        }
        
        return s;
    }

    /**
     * Estado actual de la partida general
     *
     * @return String con los turnos gastados por cada jugador y el número de
     * aciertos
     */
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
