package Carta;
public enum Palo {CORAZONES,PICAS,TREBOLES,DIAMANTES;
    private Color color;
    static {
        CORAZONES.color = Color.ROJO;
        DIAMANTES.color = Color.ROJO;
        PICAS.color = Color.NEGRO;
        TREBOLES.color = Color.NEGRO;
    }
    public Color obtenerColor(){
        return this.color;
    }
}
