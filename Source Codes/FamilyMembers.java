import java.util.ArrayList;
import java.io.Serializable;
/**
 * This class is used to  constructs each of the family members contained in a tree
 * @author Kyaw Sitt Tway
 *
 */

public class FamilyMembers implements Serializable{

private String firstName;
private String surName;
private String surName_marriage;
private String LifeDescription;
private Address address;
private gender Gender;
private FamilyMembers Father;
private FamilyMembers Mother;
private FamilyMembers Spouse;
private ArrayList<FamilyMembers> child;
/**
 * Constructor methods with parameters for furthers usese in the GUI class which can make 
 * validation and construction of each member easier
 * @param first_name
 * @param last_name
 * @param surNameMarriage
 * @param Life_Description
 * @param address_
 * @param Gender_
 */

public FamilyMembers(String first_name,String last_name,String surNameMarriage,String Life_Description,Address address_,gender Gender_)
{
	setFirstName(first_name);
	setSurName(last_name);
	setSurName_marriage(surNameMarriage);
	setLifeDescription(Life_Description);
	setAddress(address_);
	setGender(Gender_);
	this.Father = null;
	this.Mother = null;
	this.Spouse =null;
	this.child = new ArrayList<FamilyMembers>();
	
	
}
/**
 * This gender enum is created to set the value of the members,to use in validaiton purpose and to use in GUI choicebox
 * @author Kyaw Sitt Tway
 *
 */

public enum gender
{
	MALE,
	FEMALE
}
/**
 * This Family_Members enum is created to set  the value of the members,to use in validaiton purpose and to use in GUI choicebox
 * @author Kyaw Sitt Tway
 *
 */
public enum Family_Members{
	FATHER,
	MOTHER,
	SPOUSE,
	CHILDREN
}


/**
 * 
 * @return firstName
 */
public String getFirstName() {
	return firstName;
}

/**
 * 
 * @param firstName
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * 
 * @return surName
 */
public String getSurName() {
	return surName;
}

/**
 * 
 * @param surName
 */
public void setSurName(String surName) {
	this.surName = surName;
}

/**
 * 
 * @return surName_marriage
 */
public String getSurName_marriage() {
	return surName_marriage;
}

/**
 * 
 * @param surName_marriage
 */
public void setSurName_marriage(String surName_marriage) {
	this.surName_marriage = surName_marriage;
}

/**
 * 
 * @return LifeDescription
 */
public String getLifeDescription() {
	return LifeDescription;
}

/**
 * 
 * @param lifeDescription
 */
public void setLifeDescription(String lifeDescription) {
	LifeDescription = lifeDescription;
}

/**
 * 
 * @return address
 */
public Address getAddress() {
	return address;
}

/**
 * 
 * @param address
 */
public void setAddress(Address address) {
	this.address = address;
}

/**
 * 
 * @return Gender
 */
public gender getGender() {
	return Gender;
}

/**
 * 
 * @param gender
 */
public void setGender(gender gender) {
	Gender = gender;
}

/**
 * 
 * @return Father
 */
public FamilyMembers getFather() {
	return Father;
}

/**
 * This method is to set the father of base person and set the related members of  the family
 * @param father
 */
public void setFather(FamilyMembers father) {
	Father = father;
	if(!Father.getChild().contains(this)) {
		Father.addChild(this);
	}
	if(this.getMother()!=null) {
		Father.setSpouse(this.getMother());
	}
}

/**
 * 
 * @return Mother
 */
public FamilyMembers getMother() {
	return Mother;
}

/**
 * This method is to set the mother of base person and set the related members of  the family
 * @param mother
 */
public void setMother(FamilyMembers mother) {
	Mother = mother;
	if(!Mother.getChild().contains(this)) {
		Mother.addChild(this);
	}
	if(this.getFather()!=null) {
		Mother.setSpouse(this.getFather());
	}
}

/**
 * 
 * @return Spouse;
 */
public FamilyMembers getSpouse() {
	return Spouse;
}

/**
 * 
 * This method is to set the spouse of base person and set the related members of  the family
 * 
 *
 * @param spouse
 */
public void setSpouse(FamilyMembers spouse) {
	Spouse = spouse;
	if(Spouse.getSpouse()==null) {
	Spouse.setSpouse(this);
	Spouse.setChild(this.getChild());
	}
}

/**
 * 
 * @return child
 */
public ArrayList<FamilyMembers> getChild() {
	return child;
}

/**
 * 
 * This method is to set the children of base person and set the related members of  the family
 * 
 *
 * @param child
 */
public void setChild(ArrayList<FamilyMembers> child) {
	this.child = child;
}

/**
 * This method is used to add the children to a member
 * @param add_child
 */
public void addChild(FamilyMembers add_child)
{
	child.add(add_child);
	if(this.getGender()==gender.MALE) {
		add_child.setFather(this);
		add_child.setMother(this.getSpouse());
		
	}else {
		add_child.setMother(this);
		add_child.setFather(this.getSpouse());
	}
}







	


}
