package com.implementations;

import com.common.DBConnection;
import com.model.CodeType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CodeTypeImpl {

    private static final String FIND_ALL = "SELECT * FROM code_type;";
    private static final String FIND_BY_ID = "SELECT * FROM code_type WHERE code_type_id = ?;";
    private static final String FIND_BY_TYPE = "SELECT * FROM code_type WHERE type = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 code_type_id FROM code_type ORDER BY code_type_id DESC;";
    private static final String CREATE = "INSERT INTO code_type (code_type_id, type, description) VALUES (?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE code_type SET type = ?, description = ? WHERE code_type_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM code_type WHERE code_type_id = ?;";
    private static final String DELETE_BY_TYPE = "DELETE FROM code_type WHERE type = ?;";

    
    
    /***** Find Methods *****/
    public static List<CodeType> findAll() {
        return CodeTypeImpl.findAll(null);
    }
    public static List<CodeType> findAll(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<CodeType> codeTypes = new ArrayList<CodeType>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CodeTypeImpl.FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                codeTypes.add(new CodeType(rs.getInt("code_type_id"), rs.getString("type"), rs.getString("description")));
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
        return codeTypes;
    }
    public static CodeType findByCodeTypeId(int codeTypeId) {
        return CodeTypeImpl.findByCodeTypeId(codeTypeId, null);
    }
    public static CodeType findByCodeTypeId(int codeTypeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        CodeType codeType = new CodeType();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CodeTypeImpl.FIND_BY_ID);
            ps.setInt(1, codeTypeId);
            rs = ps.executeQuery();
            while(rs.next()) {
                codeType = new CodeType(rs.getInt("code_type_id"), rs.getString("type"), rs.getString("description"));
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
        return codeType;
    }
    public static CodeType findByType(String type) {
        return CodeTypeImpl.findByType(type, null);
    }
    public static CodeType findByType(String type, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        CodeType codeType = new CodeType();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CodeTypeImpl.FIND_BY_TYPE);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while(rs.next()) {
                codeType = new CodeType(rs.getInt("code_type_id"), rs.getString("type"), rs.getString("description"));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Errorjkj: " + ex.getMessage());
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
        return codeType;
    }
    public static int findNextId() {
        return CodeTypeImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int codeTypeId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CodeTypeImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                codeTypeId = rs.getInt("code_type_id") + 1;
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
        return codeTypeId;
    }
    
    
    
    /***** Create Methods *****/
    public static CodeType create(CodeType codeType) {
        return CodeTypeImpl.create(codeType, null);
    }
    public static CodeType create(CodeType codeType, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(codeType == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            codeType.setCodeTypeId(CodeTypeImpl.findNextId(con));
            
            // Create Code Type
            ps = con.prepareStatement(CodeTypeImpl.CREATE);
            ps.setInt(1, codeType.getCodeTypeId());
            ps.setString(2, codeType.getType());
            ps.setString(3, codeType.getDescription());
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
        return codeType;
    }
    
    
    
    /***** Update Methods *****/
    public static CodeType update(CodeType codeType) {
        return CodeTypeImpl.update(codeType, null);
    }
    public static CodeType update(CodeType codeType, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(codeType == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Code Type
            ps = con.prepareStatement(CodeTypeImpl.UPDATE_BY_ID);
            ps.setString(1, codeType.getType());
            ps.setString(2, codeType.getDescription());
            ps.setInt(3, codeType.getCodeTypeId());
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
        return codeType;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteById(int codeTypeId) {
        CodeTypeImpl.deleteById(codeTypeId, null);
    }
    public static void deleteById(int codeTypeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Code Type
            ps = con.prepareStatement(CodeTypeImpl.DELETE_BY_ID);
            ps.setInt(1, codeTypeId);
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
    public static void deleteByType(String type) {
        CodeTypeImpl.deleteByType(type, null);
    }
    public static void deleteByType(String type, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Code Type
            ps = con.prepareStatement(CodeTypeImpl.DELETE_BY_TYPE);
            ps.setString(1, type);
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
