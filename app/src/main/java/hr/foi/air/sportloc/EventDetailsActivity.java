package hr.foi.air.sportloc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import hr.foi.air.sportloc.fragments.EventDetailsFragment;

public class EventDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        eventDetailsFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.eventDetailsLayout, eventDetailsFragment)
                .commit();
    }

}
