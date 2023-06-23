package persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Leitura {
	private BufferedReader leitor;
	private String diretorio;
	
	public Leitura() {
		diretorio = "src\\dados\\Dados.txt";
	}
	
	//Esse metodo le o arquivo e retorna um array de Strings (cada string se refere a uma linha do arquivo) 
	public String[] lerLinhas() {
		ArrayList<String> arrayStringTemp = new ArrayList<String>();
		
		try {
			
			leitor = new BufferedReader(new FileReader(diretorio));
			while (leitor.ready()) {
				arrayStringTemp.add(leitor.readLine());
			}
			leitor.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("EXCEPTION Classe Leitura: Arquivo De Dados não encontrado");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String arrayString[] = new String[arrayStringTemp.size()];
		
		for(int i=0; i<arrayString.length; i++) {
			arrayString[i] = arrayStringTemp.get(i);
		}
		
		return arrayString;
	}

	
	//Esse metodo serve apenas para a outra classe do pacote persistencia
	 protected String lerConcatString() {
		String str = "";
		
		try {
			
			leitor = new BufferedReader(new FileReader(diretorio));
			while (leitor.ready()) {
				str += (leitor.readLine()+"\n");
			}
			leitor.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
}
