import Carta.*;
import Columna.*;
import StackDeCartas.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnaDeJuegoTest {

    @Test
    public void agregarCartas() {

        ColumnaDeJuego ColQueMuevo = new ColumnaDeJuego();
        ColumnaDeJuego ColDestino = new ColumnaDeJuego();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        ColDestino.agregarCarta(carta1);
        ColDestino.agregarCarta(carta2);
        ColDestino.agregarCarta(carta3);

        ColQueMuevo.agregarCarta(carta4);
        ColQueMuevo.agregarCarta(carta5);
        ColQueMuevo.agregarCarta(carta6);

        ColDestino.agregarCartas(ColQueMuevo);

        assertEquals(carta6.obtenerNumero(),ColDestino.verUltimaCarta().obtenerNumero());
        assertEquals(carta6.obtenerPalo(),ColDestino.verUltimaCarta().obtenerPalo());

    }
    @Test
    public void cambiarDeColumna01() {

        ColumnaDeJuego colQueMuevo = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        Carta carta11 = new Carta(13, Palo.CORAZONES);
        Carta carta22 = new Carta(12, Palo.TREBOLES);
        Carta carta33 = new Carta(11, Palo.CORAZONES);
        Carta carta44 = new Carta(10, Palo.TREBOLES);
        Carta carta55 = new Carta(9, Palo.CORAZONES);
        Carta carta66 = new Carta(8, Palo.TREBOLES);
        Carta carta77 = new Carta(7, Palo.CORAZONES);
        Carta carta88 = new Carta(6, Palo.TREBOLES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);
        colDestino.agregarCarta(carta4);
        colDestino.agregarCarta(carta5);
        colDestino.agregarCarta(carta6);

        colQueMuevo.agregarCarta(carta11);
        colQueMuevo.agregarCarta(carta22);
        colQueMuevo.agregarCarta(carta33);
        colQueMuevo.agregarCarta(carta44);
        colQueMuevo.agregarCarta(carta55);
        colQueMuevo.agregarCarta(carta66);
        colQueMuevo.agregarCarta(carta77);
        colQueMuevo.agregarCarta(carta88);

        colQueMuevo.cambiarDeColumna(colDestino,6);


        assertEquals(6, colQueMuevo.obtenerTamanio());
        assertEquals(8, colDestino.obtenerTamanio());
        assertEquals(colDestino.verUltimaCarta().obtenerNumero(),carta88.obtenerNumero());
        assertEquals(colDestino.verUltimaCarta().obtenerPalo(),carta88.obtenerPalo());

    }
    @Test
    public void cambiarCartaDeColumenaAFundacion() {

        ColumnaDeJuego colDeLaQueMuevo = new ColumnaDeJuego();
        Fundacion fundacionDestino = new Fundacion();

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

        colDeLaQueMuevo.agregarCarta(carta1);
        colDeLaQueMuevo.agregarCarta(carta2);
        colDeLaQueMuevo.agregarCarta(carta3);
        colDeLaQueMuevo.agregarCarta(carta4);
        colDeLaQueMuevo.agregarCarta(carta5);
        colDeLaQueMuevo.agregarCarta(carta6);
        colDeLaQueMuevo.agregarCarta(carta7);
        colDeLaQueMuevo.agregarCarta(carta8);
        colDeLaQueMuevo.agregarCarta(carta9);
        colDeLaQueMuevo.agregarCarta(carta10);
        colDeLaQueMuevo.agregarCarta(carta11);
        colDeLaQueMuevo.agregarCarta(carta12);
        colDeLaQueMuevo.agregarCarta(carta13);

        colDeLaQueMuevo.cambiarAStackDeCartas(fundacionDestino);

        assertEquals(colDeLaQueMuevo.verUltimaCarta().obtenerNumero(), carta12.obtenerNumero());
        assertSame(colDeLaQueMuevo.verUltimaCarta().obtenerPalo(), carta12.obtenerPalo());
        assertEquals(fundacionDestino.verUltimaCarta().obtenerNumero(), carta13.obtenerNumero());
        assertSame(fundacionDestino.verUltimaCarta().obtenerPalo(), carta13.obtenerPalo());
    }


    @Test
    public void ultimaCartaQuedaDescubierta(){

        ColumnaDeJuego columnaOrigen = new ColumnaDeJuego();
        ColumnaDeJuego columnaDestino= new ColumnaDeJuego();

        Carta carta1 = new Carta(13, Palo.CORAZONES); // esta carta no esta visible
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        carta2.cambiarVisibilidad();
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        carta3.cambiarVisibilidad();
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        carta4.cambiarVisibilidad();


        Carta carta5 = new Carta(13, Palo.DIAMANTES);
        carta5.cambiarVisibilidad();
        Carta carta6 = new Carta(12, Palo.PICAS);
        carta6.cambiarVisibilidad();
        Carta carta7 = new Carta(11, Palo.DIAMANTES);
        carta7.cambiarVisibilidad();

        columnaOrigen.agregarCarta(carta1);
        columnaOrigen.agregarCarta(carta2);
        columnaOrigen.agregarCarta(carta3);
        columnaOrigen.agregarCarta(carta4);



        columnaDestino.agregarCarta(carta5);



        columnaOrigen.cambiarDeColumna(columnaDestino,1);

        boolean esCartaVisible = columnaOrigen.esCartaVisible(0);

        assertTrue(esCartaVisible);



    }
    @Test
    public void seCambianTodasLasCartasDeColumna(){

        ColumnaDeJuego columnaOrigen = new ColumnaDeJuego();
        ColumnaDeJuego columnaDestino= new ColumnaDeJuego();


        Carta carta1 = new Carta(12, Palo.TREBOLES);
        carta1.cambiarVisibilidad();
        Carta carta2 = new Carta(11, Palo.CORAZONES);
        carta2.cambiarVisibilidad();
        Carta carta3 = new Carta(10, Palo.TREBOLES);
        carta3.cambiarVisibilidad();


        Carta carta4 = new Carta(13, Palo.DIAMANTES);
        carta4.cambiarVisibilidad();


        columnaOrigen.agregarCarta(carta1);
        columnaOrigen.agregarCarta(carta2);
        columnaOrigen.agregarCarta(carta3);


        columnaDestino.agregarCarta(carta4);


        columnaOrigen.cambiarDeColumna(columnaDestino,0);


        assertTrue(columnaOrigen.estaVacia());
    }
}