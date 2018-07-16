package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Start dialog class.
 */
public class StartDialog {
    static private String lastXCellsInput = "";
    static private String lastYCellsInput = "";
    static private String lastMinesCountInput = "";

    Dialog<Settings> dialog = new Dialog<>();
    Settings settings;

    private boolean forceClose = false;

    private boolean validate(String xCellsInput, String yCellsInput, String minesCountInput) {
        try {
            int xCells = Integer.parseInt(xCellsInput);
            int yCells = Integer.parseInt(yCellsInput);
            int minesCount = Integer.parseInt(minesCountInput);

            if (Settings.validate(xCells, yCells, minesCount)) {
                lastXCellsInput = xCellsInput;
                lastYCellsInput = yCellsInput;
                lastMinesCountInput = minesCountInput;

                settings = new Settings(xCells, yCells, minesCount);
                return true;
            }

            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    StartDialog(String text, boolean showCancel) {
        // Initialize dialog window settings.
        dialog.setTitle("Game Settings");
        dialog.setHeaderText(text);
        dialog.setResizable(false);
        dialog.setX(0);
        dialog.setWidth(1500);
        dialog.setHeight(500);
        // Initialize dialog controls.
        Label xCellsLabel = new Label("Height: ");
        Label xCellsLabe1 = new Label("  9 ");
        Label xCellsLabe2 = new Label("  16 ");
        Label xCellsLabe3 = new Label("  16 ");
        Label xCellsLabelBeginner=new Label(" Beginner config: ");
        Label xCellsLabelIntermediate=new Label(" Intermediate config: ");
        Label xCellsLabelExpert=new Label(" Expert config: ");
        Label xCellsLabelCustom=new Label(" Custom config: ");
        Label yCellsLabel = new Label("Width: ");
        Label yCellsLabel1 = new Label("  9 ");
        Label yCellsLabel2 = new Label("  16 ");
        Label yCellsLabel3 = new Label("  30 ");
        Label minesCountLabel = new Label("Bombs: ");
        Label minesCountLabel1 = new Label("  10 ");
        Label minesCountLabel2 = new Label("  40 ");
        Label minesCountLabel3 = new Label("  99 ");
        Label errorLabel = new Label();
        errorLabel.setWrapText(true);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setMinHeight(90);
        TextField xCellsField = new TextField(lastXCellsInput);
        xCellsField.setMaxWidth(80);
        TextField yCellsField = new TextField(lastYCellsInput);
        yCellsField.setMaxWidth(80);
        TextField minesCountField = new TextField(lastMinesCountInput);
        minesCountField.setMaxWidth(80);

        // Initialize dialog grid.
        GridPane gridPane = new GridPane();
        GridPane fieldsGrid = new GridPane();
        fieldsGrid.setVgap(10);
        fieldsGrid.setPrefWidth(700);
        ColumnConstraints column1 = new ColumnConstraints();
       // column1.setPercentWidth(70);
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        //column2.setPercentWidth(100);
        column2.setPercentWidth(30);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        fieldsGrid.getColumnConstraints().addAll(column1, column2,column3);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(110);
        gridPane.getColumnConstraints().addAll(column);


//
//        fieldsGrid.add(xCellsLabel, 0, 0);
//        fieldsGrid.add(xCellsField, 1, 0);
//        fieldsGrid.add(yCellsLabel, 0, 1);
//        fieldsGrid.add(yCellsField, 1, 1);
//        fieldsGrid.add(minesCountLabel, 0, 2);
//        fieldsGrid.add(minesCountField, 1, 2);
//
//        gridPane.add(fieldsGrid, 0, 0);
        fieldsGrid.add(xCellsLabelBeginner, 0, 1);
        fieldsGrid.add(xCellsLabelIntermediate, 0, 2);
        fieldsGrid.add(xCellsLabelExpert, 0, 3);
        fieldsGrid.add(xCellsLabelCustom, 0, 4);
        fieldsGrid.add(xCellsLabel, 1, 0);
        fieldsGrid.add(xCellsLabe1, 1, 1);
        fieldsGrid.add(xCellsLabe2, 1, 2);
        fieldsGrid.add(xCellsLabe3, 1, 3);
        fieldsGrid.add(xCellsField, 1, 4);

        fieldsGrid.add(yCellsLabel, 2, 0);
        fieldsGrid.add(yCellsLabel1, 2, 1);
        fieldsGrid.add(yCellsLabel2, 2, 2);
        fieldsGrid.add(yCellsLabel3, 2, 3);
        fieldsGrid.add(yCellsField, 2, 4);

        fieldsGrid.add(minesCountLabel, 3, 0);
        fieldsGrid.add(minesCountLabel1, 3, 1);
        fieldsGrid.add(minesCountLabel2, 3, 2);
        fieldsGrid.add(minesCountLabel3, 3, 3);
        fieldsGrid.add(minesCountField, 3, 4);

        gridPane.add(fieldsGrid, 0, 0);


        gridPane.add(errorLabel, 0, 1);

        dialog.getDialogPane().setContent(gridPane);
        ButtonType buttonTypeIntermediate = new ButtonType("Intermediate");
        ButtonType buttonTypeExpert = new ButtonType("Expert");
        ButtonType buttonTypeBeginner = new ButtonType("Beginner",ButtonBar.ButtonData.LEFT);
        ButtonType buttonTypeClose = new ButtonType("CLOSE",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeBeginner);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeIntermediate);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeExpert);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeClose);
        // Initialize OK button.
        ButtonType buttonTypeOk = new ButtonType("Custom", ButtonBar.ButtonData.OK_DONE);


        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        // OK button event.
        dialog.setOnCloseRequest((event) -> {
            if (!forceClose)
                event.consume();
            else
                forceClose = false;
        });

