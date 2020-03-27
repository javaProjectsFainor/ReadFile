package readfile;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInfo fileInfo = new FileInfo();
		Scanner teclado = new Scanner(System.in);
		InetAddress end = InetAddress.getLocalHost();
		Socket s = new Socket(end, 40000);

		ObjectOutputStream saida = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

		System.out.println("Listar(list) ou Ler(read)");
		fileInfo.setTypeRequest(teclado.nextLine());
		
		if(fileInfo.getTypeRequest().equals("read")) {
			System.out.println("Digite o nome do arquivo a ser lido");
			fileInfo.setFileName(teclado.nextLine());
		}
		
		saida.writeObject(fileInfo);
		FileInfo response = (FileInfo) entrada.readObject();
		
		if(response.getTypeRequest().equals("list")) {
			for (File file : response.getFile()) {
				System.out.println(file.getName());
			}	
		} else {
			System.out.println(response.getReadLine());
		}

		entrada.close();
		saida.close();
		teclado.close();
		s.close();
	}
}
