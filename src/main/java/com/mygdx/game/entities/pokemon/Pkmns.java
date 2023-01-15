package com.mygdx.game.entities.pokemon;

public abstract class Pkmns {
	public static Pokemon get(String name) {
		//nach Nummer ordnen
		switch(name) {
			case "Sandan": return new Sandan(0);
			case "Sandamer": return new Sandan(1);
			case "Dratini": return new Dratini(0);
			case "Dragonir": return new Dratini(1);
			case "Dragoran": return new Dratini(2);
//			case "Shnebedeck": return new Shnebedeck(0);
			case "Nagelotz": return new Nagelotz(0);
			case "Kukmarda": return new Nagelotz(1);
			case "Picochilla": return new Picochilla(0);
			case "Chillabell": return new Picochilla(1);
//			case "Ignivor": return new Ignivor(0);
//			case "": return new ();
		}
		
		return null;
	}
	
//	public static Pokemon get(int number) {
//		//nach Nummer ordnen
//		switch(number) {
//			case "Sandan": return new Sandan(0);
//			case "Sandamer": return new Sandan(1);
//			case "Dratini": return new Dratini(0);
//			case "Dragonir": return new Dratini(1);
//			case "Dragoran": return new Dratini(2);
//			case "Shnebedeck": return new Shnebedeck(0);
//			case "Nagelotz": return new Nagelotz(0);
//			case "Kukmarda": return new Nagelotz(1);
//			case "Picochilla": return new Picochilla(0);
//			case "Chillabell": return new Picochilla(1);
//			case "Ignivor": return new Ignivor(0);
////			case "": return new ();
//		}
//		
//		return null;
//	}
}
