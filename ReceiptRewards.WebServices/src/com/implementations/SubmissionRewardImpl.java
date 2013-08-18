package com.implementations;

import com.common.DBConnection;
import com.model.SubmissionReward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubmissionRewardImpl {

    private static final String FIND_BY_SUBMISSION_REWARD_ID = "SELECT * FROM submission_reward_legacy WHERE submission_reward_id = ?;";
    private static final String FIND_BY_SUBMISSION_ID = "SELECT * FROM submission_reward_legacy srl JOIN submission_legacy sl ON sl.submission_reward_id = srl.submission_reward_id WHERE sl.submission_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 submission_reward_id FROM submission_reward_legacy ORDER BY submission_reward_id DESC;";
    private static final String CREATE = "INSERT INTO submission_reward_legacy (submission_reward_id, reward_id, value, redeemed) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE submission_reward_legacy SET value = ?, redeemed = ? WHERE submission_reward_id = ?;";
    private static final String DELETE_BY_SUBMISSION_REWARD_ID = "DELETE FROM submission_reward_legacy WHERE submission_reward_id = ?;";
    private static final String DELETE_BY_SUBMISSION_ID = "DELETE FROM submission_reward_legacy srl JOIN submission_legacy sl ON sl.submission_reward_id = srl.submission_reward_id WHERE sl.submission_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static SubmissionReward findBySubmissionRewardId(int submissionRewardId) {
        return SubmissionRewardImpl.findBySubmissionRewardId(submissionRewardId, null);
    }
    public static SubmissionReward findBySubmissionRewardId(int submissionRewardId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        SubmissionReward submissionReward = new SubmissionReward();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionRewardImpl.FIND_BY_SUBMISSION_REWARD_ID);
            ps.setInt(1, submissionRewardId);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionReward = new SubmissionReward(rs.getInt("submission_reward_id"), RewardImpl.findByRewardId(rs.getInt("reward_id"), con), rs.getString("value"), rs.getBoolean("redeemed"));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: SubmissionReward.FindBySubmissionRewardId = " + ex.getMessage());
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
        return submissionReward;
    }
    public static List<SubmissionReward> findBySubmissionId(int submissionId) {
        return SubmissionRewardImpl.findBySubmissionId(submissionId, null);
    }
    public static List<SubmissionReward> findBySubmissionId(int submissionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<SubmissionReward> submissionRewards = new ArrayList<SubmissionReward>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionRewardImpl.FIND_BY_SUBMISSION_ID);
            ps.setInt(1, submissionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionRewards.add(new SubmissionReward(rs.getInt("submission_reward_id"), RewardImpl.findByRewardId(rs.getInt("submission_id"), con), rs.getString("value"), rs.getBoolean("redeemed")));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: SubmissionReward.FindBySubmissionId = " + ex.getMessage());
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
        return submissionRewards;
    }
    public static int findNextId() {
        return SubmissionRewardImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int submissionRewardId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Entities
            ps = con.prepareStatement(SubmissionRewardImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionRewardId = rs.getInt("submission_reward_id") + 1;
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: SubmissionReward.FindNextId = " + ex.getMessage());
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
        return submissionRewardId;
    }
    
    
    
    /***** Create Methods *****/
    public static SubmissionReward create(SubmissionReward submissionReward) {
        return SubmissionRewardImpl.create(submissionReward, null);
    }
    public static SubmissionReward create(SubmissionReward submissionReward, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(submissionReward == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            submissionReward.setSubmissionRewardId(SubmissionRewardImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(SubmissionRewardImpl.CREATE);
            ps.setInt(1, submissionReward.getSubmissionRewardId());
            ps.setInt(2, submissionReward.getReward().getRewardId());
            ps.setString(3, submissionReward.getValue());
            ps.setBoolean(4, submissionReward.isRedeemed());
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: SubmissionRewardImpl.Create = " + ex.getMessage());
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
        return submissionReward;
    }
    
    
    
    /***** Update Methods *****/
    public static SubmissionReward update(SubmissionReward submissionReward) {
        return SubmissionRewardImpl.update(submissionReward, null);
    }
    public static SubmissionReward update(SubmissionReward submissionReward, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(submissionReward == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(SubmissionRewardImpl.UPDATE);
            ps.setString(1, submissionReward.getValue());
            ps.setBoolean(2, submissionReward.isRedeemed());
            ps.setInt(3, submissionReward.getSubmissionRewardId());
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
        return submissionReward;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteBySubmissionRewardId(int submissionRewardId) {
        SubmissionRewardImpl.deleteBySubmissionRewardId(submissionRewardId, null);
    }
    public static void deleteBySubmissionRewardId(int submissionRewardId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SubmissionRewardImpl.DELETE_BY_SUBMISSION_REWARD_ID);
            ps.setInt(1, submissionRewardId);
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
    public static void deleteBySubmissionId(int submissionId) {
        SubmissionRewardImpl.deleteBySubmissionId(submissionId, null);
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
            ps = con.prepareStatement(SubmissionRewardImpl.DELETE_BY_SUBMISSION_ID);
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
}