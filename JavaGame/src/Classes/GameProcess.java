import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class is needed to create a game process
 */
public class GameProcess {
    public static final int END_GAME = 50;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Bonuse> bonuses = new ArrayList<>();
    Stage GameStage = new Stage();
    static Pane root = new Pane();
    Scene Gamescene = new Scene(root);
    Image IMAGE1 = new Image(getClass().getResourceAsStream("human_1.png"));
    Image IMAGE2 = new Image(getClass().getResourceAsStream("human_2.png"));
    Image IMAGE3 = new Image(getClass().getResourceAsStream("fon.jpg"));
    Image StageIcon = new Image(getClass().getResourceAsStream("stageicon.png"));
    ImageView imageView1 = new ImageView(IMAGE1);
    ImageView imageView2 = new ImageView(IMAGE2);
    ImageView imageView3 = new ImageView(IMAGE3);
    Characters player1 = new Characters(imageView1);
    Characters player2 = new Characters(imageView2);
    Label ScoreLabel = new Label("0:0");
    AnimationTimer GameTimer;
    AnimationTimer Timer;
    Integer score = new Integer(4);
    Label labelTimer = new Label(score.toString());
    Label labelWIN = new Label();
    Label labelContinue = new Label();
    Button Yesbutton = new Button("Yes");
    Button Nobutton = new Button("No");
    Button SaveRecordButton = new Button("Congratulations! Your record can be saved!");
    int WinOffsetX = 85;
    int WinOffsetY = 100;
    Thread RecordTimer;
    public static Integer timercount;

