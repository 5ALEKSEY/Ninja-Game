import javafx.scene.control.Alert;

/**
 * The class is required to create dialog windows
 */
public class DialogMenager {
    /**
     * Method for creating an information dialog window
     * @param title Title text
     * @param headertext Header text
     * @param informationtext Information text
     */
    public void showInfoDialogWindow(String title, String headertext, String informationtext) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(informationtext);
        alert.setHeaderText(headertext);
        alert.showAndWait();
    }

    /**
     * The method is required to create a dialog window with an error
     * @param title Title text
     * @param headertext Header text
     * @param informationtext Information text
     */
    public void showErrorDialogWindow(String title, String headertext, String informationtext) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(informationtext);
        alert.setHeaderText(headertext);
        alert.showAndWait();
    }
}
