package com.djinggoo.getkos.backend.utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InitializeFirebaseServer {

    private static Firestore firestoreDatabase = null;

    public InitializeFirebaseServer(InputStream fileCredentils) {
        InputStream input = null;

//        input = new FileInputStream("/home/malik/Downloads/getkos-isawesome-36c92ad0f279.json");
        try {
            File file = new File("xxx");
            FileUtils.copyInputStreamToFile(fileCredentils, file);
            input = new FileInputStream(file);
            GoogleCredentials credentials = GoogleCredentials.fromStream(input);
            FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();

            FirebaseApp.initializeApp(options);
            firestoreDatabase = FirestoreClient.getFirestore();
            System.out.print("Everithing is okee");
            readData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void readData() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestoreDatabase.collection("cities").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("ID: " + document.getId());
            System.out.println("Name: " + document.getString("name"));
            System.out.println("Value: " + document.getDouble("value"));
        }
    }

    public static Firestore getFirestoreDatabase(){
        return firestoreDatabase;
    }

}
