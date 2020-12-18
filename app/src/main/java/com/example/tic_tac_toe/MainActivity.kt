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

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var ActivePlayer = 1
    var activePlayer : Boolean =  true
    var player1Win = 0
    var player2Win = 0
    var matchResult = 0
    var winnerResult : Boolean = false
    var count : Int = 0

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

        player1.clear()
        player2.clear()
        activePlayer = true
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
        playGame(btnKey, btnSelected)
    }

    private fun playGame(btnKey: Int, btnSelected: Button) {
        if (activePlayer)
        {
           btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.GREEN)
            player1.add(btnKey)
            activePlayer = false
                try {
                        checkWinner()
                        if (matchResult == 1){
                            updatePoint()
                        }else {
                            autoPlay()
                        }
                }catch (ex: Exception)
                {
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                }

            }
        else
        {
            if (matchResult == 1){

            }else {
                btnSelected.text = "O"
                btnSelected.setBackgroundColor(Color.RED)
                player2.add(btnKey)
                activePlayer = true
            }
        }
        btnSelected.isEnabled = false
        checkWinner()

    }

    private fun autoPlay() {
        var cellId: Int
        val empty = ArrayList<Int>()
        for (btnKey in 1..9) {
            if (player1.contains(btnKey) || player2.contains(btnKey))
            {

            }
            else
            {
                empty.add(btnKey)
            }
        }

        val r = Random()
        cellId = if (player1.size != 1) {
            getWeekMl(empty)
        }else {
            val randomIndex = r.nextInt(empty.size - 0) + 0
            empty[randomIndex]
        }

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

        playGame(cellId, btnSelect)
    }

    private fun getWeekMl(empty: ArrayList<Int>) : Int {
        val r = Random()
        val randomIndex = r.nextInt(empty.size - 0)+0
        if (player1.contains(1) && player1.contains(2)){
            return if (empty.contains(3)){
                3
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(1) && player1.contains(3)){
            return if (empty.contains(2)){
                //player2.add(2)
                2
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
         if (player1.contains(2) && player1.contains(3)){
             return if (empty.contains(1)){
                 //player2.add(1)
                 1
             }else{
                 //player2.add(randomIndex)
                 empty[randomIndex]
             }
        }
        if (player1.contains(1) && player1.contains(4)){
            //block 3
            return if (empty.contains(7)){
                7
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(1) && player1.contains(7)){
            return if (empty.contains(4)){
                //player2.add(2)
                4
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(4) && player1.contains(7)){
            return if (empty.contains(1)){
                //player2.add(1)
                1
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(1) && player1.contains(5)){
            //block 3
            return if (empty.contains(9)){
                9
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(1) && player1.contains(9)){
            return if (empty.contains(5)){
                //player2.add(2)
                5
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(5) && player1.contains(9)){
            return if (empty.contains(1)){
                //player2.add(1)
                1
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(2) && player1.contains(5)){
            //block 3
            return if (empty.contains(8)){
                8
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(2) && player1.contains(8)){
            return if (empty.contains(5)){
                //player2.add(2)
                5
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(5) && player1.contains(8)){
            return if (empty.contains(2)){
                //player2.add(1)
                2
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
         //
        if (player1.contains(3) && player1.contains(6)){
            //block 3
            return if (empty.contains(9)){
                9
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(3) && player1.contains(9)){
            return if (empty.contains(6)){
                //player2.add(2)
                6
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(6) && player1.contains(9)){
            return if (empty.contains(3)){
                //player2.add(1)
                3
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(3) && player1.contains(5)){
            //block 3
            return if (empty.contains(7)){
                7
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(5) && player1.contains(7)){
            return if (empty.contains(3)){
                //player2.add(2)
                3
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(3) && player1.contains(7)){
            return if (empty.contains(5)){
                //player2.add(1)
                5
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(4) && player1.contains(5)){
            //block 3
            return if (empty.contains(6)){
                6
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(4) && player1.contains(6)){
            return if (empty.contains(5)){
                //player2.add(2)
                5
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(5) && player1.contains(6)){
            return if (empty.contains(4)){
                //player2.add(1)
                4
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        if (player1.contains(7) && player1.contains(9)){
            //block 3
            return if (empty.contains(8)){
                8
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }

        }
        if (player1.contains(7) && player1.contains(8)){
            return if (empty.contains(9)){
                //player2.add(2)
                9
            }else{
                player2.add(randomIndex)
                empty[randomIndex]
            }
        }
        return if (player1.contains(8) && player1.contains(9)){
            if (empty.contains(7)){
                7
            }else{
                //player2.add(randomIndex)
                empty[randomIndex]
            }
        } else{
            empty[randomIndex]
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkWinner()
    {
        var winner = -1

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
        {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
        {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
        {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
        {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
        {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
        {
            winner = 2
        }

        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
        {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
        {
            winner = 2
        }

        //col2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
        {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
        {
            winner = 2
        }

        //col3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
        {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9))
        {
            winner = 2
        }

        //cross1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
        {
            winner = 1
    }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
        {
            winner = 2

        }

        //cross2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
        {
            winner = 1
        }
         if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
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

    @SuppressLint("SetTextI18n")
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

        var point2 = player2Win/2
        var point1 = player1Win/2
        if (player1Win == 2 && point1 == 0){
            point1 = 1
        }
        if (player1Win >2 && point1 == 1){
            point1 = 2
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