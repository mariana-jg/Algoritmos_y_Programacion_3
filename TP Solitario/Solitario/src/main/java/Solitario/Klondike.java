package Solitario;

import Carta.*;
import Columna.*;
import Excepciones.ExcepcionCartaNoVisible;
import Excepciones.ExcepcionMoverColumnaVacia;
import Excepciones.ExcepcionNoPuedoAgregarCarta;
import Excepciones.ExcepcionNoPuedoSacarCartaDelDescarte;
import Reglas.*;
import StackDeCartas.*;

import java.io.IOException;
import java.io.InputStream;

public class Klondike extends Solitario {

    //-----------------------------------------------------Atributos---------------------------------------------------//
    protected Descarte descarte;
    protected ReglasKlondike reglas;
    //-----------------------------------------------------Métodos----------------------------------------------------//
    public Klondike() {
        CANTIDADDEFUNDACIONES = 4;
        CANTIDADDECOLUMNAS = 7;
        this.mazo = new Mazo();
        this.fundaciones = new Fundacion[CANTIDADDEFUNDACIONES];
        this.iniciarFundaciones();
        this.tablero = new ColumnaDeJuego[CANTIDADDECOLUMNAS];
        this.iniciarColumnas();
        this.descarte = new Descarte();
        this.reglas = new ReglasKlondike();
        this.iniciarMesa();
    }
    public Klondike(Mazo mazo, ReglasKlondike reglas, int CANTIDADDEFUNDACIONES, int CANTIDADDECOLUMNAS) {
        this.CANTIDADDEFUNDACIONES = CANTIDADDEFUNDACIONES;
        this.CANTIDADDECOLUMNAS = CANTIDADDECOLUMNAS;
        asignarMazo(mazo);
        this.fundaciones = new Fundacion[CANTIDADDEFUNDACIONES];
        this.iniciarFundaciones();
        this.tablero = new ColumnaDeJuego[CANTIDADDECOLUMNAS];
        this.iniciarColumnas();
        this.descarte = new Descarte();
        asignarReglas(reglas);
        this.iniciarMesa();
    }
    public void asignarReglas(ReglasKlondike reglas){
        this.reglas = reglas;
    }
    private void iniciarTablero(int[] CantidadDeCartasPorColumna, StackDeCartas mazo) {
        for (int i = 0; i < CANTIDADDECOLUMNAS; i++) {
            for (int j = 0; j < CantidadDeCartasPorColumna[i]; j++) {
                tablero[i].agregarCarta(mazo.robarUltimaCarta());
            }
            tablero[i].verUltimaCarta().cambiarVisibilidad();
        }
    }
    public void iniciarMesa() {
        this.mazo.mezclarMazo();
        iniciarTablero(new int[]{1, 2, 3, 4, 5, 6, 7}, mazo);
    }
    public void jugadaFundacionAColumna(int indiceColumnaDestino, int indiceFundacionOrigen) throws ExcepcionNoPuedoAgregarCarta {
        ColumnaDeJuego columnaDestino = this.tablero[indiceColumnaDestino];
        int numeroCartaAAgregar = this.fundaciones[indiceFundacionOrigen].verUltimaCarta().obtenerNumero();
        Palo paloCartaAAgregar = this.fundaciones[indiceFundacionOrigen].verUltimaCarta().obtenerPalo();

        if (this.reglas.puedoAgregarCarta(numeroCartaAAgregar, paloCartaAAgregar, columnaDestino)) {
            this.fundaciones[indiceFundacionOrigen].cambiarAColumna(columnaDestino);
        }
        else {
            throw new ExcepcionNoPuedoAgregarCarta();
        }
    }
    public void jugadaDescarteColumna(int indiceColumnaDestino) throws ExcepcionNoPuedoSacarCartaDelDescarte {
        if(reglas.puedoSacarCartaDelDescarte(descarte)) {

            Carta cartaDescarte = this.descarte.verUltimaCarta();
            Palo paloCartaDescarte = cartaDescarte.obtenerPalo();
            int numeroCartaDescarte = cartaDescarte.obtenerNumero();

            if (reglas.puedoAgregarCarta(numeroCartaDescarte, paloCartaDescarte, this.tablero[indiceColumnaDestino])) {
                this.descarte.cambiarAColumna(this.tablero[indiceColumnaDestino]);
            }

        }
        else {

            throw new ExcepcionNoPuedoSacarCartaDelDescarte();

        }
    }
    public void jugadaDescarteFundacion(int indiceFundacion) throws ExcepcionNoPuedoSacarCartaDelDescarte, ExcepcionNoPuedoAgregarCarta {
        Fundacion fundacionDestino = this.fundaciones[indiceFundacion];
        if (this.reglas.puedoSacarCartaDelDescarte(this.descarte)) {
            int numeroCartaAAgregar = this.descarte.verUltimaCarta().obtenerNumero();
            Palo paloCartaAAgregar = this.descarte.verUltimaCarta().obtenerPalo();

            if (this.reglas.puedoAgregarCarta(numeroCartaAAgregar, paloCartaAAgregar, fundacionDestino)) {
                this.descarte.cambiarAStack(fundacionDestino);
            }
            else {
                throw new ExcepcionNoPuedoAgregarCarta();
            }
        }
        else {
            throw new ExcepcionNoPuedoSacarCartaDelDescarte();
        }
    }

