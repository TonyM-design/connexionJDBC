
/**
 * Exercice JDBC :
 * 
 * Ecrire un programme divisi� en plusieurs fichiers et dossiers.
 * 
 * Un dossier dao pour la partie de code accedant � la BDD.
 * Un dossier modele pour les objets utilis�s sous forme de JavaBean.
 * Un dossier main pour le programme principal g�rant les menus.
 * Vous pouvez creer d'autres dossiers si vous le juger necessaire.
 * 
 * Cr�er des fichiers differents selon les besoin,
 * et importer les dans les autres fichiers si n�cessaire.
 * 
 * Description : 
 * 
 * Cre�r la base de donn�es MySQL si elle n'existe pas. Nomm�e la comme vous le souhait�.
 * Connectez vous � la BDD et cr�er les tables de la BDD si elles n'existent pas.
 * Ces tables sont : Utilisateurs, Clients, Fournisseurs et Articles.
 * 
 * La table Utilisateurs correpond aux utilisateurs du programme dans une entreprise.
 * Les Utilisateurs ont un id, un numero d'employ� unique, un nom, un prenom, un email
 * un login et un mot de passe.
 * 
 * La table Clients cotiendra les client de l'entreprise. Ils ont un id, un num�ro unique,
 * un nom, un pr�nom, un email et une adresse.
 * 
 * La table Fournisseurs correspond aux fournisseurs de l'entreprise.
 * Ils ont un id, un num�ro unique, un nom, un email et une adresse.
 * 
 * La table Article correspond aux articles achet� au fournisseurs et vendu aux clients.
 * Ils ont un id, un numero unique, un champs indiquant si c'est un article acheter ou vendu, un nom, une description.
 * 
 * les op�rations � faire sur les toutes tables de la BDD et � decrire dans les menus sont :
 * la lectures compl�te.
 * la lecture d'un enregistrement selon l'id.
 * l'ecriture.
 * la modification.
 * la suppression.
 * 
 * Pour les menus, faites un premier menu pour choisir la table sur laquelle on veux faire une op�ration.
 * Puis un sous-menu corresondant aux diff�rentes op�rations.
 * 
 * Faites les controles necesaires sur les op�rations pour eviter les incoh�rences et les erreur SQL.
 * 
 * Tous les champs de toutes les tables sont obligatoires.
 * 
 * Faire les javadoc pour toutes les m�thodes et classes.
 */

package cours_exercices.exercices.JDBC;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import cours_exercices.exercices.JDBC.DAO.DaoArticle;
import cours_exercices.exercices.JDBC.DAO.DaoClient;
import cours_exercices.exercices.JDBC.model.Article;

public class MenuPrincipale {
	
	public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Gestion des articles");
            System.out.println("2. Gestion des fournisseurs");
            System.out.println("3. Gestion des clients");
            System.out.println("4. Gestion des utilisateurs");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            int choixPrincipal = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixPrincipal) {
                case 1:
                    menuArticles();
                    break;
                case 2:
                    menuFournisseurs();
                    break;
                case 3:
                    menuClients();
                    break;
                case 4:
                	menuUtilisateurs();
                	break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez choisir à nouveau.");
            }
        }
    }

    public static  void menuArticles() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== MENU ARTICLES ===");
            System.out.println("1. Afficher tous les articles");
            System.out.println("2. Rechercher un article par ID");
            System.out.println("3. Ajouter un nouvel article");
            System.out.println("4. Mettre à jour un article");
            System.out.println("5. Supprimer un article");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            int choixArticles = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixArticles) {
            case 1:
                List<Article> articles = null;
				try {
					articles = DaoArticle.selectAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
                for (Article article : articles) {
                    System.out.println(article);
                }
                break;
                case 2:
                	 System.out.print("Saisissez le numeroArticle de l'article : ");
                     int numeroArticle = scanner.nextInt();
                     scanner.nextLine(); 
						Article article = null;
				try {
					article = DaoArticle.selectById(numeroArticle);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                     if (article != null) {
                         System.out.println(article);
                     } else {
                         System.out.println("Aucun article trouvé avec l'ID spécifié.");
                     }
                     
                     break;
                case 3:
                	System.out.print("Saisissez l'id de l'article : ");
            	    int id = scanner.nextInt();
            	    scanner.nextLine();
                	 System.out.print("Saisissez le nom de l'article : ");
                	    String nomArticle = scanner.nextLine();
                	    System.out.print("Saisissez la description de l'article : ");
                	    String descriptionArticle = scanner.nextLine();
                	    System.out.print("Saisissez le statut de l'article (true/false) : ");
                	    boolean statutArticle = scanner.nextBoolean();
                	    scanner.nextLine(); 
                	    Article nouvelArticle = new Article(id, nomArticle, statutArticle, descriptionArticle);
                	    DaoArticle.insert(nouvelArticle);
                	    System.out.println("Nouvel article ajouté avec succès.");
                	    break;
                case 4:
                    System.out.print("Saisissez le numeroArticle de l'article à mettre à jour : ");
                    int numeroArticleMiseAJour = scanner.nextInt();
                    scanner.nextLine(); 
                    Article articleAMettreAJour = DaoArticle.selectById(numeroArticleMiseAJour);
                    if (articleAMettreAJour != null) {
                        System.out.println("Article actuel : " + articleAMettreAJour);
                        System.out.print("Saisissez le nouveau id de l'article : ");
                	    int nouveauId = scanner.nextInt();
                	    scanner.nextLine(); 
                        System.out.print("Saisissez le nouveau nom de l'article : ");
                        String nouveauNomArticle = scanner.nextLine();
                        System.out.print("Saisissez la nouvelle description de l'article : ");
                        String nouvelleDescriptionArticle = scanner.nextLine();
                        System.out.print("Saisissez le nouveau statut de l'article (true/false) : ");
                        boolean nouveauStatutArticle = scanner.nextBoolean();
                        scanner.nextLine();
                        articleAMettreAJour.setId(nouveauId);
                        articleAMettreAJour.setNom(nouveauNomArticle);
                        articleAMettreAJour.setDescription(nouvelleDescriptionArticle);
                        articleAMettreAJour.setStatus(nouveauStatutArticle);
                        DaoArticle.update(articleAMettreAJour);
                        System.out.println("Article mis à jour avec succès.");
                    } else {
                        System.out.println("Aucun article trouvé avec l'ID spécifié.");
                    }
                    break;
                case 5:
                	  System.out.print("Saisissez l'ID de l'article à supprimer : ");
                      int numeroArticleToDelete = scanner.nextInt();
                      scanner.nextLine(); 
                      DaoArticle.delete(numeroArticleToDelete);
                      System.out.println("Article supprimé avec succès.");
                      break;
                case 0:
                    return; // Retour au menu principal
                default:
                    System.out.println("Option invalide. Veuillez choisir à nouveau.");
            }
        }
    }

    public static void menuFournisseurs() {
    }

    public static void menuClients() {
    }
    public static void menuUtilisateurs() {
    }
	
    
}
