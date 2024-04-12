package Controllers.Musee.Components;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import Entités.Musee;

public class DecisionCell extends TableCell<Musee, Integer> {

    @Override
    protected void updateItem(Integer accept, boolean empty) {
        super.updateItem(accept, empty);

        if (empty || accept == null) {
            setText(null);
            setStyle(""); // Clear any previous styling
        } else {
            switch (accept) {
                case 0:
                    setText("En attente");
                    setTextFill(Color.ORANGE); // Set text color to orange
                    break;
                case 1:
                    setText("Accepté");
                    setTextFill(Color.GREEN); // Set text color to green
                    break;
                case 2:
                    setText("Refusé");
                    setTextFill(Color.RED); // Set text color to red
                    break;
                default:
                    setText("Unknown");
                    setTextFill(Color.BLACK); // Set text color to black (or any other default color)
                    break;
            }
        }
    }
}
