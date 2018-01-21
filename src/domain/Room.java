package domain;

import Interfaces.*;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room extends UnicastRemoteObject implements IRoom, IRemotePublisherForListener {
    private final static Logger LOGGER = Logger.getLogger(Room.class.getName());
    private Timer timer;
    private Chat chat;
    private Player host;
    private ActivePlayer activePlayer;
    private ArrayList<IPlayer> players;
    private Drawing drawing;
    private RemotePublisher publisher;
    private ArrayList<String> wordList;
    private int time;

    public Room(Player host) throws RemoteException {
        try {
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            System.out.println("Publisher failed to instantiate.");
            System.out.println("Remote exception: " + e.getMessage());
            return;
        }
        publisher.registerProperty("player");
        publisher.registerProperty("timer");
        publisher.registerProperty("newRound");

        this.loadWordList();

        host.setIsHost(true);
        this.host = host;
        this.chat = new Chat();
        this.drawing = new Drawing();
        this.setActivePlayer(host);
        this.players = new ArrayList<>();
        this.addPlayer(host);
    }

    private void startTimer() {
        //running timer task as daemon thread
        this.time = 60;
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (time < 1) {
                    //end round
                    try {
                        endRound();
                    } catch (RemoteException e) {
                        LOGGER.log(Level.WARNING, e.toString(), e);
                    }
                } else {
                    time--;
                }
                publisher.inform("timer", null, time);

            }
        }, 0, 1000);

    }

    private String getRandomWord() {
        Random rnd = new Random();
        return wordList.get(rnd.nextInt(wordList.size() - 1));
    }

    public IChat getChat() {
        return chat;
    }

    public Player getHost() {
        return host;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(IPlayer player) {
        if (!players.contains(player)) {
            this.players.add(player);
        }
        publisher.inform("player", null, getPlayers());
    }

    public void removePlayer(IPlayer player) {
        if (players.contains(player)) {
            this.players.remove(player);
        }
        publisher.inform("player", null, getPlayers());
    }

    public IDrawing getDrawing() {
        return drawing;
    }

    public IActivePlayer getActivePlayer() {
        return activePlayer;

    }

    private void setActivePlayer(Player activePlayer) {
        try {
            activePlayer.setIsActive(true);
            this.activePlayer = new ActivePlayer(activePlayer, getRandomWord());
            publisher.inform("newRound", null, getPlayers());
            startTimer();
        } catch (RemoteException e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
        }
    }

    private IPlayer getNextActivePlayer() throws RemoteException {
        ((Player) getActivePlayer().getPlayer()).setIsActive(false);
        int index = players.indexOf(getActivePlayer().getPlayer());
        int nextIndex;
        if (index + 1 >= players.size()) {
            nextIndex = 0;
        } else {
            nextIndex = index + 1;
        }
        return players.get(nextIndex);
    }

    private void win(IPlayer player) throws RemoteException {
        //increase winning players score
        player.increaseScore(time);
        endRound();
    }

    private void endRound() throws RemoteException {
        //set a new active player
        timer.cancel();
        setActivePlayer((Player) getNextActivePlayer());
        getDrawing().clear();
    }

    public boolean guessWord(String guess, IPlayer player) throws RemoteException {
        guess = guess.toLowerCase();
        String guessWord = getActivePlayer().getWord().toLowerCase();
        if (Objects.equals(guess, guessWord)) {
            this.getChat().setMessage("Guessed the word '" + guess + "', HE WAS RIGHT!!!!", LocalDateTime.now(), player.getName());
            IPlayer pl = players.get(players.indexOf(player));
            win(pl);
            return true;
        }
        this.getChat().setMessage("Guessed the word '" + guess + "', IT WAS WRONG!!!!", LocalDateTime.now(), player.getName());
        return false;
    }

    private void loadWordList() {
        ArrayList<String> words = new ArrayList<>();
        System.out.println("Trying to read words from the file");
        File wordFile = new File(System.getProperty("user.dir") + "\\src\\wordList.txt");
        try (Scanner scanner = new Scanner(new FileReader(wordFile))) {
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            System.out.println("Finished reading " + words.size() + " words.");
        } catch (Exception e) {
            System.err.println("Error reading word file, exiting.");
        }
        this.wordList = words;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) {
        publisher.unsubscribeRemoteListener(listener, property);
    }
}
