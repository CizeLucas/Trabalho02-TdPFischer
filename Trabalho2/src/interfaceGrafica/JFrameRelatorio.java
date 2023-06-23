package interfaceGrafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class JFrameRelatorio extends JFrame{
	
	private DefaultTableModel modeloJTable;
	private JTable jt;
	
	public JFrameRelatorio(String[] strDeDados, Component parentComponent) {
		
		this.setSize(1100,550);
		this.setResizable(false);
		this.setTitle("Relatorio Das Rodadas");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parentComponent);
		
		Font fontePadrao = new Font("SansSerif", Font.BOLD, 14);
		
		modeloJTable = new DefaultTableModel();
		
	 	modeloJTable.addColumn("Jogador");
	 	modeloJTable.addColumn("Qtd Rodadas");
	 	modeloJTable.addColumn("Jogo Finalizado");
	 	modeloJTable.addColumn("Celulas Vazias");
	 	modeloJTable.addColumn("Alunos resgatados");
	 	modeloJTable.addColumn("Bugs encontrados");
	 	modeloJTable.addColumn("Pontuacao Geral");
	 	modeloJTable.addColumn("Robo Andador");
	 	modeloJTable.addColumn("Robo Rei");
	 	modeloJTable.addColumn("Robo Rainha");
	 	
	 	for (String linhaDeDados : strDeDados) {
			String dados[] = linhaDeDados.split(";");
			modeloJTable.addRow(dados);
		}
	 	
		jt = new JTable(modeloJTable);
		
		jt.setBackground(Color.LIGHT_GRAY);
		jt.setFont(fontePadrao);
		
		this.add(new JScrollPane(jt));
		
		this.setVisible(true);
	}
	
}
