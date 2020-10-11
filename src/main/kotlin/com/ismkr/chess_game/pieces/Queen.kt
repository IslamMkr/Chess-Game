package com.ismkr.chess_game.pieces

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import com.ismkr.chess_game.utils.R
import java.awt.Graphics
import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

class Queen(override val pos: Point, override val color: PieceColor) : Piece() {

    private val fileName = if (color == PieceColor.BLACK) "black_queen.png" else "white_queen.png"

    override val allMoves = mutableListOf<Cell>()

    override fun moves(board: Board): List<Cell> {
        val moves = mutableListOf<Cell>()

        moves.addAll(firstDiagonal(board))
        moves.addAll(secondDiagonal(board))
        moves.addAll(line(board))
        moves.addAll(column(board))

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

    private fun line(board: Board): Collection<Cell> {
        val moves = mutableListOf<Cell>()

        for (x in pos.x-1 downTo 0) {
            if (x < 0) break
            val cell = board.getCellOrNull(x, pos.y)
            if (cell != null) {
                if (cell.piece == null) moves.add(cell)
                else {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
        }

        for (x in pos.x+1..7) {
            if (x > 7) break
            val cell = board.getCellOrNull(x, pos.y)
            if (cell != null) {
                if (cell.piece == null) moves.add(cell)
                else {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
        }

        return moves.toList()
    }

    private fun column(board: Board): Collection<Cell> {
        val moves = mutableListOf<Cell>()

        for (y in pos.y-1 downTo 0) {
            if (y < 0) break
            val cell = board.getCellOrNull(pos.x, y)
            if (cell != null) {
                if (cell.piece == null) moves.add(cell)
                else {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
        }

        for (y in pos.y+1..7) {
            if (y > 7) break
            val cell = board.getCellOrNull(pos.x, y)
            if (cell != null) {
                if (cell.piece == null) moves.add(cell)
                else {
                    if (cell.piece!!.color != color) moves.add(cell)
                    break
                }
            }
        }

        return moves.toList()
    }

}