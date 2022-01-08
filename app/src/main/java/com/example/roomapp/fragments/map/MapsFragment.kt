package com.example.roomapp.fragments.map

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.fragments.update.UpdateFragmentArgs

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.content.Context as Context1

class MapsFragment : Fragment(), OnMapReadyCallback {

    private val args by navArgs<MapsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap){
        var  addressList :List<Address>
        val geoCoder = Geocoder(context)
        addressList = geoCoder.getFromLocationName(args.currentUser.address, 1)

        if (!addressList.isNullOrEmpty()) {
            val restaurant = LatLng(addressList[0].latitude, addressList[0].longitude)

            //addressList[0].lat long 有顯示
            googleMap.addMarker(
                MarkerOptions().position(restaurant).title(args.currentUser.restaurantName)
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant,15f))
        }
    }




}