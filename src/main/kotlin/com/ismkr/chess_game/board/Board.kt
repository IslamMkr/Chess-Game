package com.ismkr.chess_game.board

import com.ismkr.chess_game.game.GameLogic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.Graphics
import java.awt.Point
import javax.swing.JPanel

class Board : JPanel() {

    private val cells = (0..7).flatMap  {  i -> (0..7).map { j -> Cell(Point(i, j)) } }
    private val initializer = BoardInitializer(this)
    private val logic = GameLogic(this)

    init {
        // A coroutine in which it places all pieces in their initial cells
        GlobalScope.launch {
            initializer.placePieces()
        }
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        cells.forEach { cell ->
            if (g != null) cell.draw(g)
        }
    }

    // fun getAllCells(): List<Cell> = cells

    /**
     * @param i
     * @param j
     * @return @Cell?
     */
    fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i !in 0..7 || j !in 0..7) return null
        return cells.first { cell -> cell.pos == Point(i, j) }
    }

    /**
     * @param pos
     * @return @Cell?
     */
    fun getCellOrNull(pos: Point): Cell? {
        if (pos.x !in 0..7 || pos.y !in 0..7) return null
        return cells.first { cell -> cell.pos == pos }
    }

    /**
     * Passes the position of the click to the @GameLogic instance
     *
     * @param pos -> position of the click
     */
    fun mouseClicked(pos: Point) {
        logic.mouseClicked(pos)
    }

}