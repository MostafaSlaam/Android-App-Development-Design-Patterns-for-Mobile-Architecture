package com.jonbott.knownspies.Activities.Details;

import android.content.Context;

import com.jonbott.knownspies.Helpers.Helper;
import com.jonbott.knownspies.ModelLayer.DTOs.SpyDTO;
import com.jonbott.knownspies.ModelLayer.Database.Realm.Spy;
import com.jonbott.knownspies.ModelLayer.ModelLayer;

import io.realm.Realm;

public class SpyDetailsPresenter {

    public int spyId;
    public String age;
    public String name;
    public String gender;
    public String imageName;
    public int imageId;

    private SpyDTO spy;
    private Context context;
    private ModelLayer modelLayer;

    public SpyDetailsPresenter(int spyId, Context context, ModelLayer modelLayer) {
        this.spyId = spyId;
        this.context = context;
        this.modelLayer = modelLayer;

        configureData();
    }

    private void configureData() {
        spy = modelLayer.spyForId(spyId);

        age = String.valueOf(spy.age);
        name = spy.name;
        gender = spy.gender.name();
        imageName = spy.imageName;

        imageId = Helper.resourceIdWith(context, imageName);
    }
}
