package regrasDeNegocio;


public class RoboRei extends RobosAbstract{

	public RoboRei(String nome, Plano plano, int corR, int corG, int corB) {
		super(nome, plano, corR, corG,  corB);
	}

	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}

}
