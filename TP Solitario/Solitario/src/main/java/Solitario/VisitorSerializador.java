package Solitario;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface VisitorSerializador {
    Klondike cargarEstadoKlondike(InputStream in) throws IOException, ClassNotFoundException;
    FreeCell cargarEstadoFreeCell(InputStream in) throws IOException, ClassNotFoundException;
    void guardarEstado(Solitario solitario, OutputStream os) throws IOException;

}
