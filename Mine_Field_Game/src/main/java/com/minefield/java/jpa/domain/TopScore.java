package com.minefield.java.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class TopScore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
  private String name;
  private int difficulty;
  private int duration;

  public Integer getId() {
	    return id;
	  }

	  public void setId(Integer id) {
	    this.id = id;
	  }
 
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDiff() {
    return difficulty;
  }

  public void setDiff(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getDuration() {
	    return duration;
	  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}