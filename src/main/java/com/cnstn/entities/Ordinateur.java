package com.cnstn.entities;

import javax.persistence.*;

@DiscriminatorValue("odri")
public class Ordinateur extends Materiels{
	private int ram;
	private String  processeur;
	//private type2 enum2;

	
	
	
}
