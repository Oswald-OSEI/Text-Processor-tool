package textprocessor.textprocessor;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessorController {

    @FXML
    private TextField KeyWordPane;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea TextBox;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField ReplacedText;

    @FXML
    private Button ReplaceButton;

    @FXML
    private TextFlow ActionPane;

    @FXML
    private ScrollPane ResultsScrollPane;

    @FXML
    private TextFlow ResultsPane;

    @FXML
    private Button PreviousButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button NextButton;

    private TextInput textInput = new TextInput();
    private int accessCounter = 0;

    @FXML
    public void initialize() {
        // Bind the width of the ResultsPane to the width of the ScrollPane's viewport
        ResultsPane.prefWidthProperty().bind(ResultsScrollPane.widthProperty());
        ResultsPane.setMaxWidth(Double.MAX_VALUE);
    }

    @FXML
    private void handleSearch() {
        String keyword = KeyWordPane.getText().trim();
        String text = TextBox.getText().trim();

        // Perform search
        if (keyword.isEmpty() || text.isEmpty()) {
            showAlert("Input Required", "Please enter both a keyword and text to search.");
            return;
        }

        List<String> searchResults = textInput.searchWord(keyword, text);
        TextFlow resultsPaneDisplay = highlightWord(text, keyword);
        String actionPaneDisplay;
        actionPaneDisplay = searchResults.get(1);
        DisplayToPane(resultsPaneDisplay, actionPaneDisplay);
    }

    @FXML
    private void handleReplace() {
        String replaceText = ReplacedText.getText().trim();
        String keyword = KeyWordPane.getText().trim();
        String matchString = TextBox.getText().trim();

        if (replaceText.isEmpty() || keyword.isEmpty()) {
            showAlert("Input Required", "Please enter both a keyword and text to replace.");
            return;
        }
        // Perform replace logic
        List<String> newString = textInput.replaceWord(keyword, matchString, replaceText);
        String newOutput = matchString;
        if (!newString.isEmpty()) {
            newOutput = newString.get(2);
        }
        TextFlow resultsPaneDisplay = highlightWord(newOutput, replaceText);
        // Update action pane
        String actionPaneDisplay = newString.get(1);
        DisplayToPane(resultsPaneDisplay, actionPaneDisplay);
    }

    @FXML
    private void handlePrevious() {
        if (accessCounter >= textInput.getResultList().size()) {
            showAlert("Access Error", "This is your first action performed");
            return;
        }

        List<String> result = textInput.accessElement(accessCounter);
        TextFlow resultsPaneDisplay = highlightWord(result.get(2), result.get(3));
        String actionPaneDisplay = result.get(1);
        DisplayToPane(resultsPaneDisplay, actionPaneDisplay);
        accessCounter++;
    }

    @FXML
    private void handleNext() {
        if (accessCounter <= 0) {
            showAlert("Access Error", " This is your last action.");
            return;
        }

        accessCounter--;
        List<String> result = textInput.accessElement(accessCounter);
        TextFlow resultsPaneDisplay = highlightWord(result.get(2), result.get(3));
        String actionPaneDisplay = result.get(1);
        DisplayToPane(resultsPaneDisplay, actionPaneDisplay);
    }

    @FXML
    private void handleDelete() {
        if (accessCounter == -1) {
            showAlert("Delete Error", "No elements to delete.");
            return;
        }

        textInput.deleteElement(accessCounter - 1);
        accessCounter--;
        String actionPaneDisplay = "History Deleted";
        DisplayToPane(new TextFlow(), actionPaneDisplay);
    }

    private void DisplayToPane(TextFlow resultText, String actionText) {
        // Clear previous results
        ActionPane.getChildren().clear();
        ResultsPane.getChildren().clear();

        // Add action text to ActionPane
        Text actionTextNode = new Text(actionText + "\n");
        ActionPane.getChildren().add(actionTextNode);

        // Add highlighted text to ResultsPane
        ResultsPane.getChildren().addAll(resultText.getChildren());
    }

    public TextFlow highlightWord(String inputText, String keyword) {
        TextFlow textFlow = new TextFlow();
        textFlow.setMaxWidth(Double.MAX_VALUE);

        // Split inputText into words using regex to handle punctuation and spaces
        String[] words = inputText.split("\\s+");
        for (String word : words) {
            Text textNode = new Text(word + " ");
            Pattern pattern = Pattern.compile(keyword);
            Matcher search = pattern.matcher(word);

            // Highlight exact match of keyword
            if (search.find()) {
                textNode.setStyle("-fx-font-weight: bold; -fx-fill: red;");
            }

            textFlow.getChildren().add(textNode);
        }

        return textFlow;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
