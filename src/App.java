
public class App {
    public static void main(String[] args) throws Exception {
        MainFrame myFrame = new MainFrame();
        MusicPlayer musicPlayer = new MusicPlayer();

        String[] arrSongs = musicPlayer.getArrSongs("C:/temp");

        myFrame.initialize(arrSongs, myFrame);

    }
}
