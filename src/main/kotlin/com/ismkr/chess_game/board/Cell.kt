package com.ismkr.chess_game.board

import com.ismkr.chess_game.pieces.Piece
import java.awt.Color
import java.awt.Graphics
import java.awt.Point

class Cell(val pos: Point) {

    private val width = 80

    private val color = when {
        pos.y % 2 == 0  -> if (pos.x % 2 == 0) Color.lightGray else Color.gray
        else            -> if (pos.x % 2 != 0) Color.lightGray else Color.gray
    }

    var piece: Piece? = null

    // Determines if this cell is a target (available) for a certain piece to move to
    var isTarget = false

    /**
     * Draws the representation of the cell and what belongs to it
     *
     * @param g -> Graphics instance
     */
    fun draw(g: Graphics) {
        g.color = color
        g.fillRect(pos.x * width, pos.y * width, width, width)

        piece?.draw(g)

        if (isTarget) {
            g.color = Color.GREEN
            g.fillOval((pos.x * width) + 30, (pos.y * width) + 30, 20, 20)
        }
    }

}