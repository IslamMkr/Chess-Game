package com.ismkr.chess_game.pieces

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import com.ismkr.chess_game.utils.R
import java.awt.Graphics
import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

class Bishop(override val pos: Point, override val color: PieceColor) : Piece() {

    private val fileName = if (color == PieceColor.BLACK) "black_bishop.png" else "white_bishop.png"

    override val allMoves = mutableListOf<Cell>()

    override fun moves(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()

        moves.addAll(firstDiagonal(board))
        moves.addAll(secondDiagonal(board))

        allMoves.clear()
        allMoves.addAll(moves)

        return moves.toList()
    }

    override fun draw(g: Graphics) {
        val piecePNG = ImageIO.read(File(R.getImageByName(fileName)))
        g.drawImage(piecePNG, (pos.x * 80) + 10, (pos.y * 80) + 10, null)
    }

    // TODO: Optimise the solution...
    private fun firstDiagonal(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()
        for (i in 1..7) {
            if (pos.y + i > 7 || pos.x + i > 7) break
            val cell = board.getCellOrNull(Point(pos.x + i, pos.y + i))
            if (cell != null) {
                if (cell.piece != null) {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
            if (cell != null) moves.add(cell)
        }
        for (i in 1..7) {
            if (pos.y - i < 0 || pos.x - i < 0) break
            val cell = board.getCellOrNull(Point(pos.x - i, pos.y - i))
            if (cell != null) {
                if (cell.piece != null) {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
            if (cell != null) moves.add(cell)
        }

        return moves.toList()
    }

    // TODO: Optimise the solution...
    private fun secondDiagonal(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()
        for (i in 1..7) {
            if (pos.y + i > 7 || pos.x - i < 0) break
            val cell = board.getCellOrNull(Point(pos.x - i, pos.y + i))
            if (cell != null) {
                if (cell.piece != null) {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
            if (cell != null) moves.add(cell)
        }
        for (i in 1..7) {
            if (pos.y - i < 0 || pos.x + i > 7) break
            val cell = board.getCellOrNull(Point(pos.x + i, pos.y - i))
            if (cell != null) {
                if (cell.piece != null) {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
            if (cell != null) moves.add(cell)
        }

        return moves.toList()
    }

}