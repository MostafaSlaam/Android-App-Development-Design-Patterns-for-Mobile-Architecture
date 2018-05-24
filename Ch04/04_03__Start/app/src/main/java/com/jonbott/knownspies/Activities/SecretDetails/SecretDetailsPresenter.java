package com.jonbott.knownspies.Activities.SecretDetails;

import com.jonbott.knownspies.Helpers.Threading;
import com.jonbott.knownspies.ModelLayer.Database.Realm.Spy;
import com.jonbott.knownspies.ModelLayer.ModelLayer;

import io.reactivex.functions.Consumer;

class SecretDetailsPresenter {
    ModelLayer modelLayer = new ModelLayer();

    private Spy spy;
    public String password;

    public SecretDetailsPresenter(int spyId) {
        spy = modelLayer.spyForId(spyId);

        password = spy.password;
    }

    public void crackPassword(Consumer<String> finished) {
        Threading.async(()-> {
            //fake processing work
            Thread.sleep(2000);
            return true;
        }, success -> {
            finished.accept(password);
        });
    }
}
