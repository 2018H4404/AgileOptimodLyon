package vue;

import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import modele.DemandeLivraison;
import modele.Intersection;
import modele.IntersectionNormal;
import modele.Plan;
import modele.TourneeManager;
import services.Paire;
import controleur.Controleur;


@SuppressWarnings("restriction")
public class ApplicationDemo extends Application{
	private VueGraphique graph;
	private VueTextuelle texte;
	private MenuBar menuBar;
	private Menu menuFile;
	private Menu menuOperation;
	private Menu menuView;
	private MenuItem itemChargerPlan;
	private MenuItem itemChargerDemandeLivraison;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        Controleur.monPlan = new Plan();
        Controleur.maDemande = new DemandeLivraison();
        graph = new VueGraphique();
        texte = new VueTextuelle();
        graph.setCompagnie(texte);
        texte.setCompagnie(graph);
        
		BorderPane pane = new BorderPane();	
        
		//Ajout de la barre de menu
        AjouterBarreNavigateur(pane,primaryStage);
        
        //Placement de differents vues
        pane.setLeft(texte);
        pane.setCenter(graph);
        BorderPane.setAlignment(graph, Pos.TOP_CENTER);
        BorderPane.setMargin(graph, new Insets(30,0,0,0));
        
        //Creation de la scene
        Scene scene = new Scene(pane, 1150, 900);
        scene.setFill(Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.setTitle("PLD Agile");
        primaryStage.show();

	}
	
	public void AjouterBarreNavigateur(BorderPane pane, Stage primaryStage) {
		
		menuBar = new MenuBar();
		
		//Ajout de l'onglet Fichiers
		menuFile = new Menu("Fichiers");
        itemChargerDemandeLivraison = new MenuItem("Charger DemandeLivraison");
        itemChargerDemandeLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	           FileChooser fileChooser = new FileChooser();
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   Controleur.chargerFichierDemandeLivraison(file);
               }
	         }
	      });
        itemChargerDemandeLivraison.setDisable(true);
        itemChargerPlan = new MenuItem("Charger Plan");
        itemChargerPlan.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   Controleur.chargerFichierPlan(file);
            	   itemChargerDemandeLivraison.setDisable(false);
               }
               
	         }
	      }); 
 
        menuFile.getItems().addAll(itemChargerPlan,itemChargerDemandeLivraison);
 
      //Ajout de l'onglet Opération
        menuOperation = new Menu("Operation");
        
      //Ajout de l'onglet View(Composant prevu pour apres)
        menuView = new Menu("View");
        
        menuBar.getMenus().addAll(menuFile, menuOperation, menuView);
		
		pane.setTop(menuBar);
	}
	
	public static void main(String[] args) {
        launch(args);
		/*PriorityQueue<Paire> queue = new PriorityQueue<Paire>(1,
				new Comparator<Paire>() {  
	                  public int compare(Paire p1, Paire p2) {  
	                	  if (p1.valeurF < p2.valeurF)
	                      {
	                          return -1;
	                      }
	                      if (p1.valeurF  > p2.valeurF)
	                      {
	                          return 1;
	                      }
	                      return 0;
	                    }  
	                  });
		queue.offer(new Paire(23456,3.45556));
		queue.offer(new Paire(21456,3.490556));
		queue.offer(new Paire(54456,3.47856));
		
		Paire p = new Paire(2674456,3.5656);
		queue.offer(p);
		for(Paire element : queue) {
			System.out.println(element.valeurF);
		}
		boolean retour = false;
		for(Paire element : queue) {
			if(element.monInter == 23456) {
				retour = true;
			}
		}
		queue.remove(p);
		queue.add(new Paire(2674456,3.789));
		System.out.println(retour);
		for(Paire element : queue) {
			System.out.println(element.valeurF);
		}*/
        Map<Double, Intersection> gris = new TreeMap<Double, Intersection>(
				new Comparator<Double>() {  
	                  public int compare(Double p1, Double p2) {  
	                	  if (p1 < p2)
	                      {
	                          return -1;
	                      }
	                      if (p1 > p2)
	                      {
	                          return 1;
	                      }
	                      return 0;
	                    }  
	                  });
        gris.put(20.0, new IntersectionNormal(20,9.0,8.0));
        gris.put(29.0, new IntersectionNormal(23,9.0,8.0));
        gris.put(21.0, new IntersectionNormal(25,9.0,8.0));
        gris.put(90.0, new IntersectionNormal(45,9.0,8.0));
        Set<Double> s = gris.keySet();
        for(Double e : s) {
        	System.out.println(e);
        }
    }

}
