package GamePlay;
//import GamePlay.GamePanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

//main class
public class Play {
    public static void main(String[] args) {
        /**
         *
         *
         */
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel(frame);
        frame.add(panel);
        frame.setVisible(true);


    }

    public static class GameFrame extends JFrame {
        public GameFrame() {
            setTitle("Lianliankan");
            setSize(810, 520);
            setLayout(new BorderLayout());
        }

    }

}
