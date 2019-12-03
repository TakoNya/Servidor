package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class MainServidor {
    private final static int PORT = 5000;

    
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket= new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado");
            System.out.println("Servidor> En espera de cliente...");
            
            Socket clientSocket;
            while(true){
                clientSocket = serverSocket.accept();
                
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                
                String request = input.readLine();
                System.out.println("Cliente> peticion ["+ request + "]");
                
                String strOutput = process(request);
                
                System.out.println("Servidor> Resultado de peticion");
                System.out.println("Servidor> \"" + strOutput + "\"");
                
                output.flush();
                output.println(strOutput);
                
                clientSocket.close();
            }
        }catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    /**
     * procesa peticion del cliente y retorna resultado
     * @param request peticion del cliente
     * @return String
     */
    public static String process(String request){
        String result= "";
        String[] phrases= {
            "La tecnologia se alimenta a si misma. La tecnologia hace posible mas tecnologia.- Alvin Toffler.",
            "La tecnologia es solo una herramienta. El profesor es lo mas importante en el trabajo y motivacion.- Bill Gates",
            "La maquina tecnologicamente mas eficiente que el hombre ha inventado es el libro.-Northrop Frye.",
            "Ya no hacen mas bugs como bunnh.-Olav Mjelde",
            "Un lenguaje para programar es de bajo nivel cuando debes prestar atencion a lo irrelevante.-Alan J.Perlis",
            "Hablar es barato.Enseñame el codigo.-Linus Torvalds",
            "No me importa si funciona en su maquina! No me envian su maquina.-Vidiu Planton",
            "Programa como si la persona que mantendra tu codigo fuera un psicopata violento que sabe donde vives.-Martin Golding"};
        ArrayList<String> phrasesList = new ArrayList<>();
        Collections.addAll(phrasesList,phrases);
        String[] books = {
            "Divina Comedia - Dande Aligheri",
            "Don Quijote de la Mancha - Miguel de Cervantes",
            "Cien años de soledad - Gabriel Garcia Marquez",
            "Moby Dick - Herman Melville",
            "Ana Karerina - Lev Tolstoi",
            "Eneida - Virgilio",
            "Otelo - William Shakespeare",
            "El viejo y el mar - Ernest Hemingway",
            "Orgullo y prejuicio - Jane Austen"};
        ArrayList<String> booksList = new ArrayList<>();
        Collections.addAll(booksList, books);
        if(request != null)
            switch(request.toLowerCase()){
            case "frase":
                Collections.shuffle(phrasesList);
                result = phrasesList.get(0);
                break;
            case "libro":
                Collections.shuffle(booksList);
                result = booksList.get(0);
                break;
            default:
                result = "La peticion no se puede resolver.";
                break;           
        
        }
        return result;
    }
    
}
