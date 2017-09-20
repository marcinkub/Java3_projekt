package pl.umk.mat.tests;

import org.junit.Test;
import pl.umk.mat.marcinkub.Figura;
import pl.umk.mat.marcinkub.Pole;

import static org.junit.Assert.*;

public class konwersja__fig_string_test {
    @Test
    public void konwersja_do_figury() throws Exception {

        assertEquals(Pole.Konwersja_do_figury("wieza"), Figura.wieza);
        assertEquals(Pole.Konwersja_do_figury("kon"), Figura.kon);


    }

    @Test
    public void konwersja_figura_string() throws Exception {
        assertEquals(Pole.Konwersja_figura_string(Figura.kon), "kon");
        assertEquals(Pole.Konwersja_figura_string(Figura.wieza), "wieza");


    }

}