package com.zack.photoapp;

import com.zack.photoapp.Services.AmazonS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    private AmazonS3Service amazonS3Service;

    public void run(ApplicationArguments applicationArguments){
        amazonS3Service.syncPictures();
    }

}
