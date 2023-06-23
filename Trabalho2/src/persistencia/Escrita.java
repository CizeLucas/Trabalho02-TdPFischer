package persistencia;

import java.io.FileWriter;

public class Escrita {
	private FileWriter escritor;
	private String diretorio;
	
	public Escrita() {
		diretorio = "src\\dados\\Dados.txt";
	}
	
	//Esse metodo concatena os dados a serem gravados com os dados ja existentes no arquivos 
	//		e grava o resultado final para nao haver perda de dados.
	public boolean gravarConcatString(String str) {
		String strTemp = "";
		
		try {
			
			Leitura ler = new Leitura();
			strTemp = (str+"\n")+ler.lerConcatString();
			escritor = new FileWriter(diretorio);	
			escritor.write(strTemp);
			escritor.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
