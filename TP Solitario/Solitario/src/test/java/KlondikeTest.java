import Excepciones.ExcepcionMoverColumnaVacia;
import Excepciones.ExcepcionNoPuedoAgregarCarta;
import Excepciones.ExcepcionNoPuedoSacarCartaDelDescarte;
import Solitario.*;
import org.junit.Test;
import utils.KlondikeTestingUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class KlondikeTest {
   @Test
    public void klondikeInicializacionEnEstadoAPuntoDeGanar1() {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnColumna();
        assertFalse(klondike.juegoTerminado());
    }
    @Test
    public void klondikeJuegoGanado1() throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnColumna();
        klondike.jugadaColumnaAFundacion(0, 3);
        assertTrue(klondike.juegoTerminado());
    }
    @Test
    public void klondikeMovimientoDesdeColumnaVacia() throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnColumna();
        try {
            klondike.jugadaColumnaAFundacion(4, 3);
        }catch(Exception error){
            assertTrue(true);
        }
    }
    @Test
    public void klondikeDescarteVacio() throws Exception {
        FabricaDeSolitarios fabricaKlondike = new FabricaDeSolitariosKlondike();
        Solitario klondike = fabricaKlondike.crearSolitario();
        try {
            klondike.jugadaDescarteFundacion(3);
        }catch(ExcepcionNoPuedoSacarCartaDelDescarte errorDescarteVacio){
            assertTrue(true);
        }
    }
    @Test
    public void klondikeVariasJugadas() throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta, ExcepcionNoPuedoSacarCartaDelDescarte {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnDescarte();
        klondike.jugadaDescarteColumna(3); //13 de picas a columna 4
        klondike.jugadaFundacionAColumna(2, 1); // 13 de diamantes a columna 3
        klondike.jugadaFundacionAColumna(3, 1); // 12 de diamantes a columna 4

        klondike.jugadaColumnaAFundacion(3, 1); // 12 de diamantes a fundacion
        klondike.jugadaColumnaAFundacion(3, 3); // 13 de picas a fundacion
        klondike.jugadaColumnaAFundacion(2, 1); // 13 de diamantes a fundacion
        assertTrue(klondike.juegoTerminado());
    }

    @Test
    public void klondikeInicializacionEnEstadoAPuntoDeGanar2() {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnDescarte();
        assertFalse(klondike.juegoTerminado());
    }
    @Test
    public void klondikeJuegoGanado2() throws ExcepcionNoPuedoSacarCartaDelDescarte, ExcepcionNoPuedoAgregarCarta {
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        klondike.juegoAPuntoDeGanarConCartaEnDescarte();
        klondike.jugadaDescarteFundacion(3);
        assertTrue(klondike.juegoTerminado());
    }
    @Test
    public void klondikeCargarJuego() throws IOException, ClassNotFoundException, ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {
        //arrange
        KlondikeTestingUtils klondike = new KlondikeTestingUtils();
        SerializadorSolitario serializadorSolitario = new SerializadorSolitario();
        klondike.juegoAPuntoDeGanarConCartaEnColumna();
        //act
        //aca sabemos que si movemos una carta ganamos el juego, lo guardamos
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        klondike.guardarEstado(serializadorSolitario,bytes);

        Solitario klondikeCargado;
        klondikeCargado = Klondike.cargarEstado(serializadorSolitario, new ByteArrayInputStream(bytes.toByteArray()));
        klondikeCargado.jugadaColumnaAFundacion(0, 3);
        //assert
        assertTrue(klondikeCargado.juegoTerminado());
    }


}