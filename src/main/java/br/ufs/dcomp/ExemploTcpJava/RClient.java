/**
 * 
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class RClient{
    
    public static void main(String[] args){
        
        String cadeiaEscrita = " ";
        String msg = " ";
        Scanner ler = new Scanner(System.in);
        byte[] buf = new byte[200];
        
        try {
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            
            while(!cadeiaEscrita.equals("exit")){
                
                System.out.print("digite uma mensagem ......................");
                //ler teclado
                cadeiaEscrita = ler.nextLine();
                //transforma em bytes
                buf = cadeiaEscrita.getBytes();
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando mensagem    ..........................  ");
                os.write(buf); //primitiva Send
                System.out.println("[OK] ]");
            
                System.out.print("[ Aguardando mensagem   ..............  ");
                is.read(buf); // Operação bloqueante 
                System.out.println("[OK] ]");
                
                //exibe mensagem
                msg = new String(buf);
                System.out.println("mensagem: " + msg);
            }
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}