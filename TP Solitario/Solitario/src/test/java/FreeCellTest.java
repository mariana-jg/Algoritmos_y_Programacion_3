import Excepciones.ExcepcionAuxiliarVacio;
import Excepciones.ExcepcionMoverColumnaVacia;
import Excepciones.ExcepcionNoPuedoAgregarCarta;
import Solitario.*;
import org.junit.Test;
import utils.FreeCellTestingUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class FreeCellTest {
    @Test
    public void freeCellInicializacionEnEstadoAPuntoDeGanar() {
        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        freeCell.juegoAPuntoDeGanarConCartaEnColumna();
        assertFalse(freeCell.juegoTerminado());
    }
    @Test
    public void freeCellJuegoGanado() throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {

        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        freeCell.juegoAPuntoDeGanarConCartaEnColumna();
        freeCell.jugadaColumnaAFundacion(0, 3);
        assertTrue(freeCell.juegoTerminado());
    }
    @Test
    public void freeCellMovimientoDesdeColumnaVacia() throws ExcepcionMoverColumnaVacia, ExcepcionNoPuedoAgregarCarta {
        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        freeCell.juegoAPuntoDeGanarConCartaEnColumna();
        try {
            freeCell.jugadaColumnaAFundacion(4, 3);
        }catch(ExcepcionMoverColumnaVacia errorColumnaVacia){
            assertFalse(freeCell.juegoTerminado());
        }
    }
    @Test
    public void freeCellMovimientoDesdeAuxiliar() throws ExcepcionAuxiliarVacio, ExcepcionNoPuedoAgregarCarta {
        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        freeCell.juegoAPuntoDeGanarConCartaEnAuxiliar();
        freeCell.jugadaAuxiliarAFundacion(0, 3);
        assertTrue(freeCell.juegoTerminado());
    }
    @Test
    public void freeCellVariosMovimientos() throws Exception {
        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        freeCell.juegoCasiGanado();
        freeCell.jugadaColumnaAColumna(5, 0, 1); //12 de picas a columna 5
        freeCell.jugadaColumnaAAuxiliar(5, 2); // 12 de picas a aux 2
        freeCell.jugadaAuxiliarAColumna(2, 6); // 12 de picas a col 5
        freeCell.jugadaColumnaAFundacion(6, 3);
        freeCell.jugadaColumnaAFundacion(0,3);

        assertTrue(freeCell.juegoTerminado());
    }

    @Test
    public void freeCellGuardarYCargarEstado() throws Exception {
        //arrange
        FreeCellTestingUtils freeCell = new FreeCellTestingUtils();
        VisitorSerializador serializador = new SerializadorSolitario();
        freeCell.juegoAPuntoDeGanarConCartaEnAuxiliar();
        freeCell.jugadaAuxiliarAColumna(0,0);
        Solitario freeCellCargado;
        //act
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        freeCell.guardarEstado(serializador, bytes);

        freeCellCargado = FreeCell.cargarEstado(serializador, new ByteArrayInputStream(bytes.toByteArray()));

        freeCellCargado.jugadaColumnaAFundacion(0,3);

        //assert
        assertTrue(freeCellCargado.juegoTerminado());
    }
}
