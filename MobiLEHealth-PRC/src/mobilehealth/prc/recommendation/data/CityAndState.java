package mobilehealth.prc.recommendation.data;

import java.io.Serializable;

public class CityAndState implements Serializable {
	
	private static final long serialVersionUID = 3737375833725727911L;
	private Integer id;
	private String city;
	private String state;

	public CityAndState(Integer id, String city, String state) {
		this.id = id;
		this.city = city;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CityAndState [id=" + id + ", city=" + city + ", state=" + state
				+ "]";
	}
	

	
}
