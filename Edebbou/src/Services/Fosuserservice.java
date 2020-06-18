/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Fos_user;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.BCrypt;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


 
/**
 *
 * @author Fida
 */
public class Fosuserservice implements IFosUserServices {
    Connection Conn;
    Statement stm;

    ObservableList<Fos_user> obList = FXCollections.observableArrayList();
    public Fosuserservice() {
        try {
            Conn = ConnexionBD.getInstance().getConnection();
            stm = Conn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String username, String motpass) {

        try {
            PreparedStatement pt = Conn.prepareStatement("select * from fos_user where email=? ");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
           while (rs.next()) {
                if (BCrypt.checkpw(motpass, rs.getString("password")) == true) {
                    
                return true;
            }}
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 public int getIdbymail(String mail) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where email=?");
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
 public String getRolebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("roles");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }
public String getUsenamebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

 public void ajouterClient(Fos_user c) {
        try {
            String req
                    = "INSERT INTO `fos_user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`,  `nom`, `prenom`, `phone`, `datenaiss`) VALUES "
                    + "( '" + c.getUsername() + "', '" + c.getUsernameCanonical() + "', '" + c.getEmail() + "', '" + c.getEmailCanonical() + "', '1', NULL,  '" +BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()) + "', NULL, NULL, NULL, 'a:0:{}', '" + c.getFirstname() + "', '" + c.getLastname() + "',  '" + c.getPhone() + "','" + c.getDatenaiss() + "')";

            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = Conn.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }
   public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = Conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }
   public void modifierClient(Fos_user c, int id) {
        PreparedStatement pt;
        try {
                    String req= "UPDATE fos_user SET username=?,"
                    + "username_canonical=?,email=?,"
                    + "email_canonical=?,"
                    
                    
                    + "nom=?,prenom=?,phone=?,datenaiss=? where id=?";
                                pt = Conn.prepareStatement(req);

            pt.setString(1, c.getUsername());
            pt.setString(2, c.getUsernameCanonical());
            pt.setString(3, c.getEmail());
            pt.setString(4, c.getEmailCanonical());
            pt.setString(5, c.getFirstname());
            pt.setString(6, c. getLastname());
            pt.setString(7, c.getPhone());
            pt.setString(8, c.getDatenaiss());
            pt.setInt(9, id);
          //  System.out.println(req);

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public ObservableList afficher()  
    {
    List<Fos_user> array= new ArrayList<>();
        Conn = ConnexionBD.getInstance().getConnection();
        ResultSet rs;//   obList.clear();

         try {
           
			PreparedStatement st= Conn.prepareStatement("select * from fos_user where roles='a:0:{}'");
			ResultSet res= st.executeQuery();
        

     while (res.next()) {        
        int id = res.getInt(1);
               String datenaiss=res.getString(16);
               String phone=res.getString(15);
               String email=res.getString(4);
               String nom=res.getString(2);
              obList.add(new Fos_user(id,nom,email,phone,datenaiss));
                System.out.println("dattttt = "+obList);
                
               }
     st.close();
      } catch (SQLException ex) {
            
        }
         
         return obList;
     
    }
     public void supprimer(int p) {
         try {
              String req2 =
                      "delete from fos_user where id=?";    
             
              PreparedStatement ps = 
                      Conn.prepareStatement(req2);
              ps.setInt(1, p);
              ps.executeUpdate();
           
          } catch (SQLException ex) {
              System.out.println(ex);
          }
    } 
     public static boolean ControleNOM(Fos_user u) {
		String str = (u.getUsername()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
       public Fos_user getuser(Fos_user u) throws SQLException {  
        Fos_user us = new Fos_user();
        PreparedStatement pre = Conn.prepareStatement("SELECT * FROM fos_user WHERE email LIKE ? ;");

        pre.setString(1, u.getEmail());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String email = rs.getString(4);
            String phone = rs.getString(15);
            String datenaiss = rs.getString(16); 
            String roles = rs.getString(12);
                    
            us.setId(id_user);
            us.setUsername(nom);
            us.setPhone(phone);
            us.setDatenaiss(datenaiss);
            us.setEmail(email);
            us.setRoles(roles);
        }
        return us;

    }
       public Fos_user getuser_id(int id) throws SQLException {  
        Fos_user us = new Fos_user();
        PreparedStatement pre = Conn.prepareStatement("SELECT * FROM fos_user WHERE id LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
              int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String email = rs.getString(4);
            String phone = rs.getString(15);
            String datenaiss = rs.getString(16); 
            String roles = rs.getString(12);
                    
            us.setId(id_user);
            us.setUsername(nom);
            us.setPhone(phone);
            us.setDatenaiss(datenaiss);
            us.setEmail(email);
            us.setRoles(roles);
           
        }
        return us;

    }
          public void getDefendants(String tab) throws Exception {

        @SuppressWarnings("unused")
        Workbook readWorkbook;
        readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\Fida\\Desktop\\Text.xlsx.xls"));
        @SuppressWarnings("resource")
        Workbook writeWorkbook = (Workbook) new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");

       
            Statement ste = null;
            ResultSet rs = null;
            try {
                String req = "SELECT * FROM fos_user";

                ste = Conn.createStatement();
                rs = ste.executeQuery(req);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                Row desRow1 = desSheet.createRow(0);
                for (int col = 0; col < columnsNumber; col++) {
                    Cell newpath = desRow1.createCell(col);
                    newpath.setCellValue(rsmd.getColumnLabel(col + 1));
                }
                while (rs.next()) {
                    System.out.println("Row number" + rs.getRow());
                    Row desRow = desSheet.createRow(rs.getRow());
                    for (int col = 0; col < columnsNumber; col++) {
                        Cell newpath = desRow.createCell(col);
                        newpath.setCellValue(rs.getString(col + 1));
                    }
                    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Fida\\Desktop\\Text.xlsx.xls");
                    writeWorkbook.write(fileOut);
                    fileOut.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to get data from database");
            }
        }
          
    public String getNombyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public String getPrenombyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("prenom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public String getDatebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getDate("datenaiss").toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Date getDateDbyId(int id) {
        Date d = new Date(1, 1, 1);
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getDate("datenaiss");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public String getTelbyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("phone");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
      public String getMailbyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}

