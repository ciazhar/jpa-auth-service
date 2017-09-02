package com.ciazhar.authserver.service

import com.ciazhar.authserver.util.ImageUtil
import org.apache.commons.io.FilenameUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException

@Service
class UploadService {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${upload.data.dir}")
    private val dataDir: String? = null

    /**
     * @param folderName
     * @param upload
     * @param createThumbnail
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun uploadImage(folderName: String, upload: MultipartFile, createThumbnail: Boolean): String? {
        return uploadImage(folderName, upload.originalFilename, upload, createThumbnail)
    }

    @Throws(IOException::class)
    fun uploadPostVenuePhoto(name: String, upload: MultipartFile, request: HttpServletRequest): String {
        val filename = uploadImage(POST_PICT, name, upload, 200, 200)
        return getBaseURl(request) + "/" + IMAGE_DIR + filename
    }

    @Throws(IOException::class)
    fun uploadAvatar(accountId: String, upload: MultipartFile, request: HttpServletRequest): String {
        val filename = uploadImage(USER_AVATAR, accountId, upload, AVATAR_WIDTH, AVATAR_HEIGHT)
        return getBaseURl(request) + "/" + IMAGE_DIR + filename
    }


    /**
     * @param folderName
     * @param name
     * @param upload
     * @param createThumbnail
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun uploadImage(folderName: String, name: String, upload: MultipartFile, createThumbnail: Boolean): String? {
        return if (createThumbnail) {
            uploadImage(folderName, name, upload, 0, 0)
        } else {
            uploadImage(folderName, name, upload, DEFAULT_THUMBNAIL_WIDTH, DEFAULT_THUMBNAIL_HEIGHT)
        }
    }

    /**
     * @param folderName
     * @param name
     * @param upload
     * @param thumbnailWidth
     * @param thumbnailHeight
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun uploadImage(folderName: String, name: String, upload: MultipartFile, thumbnailWidth: Int, thumbnailHeight: Int): String? {
        var name = name
        if (upload.contentType.contains("image") || ImageUtil.validate(upload.originalFilename)) {
            val dirString = dataDir + UPLOAD_DIR + IMAGE_DIR + folderName
            val dir = File(dirString)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val extension = FilenameUtils.getExtension(upload.originalFilename)
            name = name + "." + extension
            var filename = dir.absolutePath + "/" + name
            val src = ImageIO.read(ByteArrayInputStream(upload.bytes))
            ImageIO.write(src, extension, File(filename))
            log.info("write image on : " + filename)
            if (thumbnailWidth != 0 && thumbnailHeight != 0) {
                //				thumbnail 200x200
                val thumbnailDir = File(dirString + THUMBNAILS_DIR + "/" + thumbnailWidth.toString() + "X" + thumbnailHeight.toString())
                if (!thumbnailDir.exists()) {
                    thumbnailDir.mkdirs()
                }
                filename = thumbnailDir.absolutePath + "/" + name
                val cropImage = ImageUtil.smartCrop(src, thumbnailWidth, thumbnailHeight)
                ImageIO.write(cropImage, extension, File(filename))
                log.info("write image thumbnail on : " + filename)

                //				thumbnail 400x400
                val thumbnailDir2 = File(dirString + THUMBNAILS_DIR + "/400X400")
                if (!thumbnailDir2.exists()) {
                    thumbnailDir2.mkdirs()
                }
                filename = thumbnailDir2.absolutePath + "/" + name
                val cropImage2 = ImageUtil.smartCrop(src, 400, 400)
                ImageIO.write(cropImage2, extension, File(filename))
                log.info("write image thumbnail on : " + filename)

                //				thumbnail 75x75
                val thumbnailDir3 = File(dirString + THUMBNAILS_DIR + "/75X75")
                if (!thumbnailDir3.exists()) {
                    thumbnailDir3.mkdirs()
                }
                filename = thumbnailDir3.absolutePath + "/" + name
                val cropImage3 = ImageUtil.smartCrop(src, 75, 75)
                ImageIO.write(cropImage3, extension, File(filename))
                log.info("write image thumbnail on : " + filename)
            }
            return folderName + name
        } else {
            return null
        }
    }

    /**
     * @param folderName
     * @param name
     * @param upload
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun uploadImageAndResize(folderName: String, name: String, upload: MultipartFile, width: Int, height: Int): String? {
        var name = name
        if (upload.contentType.contains("image") || ImageUtil.validate(upload.originalFilename)) {
            val dirString = dataDir + UPLOAD_DIR + IMAGE_DIR + folderName
            val dir = File(dirString)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val extension = FilenameUtils.getExtension(upload.originalFilename)
            name = name + "." + extension
            val filename = dir.absolutePath + "/" + name
            val src = ImageIO.read(ByteArrayInputStream(upload.bytes))
            val cropImage = ImageUtil.smartCrop(src, width, height)
            ImageIO.write(cropImage, extension, File(filename))
            log.info("write image on : " + filename)
            return folderName + name
        } else {
            return null
        }
    }

    private fun getBaseURl(request: HttpServletRequest): String {
        val scheme = request.scheme
        val serverName = request.serverName
        val serverPort = request.serverPort
        val contextPath = request.contextPath
        val url = StringBuffer()
        url.append(scheme).append("://").append(serverName)
        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort)
        }
        url.append(contextPath)
        if (url.toString().endsWith("/")) {
            url.append("/")
        }
        return url.toString()
    }

    companion object {

        val UPLOAD_DIR = "uploads/"

        val IMAGE_DIR = "images/"

        val THUMBNAILS_DIR = "thumbnails/"

        val USER_AVATAR = "user/avatar/"

        val USER_COVER = "user/cover/"

        val POST_PICT = "post/"

        val DEFAULT_THUMBNAIL_WIDTH = 200

        val DEFAULT_THUMBNAIL_HEIGHT = 200

        val AVATAR_WIDTH = 200

        val AVATAR_HEIGHT = 200

        val COVER_WIDTH = 200

        val COVER_HEIGHT = 200
    }

}
