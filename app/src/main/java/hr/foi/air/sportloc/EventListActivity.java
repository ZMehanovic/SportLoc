package hr.foi.air.sportloc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.data.beans.EventBean;
import hr.foi.air.sportloc.adapters.EventListAdapter;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.WebServiceHandler;

public class EventListActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.rcvEventList)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrieveEvents();
    }

    public void retrieveEvents() {
        String type = "getEventList";

        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.callWebService(null, type, getApplicationContext(), new WebServiceHandler() {
            @Override
            public void onDataArrived(Object result) {
                ArrayList<?> resultList = (ArrayList<?>) result;
                ArrayList<EventBean> eventList = new ArrayList<>();
                for(Object event : resultList) {
                    if(event instanceof EventBean) {
                        eventList.add((EventBean) event);
                    }
                }
                mAdapter = new EventListAdapter(eventList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
