

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.util.regex.*;
import java.io.*;
/**
 * This is the GUI to interact with users' input action and performed the function according to it
 * @author Kyaw Sitt Tway
 *
 */

public class GUI_Fx extends Application implements EventHandler<ActionEvent>{

	private static final String LifeDescription_base = null;
	static Stage window;
	static Scene scene,scene1,scene2,scene3,scene4;
	private static FamilyMembers BasePerson;
	private static FamilyTree currentTree;
	private static Address BasePersonAddress;
	private static File file;
	

	public static void main(String []args)
	{
		launch(args);
	}
	
	
	/**
	 * this method will perform a set of method required to run when the GUI is started
	 */
	public void start(Stage primaryStage)throws Exception
	{
		currentTree = new FamilyTree();
		window = primaryStage;
		window.setTitle("Family Tree Application");
		createRootPerson();
		menu1();
		
	
	}
	
	/**
	 * This method will displays all the buttons from main menu
	 * @return
	 */
	public GridPane menuButtons() {
	
		//Creating a gridpane to set up the layouts
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(10,10,10,10));
		grid1.setVgap(8);
		grid1.setHgap(10);
		
		
		//Creating the label for welcome
		Label label = new Label("Welcome to the family tree application");
		label.setFont(Font.font("Arial",FontWeight.BOLD,16));
		GridPane.setConstraints(label,0,0);
		
		//Creating the button "Create"
		Button button1 = new Button("Create a base person");
		GridPane.setConstraints(button1,0,1);
		button1.setOnAction(e->window.setScene(scene1));
		
		//Creating the button "Create"
		Button button7 = new Button("Create a new tree");
		GridPane.setConstraints(button7,0,5);
		button7.setOnAction(e->create_newTree());
		
		//Creating the button "Save"
		Button button2 = new Button("Save");
		GridPane.setConstraints(button2,0,2);
		button2.setOnAction(e->save_Tree(window,currentTree));
		
		//Creating the button "Load"
		Button button3 = new Button("Load");
		GridPane.setConstraints(button3,0,3);
		button3.setOnAction(e->load_Tree(window,currentTree));
		
		//crating the button "Exit"
		Button button4 = new Button("Add a relative");
		GridPane.setConstraints(button4,0,4);
		button4.setOnAction(e->addRelative());
		
		TreeView treeView = new TreeView();
		GridPane.setConstraints(treeView,0,10);
		ArrayList<TreeItem>Family = new ArrayList<TreeItem>();
		
		//BasePersonAddress = new Address("31","Linga","31Q","112233");
		//BasePerson=new FamilyMembers("Lisa","Blink","","Lawer",BasePersonAddress,FamilyMembers.gender.FEMALE);
		
		//Creating the treeView
		TreeItem Main = new TreeItem("Main Person");
		TreeItem father = new TreeItem("FATHER");
		TreeItem mother = new TreeItem("MOTHER");
		TreeItem spouse = new TreeItem("SPOUSE");
		TreeItem children = new TreeItem("CHILDREN");
		TreeItem baseMain;
		TreeItem fatherMain;
		TreeItem motherMain ;
		TreeItem childrenMain = null;
		TreeItem spouseMain;
		
		/**
		 * if the person's father ,mother , spouse, child are null, the tree will show "No data" for them in tree else it will show their names
		 */
		
		if(currentTree.GetBaseMember()==null)
		{
			baseMain = new TreeItem("No data");
		}
		else {
			baseMain = new TreeItem(currentTree.GetBaseMember().getFirstName()+" "+currentTree.GetBaseMember().getSurName());
		
		Main.getChildren().addAll(baseMain);
		Main.getChildren().addAll(father);
		Main.getChildren().addAll(mother);
		Main.getChildren().addAll(spouse);
		Main.getChildren().addAll(children);
		Main.setExpanded(true);
		if(currentTree.GetBaseMember().getFather()==null)
		{
			fatherMain = new TreeItem("No data");
		}else
		{
			fatherMain = new TreeItem(currentTree.GetBaseMember().getFather().getFirstName()+" "+currentTree.GetBaseMember().getFather().getSurName());
		}
		father.getChildren().addAll(fatherMain);
		father.setExpanded(true);
		if(currentTree.GetBaseMember().getMother()==null)
		{
			motherMain = new TreeItem("No data");
		}else
		{
			motherMain = new TreeItem(currentTree.GetBaseMember().getMother().getFirstName()+" "+currentTree.GetBaseMember().getMother().getSurName());
		}
		mother.getChildren().addAll(motherMain);
		mother.setExpanded(true);
		if(currentTree.GetBaseMember().getSpouse()==null)
		{
			spouseMain = new TreeItem("No data");
		}else
		{
			spouseMain = new TreeItem(currentTree.GetBaseMember().getSpouse().getFirstName()+" "+currentTree.GetBaseMember().getSpouse().getSurName());
		}
		spouse.getChildren().addAll(spouseMain);
		spouse.setExpanded(true);
		if(currentTree.GetBaseMember().getChild()==null)
		{
			childrenMain = new TreeItem("No data");
		}else
		{
			for(int i=0;i<currentTree.GetBaseMember().getChild().size();i++) {
			childrenMain = new TreeItem(currentTree.GetBaseMember().getChild().get(i).getFirstName()+" "+currentTree.GetBaseMember().getChild().get(i).getSurName());
			children.getChildren().addAll(childrenMain);
			children.setExpanded(true);
			}
		}
	
		//currentTree.setBaseMember(BasePerson);
		Family.add(Main);
		treeView.setRoot(Main);
		}
		
