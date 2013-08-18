package com.implementations;

import com.common.DBConnection;
import com.model.Reward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RewardImpl {

    private static final String FIND_BY_REWARD_ID = "SELECT * FROM reward_legacy WHERE reward_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM reward_legacy r JOIN revision_legacy rl ON rl.reward_id = r.reward_id WHERE rl.revision_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 reward_id FROM reward_legacy ORDER BY reward_id DESC;";
    private static final String CREATE = "INSERT INTO reward_legacy (reward_id, code_id, form_id) VALUES (?, ?, ?);";
    private static final String DELETE_BY_REWARD_ID = "DELETE FROM reward_legacy WHERE reward_id = ?;";
    private static final String DELETE_BY_REVISION_ID = "DELETE FROM reward_legacy r JOIN revision_legacy rl ON rl.reward_id = r.reward_id WHERE rl.revision_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Reward findByRewardId(int rewardId) {
        return RewardImpl.findByRewardId(rewardId, null);
    }
    public static Reward findByRewardId(int rewardId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Reward reward = new Reward();
        
        // Verify
        if(rewardId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(RewardImpl.FIND_BY_REWARD_ID);
            ps.setInt(1, rewardId);
            rs = ps.executeQuery();
            while(rs.next()) {
                reward = new Reward(rs.getInt("reward_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), FormImpl.findByFormId(rs.getInt("form_id")));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: RewardImpl.FindByRewardId = " + ex.getMessage());
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
        return reward;
    }
    public static Reward findByRevisionId(int revisionId) {
        return RewardImpl.findByRevisionId(revisionId, null);
    }
    public static Reward findByRevisionId(int revisionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Reward reward = new Reward();
        
        // Verify
        if(revisionId <= 0) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(RewardImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                reward = new Reward(rs.getInt("reward_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), FormImpl.findByFormId(rs.getInt("form_id")));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: RewardImpl.FindByRevisionId = " + ex.getMessage());
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
        return reward;
    }
    public static int findNextId() {
        return RewardImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int rewardId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(RewardImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                rewardId = rs.getInt("reward_id") + 1;
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: RewardImpl.FindNextId = " + ex.getMessage());
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
        return rewardId;
    }
    
    
    
    /***** Create Methods *****/
    public static Reward create(Reward reward) {
        return RewardImpl.create(reward, null);
    }
    public static Reward create(Reward reward, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(reward == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            reward.setRewardId(FormImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(RewardImpl.CREATE);
            ps.setInt(1, reward.getRewardId());
            ps.setInt(2, reward.getCode().getCodeId());
            ps.setInt(3, reward.getForm().getFormId());
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
        return reward;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByRewardId(int rewardId) {
        RewardImpl.deleteByRewardId(rewardId, null);
    }
    public static void deleteByRewardId(int rewardId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(RewardImpl.DELETE_BY_REWARD_ID);
            ps.setInt(1, rewardId);
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
        RewardImpl.deleteByRevisionId(revisionId, null);
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
            ps = con.prepareStatement(RewardImpl.DELETE_BY_REVISION_ID);
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