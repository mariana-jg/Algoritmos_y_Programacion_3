import Carta.*;
import Columna.*;
import Reglas.*;
import StackDeCartas.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReglasKlondikeTest {
    @Test
    public void puedoAgregarCarta() {
        //arrange
        Fundacion fundacion = new Fundacion();
        Reglas reglas = new ReglasKlondike();
        Carta carta = new Carta(1, Palo.CORAZONES);
        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta.obtenerNumero(),carta.obtenerPalo(),fundacion);
        //preguntamos si podemos agregar un as que puede estar en la fundacion
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void puedoAgregarCarta2() {
        //arrange
        Fundacion fundacion = new Fundacion();
        Reglas reglas = new ReglasKlondike();
        Carta carta1 = new Carta(1, Palo.CORAZONES);
        Carta carta2 = new Carta(3, Palo.TREBOLES);
        fundacion.agregarCarta(carta1);
        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta2.obtenerNumero(),carta2.obtenerPalo(),fundacion);
        //preguntamos si podemos agregar una carta que no puede estar ahi

        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void puedoAgregarCarta3() {
        //arrange
        Fundacion fundacion = new Fundacion();
        Reglas reglas = new ReglasKlondike();
        Carta carta1 = new Carta(1, Palo.CORAZONES);
        Carta carta2 = new Carta(2, Palo.CORAZONES);
        fundacion.agregarCarta(carta1);

        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta2.obtenerNumero(),carta2.obtenerPalo(),fundacion);
        //preguntamos si puedo agregarle una carta correcta a la fundacion

        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void puedoAgregarCarta4() {
        //arrange
        Fundacion fundacion = new Fundacion();
        Reglas reglas = new ReglasKlondike();
        Carta carta = new Carta(6, Palo.CORAZONES);

        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta.obtenerNumero(),carta.obtenerPalo(),fundacion);
        //preguntamos si puedo agregarle una carta incorrecta a la fundacion

        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void agregarCartaOPilaDeCartaAColumnaVacia() throws Exception {
        ColumnaDeJuego columna = new ColumnaDeJuego();
        ReglasKlondike reglas = new ReglasKlondike();
        ColumnaDeJuego cartasAAgregar= new ColumnaDeJuego();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);

        cartasAAgregar.agregarCarta(carta1);
        cartasAAgregar.agregarCarta(carta2);
        cartasAAgregar.agregarCarta(carta3);
        //act
        boolean valorObtenido = reglas.puedoAgregarCartasAColumna(cartasAAgregar,columna);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void agregarCartaOPilaDeCartaAColumnaQueCorresponde() throws Exception{
        ColumnaDeJuego columna = new ColumnaDeJuego();
        ReglasKlondike reglas = new ReglasKlondike();
        ColumnaDeJuego cartasAAgregar= new ColumnaDeJuego();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        columna.agregarCarta(carta1);
        columna.agregarCarta(carta2);
        columna.agregarCarta(carta3);

        cartasAAgregar.agregarCarta(carta4);
        cartasAAgregar.agregarCarta(carta5);
        cartasAAgregar.agregarCarta(carta6);
        //act
        boolean valorObtenido = reglas.puedoAgregarCartasAColumna(cartasAAgregar,columna);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void agregarCartaOPilaDeCartaAColumnaQueNOCorresponde() throws Exception{
        ColumnaDeJuego columna = new ColumnaDeJuego();
        ReglasKlondike reglas = new ReglasKlondike();
        ColumnaDeJuego cartasAAgregar= new ColumnaDeJuego();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        columna.agregarCarta(carta1);
        columna.agregarCarta(carta2);
        columna.agregarCarta(carta3);

        cartasAAgregar.agregarCarta(carta5);
        cartasAAgregar.agregarCarta(carta6);
        //act
        boolean valorObtenido = reglas.puedoAgregarCartasAColumna(cartasAAgregar,columna);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void PuedoSacarCartaDelDeposito() {
        Mazo mazo = new Mazo();
        ReglasKlondike reglas = new ReglasKlondike();
        boolean resultado = reglas.puedoSacarCartaDelMazo(mazo);
        assertTrue(resultado);
    }
}