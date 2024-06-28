

# Text Processing Tool

## Overview

This is a Java-based text processing tool built with JavaFX for the graphical user interface. The application allows users to search for keywords within a text, replace keywords with another text, and view search and replacement history. It highlights keywords in the text and keeps track of the operations performed using a linked list.

## Features

1. **Keyword Search**: Users can search for a keyword within a text. The application highlights all occurrences of the keyword.
2. **Text Replacement**: Users can replace a keyword with another text. The application highlights the replaced text.
3. **History Tracking**: The application maintains a history of search and replacement operations using a linked list. Users can navigate through this history.
4. **Next/Previous Navigation**: Users can navigate to the next and previous search/replacement results.
5. **Delete Operation**: Users can delete specific history entries.
6. **Clear Fields** : Users can clear entry fields when the want to make new entries

## Requirements

- Java Development Kit (JDK) 8 or later
- JavaFX SDK
- An IDE such as IntelliJ IDEA or Eclipse (optional but recommended)

## Installation

### Clone the Repository

```sh
git clone  git@github.com:Oswald-OSEI/Text-Processor-tool.git
cd text-processing-tool
```

### Set Up JavaFX

1. **Download JavaFX SDK**:
   Download the JavaFX SDK from the [Gluon website](https://gluonhq.com/products/javafx/).

2. **Configure JavaFX in your IDE**:
   Follow the instructions for your specific IDE to configure JavaFX. Here are some links for popular IDEs:
   - [IntelliJ IDEA](https://www.jetbrains.com/help/idea/javafx.html)
   - [Eclipse](https://www.eclipse.org/efxclipse/install.html)

### Running the Application

1. **Open the Project**:
   Open the cloned repository in your preferred IDE.

2. **Run the Main Application**:
   Locate the main class file (typically named `Main.java`) and run it.

## Usage

1. **Search for a Keyword**:
   - Enter the keyword in the `Keyword` text field.
   - Enter the text to search in the `Text` area.
   - Click the `Search` button.
   - The application highlights all occurrences of the keyword.

2. **Replace a Keyword**:
   - Enter the keyword to be replaced in the `Keyword` text field.
   - Enter the text to search in the `Text` area.
   - Enter the replacement text in the `Replacement` text field.
   - Click the `Replace` button.
   - The application replaces all occurrences of the keyword and highlights the replaced text.

3. **Navigate Through History**:
   - Click the `Next` button to view the next history entry.
   - Click the `Previous` button to view the previous history entry.

4. **Delete History Entry**:
   - Click the `Delete` button to delete the current history entry.

5. **Clear Entry Fields**:
   - Click the `Clear Fields` button to clear entry.

## Code Structure

- **Main.java**: Entry point of the application.
- **TextProcessorController.java**: Controller class handling the UI interactions.
- **TextInput.java**: Class containing the logic for searching and replacing text.
- **DataManager.java**: Class containing the logic for handling data retrieval from arraylist and linkedlist

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`.
3. Make your changes and commit them: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Open a pull request.

## License

This project is licensed under the MIT License.


