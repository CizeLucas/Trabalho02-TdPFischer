package interfaceGrafica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import regrasDeNegocio.Celulas;


@SuppressWarnings("serial")
public class BotoesDeCoord extends PaineisJPanel {
	
	private int tamanho;
	private InterfaceGrafica ig;
	//private ArrayList<Celulas> arrayDeCelulas;
	private int coord[];
//	private Color corBotaoCoord1;
//	private Color corBotaoCoord2;
//	private Color corBotaoCoordSelec;
	//private ArrayList<JButton> botoesDeCoordenada;
	
	public BotoesDeCoord(InterfaceGrafica ig, int tamanho, ArrayList<Celulas> arrayDeCelulas) {
		this.ig = ig;
		this.tamanho = tamanho;
		//this.arrayDeCelulas = arrayDeCelulas;
		
		coord = new int[2];

		this.setLayout(new GridLayout(tamanho,tamanho));
		
		JButton botao;
		Color corBotao;
		int contador = 0;
		for(int y=0; y<tamanho; y++) {		//012345
			for(int x=0; x<tamanho; x++) {	//[x, y]
				botao=criarJButton("["+x+", "+y+"]");
				
			//Assinala a cor do botao de acordo com o conteudo da celula ou com as cores alternadas padrao do tabuleiro
				corBotao = arrayDeCelulas.get(((y*tamanho)+x)).imprimirCor();
				if(corBotao==null) {
					if(contador%2==0) 
						corBotao = Color.WHITE;
					 else 
						corBotao = new Color(0, 85, 140);
				}
				botao.setBackground(corBotao);
				
				//Confere se tem robo na celula referente a este botao e desativa o respectivo botao
				if(arrayDeCelulas.get(((y*tamanho)+x)).temRobo()) {
					botao.setEnabled(false);
				}
				
				botao.addActionListener(new botaoDasCoordenadas());
				this.add(botao);
				contador++;
			}
		}
		
	} // >> FIM CONSTRUTOR <<
	
	
	private class botaoDasCoordenadas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String nomeBotao;
			
			b.setBackground(Color.BLACK);
			//012345
			//[x, y]

			nomeBotao = b.getName();
			coord[0] = (nomeBotao.charAt(1)-48);
			coord[1] = (nomeBotao.charAt(4)-48);
			b.setEnabled(false);
			ig.retornarCoordAoRobo(coord);
		}
	}//FIM CLASSE botaoDasCoordenadas
	
	
}
