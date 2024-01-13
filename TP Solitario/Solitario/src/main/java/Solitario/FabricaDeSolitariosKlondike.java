package Solitario;

import Reglas.ReglasKlondike;
import StackDeCartas.Mazo;
public class FabricaDeSolitariosKlondike implements FabricaDeSolitarios {
    public Klondike crearSolitario(){
        final int CANTIDADDEFUNDACIONES = 4;
        final int CANTIDADDECOLUMNAS = 7;
        Mazo mazo = new Mazo();
        ReglasKlondike reglas = new ReglasKlondike();
        return new Klondike(mazo,reglas,CANTIDADDEFUNDACIONES,CANTIDADDECOLUMNAS);
    }
}
