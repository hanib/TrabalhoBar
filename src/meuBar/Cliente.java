package meuBar;

import java.util.Random;

public class Cliente extends Thread{
	
	//Declarando variaveis
	private Pedido pedido;
	private int idCliente;
	private Bar bar;
	boolean fezPedido = false;
	boolean esperouPedido = false;
	boolean recebeuPedido = false;
	boolean consumiuPedido = false;
	private int estado;
	
	//Criando o construtor Cliente
	public Cliente(Pedido pedido, int iD, Bar bar){
		this.bar = bar;
		this.estado = 1;
		this.pedido = pedido;
		this.idCliente = iD;
	}
	
	public int getIdCliente(){
		return idCliente;
	}
	
	public synchronized void fazPedido(){
		synchronized (this.pedido) {
			if(this.fezPedido){  //Testa se ja fez o pedido
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//Faz o pedido e troca o estado da variavel fezPedido
			}else {
				Random Pedido = new Random();
				int pedidoAleatorio = Pedido.nextInt(100);
				this.pedido.setArmazenaPedido(Integer.valueOf(pedidoAleatorio));  //Gera um valor aleatorio para setar como pedido
				this.pedido.setIdCliente(this.idCliente);
				System.out.println("O cliente " +this.getIdCliente()+ " fez o pedido " +pedidoAleatorio);
				this.pedido.notify();
				this.fezPedido = true;
				this.estado++;
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void esperaPedido(){
		
		synchronized (this.pedido) {
			if(this.esperouPedido){   //testa se já esperou o pedido
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//Espera o pedido e troca o estado da variavel esperouPedido
			}else {
					System.out.println("O cliente " +this.getIdCliente()+ " está esperando o pedido ");
					this.esperouPedido = true;
					this.pedido.notify();
					this.estado++;
					try {
						this.pedido.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}	
	}
		
	public void recebePedido(){
		
		synchronized (this.pedido) {
			if(this.recebeuPedido){   //Testa se já recebeu o pedido
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//Recebe o pedido e troca o valor da variavel recebeuPedido
			}else {
				for(int i = 0; i < this.bar.estadoTotal(); i++){ //verifica se ja recebeu tudo
					if (this.idCliente == this.bar.getEstadoAtual(i)){
						System.out.println("O cliente " +this.getIdCliente()+ " recebeu o pedido ");
						this.recebeuPedido = true;
						this.estado++;
					}
				}
				this.pedido.notify();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void consomePedido(){
		
		synchronized (this.pedido) {
			if(this.consumiuPedido){  //Testa se ja consumiu o pedido
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//consome o pedido e troca o estado da variavel consumiuPedido
			}else {
				for(int i = 0; i < this.bar.estadoTotal(); i++){ //verifica se já consumiu tudo
					if (this.idCliente == this.bar.getEstadoAtual(i)){
						System.out.println("O cliente " +this.getIdCliente()+ " consumiu o pedido ");
						this.consumiuPedido = true;
					}
				}
				this.pedido.notify();
				this.bar.setContaThreads(1);
				this.estado++;
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void run(){
			while(estado == 1){  //para garantir que o método rodou todas a vezes
				this.fazPedido();

			}
			while(estado == 2){  //para garantir que o método rodou todas a vezes
				this.esperaPedido();

			}
			while(estado == 3){  //para garantir que o método rodou todas a vezes
				this.recebePedido();
			}
			while(estado == 4){  //para garantir que o método rodou todas a vezes
				this.consomePedido();
			}
	}
}
