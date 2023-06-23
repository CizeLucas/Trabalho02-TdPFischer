package regrasDeNegocio;

import java.awt.Color;
import java.util.ArrayList;

public class Celulas {

	private int x;
	private int y;
	//private ArrayList<RobosAbstract> robos;
	private RobosAbstract roboDaCelula;
	private boolean temAluno;
	private boolean temBug;
	private boolean roboVisitou;
	
	public Celulas(int x, int y) {
		//robos = new ArrayList<RobosAbstract>();
		this.x = x;
		this.y = y;
		temAluno = false;
		temBug = false;
		roboVisitou = false;
	}
	
	public Color imprimirCor() {
		if(this.temRobo()) {
			return this.getRobo().getCorNoPlano(); //Celula tem um robo e retorna a cor do Robo
		} else {
			if(roboVisitou) {
				if(temAluno) {
					return new Color(35, 145, 60); //Robo visitou e tem um ALUNO -> new Color(35, 145, 60);
				}else if(temBug) {
					return new Color(110, 10, 10); //Robo visitou e tem um BUG -> new Color(110, 10, 10);
				}else {
					return new Color(125, 125, 65); //Robo visitou -> new Color(125, 65, 125);
				}
			}
				return null; //Caso o robo nao tenha visitado a celula
		}
	}

	public RobosAbstract getRobo() {
		return roboDaCelula; //retorna o robo que esta na celula
	}
	
	public void addRobo(RobosAbstract robo) {
		this.roboDaCelula = robo;
	}
	
	public void removerRobo(RobosAbstract robo) {
		this.roboDaCelula = null;
	}
	
	public boolean temRobo() {
		return this.roboDaCelula!=null;
	}
	
	public void addAluno() {
		temAluno = true;
		temBug = false;
		// fazendo isso para asegurar que não vai ter aluno e bug na mesma celula
	}
	
	public void addBug() {
		temBug = true;
		temAluno = false;
		// fazendo isso para asegurar que não vai ter aluno e bug na mesma celula
	}
	
	public boolean temAluno() {
		return temAluno;
	}

	public boolean temBug() {
		return temBug;
	}

	public boolean roboVisitou() {
		return roboVisitou;
	}
	
	public void setRoboVisitou(boolean estado) {
		roboVisitou = estado;
	}
	
	public void setTrueRoboVisitou() {
		roboVisitou=true;
	}
	
	public int[] getCoords() {
		int[] coords = {x,y};
		return coords;
	}
	
//	public RobosAbstract getRobo() {
//	return robos.get(robos.size()-1); //retorna o ultimo robo que entrou na celula
//}
//
//public void addRobo(RobosAbstract robo) {
//	robos.add(robo);
//}
//
//public void removerRobo(RobosAbstract robo) {
//	robos.remove(robo);
//}
//
//public boolean temRobo() {
//	return (!robos.isEmpty());
//}
	
}
