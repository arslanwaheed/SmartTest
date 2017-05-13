/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

/**
 *
 * @author Justin Sanchez
 */
public class Teacher extends User{
    TestsArray DeployedTests;
    TestsArray UndeployedTests;
    
    Teacher(String f, String l, String u, String p){
        super(f,l,u,p);
        this.AccountType = "Teacher";    
    }//constructor   
}//Teacher