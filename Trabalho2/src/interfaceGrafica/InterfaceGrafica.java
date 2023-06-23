package interfaceGrafica;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

import regrasDeNegocio.*;

@SuppressWarnings("serial")
public class InterfaceGrafica extends JFrame {

	private Controlador controlador;
	private int tamanho;
	private RobosAbstract roboTemp;
	private JPanel painelDeInicio;
	private JPanel painelBotoesDeCoord;
	private JPanel painelBotoesDeComando;
	
	public InterfaceGrafica(Controlador controlador, Plano plano, ArrayList<RobosAbstract> robos) {
		this.controlador = controlador;
		this.tamanho = plano.getTamanho();
		
		this.setSize(800, 650);
		this.setResizable(false);
		this.setTitle("O Regaste dos Alunos na Ilha de Java V2.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		
		painelDeInicio = new PainelDeNome(this, plano);
		this.add(painelDeInicio, BorderLayout.PAGE_START);
		
		
		painelBotoesDeCoord = new BotoesDeCoord(this, tamanho, plano.getArrayListCelulas());
		this.add(painelBotoesDeCoord, BorderLayout.WEST);
		this.setVisibilidadeBotoesCoord(false);
		
	
		painelBotoesDeComando = new BotoesDeComando(this, plano, robos);
		this.add(painelBotoesDeComando, BorderLayout.EAST);
		
		//Confere se o nome do jogador ja foi informado e muda a visibilidade do PainelBotoesDeComando
		if(plano.getNomeJogador() != null)
			this.setVisibilidadeBotoesComando(true);
		else
			this.setVisibilidadeBotoesComando(false);
		
		this.setVisible(true);
	} // >> FIM CONSTRUTOR <<
	
	
	protected void relatorioDoJogo() { //Executada ao pressionar o botao RELATORIO DO JOGO
		controlador.instanciarJanelaDeRelatorio();
	}
	
	protected void sairDoJogo() { //Executada ao pressionar o botao SAIR DO JOGO
		controlador.finalizarJogo();
		System.exit(0);
	}
	
	protected void verificar() { //Executada ao pressionar o botao VERIFICAR
		controlador.verificarRodada();
	}
	
	protected void proximaRodada() { //Executada ao pressionar o botao PROXIMA RODADA
		controlador.iniciarProximaRodada();
		this.dispose();
	}
	
	
	protected void retornarCoordAoRobo(int coord[]) {
		
		if(roboTemp != null && coord[0]!=-1 && coord[1]!=-1) {
			roboTemp.movimentar(coord);
			roboTemp=null;
			coord[0]=-1;
			coord[1]=-1;
			this.setVisibilidadeBotoesCoord(false);
		}
	}	
	
	
	protected void setVisibilidadeBotoesComando(boolean estado) {
		painelBotoesDeComando.setVisible(estado);
	}
	
	protected void setVisibilidadeBotoesCoord(boolean estado) {
		painelBotoesDeCoord.setVisible(estado);
	}
	
	
	protected Font getFontePadrao(int tamanhoFonte, int tipoDeLetra) {	
		return new Font("SansSerif", tipoDeLetra, tamanhoFonte);
	}
	
	
	protected void painelMessageDialog(Component parentComponent, String msg) {
		JOptionPane.showMessageDialog(parentComponent, msg, "Aviso do Jogo", 1);
	}
	
	public void mostrarMsgFimDeJogo() {
		this.painelMessageDialog(this, "Parabens! A Partida Acabou!\nVoce resgatou todos os alunos da Ilha De Java.");
	}
	
	protected void setRoboTemp(RobosAbstract roboTemp) {
		this.roboTemp = roboTemp;
	}
	
}
