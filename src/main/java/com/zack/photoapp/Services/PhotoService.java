package com.zack.photoapp.Services;

import com.zack.photoapp.Controllers.WebController;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class PhotoService {

	private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

	public Collection<File> getListOfImageNames(){
		File directory = new File("src/main/resources/static/images/");
		String[] extensions = new String[] { "jpg" };
		return FileUtils.listFiles(directory, extensions, false);
	}

	public Collection<String> getFirst10(){
		Collection<File> all = getListOfImageNames();
		Collection<String> first10 = new ArrayList<>();
		String path = "/images/";
		int i = 0;
		for (File file : all) {
			String filename = file.getName();
			first10.add( path + filename );
			i++;
			if (i==10) break;
		}
		return first10;
	}
}
