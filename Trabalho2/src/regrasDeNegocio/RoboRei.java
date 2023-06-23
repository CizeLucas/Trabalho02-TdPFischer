package regrasDeNegocio;

import java.awt.Color;

public class RoboRei extends RobosAbstract{

	public RoboRei(String nome, char apelidoNoPlano, Plano plano) {
		super(nome, plano);
		setCorNoPlano(new Color(0, 0, 255));
	}

	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}

}
