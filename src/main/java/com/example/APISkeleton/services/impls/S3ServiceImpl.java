package com.example.APISkeleton.services.impls;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.example.APISkeleton.services.IS3service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class S3ServiceImpl implements IS3service {

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.sessionToken}")
    private String awsSessionToken;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private String awsId ="720889212720";

    private AmazonS3 amazonS3;

    @PostConstruct
    public void postConstruct() {
        AWSCredentials credentials;
        if (awsSessionToken != null && !awsSessionToken.isEmpty()) {
            credentials = new BasicSessionCredentials(awsAccessKeyId, awsSecretKey, awsSessionToken);
        } else {
            credentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretKey);
        }

        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public String uploadResource(MultipartFile multipartFile) {

        try {
            System.out.println("Listado de buckets de S3:");
            for (Bucket bucket : amazonS3.listBuckets()) {
                System.out.println(bucket.getName());
            }
            System.out.println("Conexión exitosa a AWS S3.");
        } catch (Exception e) {
            System.err.println("Error al conectar con AWS S3: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private String generateFileName(MultipartFile multipartFile) {
        // Generar un nombre único usando UUID
        return UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename().replace(" ", "_");
    }
}
