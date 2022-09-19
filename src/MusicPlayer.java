import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MusicPlayer {
    static MusicPlayer player = new MusicPlayer();
    static Clip clip;

    static boolean isPlaying = false;

    public MusicPlayer() {

    }

    public static MusicPlayer getInstance() {
        return player;
    }

    public void loadMusic(String song) {

        try {
            File musicPath = new File("src/musicSample" + "/" + song + ".wav");
            if (musicPath.exists()) {
                if (MusicPlayer.clip != null || MusicPlayer.clip == null) {
                    if (isPlaying) {
                        clip.stop();
                    }
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    playAudio(audioInput);

                }
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playAudio(AudioInputStream audioInput) {
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            isPlaying = true;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public long pauseSong() {
        long pausedTime = clip.getMicrosecondPosition();
        clip.stop();
        return pausedTime;
    }

    public void startSongFromPause(long pausedTime) {
        clip.setMicrosecondPosition(pausedTime);
        clip.start();
    }

    public void repeatSong() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public String[] getArrSongs(String filePath) {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        String[] listOfMusic = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            String fileName = listOfFiles[i].getName();
            listOfMusic[i] = fileName.replaceFirst("[.][^.]+$", "");
        }
        return listOfMusic;
    }

    public void sortSongs(String[] arrOfSongs, MainFrame myFrame, boolean reversed) {

        Collections.reverse(Arrays.asList(arrOfSongs));
        // if (reversed == false) {

        // // Arrays.sort(MainFrame.songList);
        // } else {
        // Arrays.sort(arrOfSongs, Collections.reverseOrder());
        // }

        myFrame.initialize(arrOfSongs, myFrame);

    }

    public void searchSong(String[] arrOfSongs, String input, MainFrame myFrame) {
        boolean found = false;
        String searchedSong[];
        try {
            for (int i = 0; i < arrOfSongs.length; i++) {
                if (arrOfSongs[i].equals(input)) {
                    System.out.println(arrOfSongs[i]);
                    searchedSong = new String[1];
                    searchedSong[0] = arrOfSongs[i];
                    myFrame.initialize(searchedSong, myFrame);
                    found = true;

                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "No result! Try again");
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

}
