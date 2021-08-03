import java.io.Serializable;
/**
 * This class is used to create the required address which contain variables relating to address for constructing FamilyMember class
 * @author Kyaw Sitt Tway
 *
 */
public class Address implements Serializable {

	private String streetNumber,streetName,suburb,postcode;

	/**
	 * Constructor method with parameters required to create the Address object for the member class
	 * @param street_number
	 * @param street_name
	 * @param suburb_
	 * @param postcode_
	 */
	public Address(String street_number , String street_name,String suburb_,String postcode_)
	{
	setStreetNumber(street_number);
	setStreetName(street_name);
	setSuburb(suburb_);
	setPostcode(postcode_);
	
	}
	
	/**
	 * To get the streetNumber of the address
	 * @return streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	
	/**
	 * To set the streetNumber of the address
	 * @param streetNumber
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	/**
	 * To get the streetName of the address
	 * @return streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	
	/**
	 * To set the streetName of the address
	 * @param streetName
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	/**
	 * To get the suburb of the address
	 * @return
	 */
	public String getSuburb() {
		return suburb;
	}
	
	/**
	 * To set the surburb of the address
	 * @param suburb
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	/**
	 * To get the postcode of the address
	 * @return
	 */
	public String getPostcode() {
		return postcode;
	}
	
	/**
	 * To set the postcode of the address
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	


}
