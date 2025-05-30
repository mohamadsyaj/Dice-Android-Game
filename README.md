🎲 Two-Player Math Dice Game
An Android game written in Kotlin where two players take turns rolling a virtual die, solving randomly generated arithmetic problems, and racing to be the first to score 20 points. The twist? A growing jackpot that can swing the match in one lucky turn!

📖 Gameplay at a Glance
Die Face	Action on Roll	Possible Points Earned*	Notes
1	Addition problem (0-99)	1 pt	—
2	Subtraction problem (0-99)	2 pts	—
3	Multiplication problem (0-20)	3 pts	—
4	Random +, –, or ×	4 pts → doubled (8 pts)	Earn double on correct answer
5	Instantly ends turn	0 pts	No problem generated
6	Addition problem (0-99)	Jackpot (starts at 5 pts)	Jackpot resets on win, grows on miss

*If the answer is wrong, the points add to the jackpot instead (except on a 5).

The first player to reach 20 points wins. A victory banner briefly appears, then the game automatically resets for a new round.

🏗️ Project Structure
app/
└─ src/main/
  ├─ java/com/example/project1/
  │ └─ MainActivity.kt ← all game logic & UI binding
  ├─ res/
  │ ├─ drawable/     ← dice1 … dice6.png (die faces)
  │ ├─ layout/
  │ │ └─ activity_main.xml
  │ └─ values/
  │   └─ strings.xml, themes.xml …
  └─ AndroidManifest.xml

Key Classes & Layout
File	Role
MainActivity.kt	Handles view binding, die rolls, problem generation, answer checking, score tracking, jackpot logic, and turn switching.
activity_main.xml	Simple vertical layout with an ImageView for the die, TextViews for scores, an EditText for answers, and two Buttons (dieButton, check).

🖥️ Getting Started
Clone the repo (or copy into a new Android Studio project).

Open in Android Studio Giraffe (or newer) with Kotlin 1.9+.

Ensure ViewBinding is enabled in build.gradle:

nginx
Copy
android {
    buildFeatures {
        viewBinding true
    }
}
Add six die-face images named dice1.png … dice6.png under app/src/main/res/drawable/.

Run on an emulator or physical device running API 21 (Lollipop) or higher.

🔧 Customization Ideas
Idea	Where to Change
Target score (default 20)	Add a WIN_SCORE constant in MainActivity
Jackpot starting value	jackpot initial value
Supported operations	rollDie() → problemGenerator() logic
Larger number ranges	Arguments min, max in problemGenerator()

🚀 Roadmap
Persistent high-score table (Room database)

Haptic feedback / sound effects on roll and correct answer

Timed mode (score as many points as possible in 90 seconds)

Accessibility improvements (TalkBack labels, larger fonts)

Dark/light theme toggle

📝 License
This project is released under the MIT License.
Feel free to fork, modify, and share—just keep the copyright notice.

Author
Name – Mohamad Syaj
Pull requests welcome! For major changes, please open an issue first to discuss what you would like to change.

Happy coding, and may the dice roll ever in your favor!
