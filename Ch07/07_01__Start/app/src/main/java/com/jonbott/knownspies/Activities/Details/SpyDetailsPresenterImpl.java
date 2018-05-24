package com.jonbott.knownspies.Activities.Details;

import android.content.Context;

import com.jonbott.knownspies.Helpers.Helper;
import com.jonbott.knownspies.ModelLayer.DTOs.SpyDTO;
import com.jonbott.knownspies.ModelLayer.ModelLayer;

public class SpyDetailsPresenterImpl implements SpyDetailsPresenter {

    public int spyId;
    public String age;
    public String name;
    public String gender;
    public String imageName;
    public int imageId;

    private SpyDTO spy;

    private Context context;
    private ModelLayer modelLayer;
    public SpyDetailsPresenterImpl(int spyId, Context context, ModelLayer modelLayer) {
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

    //region Getters

    @Override
    public int getSpyId() {
        return spyId;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public int getImageId() {
        return imageId;
    }

    //endregion
}
