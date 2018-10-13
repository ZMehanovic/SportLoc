package hr.foi.air.sportloc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.sportloc.adapters.EventListAdapter;

public class EventListActivity extends AppCompatActivity {
    @BindView(R.id.rcvEventList)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> eventList = new ArrayList<>();
        eventList.add("Test 1");
        eventList.add("Test 2");
        eventList.add("Test 3");

        RecyclerView.Adapter mAdapter = new EventListAdapter(eventList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
