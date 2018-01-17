import javafx.scene.control.Alert;

public class DialogMenager {
    public void showInfoDialogWindow(String title, String headertext, String informationtext) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(informationtext);
        alert.setHeaderText(headertext);
        alert.showAndWait();
    }

    public void showErrorDialogWindow(String title, String headertext, String informationtext) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(informationtext);
        alert.setHeaderText(headertext);
        alert.showAndWait();
    }
}
