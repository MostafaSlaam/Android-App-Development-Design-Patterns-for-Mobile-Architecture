package com.jonbott.knownspies.Activities.Details;

import android.content.Context;

import com.jonbott.knownspies.Helpers.Helper;
import com.jonbott.knownspies.ModelLayer.Database.Realm.Spy;
import com.jonbott.knownspies.ModelLayer.ModelLayer;

import io.realm.Realm;

class SpyDetailsPresenter {

    ModelLayer modelLayer = new ModelLayer();


    public int spyId;
    public String age;
    public String name;
    public String gender;
    public String imageName;
    public int imageId;

    private Context context;
    private Spy spy;

    public SpyDetailsPresenter(int spyId) {
        this.spyId = spyId;

        spy = modelLayer.spyForId(spyId);

        configureForSpy();
    }

    private void configureForSpy() {
        age = String.valueOf(spy.age);
        name = spy.name;
        gender = spy.gender;
        imageName = spy.imageName;
    }

    public void configureWithContext(Context context) {
        this.context = context;
        imageId = Helper.resourceIdWith(context, imageName);
    }


}
