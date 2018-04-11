package hr.foi.air.sportloc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import hr.foi.air.sportloc.adapters.EventDetailsPageAdapter;
import hr.foi.air.sportloc.fragments.EventMembersFragment;
import hr.foi.air.sportloc.fragments.EventDetailsFragment;

public class EventDetailsActivity extends AppCompatActivity {

    private EventDetailsPageAdapter eventDetailsPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        eventDetailsPageAdapter = new EventDetailsPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager pager){
        EventDetailsPageAdapter adapter = new EventDetailsPageAdapter(getSupportFragmentManager());

        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        eventDetailsFragment.setArguments(getIntent().getExtras());
        adapter.addFragment(eventDetailsFragment, getResources().getString(R.string.title_activity_event_details));
        adapter.addFragment(new EventMembersFragment(), getResources().getString(R.string.title_activity_event_members));

        pager.setAdapter(adapter);

    }

}
