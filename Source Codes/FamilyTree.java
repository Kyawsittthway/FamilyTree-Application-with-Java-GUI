import java.io.Serializable;
/**
 * This class performed as the implementation of the FamilyMembers
 * @author Kyaw Sitt Tway
 *
 */
public class FamilyTree implements Serializable{
	 private static final long serialVersionUID = 1;
	 private FamilyMembers BaseMember;
	 
/**
 * Constructor method
 */
public FamilyTree()
{
	this.BaseMember=null;
}

/**
 * To set the base member 
 * @param Member
 */
public void setBaseMember(FamilyMembers Member)
{
	BaseMember = Member;
}

/**
 * 
 * @return BaseMember;
 */
public FamilyMembers GetBaseMember()
{
	return this.BaseMember;
}

/**
 * a method to check whether the base person this tree is empty or not
 * @return
 */
public boolean isEmpty()
{
	if(this.BaseMember==null)
	{
		return true;
	}
	else
	{
		return false;
	}
}
}
