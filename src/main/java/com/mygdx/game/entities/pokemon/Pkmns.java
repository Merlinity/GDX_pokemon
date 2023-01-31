package com.mygdx.game.entities.pokemon;

public abstract class Pkmns {
	public static Pokemon get(String name) {
		//nach Nummer ordnen
		switch(name) {
			case "Sandan": return new Pokemon(27);
			case "Sandamer": return new Pokemon(28);
			case "Dratini": return new Pokemon(147);
			case "Dragonir": return new Pokemon(148);
			case "Dragoran": return new Pokemon(149);
			case "Nagelotz": return new Pokemon(504);
			case "Kukmarda": return new Pokemon(505);
			case "Picochilla": return new Pokemon(572);
			case "Chillabell": return new Pokemon(573);
//			case "Ignivor": return new Ignivor(0);
//			case "": return new ();
		}
		
		return null;
	}

	public static String getName(int id) {
		String name;
		switch (id) {
			case 27 -> name = "Sandan";
			case 28 -> name = "Sandamer";
			case 147 -> name = "Dratini";
			case 148 -> name = "Dragonir";
			case 149 -> name = "Dragoran";
			case 504 -> name = "Nagelotz";
			case 505 -> name = "Kukmarda";
			case 572 -> name = "Picochilla";
			case 573 -> name = "Chillabell";
			default -> name = "???";
		}
		return name;
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
