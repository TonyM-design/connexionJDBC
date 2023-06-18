package cours_exercices.exercices.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cours_exercices.exercices.JDBC.model.Fournisseur;

public class DaoFournisseur {
    public Fournisseur selectById(int numeroFournisseur) {
        Fournisseur fournisseur = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Fournisseur WHERE numeroFournisseur = " + numeroFournisseur;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                fournisseur = new Fournisseur(rs.getInt("id"), rs.getInt("numeroFournisseur"), rs.getString("nom"),
                        rs.getString("mail"), rs.getString("adresse"));
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return fournisseur;
    }

    public List<Fournisseur> selectAll() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Fournisseur";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Fournisseur fournisseur = new Fournisseur(rs.getInt("id"), rs.getInt("numeroFournisseur"),
                        rs.getString("nom"), rs.getString("mail"), rs.getString("adresse"));
                fournisseurs.add(fournisseur);
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return fournisseurs;
    }
    
    public void insert(Fournisseur fournisseur) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "INSERT INTO Fournisseur (id, nom, mail, adresse) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, fournisseur.getId());
            statement.setString(2, fournisseur.getNom());
            statement.setString(3, fournisseur.getMail());
            statement.setString(4, fournisseur.getAdresse());

            statement.executeUpdate();

	        //-----------RECUPERATION DE L'AUTOINCREMENT ----------------
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int numeroFournisseur = generatedKeys.getInt(1);
                fournisseur.setNumeroFournisseur(numeroFournisseur);
            } 
            
  	      //-----------FIN RECUPERATION DE L'AUTOINCREMENT ----------------
           conn.close();
	    } catch (ClassNotFoundException e) {
	        System.err.println("Classe du pilote JDBC non trouvée");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("Erreur SQL lors de la connexion à la base de données");
	        e.printStackTrace();
	    }
	}
    public void update(Fournisseur fournisseur) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "UPDATE Fournisseur SET id = ?, nom = ?, mail = ?, adresse = ? WHERE numeroFournisseur = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, fournisseur.getId());
            statement.setString(2, fournisseur.getNom());
            statement.setString(3, fournisseur.getMail());
            statement.setString(4, fournisseur.getAdresse());
            statement.setInt(5, fournisseur.getNumeroFournisseur());

            statement.executeUpdate();

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
    }

    public void delete(int numeroFournisseur) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "DELETE FROM Fournisseur WHERE numeroFournisseur = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroFournisseur);

            statement.executeUpdate();

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
    }
}