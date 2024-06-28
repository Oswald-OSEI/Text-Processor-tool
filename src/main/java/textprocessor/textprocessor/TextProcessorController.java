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


    private final TextProcessor textProcessor; // Instance of TextProcessor for text operations
    private final DataManager dataManager; // Instance of DataManager for managing search/replace history

    private int accessCounter; // Counter to track navigation through search/replace history

    public void initialize() {
        // Bind the width of the ResultsPane to the width of the ScrollPane's viewport
        ResultsPane.prefWidthProperty().bind(ResultsScrollPane.widthProperty());
        ResultsPane.setMaxWidth(Double.MAX_VALUE);
    }

    public TextProcessorController() {
        textProcessor = new TextProcessor(); // Initialize TextProcessor
        dataManager = new DataManager(); // Initialize DataManager
        accessCounter = 0; // Initialize access counter
    }

    @FXML
    private void handleSearch() {
        String keyword = KeyWordPane.getText().trim(); // Get search keyword
        String text = TextBox.getText().trim(); // Get text to search within

        // Validate input
        if (keyword.isEmpty() || text.isEmpty()) {
            showAlert("Input Required", "Please enter both a keyword and text to search.");
            return;
        }

        // Perform search using TextProcessor
        List<String> searchResults = textProcessor.searchWord(keyword, text);

        // Display search results with highlighting
        displayResults(searchResults);

        // Store search results in DataManager for history tracking
        dataManager.storeResult(searchResults);
    }

    @FXML
    private void handleReplace() {
        String replaceText = ReplacedText.getText().trim(); // Get replacement text
        String keyword = KeyWordPane.getText().trim(); // Get keyword to replace
        String matchString = TextBox.getText().trim(); // Get text to perform replace within

        // Validate input
        if (replaceText.isEmpty() || keyword.isEmpty()) {
            showAlert("Input Required", "Please enter both a keyword and text to replace.");
            return;
        }

        // Perform replace using TextProcessor
        List<String> replaceResults = textProcessor.replaceWord(keyword, matchString, replaceText);

        // Display replace results with highlighting
        displayResults(replaceResults);

        // Store replace results in DataManager for history tracking
        dataManager.storeResult(replaceResults);
    }

    @FXML
    private void handlePrevious() {
        // Check if there are previous actions in history
        if (accessCounter >= dataManager.getResultList().size()) {
            showAlert("Access Error", "This is your first action performed");
            return;
        }

        // Retrieve previous action result from DataManager
        List<String> result = dataManager.accessElement(accessCounter);

        // Display previous action result
        displayResults(result);

        // Increment access counter for next navigation
        accessCounter++;
    }

    @FXML
    private void handleNext() {
        // Check if there are next actions in history
        if (accessCounter <= 0) {
            showAlert("Access Error", "This is your last action.");
            return;
        }

        // Decrement access counter for previous navigation
        accessCounter--;

        // Retrieve next action result from DataManager
        List<String> result = dataManager.accessElement(accessCounter);

        // Display next action result
        displayResults(result);
    }

    @FXML
    private void handleDelete() {
        // Check if there are actions to delete
        if (accessCounter <= 0) {
            showAlert("Delete Error", "No elements to delete.");
            return;
        }

        // Delete action from DataManager history
        dataManager.deleteElement(accessCounter - 1);

        // Decrement access counter after deletion
        accessCounter--;

        // Display deletion confirmation
        displayResults(List.of("History Deleted"));
    }

    @FXML
    private void clearFields() {
        // Clear input fields
        KeyWordPane.clear();
        TextBox.clear();
        ReplacedText.clear();
    }

    private void displayResults(List<String> results) {
        // Clear previous results from ActionPane and ResultsPane
        ActionPane.getChildren().clear();
        ResultsPane.getChildren().clear();

        // Display action details in ActionPane
        Text actionTextNode = new Text(results.get(1) + "\n");
        ActionPane.getChildren().add(actionTextNode);

        // Display search/replace results in ResultsPane
        if (results.size() > 1) {
            String inputText = TextBox.getText().trim(); // Get original text from TextBox
            String keyword = KeyWordPane.getText().trim(); // Get search keyword from KeyWordPane

            // Highlight keyword in original text and display in ResultsPane
            TextFlow resultTextFlow = highlightWord(results.get(2), results.getLast());
            ResultsPane.getChildren().add(resultTextFlow);
            resultTextFlow.setPrefWidth(ResultsScrollPane.getWidth()); // Ensure text flow fits within pane width

        }
    }

    private TextFlow highlightWord(String inputText, String keyword) {
        // Initialize TextFlow to display highlighted text
        TextFlow textFlow = new TextFlow();
        textFlow.setMaxWidth(Double.MAX_VALUE);


        try {
            // Compile regex pattern for keyword search
            Pattern pattern = Pattern.compile(keyword);
            Matcher matcher = pattern.matcher(inputText);

            int lastIndex = 0;
            while (matcher.find()) {
                // Add text before matched pattern
                if (matcher.start() > lastIndex) {
                    String textBefore = inputText.substring(lastIndex, matcher.start());
                    textFlow.getChildren().add(new Text(textBefore));
                }

                // Add highlighted matched pattern
                String foundPattern = inputText.substring(matcher.start(), matcher.end());
                Text highlightedText = new Text(foundPattern);
                highlightedText.setStyle("-fx-font-weight: bold; -fx-fill: red;");
                textFlow.getChildren().add(highlightedText);

                lastIndex = matcher.end();
            }

            // Add remaining text after last match
            if (lastIndex < inputText.length()) {
                String remainingText = inputText.substring(lastIndex);
                textFlow.getChildren().add(new Text(remainingText));
            }

        } catch (Exception e) {
            // Handle and display error if highlighting fails
            showAlert("Error", "An error occurred while highlighting text: " + e.getMessage());
        }


        return textFlow; // Return TextFlow with highlighted text
    }

    private void showAlert(String title, String message) {
        // Display alert dialog with given title and message
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
