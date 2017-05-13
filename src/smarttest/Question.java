/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.ArrayList;

/**
 *
 * @author
 */
public class Question implements java.io.Serializable{
    int correctOption;
    int points;
    String question;
    ArrayList<String> options;
    ArrayList<LearningOutcome> learningOutcomes;
    

    public Question(){
        learningOutcomes = new ArrayList<>();
        correctOption = 0;
        question = "";
        points = 0;
        options = new ArrayList<>();
    }
    
    //for testing purposes
    public Question(String question, ArrayList<String> options){
        learningOutcomes = new ArrayList<>();
        correctOption = 0;
        this.question = question;
        points = 0;
        this.options = options;
    }
    
    @Override
    public String toString(){
        return question + " " + options + " " + learningOutcomes + " " + correctOption + " " + points;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public int getPoints(){
        return points;
    }
    public ArrayList<String> getOptions(){
        return options;
    }
    
    public ArrayList<LearningOutcome> getOutcomes(){
        return learningOutcomes;
    }
}
