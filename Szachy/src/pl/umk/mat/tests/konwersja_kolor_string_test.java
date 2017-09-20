package pl.umk.mat.tests;

import org.junit.Test;
import pl.umk.mat.marcinkub.FIGURE_COLOR;
import pl.umk.mat.marcinkub.Pole;

import static org.junit.Assert.*;

public class konwersja_kolor_string_test {
    @Test
    public void konwersja_kolor_string() throws Exception {
        assertEquals(Pole.Konwersja_kolor_string(FIGURE_COLOR.bialy),"bialy");
        assertEquals(Pole.Konwersja_kolor_string(FIGURE_COLOR.czarny),"czarny");

        assertEquals(Pole.Konwersja_kolor_string(FIGURE_COLOR.brak),"brak");

    }

    @Test
    public void konwersja_do_koloru() throws Exception {
        assertEquals(Pole.Konwersja_do_koloru("bialy"), FIGURE_COLOR.bialy);
        assertEquals(Pole.Konwersja_do_koloru("brak"), FIGURE_COLOR.brak);

    }

}