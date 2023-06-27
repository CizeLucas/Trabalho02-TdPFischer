package interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class PaineisJPanel extends JPanel{
	
	//Esse metodo armazena todas os metodos para criacao dos JPanels
	
	private InterfaceGrafica ig;
	
	public PaineisJPanel(InterfaceGrafica ig) {
		this.ig = ig;
	} // >> FIM CONSTRUTOR <<
	
	
	protected JPanel criarJPanel() {
		return new JPanel();
	}
	
	protected JPanel criarJPanel(Color c) {
		JPanel p = new JPanel();
		p.setBackground(c);
		return p;
	}
	
	protected ImageIcon criarImageIcon(String diretorio, int tamanhoX, int tamanhoY) {
		Image novaImg = null;
		diretorio = "src\\icones\\" + diretorio;
		try {
			Image imagem = new ImageIcon(diretorio).getImage();
			novaImg = imagem.getScaledInstance(tamanhoX, tamanhoY,  java.awt.Image.SCALE_SMOOTH);
		} catch (Exception e) {
			System.out.println("Erro na criacao do ImageIcon");
			e.printStackTrace();
		}
		return new ImageIcon(novaImg);
	}
	
	protected JLabel criarJLabel(String str, Color corForeground) {
		JLabel jl = new JLabel(str);
		jl.setForeground(corForeground);
		jl.setFont(ig.getFontePadrao(14, Font.PLAIN));
		return jl;
	}
	
	protected JButton criarJButton(Color c, String msg) {
		JButton b = new JButton(msg);
		b.setName(msg);
		b.setBackground(c);
		b.setFont(ig.getFontePadrao(14, Font.BOLD));
		return b;
	}
	
	protected JButton criarJButton(Color c, String msg, Icon img) {
		JButton b = new JButton(msg, img);
		b.setName(msg);
		b.setBackground(c);
		b.setFont(ig.getFontePadrao(14, Font.BOLD));
		return b;
	}
	
	protected JButton criarJButton(String msg) {
		JButton b = new JButton(msg);
		b.setName(msg);
		b.setFont(ig.getFontePadrao(14, Font.BOLD));
		return b;
	}
	
	protected JButton criarJButton(Color c, String msg, Dimension dimension) {
		JButton b = new JButton(msg);
		b.setName(msg);
		b.setPreferredSize(dimension);
		b.setBackground(c);
		b.setFont(ig.getFontePadrao(14, Font.BOLD));
		return b;
	}
	
}
