package com.ismkr.chess_game.game

import com.ismkr.chess_game.board.Board
import com.ismkr.chess_game.board.Cell
import com.ismkr.chess_game.pieces.*
import java.awt.Point

class GameLogic(val board: Board) {

    // Saves the cell that has been clicked for next click to determine the moves to do
    private var clickedCell: Cell? = null

    private var player = PieceColor.WHITE

    fun mouseClicked(pos: Point) {
        val cell = board.getCellOrNull(pos)

        if (cell != null) manageLogic(cell)

        board.repaint()
    }

    /**
     * Manages the logic and movements of pieces
     *
     * @param cell -> clicked cell
     */
    private fun manageLogic(cell: Cell) {
        if (cell.piece?.color == player || cell.isTarget) {
            if (clickedCell == null) {
                markMoves(cell)
                clickedCell = cell
            } else {
                if (cell == clickedCell) {
                    removeMarkedMoves(cell)
                    clickedCell = null
                } else {
                    if (!cell.isTarget) {
                        removeMarkedMoves(clickedCell!!)
                        clickedCell = if (cell.piece == null) {
                            null
                        } else {
                            markMoves(cell)
                            cell
                        }
                    } else {
                        removeMarkedMoves(clickedCell!!)
                        cell.piece = getPiece(cell, clickedCell!!.piece)
                        clickedCell!!.piece = null
                        clickedCell = null
                        player = if (player == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
                    }
                }
            }
        } else if (!cell.isTarget) {
            if (clickedCell != null) {
                removeMarkedMoves(clickedCell!!)
                clickedCell = null
            }
        }
    }

    /**
     * Create the same piece as the param @{piece} with the param @{cell)'s
     * position and returns it
     *
     * @param cell -> cell that changes its piece
     * @param piece -> piece that moves
     * @return @Piece?
     */
    private fun getPiece(cell: Cell, piece: Piece?): Piece? =
        when(piece) {
            is Pawn     -> Pawn(cell.pos, piece.color)
            is Rook     -> Rook(cell.pos, piece.color)
            is Knight   -> Knight(cell.pos, piece.color)
            is Bishop   -> Bishop(cell.pos, piece.color)
            is King     -> King(cell.pos, piece.color)
            is Queen    -> Queen(cell.pos, piece.color)
            else        -> null
        }

    /**
     * Displays all the available moves for the piece in cell
     * @param cell -> clicked cell
     */
    private fun markMoves(cell: Cell) {
        if (cell.piece != null) {
            val moves = cell.piece!!.moves(board)
            moves.forEach { targetCell -> targetCell.isTarget = true }
        }
    }

    /**
     * Remove the marks from all the available moves for the piece in cell
     * @param cell -> clicked cell
     */
    private fun removeMarkedMoves(cell: Cell) {
        if (cell.piece != null) {
            val moves = cell.piece!!.moves(board)
            moves.forEach { targetCell -> targetCell.isTarget = false }
        }
    }

}