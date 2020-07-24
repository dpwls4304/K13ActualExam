package geolocation;

import java.util.ArrayList;

public interface SearchRadiusImpl {
	
	public ArrayList<MyFacilityDTO> searchRadius(int distance,
			double latTxt, double lngTxt);
}
