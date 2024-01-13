
import Solitario.FabricaDeSolitariosFreeCell;
import Solitario.FreeCell;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class FabricaDeSolitariosFreeCellTest {


    @Test
    public void crearSolitario() {
        //arrange
        FabricaDeSolitariosFreeCell fabricaDeSolitarios = new FabricaDeSolitariosFreeCell();
        //act
        FreeCell freeCell = fabricaDeSolitarios.crearSolitario();
        //assert
        assertThat(freeCell,instanceOf(FreeCell.class));
    }

}