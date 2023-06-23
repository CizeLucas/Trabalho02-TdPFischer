package regrasDeNegocio;

import java.awt.Color;

public class RoboAndador extends RobosAbstract{

	public RoboAndador(String nome, char apelidoNoPlano, Plano plano) {
		super(nome, plano);
		setCorNoPlano(new Color(0, 255, 0));
	}
	
	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}
	
}
