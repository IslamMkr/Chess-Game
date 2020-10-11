package com.ismkr.chess_game.pieces

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import java.awt.Graphics
import java.awt.Point

enum class PieceColor {
    BLACK, WHITE
}

abstract class Piece {

    abstract val pos: Point

    abstract val color: PieceColor

    abstract val allMoves: MutableList<Cell>

    abstract fun moves(board: Board): List<Cell>

    abstract fun draw(g: Graphics)

}