package pl.umk.mat.tests;

import org.junit.Test;
import pl.umk.mat.marcinkub.Pole;

import static org.junit.Assert.*;

public class PoleTest {
    @Test
    public void konwersja_do_index() throws Exception {
        int nrW =4;
        int nrK =5;
        int iindex=0;
        for(int i=1;i<9;i++)
            for(int j=1;j<9;j++) {
                assertEquals(Pole.Konwersja_do_index(i, j), iindex);
                iindex++;
            }
    }

}