package vue;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class PointLivraisonVue extends Circle{
	
	private double idPointLivraison;
	private Color originalColor;
	
	public PointLivraisonVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.DARKKHAKI);
		this.idPointLivraison = unId;
		ajouterListner();
		
	}
	public void ajouterListner() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                changerCouleurSelectionnee();
            }
        });
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
            	changerCouleurNonSelectionnee();
            }
        });
	}
	
	public void changerCouleurSelectionnee() {
		this.setStroke(Color.ORANGE);
	}
	
	public void changerCouleurNonSelectionnee() {
		this.setStroke(originalColor);
	}
	
	public void setOriginalColor(Color c) {
		this.originalColor = c;
	}
	
	public double getIdLivraison() {
		return idPointLivraison;
	}
	
}
