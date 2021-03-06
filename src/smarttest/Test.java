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
public class Test implements java.io.Serializable{
    Boolean isDeployed;
    int totalPoints;
    int testID;
    ArrayList<Question> questions;
    ArrayList<LearningOutcome> learningOutcomes;
    ArrayList<Student> students;
    int pointsObtained;
    
    
    public Test(){
        testID = generateID();
        isDeployed = false;
        totalPoints = 0;
        questions = new ArrayList<>();
        learningOutcomes = new ArrayList<>();
        students = new ArrayList<>();
        pointsObtained = 0;
    }
    
    //for testing
    public Test(Question q){
        testID = generateID();
        isDeployed = false;
        totalPoints = 0;
        questions = new ArrayList<>();
        questions.add(q);
        learningOutcomes = new ArrayList<>();
    }
    
    public void addQuestion(Question q){
        questions.add(q);
        totalPoints += q.points;

    }
    
    public void removeQuestion(Question q){
        for(Question question : questions){
            if(question.isEqual(q)){
                questions.remove(question);
                totalPoints -= question.points;
            }
        }
    }
    
    public void deployTest(){
        testID = generateID();
        isDeployed = true;
    }
    
    
    private int generateID(){
        int num;
        num = (int)(Math.random()*999999999);
        return num;
    }
    
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    
}
