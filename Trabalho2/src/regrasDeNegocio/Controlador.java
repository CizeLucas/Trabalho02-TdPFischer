package regrasDeNegocio;

import java.util.ArrayList;

import interfaceGrafica.JFrameRelatorio;
import interfaceGrafica.InterfaceGrafica;
import persistencia.*;

public final class Controlador {
	
	private ArrayList<RobosAbstract> arrayDeRobos;
	private Plano plano;
	private InterfaceGrafica intGraf;
	
	private Escrita escreverDados;
	private Leitura lerDados;
	
	public Controlador() {
		//Aqui sao instanciados todos os objetos para inciar o jogo
		
		int tamanhoTabuleiro = 7;
		int qtdDeAlunos = 5;
		int qtdDeBugs = 5;
		
		escreverDados = new Escrita();
		lerDados = new Leitura();
		
		plano = new Plano(tamanhoTabuleiro, qtdDeAlunos, qtdDeBugs);
		
		arrayDeRobos = new ArrayList<RobosAbstract>();
		arrayDeRobos.add(new RoboAndador("Robo Andador", plano, 0, 255, 0));
		arrayDeRobos.add(new RoboRei("Robo Rei", plano, 0, 0, 255));
		arrayDeRobos.add(new RoboRainha("Robo Rainha", plano, 255, 0, 0));
		
		this.instanciarJanelaDeJogo();
	}// >>>  FIM CONSTRUTOR  <<<
	
	public void finalizarJogo() { 
		//Executado quando o jogador finaliza o jogo no botao "Sair do Jogo"
		this.gravarDadosNoArquivo();
	}
	
	public void verificarRodada() { 
		//Executado quando o jogador verifica a rodada no botao "Verificar"
		
		
		for (RobosAbstract robo : arrayDeRobos) {
			//AQUI vai chamar o metodo para finalizar os movimentos e computar pontos de todos os robos
			robo.finalizarMovimentacao();
		}
		
		plano.atualizarQtdBugEAlunos();
		
	}
	
	public void iniciarProximaRodada() { 
		//Executado quando o jogador inicia outra rodada no botao "Proxima Rodada"
		plano.novaRodada();
		
		if(plano.checarSeJogoAcabou()) {
			this.gravarDadosNoArquivo();
			intGraf.mostrarMsgFimDeJogo();
			this.instanciarJanelaDeRelatorio();
		} else {
			this.instanciarJanelaDeJogo();
		}
		
	}
	
	private void instanciarJanelaDeJogo() {
		intGraf = new InterfaceGrafica(this, plano, arrayDeRobos);
	}
	
	public void instanciarJanelaDeRelatorio() {
		new JFrameRelatorio(lerDados.lerLinhas(), intGraf);
	}
	
	private void gravarDadosNoArquivo() {
		//Grava os dados da partida no arquivo (eh chamada quando o jogo acaba ou eh finalizado manualmente)
		String dadosPartida = 
				plano.getNomeJogador()+";"+
				plano.getQtdRodadas()+";"+
				plano.checarSeJogoAcabou()+";"+
				plano.calcQtdCelulasVazias()+";"+
				plano.getQtdAlunosCapturados()+";"+
				plano.getQtdBugsCapturados()+";"+
				this.calcPontuacaoGeralPartida()+";"+
				this.arrayDeRobos.get(0).getPontuacao()+";"+
				this.arrayDeRobos.get(1).getPontuacao()+";"+
				this.arrayDeRobos.get(2).getPontuacao();
		/* ----------------------------------------------------- */
		
		escreverDados.gravarConcatString(dadosPartida);
		
		//Exemplo: Cize;17;true;2;5;4;-10;-10;5;-5
	}
	
	private int calcPontuacaoGeralPartida() {
		//Itera sobre todos os robos e calcula o somatorio da pontuacao deles
		int qtdPontos=0;
		for (RobosAbstract robo : arrayDeRobos) {
			qtdPontos += robo.getPontuacao();
		}
		return qtdPontos;
	}
	
	
}