    public void jugadaColumnaAColumna (int indiceColumnaDestino, int indiceColumnaOrigen, int indiceCartaOrigen) throws ExcepcionMoverColumnaVacia, ExcepcionCartaNoVisible, ExcepcionNoPuedoAgregarCarta {
            verificarColumnaVacia(tablero[indiceColumnaOrigen]);
            ColumnaDeJuego columnaDestino = this.tablero[indiceColumnaDestino];
            ColumnaDeJuego columnaOrigen = this.tablero[indiceColumnaOrigen];

            ColumnaDeJuego auxiliar = columnaOrigen.obtenerSubColumna(indiceCartaOrigen);

            if (this.reglas.esCartaVisible(columnaOrigen, indiceCartaOrigen) ) {
                if(this.reglas.puedoAgregarCartasAColumna(auxiliar, columnaDestino)) {
                    columnaOrigen.cambiarDeColumna(columnaDestino, indiceCartaOrigen);
                    if (!this.tablero[indiceColumnaOrigen].estaVacia()) {
                        if (!this.tablero[indiceColumnaOrigen].verUltimaCarta().esVisible()) {
                            this.tablero[indiceColumnaOrigen].verUltimaCarta().cambiarVisibilidad();
                        }
                    }
                }else{
                    throw new ExcepcionNoPuedoAgregarCarta();

                }
            }else{
                throw new ExcepcionCartaNoVisible();

            }
    }
    public void jugadaSacarCartaDelMazo() {
            if (this.reglas.puedoSacarCartaDelMazo(this.mazo)) {
                this.mazo.pasarCartaADescarte(this.descarte);
            }else {
                this.descarte.vaciarDescarte(this.mazo);

            }
    }
    @Override
    public void jugadaColumnaAAuxiliar(int indiceColumnaOrigen, int indiceAuxiliar) throws Exception {
        throw new Exception("No existen auxiliares en una partida Klondike");
    }
    @Override
    public void jugadaAuxiliarAColumna(int indiceAuxiliar, int indiceColumnaDestino) throws Exception {
        throw new Exception("No existen auxiliares en una partida Klondike");
    }
    @Override
    public void jugadaAuxiliarAFundacion(int indiceAuxiliar, int indiceFundacion) throws Exception {
        throw new Exception("No existen auxiliares en una partida Klondike");
    }
    public boolean juegoTerminado(){
            return this.reglas.juegoGanado(this.fundaciones);
        }


    public static Klondike cargarEstado(VisitorSerializador visitorSerializador, InputStream in) throws IOException, ClassNotFoundException {
        return visitorSerializador.cargarEstadoKlondike(in);
    }
    @Override
    public void jugadaColumnaAFundacion(int indiceColumnaOrigen, int indiceFundacionDestino) throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {
        verificarColumnaVacia(tablero[indiceColumnaOrigen]);
        Fundacion fundacionDestino = this.fundaciones[indiceFundacionDestino];
        if (this.reglas.puedoExtraerDeColumna(tablero[indiceColumnaOrigen])) {
            int numeroCartaAAgregar = this.tablero[indiceColumnaOrigen].verUltimaCarta().obtenerNumero();
            Palo paloCartaAAgregar = this.tablero[indiceColumnaOrigen].verUltimaCarta().obtenerPalo();

            if (this.reglas.puedoAgregarCarta(numeroCartaAAgregar, paloCartaAAgregar, fundacionDestino)) {
                this.tablero[indiceColumnaOrigen].cambiarAStackDeCartas(fundacionDestino);
                if(!this.tablero[indiceColumnaOrigen].estaVacia()){
                    if(!this.tablero[indiceColumnaOrigen].verUltimaCarta().esVisible()) {
                        this.tablero[indiceColumnaOrigen].verUltimaCarta().cambiarVisibilidad();
                    }
                }
            }else{
                throw new ExcepcionNoPuedoAgregarCarta();
            }
        }
    }

    public Descarte obtenerDescarte(){
        return this.descarte;
    }

    @Override
    public StackDeCartas[] obtenerAuxiliares() {
        return null;
    }


}

