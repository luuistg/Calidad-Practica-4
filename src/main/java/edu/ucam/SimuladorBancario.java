package main.java.edu.ucam;


import main.java.edu.ucam.UI.ConsoleInterface;

public class SimuladorBancario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 ConsoleInterface console = new ConsoleInterface();
		        console.iniciar();
			
		}catch (Exception e){
			System.out.print("Error: " + e.getMessage());
		}
		
	}

}
