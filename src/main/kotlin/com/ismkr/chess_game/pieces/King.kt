package com.ismkr.chess_game.pieces

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import com.ismkr.chess_game.utils.R
import java.awt.Graphics
import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

class King(override val pos: Point, override val color: PieceColor) : Piece() {

    private val fileName = if (color == PieceColor.BLACK) "black_king.png" else "white_king.png"

    override val allMoves = mutableListOf<Cell>()

    override fun moves(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()

        for (x in pos.x-1..pos.x+1) {
            for (y in pos.y-1..pos.y+1) {
                if (!(x == pos.x && y == pos.y)) {
                    val cell = board.getCellOrNull(x, y)
                    if (cell != null) {
                        if (cell.piece == null) moves.add(cell)
                        else {
                            if (cell.piece!!.color != color) moves.add(cell)
                        }
                    }
                }
            }
        }

        allMoves.clear()
        allMoves.addAll(moves)

        return moves.toList()
    }

    override fun draw(g: Graphics) {
        val piecePNG = ImageIO.read(File(R.getImageByName(fileName)))
        g.drawImage(piecePNG, (pos.x * 80) + 10, (pos.y * 80) + 10, null)
    }

}