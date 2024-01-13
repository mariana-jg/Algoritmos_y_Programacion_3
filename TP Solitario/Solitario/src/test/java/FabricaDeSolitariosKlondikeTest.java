import Solitario.FabricaDeSolitariosKlondike;
import Solitario.Klondike;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class FabricaDeSolitariosKlondikeTest {

    @Test
    public void crearSolitario() {
        //arrange
        FabricaDeSolitariosKlondike fabricaDeSolitarios = new FabricaDeSolitariosKlondike();
        //act
        Klondike klondike = fabricaDeSolitarios.crearSolitario();
        //assert
        assertThat(klondike,instanceOf(Klondike.class));
    }

}