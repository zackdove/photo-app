package com.zack.photoapp.Services;

import com.zack.photoapp.Controllers.WebController;
import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.imgscalr.Scalr.*;

import static com.zack.photoapp.Constants.photoDir;

@Service
public class PhotoService {

	private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

	public List<File> getImageNamesInDir(String dir){
		File directory = new File(photoDir + "/" + dir);
		String[] extensions = new String[] { "jpg" };
		return new ArrayList<>(FileUtils.listFiles(directory, extensions, false));
	}

	public List<String> getIthSetOf10(Integer i){
		List<File> all = getImageNamesInDir("resized");
		List<String> ithSetOf10 = new ArrayList<>();
		for (int j = i*10; j<(i+1)*10; j++){
			try {
				ithSetOf10.add(photoDir + "/resized/" + all.get(j).getName());
			} catch (IndexOutOfBoundsException e) {
				LOG.info("Index out of bounds");
			}
		}
		return ithSetOf10;
	}

	public void resizeImages(){
		LOG.info("Starting resize image method");
		Collection<File> imagesNames = getImageNamesInDir("original");
		int i = 0;
		for (File imageName : imagesNames){
			try {
				if (new File(photoDir + "/resized/" + imageName.getName() ).isFile()){
					//Resized file already created
				} else {
					LOG.info("Resizing image "  + imageName);
					BufferedImage image = ImageIO.read(imageName);
					File imgPath = new File(photoDir + "/resized/" + imageName.getName());
					imgPath.getParentFile().mkdirs();
					ImageIO.write(Scalr.resize(image, 1600), "JPG", imgPath);
					LOG.info("Resized image: " + imageName);
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LOG.info("Completed resizing " + i + " images");
	}

}
