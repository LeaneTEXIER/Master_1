package com.example.deffontaine.voyageapresvoyage.Others;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Lieu {

    private Geocoder geocoder;
    private Double latitude, longitude;

    public Lieu(Geocoder geocoder, Double latitude, Double longitude){
        this.geocoder = geocoder;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAdresse(){
        List<Address> addresses;
        String adresse;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(addresses.size()!=1 || (adresse = (addresses.get(0).getAddressLine(0)))==null){
                adresse = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            adresse = "";
        }
        return adresse;
    }
}
