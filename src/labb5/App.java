package labb5;

public class App {
    public static void main(String[] args) throws Exception {
        MainFrame myFrame = new MainFrame();
        MusicPlayer musicPlayer = new MusicPlayer();

        String[] arrSongs = musicPlayer.getArrSongs("src/musicSample");

        myFrame.initialize(arrSongs, myFrame);

    }
}
