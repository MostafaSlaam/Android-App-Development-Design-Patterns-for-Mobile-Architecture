package com.jonbott.knownspies.Activities.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonbott.knownspies.Activities.SecretDetails.SecretDetailsActivity;
import com.jonbott.knownspies.Dependencies.DependencyRegistry;
import com.jonbott.knownspies.Helpers.Constants;
import com.jonbott.knownspies.ModelLayer.Database.Realm.Spy;
import com.jonbott.knownspies.R;

public class SpyDetailsActivity extends AppCompatActivity {

    private SpyDetailsPresenter presenter;
    private ImageView profileImage;
    private TextView  nameTextView;
    private TextView  ageTextView;
    private TextView  genderTextView;
    private ImageButton calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spy_details);
        attachUI();

        Bundle bundle = getIntent().getExtras();
        DependencyRegistry.shared.inject(this, bundle);
    }

    //region UI Methods

    private void attachUI() {
        profileImage    = (ImageView)   findViewById(R.id.details_profile_image);
        nameTextView    = (TextView)    findViewById(R.id.details_name);
        ageTextView     = (TextView)    findViewById(R.id.details_age);
        genderTextView  = (TextView)    findViewById(R.id.details_gender);
        calculateButton = (ImageButton) findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(v -> gotoSecretDetails());

    }

    //endregion

    //region Injection Methods

    public void configureWith(SpyDetailsPresenter presenter) {
        this.presenter = presenter;

        ageTextView.setText(presenter.age);
        nameTextView.setText(presenter.name);
        genderTextView.setText(presenter.gender);
        profileImage.setImageResource(presenter.imageId);
    }

    //endregion

    //region navigation

    private void gotoSecretDetails() {
        if(presenter == null) return;

        Bundle bundle = new Bundle();
               bundle.putInt(Constants.spyIdKey, presenter.spyId);

        Intent intent = new Intent(SpyDetailsActivity.this, SecretDetailsActivity.class);
               intent.putExtras(bundle);

        startActivity(intent);
    }

    //endregion
}
