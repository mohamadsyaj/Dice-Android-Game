package com.example.project1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPlayer = 1
    private var player1Score = 0
    private var player2Score = 0
    private var jackpot = 5
    private var correctAnswer: Int? = null
    private var currentDieRoll: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateInterface()

        binding.dieButton.setOnClickListener {
            rollDie()
        }

        binding.check.setOnClickListener {
            checkAnswer()
        }

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //die roll function
    @SuppressLint("DiscouragedApi")
    private fun rollDie() {
        currentDieRoll = Random.nextInt(1, 7)
        val imageName = "dice$currentDieRoll"
        val resourceID = resources.getIdentifier(imageName, "drawable", packageName)
        binding.ivDie.setImageResource(resourceID)

        when (currentDieRoll) {
            1 -> problemGenerator("+", 0, 99)
            2 -> problemGenerator("-", 0, 99)
            3 -> problemGenerator("*", 0, 20)
            4 -> {
                val operations = listOf("+", "-", "*")
                val randomOperator = operations.random()
                problemGenerator(randomOperator, if (randomOperator == "*") 0 else 0, if (randomOperator == "*") 20 else 99)
            }
            5 -> {
                clearOnTurn()
                playerTurn()
            }
            6 -> problemGenerator("+", 0, 99)
        }
    }//end roll die function

    //problem generator
    @SuppressLint("SetTextI18n")
    private fun problemGenerator(operator: String, min: Int, max: Int) {
        val num1 = Random.nextInt(min, max + 1)
        val num2 = Random.nextInt(min, max + 1)

        correctAnswer = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            else -> null
        }

        binding.equation.text = "$num1 $operator $num2"
        binding.playerAnswer.text.clear()
    }//end problem generator function

    // answer check function
    private fun checkAnswer() {
        if (currentDieRoll == null || correctAnswer == null) {
            return
        }

        val playerInput = binding.playerAnswer.text.toString().toIntOrNull() ?: return

        if (playerInput == correctAnswer) {
            var pointsEarned = currentDieRoll!!
            if (currentDieRoll == 4) pointsEarned *= 2
            if (currentDieRoll == 6) pointsEarned = jackpot

            updateScore(pointsEarned)
        } else {
            if (currentDieRoll == 6) jackpot = 5
            else jackpot += currentDieRoll!!
        }

        playerTurn()
    }// end answer check function

    //update score function
    private fun updateScore(points: Int) {
        if (currentPlayer == 1) {
            player1Score += points
        } else {
            player2Score += points
        }
        jackpot = 5
        updateInterface()
    }//end update score function

    //player turn function
    private fun playerTurn() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        clearOnTurn()
        updateInterface()
    }//end player turn function

    //update Interface function
    @SuppressLint("SetTextI18n")
    private fun updateInterface() {
        binding.currentPlayerTurn.text = "Player $currentPlayer"
        binding.player1Score.text = player1Score.toString()
        binding.player2Score.text = player2Score.toString()
        binding.jackScore.text = jackpot.toString()
        if (player1Score >= 20 || player2Score >= 20) {
            if(player1Score >= 20){
                binding.winner1Box.text = "Player 1 wins"
            }
            if(player2Score >= 20){
                binding.winner1Box.text = "Player 2 wins"
            }
            resetGame()
        }
    }//end update interface function

    //clear on turn function
    private fun clearOnTurn() {
        binding.equation.text = ""
        binding.playerAnswer.text.clear()
        correctAnswer = null
        currentDieRoll = null
    }//end of clear on turn function

    //reset game function
    private fun resetGame() {
        player1Score = 0
        player2Score = 0
        jackpot = 5
        currentPlayer = 1
        clearOnTurn()
        updateInterface()
    }//end rest game function

}//end main function
