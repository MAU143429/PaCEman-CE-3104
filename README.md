# 👾 PaCEman — Java Multiplayer Game

## 📘 Overview

**PaCEman** is a multiplayer clone of the classic Pac-Man game developed for the course CE3104 — Lenguajes, Compiladores e Intérpretes at Instituto Tecnológico de Costa Rica.

The project reimagines the iconic arcade game using Java for the game client and a custom C server for managing communication between players and spectators. The system supports real-time updates and was designed with a multi-user experience in mind.

---

## 🎮 Features

- 👤 **Single-player game mode** with all core mechanics of Pac-Man
- 👀 **Viewer mode** that lets others watch the live game session
- 🧠 Custom-built server in **C** to handle:
  - Client-server connections
  - Game state distribution
  - Player/viewer synchronization
- 🖥️ Java client with classic-style graphics and real-time controls
- 🛠️ Robust communication using low-level sockets

---

## 🧰 Technologies

- **Client**: Java (Swing for UI, Threads for concurrency)
- **Server**: C (sockets, file management, memory allocation)
- **Communication**: TCP/IP sockets over localhost or LAN
- **Platform**: Cross-platform (tested on Windows and Linux)

