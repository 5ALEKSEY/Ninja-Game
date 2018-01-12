import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameProcess {
    public static final int END_GAME = 50;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Bonuse> bonuses = new ArrayList<>();
    Stage GameStage = new Stage();
    static Pane root = new Pane();
    Scene scene = new Scene(root);
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
    int WinOffsetX = 85;
    int WinOffsetY = 100;

    public void StartGame() throws Exception {
        root.setPrefSize(600,400);
        imageView3.setFitHeight(450);
        imageView3.setFitWidth(650);

        player1.setTranslateX(570);
        root.getChildren().addAll(imageView3, player1);
        player2.setTranslateX(0);

        ScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        ScoreLabel.setTextFill(Color.AZURE);
        ScoreLabel.setTranslateX(260);
        root.getChildren().addAll(player2, ScoreLabel);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

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

        GameStage.setScene(scene);
        GameStage.setResizable(false);
        GameStage.setTitle("Ninja Game");
        GameStage.getIcons().add(StageIcon);
        GameStage.show();
    }

    public void ExitGame() {
        GameStage.close();
    }

    public boolean isPressed(KeyCode keyCode) {
        return keys.getOrDefault(keyCode, false);
    }

    public void UpdateForFirstCharacter() {
        if(isPressed(KeyCode.UP)) {

            player1.animation.play();
            player1.animation.setOffsetY(192);
            player1.moveY(-2);
        } else if(isPressed(KeyCode.DOWN)) {
            player1.animation.play();
            player1.animation.setOffsetY(0);
            player1.moveY(2);
        } else if(isPressed(KeyCode.LEFT)) {
            player1.animation.play();
            player1.animation.setOffsetY(64);
            player1.moveX(-2);
        } else if(isPressed(KeyCode.RIGHT)) {
            player1.animation.play();
            player1.animation.setOffsetY(128);
            player1.moveX(2);
        } else if(isPressed(KeyCode.ENTER)) {
            if (player1.GetSpecialReception() == true) {
                player2.SetScore(-5);
                player1.SetSpecialReception(false);
            }
        }
        else
            player1.animation.stop();
    }
    public void UpdateForSecondCharacter() {
        if(isPressed(KeyCode.W)) {
            player2.animation.play();
            player2.animation.setOffsetY(192);
            player2.moveY(-2);
        } else if(isPressed(KeyCode.S)) {
            player2.animation.play();
            player2.animation.setOffsetY(0);
            player2.moveY(2);
        } else if(isPressed(KeyCode.A)) {
            player2.animation.play();
            player2.animation.setOffsetY(64);
            player2.moveX(-2);
        } else if(isPressed(KeyCode.D)) {
            player2.animation.play();
            player2.animation.setOffsetY(128);
            player2.moveX(2);
        } else if(isPressed(KeyCode.SPACE)) {
            if (player2.GetSpecialReception() == true) {
                player1.SetScore(-5);
                player2.SetSpecialReception(false);
            }
        } else
            player2.animation.stop();
    }
    public void AddBonus() {
        int specrandom = (int)Math.floor(Math.random()*1000);
        int sx = (int)Math.floor(Math.random()*580);
        int sy = (int)Math.floor(Math.random()*330 + 50);
        int highrandom = (int)Math.floor(Math.random()*500);
        int hx = (int)Math.floor(Math.random()*580);
        int hy = (int)Math.floor(Math.random()*330 + 50);
        int random = (int)Math.floor(Math.random()*85);
        int x = (int)Math.floor(Math.random()*580);
        int y = (int)Math.floor(Math.random()*330 + 50);
        if(random == 50) {
            Bonuse bonuse = new Bonuse(x, y,1);
            bonuses.add(bonuse);
            root.getChildren().addAll(bonuse);
        }
        if(highrandom == 250) {
            Bonuse highbonuse = new Bonuse(hx, hy, 2);
            bonuses.add(highbonuse);
            root.getChildren().addAll(highbonuse);
        }
        if(specrandom == 250) {
            Bonuse specbonuse = new Bonuse(sx, sy, 3);
            bonuses.add(specbonuse);
            root.getChildren().addAll(specbonuse);
        }
    }
    public void UpdateScore() {
        ScoreLabel.setText(player2.score + ":" + player1.score);
    }

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
        if(player2.score >= END_GAME){
            labelWIN.setText("Blue player WIN!");
            labelWIN.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            labelWIN.setTranslateY(WinOffsetY);
            labelWIN.setTranslateX(WinOffsetX);
            labelWIN.setTextFill(Color.BLUE);
            root.getChildren().addAll(labelWIN);
            Continue();
        }
    }
    public void Continue() {
        GameTimer.stop();
        labelContinue.setText("Continue?");
        labelContinue.setFont(Font.font("Arial", FontWeight.LIGHT, 55));
        labelContinue.setTranslateX(WinOffsetX + 100);
        labelContinue.setTranslateY(WinOffsetY + 80);
        labelContinue.setTextFill(Color.BISQUE);

        Yesbutton.setPrefSize(100,30);
        Yesbutton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        Yesbutton.setTextFill(Color.RED);
        Yesbutton.setTranslateX(WinOffsetX + 95);
        Yesbutton.setTranslateY(labelContinue.getTranslateY() + 80);

        Nobutton.setPrefSize(Yesbutton.getPrefWidth(), Yesbutton.getPrefHeight());
        Nobutton.setFont(Yesbutton.getFont());
        Nobutton.setTextFill(Yesbutton.getTextFill());
        Nobutton.setTranslateX(Yesbutton.getTranslateX() + 150);
        Nobutton.setTranslateY(Yesbutton.getTranslateY());

        root.getChildren().addAll(labelContinue, Yesbutton, Nobutton);

        Nobutton.setOnAction(event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        Yesbutton.setOnAction(event ->  {
            root.getChildren().removeAll(labelContinue, labelWIN, Nobutton, Yesbutton, player1, player2);

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

            while(bonuses.size() > 0) {
                root.getChildren().remove(bonuses.get(0));
                bonuses.remove(0);
            }

            root.getChildren().addAll(labelTimer);

            Timer.start();

        });

    }
    public void StartTimer() throws InterruptedException {
        score--;
        labelTimer.setText(score.toString());
        if(score == 0) {
            score = 4;
            Timer.stop();
            root.getChildren().remove(labelTimer);
            GameTimer.start();
        }
    }
    public void sleep(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}
