import java.util.*;
import java.io.*;
import javax.swing.*;
public class Person implements Serializable{
private	String borrower;
private String item;
private int noItems;
private String dateBorrowed;
private String  dateReturned;
private	String studID;
private	String name;
private int quantity;
private String status;

//constructor


public Person(String item, int quantity){
	this.item=item;
	this.quantity=quantity;
}

public int getQuantity(){
	return quantity;
}

public String getStatus(){
	return status;
}
public Person(String studID, String borrower, String item,int noItems, String dateBorrowed,String dateReturned,String status){

	this.studID=studID;
	this.borrower=borrower;
	this.item=item;
	this.noItems=noItems;
	this.dateBorrowed=dateBorrowed;
	this.dateReturned=dateReturned;
	this.status=status;

}

public String studID(){
	return studID;
}

public String getBorrower(){
	return borrower;
}
public String getItem(){
	return item;
}
public int noItems(){
	return noItems;
}
public String dateBorrowed(){
	return dateBorrowed;
}
public String dateReturned(){
	return dateReturned;
}

} 
