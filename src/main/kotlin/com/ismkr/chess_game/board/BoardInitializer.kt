package com.ismkr.chess_game.board

import com.ismkr.chess_game.pieces.*
import java.awt.Point

class BoardInitializer(val board: Board) {

    /**
     * Places each piece in its initial place
     */
    fun placePieces() {
        (0..7).forEach { i ->
            board.getCellOrNull(i, 1)?.piece = Pawn(Point(i, 1), PieceColor.BLACK)
            board.getCellOrNull(i, 6)?.piece = Pawn(Point(i, 6), PieceColor.WHITE)

            setPieces(i)
        }
    }

    private fun setPieces(i: Int) =
        when (i) {
            0, 7    -> {
                board.getCellOrNull(i, 0)?.piece = Rook(Point(i, 0), PieceColor.BLACK)
                board.getCellOrNull(i, 7)?.piece = Rook(Point(i, 7), PieceColor.WHITE)
            }
            1, 6    -> {
                board.getCellOrNull(i, 0)?.piece = Knight(Point(i, 0), PieceColor.BLACK)
                board.getCellOrNull(i, 7)?.piece = Knight(Point(i, 7), PieceColor.WHITE)
            }
            2, 5    -> {
                board.getCellOrNull(Point(i, 0))?.piece = Bishop(Point(i, 0), PieceColor.BLACK)
                board.getCellOrNull(Point(i, 7))?.piece = Bishop(Point(i, 7), PieceColor.WHITE)
            }
            3       -> {
                board.getCellOrNull(Point(i, 0))?.piece = Queen(Point(i, 0), PieceColor.BLACK)
                board.getCellOrNull(Point(i, 7))?.piece = Queen(Point(i, 7), PieceColor.WHITE)
            }
            else    -> {
                board.getCellOrNull(Point(i, 0))?.piece = King(Point(i, 0), PieceColor.BLACK)
                board.getCellOrNull(Point(i, 7))?.piece = King(Point(i, 7), PieceColor.WHITE)
            }
        }

}