package cours_exercices.exercices.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cours_exercices.exercices.JDBC.model.Client;

public class DaoClient {
    public Client selectById(int numeroClient) {
        Client client = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Client WHERE numeroClient = " + numeroClient;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                client = new Client(rs.getInt("id"), rs.getInt("numeroClient"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("mail"), rs.getString("adresse"));
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return client;
    }

    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "SELECT * FROM Client";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getInt("numeroClient"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("mail"), rs.getString("adresse"));
                clients.add(client);
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Classe du pilote JDBC non trouvée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la connexion à la base de données");
            e.printStackTrace();
        }
        return clients;
    }

    public void insert(Client client) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "INSERT INTO Client (id, nom, prenom, mail, adresse) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, client.getId());
            statement.setString(3, client.getNom());
            statement.setString(4, client.getPrenom());
            statement.setString(5, client.getMail());
            statement.setString(6, client.getAdresse());

            statement.executeUpdate();

	        //-----------RECUPERATION DE L'AUTOINCREMENT ----------------
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                client.setNumeroClient(id);
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
    
    public void update(Client client) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "UPDATE Client SET id=?, nom=?, prenom=?, mail=?, adresse=? WHERE numeroClient=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, client.getId());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getPrenom());
            statement.setString(4, client.getMail());
            statement.setString(5, client.getAdresse());
            statement.setInt(6, client.getNumeroClient());

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

    public void delete(Client client) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
            String sql = "DELETE FROM Client WHERE numeroCLient=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, client.getNumeroClient());

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

    