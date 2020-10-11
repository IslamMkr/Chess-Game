package com.ismkr.chess_game.game

import com.ismkr.chess_game.board.Board
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.Point
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame

class GameFrame : JFrame() , MouseListener {

    private val gameTitle = "Chess"
    private val gameWidth = 656
    private val gameHeight = 679

    private val board = Board()

    init {
        title = gameTitle
        isResizable = false
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(gameWidth, gameHeight)
        setLocationRelativeTo(null)

        board.addMouseListener(this)

        add(board)

        isVisible = true
    }


    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mousePressed(e: MouseEvent?) {}

    override fun mouseClicked(e: MouseEvent?) {
        // Starting a coroutine when mouse is clicked
        GlobalScope.launch {
            if (e != null) {
                val pos = Point(e.point.x / 80, e.point.y / 80)
                board.mouseClicked(pos)
            }
        }
    }

}

fun main() {
    GameFrame()
}