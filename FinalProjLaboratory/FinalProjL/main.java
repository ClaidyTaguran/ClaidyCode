import java.util.*;
import java.io.*;
import javax.swing.*;
public class main{
	public static void main (String[] args) {
		
		
		Scanner in=new Scanner(System.in);
	
	String borrower;
String item="";
int noItems=0;
int IDno;
String studID;
String dateBorrowed;
String  dateReturned="Not Yet Returned";
String status="Borrowed";
int quantity=0;

		File f=new File(("Borrowers001.txt"));//File Text
		File f1=new File(("inventory001.txt"));//File Text
				
		ArrayList<Person> mylist=new ArrayList<>(); //Arraylist is used because it doesnt have limits. you can constantly add values into the array without minding the Array bounds
			ArrayList<Person> mylist1=new ArrayList<>(); //Arraylist for appending to files
		ObjectOutputStream oos = null; //used for deleting objects into the file
			ObjectOutputStream oos1 = null; //used for deleting objects into the file
		ObjectInputStream ois = null; // used for adding objects into the file
			ObjectInputStream ois1 = null; // used for adding objects into the file
			FileOutputStream fos=null; //partner for oos
			FileOutputStream fos1=null; //partner for oos
				FileInputStream fis=null; //partner for ois
				FileInputStream fis1=null; //partner for ois
					ListIterator li = null;  //used for determining all the objects so that traversal will be easier
		
		//Opens the file and read is so that the data that are stored previously will be loaded immediately	
		try{
				if(f.isFile()){
			ois = new ObjectInputStream(new FileInputStream(f));
			
			mylist = (ArrayList<Person>)ois.readObject();
		
		
		}
		}
		catch(IOException e){
			System.out.println(e);
			
		}
		catch(ClassNotFoundException ex){
			System.out.println(ex);
			
		}
		
		
			
		try{
				if(f1.isFile()){
			ois1 = new ObjectInputStream(new FileInputStream(f1));
			
			mylist1 = (ArrayList<Person>)ois1.readObject();
			ois1.close();
			
		}
		}
		catch(IOException e){
			System.out.println(e);
			
		}
		catch(ClassNotFoundException ex){
			System.out.println(ex);
			
		}
		//	this is the  main program every queries will be entertain in this part
		String choice="";
		boolean isNumber=true;	
		
	do{
		//displays the main functions or the features in the program....
		try{
				//Display Menu
				 choice=JOptionPane.showInputDialog(null,"**Welcome to Laboratory Tools and Equipment Management System**\n"+
				 										  "1. Add Items to the Inventory(AUTHORIZED PERSONNEL)\n" +
				 									      "2. Display Inventory\n" +
				 										  "3. Add Borrow Tool and Equipment\n"+
				 									      "4. Display Borrowers and Tools Borrowed\n"+
				 									      "5. Search ID of Borrower\n"+
				 									      "6. Return Borrowed Tools\n"+
				 									      "7. Delete or Clear Borrower\n"+
				 									      "8. Sort Borrowers Name\n"+
				 									      "0. Exit Program"	);


		isNumber=true;
		}catch(NumberFormatException e){
		JOptionPane.showMessageDialog(null,"Enter Input Correctly");

		isNumber=false;}
		
		
		switch (choice){
			//Add Items(Authorized Personnel)
				case "1":
	try{
		
		if(!f1.exists()){
			f1.createNewFile();
		}
		
		else{ 
		
	String mats=	JOptionPane.showInputDialog(null,"Enter Item Name");
	
			quantity=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter No Items "));
			
	
					mylist1.add(new Person(mats,quantity));
				
	 fos1= new FileOutputStream(f1);
	 oos1=new ObjectOutputStream(fos1);
		oos1.writeObject(mylist1);
		oos1.close();
		fos1.close();
	JOptionPane.showMessageDialog(null,"Added Succesfully!");
		
	}} catch(IOException e){
		System.out.println(e);
	}
	break;
	////////////////////////////*/
	
		//Display Items
		case "2":
				try{
	
				 fis1=new FileInputStream(f1);
				 ois1=new ObjectInputStream(fis1);
			ArrayList<Person> p =(ArrayList<Person>)ois1.readObject();
			ois1.close();
			for(Person person:p){
			
			JOptionPane.showMessageDialog(null, "Item Name: " + person.getItem() + "\n" +
												"Quantity: "+ person.getQuantity() +"\n" );
			}
			
			
			
			
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}break;
			
	//Add Borrow Tool and Equipment
	case "3":
		try{
		
		if(!f.exists()){
			f.createNewFile();
		}
		
		else{ 
				studID=	JOptionPane.showInputDialog(null,"Enter Borrower's ID");
	borrower=	JOptionPane.showInputDialog(null,"Enter Borrower's Name");
	boolean available=false;
	do{
	
		try{
	
				 fis1=new FileInputStream(f1);
				 ois1=new ObjectInputStream(fis1);
			ArrayList<Person> p =(ArrayList<Person>)ois1.readObject();
			ois1.close();
				item=JOptionPane.showInputDialog(null,"What item to borrow");
					noItems=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter No Items "));	
			for(Person person:p){
		if(person.getItem().equals(item)&&person.getQuantity()>noItems){
			quantity=person.getQuantity()-noItems;
			available=true;
		}
			}
			
			if(!available){
			JOptionPane.showMessageDialog(null,"There is no materials to be borrowed or the items are insufficient");
			
		}
			
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}}while(!available);

	
				dateBorrowed=JOptionPane.showInputDialog(null,"Borrowed Date");
	
					mylist.add(new Person( studID, borrower, item, noItems, dateBorrowed, dateReturned,status));
	
				
	 fos= new FileOutputStream(f);
	 oos=new ObjectOutputStream(fos);
		oos.writeObject(mylist);
		oos.close();
		fos.close();
		
		
	
		
	
		JOptionPane.showMessageDialog(null,"Borrowed Successfully!");
		
		
	}} catch(IOException e){
		System.out.println(e);
	}
	try{
			 fis1=new FileInputStream(f1);
				 ois1=new ObjectInputStream(fis1);
		
	boolean give=false;
		
			ArrayList<Person> p1 =(ArrayList<Person>)ois1.readObject();
			ois1.close();
	
					ListIterator li1=p1.listIterator();
			while(li1.hasNext()){
					Person person1=(Person)li1.next();
					
				String	loadItems=person1.getItem();
				int	loadQuantity=person1.getQuantity();
							li1.set(new Person(loadItems,loadQuantity));
					if(person1.getItem().equals(item)){
					
						quantity=person1.getQuantity()-noItems;
				li1.set(new Person(item,quantity));
				give =true;	}
				}
				
				if(give){
					oos = new ObjectOutputStream(new FileOutputStream(f1));
							oos.writeObject(p1);
							oos.close();
				}
	}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}

	break;
	
		//Display Borrowers and Tools Borrowed
		case "4":
		try{
	
				 fis=new FileInputStream(f);
				 ois=new ObjectInputStream(fis);
			ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
			
			for(Person person:p){
			
			JOptionPane.showMessageDialog(null, "Stud ID: " + person.studID() + "\n" +
												"Borrowers name: "+ person.getBorrower() +"\n" +
												"Borrowed item: " + person.getItem()+ "\n"+
												"No of Items borrowed: " + person.noItems()+"\n" +
												"Date Borrowed : " + person.dateBorrowed() + "\n" +
												"Date Returned : " + person.dateReturned()+"\n"+
													"Status : " + person.getStatus());
			}
			
			
			
			
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}
	
	break;
		
	//Search ID of Borrower
	case "5":

		try{
			if(f.isFile()){
			
				 fis=new FileInputStream(f);
				 ois=new ObjectInputStream(fis);
			ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
			ois.close();
					boolean found = false;
				String iDno2 =	JOptionPane.showInputDialog(null,"Enter Student ID No. to Search: ");
				
			for(Person person:p){
				if(person.studID().equals(iDno2)){
				
			JOptionPane.showMessageDialog(null, "Stud ID: " + person.studID() + "\n" +
												"Borrowers name: "+ person.getBorrower() +"\n" +
												"Borrowed item: " + person.getItem()+ "\n"+
												"No of Items borrowed: " + person.noItems()+"\n" +
												"Date Borrowed : " + person.dateBorrowed() + "\n" +
												"Date Returned : " + person.dateReturned());
			
			found=true;
			}
			
			}	if(!found){
				
						JOptionPane.showMessageDialog(null,"ID Not Found...");
						
			}}else{
					JOptionPane.showMessageDialog(null,"File not Exists....!");
			}
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}
	break;
	
	
		//Return Borrowed Tools
		case "6":
						try{
			if(f.isFile()){
			
				 fis=new FileInputStream(f);
				 ois=new ObjectInputStream(fis);
				 	 fis1=new FileInputStream(f1);
				 ois1=new ObjectInputStream(fis1);
			ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
	
		
			ArrayList<Person> p1 =(ArrayList<Person>)ois1.readObject();
			ois1.close();
			ois.close();
					boolean found = true;;
					boolean returned=true;
				studID=	JOptionPane.showInputDialog(null,"Enter Returnee's ID no.  ");
					 li=p.listIterator();
					ListIterator li1=p1.listIterator();
			while(li.hasNext()){
				Person person=(Person)li.next();
				
				if(person.studID().equals(studID)&&person.getStatus().equals("Borrowed")){
					returned=false;
						dateReturned=JOptionPane.showInputDialog(null,"Date Returned");
						borrower=person.getBorrower();
						dateBorrowed=person.dateBorrowed();
						studID=person.studID();
						item=person.getItem();
						noItems=person.noItems();
							item=person.getItem();
				String setStatus="Returned";
							li.set(new Person( studID,borrower, item, noItems, dateBorrowed, dateReturned,setStatus));
				//	li.set(new Person(id,name));
					
				while(li1.hasNext()){
					Person person1=(Person)li1.next();
					System.out.println(noItems);
					int tempNoItems=noItems;
					int tempQuantity=person1.getQuantity();
					String copyMats=item;
					if(person1.getItem().equals(copyMats)){
					
						quantity=person1.getQuantity()+noItems;
				li1.set(new Person(item,quantity));	found=true;}
				}
		}else{
			found=false;
			returned=false;
		}
		
			}	if(!found){
				
					JOptionPane.showMessageDialog(null,"Record Not Found...");
					
			}
				else{
						oos = new ObjectOutputStream(new FileOutputStream(f));
							oos.writeObject(p);
							oos.close();
								oos = new ObjectOutputStream(new FileOutputStream(f1));
							oos.writeObject(p1);
							oos.close();
							JOptionPane.showMessageDialog(null,"Item returned Successfully....!");
			}
			
			if(returned){
				JOptionPane.showMessageDialog(null,"The item is already returned");
			}
		}else{
					System.out.println("File not Exists....!");
			}
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}
	break;
	
	//Delete or Clear Borrower
		case "7":
		
					try{
			if(f.isFile()){
			
				 fis=new FileInputStream(f);
				 ois=new ObjectInputStream(fis);
			ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
			Person person;
			ois.close();
					boolean found = false;
					
				String	 IDno1 = 	JOptionPane.showInputDialog(null,"Enter Student ID No. to Delete: ");
					 li=p.listIterator();
			while(li.hasNext()){
				person=(Person)li.next();
				if(person.studID().equals(IDno1)){
					li.remove();
					found=true;}
		
			}	if(!found){
				
						JOptionPane.showMessageDialog(null,"Record Not Found...");
			}
				else{
						oos = new ObjectOutputStream(new FileOutputStream(f));
							oos.writeObject(p);
							oos.close();
							JOptionPane.showMessageDialog(null,"Record Deleted Successfully....!");
			}
		}else{
					JOptionPane.showMessageDialog(null,"File not Exists....!");
			}
		}catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}
	break;

//Display Sort Names
case "8":

		
try{
			if(f.isFile()){
			
				 fis=new FileInputStream(f);
				 ois=new ObjectInputStream(fis);
			ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
			ois.close();
			
			
				
						
			Collections.sort(p, new Comparator<Person>(){
							public int compare(Person p1, Person p2){
								return p1.getBorrower().compareTo(p2.getBorrower());
							}
							
						});
							for(Person person:p){
									JOptionPane.showMessageDialog(null, "Stud ID: " + person.studID() + "\n" +
												"Borrowers name: "+ person.getBorrower() +"\n" +
												"Borrowed item: " + person.getItem()+ "\n"+
												"No of Items borrowed: " + person.noItems()+"\n" +
												"Date Borrowed : " + person.dateBorrowed() + "\n" +
												"Date Returned : " + person.dateReturned()+"\n"+
												"Status : " +person.getStatus()+"\n");
			}
			
			}}
			catch(IOException e){
		System.out.println(e);
	}catch(ClassNotFoundException ex){
	System.out.println(ex);
	}
		break;
	
	case "0":
		 JOptionPane.showMessageDialog(null,"Data Succesfully Loaded\nCMJ TECHno");
		break;
		default: JOptionPane.showMessageDialog(null,"Invalid Option");
		
}	
		 }while(!choice.equals("0"));}
}
	
	