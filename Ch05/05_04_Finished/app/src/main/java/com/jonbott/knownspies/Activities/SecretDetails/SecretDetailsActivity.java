package com.jonbott.knownspies.Activities.SecretDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jonbott.knownspies.Activities.SpyList.SpyListActivity;
import com.jonbott.knownspies.Dependencies.DependencyRegistry;
import com.jonbott.knownspies.Helpers.Constants;
import com.jonbott.knownspies.R;

public class SecretDetailsActivity extends AppCompatActivity {

    SecretDetailsPresenter presenter;

    ProgressBar progressBar;
    TextView crackingLabel;
    Button finishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_details);

        attachUI();

        Bundle bundle = getIntent().getExtras();
        DependencyRegistry.shared.inject(this, bundle);
    }

    public void configureWith(SecretDetailsPresenter presenter) {
        this.presenter = presenter;
        this.presenter.crackPassword(password -> {
            progressBar.setVisibility(View.GONE);
            crackingLabel.setText(presenter.password);
        });

    }

    //region Helper Methods

    private void attachUI() {
        progressBar    = (ProgressBar) findViewById(R.id.secret_progress_bar);
        crackingLabel  = (TextView)    findViewById(R.id.secret_cracking_label);
        finishedButton = (Button)      findViewById(R.id.secret_finished_button);

        finishedButton.setOnClickListener(v -> finishedClicked() );

    }

    //region User Interaction

    private void finishedClicked() {
        Intent intent = new Intent(this, SpyListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    //endregion

}
