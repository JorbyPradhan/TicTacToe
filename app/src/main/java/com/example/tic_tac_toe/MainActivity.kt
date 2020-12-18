package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var Player1 = ArrayList<Int>()
    var Player2 = ArrayList<Int>()
    var ActivePlayer = 1
    var player1Win = 0
    var player2Win = 0
    var matchResult = 0
    //var setPlayer = 1

    //private val player1Turn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_0_0.setOnClickListener(this)
        btn_0_1.setOnClickListener(this)
        btn_0_2.setOnClickListener(this)
        btn_1_0.setOnClickListener(this)
        btn_1_1.setOnClickListener(this)
        btn_1_2.setOnClickListener(this)
        btn_2_0.setOnClickListener(this)
        btn_2_1.setOnClickListener(this)
        btn_2_2.setOnClickListener(this)
        btn_reset.setOnClickListener {
            reset()
        }
    }

    private fun reset() {
        btn_0_0.setBackgroundResource(android.R.drawable.btn_default)
        btn_0_1.setBackgroundResource(android.R.drawable.btn_default)
        btn_0_2.setBackgroundResource(android.R.drawable.btn_default)
        btn_1_0.setBackgroundResource(android.R.drawable.btn_default)
        btn_1_1.setBackgroundResource(android.R.drawable.btn_default)
        btn_1_2.setBackgroundResource(android.R.drawable.btn_default)
        btn_2_0.setBackgroundResource(android.R.drawable.btn_default)
        btn_2_1.setBackgroundResource(android.R.drawable.btn_default)
        btn_2_2.setBackgroundResource(android.R.drawable.btn_default)

        btn_0_0.text = ""
        btn_0_1.text = ""
        btn_0_2.text = ""
        btn_1_0.text = ""
        btn_1_1.text = ""
        btn_1_2.text = ""
        btn_2_0.text = ""
        btn_2_1.text = ""
        btn_2_2.text = ""

        Player1.clear()
        Player2.clear()
        ActivePlayer = 1
        matchResult = 0

        btn_0_0.isEnabled = true
        btn_0_1.isEnabled = true
        btn_0_2.isEnabled = true
        btn_1_0.isEnabled = true
        btn_1_1.isEnabled = true
        btn_1_2.isEnabled = true
        btn_2_0.isEnabled = true
        btn_2_1.isEnabled = true
        btn_2_2.isEnabled = true

     //   setPlayer = 1
        //PVP.setBackgroundColor(Color.CYAN)
       // PVC.setBackgroundColor(android.R.drawable.btn_default)
    }

    override fun onClick(v: View?) {
        val btnSelected: Button = v as Button
        var btnKey = 0
        when(btnSelected.id) {
            R.id.btn_0_0 -> btnKey = 1
            R.id.btn_0_1 -> btnKey = 2
            R.id.btn_0_2 -> btnKey = 3

            R.id.btn_1_0 -> btnKey = 4
            R.id.btn_1_1 -> btnKey = 5
            R.id.btn_1_2 -> btnKey = 6

            R.id.btn_2_0 -> btnKey = 7
            R.id.btn_2_1 -> btnKey = 8
            R.id.btn_2_2 -> btnKey = 9
        }
        playGame(btnKey,btnSelected)
    }

    private fun playGame(btnKey: Int, btnSelected: Button) {
        if (ActivePlayer == 1)
        {
           btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.GREEN)
            Player1.add(btnKey)
            ActivePlayer = 2
                try {
                        checkWinner()
                        autoPlay()

                }catch (ex:Exception)
                {
                    Toast.makeText(this,"Game Over",Toast.LENGTH_SHORT).show()
                }

            }
        else
        {
            if (matchResult == 1){

            }else {
                btnSelected.text = "O"
                btnSelected.setBackgroundColor(Color.RED)
                Player2.add(btnKey)
                ActivePlayer = 1
            }
        }
        btnSelected.isEnabled = false
        checkWinner()

    }

    private fun autoPlay() {
        val empty = ArrayList<Int>()
        for (btnKey in 1..9) {
            if (Player1.contains(btnKey) || Player2.contains(btnKey))
            {

            }
            else
            {
                empty.add(btnKey)
            }
        }

        val r = Random()
        val randomIndex = r.nextInt(empty.size-0)+0
        val cellId = empty[randomIndex]

        val btnSelect:Button?
        btnSelect = when(cellId) {
            1 -> btn_0_0
            2 -> btn_0_1
            3 -> btn_0_2
            4 -> btn_1_0
            5 -> btn_1_1
            6 -> btn_1_2
            7 -> btn_2_0
            8 -> btn_2_1
            9 -> btn_2_2
            else -> btn_0_0
        }

        playGame(cellId,btnSelect)
    }

    @SuppressLint("SetTextI18n")
    private fun checkWinner()
    {
        var winner = -1

        //row1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3))
        {
            winner = 2
        }

        //row2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6))
        {
            winner = 1
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6))
        {
            winner = 2
        }

        //row3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9))
        {
            winner = 2
        }

        //col1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7))
        {
            winner = 2
        }

        //col2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8))
        {
            winner = 1
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8))
        {
            winner = 2
        }

        //col3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9))
        {
            winner = 2
        }

        //cross1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9))
        {
            winner = 1
    }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9))
        {
            winner = 2

        }

        //cross2
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7))
        {
            winner = 1
        }
         if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            winner = 2

        }
        if (winner != -1) {
            updateWinner(winner)
        }

    }

    private fun updateWinner(winner: Int) {
            if (winner == 1)
            {
                matchResult = 1
                player1WinGame()
                stopTouch()

            }else{
                matchResult = 2
                player2WinGame()
                stopTouch()
            }
    }

    private fun player1WinGame(){
        player1Win++
        Toast.makeText(this, "Player One Won!!", Toast.LENGTH_SHORT).show()
        updatePoint()
        //reset()
    }

    private fun player2WinGame(){
        player2Win++
        Toast.makeText(this, "Player Two Won!!", Toast.LENGTH_SHORT).show()
        updatePoint()
        //reset()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePoint() {
        var point1 = player1Win/3
        var point2 = player2Win/2
        if (player1Win == 1 && point1 == 0){
            point1 = 1
        }
        if (player2Win == 1 && point2 == 0){
            point2 = 1
        }
        txt_player_one.text = "Player 1 : $point1"
        txt_player_two.text= "Player 2 : $point2"
    }


    private fun stopTouch()
    {
        btn_0_0.isEnabled = false
        btn_0_1.isEnabled = false
        btn_0_2.isEnabled = false
        btn_1_0.isEnabled = false
        btn_1_1.isEnabled = false
        btn_1_2.isEnabled = false
        btn_2_0.isEnabled = false
        btn_2_1.isEnabled = false
        btn_2_2.isEnabled = false
    }
}