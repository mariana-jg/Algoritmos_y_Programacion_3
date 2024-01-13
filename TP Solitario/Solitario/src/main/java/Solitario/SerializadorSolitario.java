package Solitario;

import java.io.*;
public class SerializadorSolitario implements VisitorSerializador{
    @Override
    public void guardarEstado(Solitario solitario, OutputStream os) throws IOException {
        ObjectOutputStream solitarioSalida = new ObjectOutputStream(os);
        solitarioSalida.writeObject(solitario);
        solitarioSalida.flush();
    }
    @Override
    public Klondike cargarEstadoKlondike(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInStream = new ObjectInputStream(in);
        return (Klondike) objectInStream.readObject();
    }
    @Override
    public FreeCell cargarEstadoFreeCell(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInStream = new ObjectInputStream(in);
        return (FreeCell) objectInStream.readObject();
    }
}
