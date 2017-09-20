package pl.umk.mat.marcinkub;

import javax.swing.*;
import java.io.Console;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Vector;

public class Gra {
    public boolean move(int index)
    {

        if(Stan == Stan_planszy.wybor_bialej_fig)
        {
            //Gracz bialy wybiera figure do ruchu


            if(plansza[index].kolor_figury != FIGURE_COLOR.bialy)
            {
                this.err_code = "Na tym polu nie ma figury, która moglbys ruszyc...";
                return false;
            }
            plansza[index].btn.add_border();
            wybrane_pole = plansza[index];
            VDPola = dostepne_Pola(index);
            for(Pole P :VDPola)
            {
                System.out.println("Pole:: " + String.valueOf(P.nrWiersza) + " " + String.valueOf(P.nrKolumny));
            }
            Stan = Stan_planszy.ruch_bialej_fig;
            return true;

        }
        else if(Stan == Stan_planszy.wybor_czarnej_fig)
        {
            //Gracz czarny wybiera figure do ruchu


            if(plansza[index].kolor_figury != FIGURE_COLOR.czarny)
            {
                this.err_code = "Na tym polu nie ma figury, która moglbys ruszyc...";
                return false;
            }
            plansza[index].btn.add_border();
            wybrane_pole = plansza[index];
            VDPola = dostepne_Pola(index);
            Stan = Stan_planszy.ruch_czarnej_fig;
            return true;
        }
        else if(Stan == Stan_planszy.ruch_bialej_fig || Stan == Stan_planszy.ruch_czarnej_fig)
        {
           for(Pole p : VDPola)
           {
               //czy mozliwy jest ruch na to pole
               if(Pole.Equals(plansza[index], p)){
                   //czy na tym polu nie ma figury bialej [RUCH WYKONUJa BIALE FIGURY]
                   if(Stan == Stan_planszy.ruch_bialej_fig && plansza[index].kolor_figury != FIGURE_COLOR.bialy) {

                       plansza[index].btn.setIcon(iconGenerator(index,Pole.Konwersja_figura_string(wybrane_pole.figura), Pole.Konwersja_kolor_string(wybrane_pole.kolor_figury)));
                       plansza[index].figura = wybrane_pole.figura;
                       plansza[index].kolor_figury = wybrane_pole.kolor_figury;


                       wybrane_pole.btn.setIcon(iconGenerator(Pole.Konwersja_do_index(wybrane_pole.nrWiersza,wybrane_pole.nrKolumny), "pole", ""));
                       wybrane_pole.figura = Figura.pole;
                       wybrane_pole.kolor_figury = FIGURE_COLOR.brak;
                       wybrane_pole.btn.delete_border();
                       log+= "BIALE :: "+  (char)(wybrane_pole.nrKolumny+64) + wybrane_pole.nrWiersza  + " -> "+(char)(plansza[index].nrKolumny+64)+ plansza[index].nrWiersza +"\n";
                       // teraz ruch czarnych figur
                       Stan = Stan_planszy.wybor_czarnej_fig;
                       return true;
                   }
                   else if(Stan == Stan_planszy.ruch_czarnej_fig && plansza[index].kolor_figury != FIGURE_COLOR.czarny)
                   {
                       plansza[index].btn.setIcon(iconGenerator(index,Pole.Konwersja_figura_string(wybrane_pole.figura), Pole.Konwersja_kolor_string(wybrane_pole.kolor_figury)));
                       plansza[index].figura = wybrane_pole.figura;
                       plansza[index].kolor_figury = wybrane_pole.kolor_figury;


                       wybrane_pole.btn.setIcon(iconGenerator(Pole.Konwersja_do_index(wybrane_pole.nrWiersza,wybrane_pole.nrKolumny), "pole", ""));
                       wybrane_pole.figura = Figura.pole;
                       wybrane_pole.kolor_figury = FIGURE_COLOR.brak;
                       wybrane_pole.btn.delete_border();

                       log+= "CZARNE :: "+  (char)(wybrane_pole.nrKolumny+64) + wybrane_pole.nrWiersza  + " -> "+(char)(plansza[index].nrKolumny+64)+ plansza[index].nrWiersza +"\n";

                       // teraz ruch bialych figur
                       Stan = Stan_planszy.wybor_bialej_fig;
                       return true;
                   }
                   // NA TYM POLU JEST FIGURA WLASCICIELA
                   else
                   {
                       err_code = "Na tym polu jest Twoja Figura";
                       return false;
                   }
                }
           }
            err_code = "Na to pole nie jest mozliwy ruch tego typu figury";
            return false;
        }

        return true;
    }

