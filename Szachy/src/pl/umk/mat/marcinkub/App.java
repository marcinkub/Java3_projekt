package pl.umk.mat.marcinkub;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class App {
    private JFrame frame = new JFrame("Grid demo");
    private GridLayout GL = new GridLayout(8,8,3,3);
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextArea text_LOG;
    private JLabel ruchinfo;
    private JButton button1;
    private MyButton tablica[] = new MyButton[64];
    private Gra gra = new Gra();


    public App() {

        panel2.setLayout(GL);
        df(0,"wieza","czarny");
        df(1,"kon","czarny");
        df(2,"goniec","czarny");
        df(3,"hetman","czarny");
        df(4,"krol","czarny");
        df(5,"goniec","czarny");
        df(6,"kon","czarny");
        df(7,"wieza","czarny");
        for(int i=8;i<16;i++)
            df(i,"pion","czarny");
        for(int i=16;i<48;i++)
            df(i,"pole","");
        for(int i=48;i<56;i++)
            df(i,"pion","bialy");
        df(56,"wieza","bialy");
        df(57,"kon","bialy");
        df(58,"goniec","bialy");
        df(59,"hetman","bialy");
        df(60,"krol","bialy");
        df(61,"goniec","bialy");
        df(62,"kon","bialy");
        df(63,"wieza","bialy");
        frame.add(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        gra.Stan = Stan_planszy.wybor_bialej_fig;

        ruchinfo.setText("TURA:: BIALE");


        //  JOptionPane.showMessageDialog(frame, String.valueOf(gra.plansza[63].nrWiersza) + " " + String.valueOf(gra.plansza[63].nrKolumny));

       // button1.setIcon(gra.plansza[63].btn.getIcon());
    }

    public void df(int nrpola, String pionek, String kolor_pionka)
    {

     //   String s = "image/" + pionek + "_" + kolor_pionka + "_" + kolor_planszy + ".png";

        int tab[] = Pole.Konwesja_do_wspolrzednych(nrpola);

        MyButton tmp = new MyButton(gra.iconGenerator(nrpola,pionek,kolor_pionka),gra.plansza[nrpola]);
        Pole tpole = new Pole(nrpola,Pole.Konwersja_do_figury(pionek), Pole.Konwersja_do_koloru(kolor_pionka), tmp);
        tpole.nrWiersza = tab[0];
        tpole.nrKolumny = tab[1];





       // tmp.setIcon();
        gra.plansza[nrpola] = tpole;

        tmp.setMargin(new Insets(0,0,0,0));
        tablica[nrpola] = tmp;
        tmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gra.move(nrpola) == false)
                    JOptionPane.showMessageDialog(frame, gra.err_code);
                if(gra.Stan == Stan_planszy.wybor_bialej_fig || gra.Stan == Stan_planszy.ruch_bialej_fig)
                    ruchinfo.setText("TURA:: BIALE");
                if(gra.Stan == Stan_planszy.wybor_czarnej_fig || gra.Stan == Stan_planszy.ruch_czarnej_fig)
                    ruchinfo.setText("TURA:: CZARNE");


              //  int tab[] = Pole.Konwesja_do_wspolrzednych(nrpola);

               // JOptionPane.showMessageDialog(frame, String.valueOf(tab[0]) + " " + String.valueOf(tab[1]));
                gra.move(nrpola);
                text_LOG.setText(gra.log);
            }
        });
        tmp.setPreferredSize(new Dimension(60,60));
        panel2.add(tmp);


    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });

    }
}
