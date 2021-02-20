package com.example.tarea1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var tiro = 0
    var player1_counter = 0
    var player2_counter = 0
    var tiros = 0
    val max_tiros = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tira_dado()
        rol_dice.text = "Player 1 turn"
    }

    fun tira_dado() {
        rol_dice.setOnClickListener {
            tiro = Random.nextInt(1, 6)
            tiros ++
            mensaje.text = "$tiro"
            game()
        }
    }

    fun game(){
        update_text()
        winner_label.text = "Here goes the winner!"
        when {
            tiros % 2 == 1 -> {
                player1_counter += tiro
                update_text()
                rol_dice.text = "Player 2 turn"
            }
            tiros >= max_tiros -> {
                player2_counter += tiro
                update_text()
                game_over()
            }
            else -> {
                player2_counter += tiro
                update_text()
                rol_dice.text = "Player 1 turn"
            }
        }
    }

    fun update_text(){
        player1_score.text = "$player1_counter"
        player2_score.text = "$player2_counter"
    }

    fun game_over(){
        rol_dice.text = "Fin de partida"

        when {
            player1_counter > player2_counter -> {
                winner_label.text = "Player 1 won with $player1_counter!"
            }
            player1_counter < player2_counter -> {
                winner_label.text = "Player 2 won with $player2_counter!"
            }
            else -> {
                winner_label.text = "It's a tie!"
            }
        }

        rol_dice.text = "Restart game?"
        rol_dice.setOnClickListener{
            recreate()
        }
    }
}