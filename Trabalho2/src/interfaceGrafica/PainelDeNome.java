package interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import regrasDeNegocio.Plano;

@SuppressWarnings("serial")
public class PainelDeNome extends PaineisJPanel{
	
	private InterfaceGrafica ig;
	private Plano plano;
	private JTextField jtf;
	private JButton botaoJogar;
	private JButton botaoRelatorioDoJogo;
	
	public PainelDeNome(InterfaceGrafica ig, Plano plano) {
		super(ig);
		this.ig = ig;
		this.plano = plano;
		
		this.setPreferredSize(new Dimension(800, 60));
		this.setLayout(new GridLayout(1,2));
		
		
		JPanel inputNomeJogador = criarJPanel(new Color(220, 230, 245));
		
		inputNomeJogador.add(criarJLabel("Nome Do Jogador:", Color.BLACK));
		
		jtf = criarJTextField(new Dimension(180, 40));
		inputNomeJogador.add(jtf);
		
		botaoJogar = criarJButton(new Color(110, 155, 235), "Jogar", new Dimension(80, 30));
		botaoJogar.addActionListener(new botaoJogar());
		inputNomeJogador.add(botaoJogar);
		/* -------------------------------------------------------------------------- */
		
		JPanel painelBotaoRelatorio = criarJPanel(new Color(220, 230, 245));
		
		botaoRelatorioDoJogo = criarJButton(new Color(110, 155, 235), "Relatorio Do Jogo", new Dimension(180, 40));
		botaoRelatorioDoJogo.addActionListener(new botaoRelatorioDoJogo());
		painelBotaoRelatorio.add(botaoRelatorioDoJogo);
		/* -------------------------------------------------------------------------- */
		
		this.add(inputNomeJogador);
		this.add(painelBotaoRelatorio);
		
		//Checa caso o nome do jogador ja tenha sido escolhido e desabilita edicao do mesmo
		if(plano.getNomeJogador() != null) {
			jtf.setText(plano.getNomeJogador());
			jtf.setEnabled(false);
			botaoJogar.setEnabled(false);
		}
		
	} // >> FIM CONSTRUTOR <<
	
	private JTextField criarJTextField(Dimension dimension) {
		JTextField jtf = new JTextField();
		jtf.setDisabledTextColor(Color.BLACK);
		jtf.setFont(ig.getFontePadrao(25, Font.BOLD)); //new Font("SansSerif", Font.BOLD, 25)
		jtf.setPreferredSize(dimension);
		return jtf;
	}
	
	private class botaoJogar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(jtf.getText().isEmpty()) {
				ig.painelMessageDialog(jtf, "Insira um nome para o jogador!");
			} else {
				plano.setNomeJogador(jtf.getText());
				jtf.setEnabled(false);
				botaoJogar.setEnabled(false);
				ig.setVisibilidadeBotoesComando(true);
			}
		}
		
	}
	
	private class botaoRelatorioDoJogo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ig.relatorioDoJogo();
		}
		
	}
}
