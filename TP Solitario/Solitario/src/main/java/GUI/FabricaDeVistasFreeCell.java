package GUI;

import Solitario.Solitario;
import javafx.stage.Stage;

public class FabricaDeVistasFreeCell implements FabricaDeVistas{
    @Override
    public VistaFreeCell crearVista(Solitario freeCell, Stage stage) {
        return new VistaFreeCell(freeCell, stage);
    }
}
