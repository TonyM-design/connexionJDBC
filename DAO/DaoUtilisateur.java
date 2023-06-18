package cours_exercices.exercices.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cours_exercices.exercices.JDBC.model.Utilisateur;

public class DaoUtilisateur {
    public Utilisateur selectById(int numeroEmploye) {
        Utilisateur utilisateur = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Utilisateur WHERE numeroEmploye = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroEmploye);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id"), rs.getInt("numeroEmploye"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("mail"), rs.getString("login"), rs.getString("motDePasse"));
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return utilisateur;
    }

    public List<Utilisateur> selectAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Utilisateur";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getInt("id"), rs.getInt("numeroEmploye"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("mail"), rs.getString("login"), rs.getString("motDePasse"));
                utilisateurs.add(utilisateur);
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public void insert(Utilisateur utilisateur) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "INSERT INTO Utilisateur (id, nom, prenom, mail, login, motDePasse) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, utilisateur.getId());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.setString(4, utilisateur.getMail());
            statement.setString(5, utilisateur.getLogin());
            statement.setString(6, utilisateur.getPassword());

            statement.executeUpdate();
	        //-----------RECUPERATION DE L'AUTOINCREMENT ----------------

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int numeroEmploye = generatedKeys.getInt(1);
                utilisateur.setNumeroEmploye(numeroEmploye);
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
    
            
            
            public void update(Utilisateur utilisateur) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
                    String sql = "UPDATE Utilisateur SET id = ?, nom = ?, prenom = ?, mail = ?, login = ?, motDePasse = ? WHERE numeroEmploye = ?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, utilisateur.getId());
                    statement.setString(2, utilisateur.getNom());
                    statement.setString(3, utilisateur.getPrenom());
                    statement.setString(4, utilisateur.getMail());
                    statement.setString(5, utilisateur.getLogin());
                    statement.setString(6, utilisateur.getPassword());
                    statement.setInt(7, utilisateur.getNumeroEmploye());

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

            public void delete(int numeroEmploye) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
                    String sql = "DELETE FROM Utilisateur WHERE numeroEmploye = ?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, numeroEmploye);

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