    public Vector<Pole> dostepne_Pola(int index)
    {
        Vector<Pole> v = new Vector<Pole>();

        //Wieza moze poruszac sie na obszarze calej kolumny i wiersza...

        if(plansza[index].figura == Figura.wieza)
        {
            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;

            System.out.println("WIEZA ::" + String.valueOf(nrW) + " "+ String.valueOf(nrK));
            for(int i=1;i<9;i++)
            {
                v.add(plansza[Pole.Konwersja_do_index(nrW,i)]);
                v.add(plansza[Pole.Konwersja_do_index(i,nrK)]);
            }
        }
        // Skoczek wykonuje ruch w ksztalcie L w 4 kierunkach
        else if(plansza[index].figura == Figura.kon)
        {
            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;
            System.out.println("KON ::" + String.valueOf(nrW) + " "+ String.valueOf(nrK));


            if(czy_nalezy(nrW+2,nrK+1))
                v.add(plansza[Pole.Konwersja_do_index(nrW+2,nrK+1)]);
            if(czy_nalezy(nrW+2,nrK-1))
                v.add(plansza[Pole.Konwersja_do_index(nrW+2,nrK-1)]);

            if(czy_nalezy(nrW-2,nrK+1))
                v.add(plansza[Pole.Konwersja_do_index(nrW-2,nrK+1)]);
            if(czy_nalezy(nrW-2,nrK-1))
                v.add(plansza[Pole.Konwersja_do_index(nrW-2,nrK-1)]);

            if(czy_nalezy(nrW+1,nrK+2))
                v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK+2)]);
            if(czy_nalezy(nrW-1,nrK+2))
                v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK+2)]);

            if(czy_nalezy(nrW+1,nrK-2))
                v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK-2)]);
            if(czy_nalezy(nrW-1,nrK-2))
                v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK-2)]);
        }
        else if(plansza[index].figura == Figura.goniec)
        {

            // Goniec po przekatnych w 4kierunkach

            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;

            System.out.println("GONIEC ::" + String.valueOf(nrW) + " "+ String.valueOf(nrK));


            for(int i=nrW-1;i>0;i--)
                for(int j=nrK-1;j>0;j--)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

            for(int i=nrW+1;i<9;i++)
                for(int j=nrK-1;j>0;j--)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

            for(int i=nrW+1;i<9;i++)
                for(int j=nrK+1;j<9;j++)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);


            for(int i=nrW-1;i>0;i--)
                for(int j=nrK+1;j<9;j++)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

        }
        else if(plansza[index].figura == Figura.hetman)
        {
            // Hetman wykonuje ruch wiezy oraz gonca

            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;

            System.out.println("HETMAN ::" + String.valueOf(nrW) + " "+ String.valueOf(nrK));

            for(int i=1;i<9;i++)
            {
                v.add(plansza[Pole.Konwersja_do_index(nrW,i)]);
                v.add(plansza[Pole.Konwersja_do_index(i,nrK)]);
            }

            nrW = plansza[index].nrWiersza;
            nrK = plansza[index].nrKolumny;

            for(int i=nrW-1;i>0;i--)
                for(int j=nrK-1;j>0;j--)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

            for(int i=nrW+1;i<9;i++)
                for(int j=nrK-1;j>0;j--)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

            for(int i=nrW+1;i<9;i++)
                for(int j=nrK+1;j<9;j++)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);


            for(int i=nrW-1;i>0;i--)
                for(int j=nrK+1;j<9;j++)
                    if(czy_nalezy(i,j))
                        v.add(plansza[Pole.Konwersja_do_index(i,j)]);

        }
        else if(plansza[index].figura == Figura.krol)
        {
            //Krol moze wykonac ruch w kazdym kierunku o 1 pole
            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;

            if(czy_nalezy(nrW+1,nrK+1))
                v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK+1)]);

            if(czy_nalezy(nrW-1,nrK-1))
                v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK-1)]);

            if(czy_nalezy(nrW+1,nrK-1))
                v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK-1)]);

            if(czy_nalezy(nrW-1,nrK+1))
                v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK+1)]);

            if(czy_nalezy(nrW,nrK+1))
                v.add(plansza[Pole.Konwersja_do_index(nrW,nrK+1)]);
            if(czy_nalezy(nrW,nrK-1))
                v.add(plansza[Pole.Konwersja_do_index(nrW,nrK-1)]);

            if(czy_nalezy(nrW +1,nrK))
                v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK)]);
            if(czy_nalezy(nrW -1,nrK))
                v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK)]);
        }
        else if(plansza[index].figura == Figura.pion)
        {
            // pion porusza sie tylko do przodu
            int nrW = plansza[index].nrWiersza;
            int nrK = plansza[index].nrKolumny;

            if(Stan == Stan_planszy.wybor_bialej_fig)
            {
                if(czy_nalezy(nrW-1,nrK))
                    v.add(plansza[Pole.Konwersja_do_index(nrW-1,nrK)]);
                if(nrW==7)
                    if(czy_nalezy(nrW-2,nrK))
                        v.add(plansza[Pole.Konwersja_do_index(nrW-2,nrK)]);
                if(czy_nalezy(nrW-1,nrK+1))
                    if (plansza[Pole.Konwersja_do_index(nrW - 1, nrK +1)].figura != Figura.pole && plansza[Pole.Konwersja_do_index(nrW - 1, nrK + 1)].kolor_figury == FIGURE_COLOR.czarny)
                        v.add(plansza[Pole.Konwersja_do_index(nrW - 1, nrK + 1)]);
                if(czy_nalezy(nrW-1,nrK-1))
                    if (plansza[Pole.Konwersja_do_index(nrW - 1, nrK - 1)].figura != Figura.pole && plansza[Pole.Konwersja_do_index(nrW - 1, nrK - 1)].kolor_figury == FIGURE_COLOR.czarny)
                        v.add(plansza[Pole.Konwersja_do_index(nrW - 1, nrK - 1)]);
            }
            else if(Stan == Stan_planszy.wybor_czarnej_fig)
            {
                if(czy_nalezy(nrW+1,nrK))
                    v.add(plansza[Pole.Konwersja_do_index(nrW+1,nrK)]);
                if(nrW==2)
                    if(czy_nalezy(nrW+2,nrK))
                        v.add(plansza[Pole.Konwersja_do_index(nrW+2,nrK)]);
                if(czy_nalezy(nrW+1,nrK+1))
                    if (plansza[Pole.Konwersja_do_index(nrW +1, nrK +1)].figura != Figura.pole && plansza[Pole.Konwersja_do_index(nrW + 1, nrK + 1)].kolor_figury == FIGURE_COLOR.bialy)
                        v.add(plansza[Pole.Konwersja_do_index(nrW + 1, nrK + 1)]);
                if(czy_nalezy(nrW+1,nrK-1))
                    if (plansza[Pole.Konwersja_do_index(nrW + 1, nrK - 1)].figura != Figura.pole && plansza[Pole.Konwersja_do_index(nrW + 1, nrK - 1)].kolor_figury == FIGURE_COLOR.bialy)
                        v.add(plansza[Pole.Konwersja_do_index(nrW + 1, nrK - 1)]);

            }






        }

        return v;

    }

    public void sprawdz_warunek_szach_mat(FIGURE_COLOR fc)
    {


    }
    private boolean czy_nalezy(int nrWiersza, int nrKolumny)
    {
        if(nrWiersza>=1 && nrWiersza <=8 && nrKolumny>=1 && nrKolumny<=8)
            return true;
        else return false;
    }

    public ImageIcon iconGenerator(int nrpola, String pionek, String kolor_pionka)
    {
        String kolor_planszy;

        int tab[] = Pole.Konwesja_do_wspolrzednych(nrpola);

        if(tab[0]%2 == 0)
        {
            if(tab[1]%2 == 0)
                kolor_planszy = "bialy";
            else
                kolor_planszy = "czarny";
        }
        else
        {
            if(tab[1]%2 == 1)
                kolor_planszy = "bialy";
            else
                kolor_planszy = "czarny";
        }
        String s = "image/" + pionek + "_" + kolor_pionka + "_" + kolor_planszy + ".png";

        System.out.println(String.valueOf(nrpola) + " " + s);
        return new ImageIcon("image/" + pionek + "_" + kolor_pionka + "_" + kolor_planszy + ".png");
    }

    public Pole plansza[] = new Pole[64];
    private Pole bialy_krol;
    private Pole czarny_krol;
    private Pole wybrane_pole;
    public Stan_planszy Stan;
    private Vector<Pole> VDPola;
    public String log;

    public String err_code;
    public Gra()
    {
        log="";
    }
}