    /**
     * The method is necessary for creating a game board and game process
     *
     * @throws Exception Expected exception
     */
    public void StartGame() throws Exception {
        root.setPrefSize(600, 400);
        imageView3.setFitHeight(450);
        imageView3.setFitWidth(650);

        player1.setTranslateX(570);
        root.getChildren().addAll(imageView3, player1);
        player2.setTranslateX(0);

        ScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        ScoreLabel.setTextFill(Color.AZURE);
        ScoreLabel.setTranslateX(260);
        root.getChildren().addAll(player2, ScoreLabel);
        Gamescene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        Gamescene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        labelTimer.setFont(Font.font("Times new Roman", FontWeight.BOLD, 300));
        labelTimer.setTextFill(Color.BLUE);
        labelTimer.setTranslateX(230);
        labelTimer.setTranslateY(60);
        root.getChildren().addAll(labelTimer);

        GameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                UpdateForFirstCharacter();
                UpdateForSecondCharacter();
                UpdateScore();
                AddBonus();
                TheEnd();
            }
        };

        Timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    StartTimer();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer.start();

        GameStage.setScene(Gamescene);
        GameStage.setResizable(false);
        GameStage.setTitle("Ninja Game");
        GameStage.getIcons().add(StageIcon);
        GameStage.show();
    }

    /**
     * The method is necessary for catching keystrokes of the keyboard
     *
     * @param keyCode keyboard key code
     * @return adding to the list of keys
     */
    public boolean isPressed(KeyCode keyCode) {
        return keys.getOrDefault(keyCode, false);
    }

    /**
     * The method is necessary for moving the first character on the playing board
     */
    public void UpdateForFirstCharacter() {
        if (isPressed(KeyCode.UP)) {
            player1.animation.play();
            player1.animation.setOffsetY(192);
            player1.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            player1.animation.play();
            player1.animation.setOffsetY(0);
            player1.moveY(2);
        } else if (isPressed(KeyCode.LEFT)) {
            player1.animation.play();
            player1.animation.setOffsetY(64);
            player1.moveX(-2);
        } else if (isPressed(KeyCode.RIGHT)) {
            player1.animation.play();
            player1.animation.setOffsetY(128);
            player1.moveX(2);
        } else if (isPressed(KeyCode.ENTER)) {
            if (player1.GetSpecialReception() == true) {
                player2.SetScore(-5);
                player1.SetSpecialReception(false);
            }
        } else
            player1.animation.stop();
    }

    /**
     * The method is necessary for moving the second character on the playing board
     */
    public void UpdateForSecondCharacter() {
        if (isPressed(KeyCode.W)) {
            player2.animation.play();
            player2.animation.setOffsetY(192);
            player2.moveY(-2);
        } else if (isPressed(KeyCode.S)) {
            player2.animation.play();
            player2.animation.setOffsetY(0);
            player2.moveY(2);
        } else if (isPressed(KeyCode.A)) {
            player2.animation.play();
            player2.animation.setOffsetY(64);
            player2.moveX(-2);
        } else if (isPressed(KeyCode.D)) {
            player2.animation.play();
            player2.animation.setOffsetY(128);
            player2.moveX(2);
        } else if (isPressed(KeyCode.SPACE)) {
            if (player2.GetSpecialReception() == true) {
                player1.SetScore(-5);
                player2.SetSpecialReception(false);
            }
        } else
            player2.animation.stop();
    }

    /**
     * The method is necessary to create a bonus on the playing board
     */
    public void AddBonus() {
        int specrandom = (int) Math.floor(Math.random() * 1000);
        int sx = (int) Math.floor(Math.random() * 580);
        int sy = (int) Math.floor(Math.random() * 330 + 50);
        int highrandom = (int) Math.floor(Math.random() * 500);
        int hx = (int) Math.floor(Math.random() * 580);
        int hy = (int) Math.floor(Math.random() * 330 + 50);
        int random = (int) Math.floor(Math.random() * 85);
        int x = (int) Math.floor(Math.random() * 580);
        int y = (int) Math.floor(Math.random() * 330 + 50);
        if (random == 50) {
            Bonuse bonuse = new Bonuse(x, y, 1);
            bonuses.add(bonuse);
            root.getChildren().addAll(bonuse);
        }
        if (highrandom == 250) {
            Bonuse highbonuse = new Bonuse(hx, hy, 2);
            bonuses.add(highbonuse);
            root.getChildren().addAll(highbonuse);
        }
        if (specrandom == 250) {
            Bonuse specbonuse = new Bonuse(sx, sy, 3);
            bonuses.add(specbonuse);
            root.getChildren().addAll(specbonuse);
        }
    }

    /**
     * Method is required to update the game points
     */
    public void UpdateScore() {
        ScoreLabel.setText(player2.score + ":" + player1.score);
    }

    /**
     * The method is necessary to wait for the end event of the game
     */
    public void TheEnd() {
        if (player1.score >= END_GAME) {
            labelWIN.setText("Red player WIN!");
            labelWIN.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            labelWIN.setTranslateY(WinOffsetY);
            labelWIN.setTranslateX(WinOffsetX);
            labelWIN.setTextFill(Color.RED);
            root.getChildren().addAll(labelWIN);
            Continue();
        }
        if (player2.score >= END_GAME) {
            labelWIN.setText("Blue player WIN!");
            labelWIN.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            labelWIN.setTranslateY(WinOffsetY);
            labelWIN.setTranslateX(WinOffsetX);
            labelWIN.setTextFill(Color.BLUE);
            root.getChildren().addAll(labelWIN);
            Continue();
        }
    }

    /**
     * The method is necessary to suggest a choice about further actions
     */
    public void Continue() {
        GameTimer.stop();
        RecordTimer.stop();
        labelContinue.setText("Continue?");
        labelContinue.setFont(Font.font("Arial", FontWeight.LIGHT, 55));
        labelContinue.setTranslateX(WinOffsetX + 100);
        labelContinue.setTranslateY(WinOffsetY + 80);
        labelContinue.setTextFill(Color.BISQUE);

        SaveRecordButton.setPrefSize(400, 30);
        SaveRecordButton.setFont(Font.font("Arial", FontWeight.LIGHT, 15));
        SaveRecordButton.setTextFill(Color.RED);
        SaveRecordButton.setTranslateX(110);
        SaveRecordButton.setTranslateY(330);

        Yesbutton.setPrefSize(100, 30);
        Yesbutton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        Yesbutton.setTextFill(Color.RED);
        Yesbutton.setTranslateX(WinOffsetX + 95);
        Yesbutton.setTranslateY(labelContinue.getTranslateY() + 80);

        Nobutton.setPrefSize(Yesbutton.getPrefWidth(), Yesbutton.getPrefHeight());
        Nobutton.setFont(Yesbutton.getFont());
        Nobutton.setTextFill(Yesbutton.getTextFill());
        Nobutton.setTranslateX(Yesbutton.getTranslateX() + 150);
        Nobutton.setTranslateY(Yesbutton.getTranslateY());

        if (timercount < 80)
            root.getChildren().add(SaveRecordButton);

        root.getChildren().addAll(labelContinue, Yesbutton, Nobutton);

        Nobutton.setOnAction(event -> {
            GameStage.close();
        });

        Yesbutton.setOnAction(event -> {
            root.getChildren().removeAll(labelContinue, labelWIN, Nobutton, Yesbutton, player1, player2, SaveRecordButton);

            player1.setTranslateX(570);
            player1.setTranslateY(0);
            player1.animation.setOffsetY(0);
            player1.animation.setOffsetX(0);

            player2.setTranslateX(0);
            player2.setTranslateY(0);
            player2.animation.setOffsetY(0);
            player2.animation.setOffsetX(0);

            root.getChildren().addAll(player1, player2);

            player2.score = player1.score = 0;
            UpdateScore();

            while (bonuses.size() > 0) {
                root.getChildren().remove(bonuses.get(0));
                bonuses.remove(0);
            }

            root.getChildren().addAll(labelTimer);

            Timer.start();
        });

        SaveRecordButton.setOnAction(event -> {
            Stage SaveRecordStage = new Stage();
            Pane SaveRecordroot = new Pane();

            Label startLabel = new Label("Congratulations! Your time: " + timercount.toString() + " seconds");
            startLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            Label nickNameLabel = new Label("NickName: ");
            nickNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            TextField nickNameEnterTextField = new TextField();

            HBox hBox = new HBox();
            hBox.getChildren().addAll(nickNameLabel, nickNameEnterTextField);

            Button saveRecordButton = new Button("Save record");
            saveRecordButton.setPrefSize(100, 10);
            saveRecordButton.setOnAction(event1 -> {
                if (nickNameEnterTextField.getText().isEmpty()) {
                    new DialogMenager().showErrorDialogWindow("Save record",
                            "Nickname is not entered",
                            "Please enter nickname correctly");
                } else if (!isCorrectNickName(nickNameEnterTextField.getText())) {
                    new DialogMenager().showErrorDialogWindow("Save record",
                            "Nickname was entered incorrectly",
                            "Use for input only English characters and numbers");
                } else {
                    try {
                        new InformationControl().SaveRecord(nickNameEnterTextField.getText(), timercount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new DialogMenager().showInfoDialogWindow("Save record",
                            "Nickname " + nickNameEnterTextField.getText() + ": " + timercount.toString() + " seconds",
                            "Record successfully saved");
                    SaveRecordStage.close();
                }
            });

            VBox vBox = new VBox();
            vBox.getChildren().addAll(startLabel, hBox, saveRecordButton);

            SaveRecordroot.getChildren().addAll(vBox);
            Scene Rulesscene = new Scene(SaveRecordroot);
            SaveRecordStage.setScene(Rulesscene);
            SaveRecordStage.setResizable(false);
            SaveRecordStage.show();
        });
    }

    /**
     * The method is necessary for the initial timer
     * @throws InterruptedException Expected exception
     */
    public void StartTimer() throws InterruptedException {
        score--;
        labelTimer.setText(score.toString());
        if (score == 0) {
            score = 4;
            Timer.stop();
            root.getChildren().remove(labelTimer);
            GameTimer.start();
            ResetTimerCount();
            RecordTimer = new Thread(new TimerCount());
            RecordTimer.start();
        }
    }

    /**
     * The method is required to set the interval for the initial timer
     * @param time number of sleep timer in milliseconds
     * @throws InterruptedException
     */
    public void sleep(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    /**
     * Method for checking for correct input of a nickname
     * @param tempnickname nickname for verification
     * @return Boolean test result variable
     */
    public boolean isCorrectNickName(String tempnickname) {
        if (tempnickname.contains(" ") || tempnickname.contains("-") || tempnickname.contains("*") ||
                tempnickname.contains("_") || tempnickname.contains("@") || tempnickname.contains("&") ||
                tempnickname.contains("?") || tempnickname.contains("!") || tempnickname.contains(".") ||
                tempnickname.contains("#") || tempnickname.contains("^") || tempnickname.contains("%"))
            return false;
        else
            return true;
    }

    /**
     * Method for resetting the main game timer
     */
    public static void ResetTimerCount() {
        timercount = 0;
    }
}
