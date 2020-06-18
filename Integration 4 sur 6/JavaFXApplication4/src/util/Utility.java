
package util;


public interface Utility {
	public static String tableNameUser = "fos_user";
	public static String ID = "ID";
	public static String NOM = "USERNAME";
	public static String PRENOM = "PRENOM";
	public static String EMAIL = "EMAIL";
	public static String ADRESSE = "ADRESSE";
	public static String DATE_NAISSANCE = "DATE_NAISSANCE";
	
	public static String ROLE = "ROLES";
	public static String MISSION = "MISSION";
	public static String ROLE_EMPLOYER = "EMPLOYE";
	public static String listattribut = "USERNAME, PRENOM, EMAIL, ADRESSE, DATE_NAISSANCE, CIN, ROLES, MISSION, telephone,password, disponible";
	public static String listattributupdate = "USERNAME = ?, PRENOM = ?, EMAIL = ?, ADRESSE = ?, DATE_NAISSANCE = ?, CIN= ?, ROLES = ?, MISSION = ?, telephone = ? ";
	

}
