package regrasDeNegocio;


public class RoboAndador extends RobosAbstract{

	public RoboAndador(String nome, Plano plano, int corR, int corG, int corB) {
		super(nome, plano, corR, corG,  corB);
	}
	
	public void movimentar(int coord[]) {
		this.setCoordRobo(this.movimentarRobo(getCoordRobo(), coord));
	}
	
}
