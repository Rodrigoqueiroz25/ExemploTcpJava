/**
 * 
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class RServer{
    
    public static void main(String[] args){
        
        String cadeiaEscrita = " ";
        String msg = "";
        Scanner ler = new Scanner(System.in);
        byte[] buf = new byte[200]; // buffer de recepção. faz leitura dos n primeiros bytes de acordo com o tamanho n do buffer.
        
        try {
            System.out.print("[ Iniciando Radio Servidor TCP    .........................  ");
            //tamanho da fila é 5. porta 3300
            ServerSocket ss = new ServerSocket(3300, 8, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            //sock representa a conexão. primitiva Accept.
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            
            
            // Envia e recebe mensagens até o usuário colocar exit para terminar.
            while(!cadeiaEscrita.equals("exit")){
                
                System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
                is.read(buf); // Operação bloqueante (aguardando chegada de dados). primitiva Receive, o bloqueante. retorna o numero de bytes lidos.
                System.out.println("[OK] ]");    
                //Exibição de mensagem
                msg = new String(buf); // Mapeando vetor de bytes recebido para String
                System.out.println("Mensagem: "+ msg); 
                
                System.out.print("digite uma mensagem ......................");
                //ler caracteres do teclado
                cadeiaEscrita = ler.nextLine();
                //transforma em bytes
                buf = cadeiaEscrita.getBytes();
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(buf); //primitiva Send
                System.out.println("[OK] ]");
                
            }
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}

 //mvn compile;   java -cp target/classes br.