package Solitario;

import Columna.ColumnaDeJuego;
import Excepciones.ExcepcionCartaNoVisible;
import Excepciones.ExcepcionMoverColumnaVacia;
import Excepciones.ExcepcionNoPuedoAgregarCarta;
import StackDeCartas.*;
import javafx.scene.layout.Pane;

import java.io.*;

public abstract class Solitario implements Serializable {
    //-----------------------------------------------------Atributos---------------------------------------------------//
    protected int CANTIDADDEFUNDACIONES;
    protected int CANTIDADDECOLUMNAS;

    protected Mazo mazo;
    protected Fundacion[] fundaciones;
    protected ColumnaDeJuego[] tablero;
    //-----------------------------------------------------Métodos----------------------------------------------------//
    protected void iniciarFundaciones(){
        for (int i = 0; i < CANTIDADDEFUNDACIONES; i++){
            fundaciones[i] = new Fundacion();
        }
    }
    protected void iniciarColumnas(){
        for (int i = 0; i < CANTIDADDECOLUMNAS; i++){
            tablero[i] = new ColumnaDeJuego();
        }
    }


    public abstract void jugadaSacarCartaDelMazo() throws Exception;
    public abstract void jugadaFundacionAColumna(int indiceColumnaDestino, int indiceFundacionOrigen) throws Exception;
    public abstract void jugadaColumnaAFundacion(int indiceColumnaOrigen, int indiceFundacionDestino) throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta;
    public abstract void jugadaColumnaAColumna (int indiceColumnaDestino, int indiceColumnaOrigen, int indiceCartaOrigen) throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta, ExcepcionCartaNoVisible;
    public abstract void jugadaColumnaAAuxiliar(int indiceColumnaOrigen, int indiceAuxiliar) throws Exception;
    public abstract void jugadaDescarteColumna(int indiceColumnaDestino) throws Exception;
    public abstract void jugadaDescarteFundacion(int indiceFundacion) throws Exception;
    public abstract void jugadaAuxiliarAColumna(int indiceAuxiliar, int indiceColumnaDestino) throws Exception;
    public abstract void jugadaAuxiliarAFundacion(int indiceAuxiliar, int indiceFundacion) throws Exception;

    protected void verificarColumnaVacia(ColumnaDeJuego columna) throws ExcepcionMoverColumnaVacia{
        if(columna.estaVacia()){
            throw new ExcepcionMoverColumnaVacia();
        }
    }
    protected abstract void iniciarMesa();

    public void guardarEstado(VisitorSerializador visitorSerializador, OutputStream os) throws IOException {
        visitorSerializador.guardarEstado(this, os);

    }
    public void asignarMazo(Mazo mazo){
        this.mazo = mazo;
    }
    public abstract boolean juegoTerminado();

    public Mazo obtenerMazo(){
        return this.mazo;
    }

    public Fundacion[] obtenerFundaciones(){
        return this.fundaciones;
    }

    public ColumnaDeJuego[] obtenerTablero(){
        return this.tablero;
    }

    public abstract Descarte obtenerDescarte();

    public abstract StackDeCartas[] obtenerAuxiliares();
}
