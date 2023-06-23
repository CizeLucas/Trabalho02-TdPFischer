package interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import regrasDeNegocio.*;

@SuppressWarnings("serial")
public class PainelDeRelatorio extends PaineisJPanel{
	
	private InterfaceGrafica ig;
	private Plano plano;
	private ArrayList<RobosAbstract> robos;
	private JLabel jlQtdAlunos;
	private JLabel jlQtdBugs;
	private JLabel jlPontuacaoRobo1;
	private JLabel jlPontuacaoRobo2;
	private JLabel jlPontuacaoRobo3;
	
	
	protected PainelDeRelatorio (InterfaceGrafica ig, ArrayList<RobosAbstract> robos, Plano plano, Color corBackgroung) {
		this.ig = ig;
		this.robos = robos;
		this.plano = plano;
		
		Color cor = corBackgroung.darker();
		this.setBackground(cor); 	// new Color(150, 150, 150)
		this.setBorder(BorderFactory.createSoftBevelBorder(5));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		JPanel painelQtdBugEAlunos = criarJPanel(cor);
		painelQtdBugEAlunos.setPreferredSize(new Dimension(100,45));
		painelQtdBugEAlunos.setBorder(BorderFactory.createSoftBevelBorder(1));
		painelQtdBugEAlunos.add(criarJLabel("Quantidades de bugs e alunos capturados:", Color.WHITE));
		
		jlQtdAlunos = criarJLabel(plano.getQtdAlunosCapturados()+"     ",  "Aluno.png", Color.WHITE, Color.LIGHT_GRAY);
		jlQtdBugs = criarJLabel(plano.getQtdBugsCapturados()+"     ",  "Bug.png", Color.WHITE, Color.LIGHT_GRAY);
		
		painelQtdBugEAlunos.add(jlQtdAlunos);
		painelQtdBugEAlunos.add(jlQtdBugs);
		/* -------------------------------------------------------------------------- */
		
		
		JPanel painelPontosDosRobos = criarJPanel(cor);
		painelPontosDosRobos.setPreferredSize(new Dimension(100,55));
		painelPontosDosRobos.setBorder(BorderFactory.createSoftBevelBorder(1));
		painelPontosDosRobos.add(criarJLabel("   Pontuacoes dos Robos:   ", Color.WHITE));
		
		jlPontuacaoRobo1 = criarJLabel(robos.get(0).getPontuacao()+" Pontos    ",  "RoboAndador.png", Color.WHITE, Color.LIGHT_GRAY);
		
		jlPontuacaoRobo2 = criarJLabel(robos.get(1).getPontuacao()+" Pontos    ",  "Rei.png", Color.WHITE, Color.LIGHT_GRAY);
		
		jlPontuacaoRobo3 = criarJLabel(robos.get(2).getPontuacao()+" Pontos    ",  "Rainha.png", Color.WHITE, Color.LIGHT_GRAY);
		
		painelPontosDosRobos.add(jlPontuacaoRobo1);
		painelPontosDosRobos.add(jlPontuacaoRobo2);
		painelPontosDosRobos.add(jlPontuacaoRobo3);
		/* -------------------------------------------------------------------------- */
		
		this.add(painelQtdBugEAlunos);
		this.add(painelPontosDosRobos);
	}
	
	
	//Metodo cria um JLabel com configuracoes especificas para essa classe
	private JLabel criarJLabel(String msg, String nomeIconeArquivo, Color corForeground, Color corBackground) {
		JLabel jl = new JLabel(msg);
		jl.setFont(ig.getFontePadrao(14, Font.PLAIN));
		jl.setIcon(criarImageIcon(nomeIconeArquivo, 20, 20));
		jl.setIconTextGap(7);
		jl.setForeground(corForeground);
		jl.setBackground(corBackground);
		return jl;
	}
	
	
	protected void atualizarJLabelsDeRelatorio() {
		jlQtdAlunos.setText(plano.getQtdAlunosCapturados()+"     ");
		jlQtdBugs.setText(plano.getQtdBugsCapturados()+"     ");
		
		jlPontuacaoRobo1.setText(robos.get(0).getPontuacao()+" Pontos    ");
		jlPontuacaoRobo2.setText(robos.get(1).getPontuacao()+" Pontos    ");
		jlPontuacaoRobo3.setText(robos.get(2).getPontuacao()+" Pontos    ");
	}
	
}
