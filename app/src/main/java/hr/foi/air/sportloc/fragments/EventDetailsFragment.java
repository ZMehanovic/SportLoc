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
import android.widget.EditText;
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
    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.dvdHorizontal)
    View dvdHorizontal;
    @BindView(R.id.tvSpace)
    TextView tvSpace;
    @BindView(R.id.tvCreatorLabel)
    TextView tvCreatorLabel;
    @BindView(R.id.tvCreator)
    TextView tvCreator;
    @BindView(R.id.tvAddressLabel)
    TextView tvAddressLabel;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.tvDateLabel)
    TextView tvDateLabel;
    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.tvTimeLabel)
    TextView tvTimeLabel;
    @BindView(R.id.etTime)
    EditText etTime;
    @BindView(R.id.tvDescriptionLabel)
    TextView tvDescriptionLabel;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.btnJoinLeaveRequest)
    Button btnJoinLeaveRequest;
    @BindView(R.id.tvMapLabel)
    TextView tvMapLabel;
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
