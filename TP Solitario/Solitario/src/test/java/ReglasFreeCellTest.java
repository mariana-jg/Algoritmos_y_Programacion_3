import Carta.*;
import Reglas.ReglasFreeCell;
import StackDeCartas.StackDeCartas;
import org.junit.Test;
import Columna.ColumnaDeJuego;

import static org.junit.Assert.*;

public class ReglasFreeCellTest {

    @Test
    public void puedoExtraerDeColumnaConCartas() {
        ColumnaDeJuego columnaAExtraer = new ColumnaDeJuego();
        ReglasFreeCell reglas = new ReglasFreeCell();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        columnaAExtraer.agregarCarta(carta1);
        //act
        boolean valorObtenido = reglas.puedoExtraerDeColumna(columnaAExtraer);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void puedoExtraerDeColumnaVacia() {
        ColumnaDeJuego columnaAExtraer = new ColumnaDeJuego();
        ReglasFreeCell reglas = new ReglasFreeCell();

        //act
        boolean valorObtenido = reglas.puedoExtraerDeColumna(columnaAExtraer);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void puedoAgregarCartaAColVacia() {
        ColumnaDeJuego columnaAAgregar = new ColumnaDeJuego();
        ReglasFreeCell reglas = new ReglasFreeCell();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta1.obtenerNumero(),carta1.obtenerPalo(),columnaAAgregar);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void puedoAgregarCartaAColConCartas() {
        ColumnaDeJuego columnaAAgregar = new ColumnaDeJuego();
        ReglasFreeCell reglas = new ReglasFreeCell();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        columnaAAgregar.agregarCarta(carta1);

        Carta carta2 = new Carta(12, Palo.PICAS);
        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta2.obtenerNumero(),carta2.obtenerPalo(),columnaAAgregar);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void NopuedoAgregarCartaAColConCartas() {
        ColumnaDeJuego columnaAAgregar = new ColumnaDeJuego();
        ReglasFreeCell reglas = new ReglasFreeCell();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        columnaAAgregar.agregarCarta(carta1);

        Carta carta2 = new Carta(12, Palo.CORAZONES);
        //act
        boolean valorObtenido = reglas.puedoAgregarCarta(carta2.obtenerNumero(),carta2.obtenerPalo(),columnaAAgregar);
        //assert
        assertFalse(valorObtenido);
    }

    @Test
    public void puedoAgregarCartaDelAuxiliar() {

        ReglasFreeCell reglas = new ReglasFreeCell();
        StackDeCartas auxiliar = new StackDeCartas();
        //act
        boolean valorObtenido = reglas.puedoAgregarCartaAlAuxiliar(auxiliar);
        //assert
        assertTrue(valorObtenido);
    }

    @Test
    public void NopuedoAgregarCartaDelAuxiliar() {

        ReglasFreeCell reglas = new ReglasFreeCell();
        StackDeCartas auxiliar = new StackDeCartas();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        auxiliar.agregarCarta(carta1);
        //act
        boolean valorObtenido = reglas.puedoAgregarCartaAlAuxiliar(auxiliar);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void puedoSacarCartaDelAuxiliar() {

        ReglasFreeCell reglas = new ReglasFreeCell();
        StackDeCartas auxiliar = new StackDeCartas();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        auxiliar.agregarCarta(carta1);
        //act
        boolean valorObtenido = reglas.puedoSacarCartaDelAuxiliar(auxiliar);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void NoPuedoSacarCartaDelAuxiliar() {

        ReglasFreeCell reglas = new ReglasFreeCell();
        StackDeCartas auxiliar = new StackDeCartas();
        //act
        boolean valorObtenido = reglas.puedoSacarCartaDelAuxiliar(auxiliar);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void PuedoAgregarColACol() throws Exception {
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();
        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);

        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);

        colOrigen.agregarCarta(carta4);
        colOrigen.agregarCarta(carta5);
        colOrigen.agregarCarta(carta6);

        int espaciosVacios = 3;
        //act
        boolean valorObtenido =  reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        //assert
        assertTrue(valorObtenido);
    }
    @Test
    public void NoPuedoAgregarColACol()throws Exception {
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);

        colOrigen.agregarCarta(carta4);
        colOrigen.agregarCarta(carta5);
        colOrigen.agregarCarta(carta6);

        int espaciosVacios = 1;
        //act
        boolean valorObtenido =  reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void PuedoAgregarColACol2() throws Exception{
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.TREBOLES);
        Carta carta5 = new Carta(9, Palo.CORAZONES);
        Carta carta6 = new Carta(8, Palo.TREBOLES);

        colOrigen.agregarCarta(carta4);
        colOrigen.agregarCarta(carta5);
        colOrigen.agregarCarta(carta6);

        int espaciosVacios = 2;
        //act
        boolean valorObtenido =  reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void NoPuedoAgregarColAColVacia()throws Exception {
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();

        int espaciosVacios = 1;
        //assert
        try {
            reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        }catch(Exception e){
            assertTrue(true);
        }
    }
    @Test
    public void NoPuedoAgregarColACol3() throws Exception {
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(10, Palo.CORAZONES);
        Carta carta5 = new Carta(9, Palo.TREBOLES);
        Carta carta6 = new Carta(8, Palo.CORAZONES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);

        colOrigen.agregarCarta(carta4);
        colOrigen.agregarCarta(carta5);
        colOrigen.agregarCarta(carta6);

        int espaciosVacios = 2;
        //act
        boolean valorObtenido =  reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        //assert
        assertFalse(valorObtenido);
    }
    @Test
    public void NoPuedoAgregarColACol4()throws Exception {
        ColumnaDeJuego colOrigen = new ColumnaDeJuego();
        ColumnaDeJuego colDestino = new ColumnaDeJuego();

        ReglasFreeCell reglas = new ReglasFreeCell();

        Carta carta1 = new Carta(13, Palo.CORAZONES);
        Carta carta2 = new Carta(12, Palo.TREBOLES);
        Carta carta3 = new Carta(11, Palo.CORAZONES);
        Carta carta4 = new Carta(13, Palo.TREBOLES);
        Carta carta5 = new Carta(12, Palo.CORAZONES);
        Carta carta6 = new Carta(11, Palo.TREBOLES);

        colDestino.agregarCarta(carta1);
        colDestino.agregarCarta(carta2);
        colDestino.agregarCarta(carta3);

        colOrigen.agregarCarta(carta4);
        colOrigen.agregarCarta(carta5);
        colOrigen.agregarCarta(carta6);

        int espaciosVacios = 2;
        //act
        boolean valorObtenido =  reglas.puedoAgregarCartasAColumna(colOrigen,colDestino,espaciosVacios);
        //assert
        assertFalse(valorObtenido);
    }

}