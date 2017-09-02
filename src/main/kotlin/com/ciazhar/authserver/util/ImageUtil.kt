package com.ciazhar.authserver.util

import javax.imageio.ImageIO
import java.awt.*
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern

object ImageUtil {

    private val IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)"

    @Throws(IOException::class)
    fun crop(bais: ByteArrayInputStream, width: Int, height: Int): ByteArrayOutputStream {
        val src = ImageIO.read(bais)
        val clipping = crop(src, width, height)
        val baos = ByteArrayOutputStream()
        ImageIO.write(clipping, "JPG", baos)
        return baos
    }

    @Throws(IOException::class)
    fun crop(src: BufferedImage, width: Int, height: Int): BufferedImage {
        val x = src.width / 2 - width / 2
        val y = src.height / 2 - height / 2

        val clipping = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)// src.getType());
        val area = clipping.graphics.create() as Graphics2D
        area.drawImage(src, 0, 0, clipping.width, clipping.height, x, y, x + clipping.width,
                y + clipping.height, null)
        area.dispose()

        return clipping
    }

    @Throws(IOException::class)
    fun smartCrop(bais: ByteArrayInputStream, width: Int, height: Int): ByteArrayOutputStream {
        val src = ImageIO.read(bais)

        var scale: Float?
        if (src.width > src.height) {
            scale = java.lang.Float.valueOf(height.toFloat()) / java.lang.Float.valueOf(src.height.toFloat())
            if (src.width * scale < width) {
                scale = java.lang.Float.valueOf(width.toFloat()) / java.lang.Float.valueOf(src.width.toFloat())
            }
        } else {
            scale = java.lang.Float.valueOf(width.toFloat()) / java.lang.Float.valueOf(src.width.toFloat())
            if (src.height * scale < height) {
                scale = java.lang.Float.valueOf(height.toFloat()) / java.lang.Float.valueOf(src.height.toFloat())
            }
        }

        var temp = scale(src, java.lang.Float.valueOf(src.width * scale).toInt(),
                java.lang.Float.valueOf(src.height * scale).toInt())

        temp = crop(temp, width, height)

        val baos = ByteArrayOutputStream()
        ImageIO.write(temp, "JPG", baos)

        return baos
    }

    @Throws(IOException::class)
    fun smartCrop(src: BufferedImage, width: Int, height: Int): BufferedImage {
        var scale: Float?
        if (src.width > src.height) {
            scale = java.lang.Float.valueOf(height.toFloat()) / java.lang.Float.valueOf(src.height.toFloat())
            if (src.width * scale < width) {
                scale = java.lang.Float.valueOf(width.toFloat()) / java.lang.Float.valueOf(src.width.toFloat())
            }
        } else {
            scale = java.lang.Float.valueOf(width.toFloat()) / java.lang.Float.valueOf(src.width.toFloat())
            if (src.height * scale < height) {
                scale = java.lang.Float.valueOf(height.toFloat()) / java.lang.Float.valueOf(src.height.toFloat())
            }
        }

        var temp = scale(src, java.lang.Float.valueOf(src.width * scale).toInt(),
                java.lang.Float.valueOf(src.height * scale).toInt())

        temp = crop(temp, width, height)
        return temp
    }

    @Throws(IOException::class)
    fun scale(bais: ByteArrayInputStream, width: Int, height: Int): ByteArrayOutputStream {
        val src = ImageIO.read(bais)
        val dest = scale(src, width, height)
        val baos = ByteArrayOutputStream()
        ImageIO.write(dest, "JPG", baos)
        return baos
    }

    @Throws(IOException::class)
    fun scale(src: BufferedImage, width: Int, height: Int): BufferedImage {
        val dest = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g = dest.createGraphics()
        val at = AffineTransform.getScaleInstance(width.toDouble() / src.width,
                height.toDouble() / src.height)
        g.drawRenderedImage(src, at)
        return dest
    }

    /**
     * Validate image with regular expression
     *
     * @param image
     * image for validation
     * @return true valid image, false invalid image
     */
    fun validate(image: String): Boolean {
        val pattern = Pattern.compile(IMAGE_PATTERN)
        val matcher = pattern.matcher(image)
        return matcher.matches()

    }
}
