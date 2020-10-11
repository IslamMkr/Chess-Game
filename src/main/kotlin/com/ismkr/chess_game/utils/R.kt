package com.ismkr.chess_game.utils

import java.lang.Exception
import java.net.URI

abstract class R {

    companion object {

        // Resources folder
        private val res = ClassLoader.getSystemClassLoader()

        private const val IMG = "img\\"

        /**
         * Returns the file's URI if the file exists
         * otherwise it throws an Exception
         *
         * @param name -> name of the image file
         * @return @URI -> the uri of the file
         */
        fun getImageByName(name: String): URI {
            val resUrl = res.getResource(IMG + name) ?: throw Exception("ResourceNotFound")
            return resUrl.toURI()
        }

    }

}