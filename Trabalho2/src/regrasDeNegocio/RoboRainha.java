package regrasDeNegocio;

import java.awt.Color;

public class RoboRainha extends RobosAbstract{
	
	public RoboRainha(String nome, char apelidoNoPlano, Plano plano) {
		super(nome, plano);
		setCorNoPlano(new Color(255, 0, 0));
	}

	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}

}
