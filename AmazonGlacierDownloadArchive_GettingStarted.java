package com.amazonaws.samples;

import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;

public class AmazonGlacierDownloadArchive_GettingStarted {
    public static String vaultName = "examplevault";
    public static String archiveId = "Y8zo_wBCxZL7bJvUvnElGCfrkt_qgZBATP_op-6gsIOiEn2VbxcZJJhTbzUdUXrmBwihKdfHho9M5RRQ8FI8BHL1OEBhZaefIxb3wTaVm46QnKRrJMxEvg_e3VgEjGnvDfNeJqdltw";
    public static String downloadFilePath  = "/Users/ffmobile14/glacierdownload";
    
    public static AmazonGlacierClient glacierClient;
    public static AmazonSQSClient sqsClient;
    public static AmazonSNSClient snsClient;
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        
        
    	ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
    	
        glacierClient = new AmazonGlacierClient(credentials);        
        sqsClient = new AmazonSQSClient(credentials);
        snsClient = new AmazonSNSClient(credentials);
        
        glacierClient.setEndpoint("glacier.us-east-2.amazonaws.com");
        sqsClient.setEndpoint("sqs.us-east-2.amazonaws.com");
        snsClient.setEndpoint("sns.us-east-2.amazonaws.com");

        try {
            ArchiveTransferManager atm = new ArchiveTransferManager(glacierClient, sqsClient, snsClient);
            
            atm.download(vaultName, archiveId, new File(downloadFilePath));
            
        } catch (Exception e)
        {
            System.err.println(e);
        }
    }
}
