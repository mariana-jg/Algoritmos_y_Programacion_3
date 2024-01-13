import Carta.*;
import Columna.*;
import StackDeCartas.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class FundacionTest {

    @Test
    public void cambiarAColumna() {

        ColumnaDeJuego columnaDestino = new ColumnaDeJuego();
        Fundacion fundacionDeLaQueMuevo = new Fundacion();

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

        columnaDestino.agregarCarta(carta1);
        columnaDestino.agregarCarta(carta2);
        columnaDestino.agregarCarta(carta3);
        columnaDestino.agregarCarta(carta4);
        columnaDestino.agregarCarta(carta5);
        columnaDestino.agregarCarta(carta6);
        columnaDestino.agregarCarta(carta7);
        columnaDestino.agregarCarta(carta8);
        columnaDestino.agregarCarta(carta9);
        columnaDestino.agregarCarta(carta10);
        columnaDestino.agregarCarta(carta11);
        columnaDestino.agregarCarta(carta12);
        fundacionDeLaQueMuevo.agregarCarta(carta13);

        fundacionDeLaQueMuevo.cambiarAColumna(columnaDestino);

        assertTrue(fundacionDeLaQueMuevo.estaVacia());
        assertSame(columnaDestino.verUltimaCarta().obtenerPalo(), carta13.obtenerPalo());
        assertEquals(columnaDestino.verUltimaCarta().obtenerNumero(), carta13.obtenerNumero());
    }
}