package twister;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import twister.behaviors.ColorDetector;
import twister.behaviors.Quit;

/**
 * Classe main du projet.
 * 
 * @author nicolas-carbonnier
 */
public class BumperCar {

	/**
	 * Appelee lors du lancement du programme.
	 * 
	 * @param args Arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		System.out.println("Appuyez pour demarrer");
		Button.waitForAnyPress();
		
		// Initialisation des capteurs
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		colorSensor.setFloodlight(Color.WHITE);
		float[] sample = new float[3];
		
		// Definition des Behavior
		Behavior colorDetector = new ColorDetector(colorSensor, sample, 0);
		Behavior quit = new Quit(colorSensor, 10);
		
		Behavior[] behaviors = {
				colorDetector,
				quit
			};
		
		// Definition de l'Arbitrator
		Arbitrator arby = new Arbitrator(behaviors);
		((Quit) quit).setArby(arby);
		arby.go();
	}

}
