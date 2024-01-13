package GUI;

import Solitario.Solitario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VistaFreeCell extends Vista{

    public VistaFreeCell(Solitario FreeCell, Stage stage){

        super(FreeCell, stage);
        stage.setScene(new Scene(pane,1000,1000));
        stage.setMaximized(true);
    }

    @Override
    public void  mostrar(EventHandler<ActionEvent> pulsarCarta, EventHandler<ActionEvent> pulsarMazo){
        limpiarPantalla();
        mostrarAuxiliares(this.solitario.obtenerAuxiliares(), pane,100,50, pulsarCarta);
        mostrarFundaciones(this.solitario.obtenerFundaciones(), pane,1025,50, pulsarCarta);
        mostrarColumnas(this.solitario.obtenerTablero(), pane,100, 225, pulsarCarta);
        stage.show();

    }
}
