import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int borderwidth=600;
        int borderheight=600;

        JFrame frame=new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(borderwidth, borderwidth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Snakegame snakegame =new Snakegame(borderwidth, borderheight);
         frame.add(snakegame);
         frame.pack();
         snakegame.requestFocus();
    }
}
