/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.gluonhq.charm.glisten.control.TextField;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import entite.Fos_user;
import service.Fosuserservice;
import service.IFosUserServices;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UserSession;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class DashController {

    @FXML
   private TableView<Fos_user> Tableau;

	@FXML
	private TableColumn<Fos_user, String> ID_Emp;

	@FXML
	private TableColumn<Fos_user, String> Nom;


	@FXML
	private TableColumn<Fos_user, Date> Dare_naissance;

	@FXML
	private TableColumn<Fos_user, String> adresse;

	@FXML
	private TableColumn<Fos_user, String> Num_Tel;

ObservableList<Fos_user>observableList;
ObservableList<Fos_user>e;
    Fosuserservice fosuser = new Fosuserservice();
    

    private TextField RechercherEmp;
    @FXML
    private Button Bt_Supprimer;
    @FXML
    private Text alertRechere;
    @FXML
    private Button Users;
    @FXML
    private Button annonce;
    @FXML
    private javafx.scene.control.TextField filterField;
    @FXML
    private javafx.scene.control.TextField mail;
    @FXML
    private javafx.scene.control.TextField nom;

            UserSession n = UserSession.getInstance();
    @FXML
    private AnchorPane parent1;
    @FXML
    private Button excel;
    @FXML
    private Button login;
    @FXML
    private Button pdf;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button Glivreur;
    @FXML
    private Button GProduit;
    @FXML
    private Button GCategorie;
    @FXML
    private Button Gcommande;
    @FXML
    private Button Ghome;
    @FXML
    private Button GF;


    /**
     * Initializes the controller class.
     */
  
    public void initialize() {
     assert Tableau != null : "fx:id=\"Tableau\" was not injected: check your FXML file 'Dash.fxml'.";
		assert ID_Emp != null : "fx:id=\"ID_Emp\" was not injected: check your FXML file 'Dash.fxml'.";
		assert Nom != null : "fx:id=\"Nom\" was not injected: check your FXML file 'Dash.fxml'.";
		assert Dare_naissance != null : "fx:id=\"Dare_naissance\" was not injected: check your FXML file 'Dash.fxml'.";
		assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'Dash.fxml'.";
		assert Num_Tel != null : "fx:id=\"Num_Tel\" was not injected: check your FXML file 'Dash.fxml'.";
                observableList = fosuser.afficher();



		ID_Emp.setCellValueFactory(new PropertyValueFactory<Fos_user, String>("id"));
		Nom.setCellValueFactory(new PropertyValueFactory<Fos_user, String>("username"));
		Dare_naissance.setCellValueFactory(new PropertyValueFactory<Fos_user, Date>("datenaiss"));
		adresse.setCellValueFactory(new PropertyValueFactory<Fos_user, String>("email"));
		Num_Tel.setCellValueFactory(new PropertyValueFactory<Fos_user, String>("phone")); 
                Tableau.setItems(observableList);
                Tableau.refresh(); 
                FilteredList<Fos_user> filteredData = new FilteredList<>(observableList , b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}else if (employee.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Fos_user> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Tableau.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Tableau.setItems(sortedData);
               
            nom.setText(n.getUserName());   
         mail.setText(n.getEmail());  
    }    
	  
	
    


    @FXML
    private void SupprimerEmployer(ActionEvent event) {
        
    Fosuserservice sp = new Fosuserservice();
    if (Tableau.getSelectionModel().getSelectedItem()!=null){
        sp.supprimer(Tableau.getSelectionModel().getSelectedItem().getId());
        Tableau.refresh();
          Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
   alert.setHeaderText("!!! Suppression effectuer avec suucces !!!");
   alert.setContentText("succes");
   observableList.clear();
   observableList = fosuser.afficher();
   Tableau.setItems(observableList);
   alert.showAndWait();
   Tableau.refresh();
    }
    }
   

    private void SearchEmployer(ActionEvent event) {
         Fos_user u = new Fos_user();
            u.setUsername(RechercherEmp.getText().toString().toLowerCase());
            if (!fosuser.ControleNOM(u)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez Vos Coordonnees !!!");
            alert.showAndWait();
            }
            	
    }

    @FXML
    private void annonce(ActionEvent event) throws IOException {
     
         FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/backan.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
        
    
    }

    @FXML
    private void Excel(ActionEvent event) throws Exception {
         try {
              Fosuserservice ser = new  Fosuserservice();
            ser.getDefendants("Fos_user"); //nom table              
        }catch (SQLException e) {
                   System.out.println("Failed to get data from database");
                 }
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void loginbut(ActionEvent event)throws IOException {
         n.cleanUserSession(); 
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    @FXML
    private void pdf(ActionEvent event) throws SQLException, DocumentException, BadElementException, IOException {
         
                String ad="C:\\Users\\Fida\\Desktop\\List.pdf";
                Document doc=new Document();
                Alert dialogC = new Alert(Alert.AlertType.INFORMATION);
                dialogC.setTitle(" Confirmation ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Are you sure to export pdf ");
                Optional<ButtonType> answer = dialogC.showAndWait();
               try {
                   PdfWriter.getInstance(doc, new FileOutputStream(ad));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               doc.open();
               try {
                    doc.add(new Paragraph("Members"));
                    doc.add(new Paragraph("Members List"));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    String d=dtf.format(now);
                    doc.add(new Paragraph("Date: "+d));
                    doc.add(new Paragraph(" "));
                        String imageNames =  "C:\\Users\\Fida\\Documents\\NetBeansProjects\\JavaFXApplication5\\src\\ressources\\logo.png" ;
	

			Image image = Image.getInstance(imageNames);
 
		image.setAbsolutePosition(400f, 750f);

			doc.add(image);
                    PdfPTable table = new PdfPTable(5);
                     table.setWidthPercentage(100.0f);
                     table.setSpacingBefore(50);
                    PdfPCell c1=new PdfPCell(new Phrase("id"));
                    
                     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                     c1.setPadding(10.0f);
                     c1.setBackgroundColor(new BaseColor(251, 183, 16));
                    table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Nom"));
                     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                     c1.setPadding(10.0f);
                     c1.setBackgroundColor(new BaseColor(251, 183, 16));
                    table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Date naissance"));
                     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                     c1.setPadding(10.0f);
                     c1.setBackgroundColor(new BaseColor(251, 183, 16));
                    table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Mail"));
                     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                     c1.setPadding(10.0f);
                     c1.setBackgroundColor(new BaseColor(251, 183, 16));
                    table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Phone"));
                     c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                     c1.setPadding(10.0f);
                     c1.setBackgroundColor(new BaseColor(251, 183, 16));
                    table.addCell(c1);
                   
                    // table.setHeaderRows(0);
                    Fosuserservice s = new Fosuserservice();
                    e =s.afficher();
                    for(int i=0;i<e.size();i++)
                    {
                        int idAnnonce=e.get(i).getId();
                        table.addCell(String.valueOf(idAnnonce)); 
                        
                        String idAnnonceRS=e.get(i).getUsername();
                        table.addCell(String.valueOf(idAnnonceRS));
                             
                        String nomAnnonce=e.get(i).getDatenaiss();
                        table.addCell(nomAnnonce);
                        
                        String descriptionAnnonce=e.get(i).getEmail();
                        table.addCell(descriptionAnnonce);
                        
                        String idUser=e.get(i).getPhone();
                        table.addCell(String.valueOf(idUser));        
                    }
                    doc.add(table);
               }catch (DocumentException ex) {
                   Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
    }

    @FXML
    private void Gdepotsss(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherdepots.fxml"));
            Parent root= loader.load();  
            Gdepotsss.getScene().setRoot(root);
    }

    @FXML
    private void livreurs(MouseEvent event) throws IOException {
                                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void Glivreur(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void products(MouseEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
    }

    @FXML
    private void GProduit(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
    }

    @FXML
    private void categoriess(MouseEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
                        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void Gcommande(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();  
            Gcommande.getScene().setRoot(root);
    }

    @FXML
    private void Ghome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
    }

    @FXML
    private void GF(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    @FXML
    private void users(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/dash.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

  
  

        
}
