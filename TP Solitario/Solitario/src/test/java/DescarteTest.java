import Carta.*;
import Columna.*;
import StackDeCartas.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DescarteTest {

    @Test
    public void robarCarta() {
        //arrange
        Descarte d = new Descarte();
        Carta c = new Carta(1, Palo.DIAMANTES);
        d.agregarCarta(c);
        Carta c2 = new Carta(2, Palo.DIAMANTES);
        d.agregarCarta(c2);
        Carta c3 = new Carta(3, Palo.DIAMANTES);
        d.agregarCarta(c3);
        Carta esperada = c3;
        //act
        Carta obtenida = d.robarUltimaCarta();
        //assert
        assertTrue(!(obtenida.esPosterior(esperada.obtenerNumero())) && !(obtenida.esAnterior(esperada.obtenerNumero()))); //si no es mayor ni menor es igual
        assertTrue(obtenida.esMismoPalo(esperada.obtenerPalo()));
    }
    @Test
    public void cambiarCartaAColumna() {


        ColumnaDeJuego colDestino = new ColumnaDeJuego();
        Descarte descarteQueMuevo = new Descarte();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        Carta carta7 = new Carta(7, Palo.CORAZONES);
        Carta carta8 = new Carta(6, Palo.TREBOLES);
        Carta carta9 = new Carta(5, Palo.CORAZONES);
        Carta carta10 = new Carta(4, Palo.TREBOLES);
        Carta carta11 = new Carta(3, Palo.CORAZONES);
        Carta carta12 = new Carta(2, Palo.TREBOLES);
        Carta carta13 = new Carta(1, Palo.CORAZONES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);
        colDestino.agregarCarta(carta4);
        colDestino.agregarCarta(carta5);
        colDestino.agregarCarta(carta6);
        colDestino.agregarCarta(carta7);
        colDestino.agregarCarta(carta8);
        colDestino.agregarCarta(carta9);
        colDestino.agregarCarta(carta10);
        colDestino.agregarCarta(carta11);
        colDestino.agregarCarta(carta12);
        descarteQueMuevo.agregarCarta(carta13);

        descarteQueMuevo.cambiarAColumna(colDestino);

        assertEquals(colDestino.verUltimaCarta().obtenerNumero(), carta13.obtenerNumero());
        assertSame(colDestino.verUltimaCarta().obtenerPalo(), carta13.obtenerPalo());
        assertTrue(descarteQueMuevo.estaVacia());

    }
    @Test
    public void cambiarCartaAFundacion() {


        Fundacion fundacionDestino = new Fundacion();
        Descarte descarteQueMuevo = new Descarte();

        Carta carta10 = new Carta(4, Palo.CORAZONES);
        Carta carta11 = new Carta(3, Palo.CORAZONES);
        Carta carta12 = new Carta(2, Palo.CORAZONES);
        Carta carta13 = new Carta(1, Palo.CORAZONES);


        descarteQueMuevo.agregarCarta(carta10);
        descarteQueMuevo.agregarCarta(carta11);
        descarteQueMuevo.agregarCarta(carta12);
        fundacionDestino.agregarCarta(carta13);

        descarteQueMuevo.cambiarAStack(fundacionDestino);

        assertEquals(fundacionDestino.verUltimaCarta().obtenerNumero(), carta12.obtenerNumero());
        assertSame(fundacionDestino.verUltimaCarta().obtenerPalo(), carta12.obtenerPalo());
        assertEquals(descarteQueMuevo.verUltimaCarta().obtenerNumero(), carta11.obtenerNumero());
        assertSame(descarteQueMuevo.verUltimaCarta().obtenerPalo(), carta11.obtenerPalo());
    }


    @Test
    public void vaciarDescarte(){
        //arrange
        Descarte descarte = new Descarte();
        Mazo mazo = new Mazo();

        while(!mazo.estaVacia()){
            mazo.pasarCartaADescarte(descarte);
        }

        //act

        descarte.vaciarDescarte(mazo);

        //assert
        assertTrue(descarte.estaVacia());
        assertFalse(mazo.estaVacia());

    }
}