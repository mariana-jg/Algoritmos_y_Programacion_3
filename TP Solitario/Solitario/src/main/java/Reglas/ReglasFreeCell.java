package Reglas;
import Carta.Carta;
import Carta.Palo;
import Columna.ColumnaDeJuego;
import StackDeCartas.StackDeCartas;

public class ReglasFreeCell extends Reglas{
    @Override
    public boolean puedoExtraerDeColumna(ColumnaDeJuego columna) {
        return !columna.estaVacia();
    }
    public boolean puedoAgregarCartasAColumna(ColumnaDeJuego columnaExtraer, ColumnaDeJuego columnaDepositar, int espaciosVacios){

        if(columnaDepositar.estaVacia()){ // si esta vacia y
            return columnaExtraer.obtenerTamanio() <= espaciosVacios;
        }
        else if(esDescendenteYAlternada(columnaExtraer) && columnaExtraer.obtenerTamanio() <= (espaciosVacios)){
            Carta primeraCartaExtraer = columnaExtraer.verPrimeraCarta();
            Carta ultimaCartaDepositar = columnaDepositar.verUltimaCarta();
            return primeraCartaExtraer.obtenerNumero() == (ultimaCartaDepositar.obtenerNumero()-1) &&
                    primeraCartaExtraer.obtenerColor() != ultimaCartaDepositar.obtenerColor();
        }
        return false;
    }

    private boolean esDescendenteYAlternada(ColumnaDeJuego columna){
        boolean esAlternada = true;
        boolean esDescendente = true;
        int i = 0;
        int j = 1;
        if(columna.obtenerTamanio() >=2) {
            Carta carta1;
            Carta carta2;

            while (esAlternada && esDescendente && j < columna.obtenerTamanio() ){
                carta1 = columna.verCarta(i);
                carta2 = columna.verCarta(j);
                if(carta1.obtenerColor() == carta2.obtenerColor()){
                    esAlternada = false;
                }
                if(carta2.obtenerNumero() != (carta1.obtenerNumero()-1)){
                    esDescendente = false;
                }

                i++;
                j++;

            }


        }
        return (esAlternada && esDescendente);
    }
    @Override //si la columna no esta vacia se agregan de forma descendente y alternada en color
    public boolean puedoAgregarCarta(int numeroCarta, Palo paloCarta, ColumnaDeJuego columna) {
        if(columna.estaVacia()){
            return true; // cualquier carta puede ubicarse en una columna vacia
        } else {
            return(numeroCarta < columna.verUltimaCarta().obtenerNumero() &&
                    (paloCarta.obtenerColor() != columna.verUltimaCarta().obtenerColor()));
        }
    }
    public boolean puedoAgregarCartaAlAuxiliar(StackDeCartas auxiliar) {
        return auxiliar.estaVacia();
    }
    public boolean puedoSacarCartaDelAuxiliar(StackDeCartas auxiliar){
        return !auxiliar.estaVacia();
    }
}
