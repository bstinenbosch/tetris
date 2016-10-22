package highscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import java.util.stream.Collectors;

import logging.Logger;

public class OnlineScoreBoard implements IScoreBoard {

    private TreeSet<GameEntry> board = new TreeSet<>();
    private final String server = "jdbc:mysql://remote-mysql3.servage.net";
    private final String user = "tetrisSEM";
    private final String pass = "tetrisSEM1";

    public OnlineScoreBoard() {
        fetchBoard();
    }

    private void fetchBoard() {
        try {
//            Connection connection = DriverManager.getConnection(server, user, pass);
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(user);
            dataSource.setPassword(pass);
            dataSource.setServerName(server);
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                .executeQuery("select username,score,syscreated from top10");
            while (resultSet.next()) {
                board
                    .add(new GameEntry(resultSet.getString("username"), resultSet.getInt("score")));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            Logger.warning(this,
                "Could not succesfully get a connection to the highscores database. Are you connected to the internet?");
        }
    }

    public OnlineScoreBoard(boolean startEmpty) {
        if (!startEmpty) {
            fetchBoard();
        }
    }

    @Override
    public GameEntry[] getScores() {
        return (GameEntry[]) board.toArray();
    }

    @Override
    public void add(GameEntry entry) {
        board.add(entry);
        if (board.size() > 10) {
            board.pollFirst();
        }
        try {
            Connection connection = DriverManager.getConnection(server, user, pass);
            PreparedStatement pst = connection
                .prepareStatement("INSERT INTO highscores(username,score) VALUES(?,?)");
            pst.setString(1, entry.getName());
            pst.setInt(2, entry.getScore());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException exception) {
            Logger.warning(this,
                "Could not succesfully get a connection to the highscores database. Are you connected to the internet?");
        }
    }

    @Override
    public boolean isHighscore(int score) {
        return (board.first().getScore() < score);
    }

    @Override
    public String toString() {
        return board.descendingSet().stream().map(GameEntry::toString)
            .collect(Collectors.joining(", ", "{", "}"));
    }

    public static void main(String[] args) {
        System.out.println(new OnlineScoreBoard().toString());
    }

}
