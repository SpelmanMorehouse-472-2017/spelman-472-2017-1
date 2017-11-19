package com.google.devrel.training.conference.form;

/**
 * Pojo representing a profile form on the client side.
 */
public class ProfileForm {
    /**
     * Any string user wants us to display him/her on this system.
     */
    private String displayName;

    /**
     * T shirt size.
     */
    private TeeShirtSize teeShirtSize;
    private Subject subject;
    private Acivity acivity;
    private CollegeType collegeType;
    private LearningType learningType;
    private WorkType workType;

    private ProfileForm () {}

    /**
     * Constructor for ProfileForm, solely for unit test.
     * @param displayName A String for displaying the user on this system.
     * @param notificationEmail An e-mail address for getting notifications from this system.
     */
    public ProfileForm(String displayName, TeeShirtSize teeShirtSize) {
        this.displayName = displayName;
        this.teeShirtSize = teeShirtSize;
    }

    public String getDisplayName() {
        return displayName;
    }

    public TeeShirtSize getTeeShirtSize() {
        return teeShirtSize;
    }

    //These are the api calls to recieve input for major quiz
    public Subject subject() {return subject;}
    public Acivity acivity() {return acivity;}
    public CollegeType collegeType() {return  collegeType;}
    public LearningType learningType() {return learningType;}
    public WorkType workType() {return workType;}
    
    public static enum TeeShirtSize {
    	NOT_SPECIFIED,
        XS,
        S,
        M,
        L, 
        XL, 
        XXL,
        XXXL
    }

    //These are listing out the available options for each object
    public static enum Subject {
        Math,
        English,
        Science,
        Art
    }   public static enum Acivity {
        Robotics,
        State,
        Band,
        Poetry
    }    public static enum CollegeType {
        Fun,
        Learning,
        Earnings
    }
    public static enum LearningType {
        handson,
        brainbased
    }
    public static enum WorkType {
        Papers,
        Lab,
        Reasearch,
        Artwork
    }
}
