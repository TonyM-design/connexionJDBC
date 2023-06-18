package cours_exercices.exercices.JDBC.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import cours_exercices.exercices.JDBC.MenuPrincipale;
import cours_exercices.exercices.JDBC.DAO.DaoArticle;
import cours_exercices.exercices.JDBC.model.Article;

public class Main {
	private static MenuPrincipale menu = new MenuPrincipale();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		createTables();
		populateTables();
		menu.menuPrincipal();
	}

	static void testSelectbyId(int id) throws ClassNotFoundException, SQLException {
		int idArticles = id;
		Article a = new DaoArticle().selectById(idArticles);
		if (a != null)
			System.out.println(a);
		else
			System.out.println("KO");

	}

	static boolean tableIsExist(String tableName) throws SQLException {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");) {
			ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null);
			if (rs.next()) {
				System.out.println("La table '" + tableName + "' existe déjà.");
				return true;
			} else {
				System.out.println("Création de la table " + tableName);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean tableIsEmpty(String tableName) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");) {
			Statement stmt = (Statement) conn.createStatement();
			String countQuery = "SELECT COUNT(*) FROM " + tableName;
			ResultSet rs = stmt.executeQuery(countQuery);
			rs.next();
			int rowCount = rs.getInt(1);
			return rowCount == 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static void createTables() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
				Statement stmt = (Statement) conn.createStatement()) {

			if (!tableIsExist("Utilisateur")) {
				String createTableQuery = "CREATE TABLE Utilisateur (" + "id INT ,"
						+ "numeroEmploye INT PRIMARY KEY AUTO_INCREMENT UNIQUE," + "nom VARCHAR(50),"
						+ "prenom VARCHAR(50)," + "mail VARCHAR(100)," + "login VARCHAR(50)," + "motDePasse VARCHAR(50)"
						+ ")";
				stmt.executeUpdate(createTableQuery);
				System.out.println("La table 'Utilisateur' a été créée avec succès.");
			}
			if (!tableIsExist("Client")) {
				String createTableQuery = "CREATE TABLE Client (" + "id INT ,"
						+ "numeroClient INT PRIMARY KEY AUTO_INCREMENT UNIQUE," + "nom VARCHAR(50),"
						+ "prenom VARCHAR(50)," + "mail VARCHAR(100)," + "adresse VARCHAR(100)" + ")";
				stmt.executeUpdate(createTableQuery);
				System.out.println("La table 'Client' a été créée avec succès.");
			}
			if (!tableIsExist("Fournisseur")) {
				String createTableQuery = "CREATE TABLE Fournisseur (" + "id INT ,"
						+ "numeroFournisseur INT PRIMARY KEY AUTO_INCREMENT UNIQUE," + "nom VARCHAR(50),"
						+ "mail VARCHAR(100)," + "adresse VARCHAR(100)" + ")";
				stmt.executeUpdate(createTableQuery);
				System.out.println("La table 'Fournisseur' a été créée avec succès.");
			}
			if (!tableIsExist("Article")) {
				String createTableQuery = "CREATE TABLE Article (" + "id INT ,"
						+ "numeroArticle INT PRIMARY KEY AUTO_INCREMENT UNIQUE," + "nom VARCHAR(50),"
						+ "status TINYINT(2)," + "description VARCHAR(100)" + ")";
				stmt.executeUpdate(createTableQuery);
				System.out.println("La table 'Article' a été créée avec succès.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void populateTables() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");) {
			Statement stmt = (Statement) conn.createStatement();
			{

				// ajouts données initiale a la table utilisateur
				if (tableIsEmpty("Utilisateur")) {
					for (int i = 1; i <= 4; i++) {
						String insertQuery = "INSERT INTO Utilisateur (id, nom, prenom, mail, login, motDePasse) "
								+ "VALUES (" + i + ", 'Nom" + i + "', 'Prénom" + i + "', 'mail" + i
								+ "@exemple.com', 'login" + i + "', 'motdepasse" + i + "')";
						stmt.executeUpdate(insertQuery);
					}
					System.out.println("initialisation de 4 nouvelles entrées à la table 'Utilisateur'");
				}

				// ajouts données initiale a la table client
				if (tableIsEmpty("Client")) {
					for (int i = 1; i <= 4; i++) {
						String insertQuery = "INSERT INTO Client (id, nom, prenom, mail, adresse) " + "VALUES (" + i
								+ ", 'Nom" + i + "', 'Prénom" + i + "', 'mail" + i + "@exemple.com', 'adresse" + i
								+ "')";
						stmt.executeUpdate(insertQuery);
					}
					System.out.println("initialisation de 4 nouvelles entrées à la table 'Client'");
				}

				// ajouts données initiale a la table utilisateur
				if (tableIsEmpty("Fournisseur")) {
					for (int i = 1; i <= 4; i++) {
						String insertQuery = "INSERT INTO Fournisseur (id, nom, mail, adresse) " + "VALUES (" + i
								+ ", 'Nom" + i + "', 'mail" + i + "@exemple.com', 'Adresse" + i + "')";
						stmt.executeUpdate(insertQuery);
					}
					System.out.println("initialisation de 4 nouvelles entrées à la table 'Fournisseur'.");
				}

				// ajouts données initiale a la table Article
				if (tableIsEmpty("Article")) {
					for (int i = 1; i <= 4; i++) {
						String insertQuery = "INSERT INTO Article (id, nom, status, description) " + "VALUES (" + i
								+ ", 'Nom" + i + "', '" + 0 + "', 'Description" + i + "')";
						stmt.executeUpdate(insertQuery);
					}
					System.out.println("initialisation de 4 nouvelles entrées à la table 'Article'.");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
