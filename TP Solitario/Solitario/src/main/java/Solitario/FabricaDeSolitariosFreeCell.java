package Solitario;

import Reglas.ReglasFreeCell;
import StackDeCartas.Mazo;
public class FabricaDeSolitariosFreeCell implements FabricaDeSolitarios {
    public FreeCell crearSolitario(){
        final int CANTIDADDEFUNDACIONES = 4;
        final int CANTIDADDECOLUMNAS = 8;
        Mazo mazo = new Mazo();
        ReglasFreeCell reglas = new ReglasFreeCell();
        return new FreeCell(mazo,reglas,CANTIDADDEFUNDACIONES,CANTIDADDECOLUMNAS);
    }
}
