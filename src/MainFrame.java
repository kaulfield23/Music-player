import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    final private Font mainFont = new Font("Roboto", Font.BOLD, 18);
    JTextField searchField;
    static MusicPlayer musicPlayer = new MusicPlayer();
    static long pausedTime;

    public void initialize(String[] arrOfSongs) {
        // title
        JLabel[] songList = new JLabel[arrOfSongs.length];
        JLabel playerTitle = new JLabel("Music player");
        playerTitle.setFont(mainFont);
        // search field
        JPanel searchForm = new JPanel();
        JButton searchBtn = new JButton("Search");
        searchField = new JTextField();
        searchField.setFont(mainFont);

        searchBtn.setSize(50, 50);
        searchBtn.setBounds(10, 10, 5, 5);
        searchForm.setLayout(new GridLayout(1, 2, 5, 5));
        searchForm.add(searchField);
        searchForm.add(searchBtn);

        // Music lists
        JPanel listForm = new JPanel();
        listForm.setBackground(new Color(213, 246, 227));

        JScrollPane scrPane = new JScrollPane(listForm);
        listForm.setLayout(new GridLayout(songList.length, 1, 5, 5));

        for (int i = 0; i < songList.length; i++) {
            songList[i] = new JLabel(arrOfSongs[i]);
            EmptyBorder border = new EmptyBorder(0, 10, 1, 0);
            songList[i].setBorder(border);
            songList[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            songList[i] = addPlaySongFunction(songList[i]);
        }
        for (JLabel item : songList) {
            listForm.add(item);
        }
        // North - title and search filed with search button
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 1, 5, 5));
        formPanel.setOpaque(false);
        formPanel.add(playerTitle);
        formPanel.add(searchForm);

        // South - three buttons with function
        JButton btnPause = new JButton("Pause");
        btnPause.setFont(mainFont);
        btnPause.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {

                String pauseBtnText = btnPause.getText() == "Pause" ? "Play" : "Pause";
                if (pauseBtnText.equals("Play")) {
                    pausedTime = musicPlayer.pauseSong();
                } else {
                    musicPlayer.startSongFromPause(pausedTime);
                }
                btnPause.setText(pauseBtnText);
            }
        });

        JButton btnRepeat = new JButton("Repeat");
        btnPause.setFont(mainFont);

        btnRepeat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                musicPlayer.repeatSong();
            }
        });
        JButton btnSort = new JButton("Sort A-Z");
        btnPause.setFont(mainFont);

        btnSort.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                playerTitle.setText("Sort A-Z");
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnPause);
        buttonsPanel.add(btnRepeat);
        buttonsPanel.add(btnSort);

        // main panel - north, center, south
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(246, 213, 232));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrPane, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Music player");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static JLabel addPlaySongFunction(final JLabel songTitle) {

        songTitle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                pausedTime = 0;
                String song = songTitle.getText();

                musicPlayer.loadMusic(song);
                System.out.println(song + " is playing");
            }
        });
        return songTitle;
    }

}