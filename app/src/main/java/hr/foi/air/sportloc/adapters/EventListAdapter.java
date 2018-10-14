package hr.foi.air.sportloc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.data.beans.EventBean;
import hr.foi.air.sportloc.R;

/**
 * Created by Gabriel on 22.3.2018..
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private ArrayList<EventBean> eventList;

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

    public EventListAdapter(ArrayList<EventBean> eventList) {
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
        eventName.setText(eventList.get(position).getTitle());

        String info = eventList.get(position).getDescription() + ", " +
                      eventList.get(position).getLocation() + " (" +
                      eventList.get(position).getCurrentCapacity() + "/" +
                      eventList.get(position).getMaxCapacity() + ")";

        TextView eventInfo = holder.eventInfo;
        eventInfo.setText(info);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("d.M.yyyy HH:mm", Locale.getDefault());
        Date startTime = inputFormat.parse(eventList.get(position).getStartTime());

        TextView eventDateTime = holder.eventDateTime;
        eventDateTime.setText(eventList.get(position).getStartTime());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
