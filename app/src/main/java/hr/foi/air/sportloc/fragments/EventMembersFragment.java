package hr.foi.air.sportloc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.foi.air.sportloc.R;

public class EventMembersFragment extends Fragment {

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.lblCurrent)
    TextView lblCurrent;
    @BindView(R.id.lblCurrentNumber)
    TextView lblCurrentNumber;
    @BindView(R.id.dvdHorizontal1)
    View dvdHorizontal1;
    @BindView(R.id.rcvCurrentMembers)
    RecyclerView rcvCurrentMembers;
    @BindView(R.id.lblPending)
    TextView lblPending;
    @BindView(R.id.lblPendingNumber)
    TextView lblPendingNumber;
    @BindView(R.id.dvdHorizontal2)
    View dvdHorizontal2;
    @BindView(R.id.rcvPendingMembers)
    RecyclerView rcvPendingMembers;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_members, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
