package regrasDeNegocio;
import java.awt.Color;

public abstract class RobosAbstract implements RobosInterface{
	
	private Plano plano;
	private String nome;
	private int coordRobo[];
	private int pontuacao;
	private int qtdAlunosColetados;
	private int qtdBugsColetados;
	private Color corNoPlano;
	
	public RobosAbstract(String nome, Plano plano) {
		this.nome = nome;
		this.plano = plano;
		coordRobo = new int[2];	 coordRobo[0]=0; coordRobo[1]=0;
		pontuacao = 0;
		qtdAlunosColetados=0;
		qtdBugsColetados=0;
		
	} //fim construtor
	
	
	public void receberCoord(int[] coord) {
		this.coordRobo[0]=coord[0];
		this.coordRobo[1]=coord[1];
	}
	
	
	protected int[] movimentarRobo(int coordInicial[], int coordFinal[]) {

			if(coordInicial[0] == coordFinal[0] && coordInicial[1] == coordFinal[1]) { 
				return coordInicial;
			} else {
				plano.moverRobo(coordInicial, coordFinal, this);
				this.checarAlunoOuBugNaCelula(coordFinal);
				return coordFinal;
			}
				
		//O if acima checa se houve mudanca nas coordenadas do robo (se houve movimento)
		// Caso haja movimento, o robo eh movido no plano, sua pontuacao eh aferida e retorna sua coordenada atual
		// Caso NAO haja movimento, apenas a coordenada incial eh retornada
	}
	

	protected void finalizarMovimentacao() {
			plano.roboVisitouCelula(coordRobo);
	}
	
	protected void setCorNoPlano(Color corNoPlano) {
		this.corNoPlano = corNoPlano;
	}
	
	private void checarAlunoOuBugNaCelula(int coord[]) {
		if(plano.celulaTemAluno(coord)) {
			this.atualizarPontuacao(10, true);
			qtdAlunosColetados++;
		}
		
		if(plano.celulaTemBug(coord)) {
			this.atualizarPontuacao(15, false);
			qtdBugsColetados++;
		}
	}
	
	
	private void atualizarPontuacao(int qtd, boolean ganhou) {
		if(ganhou)
			pontuacao+=qtd;
		else
			pontuacao-=qtd;
	}
	
	protected void setCoordRobo(int coord[]) {
		this.coordRobo[0] = coord[0];
		this.coordRobo[1] = coord[1];
	}
	
	
	protected int[] getCoordRobo() {
		return coordRobo;
	}

	public int getPontuacao() {
		return pontuacao;
	}
	
	protected String getNome() {
		return nome;
	}
	
	protected int getQtdAlunosColetados() {
		return qtdAlunosColetados;
	}
	
	protected int getQtdBugsColetados() {
		return qtdBugsColetados;
	}

	public Color getCorNoPlano() {
		return corNoPlano;
	}

	
}
