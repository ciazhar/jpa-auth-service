package com.ciazhar.authserver.service;

import com.ciazhar.authserver.util.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${upload.data.dir}")
	private String dataDir;
	
	public static final String UPLOAD_DIR= "uploads/";
	
	public static final String IMAGE_DIR= "images/";
	
	public static final String THUMBNAILS_DIR= "thumbnails/";
	
	public static final String USER_AVATAR = "user/avatar/";
	
	public static final String USER_COVER = "user/cover/";

	public static final String POST_PICT = "post/";
	
	public static final int DEFAULT_THUMBNAIL_WIDTH = 200;
	
	public static final int DEFAULT_THUMBNAIL_HEIGHT = 200;

	public static final int AVATAR_WIDTH = 200;
	
	public static final int AVATAR_HEIGHT = 200;

	public static final int COVER_WIDTH = 200;
	
	public static final int COVER_HEIGHT = 200;
	
	/**
	 * @param folderName
	 * @param upload
	 * @param createThumbnail
	 * @return
	 * @throws IOException
	 */
	public String uploadImage(String folderName, MultipartFile upload, boolean createThumbnail) throws IOException {
		return uploadImage(folderName, upload.getOriginalFilename(), upload, createThumbnail);
	}

	public String uploadPostVenuePhoto(String name, MultipartFile upload, HttpServletRequest request) throws IOException {
		String filename = uploadImage(POST_PICT,name,upload,200,200);
		return getBaseURl(request) + "/" + IMAGE_DIR + filename;
	}

	public String uploadAvatar(String accountId, MultipartFile upload, HttpServletRequest request) throws IOException {
		String filename = uploadImage(USER_AVATAR,accountId,upload,AVATAR_WIDTH,AVATAR_HEIGHT);
		return getBaseURl(request) + "/" + IMAGE_DIR + filename;
	}



	/**
	 * @param folderName
	 * @param name
	 * @param upload
	 * @param createThumbnail
	 * @return
	 * @throws IOException
	 */
	private String uploadImage(String folderName, String name, MultipartFile upload, boolean createThumbnail) throws IOException {
		if(createThumbnail){
			return uploadImage(folderName, name, upload, 0, 0);
		}else{
			return uploadImage(folderName, name, upload, DEFAULT_THUMBNAIL_WIDTH, DEFAULT_THUMBNAIL_HEIGHT);
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
	private String uploadImage(String folderName, String name, MultipartFile upload, int thumbnailWidth, int thumbnailHeight) throws IOException {
		if(upload.getContentType().contains("image") || ImageUtil.validate(upload.getOriginalFilename())){
			String dirString = dataDir + UPLOAD_DIR + IMAGE_DIR + folderName;
			File dir = new File(dirString);
			if(!dir.exists()){
				dir.mkdirs();
			}
			String extension = FilenameUtils.getExtension(upload.getOriginalFilename());
			name = name + "." + extension;
			String filename = dir.getAbsolutePath()+"/"+name;
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(upload.getBytes()));
			ImageIO.write(src, extension, new File(filename));
			log.info("write image on : " + filename);
			if(thumbnailWidth != 0 && thumbnailHeight != 0){
//				thumbnail 200x200
	        	File thumbnailDir = new File(dirString+THUMBNAILS_DIR+"/"+String.valueOf(thumbnailWidth)+"X"+String.valueOf(thumbnailHeight));
				if(!thumbnailDir.exists()){
					thumbnailDir.mkdirs();
				}
				filename = thumbnailDir.getAbsolutePath()+"/"+name;
				BufferedImage cropImage = ImageUtil.smartCrop(src, thumbnailWidth, thumbnailHeight);
				ImageIO.write(cropImage, extension, new File(filename));
				log.info("write image thumbnail on : " + filename);

//				thumbnail 400x400
				File thumbnailDir2 = new File(dirString+THUMBNAILS_DIR+"/400X400");
				if(!thumbnailDir2.exists()){
					thumbnailDir2.mkdirs();
				}
				filename = thumbnailDir2.getAbsolutePath()+"/"+name;
				BufferedImage cropImage2 = ImageUtil.smartCrop(src, 400, 400);
				ImageIO.write(cropImage2, extension, new File(filename));
				log.info("write image thumbnail on : " + filename);

//				thumbnail 75x75
				File thumbnailDir3 = new File(dirString+THUMBNAILS_DIR+"/75X75");
				if(!thumbnailDir3.exists()){
					thumbnailDir3.mkdirs();
				}
				filename = thumbnailDir3.getAbsolutePath()+"/"+name;
				BufferedImage cropImage3 = ImageUtil.smartCrop(src, 75, 75);
				ImageIO.write(cropImage3, extension, new File(filename));
				log.info("write image thumbnail on : " + filename);
			}
	        return folderName + name;
		}else{
			return null;
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
	private String uploadImageAndResize(String folderName, String name, MultipartFile upload, int width, int height) throws IOException {
		if(upload.getContentType().contains("image") || ImageUtil.validate(upload.getOriginalFilename())){
			String dirString = dataDir + UPLOAD_DIR + IMAGE_DIR + folderName;
			File dir = new File(dirString);
			if(!dir.exists()){
				dir.mkdirs();
			}
			String extension = FilenameUtils.getExtension(upload.getOriginalFilename());
			name = name + "." + extension;
			String filename = dir.getAbsolutePath()+"/"+ name;
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(upload.getBytes()));
			BufferedImage cropImage = ImageUtil.smartCrop(src, width, height);
			ImageIO.write(cropImage, extension, new File(filename));
			log.info("write image on : " + filename);
			return folderName + name;
		}else{
			return null;
		}
	}
	
	private String getBaseURl(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
	
}
