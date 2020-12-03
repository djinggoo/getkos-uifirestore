package com.djinggoo.getkos.backend.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

//@Configuration
public class GCPConfig {

//    @Bean
    public void initFirebase() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        FirebaseOptions options = FirebaseOptions
                .builder()
                .setCredentials(credentials)
                .setProjectId("")
                .build();
        FirebaseApp.initializeApp();

        Firestore firebaseDb = FirestoreClient.getFirestore();

    }

}
