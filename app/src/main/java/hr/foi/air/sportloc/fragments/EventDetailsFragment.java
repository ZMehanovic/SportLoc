package hr.foi.air.sportloc.fragments;


import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hr.foi.air.data.beans.BeanTypes;
import hr.foi.air.data.beans.EventBean;
import hr.foi.air.sportloc.R;
import hr.foi.air.sportloc.databinding.FragmentEventDetailsBinding;

public class EventDetailsFragment extends Fragment implements OnMapReadyCallback {

    Unbinder unbinder;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvSportLocation)
    TextView tvSportLocation;
    @BindView(R.id.dvdHorizontal)
    View dvdHorizontal;
    @BindView(R.id.tvCapacity)
    TextView tvCapacity;
    @BindView(R.id.lblCreator)
    TextView lblCreator;
    @BindView(R.id.tvCreatorUserName)
    TextView tvCreatorUserName;
    @BindView(R.id.lblAddress)
    TextView lblAddress;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.lblStartTime)
    TextView lblStartTime;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.lblEndTime)
    TextView lblEndTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.lblDescription)
    TextView lblDescription;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.btnJoinLeaveRequest)
    Button btnJoinLeaveRequest;
    @BindView(R.id.lblMap)
    TextView lblMap;
    @BindView(R.id.ivEventDetailsTick)
    ImageView ivEventDetailsTick;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private EventBean bean;

    public EventDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEventDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_details, container, false);
        View view = binding.getRoot();
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            bean = bundle.getParcelable(BeanTypes.EventBean.name());
            binding.setEvent(bean);
            if (!bean.isOpenEvent()) {
                hideUnavailableData();
            }
        }

        return view;
    }

    private void hideUnavailableData() {
        btnJoinLeaveRequest.setVisibility(View.GONE);
    }

    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(getActivity().getApplicationContext());
        LatLng result = null;

        try {
            List<Address> address = coder.getFromLocationName(strAddress, 1);
            if (address != null) {
                Address location = address.get(0);
                result = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng eventLocation = getLocationFromAddress(bean.getAddress() + " " + bean.getLocation());
        if (eventLocation != null) {
            map.addMarker(new MarkerOptions().position(eventLocation).title(bean.getTitle()));
            map.moveCamera(CameraUpdateFactory.newLatLng(eventLocation));
            map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
