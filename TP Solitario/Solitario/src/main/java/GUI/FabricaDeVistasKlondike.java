package GUI;

import Solitario.Solitario;
import javafx.stage.Stage;

public class FabricaDeVistasKlondike implements FabricaDeVistas{

    @Override
    public Vista crearVista(Solitario klondike, Stage stage) {
        return new VistaKlondike(klondike, stage);
    }
}
