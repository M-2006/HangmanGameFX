# HangmanGameFX 🎮

[![Java Version](https://img.shields.io/badge/Java-11+-blue)]()
[![JavaFX Version](https://img.shields.io/badge/JavaFX-11+-orange)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/badge/Build-Passing-green)]()

A modern JavaFX implementation of the classic Hangman word-guessing game with sleek visuals and intuitive gameplay.

![Gameplay Demo](demo/hangman-demo.gif)

## Table of Contents
- [Features](#features-)
- [Requirements](#requirements-)
- [Installation](#installation-)
- [Running the Game](#running-the-game-)
- [How to Play](#how-to-play-)
- [Configuration](#configuration-)
- [Building from Source](#building-from-source-)
- [Contributing](#contributing-)
- [License](#license-)

## Features ✨

- 🎨 Modern JavaFX UI with smooth animations
- 📚 Multiple word categories (Animals, Countries, Programming)
- 🎚️ Adjustable difficulty levels
- 🖱️ Dual input support (keyboard and mouse)
- 📊 Score tracking and game statistics
- 🌈 Customizable visual themes
- ⏳ Timed game mode option
- 📶 Responsive design for various window sizes

## Requirements 📋

- Java JDK 11 or higher
- JavaFX 11+ SDK
- Maven 3.6+ (for building)
- 2GB RAM minimum
- 1024×768 minimum display resolution

## Installation ⚙️

### Using Pre-built JAR
1. Download the latest release from [Releases](https://github.com/M-2006/HangmanGameFX/releases)
2. Run with:
   ```bash
   java -jar HangmanGameFX.jar
From Source
bash
Copy
git clone https://github.com/M-2006/HangmanGameFX.git
cd HangmanGameFX
Running the Game 🚀
IDE
Import as Maven project and run Main.java

Command Line
bash
Copy
mvn clean javafx:run
How to Play 🎮
Launch the game and select your preferences

Guess letters by:

Clicking on-screen keyboard

Typing on physical keyboard

Correct guesses reveal letters

Incorrect guesses (max 6) progress the hangman drawing

Win by guessing all letters before hangman is complete

Configuration ⚙️
Create config.properties in the root directory to customize:

properties
Copy
# Theme options: DARK, LIGHT, BLUE
theme=DARK

# Default difficulty: EASY, MEDIUM, HARD
difficulty=MEDIUM

# Enable/disable animations
animations=true
Building from Source 🔨
Ensure you have JDK 11+ and Maven installed

Build executable JAR:

bash
Copy
mvn clean package
Find the packaged JAR in target/ directory

Contributing 🤝
We welcome contributions! Please follow these guidelines:

Fork the repository

Create a feature branch (git checkout -b feature/your-feature)

Commit your changes (git commit -m 'Add some feature')

Push to the branch (git push origin feature/your-feature)

Open a Pull Request

Please ensure:

Code follows existing style

New features include tests

Documentation is updated

License 📄
This project is licensed under the MIT License - see the LICENSE file for details.

Made with ❤️ and JavaFX | Report Issues