		//Creating button "show details" to show the details of the members
		Button button5 = new Button("Show Details");
		GridPane.setConstraints(button5, 0, 11);
		button5.setOnAction(e->showDetails(treeView,currentTree,grid1));
		
		//Edit member
		Button button6 = new Button ("Edit Members");
		GridPane.setConstraints(button6, 1, 11);
		button6.setOnAction(e->editRelative_Treetracker(currentTree.GetBaseMember(),treeView));
		
		
		
		grid1.getChildren().addAll(label,button1,button7,button2,button3,button4,button5,treeView,button6);
		scene = new Scene(grid1,600,600);
		window.setScene(scene);
		window.show();
		
	
		return grid1;
		
	}
	
	/**
	 * The scene to add information about the root member
	 * @return
	 */
	
	public GridPane createRootPerson()
	{

		
		//GridPane to set the layout
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(10,10,10,10));
		grid1.setVgap(8);
		grid1.setHgap(10);
		
		//Name label
		Label firstname= new  Label("First Name");
		GridPane.setConstraints(firstname,0,0);
		
		//Name input
		TextField firstname_input = new TextField();
		GridPane.setConstraints(firstname_input, 1, 0);
		
		//Lastname Label
		Label lastname = new Label("Sur Name");
		GridPane.setConstraints(lastname, 0, 1);
		
		//Lastname input
		TextField lastname_input= new TextField();
		lastname_input.setPromptText("Enter Sur name");
		GridPane.setConstraints(lastname_input,1 , 1);
		
		//Lastname2 label
		Label lastname2 = new Label("Family Last Name");
		GridPane.setConstraints(lastname2,0,2);
		
		//Lastname2 input
		TextField lastname2_input = new TextField();
		lastname2_input.setPromptText("Only for females");
		GridPane.setConstraints(lastname2_input,1,2);
		
		//LifeDescirption label
		Label LifeDescription = new Label("Life Description");
		GridPane.setConstraints(LifeDescription, 0, 3);
		
		//LifeDescriptin Input
		TextField LifeDescription_input = new TextField();
		GridPane.setConstraints(LifeDescription_input, 1, 3);
		
		//StreetNo Label
		Label StreetNo = new Label("Street Number");
		GridPane.setConstraints(StreetNo, 0, 4);
		
		//StreetNo Input
		TextField StreetNo_input = new TextField();
		GridPane.setConstraints(StreetNo_input, 1, 4);
		
		//StreetName label
		Label StreetName = new Label("Street Name");
		GridPane.setConstraints(StreetName, 0, 5);
		
		//StreetName Input
		TextField StreetName_input = new TextField();
		GridPane.setConstraints(StreetName_input, 1, 5);
		
		//Suburb Label
		Label Suburb = new Label("Suburb");
		GridPane.setConstraints(Suburb, 0, 6);
		
		//Suburb Input
		TextField Suburb_input = new TextField();
		GridPane.setConstraints(Suburb_input, 1, 6);
		
		//Postcode Label
		Label Postcode = new Label("Post Code");
		GridPane.setConstraints(Postcode, 0, 7);
		
		//Postcode Input
		TextField Postcode_input = new TextField();
		GridPane.setConstraints(Postcode_input, 1, 7);

		//Gender Label
		Label genderLabel = new Label("Gender");
		GridPane.setConstraints(genderLabel, 0, 8);
		
		//Gender choice box
		ChoiceBox<FamilyMembers.gender> gender = new ChoiceBox<>();
		gender.getItems().add(gender.getValue().MALE);
		gender.getItems().add(gender.getValue().FEMALE);
		GridPane.setConstraints(gender, 1, 8);
		
		
		//Extracting the values from textfield
		Button accept = new Button("Create");
		GridPane.setConstraints(accept,0,9);
		accept.setOnAction(e->{
	
			String firstname_base = firstname_input.getText();
			String lastname_base = lastname_input.getText();
			String lastname2_base = lastname2_input.getText();
			String LifeDescription_base = LifeDescription_input.getText();
			String streetNo_base = StreetNo_input.getText();
			String streetName_base = StreetName_input.getText();
			String suburb_base = Suburb_input.getText();
			String Postcode_base = Postcode_input.getText();
			/**
			 * Validation of the input fields if it is valid, the member will be created
			 */
			try{
			if((gender.getValue().equals(FamilyMembers.gender.MALE)) || gender.getValue().equals(FamilyMembers.gender.FEMALE))
			{
			
			
			if((!overallCheck(firstname_base,lastname_base,lastname2_base,LifeDescription_base,streetNo_base,streetName_base,suburb_base,Postcode_base) ))
			{
			/*Alert.display("Invalid", "Fill up all fields!");
		
				System.out.println("Here");
			}else if(!validationCheck_Base2(firstname_base,lastname_base,lastname2_base)){
				System.out.println("Number mistake");
				*/
			}else {
			
		//System.out.println("There");
			BasePersonAddress = new Address(streetNo_base,streetName_base,suburb_base,Postcode_base);
			BasePerson = new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,BasePersonAddress,gender.getValue());
			//currentTree.setBaseMember(BasePerson);
			basePerson_create(BasePerson);
			//System.out.println(currentTree.GetBaseMember().getFirstName());
			//System.out.println(BasePerson.getFirstName());
			menuButtons();
			window.setScene(scene);
			}
		}}catch(Exception e1) {
			Alert.display("Error","Invalid: Choose one value for gender");
			createRootPerson();
		}
			});

		
		Button cancel = new Button("Cancel");
		GridPane.setConstraints(cancel,1,9);
		cancel.setOnAction(e->menu1());
		
		grid1.getChildren().addAll(firstname,firstname_input,lastname,lastname_input,lastname2,lastname2_input,LifeDescription,LifeDescription_input, StreetNo,StreetNo_input,StreetName,StreetName_input,Suburb,Suburb_input,Postcode,Postcode_input,genderLabel,gender,accept,cancel);
		 scene1 = new Scene(grid1,600,600);
		window.setScene(scene1);
		window.show();
		return grid1;
		
	}
	
	
	/**
	 * This method is to add the relative of the base members
	 */
	
	public void addRelative() {
		//GridPane to set the layout
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(10,10,10,10));
		grid1.setVgap(8);
		grid1.setHgap(10);
		
		//Name label
		Label firstname= new  Label("First Name");
		GridPane.setConstraints(firstname,0,0);
		
		//Name input
		TextField firstname_input = new TextField();
		GridPane.setConstraints(firstname_input, 1, 0);
		
		//Lastname Label
		Label lastname = new Label("Sur Name");
		GridPane.setConstraints(lastname, 0, 1);
		
		//Lastname input
		TextField lastname_input= new TextField();
		lastname_input.setPromptText("Enter Sur name");
		GridPane.setConstraints(lastname_input,1 , 1);
		
		//Lastname2 label
		Label lastname2 = new Label("Family Last Name");
		GridPane.setConstraints(lastname2,0,2);
		
		//Lastname2 input
		TextField lastname2_input = new TextField();
		lastname2_input.setPromptText("Only for females");
		GridPane.setConstraints(lastname2_input,1,2);
		
		//LifeDescirption label
		Label LifeDescription = new Label("Life Description");
		GridPane.setConstraints(LifeDescription, 0, 3);
		
		//LifeDescriptin Input
		TextField LifeDescription_input = new TextField();
		GridPane.setConstraints(LifeDescription_input, 1, 3);
		
		//StreetNo Label
		Label StreetNo = new Label("Street Number");
		GridPane.setConstraints(StreetNo, 0, 4);
		
		//StreetNo Input
		TextField StreetNo_input = new TextField();
		GridPane.setConstraints(StreetNo_input, 1, 4);
		
		//StreetName label
		Label StreetName = new Label("Street Name");
		GridPane.setConstraints(StreetName, 0, 5);
		
		//StreetName Input
		TextField StreetName_input = new TextField();
		GridPane.setConstraints(StreetName_input, 1, 5);
		
		//Suburb Label
		Label Suburb = new Label("Suburb");
		GridPane.setConstraints(Suburb, 0, 6);
		
		//Suburb Input
		TextField Suburb_input = new TextField();
		GridPane.setConstraints(Suburb_input, 1, 6);
		
		//Postcode Label
		Label Postcode = new Label("Post Code");
		GridPane.setConstraints(Postcode, 0, 7);
		
		//Postcode Input
		TextField Postcode_input = new TextField();
		GridPane.setConstraints(Postcode_input, 1, 7);

		//Gender Label
		Label genderLabel = new Label("Gender");
		GridPane.setConstraints(genderLabel, 0, 8);
		
		//Gender choice box
		ChoiceBox<FamilyMembers.gender> gender = new ChoiceBox<>();
		gender.getItems().add(gender.getValue().MALE);
		gender.getItems().add(gender.getValue().FEMALE);
		GridPane.setConstraints(gender, 1, 8);
		

		//Gender Label
		Label relativeLabel = new Label("Relative Type");
		GridPane.setConstraints(relativeLabel, 0, 9);
		
		//Relative Type choice Box
		ChoiceBox<FamilyMembers.Family_Members> members = new ChoiceBox<>();
		members.getItems().add(members.getValue().FATHER);
		members.getItems().add(members.getValue().MOTHER);
		members.getItems().add(members.getValue().SPOUSE);
		members.getItems().add(members.getValue().CHILDREN);
		GridPane.setConstraints(members, 1, 9);
		
		
		//Add button
		Button add = new Button("Add");
		GridPane.setConstraints(add,0,10);
		add.setOnAction(e->{
			String firstname_base = firstname_input.getText();
			String lastname_base = lastname_input.getText();
			String lastname2_base = lastname2_input.getText();
			String LifeDescription_base = LifeDescription_input.getText();
			String streetNo_base = StreetNo_input.getText();
			String streetName_base = StreetName_input.getText();
			String suburb_base = Suburb_input.getText();
			String Postcode_base = Postcode_input.getText();
			/**
			 * Validation of the input fields if it is valid, the member will be created
			 */
			try{
				if((
						(gender.getValue().equals(FamilyMembers.gender.MALE)) || gender.getValue().equals(FamilyMembers.gender.FEMALE)) 
						 &&
						(members.getValue().equals(FamilyMembers.Family_Members.CHILDREN)||
						(members.getValue().equals(FamilyMembers.Family_Members.SPOUSE)	 ||
						(members.getValue().equals(FamilyMembers.Family_Members.FATHER)) ||
						(members.getValue().equals(FamilyMembers.Family_Members.MOTHER))
				  )))
				{
				
				
				if((!overallCheck(firstname_base,lastname_base,lastname2_base,LifeDescription_base,streetNo_base,streetName_base,suburb_base,Postcode_base) ))
				{
				/*Alert.display("Invalid", "Fill up all fields!");
			
					System.out.println("Here");
				}else if(!validationCheck_Base2(firstname_base,lastname_base,lastname2_base)){
					System.out.println("Number mistake");
					*/
				}else {
			
			
		
			Address relativeAddress = new Address(streetNo_base,streetName_base,suburb_base,Postcode_base);
			FamilyMembers relative ;

			//System.out.println(relative.getFirstName());
		
			//will create a relative if they are are null in the base memebrs and the choicebox value is correct
			if(currentTree.GetBaseMember().getFather()==null && members.getValue().equals(FamilyMembers.Family_Members.FATHER))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setFather(relative);
			}
			if(currentTree.GetBaseMember().getMother()==null && members.getValue().equals(FamilyMembers.Family_Members.MOTHER))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setMother(relative);
			}
			if(currentTree.GetBaseMember().getSpouse()==null && members.getValue().equals(FamilyMembers.Family_Members.SPOUSE))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setSpouse(relative);
			}
			if(members.getValue().equals(FamilyMembers.Family_Members.CHILDREN))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().addChild(relative);
			
			}
		
			menuButtons();
			window.setScene(scene);
		}}
				}catch(Exception r) {
					Alert.display("Error","Invalid: Choose one value for gender and Relative Type");
				}
		}
		);
		//alert box to be added
		
		
		//cancel button
		Button cancel = new Button("Cancel");
		GridPane.setConstraints(cancel,1,10);
		cancel.setOnAction(e->menu1());
	
		grid1.getChildren().addAll(firstname,firstname_input,lastname,lastname_input,lastname2,lastname2_input,LifeDescription,LifeDescription_input, StreetNo,StreetNo_input,StreetName,StreetName_input,Suburb,Suburb_input,Postcode,Postcode_input,genderLabel,gender,relativeLabel,members,add,cancel);
		
		scene2 = new Scene(grid1,600,600);
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * This method will track the selected member in the tree to show the detail of it
	 * @param person
	 * @param detailView
	 */
	public void editRelative_Treetracker(FamilyMembers person , TreeView detailView)
	{
		
		try {
			
			if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.getFirstName()+" "+person.getSurName() +" ]"))
			{
				editRelative(person);
			}
			if(person.getSpouse()!=null) {
			 if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.getSpouse().getFirstName()+" "+person.getSpouse().getSurName()  +" ]"))
			{
				editRelative(person.getSpouse());
			}
			}
			 if(person.getFather()!=null)
			{
			if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.getFather().getFirstName()+" "+person.getFather().getSurName()+" ]"))
			{
				//System.out.println("here");
				editRelative(person.getFather());
			}
			}
			 if(person.getMother()!=null) {
			if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.getMother().getFirstName()+" "+person.getMother().getSurName() +" ]"))
			{
				editRelative(person.getMother());
				
			}
		    }
		    if(person.getChild()!=null){
				for(int i =0;i<person.getChild().size();i++)
				{
					if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.getChild().get(i).getFirstName()+" "+person.getChild().get(i).getSurName() +" ]"))
					editRelative(person.getChild().get(i));
				}
			}
		}catch(Exception e){
		
	}}
	
	/**
	 * This method is used to display the edit panel of the members and to provide the edit funciton of it
	 * @param member
	 */
	public void editRelative(FamilyMembers member) {
		//GridPane to set the layout
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(10,10,10,10));
		grid1.setVgap(8);
		grid1.setHgap(10);
		
		
		//Name label
		Label firstname= new  Label("First Name");
		GridPane.setConstraints(firstname,0,0);
		
		//Name input
		TextField firstname_input = new TextField(member.getFirstName());
		GridPane.setConstraints(firstname_input, 1, 0);
		
		//Lastname Label
		Label lastname = new Label("Sur Name");
		GridPane.setConstraints(lastname, 0, 1);
		
		//Lastname input
		TextField lastname_input= new TextField(member.getSurName());
		lastname_input.setPromptText("Enter Sur name");
		GridPane.setConstraints(lastname_input,1 , 1);
		
		//Lastname2 label
		Label lastname2 = new Label("Family Last Name");
		GridPane.setConstraints(lastname2,0,2);
		
		//Lastname2 input
		TextField lastname2_input = new TextField(member.getSurName_marriage());
		lastname2_input.setPromptText("Only for females");
		GridPane.setConstraints(lastname2_input,1,2);
		
		//LifeDescirption label
		Label LifeDescription = new Label("Life Description");
		GridPane.setConstraints(LifeDescription, 0, 3);
		
		//LifeDescriptin Input
		TextField LifeDescription_input = new TextField(member.getLifeDescription());
		GridPane.setConstraints(LifeDescription_input, 1, 3);
		
		//StreetNo Label
		Label StreetNo = new Label("Street Number");
		GridPane.setConstraints(StreetNo, 0, 4);
		
		//StreetNo Input
		TextField StreetNo_input = new TextField(member.getAddress().getStreetNumber());
		GridPane.setConstraints(StreetNo_input, 1, 4);
		
		//StreetName label
		Label StreetName = new Label("Street Name");
		GridPane.setConstraints(StreetName, 0, 5);
		
		//StreetName Input
		TextField StreetName_input = new TextField(member.getAddress().getStreetName());
		GridPane.setConstraints(StreetName_input, 1, 5);
		
		//Suburb Label
		Label Suburb = new Label("Suburb");
		GridPane.setConstraints(Suburb, 0, 6);
		
		//Suburb Input
		TextField Suburb_input = new TextField(member.getAddress().getSuburb());
		GridPane.setConstraints(Suburb_input, 1, 6);
		
		//Postcode Label
		Label Postcode = new Label("Post Code");
		GridPane.setConstraints(Postcode, 0, 7);
		
		//Postcode Input
		TextField Postcode_input = new TextField(member.getAddress().getPostcode());
		GridPane.setConstraints(Postcode_input, 1, 7);

		//Gender Label
		Label genderLabel = new Label("Gender");
		GridPane.setConstraints(genderLabel, 0, 8);
		
		//Gender choice box
		ChoiceBox<FamilyMembers.gender> gender = new ChoiceBox<>();
		gender.getItems().add(gender.getValue().MALE);
		gender.getItems().add(gender.getValue().FEMALE);
		gender.getValue();
		GridPane.setConstraints(gender, 1, 8);
		

		//Gender Label
		Label relativeLabel = new Label("Relative Type");
		GridPane.setConstraints(relativeLabel, 0, 9);
		
		//Relative Type choice Box
		ChoiceBox<FamilyMembers.Family_Members> members = new ChoiceBox<>();
		members.getItems().add(members.getValue().FATHER);
		members.getItems().add(members.getValue().MOTHER);
		members.getItems().add(members.getValue().SPOUSE);
		members.getItems().add(members.getValue().CHILDREN);
		members.getValue();
		GridPane.setConstraints(members, 1, 9);
		
		
		//Add button
		Button add = new Button("Edit");
		GridPane.setConstraints(add,0,10);
		add.setOnAction(e->{
			String firstname_base = firstname_input.getText();
			String lastname_base = lastname_input.getText();
			String lastname2_base = lastname2_input.getText();
			String LifeDescription_base = LifeDescription_input.getText();
			String streetNo_base = StreetNo_input.getText();
			String streetName_base = StreetName_input.getText();
			String suburb_base = Suburb_input.getText();
			String Postcode_base = Postcode_input.getText();
			
			try {
			if((
					(gender.getValue().equals(FamilyMembers.gender.MALE)) || gender.getValue().equals(FamilyMembers.gender.FEMALE)) 
					 &&
					(members.getValue().equals(FamilyMembers.Family_Members.CHILDREN)||
					(members.getValue().equals(FamilyMembers.Family_Members.SPOUSE)	 ||
					(members.getValue().equals(FamilyMembers.Family_Members.FATHER)) ||
					(members.getValue().equals(FamilyMembers.Family_Members.MOTHER))
			  )))
			{
			
			
			if((!overallCheck(firstname_base,lastname_base,lastname2_base,LifeDescription_base,streetNo_base,streetName_base,suburb_base,Postcode_base) ))
			{
			/*Alert.display("Invalid", "Fill up all fields!");
		
				System.out.println("Here");
			}else if(!validationCheck_Base2(firstname_base,lastname_base,lastname2_base)){
				System.out.println("Number mistake");
				*/
			}else {
			Address relativeAddress = new Address(streetNo_base,streetName_base,suburb_base,Postcode_base);
			FamilyMembers relative ;
			if(member==currentTree.GetBaseMember()) {
				relative = new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				menuButtons();
				
				window.setScene(scene);
			}else {
			//System.out.println(relative.getFirstName());
		
			//will create a relative if they are are null in the base memebrs and the choicebox value is correct
			if( members.getValue().equals(FamilyMembers.Family_Members.FATHER))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setFather(relative);
			}
			if( members.getValue().equals(FamilyMembers.Family_Members.MOTHER))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setMother(relative);
			}
			if( members.getValue().equals(FamilyMembers.Family_Members.SPOUSE))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().setSpouse(relative);
			}
			if(members.getValue().equals(FamilyMembers.Family_Members.CHILDREN))
			{
				relative =	new FamilyMembers(firstname_base,lastname_base,lastname2_base,LifeDescription_base,relativeAddress,gender.getValue());
				currentTree.GetBaseMember().addChild(relative);
			
			}
		
			menuButtons();
			window.setScene(scene);
			}
			}}}catch(Exception k){
				Alert.display("Error","Invalid: Choose one value for gender and Relative Type");
			}
		});
		//alert box to be added
		
		
		//cancel button
		Button cancel = new Button("Cancel");
		GridPane.setConstraints(cancel,1,10);
		cancel.setOnAction(e->menuButtons());
		
		//If the member passed is base person, there will be no relative choicebox is shown
		if(member==currentTree.GetBaseMember())
		{
		grid1.getChildren().addAll(firstname,firstname_input,lastname,lastname_input,lastname2,lastname2_input,LifeDescription,LifeDescription_input, StreetNo,StreetNo_input,StreetName,StreetName_input,Suburb,Suburb_input,Postcode,Postcode_input,genderLabel,gender,add,cancel);
		}
		else {
			grid1.getChildren().addAll(firstname,firstname_input,lastname,lastname_input,lastname2,lastname2_input,LifeDescription,LifeDescription_input, StreetNo,StreetNo_input,StreetName,StreetName_input,Suburb,Suburb_input,Postcode,Postcode_input,genderLabel,gender,relativeLabel,members,add,cancel);
		}
		scene2 = new Scene(grid1,600,600);
		window.setScene(scene2);
		window.show();
	}
	
	/**
	 * This method is to display the welcoming menu
	 * @return
	 */

	public GridPane menu1()
	{
		GridPane grid1 = new GridPane();
		grid1.setPadding(new Insets(10,10,10,10));
		grid1.setVgap(8);
		grid1.setHgap(10);
		
		
		//Creating the label for welcome
		Label label = new Label("Welcome to the family tree application");
		GridPane.setConstraints(label,0,0);
		label.setFont(Font.font("Arial",FontWeight.BOLD,16));
		
		//Creating the button "Create"
		Button button1 = new Button("Get Started!");
		GridPane.setConstraints(button1,0,1);
		button1.setOnAction(e->menuButtons());
		

		
		//Creating the button "Load"
		Button button3 = new Button("Exit");
		GridPane.setConstraints(button3,0,3);
		button3.setOnAction(e->window.close());
		
		grid1.getChildren().addAll(label,button1,button3);
		scene3 = new Scene(grid1,300,300);
		window.setScene(scene3);
		window.show();
		
	
		return grid1;
	}
	//Inserting the base person into the object serialization
	public void basePerson_create(FamilyMembers root_BasePerson)
	{
		
		currentTree= new FamilyTree();
		currentTree.setBaseMember(root_BasePerson);
		
	}

	//Method to show details of the members when the button is clicked
	/**
	 * This method is used to show the details of the member selected
	 * @param detailView
	 * @param person
	 * @param gp
	 */
	public void showDetails(TreeView detailView,FamilyTree person,GridPane gp)
	{
	
		
		//To handle the error where the person to show detail is not created yet
			try {
				
				if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.GetBaseMember().getFirstName()+" "+person.GetBaseMember().getSurName() +" ]"))
				{
					details(person.GetBaseMember(),gp);
				}
				if(person.GetBaseMember().getSpouse()!=null) {
				 if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.GetBaseMember().getSpouse().getFirstName()+" "+person.GetBaseMember().getSpouse().getSurName()  +" ]"))
				{
					details(person.GetBaseMember().getSpouse(),gp);
				}
				}
				if(person.GetBaseMember().getFather()!=null)
				{
				if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.GetBaseMember().getFather().getFirstName()+" "+person.GetBaseMember().getFather().getSurName()+" ]"))
				{
					details(person.GetBaseMember().getFather(),gp);
				}
				}
				if(person.GetBaseMember().getMother()!=null) {
				if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.GetBaseMember().getMother().getFirstName()+" "+person.GetBaseMember().getMother().getSurName() +" ]"))
				{
					details(person.GetBaseMember().getMother(),gp);
					
				}
			    }
			    if(person.GetBaseMember().getChild()!=null){
					for(int i =0;i<person.GetBaseMember().getChild().size();i++)
					{
						if(detailView.getSelectionModel().selectedItemProperty().getValue().toString().equals("TreeItem [ value: "+person.GetBaseMember().getChild().get(i).getFirstName()+" "+person.GetBaseMember().getChild().get(i).getSurName() +" ]"))
						details(person.GetBaseMember().getChild().get(i),gp);
					}
				}
			}catch(Exception e){


						//System.out.println("Not exist yet");
					}
					
					
				
						
				
			
			};
			

	//Global Vbox to use in showing detail pannel
	private static VBox vbox_details=new VBox(20);
	
	/**
	 * This method is to display the details of the member in GUI using Label and Text
	 * @param fam
	 * @param gp
	 */
	public void details(FamilyMembers fam,GridPane gp)
	{
		//System.out.println("I am here");
		//clearing the pre existed lines in vbox
		vbox_details.getChildren().clear();
		
		Text welcome = new Text("Personal Detail");
		vbox_details.setSpacing(2);
		welcome.setFont(Font.font("Arial",FontWeight.BOLD,14));
		
		Label namelabel = new Label("Name: "+ fam.getFirstName()+" "+fam.getSurName());
		Label namelabel2 = new Label("Family Name: "+fam.getSurName_marriage());
		Label lifeDescription_label = new Label("Life Description:" + fam.getLifeDescription());
		Label gender_det = new Label("Gender:" + fam.getGender());
		Label Address_det = new Label("Address: "+fam.getAddress().getSuburb()+", "+fam.getAddress().getStreetName()+", "+fam.getAddress().getStreetNumber()+", "+fam.getAddress().getPostcode());
		Label father,mother,spouse;
		Label children= new Label("Children: ");
		Text relative = new Text("Relative");
		vbox_details.setSpacing(2);
		relative.setFont(Font.font("Arial",FontWeight.BOLD,14));
		if(fam.getFather()==null) {
			father =new Label("Father: ");
		}else {
			father = new Label("Father: "+ fam.getFather().getFirstName().toString()+" "+fam.getFather().getSurName().toString());
		}
		if(fam.getMother()==null) {
			mother =new Label("Mother: ");
		}else {
			mother=new Label("Mother: "+ fam.getMother().getFirstName().toString()+" "+fam.getMother().getSurName().toString());
		}
		if(fam.getSpouse()==null) {
			spouse=new Label("Spoouse: ");
		}else
		{
			spouse = new Label("Spouse:" + fam.getSpouse().getFirstName().toString()+" "+fam.getSpouse().getSurName().toString());
		}
		if(!fam.getChild().isEmpty())
		for(int i =0;i<fam.getChild().size();i++) {

		children = new Label("Children: "+fam.getChild().get(i).getFirstName()+" "+fam.getChild().get(i).getSurName());
		
		}
		//Label mother = new Label("Mother: "+fam.getMother().getFirstName().toString()+" "+fam.getMother().getSurName().toString());
		//Label spouse = new Label("Spouse:" + fam.getSpouse().getFirstName().toString()+" "+fam.getSpouse().getSurName().toString());
		
		System.out.println("I am here now");
		vbox_details.getChildren().addAll(welcome,namelabel,namelabel2,lifeDescription_label,gender_det,Address_det,relative,father,mother,spouse,children);
		GridPane.setConstraints(vbox_details, 1, 10);
		
		gp.getChildren().addAll(vbox_details);
		
	
		
		
	}
	
	/**
	 * This method is used to create a new tree
	 */
	//Create a new Tree 
	private void create_newTree()
	{
		Alert.yesNo("Warning", "Are u sure to creat a new tree?");
		currentTree = new FamilyTree();
		menuButtons();
	}
	
	/**
	 * This method is used to validate the input fields to create members are not empty
	 * @param firstname
	 * @param lastname
	 * @param lastname2
	 * @param lifedescription
	 * @param streetno
	 * @param streetname
	 * @param suburb
	 * @param postcode
	 * @return
	 */
	
	//validation check method for empty fields
	private boolean validationCheck_Base(String firstname,String lastname,String lastname2,String lifedescription,String streetno,String streetname,String suburb,String postcode)
	{
		if(firstname.isEmpty()||lastname.isEmpty()||lifedescription.isEmpty()||streetno.isEmpty()||streetname.isEmpty()||suburb.isEmpty()||postcode.isEmpty())
		{
			
			Alert.display("Invalid","Fill up all the fields!!!");
			//createRootPerson();
			return false;
			}else {return true;}
		//System.out.println("THe first name is : " + firstname);
	}
	
	/**
	 * This method is to validate the input names of the members do not include special characters and numbers
	 * @param firstname
	 * @param lastname
	 * @param lastname2
	 * @return
	 */
	
	//validation check for names
	private boolean validationCheck_Base2(String firstname,String lastname,String lastname2)
	{
		String fullname = firstname+lastname;
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(fullname);
		
		if(fullname.matches(".*\\d.*")||matcher.find())
		{
			Alert.display("Error", "Invalid Input: Inputed names including numbers (or) Special Characters");
			//createRootPerson();
			return false;
		}else {
		return true;}
	}
	
	
	//validation method for over all check
	/**
	 * This method is a collection of validation check methods to perform in this
	 * @param firstname
	 * @param lastname
	 * @param lastname2
	 * @param lifedescription
	 * @param streetno
	 * @param streetname
	 * @param suburb
	 * @param postcode
	 * @return
	 */
	public boolean overallCheck(String firstname,String lastname,String lastname2,String lifedescription,String streetno,String streetname,String suburb,String postcode)
	{
		boolean checked = false;
		String firstname_valid = firstname;
		String lastname_valid = lastname;
		String lastname2_valid = lastname2;
		String lifedescription_valid = lifedescription;
		String streetno_valid = streetno;
		String streetname_valid = streetname;
		String suburb_valid = suburb;
		String postcode_valid = postcode;
		
		if(!validationCheck_Base(firstname_valid,lastname_valid,lastname2_valid,lifedescription_valid,streetno_valid,streetname_valid,suburb_valid,postcode_valid)) {
			//System.out.println("Here");
		}else if(!validationCheck_Base2(firstname_valid,lastname_valid,lastname2_valid)){
			//System.out.println("Number mistake");
		
		}else {
			checked = true;
		}
		return checked;
	
	
	}
	
	/**
	 * This method is used to save the tree object to a file using object serialization
	 * @param primaryStage
	 * @param tree
	 */
	public void save_Tree(Stage primaryStage,FamilyTree tree)
	{
		try {
		
		FileChooser saveFile_chooser = new FileChooser();
		saveFile_chooser.setTitle("Save File");
		saveFile_chooser.setInitialFileName("FamilyTree.ft");
		saveFile_chooser.getExtensionFilters().addAll(new ExtensionFilter("FT Files (*.ft)","*.ft"));
		saveFile_chooser.setInitialDirectory(new File("Family Trees"));
	
		file =saveFile_chooser.showSaveDialog(window);
	    String fileName =file.getName();
			
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(tree);
		out.close();
		fileOut.close();
		Alert.display("Succeeded", "The tree is saved");
		}catch(IOException i)
		{
			i.printStackTrace();
		}
		}
	
	/**
	 * This method is used to load the tree object from a file using object deserialization
	 * @param primaryStage
	 * @param tree
	 */
	public void load_Tree(Stage primaryStage,FamilyTree tree)
	{
		try {
		
		FileChooser File_chooser = new FileChooser();
		File_chooser.setTitle("Load File");
		File_chooser.getExtensionFilters().addAll(new ExtensionFilter("FT Files (*.ft)","*.ft"));
		File_chooser.setInitialDirectory(new File("Family Trees"));
	
		 file =File_chooser.showOpenDialog(window);
	    String fileName =file.getName();
			//System.out.println(fileName);
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		tree =(FamilyTree) in.readObject();
		in.close();
		fileIn.close();
		
		currentTree.setBaseMember(tree.GetBaseMember());
		menuButtons();
		} catch(IOException i) {
			i.printStackTrace();
			return;}
		catch(ClassNotFoundException x)
		{
			Alert.display("Error", "The tree is not found!");
			x.printStackTrace();
			return;
		}
		Alert.display("Succeeded", "The tree is loaded");
		menuButtons();
		//System.out.println(currentTree.GetBaseMember().getFirstName());
		}

	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}