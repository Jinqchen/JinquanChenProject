package GamePlay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    public static final int ROWS = 8;
    public static final int COLS = 18;
    //public static final int deletetotal=72;//you need to delete 72 couples of image to win a game
    //public static boolean restartgame=true;
    private static int currentdelete = 0;//your current delete
    private int time = 0;
    //image
    //public BufferedImage temimage=null;
    public List temImages = new ArrayList();
    List cards = new ArrayList();
    List indexs = new ArrayList();
    List cardIndexs = new ArrayList();

    //card
    private Card curCard = null;//current chosen card


    //imagevalue
    public static List<BufferedImage> cardList = new ArrayList<BufferedImage>();
    public static List<BufferedImage> chooseCard = new ArrayList<BufferedImage>();
    public static BufferedImage readyImage = null;
    //public static BufferedImage WinImage = null;

    //paint
    private int gameHeight = 0;
    private int gameWidth = 0;


    GamePanel gamePanel = this;
    private JFrame mainFrame = null;  //initate frame
    JMenuBar menu = null;// initate menu
    private Thread mainThread = null;
    //backgroud music
    private int image = 12;


    public GamePanel(JFrame frame) {
        setBackground(Color.white);
        this.setLayout(null);
        mainFrame = frame;
        initMenu();
        initcard();
        initIndexs();
        drawCard();
        //


        createMouseListener();
        mainFrame.setVisible(true);

        mainThread = new Thread(new RefreshThread());
        mainThread.start();


        Music.background();
        ready();


    }

    private void initIndexs() {

        for (int i = 1; i <= image; i++) {
            indexs.add(i);
        }
        //System.out.println("x");
        Collections.shuffle(indexs);
        /*for(int i=0;i<indexs.size();i++){
            System.out.print(indexs.get(i));
        }*/
        //System.out.print(indexs.size());
        System.out.println(cardIndexs.size());
        while (cardIndexs.size() < 144) {
            cardIndexs.addAll(indexs);
        }
        System.out.println(cardIndexs.size());
        Collections.shuffle(cardIndexs);
        /*for(int i=0;i<cardIndexs.size();i++){
            System.out.println(cardIndexs.get(i));}*/


    }

    private void drawCard() {
        Card card;
        int x = 0;
        int y = 0;
        int index = 0;
        int temp = 0;
        for (int i = 0; i < ROWS; i++) {
            y = 35 + 49 * i;
            for (int j = 0; j < COLS; j++) {
                x = 40 + 39 * j;
                temp = Integer.valueOf(String.valueOf(cardIndexs.get(index)));
                //System.out.println(temp);
                card = new Card(x, y, temp - 1, i, j, this);
                cards.add(card);
                index++;
            }
        }

    }

    public static void initcard() {
        String initpath = "/gameimage/";
        String path = "";
        for (int i = 1; i <= 12; i++) {
            try {
                path = initpath + i + ".gif";
                cardList.add(ImageIO.read(GamePanel.class.getResource(path)));
                path = initpath + i + "_over.gif";
                chooseCard.add(ImageIO.read(GamePanel.class.getResource(path)));
                //System.out.println(cardList.get(i-1).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void initMenu() {
        menu = new JMenuBar();
        JMenu jm1 = new JMenu("Help");
        JMenu jm2 = new JMenu("Game");
        JMenuItem jm11 = new JMenuItem("Rule");
        JMenuItem jm12 = new JMenuItem("mute music");
        jm1.add(jm11);
        jm1.add(jm12);
        JMenuItem jm21 = new JMenuItem("Restart");
        JMenuItem jm22 = new JMenuItem("Exit");
        jm2.add(jm21);
        jm2.add(jm22);
        menu.add(jm1);
        menu.add(jm2);
        mainFrame.setJMenuBar(menu);
        jm11.addActionListener(this);
        jm11.setActionCommand("Rule");
        jm12.addActionListener(this);
        jm12.setActionCommand("Mute");
        jm21.addActionListener(this);
        jm21.setActionCommand("Restart");
        jm22.addActionListener(this);
        jm22.setActionCommand("Exit");


    }


    private void ready() {
        readyimage();

        temImages.add(readyImage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                temImages.remove(readyImage);

            }

        }).start();

    }


    //does not work
    private void restart() {

        time = 0;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Object[] options = {"Yes", "No"};
        if ("Exit".equals(command)) {

            int response = JOptionPane.showOptionDialog(this, "Are you sure?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                System.exit(0);

            }
        } else if ("Restart".equals(command)) {
            int response = JOptionPane.showOptionDialog(this, "Are you sure?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                restart();

            }
        } else if ("Rule".equals(command)) {
            JOptionPane.showMessageDialog(null, "Lianliankan tests your eyesight. \n In a " +
                            "limited time, as long as you find all the same patterns \nthat can be connected in pairs, " +
                            "they will automatically disappear every time you find a pair. \nJust get all the patterns. " +
                            "You can win when you finish the elimination. The so-called being \nable to connect means that " +
                            "no matter whether it is horizontal or vertical, the line from one\n pattern to another cannot" +
                            " exceed two bends, and the line cannot pass through the pattern \nthat has not been eliminated.", "Rule",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }
   /* public static void initimage(){

    }

    */

    public static void readyimage() {
        try {
            readyImage = ImageIO.read(GamePanel.class.getResource("/gameimage/ready2.gif"));
            //System.out.println(readyImage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gameHeight = this.getHeight();
        gameWidth = this.getWidth();


        //canvas
        BasicStroke bs_2 = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        Graphics2D g_2d = (Graphics2D) g;
        g_2d.setColor(new Color(0, 191, 255));
        g_2d.setStroke(bs_2);
        g_2d.drawRect(38, 32, 705, 396);

        Card card;
        for (int i = 0; i < cards.size(); i++) {
            card = (Card) cards.get(i);
            card.draw(g);
        }


        //image
        BufferedImage tempImage = null;
        int x = 0;
        int y = 0;
        for (int i = 0; i < temImages.size(); i++) {
            tempImage = (BufferedImage) temImages.get(i);
            x = gameWidth / 2 - tempImage.getWidth() / 2;
            y = gameHeight / 2 - tempImage.getHeight() / 2;
            g.drawImage(tempImage, x, y, null);
        }

        //score and time
        //delete score
        g.setColor(Color.black);

        //g.drawString("Score："+currentdelete+"",400, 24);

        g.drawString("Time：" + time + "", 810 / 2, 24);


    }


    private void createMouseListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {


                int x = e.getX();
                int y = e.getY();
                Card card;
                for (int i = 0; i < cards.size(); i++) {
                    card = (Card) cards.get(i);
                    if (card.isPoint(x, y)) {
                        if (card.getType() != 0) {
                            continue;
                        }


                        card.setType(1);
                        if (curCard == null) {
                            curCard = card;
                        } else {
                            checkPath(card);
                        }
                        break;
                    }
                }
            }
        };
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    //check if the path is legal to connect
    protected void checkPath(Card card) {

        if (curCard.getI() == card.getI() && curCard.getJ() == card.getJ()) {
            return;
        }
        if (card.getIndex() != curCard.getIndex()) {
            curCard.setType(0);
            curCard = card;
            return;
        }
        boolean legal = turnZeroX(card, curCard)
                || turnZeroY(card, curCard)
                || border(card, curCard)
                || turnOnce(card, curCard)
                /*|| turnTwo(card, curCard)*/;
        ;


        if (legal) {

            card.setType(2);
            curCard.setType(2);
            curCard = null;


        } else {
            curCard.setType(0);
            curCard = card;
        }
    }


    private boolean turnZeroX(Card card, Card curCard) {
        if (card.getI() == curCard.getI()) {
            Card itemCard;
            for (int i = 0; i < cards.size(); i++) {
                itemCard = (Card) cards.get(i);
                if (itemCard.getI() == card.getI()) {
                    if ((itemCard.getJ() > card.getJ() && itemCard.getJ() < curCard.getJ())
                            || (itemCard.getJ() > curCard.getJ() && itemCard.getJ() < card.getJ())) {
                        if (itemCard.getType() == 0) {
                            return false;
                        }
                    }
                }
            }


            return true;
        }
        return false;
    }


    private boolean turnZeroY(Card card, Card curCard) {
        if (card.getJ() == curCard.getJ()) {
            Card itemCard;
            for (int i = 0; i < cards.size(); i++) {
                itemCard = (Card) cards.get(i);
                if (itemCard.getJ() == card.getJ()) {
                    if ((itemCard.getI() > card.getI() && itemCard.getI() < curCard.getI())
                            || (itemCard.getI() > curCard.getI() && itemCard.getI() < card.getI()) && itemCard.getType() == 0) {


                        return false;

                    }
                }
            }

            return true;
        }
        return false;
    }

    private boolean border(Card card, Card curCard) {
        if (card.getI() == curCard.getI()) {
            if (card.getI() == 0 || card.getI() == ROWS - 1) {
                return true;
            }
        }
        if (card.getJ() == curCard.getJ()) {
            if (card.getJ() == 0 || card.getJ() == COLS - 1) {
                return true;
            }
        }
        return false;
    }




    private Card getTempCard(int i, int j) {
        Card itemCard;
        for (int k = 0; k < cards.size(); k++) {
            itemCard = (Card) cards.get(k);
            if (itemCard.getI() == i && itemCard.getJ() == j) {
                return itemCard;
            }
        }
        return null;
    }


    private boolean turnOnce(Card card, Card curCard) {
        boolean method1 = true, method2 = true;
        Card cardx = getTempCard(card.getI(), curCard.getJ());
        if (cardx == null || cardx.getType() != 2) {
            return false;
        }


        Card itemCard;
        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card) cards.get(i);
            if (itemCard.getI() == cardx.getI() &&
                    (itemCard.getJ() > card.getJ() && itemCard.getJ() < cardx.getJ())
                    || (itemCard.getJ() > cardx.getJ()
                    && itemCard.getJ() < card.getJ()) && itemCard.getType() == 0) {


                method1 = false;


            }
        }

        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card) cards.get(i);
            if (itemCard.getJ() == cardx.getJ()
                    && (itemCard.getI() > cardx.getI() && itemCard.getI() < curCard.getI())
                    || (itemCard.getI() > curCard.getI() && itemCard.getI() < cardx.getI())
                    && itemCard.getType() == 0) {


                method1 = false;


            }
        }


        Card itemCard1;
        for (int i = 0; i < cards.size(); i++) {
            itemCard1 = (Card) cards.get(i);
            if (itemCard1.getI() == cardx.getI()
                    && (itemCard1.getJ() > curCard.getJ() && itemCard1.getJ() < cardx.getJ())
                    || (itemCard1.getJ() > cardx.getJ() && itemCard1.getJ() < curCard.getJ())
                    && itemCard1.getType() == 0) {


                method2 = false;


            }
        }

        for (int i = 0; i < cards.size(); i++) {
            itemCard1 = (Card) cards.get(i);
            if (itemCard1.getJ() == cardx.getJ()
                    && (itemCard1.getI() > cardx.getI() && itemCard1.getI() < card.getI())
                    || (itemCard1.getI() > card.getI() && itemCard1.getI() < cardx.getI())
                    && itemCard1.getType() == 0) {


                method2 = false;


            }
        }


        return method1 || method2;
    }

   /* private boolean method1(Card card,Card curCard) {
        Card tempCard =  getTempCard(card.getI(),curCard.getJ());
        if(tempCard==null || tempCard.getType()!=2){
            return false;
        }


        Card itemCard;
        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card)cards.get(i);
            if(itemCard.getI()==tempCard.getI()){
                if( (itemCard.getJ()>card.getJ() && itemCard.getJ()<tempCard.getJ())
                        || (itemCard.getJ()>tempCard.getJ() && itemCard.getJ()<card.getJ()) ){
                    if(itemCard.getType()==0){
                        return false;
                    }
                }
            }
        }
        ///////////////////////////////////////////////////
        int x1 = curCard.getX()+curCard.getWidth()/2;
        int y1 = curCard.getY()+curCard.getHeight()/2;
        int x2 = tempCard.getX()+tempCard.getWidth()/2;
        int y2 = tempCard.getY()+tempCard.getHeight()/2;

        x1 = tempCard.getX()+tempCard.getWidth()/2;
        y1 = tempCard.getY()+tempCard.getHeight()/2;
        x2 = card.getX()+card.getWidth()/2;
        y2 = card.getY()+card.getHeight()/2;

        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card)cards.get(i);
            if(itemCard.getJ()==tempCard.getJ()){
                if( (itemCard.getI()>tempCard.getI() && itemCard.getI()<curCard.getI())
                        || (itemCard.getI()>curCard.getI() && itemCard.getI()<tempCard.getI()) ){
                    if(itemCard.getType()==0){
                        return false;
                    }
                }
            }
        }


        return true;
    }


    private boolean method2(Card card,Card curCard) {
        Card tempCard =  getTempCard(curCard.getI(),card.getJ());
        if(tempCard==null || tempCard.getType()!=2){
            return false;
        }
        Card itemCard;
        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card)cards.get(i);
            if(itemCard.getI()==tempCard.getI()){//in same row
                if( (itemCard.getJ()>curCard.getJ() && itemCard.getJ()<tempCard.getJ())
                        || (itemCard.getJ()>tempCard.getJ() && itemCard.getJ()<curCard.getJ()) ){
                    if(itemCard.getType()==0){
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < cards.size(); i++) {
            itemCard = (Card)cards.get(i);
            if(itemCard.getJ()==tempCard.getJ()){//in same column
                if( (itemCard.getI()>tempCard.getI() && itemCard.getI()<card.getI())
                        || (itemCard.getI()>card.getI() && itemCard.getI()<tempCard.getI()) ){
                    if(itemCard.getType()==0){
                        return false;
                    }
                }
            }
        }

        /*int x1 = curCard.getX()+curCard.getWidth()/2;
        int y1 = curCard.getY()+curCard.getHeight()/2;
        int x2 = tempCard.getX()+tempCard.getWidth()/2;
        int y2 = tempCard.getY()+tempCard.getHeight()/2;

        x1 = tempCard.getX()+tempCard.getWidth()/2;
        y1 = tempCard.getY()+tempCard.getHeight()/2;
        x2 = card.getX()+card.getWidth()/2;
        y2 = card.getY()+card.getHeight()/2;

        return true;
    }*/

    int n = 0;

    private class RefreshThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();

                try {
                    //Thread.sleep(50);
                    //Thread.sleep(100);
                    Thread.sleep(200);
                    n++;
                    if (n == 5) {
                        time++;
                        n = 0;
                    }

                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    break;
                }
            }
        }
    }


}

