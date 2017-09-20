package pl.umk.mat.marcinkub;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MyButton extends JButton {
    public Pole pole;
    public MyButton(ImageIcon ii, Pole p1)
    {
        this.setIcon(ii);
        this.pole = p1;

    }
    public void add_border()
    {
        this.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));

    }
    public void delete_border()
    {
        this.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));

    }
}
