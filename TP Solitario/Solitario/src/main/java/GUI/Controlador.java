package GUI;
import Excepciones.ExcepcionDestinoInvalido;
import Excepciones.ExcepcionOrigenInvalido;
import Solitario.*;
import javafx.scene.control.Button;



public class Controlador {


    protected Vista vista;
    protected Solitario solitario;

    protected final int SININDICE = -1;

    final private String COLUMNA = "columna";
    final private String FUNDACION = "fundacion";
    final private String DESCARTE = "descarte";

    final private String MAZO = "mazo";

    final private String AUXILIAR = "auxiliar";

    protected Boolean esperandoDestino;

    protected String estructuraOrigen;
    protected int indiceEstructuraOrigen;
    protected String estructuraDestino;
    protected int indiceEstructuraDestino;

    protected int indiceCarta;


    public Controlador(Solitario solitario, Vista vista){
        this.solitario = solitario;
        this.vista = vista;
        this.esperandoDestino = false;
    }

    public void iniciar(){
        vista.mostrar(pulsarCarta -> {
            try {
                Button botonFuente = (Button) pulsarCarta.getSource();
                String estructura = (String) botonFuente.getProperties().get("estructura");
                int indiceEstructura = (int) botonFuente.getProperties().get("indiceEstructura");
                int indiceCarta = (int) botonFuente.getProperties().get("indiceCarta");
                
                if (esperandoDestino) {
                    setDestino(estructura, indiceEstructura);
                } else {
                    setOrigen(estructura, indiceEstructura, indiceCarta);

                }
            }catch(Exception E){
                vista.mensajeError(E);
            }


        }, pulsarMazo -> {
            try {
                solitario.jugadaSacarCartaDelMazo();
                iniciar();

            }catch(Exception E){
                vista.mensajeError(E);
            }


        });

    }


    public void setOrigen(String estructuraOrigen, int indiceOrigen, int indiceCarta){
        this.esperandoDestino = true;
        this.estructuraOrigen = estructuraOrigen;
        this.indiceEstructuraOrigen = indiceOrigen;
        this.indiceCarta = indiceCarta;

    }


    public void setDestino(String estructuraDestino, int indiceEstructuraDestino) throws Exception{
        this.esperandoDestino = false;
        if(destinoValido(estructuraDestino,indiceEstructuraDestino)) {
            this.estructuraDestino = estructuraDestino;
            this.indiceEstructuraDestino = indiceEstructuraDestino;

            procesar(estructuraOrigen, indiceEstructuraOrigen, indiceCarta, this.estructuraDestino, this.indiceEstructuraDestino);

            iniciar();
            verificarJuegoGanado();
        }else{
            vista.mensajeError(new ExcepcionDestinoInvalido());
        }
    }
public void verificarJuegoGanado(){
        if(this.solitario.juegoTerminado()){
            vista.mensajeAlUsuario("Felicitaciones ganaste!");
        }
}
public boolean destinoValido(String estructuraDestino, int indiceEstructuraDestino ){
    return !this.estructuraOrigen.equals(estructuraDestino) || indiceEstructuraDestino != indiceEstructuraOrigen;
}
    public void procesar(String estructuraOrigen,int indiceEstructuraOrigen,int indiceCarta, String estructuraDestino, int indiceEstructuraDestino) throws Exception {

        switch(estructuraOrigen){
            case COLUMNA:
                procesarMovimientosColumna(indiceEstructuraOrigen,indiceCarta, estructuraDestino, indiceEstructuraDestino);
                break;

            case FUNDACION: // la fundacion solo puede mover a columna
                solitario.jugadaFundacionAColumna(indiceEstructuraDestino,indiceEstructuraOrigen);

            case DESCARTE:
                procesarMovimientosDescarte(estructuraDestino,indiceEstructuraDestino);
                break;

            case AUXILIAR:
                procesarMovimientosAuxiliar(indiceEstructuraOrigen,estructuraDestino,indiceEstructuraDestino);
                break;
            default:
                vista.mensajeError(new ExcepcionOrigenInvalido());
        }

    }


    private void procesarMovimientosColumna(int indiceEstructuraOrigen,int indiceCarta, String estructuraDestino, int indiceEstructuraDestino) throws Exception {
        switch(estructuraDestino){
            case COLUMNA:
                solitario.jugadaColumnaAColumna(indiceEstructuraDestino,indiceEstructuraOrigen,indiceCarta);
                break;

            case FUNDACION:
                solitario.jugadaColumnaAFundacion(indiceEstructuraOrigen,indiceEstructuraDestino);
                break;

            case AUXILIAR:
                solitario.jugadaColumnaAAuxiliar(indiceEstructuraOrigen,indiceEstructuraDestino);
                break;

            default:
                vista.mensajeError(new ExcepcionDestinoInvalido());


        }
    }

    private void procesarMovimientosDescarte(String estructuraDestino, int indiceEstructuraDestino) throws Exception {
        switch(estructuraDestino){
            case COLUMNA:
                solitario.jugadaDescarteColumna(indiceEstructuraDestino);
                break;
            case FUNDACION:
                solitario.jugadaDescarteFundacion(indiceEstructuraDestino);
                break;
            default:
                vista.mensajeError(new ExcepcionDestinoInvalido());
        }
    }

    private void procesarMovimientosAuxiliar(int indiceEstructuraOrigen, String estructuraDestino, int indiceEstructuraDestino) throws Exception {
        switch(estructuraDestino){
            case COLUMNA:
                solitario.jugadaAuxiliarAColumna(indiceEstructuraOrigen,indiceEstructuraDestino);
                break;

            case FUNDACION:
                solitario.jugadaAuxiliarAFundacion(indiceEstructuraOrigen,indiceEstructuraDestino);
                break;

            default:
                vista.mensajeError(new ExcepcionDestinoInvalido());
        }
    }


}
