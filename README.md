# Shadow Of Noxus

Shadow of Noxus is a text-based RPG developed in Java, set in the dark and militaristic lands of Noxus. The game combines turn-based combat, exploration, quests, riddles, and a shop system, offering a complete console RPG experience focused on object-oriented programming.

This project was created for academic purposes, with an emphasis on clean code, modular design, and proper use of Java fundamentals.

---

## 🎮 Game Overview

In ShadowOfNoxus, the player takes the role of a warrior facing dangerous regions, deadly enemies, and strategic decisions. Progression is achieved through battles, quests, gambling, and item management.

The game runs entirely in the console and uses ASCII art to represent maps and environments.

---

## 🧙 Playable Classes

Each class has its own identity and weapon restrictions:

* **Legionnaire** – Heavy melee fighter, specializes in swords
* **Bloody Mage** – Spellcaster that uses blood magic through staffs
* **Crimson Archer** – Ranged damage dealer with bows
* **Red Bastion** – Defensive warrior focused on shields

Weapon upgrades are class-locked, except for universal items usable by all classes.

---

## 🗺️ Game Features

* Turn-based combat system
* Multiple regions with increasing difficulty
* Tavern hub with several activities:

  * Shop system with random daily stock
  * Gambling games (dice-based)
  * Quest selection and progression
* Inventory management
* Riddle-based challenges
* ASCII world map with color-coded locations

---

## 🏪 Shop System

The shop generates a random selection of items from a predefined pool:

* **Consumables**: Healing and temporary attack buffs
* **Weapons**: Permanent attack upgrades, restricted by class

Gold is earned through gameplay and spent strategically to improve the character.

---

## 🧩 Riddle System

The game includes a riddle system used in specific quests or events. Each riddle:

* Has multiple choices
* Allows only one correct answer
* Is tracked to prevent repetition

---

## 🛠️ Technologies Used

* Java (Object-Oriented Programming)
* IntelliJ IDEA
* Java Collections (ArrayList)
* Console input/output (Scanner)
* ANSI escape codes for colored text

---

## 📁 Project Structure

The project is organized into clear packages:

* `Character` – Heroes and enemies
* `Game` – Core game logic and flow
* `Item` – Items, weapons, and consumables
* `Enums` – Enumerations for item types and classes
* `Quest` – Missions and challenges

---

## ▶️ How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA (or another Java IDE)
3. Run the `Main` class
4. Follow the instructions in the console

---

## 📌 Notes

* This project is console-based by design
* Focused on learning and applying Java OOP concepts
* No external libraries were used

---

## 👤 Author

Developed by **Sandro Draeger**
Academic Java Project
