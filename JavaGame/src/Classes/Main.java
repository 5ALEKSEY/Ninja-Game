import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Class for creating the main menu
 */
public class Main extends Application{
    static Pane primaryroot = new Pane();
    Scene primaryscene = new Scene(primaryroot);
    Image MainFon = new Image(getClass().getResourceAsStream("mainfon.jpg"));
    ImageView imageViewMainFon = new ImageView(MainFon);
    Button StartGameButton = new Button("Начать игру");
    Button ViewRulesButton = new Button("Правила игры");
    Button ViewRecordTableButton = new Button("Таблица рекордов");
    Button ExitGameButton = new Button("Выйти");
    Media media = new Media("File:///D:/Алексей/Программы/work_space/JavaGameProject/src/music.mp3");
    Image StageIcon = new Image(getClass().getResourceAsStream("stageicon.png"));
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    Label WellcomeLabel = new Label("Ninja Game");
    GameProcess gameProcess;

    /**
     * The main method of creating menus
     * @param primaryStage primary stage in menu
     * @throws Exception Expected exceptions
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        mediaPlayer.setVolume(0.02);
        mediaPlayer.setAutoPlay(true);

        WellcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        WellcomeLabel.setTextFill(Color.RED);
        WellcomeLabel.setTranslateX(195);
        WellcomeLabel.setTranslateY(20);

        primaryroot.setPrefSize(600,400);
        imageViewMainFon.setFitHeight(450);
        imageViewMainFon.setFitWidth(650);

        StartGameButton.setPrefSize(250,40);
        StartGameButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        StartGameButton.setTextFill(Color.RED);
        StartGameButton.setTranslateX(180);
        StartGameButton.setTranslateY(100);

        StartGameButton.setOnAction(event -> {
            gameProcess = new GameProcess();
            try {
                gameProcess.StartGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ViewRulesButton.setPrefSize(250,40);
        ViewRulesButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        ViewRulesButton.setTextFill(Color.RED);
        ViewRulesButton.setTranslateX(180);
        ViewRulesButton.setTranslateY(160);

        java.lang.String TextRules = new InformationControl().GetRules();
        ViewRulesButton.setOnAction(event -> {
            Stage RulesStage = new Stage();
            Pane Rulesroot = new Pane();
            Rulesroot.setPrefSize(600,400);

            TextArea RulesArea = new TextArea(TextRules);
            RulesArea.setEditable(false);
            RulesArea.setFont(new Font("Arial", 24));
            RulesArea.setPrefSize(605,405);

            Rulesroot.getChildren().addAll(RulesArea);
            Scene Rulesscene = new Scene(Rulesroot);
            RulesStage.setScene(Rulesscene);
            RulesStage.setResizable(false);
            RulesStage.setTitle("Rules");
            RulesStage.getIcons().add(StageIcon);
            RulesStage.show();
        });

        ViewRecordTableButton.setPrefSize(250,40);
        ViewRecordTableButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        ViewRecordTableButton.setTextFill(Color.RED);
        ViewRecordTableButton.setTranslateX(180);
        ViewRecordTableButton.setTranslateY(220);

        java.lang.String  nameTableText = new InformationControl().GetRecordName();
        java.lang.String  timeTableText = new InformationControl().GetRecordTime();
        ViewRecordTableButton.setOnAction(event -> {
            Stage RecordTableStage = new Stage();

            Label nickNameEnterLabel = new Label("Enter nickname for fast search:");
            nickNameEnterLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            TextField nickNameEnterTextField = new TextField();

            Button findRecordButton = new Button("Find record");
            findRecordButton.setOnAction(event1 -> {
                try {
                    if (nickNameEnterTextField.getText().isEmpty()) {
                        new DialogMenager().showErrorDialogWindow("Search by nickname",
                                "Nickname is not entered",
                                "Please enter nickname correctly");
                    } else if (new InformationControl().GetTimeFromNickName(nickNameEnterTextField.getText()) == null) {
                        new DialogMenager().showErrorDialogWindow("Search by nickname",
                                "Record by nickname " + nickNameEnterTextField.getText() + " not found",
                                "Nickname is incorrect or nickname is not present in the table");
                    } else {
                        new DialogMenager().showInfoDialogWindow("Search by nickname",
                                "Record by nickname " + nickNameEnterTextField.getText() + ": "
                                        + new InformationControl().GetTimeFromNickName(nickNameEnterTextField.getText()),
                                "Record successfully found");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            nickNameEnterTextField.setOnAction(findRecordButton.getOnAction());

            Label tableLabel = new Label("Record table");
            tableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            tableLabel.setTextFill(Color.RED);

            TextArea tableNameTextArea = new TextArea("NICKNAME\n\n" + nameTableText);
            tableNameTextArea.setEditable(false);
            tableNameTextArea.setFont(new Font("Arial", 15));
            tableNameTextArea.setPrefSize(150,250);

            TextArea tableTimeTextArea = new TextArea("TIME (sec)\n\n" + timeTableText);
            tableTimeTextArea.setEditable(false);
            tableTimeTextArea.setFont(new Font("Arial", 15));
            tableTimeTextArea.setPrefSize(150,250);

            HBox hBox = new HBox();
            hBox.getChildren().addAll(tableNameTextArea, tableTimeTextArea);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(nickNameEnterLabel, nickNameEnterTextField,
                    findRecordButton, tableLabel, hBox);


            Scene RecordTablescene = new Scene(vBox);
            RecordTableStage.setScene(RecordTablescene);
            RecordTableStage.setResizable(true);
            RecordTableStage.setTitle("Record table");
            RecordTableStage.getIcons().add(StageIcon);
            RecordTableStage.show();
        });

        ExitGameButton.setPrefSize(250,40);
        ExitGameButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        ExitGameButton.setTextFill(Color.RED);
        ExitGameButton.setTranslateX(180);
        ExitGameButton.setTranslateY(280);

        ExitGameButton.setOnAction(event -> {
            primaryStage.close();
        });

        primaryroot.getChildren().addAll(imageViewMainFon, StartGameButton, ViewRulesButton,
                ViewRecordTableButton, ExitGameButton, WellcomeLabel);
        primaryStage.setScene(primaryscene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Main menu");
        primaryStage.getIcons().add(StageIcon);
        primaryStage.show();
    }
}
