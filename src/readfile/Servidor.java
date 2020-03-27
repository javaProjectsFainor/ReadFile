package readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(40000);
		File directory;
		while(true) {
			System.out.println("Aguardando cliente...");
			Socket cliente = server.accept();
	        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
	        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
	        
	        FileInfo fileInfo = (FileInfo) entrada.readObject();
	                
			if(fileInfo.getTypeRequest().equals("list")) {
		    	directory = new File("C:\\Users\\Megsoft\\eclipse-workspace\\ReadFile\\files");
		    	fileInfo.setFile(directory.listFiles());	
			} else {
		    	directory = new File("C:\\Users\\Megsoft\\eclipse-workspace\\ReadFile\\files");
		    	for (File file : directory.listFiles()) {
		    		if(file.getName().equals(fileInfo.getFileName())) {
				        try(BufferedReader br  = new BufferedReader(new FileReader(file))){
							String strLine;
				            // Read lines from the file, returns null when end of stream 
				            // is reached
				            while((strLine = br.readLine()) != null){
				                 fileInfo.setReadLine(strLine);
				            }   
				        }	
		    		}
				}
			}
	        
	    	saida.writeObject(fileInfo);
	        
	        entrada.close();
	        saida.close();
	        cliente.close();	
		}
    }
	
//	public static void main(String[] args) throws IOException {
//		ServerSocket server = new ServerSocket(40000);
//		while(true) {
//			System.out.println("Aguardando cliente...");
//			Socket cliente = server.accept();
//            Scanner entrada = new Scanner(cliente.getInputStream());
//            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
//            
//            String type = entrada.nextLine();
//            switch(type) {
//            case "list":
//    			try (Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\Megsoft\\eclipse-workspace\\ReadFile\\files"))) {
//
//    				List<String> result = walk.filter(Files::isRegularFile)
//    						.map(x -> x.toString()).collect(Collectors.toList());
//
//    				saida.println(result);
//    			} catch (IOException e) {
//    				e.printStackTrace();
//    			}	
//            }			
//		}
//	}
}
