package main.java;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.*;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.standalone.CommandLineOptions;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsLoader;

import us.monoid.web.JSONResource;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;

public class GoogleDrivingDirections {
	private URL url;
	private URI uri;
	private JSONResource response;

    private static final String X_MASHAPE_KEY = "dz76zlrCVimsh0GehwwnBjbZVXdgp1LwLckjsnety8AmdZq63k";

	public GoogleDrivingDirections(String startingPoint, String endingPoint, boolean useFakeServer) {

		Resty restCall = new Resty();
        restCall.withHeader("X-Mashape-Key", X_MASHAPE_KEY);

		WireMockServer wireMockServer = null;
		
		try {
			if (!useFakeServer) {
				url = new URL("https://montanaflynn-mapit.p.mashape.com/directions?ending=" + endingPoint+ "&starting=" + startingPoint);
			} else {				
				FileSource fileSource=new SingleRootFileSource("./wiremock");
				FileSource filesFileSource=fileSource.child("__files");
				FileSource mappingsFileSource=fileSource.child("mappings");
				
				CommandLineOptions options=new CommandLineOptions();

				wireMockServer=new WireMockServer(Options.DEFAULT_PORT, fileSource, options.browserProxyingEnabled());				
				wireMockServer.loadMappingsUsing(new JsonFileMappingsLoader(mappingsFileSource));
				
				wireMockServer.start();

                url = new URL("http://localhost:8080/directions?ending=" + endingPoint+ "&starting=" + startingPoint);
			}

			uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());	
			response = restCall.json(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (useFakeServer) {
			wireMockServer.stop();
		}
	}

    public String getTotalDistance() throws Exception {
        return response.get("distance").toString();
    }

    public String getTotalTime() throws Exception {
        return response.get("duration").toString();
    }

    public ArrayList<GoogleDrivingDirectionsLeg> getDirections() throws Exception {
        ArrayList<GoogleDrivingDirectionsLeg> directions = new ArrayList<GoogleDrivingDirectionsLeg>();
        JSONArray responseArray = new JSONArray(response.get("directions").toString());
        for (int i=0; i<responseArray.length(); i++) {
            directions.add(new GoogleDrivingDirectionsLeg(
                    i+1,
                    responseArray.getJSONObject(i).get("distance").toString(),
                    responseArray.getJSONObject(i).get("duration").toString(),
                    responseArray.getJSONObject(i).get("direction").toString()
                    ));
        }
        return directions;
    }
}