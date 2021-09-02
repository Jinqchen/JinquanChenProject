package GamePlay;


import java.io.*;

import javax.sound.sampled.*;

//this class uses for play background music
public class Music {
    private Clip clip;

    /*public Music(String music){
        AudioInputStream audio;
        try{
            URL url=Music.class.getResource(music);
            audio = AudioSystem.getAudioInputStream(url);
            clip= AudioSystem.getClip();
            clip.open(audio);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }



    }
    public void repeat() {
        clip.loop(-1);
    }



    public static void background(){ new Music("/musicfile/background.wav").repeat();}*/
    public Music(String music) {
        /*InputStream path;
        try{
            path=new FileInputStream(new File(music));
            AudioSystem audios=new AudioStream(path);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error");
        }*/

        /*try{
           // InputStream path;
            File path=new File(music).getAbsoluteFile();
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;
            stream = AudioSystem.getAudioInputStream(path);
            format= stream.getFormat();
            info=new DataLine.Info(Clip.class,format);
            clip=(Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }*/


        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(music).getAbsoluteFile()));
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }


    public static void background() {
        new Music("musicfile/background.wav");
    }

}
