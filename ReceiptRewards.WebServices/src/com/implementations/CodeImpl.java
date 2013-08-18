package com.implementations;

import com.common.DBConnection;
import com.model.Code;
import com.model.CodeType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CodeImpl {

    private static final String FIND_ALL = "SELECT * FROM code;";
    private static final String FIND_BY_TYPE = "SELECT * FROM code WHERE code_type_id = ?;";
    private static final String FIND_BY_ID = "SELECT * FROM code WHERE code_id = ?;";
    private static final String FIND_BY_CODE = "SELECT * FROM code WHERE code_value = ? AND code_type_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 code_id FROM code ORDER BY code_id DESC;";
    private static final String CREATE = "INSERT INTO code (code_id, code_value, name, description, code_type_id) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE code SET code_value = ?, name = ?, description = ? WHERE code_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM code WHERE code_id = ?;";
    private static final String DELETE_BY_CODE = "DELETE FROM code WHERE code = ?;";
    
    
    
    /***** Find Methods *****/
    public static List<Code> findAll() {
        return CodeImpl.findAll(null);
    }
    public static List<Code> findAll(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Code> codes = new ArrayList<Code>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Codes
            ps = con.prepareStatement(CodeImpl.FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                codes.add(new Code(rs.getInt("code_id"), rs.getString("code_value"), rs.getString("name"), rs.getString("description"), new CodeType(rs.getInt("code_type_id"))));
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
        return codes;
    }
    public static List<Code> findByType(int type) {
        return CodeImpl.findByType(type, null);
    }
    public static List<Code> findByType(int type, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Code> codes = new ArrayList<Code>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Codes
            ps = con.prepareStatement(CodeImpl.FIND_BY_TYPE);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            while(rs.next()) {
                codes.add(new Code(rs.getInt("code_id"), rs.getString("code_value"), rs.getString("name"), rs.getString("description"), new CodeType(rs.getInt("code_type_id"))));
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
        return codes;
    }
    public static Code findByCodeId(int codeId) {
        return CodeImpl.findByCodeId(codeId, null);
    }
    public static Code findByCodeId(int codeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Code code = new Code();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code
            ps = con.prepareStatement(CodeImpl.FIND_BY_ID);
            ps.setInt(1, codeId);
            rs = ps.executeQuery();
            while(rs.next()) {
                code = new Code(rs.getInt("code_id"), rs.getString("code_value"), rs.getString("name"), rs.getString("description"), new CodeType(rs.getInt("code_type_id")));
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
        return code;
    }
    public static Code findByCode(Code code) {
        return CodeImpl.findByCode(code, null);
    }
    public static Code findByCode(Code code, Connection con) {
        return CodeImpl.findByCode(code.getCode(), code.getCodeType(), con);
    }
    public static Code findByCode(String name, CodeType codeType) {
        return CodeImpl.findByCode(name, codeType, null);
    }
    public static Code findByCode(String name, CodeType codeType, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Code code = new Code();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Complete the Code Type Object
            if(codeType.getCodeTypeId() <= 0) {
                codeType = CodeTypeImpl.findByType(codeType.getType(), con);
            }
            
            // Get All the Codes
            ps = con.prepareStatement(CodeImpl.FIND_BY_CODE);
            ps.setString(1, name);
            ps.setInt(2, codeType.getCodeTypeId());
            rs = ps.executeQuery();
            while(rs.next()) {
                code = new Code(rs.getInt("code_id"), rs.getString("code_value"), rs.getString("name"), rs.getString("description"), new CodeType(rs.getInt("code_type_id")));
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
        return code;
    }
    public static int findNextId() {
        return CodeImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int codeId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CodeImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                codeId = rs.getInt("code_id") + 1;
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
        return codeId;
    }
    
    
    
    /***** Create Methods *****/
    public static Code create(Code code) {
        return CodeImpl.create(code, null);
    }
    public static Code create(Code code, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(code == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            code.setCodeId(CodeImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(CodeImpl.CREATE);
            ps.setInt(1, code.getCodeId());
            ps.setString(2, code.getCode());
            ps.setString(3, code.getName());
            ps.setString(4, code.getDescription());
            ps.setInt(5, code.getCodeType().getCodeTypeId());
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
        return code;
    }
    
    
    
    /***** Update Methods *****/
    public static Code update(Code code) {
        return CodeImpl.update(code, null);
    }
    public static Code update(Code code, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(code == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Create Code
            ps = con.prepareStatement(CodeImpl.UPDATE_BY_ID);
            ps.setString(1, code.getCode());
            ps.setString(2, code.getName());
            ps.setString(3, code.getDescription());
            ps.setInt(4, code.getCodeId());
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
        return null;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteById(int codeId) {
        CodeImpl.deleteById(codeId, null);
    }
    public static void deleteById(int codeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Code
            ps = con.prepareStatement(CodeImpl.DELETE_BY_ID);
            ps.setInt(1, codeId);
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
    public static void deleteByCode(String code) {
        CodeImpl.deleteByCode(code, null);
    }
    public static void deleteByCode(String code, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Code
            ps = con.prepareStatement(CodeImpl.DELETE_BY_CODE);
            ps.setString(1, code);
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
