import Carta.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    @Test
    public void ComprobarCartaAnterior() {
        //
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        //
        boolean resultado = carta1.esPosterior(carta2.obtenerNumero());
        //
        assertTrue(resultado);
    }
    @Test
    public void ComprobarCartaPosterior() {
        //
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        //
        boolean resultado = carta2.esAnterior(carta1.obtenerNumero());
        //
        assertTrue(resultado);
    }
}