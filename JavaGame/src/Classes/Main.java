import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    Label WellcomeLabel = new Label("Ninja Game");
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
            primaryStage.close();
            GameProcess gameprocess = new GameProcess();
            try {
                gameprocess.StartGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ViewRulesButton.setPrefSize(250,40);
        ViewRulesButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        ViewRulesButton.setTextFill(Color.RED);
        ViewRulesButton.setTranslateX(180);
        ViewRulesButton.setTranslateY(160);

        java.lang.String finalRules = new InformationControl().GetRules();
        ViewRulesButton.setOnAction(event -> {
            Stage RulesStage = new Stage();
            Pane Rulesroot = new Pane();
            Rulesroot.setPrefSize(600,400);

            TextArea RulesArea = new TextArea(finalRules);
            RulesArea.setEditable(false);
            RulesArea.setFont(new Font("Arial", 24));
            RulesArea.setPrefSize(605,405);

            Rulesroot.getChildren().addAll(RulesArea);
            Scene Rulesscene = new Scene(Rulesroot);
            RulesStage.setScene(Rulesscene);
            RulesStage.setResizable(false);
            RulesStage.show();
        });

        ViewRecordTableButton.setPrefSize(250,40);
        ViewRecordTableButton.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        ViewRecordTableButton.setTextFill(Color.RED);
        ViewRecordTableButton.setTranslateX(180);
        ViewRecordTableButton.setTranslateY(220);

        ObservableList<Records> RecordsList = new InformationControl().getRecordsList();
        ViewRecordTableButton.setOnAction(event -> {
            Stage RecordTableStage = new Stage();

            TableColumn<Records, java.lang.String> RecordNameColumn = new TableColumn<>("Name");
            RecordNameColumn.setMinWidth(200);
            RecordNameColumn.setCellValueFactory(new PropertyValueFactory<Records, java.lang.String>("name"));

            TableColumn<Records, Double> RecordTimeColumn = new TableColumn<>("Time");
            RecordTimeColumn.setMinWidth(200);
            RecordTimeColumn.setCellValueFactory(new PropertyValueFactory<Records, Double>("time"));

            TableView<Records> tableView = new TableView<>();
            tableView.setItems(RecordsList);
            tableView.getColumns().addAll(RecordNameColumn, RecordTimeColumn);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView);

            Scene RecordTablescene = new Scene(vBox);
            RecordTableStage.setScene(RecordTablescene);
            RecordTableStage.setResizable(true);
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
        primaryStage.show();
    }

}
