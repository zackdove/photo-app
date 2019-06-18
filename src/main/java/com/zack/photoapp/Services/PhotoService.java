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

@Service
public class PhotoService {

	private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

	public List<File> getOriginalImageNames(){
		File directory = new File("src/main/resources/static/images/original/");
		String[] extensions = new String[] { "jpg" };
		return new ArrayList<>(FileUtils.listFiles(directory, extensions, false));
	}

	public Collection<String> getFirst10(){
		List<File> all = getOriginalImageNames();
		List<String> first10 = new ArrayList<>();
		String path = "/images/resized/";
		int i = 0;
		for (File file : all) {
			String filename = file.getName();
			first10.add( path + filename );
			i++;
			if (i==10) break;
		}
		return first10;
	}

	public List<String> getIthSetOf10(Integer i){
		List<File> all = getOriginalImageNames();
		List<String> ithSetOf10 = new ArrayList<>();
		String path = "/images/resized/";
		for (int j = i*10; j<(i+1)*10; j++){
			try {
				ithSetOf10.add(path + all.get(j).getName());
			} catch (IndexOutOfBoundsException e) {
//				LOG.info("Index out of bounds");
			}
		}
		return ithSetOf10;
	}


	public void resizeImages(){
		LOG.info("Starting resize image method");
		Collection<File> imagesNames = getOriginalImageNames();
		int i = 0;
		for (File imageName : imagesNames){
			try {
				if (new File("src/main/resources/static/images/resized/" + imageName.getName()).isFile()){
					//Resized file already created
				} else {
					BufferedImage image = ImageIO.read(imageName);
					String newPath = "src/main/resources/static/images/resized/" + imageName.getName();
					ImageIO.write(Scalr.resize(image, Method.ULTRA_QUALITY, 1600), "JPG", new File(newPath));
//					LOG.info("Resized image: " + imageName);
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LOG.info("Completed resizing " + i + " images");
	}
}
