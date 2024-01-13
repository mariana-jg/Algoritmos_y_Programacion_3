package Excepciones;

public class ExcepcionMoverColumnaVacia extends Exception{
    public ExcepcionMoverColumnaVacia(){
        super("No se puede mover una columna vacia");
    }
}
