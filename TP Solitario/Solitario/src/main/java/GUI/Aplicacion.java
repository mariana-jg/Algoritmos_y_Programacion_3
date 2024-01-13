package GUI;

import Solitario.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import ManejoDeArchivos.*;

import java.io.IOException;

import static ManejoDeArchivos.ManejoDeArchivos.existeArchivo;
import static ManejoDeArchivos.ManejoDeArchivos.obtenerPathRaiz;


public class Aplicacion extends Application {

    private FabricaDeSolitarios fabricaDeSolitarios;
    private FabricaDeVistas fabricaDeVistas;
    Solitario solitario;
    private Vista vista;
    private Controlador controlador;
    public void iniciarSolitario(String nombreSolitario, Stage stage) {

        crearFabricaYVista(nombreSolitario);

        iniciarMVC(stage);


    }
    public void iniciarMVC(Stage stage){
        solitario = fabricaDeSolitarios.crearSolitario();

        vista = fabricaDeVistas.crearVista(solitario,stage);

        controlador = new Controlador(solitario,vista);

        controlador.iniciar();
    }
    public void crearFabricaYVista(String nombreSolitario){
        if(nombreSolitario.equals("Klondike")){
            fabricaDeSolitarios = new FabricaDeSolitariosKlondike();
            fabricaDeVistas = new FabricaDeVistasKlondike();

        }else{

            fabricaDeSolitarios = new FabricaDeSolitariosFreeCell();
            fabricaDeVistas = new FabricaDeVistasFreeCell();

        }
    }

    public void continuarSolitarioKlondike(Klondike klondike, Stage stage){

        solitario = klondike;
        if(!solitario.juegoTerminado()) {
            fabricaDeVistas = new FabricaDeVistasKlondike();
            vista = fabricaDeVistas.crearVista(solitario, stage);
            controlador = new Controlador(solitario, vista);

            controlador.iniciar();
        }
        else{
            menuPrincipal(stage);
        }
    }

    public void continuarSolitarioFreeCell(FreeCell freeCell, Stage stage){
        solitario = freeCell;
        if(!solitario.juegoTerminado()){
        fabricaDeVistas = new FabricaDeVistasFreeCell();
        vista = fabricaDeVistas.crearVista(solitario,stage);
        controlador = new Controlador(solitario,vista);

        controlador.iniciar();}
        else{
            menuPrincipal(stage);
        }
    }

    public void guardarEstado(){
        String tipoSolitario;
        if(solitario instanceof FreeCell){
            tipoSolitario = "FreeCell";
        }else{
            tipoSolitario = "Klondike";
        }
        try {
            ManejoDeArchivos.guardarSolitario(solitario, tipoSolitario);
        }catch(IOException errorGuardado){
            vista.mensajeError(errorGuardado);
        }
    }

    public void menuPrincipal(Stage stage){
        stage.setTitle("Menu Principal");
        var stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #025928;");

        var opcionKlondike = new MenuItem("Klondike");
        var opcionFreeCell = new MenuItem("FreeCell");


        var menuDesplegable = new MenuButton("Seleccione un solitario");
        menuDesplegable.getItems().addAll(opcionKlondike, opcionFreeCell);

        stackPane.getChildren().add(menuDesplegable);
        StackPane.setAlignment(menuDesplegable, Pos.CENTER);

        var scene = new Scene(stackPane, 640, 500);
        EventHandler<ActionEvent> pulsarBoton = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent evento) {
                String opcionElegida = ((MenuItem) evento.getSource()).getText();
                menuDesplegable.setText(opcionElegida);

                iniciarSolitario(opcionElegida, stage);

            }
        };

        opcionKlondike.setOnAction(pulsarBoton);
        opcionFreeCell.setOnAction(pulsarBoton);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        try{
            if(existeArchivo(obtenerPathRaiz() + "/FreeCell")){

                FreeCell solitario = ManejoDeArchivos.cargarSolitarioFreecell("FreeCell");
                continuarSolitarioFreeCell(solitario, stage);

            }else if(existeArchivo(obtenerPathRaiz() + "/Klondike")){

                Klondike solitario = ManejoDeArchivos.cargarSolitarioKlondike("Klondike");
                continuarSolitarioKlondike(solitario, stage);
            }
            else{
                menuPrincipal(stage);
            }
        }catch(IOException | ClassNotFoundException errorLectura){

            menuPrincipal(stage);
        }
    }
    @Override
    public void stop(){
        try{
            guardarEstado();
        }catch(NullPointerException noHaySolitario){
            //este error sucede cuando se cierra la aplicacion antes de crear un solitario
            //realmente no hay que proceder en este bloque catch
        }
    }


}