// package test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import labb5.*;

// public class MusicPlayerTest {
// private MainFrame myFrame;
// private MusicPlayer musicPlayer;

// @Test
// @DisplayName("Songs length in MusicSample and songList array length should be
// same")
// void testMain() {
// MainFrame myFrame = new MainFrame();
// MusicPlayer musicPlayer = new MusicPlayer();

// String[] arrSongs = musicPlayer.getArrSongs("src/musicSample");
// myFrame.initialize(arrSongs, myFrame);

// int arrSongsLength = arrSongs.length;
// int songListLength = myFrame.getSonglists().length;
// assertEquals(arrSongsLength, songListLength);
// }

// @BeforeEach
// public void setUp() {
// System.out.println("before");
// myFrame = new MainFrame();
// musicPlayer = new MusicPlayer();

// }
// }
