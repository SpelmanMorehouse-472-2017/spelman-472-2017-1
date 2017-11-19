package com.google.devrel.training.conference.domain;

import com.google.devrel.training.conference.form.ProfileForm.TeeShirtSize;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


// TODO indicate that this class is an Entity
public class Profile {
	String displayName;
	String mainEmail;
	TeeShirtSize teeShirtSize;
	Subject subject;
	Activity activity;
	CollegeType collegeType;
	LearningType learningType;
	WorkType workType;

	// TODO indicate that the userId is to be used in the Entity's key
	String userId;
    
    /**
     * Public constructor for Profile.
     * @param userId The user id, obtained from the email
     * @param displayName Any string user wants us to display him/her on this system.
     * @param mainEmail User's main e-mail address.
     * @param teeShirtSize The User's tee shirt size
     * 
     */
    public Profile (String userId, String displayName, String mainEmail, TeeShirtSize teeShirtSize, Subject subject, Activity activity,
	CollegeType collegeType, Learningtype learningtype, WorkType workType) {
    	this.userId = userId;
    	this.displayName = displayName;
    	this.mainEmail = mainEmail;
    	this.teeShirtSize = teeShirtSize;
    	this.subject = subject;
    	this.activity = activity;
    	this.collegeType = collegeType;
    	this.learningType = learningType;
    	this.workType = workType;
    }
    
	public String getDisplayName() {
		return displayName;
	}

	public String getMainEmail() {
		return mainEmail;
	}

	public TeeShirtSize getTeeShirtSize() {
		return teeShirtSize;
	}

	public String getUserId() {
		return userId;
	}

	public Subject getSubject() {
		return subject;
	}

	public Activity getActivity() {
		return activity;
	}

	public CollegeType getCollegeType() {
		return collegeType;
	}

	public LearningType getLearningType() {
		return learningType;
	}

	public WorkType getWorkType() {
		return workType;
	}

	/**
     * Just making the default constructor private.
     */
    private Profile() {}

}
