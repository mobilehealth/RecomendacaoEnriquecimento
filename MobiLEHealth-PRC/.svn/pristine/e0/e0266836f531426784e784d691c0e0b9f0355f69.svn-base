package mobilehealth.client_desktop.simulation.data;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.controller.inputdata.SecurityUbi;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Location;

public class GenerateLocation {

	private int size = 120;
	
	//private static ManagerServer managerServer = ManagerServer.getInstance();
	IUbi managerServer = FacadeLocal.getInstance();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new GenerateLocation()).run();

	}
	
	public void run() {
		List<Location> loc = new ArrayList<Location>();
		
		int cep = 59611520;
		Random random = new Random();

		String city;
		String state;
		ContainerCityAndState cityAndState = new ContainerCityAndState();
		
		System.out.println("\nConstrução de Locations:");

		for (int i = 0; i < size; i++) {

			Location location = new Location();
			
			Integer num = random.nextInt(2000);
			city = cityAndState.getCityAndState().get(num).getCity();
			state = cityAndState.getCityAndState().get(num).getState();
			cep = cep+i;
			
			location.setCEP(Integer.toString(cep));
			location.setAltitude( random.nextInt(1000) );
			location.setCity(city);
			location.setCountry("Brasil");
			location.setDateCreation( generateDate(i) );
			location.setDescription("Descrição Teste " + i);
			location.setIdGoogle("1");
			location.setLatitude( random.nextInt(1000) );
			location.setLongitude( random.nextInt(1000) );
			location.setNeighborhood("Bairro " + i);
			location.setRateAcceptanceHere( 0 );
			location.setRateRatingHere( 0 );
			location.setRating( 0 );
			location.setState(state);
			location.setCity(city);
			location.setStreetName("Rua Teste " + i);
			location.setStreetNumber(i);
			location.setTitle("Titulo " + i);
			location.setVisibility( Location.VIS_PUBLIC);
			location.setFrequency(null);
						
			loc.add(location);
		}
		System.out.println("\nGerados:" + loc.size()+" Locations.");	
		saveServer(loc);
		System.out.println("\nSalvos:" + loc.size()+" Locations.");	

		//saveInFile(loc);
	}

	private void saveServer(List<Location> loc) {
	
		for(Location location : loc) {
			managerServer.createLocation(location);
		}
		
	}

	private void saveInFile(List<Location> loc) {

		try {
			PrintWriter out = new PrintWriter("teste_location.txt");

			for (Location location : loc) {
				// Apagar esse trecho depois
				String strdate = null;

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

				if (location.getDateCreation() != null) {
					strdate = sdf.format(location.getDateCreation().getTime());
				}

				String imprimir = location.getId() + "|"
						+ location.getCEP() + "|" + location.getCity() + "|"
						+ location.getCountry() + "|" + strdate + "|"
						+ location.getDescription() + "|" + location.getIdGoogle();

				out.println(imprimir);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private Calendar generateDate(int i) {

		int year, month, day;
		int age = 0;

		Calendar date = Calendar.getInstance();

		Calendar currentDate = Calendar.getInstance();
		int currentYear = currentDate.get(Calendar.YEAR);
		int currentMonth = currentDate.get(Calendar.MONTH);
		int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

		Random random = new Random();

		if (i < this.size/2) { 
			age = 0;
		} else if (i < 3* this.size / 4) { 
			age = 1;
		} else { 
			age = 2;
		}

		year = currentYear - age;
		month = random.nextInt(currentMonth + 1);
		if (month == currentMonth) {
			day = random.nextInt(currentDay) + 1;
		} else {
			date.set(year, month, 1);
			date.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = date.get(Calendar.DAY_OF_MONTH);
		}

		date.set(year, month, day);

		return date;
	}
	
	
	
}
