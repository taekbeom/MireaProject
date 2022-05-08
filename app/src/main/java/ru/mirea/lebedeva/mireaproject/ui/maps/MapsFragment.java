package ru.mirea.lebedeva.mireaproject.ui.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.GeoObjectSelectionMetadata;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.lebedeva.mireaproject.R;

public class MapsFragment extends Fragment implements DrivingSession.DrivingRouteListener, GeoObjectTapListener {

    private final String MAPKIT_API_KEY = "YOUR_API";
    private final Point ROUTE_STROMYNKA = new Point(55.794259, 37.701448);
    private final Point ROUTE_VERNADKA = new Point(55.669192, 37.481598);
    private final Point ROUTE_END_LOCATION = new Point(54.708595, 85.771141);
    private final Point SCREEN_CENTER = new Point(
            (ROUTE_STROMYNKA.getLatitude() + ROUTE_END_LOCATION.getLatitude()) / 2,
            (ROUTE_STROMYNKA.getLongitude() + ROUTE_END_LOCATION.getLongitude()) /
                    2);
    private MapView mapView;
    private MapObjectCollection mapObjects;
    private DrivingRouter drivingRouter;
    private DrivingSession drivingSession;
    private int[] colors = {0xFFFF0000, 0xFF00FF00, 0x00FFBBBB, 0xFF0000FF, 0xFFFFFF00, 0xFFF0FFFF};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(getContext());
        DirectionsFactory.initialize(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = rootView.findViewById(R.id.mapview);
        mapView.getMap().move(new CameraPosition(
                SCREEN_CENTER, 10, 0, 0));
        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter();
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        markInfo();

        submitRequest(ROUTE_STROMYNKA);

        return rootView;
    }

    @Override
    public void onDrivingRoutes(@NonNull List<DrivingRoute> list) {
        int color;
        for (int i = 0; i < list.size(); i++) {
            color = colors[i];
            mapObjects.addPolyline(list.get(i).getGeometry()).setStrokeColor(color);
        }
    }

    @Override
    public void onDrivingRoutesError(@NonNull Error error) {
    }

    @Override
    public void onStop() {
        super.onStop();
        MapKitFactory.getInstance().onStop();
        mapView.onStop();
    }
    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private void submitRequest(Point point) {
        DrivingOptions options = new DrivingOptions();
        options.setAlternativeCount(6);
        ArrayList<RequestPoint> requestPoints = new ArrayList<>();
        requestPoints.add(new RequestPoint(
                point,
                RequestPointType.WAYPOINT,
                null));
        requestPoints.add(new RequestPoint(
                ROUTE_END_LOCATION,
                RequestPointType.WAYPOINT,
                null));
        drivingSession = drivingRouter.requestRoutes(requestPoints, options, this);
    }

    public void markInfo(){
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        PlacemarkMapObject markStr = mapObjects.addPlacemark(ROUTE_STROMYNKA, ImageProvider.fromResource(getContext(), R.drawable.search_layer_pin_selected_default));
        PlaceCoordinates placeCoordinatesStr = new
                PlaceCoordinates("РТУ МИРЭА (Кампус ул. Стромынка, 20)", 1947, "107996, ЦФО, г. Москва, ул. Стромынка, д.20",
                ROUTE_STROMYNKA.getLatitude(), ROUTE_STROMYNKA.getLongitude());
        markStr.setUserData(placeCoordinatesStr);

        PlacemarkMapObject markVer = mapObjects.addPlacemark(ROUTE_VERNADKA, ImageProvider.fromResource(getContext(), R.drawable.search_layer_pin_selected_default));
        PlaceCoordinates placeCoordinatesVer = new
                PlaceCoordinates("РТУ МИРЭА (Пр-т Вернадского, 78)", 1947, "119454, ЦФО, г. Москва, Проспект Вернадского, д. 78",
                ROUTE_VERNADKA.getLatitude(), ROUTE_VERNADKA.getLongitude());
        markVer.setUserData(placeCoordinatesVer);

        markStr.addTapListener((mapObject, point) -> {
            Toast.makeText(requireContext(), (mapObject.getUserData().toString()), Toast.LENGTH_SHORT).show();
            return true;
        });

        markVer.addTapListener((mapObject, point) -> {
            Toast.makeText(requireContext(), (mapObject.getUserData().toString()), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public boolean onObjectTap(GeoObjectTapEvent event) {
        final GeoObjectSelectionMetadata selectionMetadata = event.getGeoObject()
                .getMetadataContainer()
                .getItem(GeoObjectSelectionMetadata.class);

        if (selectionMetadata != null) {
            mapView.getMap().selectGeoObject(selectionMetadata.getId(), selectionMetadata.getLayerId());
        }
        return selectionMetadata != null;
    }

}