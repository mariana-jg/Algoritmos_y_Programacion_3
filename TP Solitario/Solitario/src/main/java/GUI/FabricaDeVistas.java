package GUI;

import Solitario.Solitario;
import javafx.stage.Stage;

public interface FabricaDeVistas {

    Vista crearVista(Solitario solitario, Stage stage);
}
