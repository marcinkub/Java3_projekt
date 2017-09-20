package pl.umk.mat.marcinkub;

public class Pole {
    public int nrKolumny;
    public int nrWiersza;
    public Figura figura;
    public FIGURE_COLOR kolor_figury;
    public MyButton btn;


    public Pole(int index, Figura f, FIGURE_COLOR fc, MyButton kbtn)
    {
        figura = f;
        kolor_figury = fc;
        btn = kbtn;


    }
    public static boolean Equals(Pole p1, Pole p2)
    {
        if(p1.nrWiersza == p2.nrWiersza && p1.nrKolumny == p2.nrKolumny)
            return true;
        else
            return false;
    }

    public static int Konwersja_do_index(int nWiersza, int nKolumny)
    {
        System.out.println("KONWERTER ::" + String.valueOf(nWiersza) + " "+ String.valueOf(nKolumny));
        if((nWiersza-1)*8+(nKolumny-1)<0 ||  (nWiersza-1)*8+(nKolumny-1)>63)
            System.out.println("KONWERTER SIE POJEBAL ::" + String.valueOf(nWiersza) + " "+ String.valueOf(nKolumny));

        return (nWiersza-1)*8+(nKolumny-1);
    }
    public static Figura Konwersja_do_figury(String nazwa)
    {
        switch(nazwa)
        {
            case "pion":
                return Figura.pion;
            case "wieza":
                return Figura.wieza;
            case "kon":
                return Figura.kon;
            case "goniec":
                return Figura.goniec;
            case "hetman":
                return Figura.hetman;
            case "krol":
                return Figura.krol;
            case "pole":
                return Figura.pole;
            default:
                return Figura.pole;

        }

    }
    public static String Konwersja_figura_string(Figura f)
    {
       if(f==Figura.pion) return "pion";
       else if(f==Figura.wieza) return "wieza";
       else if(f==Figura.kon) return "kon";
       else if(f==Figura.goniec) return "goniec";
       else if(f==Figura.hetman) return "hetman";
       else if(f==Figura.krol) return "krol";
       else return "pole";
    }
    public static  String Konwersja_kolor_string(FIGURE_COLOR fc)
    {
        if(fc == FIGURE_COLOR.bialy) return "bialy";
        else if(fc == FIGURE_COLOR.czarny) return "czarny";
        else return "brak";
    }
    public static FIGURE_COLOR Konwersja_do_koloru(String kolor)
    {
        switch(kolor){
            case "czarny":
                return FIGURE_COLOR.czarny;
            case "bialy":
                return FIGURE_COLOR.bialy;
            default:
                    return FIGURE_COLOR.brak;
        }
    }
    public static int[] Konwesja_do_wspolrzednych(int index)
    {
        int tab[] = new int[2];
        tab[1] = index%8+1;
        int wiersz = 0;
        int licznik = 0;
        for(int i=0; i<=index;i++)
        {
            if(licznik == 8)
            {
                wiersz++;
                licznik=0;
            }
            licznik++;
        }
        tab[0] = wiersz+1;
        return tab;
    }

}
