package regrasDeNegocio;

public class RoboRainha extends RobosAbstract{
	
	public RoboRainha(String nome, Plano plano, int corR, int corG, int corB) {
		super(nome, plano, corR, corG,  corB);
	}

	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}

}
