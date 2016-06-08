package mobilehealth.prc.collector.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mobilehealth.core.domain.Location;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FillLocation {
	public static final String DEVELOPER_KEY = "AIzaSyAA_x8u0YSYNY17ylC9NzTDxdJFxCB_s9E";
	/**
	 * Recebe o Location (semi preenchido) e complementa com informacoes que so podem ser preenchidas no Server
	 */
	
	// Testamto Commint

	
	@SuppressWarnings("finally")
    public Location reverseGeocoding(Location location){
        try{
        	String asd = "http://maps.googleapis.com/maps/api/geocode/json?latlng="+location.getLatitude()+","+location.getLongitude()+"&sensor=true&language=pt-BR";

        	JsonObject json = new JsonParser().parse(new String(send(asd).getBytes(), "UTF-8")).getAsJsonObject();
        	
        	if(json.get("status").getAsString().equals("OK")){	
        		location.setTitle(json.get("results").getAsJsonArray().get(0).getAsJsonObject().get("formatted_address").getAsString());
        		location.setDescription(location.getTitle());
        		
        		for(JsonElement address : json.get("results").getAsJsonArray().get(0).getAsJsonObject().get("address_components").getAsJsonArray()){
        			for(JsonElement types :address.getAsJsonObject().get("types").getAsJsonArray()){
        				String type = types.getAsString();
        				String ob = address.getAsJsonObject().get("long_name").getAsString();
        				
        				if(type.equals("street_number"))					location.setStreetNumber(Integer.parseInt(ob.contains("-")?ob.split("-")[0]:ob));
        				else if(type.equals("route")) 						location.setStreetName(ob);
        				else if(type.equals("locality")) 					location.setCity(ob);
        				else if(type.equals("administrative_area_level_1"))	location.setState(ob);
        				else if(type.equals("country"))						location.setCountry(ob);
        				else if(type.equals("neighborhood"))				location.setNeighborhood(ob);
        				else if(type.equals("postal_code"))					location.setCEP(ob);
        			}
        		}
        	}/*else if(json.get("status").getAsString().equals("ZERO_RESULTS")){
        		
        	}else{
        		
        	}*/
        	location.setDateCreation(Calendar.getInstance());
        }catch(Exception e){
            System.out.println("Exception reverseGeocoding: "+e.getMessage());
        }finally{
            return location;
        }
    }

	@SuppressWarnings("finally")
    private String send(String url){
        String response=null;
        HttpURLConnection connection = null;
        BufferedReader br = null;
        try {  
            //String url = "http://api.photobucket.com/login/request";  
            URL endereco = new URL(url);  
            connection = (HttpURLConnection) endereco.openConnection();  
            connection.setRequestMethod("GET");  
            connection.setDoInput(true);  
            connection.setDoOutput(false);  
            connection.connect();  

            // abre a conexao pra input  
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            // le ate o final  
            StringBuffer newData = new StringBuffer(10000);  
            String s = "";  
            while (null != ((s = br.readLine()))) {  
             newData.append(s.trim());  
            }  
            br.close();  
            response = new String(newData);
            //System.out.println(response);  
            //System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());  
            
        } catch (Exception e) {  
            System.out.println("Exception Class Server -> send(): "+e.getMessage());  
        }  finally{
        	try {
        		br.close();
        		br = null;
        		
        		connection.disconnect();
				connection = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return response;
        }
    }    
	
    public double getDistance (double p1Latitude, double p1Longitude, double p2Latitude, double p2Longitude){
        //System.out.println("Coordenadas "+p1LA+" "+p1LO+" "+p2LA+" "+p2LO);
        int r = 6371;
        
        p1Latitude = (p1Latitude * Math.PI) / 180;
        p1Longitude = (p1Longitude * Math.PI) / 180;
        p2Latitude = (p2Latitude * Math.PI) / 180;
        p2Longitude = (p2Longitude * Math.PI) / 180;
        double dLat = p2Latitude - p1Latitude;
        double dLong = p2Longitude - p1Longitude;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(p1Latitude) * Math.cos(p2Latitude) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //System.out.println("Distancia: "+(c * r));
        return (c * r);
    }
    
    public List<Location> getVicinityPlaces(Location location){
    	try{
    		List<Location> places = new ArrayList<Location>();
    		
    		/*Busca as referências dos locais próximo*/
	    	String url = "https://maps.googleapis.com/maps/api/place/search/json?location="+location.getLatitude()+","+location.getLongitude()+"&radius=500&sensor=true&language=pt-BR&key="+DEVELOPER_KEY;
	    	JsonObject json = new JsonParser().parse(new String(send(url).getBytes(), "UTF-8")).getAsJsonObject();
        	if(json.get("status").getAsString().equals("OK")){	
        		for(JsonElement address : json.get("results").getAsJsonArray()){
        			Location place = new Location();
        			JsonObject jsonLocation = address.getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");
        			
        			place.setTitle(address.getAsJsonObject().get("name").getAsString());
        			place.setDescription(place.getTitle());
        			place.setIdGoogle(address.getAsJsonObject().get("reference").getAsString());
        			place.setLatitude(jsonLocation.get("lat").getAsDouble());
        			place.setLongitude(jsonLocation.get("lng").getAsDouble());
 	
        			///*classificação do lugar*/if(address.getAsJsonObject().has("rating"))System.out.println(address.getAsJsonObject().get("rating").getAsString());
        			///*informa se está aberto ou não*/if(address.getAsJsonObject().has("opening_hours"))System.out.println(address.getAsJsonObject().getAsJsonObject("opening_hours").get("open_now").getAsBoolean());
        			if(place.getListTypes() == null) place.setListTypes(new ArrayList<String>());
        			for(JsonElement type : address.getAsJsonObject().get("types").getAsJsonArray()) place.getListTypes().add(type.getAsString());
        			places.add(place);
        		}
        	}

        	/*recupera detalhes do lugar*/
        	for(Location place : places){
        		String url2 = "https://maps.googleapis.com/maps/api/place/details/json?reference="+place.getIdGoogle()+"&sensor=true&language=pt-BR&key="+DEVELOPER_KEY;
    	    	JsonObject json2 = new JsonParser().parse(new String(send(url2).getBytes(), "UTF-8")).getAsJsonObject();
    	    	
    	    	if(json2.getAsJsonObject("result").has("address_components")){
	    	    	for(JsonElement address : json2.getAsJsonObject("result").get("address_components").getAsJsonArray()){
		    	    	for(JsonElement types :address.getAsJsonObject().get("types").getAsJsonArray()){
	        				String type = types.getAsString().trim();
	        				String ob = address.getAsJsonObject().get("long_name").getAsString();
	        				
	        				if(type.equals("street_number"))												place.setStreetNumber(Integer.parseInt(ob.contains("-")?ob.split("-")[0]:ob));
	        				else if(type.equals("route")) 													place.setStreetName(ob);
	        				else if(type.equals("locality") || type.equals("administrative_area_level_2")) 	place.setCity(ob);
	        				else if(type.equals("administrative_area_level_1"))								place.setState(ob);
	        				else if(type.equals("country"))								  					place.setCountry(ob);
	        				else if(type.equals("neighborhood"))											place.setNeighborhood(ob);
	        				else if(type.equals("postal_code"))												place.setCEP(ob);
	        			}
	    	    	}
	        		
    	    	}
        	}
        	return places;	
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Exception");
    		return null;
    	}
    }
    
    public static void main(String args[]){
    	Location location = new Location();
    	location.setLatitude(-5.17279);
    	location.setLongitude(-37.37324);
    	
    	//for(Location place : new FillLocation().getVicinityPlaces(location)) System.out.println(place);
    }
}