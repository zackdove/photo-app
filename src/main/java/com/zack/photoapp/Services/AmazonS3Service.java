package com.zack.photoapp.Services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.opencsv.CSVReader;
import com.zack.photoapp.Controllers.WebController;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@Service
public class AmazonS3Service {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    private void saveObjectToFile(S3Object s3object) {
        try {
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            String filename = "src/main/resources/static/images/" + s3object.getKey();
            FileUtils.copyInputStreamToFile(inputStream, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void syncPictures(){
        //Looks in ~/.aws/credentials for credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        ObjectListing objectListing = s3client.listObjects("zack-photo-app-bucket");
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            LOG.info(os.getKey());

        }
        

        S3Object img1 = s3client.getObject("zack-photo-app-bucket","000001bw.jpg");

        saveObjectToFile(img1);


    }
}
