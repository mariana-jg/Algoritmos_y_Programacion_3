package GUI;

import Columna.ColumnaDeJuego;
import ManejoDeArchivos.ManejoDeArchivos;
import StackDeCartas.Descarte;
import StackDeCartas.Fundacion;
import Carta.*;
import Solitario.*;
import StackDeCartas.Mazo;
import StackDeCartas.StackDeCartas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public abstract class Vista {

    final private int ESPACIADOENTRECARTAS = 27;

    final private int SININDICE = -1;

    final private String COLUMNA = "columna";
    final private String FUNDACION = "fundacion";
    final private String DESCARTE = "descarte";

    final private String MAZO = "mazo";

    final private String AUXILIAR = "auxiliar";

    protected Pane pane;

    protected Solitario solitario;

    protected Stage stage;


    protected Vista(Solitario solitario, Stage stage){
        this.pane = new Pane();
        this.solitario = solitario;
        this.stage = stage;
        pane.setStyle("-fx-background-color: #025928;");



    }


    protected void mostrarColumnas(ColumnaDeJuego[] tablero, Pane pane, int coordenadaX, int coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        for(int columna = 0; columna < tablero.length; columna++){
            mostrarColumna(tablero[columna], pane,columna, coordenadaX,coordenadaY,pulsarCarta);
            coordenadaX = coordenadaX + 75;
        }

    }

    protected void mostrarColumna(ColumnaDeJuego columna, Pane pane, int indiceEstructura, int coordenadaX, int coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        if(!columna.estaVacia()) {
            for (int i = 0; i < columna.obtenerTamanio(); i++) {
                mostrarCarta(columna.verCarta(i), pane, COLUMNA, indiceEstructura, i, coordenadaX, coordenadaY, pulsarCarta);
                coordenadaY = coordenadaY + ESPACIADOENTRECARTAS;
            }
        }else{
            Button botonColumna = new Button();
            botonColumna.getProperties().put("estructura",COLUMNA);
            botonColumna.getProperties().put("indiceEstructura", indiceEstructura);
            botonColumna.getProperties().put("indiceCarta", indiceEstructura);
            botonColumna.setOnAction(pulsarCarta);
            botonColumna.setPrefSize(68,80);
            pane.getChildren().add(botonColumna);
            botonColumna.setLayoutX(coordenadaX);
            botonColumna.setLayoutY(coordenadaY);

        }
    }

    protected void mostrarFundaciones(Fundacion[] fundaciones, Pane pane, double coordenadaX, double coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        for(int fundacion = 0; fundacion < fundaciones.length; fundacion++){
            mostrarFundacion(fundaciones[fundacion], pane,fundacion, coordenadaX,coordenadaY, pulsarCarta);
            coordenadaX = coordenadaX + 75;
        }
    }

    protected void mostrarFundacion(Fundacion fundacion,Pane pane,int indiceEstructura, double coordenadaX,double coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        if(!fundacion.estaVacia()) {
            mostrarCarta(fundacion.verUltimaCarta(), pane,FUNDACION,indiceEstructura, SININDICE , coordenadaX, coordenadaY, pulsarCarta);
        }else{
            Button botonFundacion = new Button();
            botonFundacion.getProperties().put("estructura",FUNDACION);
            botonFundacion.getProperties().put("indiceEstructura", indiceEstructura);
            botonFundacion.getProperties().put("indiceCarta", SININDICE);
            botonFundacion.setOnAction(pulsarCarta);
            botonFundacion.setPrefSize(68,80);
            pane.getChildren().add(botonFundacion);
            botonFundacion.setLayoutX(coordenadaX);
            botonFundacion.setLayoutY(coordenadaY);
        }
    }

    protected void mostrarCarta(Carta carta, Pane pane, String estructura,int indiceEstructura, int indiceCarta, double coordenadaX, double coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        ImageView imagenCarta = new ImageView();
        if(carta.esVisible()){
            imagenCarta.setImage(devolverImagenCarta(carta.obtenerNumero(),carta.obtenerPalo()));
        }else{
            imagenCarta.setImage(new Image("file:" + ManejoDeArchivos.obtenerCarpetaAssets() + "cartas/dorso/card_back.png"));

        }


        Button botonCarta = new Button();
        imagenCarta.setFitWidth(50);
        imagenCarta.setFitHeight(100);
        imagenCarta.setPreserveRatio(true);

        botonCarta.setGraphic(imagenCarta);
        botonCarta.getProperties().put("estructura",estructura );
        botonCarta.getProperties().put("indiceEstructura", indiceEstructura);
        botonCarta.getProperties().put("indiceCarta", indiceCarta);
        botonCarta.setOnAction(pulsarCarta);
        pane.getChildren().add(botonCarta);

        botonCarta.setLayoutX(coordenadaX);
        botonCarta.setLayoutY(coordenadaY);

    }
    protected Image devolverImagenCarta(int numero, Palo palo){
        return switch (palo) {
            case PICAS ->
                    new Image("file:" + ManejoDeArchivos.obtenerCarpetaAssets() + "cartas/picas/" + numero + ".png");
            case CORAZONES ->
                    new Image("file:" + ManejoDeArchivos.obtenerCarpetaAssets() + "cartas/corazones/" + numero + ".png");
            case DIAMANTES ->
                    new Image("file:" + ManejoDeArchivos.obtenerCarpetaAssets() + "cartas/diamantes/" + numero + ".png");
            default ->
                    new Image("file:" + ManejoDeArchivos.obtenerCarpetaAssets() + "cartas/treboles/" + numero + ".png");
        };
    }


    protected void mostrarMazo(Mazo mazo, Pane pane, double coordenadaX, double coordenadaY, EventHandler<ActionEvent> pulsarMazo){
        if(!mazo.estaVacia()) {
            mostrarCarta(mazo.verUltimaCarta(), pane,MAZO,SININDICE,SININDICE, coordenadaX, coordenadaY,pulsarMazo);
        }else{
            Button botonPedirCarta = new Button();
            botonPedirCarta.setOnAction(pulsarMazo);
            botonPedirCarta.setPrefSize(68,80);
            pane.getChildren().add(botonPedirCarta);
            botonPedirCarta.setLayoutX(coordenadaX);
            botonPedirCarta.setLayoutY(coordenadaY);
        }
    }

    protected void mostrarDescarte(Descarte descarte, Pane pane, int coordenadaX, int coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        if(!descarte.estaVacia()) {
            mostrarCarta(descarte.verUltimaCarta(),pane, DESCARTE,SININDICE,SININDICE,coordenadaX, coordenadaY, pulsarCarta);
        }
    }

    protected void mostrarStackGenerico(StackDeCartas stack, Pane pane, String nombreEstructura, int indiceEstructura, double coordenadaX, double coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        if(!stack.estaVacia()) {
            mostrarCarta(stack.verUltimaCarta(), pane,nombreEstructura,indiceEstructura,SININDICE, coordenadaX, coordenadaY, pulsarCarta);
        }else{
            Button botonStack = new Button();
            botonStack.getProperties().put("estructura",nombreEstructura);
            botonStack.getProperties().put("indiceEstructura",indiceEstructura);
            botonStack.getProperties().put("indiceCarta",SININDICE);
            botonStack.setPrefSize(68,80);
            botonStack.setOnAction(pulsarCarta);
            pane.getChildren().add(botonStack);
            botonStack.setLayoutX(coordenadaX);
            botonStack.setLayoutY(coordenadaY);
        }
    }

    protected void mostrarAuxiliares(StackDeCartas[] auxiliares , Pane pane, double coordenadaX, double coordenadaY, EventHandler<ActionEvent> pulsarCarta){
        for(int auxiliar = 0; auxiliar < auxiliares.length; auxiliar++){
            mostrarStackGenerico(auxiliares[auxiliar], pane,AUXILIAR,auxiliar, coordenadaX,coordenadaY, pulsarCarta);
            coordenadaX = coordenadaX + 75;
        }
    }

    public void limpiarPantalla(){
        pane.getChildren().clear();
    }

    public void mensajeError(Exception excepcion){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Movimiento invalido");
        alerta.setHeaderText("Se ha producido un movimiento invalido");
        alerta.setContentText(excepcion.getMessage());
        alerta.showAndWait();
    }

    public void mensajeAlUsuario(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Atencion!");
        alerta.setHeaderText(mensaje);
        alerta.showAndWait();

    }

    public abstract void mostrar(EventHandler<ActionEvent> pulsarCarta, EventHandler<ActionEvent> pulsarMazo);



}
