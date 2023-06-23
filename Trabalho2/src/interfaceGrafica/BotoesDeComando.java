package interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import regrasDeNegocio.*;

@SuppressWarnings("serial")
public class BotoesDeComando extends PaineisJPanel{
	
	private InterfaceGrafica ig;
	//private Plano plano;
	private ArrayList<RobosAbstract> arrayDeRobos;
	private JPanel painelBotoesDosRobos;
	private JPanel painelBotoesDeControle;
	private JPanel painelDeRelatorio;
	private JButton botaoProxRodada;
	private ArrayList<JButton> botoesDeRobos;
	private Color corPadrao;
	
	public BotoesDeComando(InterfaceGrafica ig, Plano plano, ArrayList<RobosAbstract> arrayDeRobos) {
		
		this.ig = ig;
		//this.plano = plano;
		this.arrayDeRobos = arrayDeRobos;
		
		corPadrao = new Color(204, 204, 204); //Cor Cinza
		
		JButton botao;
		
		this.setBackground(corPadrao);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(300, 500));
		botoesDeRobos = new ArrayList<JButton>();
	
		
		//Botoes Dos Robos
		painelBotoesDosRobos = criarJPanel(corPadrao);
		painelBotoesDosRobos.setPreferredSize(new Dimension(300, 120));
		painelBotoesDosRobos.setLayout(new BoxLayout(painelBotoesDosRobos, BoxLayout.PAGE_AXIS));
		
		botao = this.criarBotaoDoRobo(
				painelBotoesDosRobos, "1) Robo Andador", arrayDeRobos.get(0).getCorNoPlano(), new botaoDosRobos());
		painelBotoesDosRobos.add(botao);
		botoesDeRobos.add(botao);
		
		botao = this.criarBotaoDoRobo(
				painelBotoesDosRobos, "2) Robo Rei", arrayDeRobos.get(1).getCorNoPlano(), new botaoDosRobos());
		painelBotoesDosRobos.add(botao);
		botoesDeRobos.add(botao);
		
		botao = this.criarBotaoDoRobo(
				painelBotoesDosRobos, "3) Robo Rainha", arrayDeRobos.get(2).getCorNoPlano(), new botaoDosRobos());
		painelBotoesDosRobos.add(botao);
		botoesDeRobos.add(botao);
		/* -------------------------------------------------------------------------- */
		
		
		//Relatorio Dos Robos
		painelDeRelatorio = new PainelDeRelatorio(ig, arrayDeRobos, plano, corPadrao);
		/* -------------------------------------------------------------------------- */
		
		
		//Botoes De Controle
		painelBotoesDeControle = criarJPanel(corPadrao); //Color.GRAY
		painelBotoesDeControle.setPreferredSize(new Dimension(300, 200));
		painelBotoesDeControle.setLayout(new GridLayout(3,1));
		
		botao = criarJButton(Color.GREEN, "Verificar");
		botao.addActionListener(new botaoVerificar());
		painelBotoesDeControle.add(botao);
		
		botaoProxRodada = criarJButton(Color.GREEN, "Proxima Rodada");
		botaoProxRodada.addActionListener(new botaoProxRogada());
		botaoProxRodada.setEnabled(false);
		painelBotoesDeControle.add(botaoProxRodada);
		
		botao = criarJButton(Color.GREEN, "Sair Do Jogo");
		botao.addActionListener(new botaoSairDoJogo());
		painelBotoesDeControle.add(botao);
		/* -------------------------------------------------------------------------- */
	
		this.add(painelBotoesDosRobos);
		this.add(painelDeRelatorio);
		this.add(painelBotoesDeControle);
		
	} // >> FIM CONSTRUTOR <<
	
	private JButton criarBotaoDoRobo(JPanel jpanel, String nomeBotao, Color cor, ActionListener classeActListener ) {
		JButton botao = criarJButton(cor, nomeBotao);
		botao.addActionListener(classeActListener);
		botao.setFont(ig.getFontePadrao(14, Font.BOLD)); //new Font("SansSerif", Font.BOLD, 14)
		botao.setForeground(Color.WHITE);
		botao.setPreferredSize(new Dimension(150,45));
		botao.setAlignmentX(CENTER_ALIGNMENT);
		//botoesDeRobos.add(botao);
		return botao;
	}
	
	private RobosAbstract selecionarRobo(int indexArrayDeRobos) {
		RobosAbstract roboTemp = null;
		try {
			roboTemp = (RobosAbstract) arrayDeRobos.get(indexArrayDeRobos);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("INDEX INCORRETO para arrayDeRobos");
		}
		return roboTemp;
	}
	
	
	/*	 	INICIO CLASSES PRIVADAS DOS BOTOES		*/
	
	private class botaoVerificar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int qtdBotoesDeRoboAtivos=0;
			for (JButton botao : botoesDeRobos) 
				if(!botao.isEnabled()) 
					qtdBotoesDeRoboAtivos++;
			
			//IFs para checar se todos os botoes de selecao de robos foram pressionados antes de verificar a rodada.
			if(qtdBotoesDeRoboAtivos == botoesDeRobos.size()) {
				((JButton) e.getSource()).setEnabled(false);
				botaoProxRodada.setEnabled(true);
				ig.verificar();
				((PainelDeRelatorio) painelDeRelatorio).atualizarJLabelsDeRelatorio();
			} else {
				ig.painelMessageDialog(((JButton) e.getSource()), "Primeiro selecione as posicoes para todos os robos");
			}
			
		}
	}
	
	private class botaoProxRogada implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ig.proximaRodada();
			
		}
	}
	
	private class botaoSairDoJogo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ig.sairDoJogo();
		}
	}
	
	private class botaoDosRobos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
				b.setBackground(b.getBackground().darker());
				b.setEnabled(false);
				ig.setRoboTemp(selecionarRobo((b.getName().charAt(0)-48)-1));
				//o comando acima le o primeiro char do nome do botao "*1*) Robo XXXX" 
				//		e converte para um numero e seleciona o robo no arraylist.
				ig.setVisibilidadeBotoesCoord(true);

		}
	}//FIM CLASSE PRIVADA botaoDosRobos
	
	
	
} // >> FIM CLASSE <<
