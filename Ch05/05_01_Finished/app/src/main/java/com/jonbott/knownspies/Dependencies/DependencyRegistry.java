package com.jonbott.knownspies.Dependencies;

import com.google.gson.Gson;
import com.jonbott.knownspies.ModelLayer.Database.DataLayer;
import com.jonbott.knownspies.ModelLayer.ModelLayer;
import com.jonbott.knownspies.ModelLayer.Network.NetworkLayer;
import com.jonbott.knownspies.ModelLayer.Translation.SpyTranslator;
import com.jonbott.knownspies.ModelLayer.Translation.TranslationLayer;

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

}
