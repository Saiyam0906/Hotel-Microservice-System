package com.example.User.Service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.User.Interface.S3Interface;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class S3Service implements S3Interface{
	
	 private final S3Client s3Client;
	 private final S3Presigner s3Presigner;
	 
	 
	 @Value("${aws.bucket}")
	 private String bucketName;

	 @Value("${aws.presigned-url.expiration-minutes}")
	 private int expirationMinutes;
	 
	 public String generateUploadPresignedUrl(String objectKey, String contentType) {
	        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
	                .bucket(bucketName)
	                .key(objectKey)
	                .contentType(contentType)
	                .build();

	        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
	                .signatureDuration(Duration.ofMinutes(expirationMinutes))
	                .putObjectRequest(putObjectRequest)
	                .build();

	        return s3Presigner.presignPutObject(presignRequest).url().toString();
	    }
	 
	 public void deleteObject(String objectKey) {
	        s3Client.deleteObject(DeleteObjectRequest.builder()
	                .bucket(bucketName)
	                .key(objectKey)
	                .build());
	    }
	 

}