        dialog.getDialogPane().lookupButton(buttonTypeClose).addEventHandler(ActionEvent.ACTION, (event) -> {
                forceClose = true;
                dialog.close();
        });




        dialog.getDialogPane().lookupButton(buttonTypeBeginner).addEventHandler(ActionEvent.ACTION, (event) -> {
            if (validate("9", "9", "10")) {
                forceClose = true;
                dialog.close();
            } else {
                errorLabel.setText("Each field must contain positive integer.\nBombs must be equal or lower than cells number.");
            }
        });

        dialog.getDialogPane().lookupButton(buttonTypeIntermediate).addEventHandler(ActionEvent.ACTION, (event) -> {
            if (validate("16", "16", "40")) {
                forceClose = true;
                dialog.close();
            }
            else {
                errorLabel.setText("Each field must contain positive integer.\nBombs must be equal or lower than cells number.");
            }
        });

        dialog.getDialogPane().lookupButton(buttonTypeExpert).addEventHandler(ActionEvent.ACTION, (event) -> {
            if (validate("16", "30", "99")) {
                forceClose = true;
                dialog.close();
            }
            else {
                errorLabel.setText("Each field must contain positive integer.\nBombs must be equal or lower than cells number.");
            }
        });


        dialog.getDialogPane().lookupButton(buttonTypeOk).addEventHandler(ActionEvent.ACTION, (event) -> {
            if (validate(xCellsField.getText(), yCellsField.getText(), minesCountField.getText())) {
                forceClose = true;
                dialog.close();
            }
            else {
                errorLabel.setText("Each field must contain positive integer.\nBombs must be equal or lower than cells number.");
            }
        });

        // Initialize Cancel button.
        if (showCancel) {
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
            dialog.getDialogPane().lookupButton(buttonTypeCancel).addEventHandler(ActionEvent.ACTION, (event) -> {
                forceClose = true;
                dialog.close();
            });
        }

    }

    public Settings getSettings() {
        dialog.showAndWait();
        return settings;
    }
}
