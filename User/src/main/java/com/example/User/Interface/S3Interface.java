package com.example.User.Interface;

public interface S3Interface {
	
	   String generateUploadPresignedUrl(String objectKey, String contentType);

	    void deleteObject(String objectKey);

}
