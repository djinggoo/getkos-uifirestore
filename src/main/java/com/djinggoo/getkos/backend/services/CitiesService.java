package com.djinggoo.getkos.backend.services;

import com.djinggoo.getkos.backend.entity.City;
import com.djinggoo.getkos.backend.utils.InitializeFirebaseServer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CitiesService {

    public ArrayList<City> getCities(){
        ArrayList<City> result = new ArrayList<>();
        if (InitializeFirebaseServer.getFirestoreDatabase() == null) return new ArrayList<City>();
        ApiFuture<QuerySnapshot> query = InitializeFirebaseServer.getFirestoreDatabase().collection("cities").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            result.add(new City(document.getString("name"), document.getDouble("value").intValue()));
//            System.out.println("ID: " + document.getId());
//            System.out.println("Name: " + document.getString("name"));
//            System.out.println("Value: " + document.getDouble("value"));
        }
        return result;
    }

}
