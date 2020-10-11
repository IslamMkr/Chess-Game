package com.ismkr.chess_game.pieces

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import com.ismkr.chess_game.utils.R
import java.awt.Graphics
import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

class Pawn(override val pos: Point, override val color: PieceColor) : Piece() {

    private val fileName = if (color == PieceColor.BLACK) "black_pawn.png" else "white_pawn.png"

    override val allMoves = mutableListOf<Cell>()

    /**
     * Calculates available moves
     * @param @Board the board
     * @return List<Cell> a list of cells
     */
    override fun moves(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()

        var y = if (color == PieceColor.BLACK) 1 else -1

        if (pos.y in 1..6 && pos.x in 1..6) {
            (-1..1 step 2).forEach { i ->
                val piece = board.getCellOrNull(pos.x + i, pos.y + y)?.piece
                if (piece != null && piece.color != color) moves.add(board.getCellOrNull(pos.x + i, pos.y + y)!!)
            }
        }

        if (pos.y < 7 && board.getCellOrNull(pos.x, pos.y + y)?.piece == null) {
            val cell = board.getCellOrNull(pos.x, pos.y + y)
            if (cell != null) moves.add(cell)
        }

        y += if (color == PieceColor.BLACK) 1 else -1

        if ((pos.y == 1 || pos.y == 6) && board.getCellOrNull(pos.x, pos.y + y)?.piece == null) {
            val cell = board.getCellOrNull(pos.x, pos.y + y)
            if (cell != null) moves.add(cell)
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