package cours_exercices.exercices.JDBC.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cours_exercices.exercices.JDBC.model.Article;

public class DaoArticle {
	public static Article selectById(int id) {
	    Article article = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
	        String sql = "SELECT * FROM Article WHERE numeroArticle = " + id;

	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        if (rs.next()) {
	            article = new Article(rs.getInt("id"), rs.getInt("numeroArticle"), rs.getString("nom"),
	                    rs.getBoolean("status"),rs.getString("description"));
	        }

	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.err.println("Classe du pilote JDBC non trouvée");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("Erreur SQL lors de la connexion à la base de données");
	        e.printStackTrace();
	    }
	    return article;
	}
	
	public static List<Article> selectAll() {
	    List<Article> articles = new ArrayList<>();
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
	        String sql = "SELECT * FROM Article";

	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            Article  article = new Article(rs.getInt("id"),rs.getInt("numeroArticle"), rs.getString("nom"),
	                    rs.getBoolean("status"),rs.getString("description"));
	            articles.add(article);
	        }

	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.err.println("Classe du pilote JDBC non trouvée");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("Erreur SQL lors de la connexion à la base de données");
	        e.printStackTrace();
	    }
	    return articles;
	}

	public static void insert(Article article) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
	        String sql = "INSERT INTO Article (id, nom, description, status) VALUES (?, ?, ?, ?)";

	        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, article.getId());
	        statement.setString(2, article.getNom());
	        statement.setString(3, article.getDescription());
	        if (article.getStatus() != null) {
	            statement.setBoolean(4, article.getStatus());
	        } else {
	            statement.setNull(4, Types.BOOLEAN);
	        }
	        statement.executeUpdate();

	        //-----------RECUPERATION DE L'AUTOINCREMENT ----------------
	        ResultSet generatedKeys = statement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int numeroArticle = generatedKeys.getInt(1);
	            article.setNumeroArticle(numeroArticle);
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

	public static void update(Article article) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
	        String sql = "UPDATE Article SET id = ?, nom = ?, description = ?, status = ? WHERE numeroArticle = ?";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, article.getId());
	        statement.setString(2, article.getNom());
	        statement.setString(3, article.getDescription());
	        if (article.getStatus() != null) {
	            statement.setBoolean(4, article.getStatus());
	        } else {
	            statement.setNull(4, Types.BOOLEAN);
	        }
	        statement.setInt(5, article.getNumeroArticle());

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

	public static void delete(int numeroArticle) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exerciebdd", "root", "root");
	        String sql = "DELETE FROM Article WHERE numeroArticle = ?";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, numeroArticle);

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