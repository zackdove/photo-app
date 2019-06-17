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

    private String bucketName = "zack-photo-app-bucket";

    private void saveObjectToFile(S3ObjectSummary os,  AmazonS3 s3client) {
        try {
            S3ObjectInputStream inputStream = s3client.getObject(bucketName,os.getKey()).getObjectContent();
            String filename = "src/main/resources/static/images/" + os.getKey();
            FileUtils.copyInputStreamToFile(inputStream, new File(filename));
            LOG.info("Downloaded " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean fileExists(S3ObjectSummary os){
        return (new File("src/main/resources/static/images/" + os.getKey()).isFile());
    }

    public void syncPictures(){
        //Looks in ~/.aws/credentials for credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            if (fileExists(os)) {
                LOG.info("File already downloaded");
            } else {
                //download the file
                saveObjectToFile(os, s3client);
            }
        }



    }
}
