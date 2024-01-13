package Excepciones;

public class ExcepcionCartaNoVisible extends Exception {

    public ExcepcionCartaNoVisible(){
        super("La carta seleccionada esta dada vuelta");
    }

}
