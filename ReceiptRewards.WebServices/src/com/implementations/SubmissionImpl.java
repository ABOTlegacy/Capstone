package com.implementations;

import com.common.DBConnection;
import com.model.Submission;
import com.model.SubmissionReward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubmissionImpl {

    private static final String FIND_BY_SUBMISSION_ID = "SELECT * FROM submission_legacy WHERE submission_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM submission_legacy WHERE revision_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 submission_id FROM submission_legacy ORDER BY submission_id DESC;";
    private static final String CREATE = "INSERT INTO submission_legacy (submission_id, revision_id, date_started, date_completed, submission_reward_id) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE submission_legacy SET date_completed = ?, submission_reward_id = ? WHERE submission_id = ?;";
    private static final String DELETE_BY_SUBMISSION_ID = "DELETE FROM submission_legacy WHERE submission_id = ?;";
    private static final String DELETE_BY_REVISION_ID = "DELETE FROM submission_legacy WHERE revision_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Submission findBySubmissionId(int submissionId) {
        return SubmissionImpl.findBySubmissionId(submissionId, null);
    }
    public static Submission findBySubmissionId(int submissionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Submission submission = new Submission();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionImpl.FIND_BY_SUBMISSION_ID);
            ps.setInt(1, submissionId);
            rs = ps.executeQuery();
            if(rs.next()) {
                submission = new Submission(rs.getInt("submission_id"), rs.getInt("revision_id"), new Date(), new Date() /*rs.getDate("date_started"), new Date(rs.getDate("date_completed").getTime())*/, SubmissionAnswerImpl.findBySubmissionId(rs.getInt("submission_id"), con), SubmissionRewardImpl.findBySubmissionRewardId(rs.getInt("submission_reward_id"), con));
            }
            rs.close();
            ps.close();
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
        return submission;
    }
    public static List<Submission> findByRevisionId(int formId) {
        return SubmissionImpl.findByRevisionId(formId, null);
    }
    public static List<Submission> findByRevisionId(int formId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Submission> submissions = new ArrayList<Submission>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissions.add(new Submission(rs.getInt("submission_id"), rs.getInt("revision_id"), new Date(), new Date(), /*rs.getDate("date_started"), new Date(rs.getDate("date_completed").getTime()),*/ SubmissionAnswerImpl.findBySubmissionId(rs.getByte("submission_id"), con), SubmissionRewardImpl.findBySubmissionRewardId(rs.getInt("submission_reward_id"), con)));
            }
            rs.close();
            ps.close();
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
        return submissions;
    }
    public static int findNextId() {
        return SubmissionImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int submissionId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get the Next Id
            ps = con.prepareStatement(SubmissionImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionId = rs.getInt("submission_id") + 1;
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: Submission.FindNextId = " + ex.getMessage());
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
        return submissionId;
    }
    
    
    
    /***** Create Methods *****/
    public static Submission create(Submission submission) {
        return SubmissionImpl.create(submission, null);
    }
    public static Submission create(Submission submission, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(submission == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            submission.setSubmissionId(SubmissionImpl.findNextId(con));
            
            // Set Reward ID To 0
            if(submission.getSubmissionReward() == null) {
                submission.setSubmissionReward(new SubmissionReward(0, RewardImpl.findByRevisionId(submission.getRevisionId()), "", false));
            }
            
            // @TODO: I may have to save form answers here maybe?
            for(int i = 0; i < submission.getSubmissionAnswers().size(); i++) {
                submission.getSubmissionAnswers().get(i).setSubmissionId(submission.getSubmissionId());
                SubmissionAnswerImpl.create(submission.getSubmissionAnswers().get(i), con);
            }
            
            // Create New Entry
            ps = con.prepareStatement(SubmissionImpl.CREATE);
            ps.setInt(1, submission.getSubmissionId());
            ps.setInt(2, submission.getRevisionId());
            //ps.setDate(3, new java.sql.Date(submission.getDateStarted().getTime()));
            //ps.setDate(4, new java.sql.Date(submission.getDateCompleted().getTime()));
            ps.setDate(3, null);
            ps.setDate(4, null);
            ps.setInt(5, submission.getSubmissionReward().getSubmissionRewardId());
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: Submission.Create = " + ex.getMessage());
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
        return submission;
    }
    
    
    
    /***** Update Methods *****/
    public static Submission update(Submission submission) {
        return SubmissionImpl.update(submission, null);
    }
    public static Submission update(Submission submission, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(submission == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(SubmissionImpl.UPDATE);
            ps.setDate(1, new java.sql.Date(submission.getDateCompleted().getTime()));
            ps.setInt(2, submission.getSubmissionReward().getSubmissionRewardId());
            ps.setInt(3, submission.getSubmissionId());
            ps.executeUpdate();
            ps.close();
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
        return submission;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteBySubmissionId(int submissionId) {
        SubmissionImpl.deleteBySubmissionId(submissionId, null);
    }
    public static void deleteBySubmissionId(int submissionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SubmissionImpl.DELETE_BY_SUBMISSION_ID);
            ps.setInt(1, submissionId);
            ps.executeUpdate();
            ps.close();
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
    }
    public static void deleteByRevisionId(int revisionId) {
        SubmissionImpl.deleteByRevisionId(revisionId, null);
    }
    public static void deleteByRevisionId(int revisionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SubmissionImpl.DELETE_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            ps.executeUpdate();
            ps.close();
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
    }
}