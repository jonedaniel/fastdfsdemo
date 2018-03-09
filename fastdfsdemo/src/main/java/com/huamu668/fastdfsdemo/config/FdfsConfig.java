package com.huamu668.fastdfsdemo.config;

import org.csource.fastdfs.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FdfsConfig {

    static final String FASTDFS_CONFIG = "fastdfs_client.conf";

    @Bean
    public StorageClient1 initStorageClient() {
        StorageClient1 storageClient = null;
        try {
            ClientGlobal.init(FASTDFS_CONFIG);
            System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("getConnection return null");
            }
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("getStoreStorage return null");
            }
            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageClient;
    }
}
