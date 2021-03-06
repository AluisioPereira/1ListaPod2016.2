/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aluisiopereira
 */
public class Cliente {

    public static void main(String[] args) throws IOException {

        Random r = new Random();

        int escolha = 1;

        while (escolha == 1) {

            int x = r.nextInt(100);
            int y = r.nextInt(100);

            int opEnviar = r.nextInt(2);

            switch (opEnviar) {
                case 0: {
                    //abrindo comunicação com node3
                    Socket node3 = new Socket("localhost", 1088);
                    //enviando o objeto
                    ObjectOutputStream output3 = new ObjectOutputStream(node3.getOutputStream());
                    System.out.println("Op1=sum(x,y) \nContruindo temos: Op1=sum(" + x + ", " + y + ")");
                    Op1 op1 = new Op1(x, y);
                    output3.writeObject(op1);
                    output3.flush();
                    System.out.println("Enviando a node3.");

                    System.out.println("Recebendo...");
                    ObjectInputStream input3 = new ObjectInputStream(node3.getInputStream());

                    input3.readInt();
                    System.out.println("Resultado op1:sum(x,y), é igual a:" + input3);

                    output3.close();
                    node3.close();
                    break;

                }
                case 1: {
                    //abrindo comunicação com node2
                    Socket node2 = new Socket("localhost", 1099);
                    //enviando o objeto
                    ObjectOutputStream output2 = new ObjectOutputStream(node2.getOutputStream());

                    System.out.println("Op2=diff(x,y) \nContruindo temos: Op2=diff(" + x + ", " + y + ")");
                    Op2 op2 = new Op2(x, y);
                    output2.writeObject(op2);
                    output2.flush();
                    System.out.println("Enviando a node2.");

                    System.out.println("Recebendo...");
                    ObjectInputStream input3 = new ObjectInputStream(node2.getInputStream());

                    input3.readInt();
                    System.out.println("Resultado op1:diff(x,y), é igual a:" + input3);

                    output2.close();
                    node2.close();

                    break;
                }
            }

            Scanner s = new Scanner(System.in);
            System.out.println("------------ \n Menu \n 1 para execultar novamente; \n 0 para encerra. ");
            System.out.print("Digite: ");
            escolha = s.nextInt();
            System.out.println("__________________");

        }

    }

}
