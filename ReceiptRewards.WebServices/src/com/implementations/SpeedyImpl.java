package com.implementations;

import com.aliasmodels.R;
import com.common.AliasHelper;
import com.common.DBConnection;
import com.model.Question;
import com.model.Revision;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SpeedyImpl {

    /***** Find Methods *****/
    public static R findByRevisionId(int revisionId, String command) {
        // Instantiate Variables
        Connection con = null;
        boolean conBool = false;
        Revision revision = null;
        R r = null;
        List<Question> allQuestions = null;
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get Initial Revision
            revision = RevisionImpl.findByRevisionId(revisionId, 1, con);
            
            // Get All the Questions for the Revision
            allQuestions = QuestionImpl.findByRevisionId(revisionId, 3, con);
            
            // Iterate to compile the entire run of flows for the revision
            
            // Iterate Through All The Questions Only Once
            for(int i = 0; i < allQuestions.size(); i++) {
                
                // Iterate Through All the Automation Flows
                if(command.equals("AUTOMATION")) {
                    for(int j = 0; j < allQuestions.get(i).getAutomation().getAutomationFlows().size(); j++) {
                        // Iterate through all the Questions
                        for(int h = 0; h < allQuestions.size(); h++) {
                            // If the targeted question id in the flow equals the question id then set that question
                            if(allQuestions.get(i).getAutomation().getAutomationFlows().get(j).getQuestion().getQuestionId() == allQuestions.get(h).getQuestionId()) {
                                allQuestions.get(i).getAutomation().getAutomationFlows().get(j).setQuestion(allQuestions.get(h));
                            }
                        }
                    }
                }
                
                // Iterate Through All the Form Flows
                if(command.equals("FORM")) {
                    for(int j = 0; j < allQuestions.get(i).getForm().getFormFlows().size(); j++) {
                        // Iterate through all the Questions
                        for(int h = 0; h < allQuestions.size(); h++) {
                            // If the targeted question id in the flow equals the question id then set that question
                            if(allQuestions.get(i).getForm().getFormFlows().get(j).getQuestion().getQuestionId() == allQuestions.get(h).getQuestionId()) {
                                allQuestions.get(i).getForm().getFormFlows().get(j).setQuestion(allQuestions.get(h));
                            }
                        }
                    }
                }
                
                // If Question Id equals the revision question id set the revision to that question
                if(revision.getQuestion().getQuestionId() == allQuestions.get(i).getQuestionId()) {
                    revision.setQuestion(allQuestions.get(i));
                }
            }
            
            // Find the alias
            r = AliasHelper.getRevisionAlias(revision);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return r;
    }
    public static List<R> findByActiveSurveyId(int surveyId, String command) {
        // Instantiate Variables
        Connection con = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();
        List<R> rArray = new ArrayList<R>();

        // Verify
        if(surveyId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get the Survey Id
            revisions = RevisionImpl.findByActiveSurveyId(surveyId, 1, con);
            
            // Get The Revision
            if(revisions.get(0) != null) {
                rArray.add(SpeedyImpl.findByRevisionId(revisions.get(0).getRevisionId(), command));
            }
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return rArray;
    }
    public static List<R> findBySurveyId(int surveyId, String command) {
        // Instantiate Variables
        Connection con = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();
        List<R> rArray = new ArrayList<R>();

        // Verify
        if(surveyId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get the Survey Id
            revisions = RevisionImpl.findBySurveyId(surveyId, 1, con);
            
            // Get The Revision
            for(int i = 0; i < revisions.size(); i++) {
                if(revisions.get(i) != null) {
                    rArray.add(SpeedyImpl.findByRevisionId(revisions.get(i).getRevisionId(), command));
                }
            }
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return rArray;
    }
}