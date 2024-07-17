# Tank Game

## 簡介
這是一款經典的坦克小遊戲，你可以控制坦克在遊戲中移動和射擊，擊敗敵人並躲避障礙。遊戲具有多種有趣的功能和挑戰，適合各個年齡層的玩家。

## 技術細節
- **多執行緒 (Multithreading)**: 
  - 使用多執行緒技術來管理坦克、彈藥和爆炸效果的動作和狀態更新。 
  - 每個坦克和彈藥都在獨立的執行緒中運行，確保遊戲的流暢運行。
- **物件導向設計 (Object-Oriented Design)**: 
  - 使用物件導向的設計方法，將不同的遊戲元素（如坦克、彈藥、牆體）封裝成類，增強代碼的可讀性和可維護性。
- **碰撞檢測 (Collision Detection)**: 
  - 實現了坦克、彈藥和牆體之間的碰撞檢測，確保遊戲的物理效果真實可信。
- **圖形繪製 (Graphics Rendering)**: 
  - 使用 Java 的圖形繪製功能來顯示遊戲中的坦克、彈藥、牆體和爆炸效果，提供豐富的視覺體驗。

## 遊戲特點
- **彈藥 (Ammo)**: 代表坦克發射的彈藥，包含彈藥的基本屬性如位置和速度，並控制彈藥的移動和狀態變化。
- **炸彈 (Bomb)**: 代表遊戲中的炸彈效果，包含炸彈的位置及生命週期，控制炸彈的爆炸效果和顯示。
- **遊戲數據 (GameData)**: 管理遊戲數據和狀態，負責初始化遊戲參數和配置，並提供數據存取的方法。
- **玩家坦克 (Hero)**: 代表玩家控制的坦克，繼承自 `Tank` 類，包含玩家坦克的專有屬性和行為。
- **基本坦克 (Tank)**: 基本的坦克類別，包含坦克的屬性如位置、方向、速度和狀態，提供坦克的移動和射擊功能。
- **遊戲主入口 (TankGame)**: 遊戲的主入口，初始化遊戲視窗和主要邏輯，控制遊戲的整體運行流程。
- **牆體 (Wall)**: 代表遊戲中的牆體，包含牆體的位置和狀態，提供牆體的顯示和碰撞檢測。
- **敵人坦克 (Enemy)**: 代表遊戲中的敵人坦克，繼承自 `Tank` 類，包含敵人坦克的專有屬性和行為，提供敵人坦克的自動移動和攻擊功能。
- **遊戲面板 (MyPanel)**: 遊戲的主要面板，負責遊戲的繪圖和刷新，處理遊戲的各種事件和交互。

## 玩法介紹
1. **移動**: 使用方向鍵（上、下、左、右）控制玩家坦克的移動。
2. **射擊**: 按下空格鍵發射彈藥，擊敗敵人坦克。
3. **躲避障礙**: 遊戲中的牆體和敵人彈藥都是障礙物，玩家需要靈活躲避。
4. **擊敗敵人**: 玩家需要擊敗所有敵人坦克才能通過關卡。
5. **生命值**: 玩家坦克有一定的生命值，被敵人攻擊會減少生命值，生命值歸零則遊戲結束。
6. **得分系統**: 每擊敗一個敵人坦克會獲得分數，挑戰更高的分數吧！

## 如何運行
1. 克隆此倉庫到你的本地機器。
2. 在你的開發環境中打開項目。
3. 運行 `TankGame.java` 來啟動遊戲。

## 貢獻
歡迎任何人貢獻代碼，提交 Pull Request 或提出問題。我們非常感謝您的幫助！

------------------------------------------------------------------------------------------------------------------------------------------

# Tank Game

## Introduction
This is a classic tank game where you control a tank to move and shoot, defeat enemies, and avoid obstacles. The game offers various interesting features and challenges suitable for players of all ages.

## Technical Details
- **Multithreading**: 
  - Utilizes multithreading technology to manage the actions and state updates of tanks, ammunition, and explosion effects. 
  - Each tank and ammunition run in separate threads to ensure smooth gameplay.
- **Object-Oriented Design**: 
  - Uses object-oriented design methodology to encapsulate different game elements (such as tanks, ammunition, walls) into classes, enhancing code readability and maintainability.
- **Collision Detection**: 
  - Implements collision detection between tanks, ammunition, and walls to ensure realistic physical effects in the game.
- **Graphics Rendering**: 
  - Uses Java's graphics rendering capabilities to display tanks, ammunition, walls, and explosion effects in the game, providing a rich visual experience.
  - 
## Game Features
- **Ammo**: Represents the ammunition fired by tanks, including basic attributes like position and speed, and controls the movement and state changes of the ammunition.
- **Bomb**: Represents the bomb effects in the game, including the position and lifecycle of the bomb, and controls the explosion effects and display.
- **GameData**: Manages game data and state, responsible for initializing game parameters and configurations, and provides methods for data access.
- **Hero**: Represents the player-controlled tank, inheriting from the `Tank` class, including attributes and behaviors specific to the player's tank.
- **Tank**: Basic tank class, includes attributes such as position, direction, speed, and state, and provides movement and shooting functionalities.
- **TankGame**: The main entry point of the game, initializes the game window and main logic, controlling the overall game flow.
- **Wall**: Represents the walls in the game, includes position and state, and provides display and collision detection functionalities.
- **Enemy**: Represents the enemy tanks in the game, inheriting from the `Tank` class, including attributes and behaviors specific to enemy tanks, and provides automated movement and attack functionalities.
- **MyPanel**: The main game panel, responsible for drawing and refreshing the game, and handling various game events and interactions.

## How to Play
1. **Move**: Use the arrow keys (up, down, left, right) to control the movement of the player's tank.
2. **Shoot**: Press the spacebar to fire ammunition and defeat enemy tanks.
3. **Avoid Obstacles**: The walls and enemy ammunition in the game are obstacles that players need to avoid.
4. **Defeat Enemies**: Players need to defeat all enemy tanks to pass the level.
5. **Health Points**: The player's tank has a certain amount of health. Being attacked by enemies will reduce health, and the game ends when health reaches zero.
6. **Scoring System**: Players earn points for each enemy tank defeated. Challenge yourself to achieve higher scores!

## How to Run
1. Clone this repository to your local machine.
2. Open the project in your development environment.
3. Run `TankGame.java` to start the game.

## Contribution
Anyone is welcome to contribute code, submit pull requests, or raise issues. We greatly appreciate your help!
