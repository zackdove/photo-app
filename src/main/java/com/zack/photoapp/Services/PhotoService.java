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

	public List<File> getOriginalImageNames(){
		File directory = new File(photoDir);
		String[] extensions = new String[] { "jpg" };
		return new ArrayList<>(FileUtils.listFiles(directory, extensions, false));
	}

	public List<String> getIthSetOf10(Integer i){
		List<File> all = getOriginalImageNames();
		List<String> ithSetOf10 = new ArrayList<>();
		for (int j = i*10; j<(i+1)*10; j++){
			try {
				ithSetOf10.add(photoDir + all.get(j).getName());
			} catch (IndexOutOfBoundsException e) {
//				LOG.info("Index out of bounds");
			}
		}
		return ithSetOf10;
	}

}
