package meuBar;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args){
		
		int numeroRodadas;
		int numeroClientes;
		int numeroGarcom;
		int limiteAtendimento;
		
		//Recebendo os valores do usuário
		Scanner entrada = new Scanner (System.in);
		
		System.out.println("Digite o numero de Clientes ");
		numeroClientes = entrada.nextInt();
		
		System.out.println("Digite o numero de Garcom ");
		numeroGarcom = entrada.nextInt();
		
		System.out.println("Digite a capacidade de atendimento de cada Garcom ");
		limiteAtendimento = entrada.nextInt();
		
		System.out.println("Digite o numero de rodadas liberadas ");
		numeroRodadas = entrada.nextInt();
		
		System.out.println("\n");
		
		Bar  meuBar = new Bar(numeroClientes, numeroGarcom, limiteAtendimento, numeroRodadas);		
		//Pedido pedido1 = new Pedido(1);
	
		int idCliente = 0; //variavel para crirar o identificador de cada cliente
		int idGarcom = 0; //variavel para crirar o identificador de cada cliente
		int contGarcom = 0;
		int contCliente = 0;
		
		//Laços para criar os objetos e dar start nas threads garçom e cliente
		for(int i = 0; i < numeroRodadas; i++){
			System.out.println("Rodada "+i);
			System.out.println("________________________________________________________ \n");
			while(numeroGarcom > contGarcom){
				Pedido pedido = new Pedido(idCliente);
				Garcom garcom = new Garcom(idGarcom , pedido, meuBar);
				idGarcom++;
				while((numeroClientes/numeroGarcom) > contCliente){
					Cliente cliente = new Cliente(pedido, idCliente, meuBar);
					cliente.start();
					idCliente++;
					contCliente++;
				}
				contCliente = 0;
				contGarcom++;
				garcom.start();
			}
			//testa se todas as threads terminaram
			while((numeroGarcom+numeroClientes) > meuBar.getContaThreads()){
				Random sleepRandom = new Random();
				try {
					Thread.sleep(sleepRandom.nextInt(500));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
