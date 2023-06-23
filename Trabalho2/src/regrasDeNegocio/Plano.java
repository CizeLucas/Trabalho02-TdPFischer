package regrasDeNegocio;
import java.util.ArrayList;
import java.util.Random;

public class Plano {
	
	private int tamanho;
	private ArrayList<Celulas> celulas;
	private int qtdAlunos;
	private int qtdAlunosCapturados;
	private int qtdBugsCapturados;
	
	//Variaveis de Jogo
	private String nomeJogador;
	private int qtdRodadas;
	
	public Plano(int tamanho, int qtdAlunos, int qtdBugs) {
		this.tamanho = tamanho;
		
		qtdRodadas=1; //inicializa o contador de rodadas com a primeira rodada
		
		
		celulas = new ArrayList<Celulas>();
		for(int y=0; y<tamanho; y++)
			for(int x=0; x<tamanho; x++)
				celulas.add(new Celulas(x, y));
			
		
		//gera um ArrayList entre 0 e ((tamanho^2)-1) que se refere as posicoes do arraylist de celulas
		Random numAleatorio = new Random();
		ArrayList<Integer> listaNumAleatorios = new ArrayList<>();
		int num;
		for(; listaNumAleatorios.size()<(qtdBugs+qtdAlunos); ) {
			do {
				num = numAleatorio.nextInt(tamanho*tamanho);
			} while(listaNumAleatorios.contains(num));
			listaNumAleatorios.add(num);
		}
		
		//for para ler o ArrayList de numeros aleatorios e atribuir bugs ou alunos as celulas
		for(int i=0; i<listaNumAleatorios.size(); i++) {
			if(i<qtdBugs) {
				celulas.get(listaNumAleatorios.get(i)).addBug();
			} else {
				celulas.get(listaNumAleatorios.get(i)).addAluno();
			}
		}
		
		//O codigo abaixo conta quantas celulas tem alunos 
		//	(para evitar qualquer bug com o sistema de sorteio aleatorio de numeros)
		this.qtdAlunos=0;
		for (Celulas celula : this.celulas) {
			if(celula.temAluno()) {
				this.qtdAlunos++;
			}
		}
		
	} // fim construtor
	
	protected void moverRobo(int coordInicial[], int coordFinal[], RobosAbstract robo) {
		this.encontrarCelula(coordInicial).removerRobo(robo);
		this.encontrarCelula(coordFinal).addRobo(robo);
		//metodo remove o robo da coordInicial e adiciona-o em coordFinal do movimento
	}
	
	
	public void atualizarQtdBugEAlunos() {
		//Conta a quantidade de alunos e bugs ja foram capturados
		qtdAlunosCapturados = 0;
		qtdBugsCapturados = 0;
		for (Celulas celula : celulas)
			if(celula.roboVisitou()) {
				
				if(celula.temAluno()) 
					qtdAlunosCapturados++;
				
				if(celula.temBug()) 
					qtdBugsCapturados++;
			}
	}
	
	
	protected int calcQtdCelulasVazias() {
		//Intera em todas as celulas e checa quantas o robo ainda nao visitou
		int contador=0;
		for (Celulas celula : celulas) 
			if(!celula.roboVisitou()) 
				contador++;

		return contador;
	}
	
	
	protected boolean checarSeJogoAcabou() {
		
		int contador=0;
		for (Celulas celula : this.celulas)
			if(celula.roboVisitou() &&  celula.temAluno())
				contador++;
		
		return (contador>=qtdAlunos);
	}
	
	
	protected void roboVisitouCelula(int coord[]) {
		this.encontrarCelula(coord).setTrueRoboVisitou();
	}
	
	
	public void novaRodada() {
		qtdRodadas++; //incrementa o contador de rodadas
	}
	
	
	protected boolean celulaTemAluno(int coord[]) {
		Celulas celulaTemp = encontrarCelula(coord);
			if(celulaTemp.temAluno() && !celulaTemp.roboVisitou()) 
				return true;
		return false;
	}
	
	protected boolean celulaTemBug(int coord[]) {
		Celulas celulaTemp = encontrarCelula(coord);
			if(celulaTemp.temBug() && !celulaTemp.roboVisitou())
				return true;
		return false;
	}
	
	
	
	
	/*	 >>> GETs & SETTERs e Metodos Privados <<<	 */
	private Celulas encontrarCelula(int[] coord) {
		for (Celulas celula : this.celulas)
			if(celula.getCoords()[0] == coord[0] && celula.getCoords()[1] == coord[1]) 
				return celula;
		return null;
	}
	
	public ArrayList<Celulas> getArrayListCelulas(){
		return this.celulas;
	}
	
	public int getQtdAlunosCapturados() {
		return qtdAlunosCapturados;
	}

	public int getQtdBugsCapturados() {
		return qtdBugsCapturados;
	}
	
	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public int getQtdRodadas() {
		return qtdRodadas;
	}

	public int getTamanho() {
		return this.tamanho;
	}
	
}
