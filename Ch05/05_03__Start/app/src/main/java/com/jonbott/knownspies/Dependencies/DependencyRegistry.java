package com.jonbott.knownspies.Dependencies;

import android.os.Bundle;

import com.google.gson.Gson;
import com.jonbott.knownspies.Activities.Details.SpyDetailsActivity;
import com.jonbott.knownspies.Activities.Details.SpyDetailsPresenter;
import com.jonbott.knownspies.Activities.SecretDetails.SecretDetailsActivity;
import com.jonbott.knownspies.Activities.SecretDetails.SecretDetailsPresenter;
import com.jonbott.knownspies.Activities.SpyList.SpyListActivity;
import com.jonbott.knownspies.Activities.SpyList.SpyListPresenter;
import com.jonbott.knownspies.Helpers.Constants;
import com.jonbott.knownspies.ModelLayer.Database.DataLayer;
import com.jonbott.knownspies.ModelLayer.ModelLayer;
import com.jonbott.knownspies.ModelLayer.Network.NetworkLayer;
import com.jonbott.knownspies.ModelLayer.Translation.SpyTranslator;
import com.jonbott.knownspies.ModelLayer.Translation.TranslationLayer;

import java.util.NoSuchElementException;

import io.realm.Realm;

public class DependencyRegistry {
    public static  DependencyRegistry shared = new DependencyRegistry();

    private Gson gson = new Gson();

    //region Realm

    private Realm realm = Realm.getDefaultInstance();

    public Realm newRealmInstanceOnCurrentThread() {
        return Realm.getInstance(realm.getConfiguration());
    }

    //endregion

    //region Singletons

    public SpyTranslator spyTranslator = new SpyTranslator();

    public TranslationLayer translationLayer = createTranslationLayer();
    private TranslationLayer createTranslationLayer() {
        return new TranslationLayer(gson, spyTranslator);
    }

    public DataLayer dataLayer = createDataLayer();
    private DataLayer createDataLayer() {
        return new DataLayer(realm, this::newRealmInstanceOnCurrentThread);
    }

    public NetworkLayer networkLayer = new NetworkLayer();

    public ModelLayer modelLayer = createModelLayer();

    private ModelLayer createModelLayer() {
        return new ModelLayer(networkLayer, dataLayer, translationLayer);
    }

    //endregion

    //region InjectionMethods
    public void inject(SpyDetailsActivity activity, Bundle bundle) throws NoSuchElementException {
        int spyId = idFromBundle(bundle);

        SpyDetailsPresenter presenter = new SpyDetailsPresenter(spyId, activity, modelLayer);
        activity.configureWith(presenter);
    }

    public void inject(SecretDetailsActivity activity, Bundle bundle) throws NoSuchElementException {
        int spyId = idFromBundle(bundle);

        SecretDetailsPresenter presenter = new SecretDetailsPresenter(spyId, modelLayer);

    }

    public void inject(SpyListActivity activity) {
        SpyListPresenter presenter = new SpyListPresenter(modelLayer);
        activity.configureWith(presenter);
    }

    //endregion

    //region Helper Methods

    private int idFromBundle(Bundle bundle) {
        if(bundle == null) throw new NoSuchElementException("Unable to get spy id from bundle");

        int spyId = bundle.getInt(Constants.spyIdKey);
        if(spyId == 0) throw new NoSuchElementException("Unable to get spy id from bundle");
        return spyId;
    }

    //endregion

}
