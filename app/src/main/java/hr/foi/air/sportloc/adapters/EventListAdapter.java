package hr.foi.air.sportloc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.sportloc.R;

/**
 * Created by Gabriel on 22.3.2018..
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private ArrayList<String> eventList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvEventName)
        TextView eventName;

        @BindView(R.id.tvEventInfo)
        TextView eventInfo;

        @BindView(R.id.tvEventDateTime)
        TextView eventDateTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public EventListAdapter(ArrayList<String> eventList) {
        this.eventList = eventList;
    }

    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView eventName = holder.eventName;
        eventName.setText(eventList.get(position));

        TextView eventInfo = holder.eventInfo;
        eventInfo.setText(eventList.get(position));

        TextView eventDateTime = holder.eventDateTime;
        eventDateTime.setText(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
