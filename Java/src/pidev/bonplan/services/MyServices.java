 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.Date;
import pidev.sandy.entites.Personne;
import pidev.sandy.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.sandy.entites.Badge;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Categoris;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.DemandeCadeau;
import pidev.sandy.entites.User;

/**
 *
 * @author X
 */
public class MyServices {

    /**
     * **************************Partie
     * Personne***********************************
     */
    public void ajouterPersonne2(Personne p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO personne(nom,prenom) VALUES(?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());

            pst.executeUpdate();

            System.out.println("Perosonne ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierPersonne1(Personne p) {
        try {
            String requette2 = "UPDATE personne SET nom=?,prenom=? Where id=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            pst2.setInt(3, p.getId());
            pst2.setString(2, p.getNom());
            pst2.setString(1, p.getPrenom());

            pst2.executeUpdate();
            System.out.println("Modification avec succe");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Personne> AfficherListPersonne() {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */

        List<Personne> myListe = new ArrayList<>();
        String requette2 = "select * from personne";
        try {

            Statement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            ResultSet ResListe = pst2.executeQuery(requette2);

            while (ResListe.next()) {

                Personne p = new Personne();
                p.setId(ResListe.getInt(1));
                p.setNom(ResListe.getString(2));
                p.setPrenom(ResListe.getString(3));

                p.setId(ResListe.getInt(1));

                myListe.add(p);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return myListe;

    }

    public boolean supprimerPersonne(Personne p) {
        String sql = "DELETE FROM personne WHERE id=?";
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);

            stmt.setInt(1, p.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Personne chercherPersonne(Personne p) {
        String sql = "SELECT * FROM personne WHERE id=?";
        Personne personne = new Personne();
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, p.getId());
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                p.setNom(res.getString("abdelkader"));
                personne = p;
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return personne;
    }

    /**
     * **************************Partie
     * Compte**********************************
     */
    public void ajouterUtilisateurs(User u) {
        try {
            System.out.println(u.toString());
            String requete = "INSERT INTO fosuser (username,username_canonical,email,email_canonical,enabled,password,"
                    + "roles,nom,prenom,phone,image,date_naissance,date_inscription,genre) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getUsername());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getEmail());
            pst.setInt(5, u.getEnabled());
            pst.setString(6, u.getPassword());
            pst.setString(7, u.getRoles());
            pst.setString(8, u.getNom());
            pst.setString(9, u.getPrenom());
            pst.setString(10, u.getPhone());
            pst.setString(11, u.getImage());
            pst.setString(12, u.getDate_naissance());
            pst.setString(13, u.getDate_inscription());
            pst.setString(14, u.getGenre());

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifierUtilisateurs(User u) {
        try {
            System.out.println(u.toString());
            String requete = "update fosuser set username=?,username_canonical=?,email=?,email_canonical=?,password=?,enabled=?,roles=?,"
                    + "nom=?,prenom=?,phone=?,image=?,date_naissance=?,date_inscription=?,genre=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getUsername_canonical());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getEmail());
             pst.setString(5, u.getPassword());
            pst.setInt(6, u.getEnabled());
           
            pst.setString(7, u.getRoles());
            pst.setString(8, u.getNom());
            pst.setString(9, u.getPrenom());
            pst.setString(10, u.getPhone());
            pst.setString(11, u.getImage());
            pst.setString(12, u.getDate_naissance());
            pst.setString(13, u.getDate_inscription());
            pst.setString(14, u.getGenre());
            
            
   pst.setInt(15, u.getId());
            pst.executeUpdate();
            System.out.println("Modification effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
       public void modifierEMAILUtilisateurs(User u,int id) {
        try {
            System.out.println(u.toString());
            String requete = "update fosuser set email=?,email_canonical=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1, u.getEmail());
            pst.setString(2, u.getEmail());
          
          
   pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("Modification de l'email effectué avec succés"+u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
       public void modifierPasswordUtilisateurs(User u,int id) {
        try {
            System.out.println(u.toString());
            String requete = "update fosuser set password=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1, u.getPassword());
      
          
          
   pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Modification de password effectué avec succés"+u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
       public void modifierImageUtilisateurs(User u,int id) {
        try {
            System.out.println(u.toString());
            String requete = "update fosuser set image=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1, u.getImage());
      
          
          
   pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Modification de image effectué avec succés"+u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public void modifierUtilisateursInformationPersonnel(User u,int id) {
        try {
            System.out.println(u.toString());
            String requete = "update fosuser set username=?,username_canonical=?,roles=?,"
                    + "nom=?,prenom=?,phone=?,genre=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
      pst.setString(1, u.getUsername());
            pst.setString(2, u.getUsername());
         
           
            pst.setString(3, u.getRoles());
            pst.setString(4, u.getNom());
            pst.setString(5, u.getPrenom());
            pst.setString(6, u.getPhone());
         
           
 
            pst.setString(7, u.getGenre());
            
            
   pst.setInt(8, id);
            pst.executeUpdate();
            System.out.println("Modification d'information personnelle  effectué avec succés"+u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
   
                 
 
    
    

    public void supprimerUtilisateur(User u) {
        try {
            String requete = "delete from fosuser where id=?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> afficherlisteUtilisateurs() {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */

        List<User> myListe = new ArrayList<>();
        String requette2 = "select * from fosuser";
        try {

            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            ResultSet ResListe = pst2.executeQuery(requette2);

            while (ResListe.next()) {

                User p = new User();
                p.setId(ResListe.getInt(1));
                p.setUsername(ResListe.getString(2));
                p.setUsername_canonical(ResListe.getString(3));
                p.setEmail(ResListe.getString(4));
                p.setEmail_canonical(ResListe.getString(5));
               

                p.setPassword(ResListe.getString(7));

                p.setRoles(ResListe.getString(12));
                p.setNom(ResListe.getString(13));
                p.setPrenom(ResListe.getString(14));
                p.setPhone(ResListe.getString(15));
                p.setImage(ResListe.getString(16));
                p.setDate_naissance(ResListe.getString(17));
                p.setDate_inscription(ResListe.getString(18));
                p.setGenre(ResListe.getString(19));
                
                
        
                myListe.add(p);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
        public List<String> afficherlisteMembreAPointMerciSuffisant(String r,float  quantite) {
        List<String> list = new ArrayList<String>();
        String req = "select f.username from fosuser f,compte c where c.id_user=f.id and roles =? and c.point_merci > ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, r);
             preparedStatement.setInt(2, Math.round(quantite));
            ResultSet resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
             User   user = new User();
             
      
             user.setUsername( resultSet.getString("username"));
                       
                       
              
             list.add(user.getUsername());
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
      public List<String> afficherlistePartenaire(String r) {
        List<String> list = new ArrayList<String>();
        String req = "select username from fosuser where roles =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
             User   user = new User();
             
      
             user.setUsername( resultSet.getString("username"));
                       
                       
              
             list.add(user.getUsername());
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public User chercherUtilisateurByid(User U) {
        String sql = "SELECT * FROM fosuser WHERE id=?";
           User user = null;
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, U.getId());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                 
                 user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
             
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public User chercherUtilisateurByid(Integer r) {
        User user = null;
        String req = "select * from fosuser where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
                user = new User( 
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                         
                        
                        
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("image"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
      public User chercherUtilisateurByPhoneUsername(String r,String username) {
        User user = null;
        String req = "select * from fosuser where phone =? and username=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, r);
            preparedStatement.setString(2, username);
            ResultSet resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean chercherUtilisateurBylogin(String s) {
        User user = null;
        String req = "select * from fosuser where username =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
    public User chercherUtilisateurByUsername(String s) {
        User user = null;
     
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement("select * from fosuser where username =?");
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return null;
        }
        return user;
    }
    public Compte chercherUtilisateurByUsernameDansLecompte(String s) {
        Compte compte = null;
        Badge badge = new Badge();
        User user = new User();
        Cadeaux cadeaux = new Cadeaux();
 
         
   
        try {
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement("select c.* from fosuser f,compte c where c.id_user=f.id and f.username =? ");
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  compte = new Compte();
                       compte.setIdCompte(resultSet.getInt("id"));
                       
                       /*****************On Prend Le  badge et compte*************************/
                       badge=chercherBadgeById(resultSet.getInt("id_badge"));
                       cadeaux=chercherCadeauxById(resultSet.getInt("id_cadeau"));
                       user=chercherUtilisateurByid(resultSet.getInt("id_user"));
                       compte.setId_badge(badge);
                       compte.setId_cadeau(cadeaux);
                       compte.setId_user(user);
                  
                        compte.setPoint_merci(resultSet.getInt("point_merci")
                       );
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (compte == null) {
            return null;
        }
        return compte;
    }

 

    public boolean chercherUtilisateurBypassword(String s, String p) {
        User user = null;
        String req = "select password from user where username =?";
        System.out.println(s + "     " + p);
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
              while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
            System.out.println(user.getPassword());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        if (user.getPassword().equals(p)) {
            return true;

        }
        return false;
    }
    
    
     public Boolean verifierpassword(String pword, String uname) {
        String s1 = "";
        String req = "Select password from fosuser where username= ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString(1);
               // String s2=uname+"{"+s1+"}";
 
                  
              
                System.out.println("ili 3malnelou deccryptage==>"+Password.checkPassword(pword,s1));
                  //   System.out.println(uname);
                   System.out.println(s1);
                 
         
                if ( (Password.checkPassword(pword,s1))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }
     
     public boolean chercherUtilisateurByEmail(String s) {
        User user = null;
        String req = "select * from fosuser where email =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                        resultSet.getString("genre"));
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
     
     
      public String Gettype(String s) {
        String s1 = "";
        String req = "select roles from fosuser where username =?";
        PreparedStatement preparedStatement;
        try {
          preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString("roles");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s1;
    }
     
   
     public void changepassword(String s1, String numtel) {
        try {
            String requete = "update fosuser set password=? where phone=? ";
         PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setString(1, s1);
            preparedStatement.setString(2, numtel);
        
            preparedStatement.executeUpdate();
            System.out.println("Modification effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
     
     
     

    /**
     * **************************Partie
     * badge***********************************
     */
     
      public Badge chercherBadgeById(int s) {
        Badge badge = null;
        String req = "select * from badge where id =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  badge = new Badge(
                        resultSet.getInt("id"),
                        resultSet.getString("level")
                  );
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (badge == null) {
            return null;
        }
        return badge;
    }
    /**
     * **************************Partie
     * BonCadeaux***********************************
     */
      
       public boolean AjouteBonCadeaux(Boncadeaux BD) throws SQLException {
         
        
 
        try {
        System.out.println(BD.toString());
            String requete = "INSERT INTO boncadeaux (type_bon,description,date_cadeaux,MembreConcerne,Cadeaux) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
            stmt.setString(1, (BD.getType_bonBoncadeaux()));
            stmt.setString(2, BD.getDescriptionBoncadeaux());
                 stmt.setString(3,  (BD.getDate_cadeaux()));
            stmt.setInt(4, BD.getMembreConcerne().getIdCompte());
            stmt.setInt(5, BD.getCadeaux().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }}
       
       
       
         public void supprimerBonCadeaux(Boncadeaux C) {
        try {
            String requete = "delete from boncadeaux where id=?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, C.getIdBoncadeaux());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Boncadeaux> afficherlisteBoncadeaux() {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */

        List<Boncadeaux> myListe = new ArrayList<>();
        String requette2 = "select * from boncadeaux";
        try {

            Statement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            ResultSet ResListe = pst2.executeQuery(requette2);

            while (ResListe.next()) {
                
                Compte compte=new Compte();
                Cadeaux cadeaux=new Cadeaux();

                Boncadeaux boncadeaux = new Boncadeaux(ResListe.getInt(1), ResListe.getString(2), ResListe.getString(3), ResListe.getString(4));
                compte.setIdCompte(ResListe.getInt(5));
                cadeaux.setId(ResListe.getInt(6));
      /********************Il faut faire une categ*****************************************/
      
 MyServices myServices=new MyServices();
 cadeaux=myServices.chercherCadeauxById(cadeaux.getId());
 compte=myServices.chercherCompteById(compte.getIdCompte());
 
 boncadeaux.setCadeaux(cadeaux);
 boncadeaux.setMembreConcerne(compte);
                System.out.println(boncadeaux.toString());
          
                myListe.add(boncadeaux);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
       
       
       
       
       
    /**
     * **************************Partie
     * DemandeCadeaux***********************************
     */
     
     
      /**
     * **************************Partie
     * Cadeaux***********************************
     */
     
      public void ajouterCadeaux(Cadeaux C) {
        try {
            System.out.println(C.toString());
            String requete = "INSERT INTO cadeaux (libelle,description,prix_reel,valeur_point,quantite_initial,"
                    + "quantite_actuel,image,categorisCadeaux) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, C.getLibelle());
            pst.setString(2, C.getDescription());
            pst.setFloat(3, C.getPrix_reel());
            pst.setFloat(4, C.getValeur_point());
            pst.setInt(5, C.getQuantite_initial());
            pst.setInt(6, C.getQuantite_actuel());
            pst.setString(7, C.getImage());
            pst.setInt(8, C.getCategorisCadeaux().getId());

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifierCadeaux(Cadeaux C) {
        try {
            System.out.println(C.toString());
            String requete = "update cadeaux set libelle=?,description=?,prix_reel=?,valeur_point=?,quantite_initial=?,"
                    + "quantite_actuel=?,"
                    + "image=?,categorisCadeaux=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
       pst.setString(1, C.getLibelle());
            pst.setString(2, C.getDescription());
            pst.setFloat(3, C.getPrix_reel());
            pst.setFloat(4, C.getValeur_point());
            pst.setInt(5, C.getQuantite_initial());
            pst.setInt(6, C.getQuantite_actuel());
            pst.setString(7, C.getImage());
            pst.setInt(8, C.getCategorisCadeaux().getId());
           
 
            
            
   pst.setInt(9, C.getId());
            pst.executeUpdate();
            System.out.println("Modification effectué avec succés"+C.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void supprimerCadeaux(Cadeaux C) {
        try {
            String requete = "delete from cadeaux where id=?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, C.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Cadeaux> afficherlisteCadeaux() {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */

        List<Cadeaux> myListe = new ArrayList<>();
        String requette2 = "select * from cadeaux";
        try {

            Statement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            ResultSet ResListe = pst2.executeQuery(requette2);

            while (ResListe.next()) {
                
                Categoris categoris=new Categoris();
                Cadeaux cadeaux=new Cadeaux();

                Cadeaux p = new Cadeaux(ResListe.getInt(1), ResListe.getString(2), ResListe.getString(3), ResListe.getFloat(4),
                        ResListe.getFloat(5), ResListe.getInt(6), ResListe.getInt(7),ResListe.getString(8));
                categoris.setId(ResListe.getInt(9));
      /********************Il faut faire une categ*****************************************/
      
 MyServices myServices=new MyServices();
 categoris=myServices.chercherCategorieById(categoris);
 
 p.setCategorisCadeaux(categoris);
          
                myListe.add(p);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
    
       public Cadeaux  chercherCadeauxById(int id) {
        String sql = "SELECT * FROM cadeaux WHERE id=?";
        
        Cadeaux  cadeaux = new Cadeaux();
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet Res = stmt.executeQuery();
            if (Res.next()) {
                
               
                cadeaux.setId(Res.getInt(1));
                cadeaux.setLibelle(Res.getString(2));
                cadeaux.setDescription(Res.getString(3));
                cadeaux.setPrix_reel(Res.getFloat(4));
                cadeaux.setValeur_point(Res.getFloat(5));
                cadeaux.setQuantite_initial(Res.getInt(6));
                cadeaux.setQuantite_actuel(Res.getInt(7));
                cadeaux.setImage(Res.getString(8));
                Categoris categoris=new Categoris();
                categoris.setId(Res.getInt(9));
                cadeaux.setCategorisCadeaux(categoris);
            
                
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return cadeaux;
    }
       public Cadeaux  chercherCadeauxByLibelle(String Libelle) {
    
        
        Cadeaux  cadeaux = new Cadeaux();
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT * FROM cadeaux WHERE libelle=?");
            stmt.setString(1, Libelle);
            ResultSet Res = stmt.executeQuery();
            if (Res.next()) {
                
               
                cadeaux.setId(Res.getInt(1));
                cadeaux.setLibelle(Res.getString(2));
                cadeaux.setDescription(Res.getString(3));
                cadeaux.setPrix_reel(Res.getFloat(4));
                cadeaux.setValeur_point(Res.getFloat(5));
                cadeaux.setQuantite_initial(Res.getInt(6));
                cadeaux.setQuantite_actuel(Res.getInt(7));
                cadeaux.setImage(Res.getString(8));
                Categoris categoris=new Categoris();
                categoris.setId(Res.getInt(9));
                cadeaux.setCategorisCadeaux(categoris);
            
                
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return cadeaux;
    }
    
    
    
    
/***************************Bon Plan**********************************/
          public BonPlan  chercherBonPlanById(BonPlan bonPlan) {
        String sql = "SELECT * FROM bon_plan WHERE id=?";
        BonPlan  BP = new BonPlan();
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, bonPlan.getId());
            ResultSet Res = stmt.executeQuery();
            if (Res.next()) {
                bonPlan.setId(Res.getInt("id"));
                BP = bonPlan;
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return BP;
    }
    
    
    
     
    
    
    
    
    
    
    
    
       public List<BonPlan> afficherlisteBonPlanParUser22(User U) {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */
    
         List<BonPlan> myListe = new ArrayList<>();
   
       
       
        try {

            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement("SELECT * FROM bon_plan where ref_compte=? ");
          pst2.setInt(1, U.getId());
            ResultSet ResListe = pst2.executeQuery();

            while (ResListe.next()) {
                
            
                

                   BonPlan  bonPlan = new BonPlan(ResListe.getInt(1), ResListe.getInt(4),
                           ResListe.getString(5),ResListe.getInt(6), ResListe.getString(7)
                           , ResListe.getString(8),ResListe.getString(9),ResListe.getFloat(10),
                           ResListe.getTime(11), ResListe.getTime(12), ResListe.getInt(13)
                           , ResListe.getString(14), ResListe.getInt(15), ResListe.getInt(16)
                           , ResListe.getInt(17), ResListe.getInt(18), ResListe.getString(19));
                     
      /********************Il faut faire une categ et user*****************************************/
      
      User user=new User();
      user=U;
     Categoris categoris=new Categoris();

     
 MyServices myServices=new MyServices();
      categoris=myServices.chercherCategorieById(ResListe.getInt(3));
categoris=myServices.chercherCategorieById(categoris);
 user=myServices.chercherUtilisateurByid(user);
 
 bonPlan.setRefcategorie(categoris);
 bonPlan.setRef_compte(user);
          
                myListe.add(bonPlan);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
       
       
       
          public BonPlan  chercherBonPlanBylibelle(String libelle) {
         BonPlan bonPlan=null;
        
          try {

            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement("SELECT * FROM bon_plan WHERE libelle=?");
          pst2.setString(1, libelle);
            ResultSet ResListe = pst2.executeQuery();

            while (ResListe.next()) {
                
            
                

                      bonPlan = new BonPlan(ResListe.getInt(1), ResListe.getInt(4),
                           ResListe.getString(5),ResListe.getInt(6), ResListe.getString(7)
                           , ResListe.getString(8),ResListe.getString(9),ResListe.getFloat(10),
                           ResListe.getTime(11), ResListe.getTime(12), ResListe.getInt(13)
                           , ResListe.getString(14), ResListe.getInt(15), ResListe.getInt(16)
                           , ResListe.getInt(17), ResListe.getInt(18), ResListe.getString(19));
                     
      /********************Il faut faire une categ et user*****************************************/
      
      User user=new User();
      
     Categoris categoris=new Categoris();

     
 MyServices myServices=new MyServices();
      categoris=myServices.chercherCategorieById(ResListe.getInt(3));
categoris=myServices.chercherCategorieById(categoris);
 user=myServices.chercherUtilisateurByid(ResListe.getInt(2));
 
 bonPlan.setRefcategorie(categoris);
 bonPlan.setRef_compte(user);
          
              
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


          if (bonPlan == null) {
            return null;
        }
        return bonPlan;

    } 
       
       
       /**
     * **************************Partie
     * Compte***********************************
     */
     
     
     public void ajouterCompteUtilisateurs(Compte C) {
        try {
            System.out.println(C.toString());
            String requete = "INSERT INTO compte (id_user,point_merci) VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, C.getId_badge().getIdBadge());
//            pst.setInt(2, C.getId_cadeau().getId());
            pst.setInt(1, C.getId_user().getId());
            pst.setInt(2, 0);
         

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     /*************Partie Categors**************/
      public Categoris chercherCategorieById(int id) {
         
      
        String sql = "SELECT * FROM categorie WHERE id=? ";
        Categoris Cat = new Categoris();
  
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, id);
             
            ResultSet Res = stmt.executeQuery();
           
            if (Res.next()) {
                Cat.setId(Res.getInt("id"));
                Cat.setDiscription(Res.getString("discription"));
                
                Cat.setIdcategoriemere(Cat.getIdcategoriemere());
                
             
                
                Cat.setImage(Res.getString("image"));
                Cat.setLibelle(Res.getString("libelle"));
             
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
         //System.out.println("Cat mere");
         //System.out.println(Cat.toString());
        return Cat;
    }
      public List<Categoris> chercherCategorieBylibelle(String valeur) {
        List<Categoris> myListCadeaux = new ArrayList<Categoris>();
        String requete = null;
          MyServices myServices=new MyServices();
        try { 
           
                requete = "SELECT * from categorie where libelle like '%" + valeur + "%'"; 
    

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);  
            ResultSet Res = pst.executeQuery(requete);
            while (Res.next()) {
                Categoris categoria = new Categoris();
                    categoria.setId(Res.getInt("id"));
                categoria.setDiscription(Res.getString("discription"));
                
                categoria.setIdcategoriemere(categoria.getIdcategoriemere());
                
             
                
                categoria.setImage(Res.getString("image"));
                categoria.setLibelle(Res.getString("libelle"));
         myListCadeaux.add(categoria);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListCadeaux;

    }
 
     public Categoris chercherCategorieById(Categoris categoria) {
         
      
        String sql = "SELECT * FROM categorie WHERE id=? ";
        Categoris Cat = new Categoris();
  
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
             
            ResultSet Res = stmt.executeQuery();
           
            if (Res.next()) {
                categoria.setId(Res.getInt("id"));
                categoria.setDiscription(Res.getString("discription"));
                
                categoria.setIdcategoriemere(categoria.getIdcategoriemere());
                
             
                
                categoria.setImage(Res.getString("image"));
                categoria.setLibelle(Res.getString("libelle"));
                Cat = categoria;
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
         //System.out.println("Cat mere");
         //System.out.println(Cat.toString());
        return Cat;
    }
     public Categoris chercherCategorieByCateMere(Categoris categoria) {
      
      
        String sql = "SELECT * FROM categorie WHERE idcategoriemere=? ";
        Categoris Cat = new Categoris();
  
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            /****************Chercher de la cat mere ***************************/
            Categoris Catmere = new Categoris();
            
            MyServices myServices=new MyServices();
            Catmere=myServices.chercherCategorieById(categoria);
            Cat.setIdcategoriemere(Catmere);
            
            
            
            stmt.setInt(1, Cat.getId());
             
            ResultSet Res = stmt.executeQuery();
           
            if (Res.next()) {
                categoria.setId(Res.getInt("id"));
                categoria.setDiscription(Res.getString("discription"));
                
                categoria.setIdcategoriemere(categoria.getIdcategoriemere());
                
             
                
                categoria.setImage(Res.getString("image"));
                categoria.setLibelle(Res.getString("libelle"));
                Cat = categoria;
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        
        return Cat;
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
        public Compte  chercherCompteById(int id) {
       
        
        Compte  compte = new Compte();
        try {
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT * FROM compte WHERE id=?");
            stmt.setInt(1, id);
            ResultSet Res = stmt.executeQuery();
            if (Res.next()) {
                
               
                compte.setIdCompte(Res.getInt(1));
                    Badge badge=new Badge();
                badge.setIdBadge(Res.getInt(2));
                compte.setId_badge(badge);
                    Cadeaux cadeaux=new Cadeaux();
                cadeaux.setId(Res.getInt(3));
                compte.setId_cadeau(cadeaux);
                    User user=new User();
                user.setId(Res.getInt(4));
                compte.setId_user(user);
                
                
                compte.setPoint_merci(Res.getInt(5));
              
                
                
            }
        }       catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return compte;
    }
     
     
     
     
 public boolean AjouteDemandecadeauxMembre(DemandeCadeau BD) throws SQLException {
         
        
 
        try {
        System.out.println(BD.toString());
            String requete = "INSERT INTO demandecadeaux (status,description,datedemande,message"
                    + ""
                    + ",datedexpiration,MembreDemande,Cadeaux) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
            stmt.setString(1, (BD.getStatusDemandeCadeaux()));
            stmt.setString(2, BD.getDescriptionDemandeCadeaux());
                 stmt.setString(3,  (BD.getDatedemandeDemandeCadeaux()));
            stmt.setString(4, BD.getMessageDemandeCadeaux());
            stmt.setString(5, BD.getDatedexpirationDemandeCadeaux());
            stmt.setInt(6, BD.getMembreDemande().getIdCompte());
            stmt.setInt(7, BD.getCadeaux().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }}
     
          public void modifierDemandecadeauxMembre(DemandeCadeau C,int idMembre) {
        try {
            System.out.println(C.toString());
            String requete = "update demandecadeaux set description=?,datedemande=?,message=?,datedexpiration=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
       pst.setString(1, C.getDescriptionDemandeCadeaux());
            pst.setString(2, C.getDatedemandeDemandeCadeaux());
       pst.setString(3, C.getMessageDemandeCadeaux());
            pst.setString(4, C.getDatedexpirationDemandeCadeaux());
            System.out.println("id----------->"+C.getIdDemandeCadeaux());
     
   pst.setInt(5, idMembre);
            pst.executeUpdate();
            System.out.println("Modification effectué avec succés"+C.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
        
        
        
        public void modifierDemandecadeaux(DemandeCadeau C) {
        try {
            System.out.println(C.toString());
            String requete = "update demandecadeaux set status=?,message=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
       pst.setString(1, C.getStatusDemandeCadeaux());
            pst.setString(2, C.getMessageDemandeCadeaux());
     
   pst.setInt(3, C.getIdDemandeCadeaux());
            pst.executeUpdate();
            System.out.println("Modification effectué avec succés"+C.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
        
           public List<DemandeCadeau> afficherlisteDemandecadeaux() {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */

        List<DemandeCadeau> myListe = new ArrayList<>();
        String requette2 = "select * from demandecadeaux";
        try {

            Statement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requette2);
            ResultSet ResListe = pst2.executeQuery(requette2);

            while (ResListe.next()) {
                
                Compte compte=new Compte();
                Cadeaux cadeaux=new Cadeaux();

                DemandeCadeau demandeCadeau = new DemandeCadeau(ResListe.getInt(1), ResListe.getString(2), ResListe.getString(3), ResListe.getString(4)
                , ResListe.getString(5), ResListe.getString(6) );
                compte.setIdCompte(ResListe.getInt(7));
                
             
                cadeaux.setId(ResListe.getInt(8));
      /********************Il faut faire une categ*****************************************/
      
 MyServices myServices=new MyServices();
 cadeaux=myServices.chercherCadeauxById(cadeaux.getId());
 compte=myServices.chercherCompteById(compte.getIdCompte());
 
 demandeCadeau.setCadeaux(cadeaux);
 demandeCadeau.setMembreDemande(compte);
    System.out.println(compte.toString());
                System.out.println(cadeaux.toString());
                System.out.println(demandeCadeau.toString());
          
                myListe.add(demandeCadeau);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
        
            public void supprimerDemandeCadeau(DemandeCadeau C) {
        try {
            String requete = "delete from demandecadeaux where id=?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, C.getIdDemandeCadeaux());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
            public List<DemandeCadeau> afficherlisteDemandecadeauxParMembre(int user) {
        /*Analyse de la requête
Compilation de la requête
Optimisation de la requête
Exécution de la requête  
         */
        
        List<DemandeCadeau> myListe = new ArrayList<>();
       
//                  user=22;

        try {     Statement  preparedStatement = MyConnection.getInstance().getCnx().createStatement();
              String req = "SELECT * FROM demandecadeaux where MembreDemande='" + user + "'";
            ResultSet ResListe = preparedStatement.executeQuery(req);
            
 
   System.out.println("Id"+user);
            while (ResListe.next()) {
                     
                Compte compte=new Compte();
                Cadeaux cadeaux=new Cadeaux();

                DemandeCadeau demandeCadeau = new DemandeCadeau(ResListe.getInt(1), ResListe.getString(2), ResListe.getString(3), ResListe.getString(4)
                , ResListe.getString(5), ResListe.getString(6) );
                compte.setIdCompte(ResListe.getInt(7));
                
             
                cadeaux.setId(ResListe.getInt(8));
      /********************Il faut faire une categ*****************************************/
      
 MyServices myServices=new MyServices();
 cadeaux=myServices.chercherCadeauxById(cadeaux.getId());
 compte=myServices.chercherCompteById(compte.getIdCompte());
 
 demandeCadeau.setCadeaux(cadeaux);
 demandeCadeau.setMembreDemande(compte);
    System.out.println(compte.toString());
                System.out.println(cadeaux.toString());
                System.out.println(demandeCadeau.toString());
          
                myListe.add(demandeCadeau);
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }


        return myListe;

    }
//           public User afficherMembre(String r,float  quantite) {
//        List<String> list = new ArrayList<String>();
//        String req = "select f.username from fosuser f,compte c where c.id_user=f.id and roles =? and c.point_merci > ?";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
//            preparedStatement.setString(1, r);
//            preparedStatement.setFloat(2, quantite);
//            ResultSet resultSet = preparedStatement.executeQuery();
//        
//            while (resultSet.next()) {
//             User   user = new User();
//             
//      
//             user.setUsername( resultSet.getString("username"));
//                       
//                       
//              
//             list.add(user.getUsername());
//                    
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
           
           
           
            public void NoterMembre(Compte u,int id) {
        try {
            System.out.println(u.toString());
            String requete = "update compte set point_merci=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.setInt(1, u.getPoint_merci());
               pst.setInt(2, id);
          
          

            pst.executeUpdate();
            System.out.println("Membre est noter avec suceé"+u.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
            
            
            
            
            
            
            
            
            
            
            
            
            
}

 
    
                 
                    
    

    