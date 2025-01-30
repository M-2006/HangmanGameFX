package com.example.hangmangamefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HangmanGameFX extends Application {
    private VBox root;
    private Label hangmanArtLabel;
    private Label wordLabel;
    private Label messageLabel;
    private FlowPane keyboardPane;
    private Button restartButton;

    private ArrayList<String> words = new ArrayList<>();
    private String currentWord;
    private char[] wordState;
    private int wrongGuesses;

    private final String[] HANGMAN_ART = {
            """
               +---+
               |   |
                   |
                   |
                   |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
                   |
                   |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
               |   |
                   |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
              /|   |
                   |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
                   |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
              /    |
                   |
            =========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
              / \\  |
                   |
            =========
            """
    };

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEFA, #4682B4);");

        // Hangman Art
        hangmanArtLabel = new Label(HANGMAN_ART[1]);
        hangmanArtLabel.setFont(Font.font("Courier New", 20));
        hangmanArtLabel.setTextFill(Color.WHITE);

        // Word Display
        wordLabel = new Label();
        wordLabel.setFont(Font.font("Courier New", 40));
        wordLabel.setTextFill(Color.WHITE);

        // Message Label
        messageLabel = new Label("Guess the word!");
        messageLabel.setFont(Font.font("Arial", 18));
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Keyboard Panel
        keyboardPane = new FlowPane();
        keyboardPane.setHgap(10);
        keyboardPane.setVgap(10);
        keyboardPane.setAlignment(Pos.CENTER);

        // Restart Button
        restartButton = new Button("Restart");
        restartButton.setFont(Font.font("Arial", 18));
        restartButton.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-padding: 10 20;");
        restartButton.setVisible(false);
        restartButton.setOnAction(e -> initializeGame());

        root.getChildren().addAll(hangmanArtLabel, wordLabel, messageLabel, keyboardPane, restartButton);

        // Load words from file and initialize the game
        loadWordsFromFile("words.txt");
        initializeGame(); // This should be called after everything is initialized.

        Scene scene = new Scene(root, 600, 800);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey()) {
                handleGuess(event.getText().toLowerCase().charAt(0), null);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hangman Game | By: Muhamet Maliqi");
        Image image = new Image(getClass().getResourceAsStream("/codingg.jpeg"));
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }

    private void loadWordsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            showAlert("Error", "Error loading words from file: " + e.getMessage());
        }
    }

    private void initializeGame() {
        if (words.isEmpty()) {
            showAlert("Error", "No words available to play.");
            return;
        }

        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size())).toLowerCase();
        wordState = new char[currentWord.length()];
        for (int i = 0; i < wordState.length; i++) {
            wordState[i] = '_';
        }
        wrongGuesses = 0; // Make sure this resets each time
        //System.out.println("Current word: " + currentWord); // Debug current word
        updateDisplay();
        createKeyboardButtons();
        messageLabel.setText("Guess the word!");
        restartButton.setVisible(false);
    }

    private void updateDisplay() {
        int displayIndex = Math.min(wrongGuesses, HANGMAN_ART.length - 1); // Ensure within bounds
        hangmanArtLabel.setText(HANGMAN_ART[displayIndex]);
        wordLabel.setText(String.valueOf(wordState).replace("", " ").trim());
    }

    private void createKeyboardButtons() {
        keyboardPane.getChildren().clear();
        for (char c = 'A'; c <= 'Z'; c++) {
            Button button = new Button(String.valueOf(c));
            button.setFont(Font.font("Arial", 16));
            button.setStyle("-fx-background-color: white; -fx-border-color: #4682B4; -fx-border-radius: 5; -fx-padding: 5 10;");
            char finalC = c;
            button.setOnAction(e -> handleGuess(Character.toLowerCase(finalC), button));
            keyboardPane.getChildren().add(button);
        }
    }

    private void handleGuess(char guess, Button button) {
        if (button != null) {
            button.setDisable(true);
            button.setStyle("-fx-background-color: gray;");
        }

        boolean correct = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == guess) {
                wordState[i] = guess;
                correct = true;
            }
        }

        if (correct) {
            if (new String(wordState).equals(currentWord)) {
                messageLabel.setText("🎉 You Win! The word was: " + currentWord);
                messageLabel.setStyle("-fx-background-color: rgba(0,255,0,0.5);");
                endGame();
            }
        } else {
            wrongGuesses++;
            //System.out.println("Wrong guesses before update: " + (wrongGuesses - 1));  // Debug before increment
            //System.out.println("Wrong guesses after update: " + wrongGuesses);        // Debug after increment
            if (wrongGuesses >= HANGMAN_ART.length) { // Use >= HANGMAN_ART.length
                messageLabel.setText("❌ Game Over! The word was: " + currentWord);
                messageLabel.setStyle("-fx-background-color: rgba(255,0,0,0.5);");
                endGame();
            } else {
                messageLabel.setText("⚠️ Wrong guess! Try again.");
                messageLabel.setStyle("-fx-background-color: rgba(255,255,0,0.5);");
            }
        }

        updateDisplay();
    }

    private void endGame() {
        for (var node : keyboardPane.getChildren()) {
            node.setDisable(true);
        }
        restartButton.setVisible(true);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
