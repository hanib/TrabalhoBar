package meuBar;

public class Garcom extends Thread {
	
	//Declarando variaveis
	private Pedido pedido;
	private int idGarcom;
	private Bar bar;
	boolean registrouPedido = false;
	boolean entregouPedido = false;
	
	//Contrudo garcom
	public Garcom(int idGarcom, Pedido pedido, Bar bar) {
		this.idGarcom = idGarcom;
		this.pedido = pedido;
		this.bar = bar;

	}

	public int getIdGarcom() {
		return idGarcom;

	}
	
	public synchronized void recebeMaximoPedidos() {

		synchronized (this.pedido) {
			System.out.println("Garçom " + this.getIdGarcom()+ " recebe pedido: " + this.pedido.getArmazenaPedido());
			this.pedido.notify();
			try {
				this.pedido.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void registraPedido() {

		synchronized (this.pedido){
			if(this.registrouPedido){ //testa se já registrou os pedidos
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//Registra os pedido e muda o estado da variavel registrouPedido
			}else {
				this.bar.setPedidosBar(this.pedido);
				this.registrouPedido = true;
				System.out.println("O garçom " +this.getIdGarcom()+" registou o pedido " +this.pedido.getArmazenaPedido()); 
				this.pedido.notify();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void entregaPedido() {

		synchronized (this.pedido) {
			if(this.entregouPedido){  //testa se entregou os pedidos
				this.pedido.notifyAll();
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//faz a entrega dos pedidos e altera a variável entregouPedido
			}else {
				for(int i = 0; i < this.pedido.getArmazenaPedido().size(); i++){
					int idCliente = this.pedido.getIdCliente(i);
					int pedidoCliente = this.pedido.getIdPedido(i);
					System.out.println("O garçom " +this.getIdGarcom()+" entregou o pedido " +pedidoCliente+ " para o cliente " +idCliente);
					this.bar.adicionaEstado(idCliente);
				}
				this.entregouPedido = true;
				this.pedido.getArmazenaPedido().clear();
				this.pedido.notify();
				this.bar.setContaThreads(1);
				try {
					this.pedido.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void run() {
		
		if((bar.getNumeroClientes() / bar.getNumeroGracom()) < bar.getLimiteAtendimento()){  //verificar quantas vezes tem de rodar o metodo
			for(int i = 0; i < bar.getLimiteAtendimento(); i++){
				this.recebeMaximoPedidos();
			}
		}else { 
			for (int i = 0; i < bar.getLimiteAtendimento(); i++){ 
				this.recebeMaximoPedidos();
			}
		}
		this.registraPedido();
		this.entregaPedido();
	}

	

}
