package meuBar;

import java.util.ArrayList;

//Classe apenas para receber os valores do usuario e distribuir para cliente e garcom
public class Bar {
	
	private int numeroRodadas;
	private int numeroClientes;
	private int numeroGarcom;
	private int limiteAtendimento;
	private ArrayList<Pedido> pedidosBar;
	private ArrayList<Integer> contaThreads;
	private ArrayList<Integer> estadoAtual;
	
	public Bar(int numeroClientes, int numeroGarcom, int limiteAtendimento, int numeroRodadas){
		this.estadoAtual = new ArrayList<Integer>();
		this.contaThreads = new ArrayList<Integer>();
		this.pedidosBar = new ArrayList<Pedido>();
		this.limiteAtendimento = limiteAtendimento;
		this.numeroClientes = numeroClientes;
		this.numeroGarcom = numeroGarcom;
		this.numeroRodadas = numeroRodadas;
		
		
	}
	
	
	public int getNumeroPedidos() {
		return numeroRodadas;
	}
	
	
	public void setNumeroPedidos(int numeroRodadas) {
		this.numeroRodadas = numeroRodadas;
	}
	
	
	public int getNumeroClientes() {
		return numeroClientes;
	}
	
	
	public void setNumeroClientes(int numeroClientes) {
		this.numeroClientes = numeroClientes;
	}
	
	
	public int getNumeroGracom() {
		return numeroGarcom;
	}
	
	
	public void setNumeroGracom(int numeroGracom) {
		this.numeroGarcom = numeroGracom;
	}


	public int getLimiteAtendimento() {
		return limiteAtendimento;
	}


	public void setLimiteAtendimento(int limiteAtendimento) {
		this.limiteAtendimento = limiteAtendimento;
	}


	public ArrayList<Pedido> getPedidosBar() {
		return pedidosBar;
	}


	public void setPedidosBar(Pedido pedido) {
		this.pedidosBar.add(pedido);
	}

	public int getContaThreads(){
		return this.contaThreads.size();
	}

	public void setContaThreads(int contaThreads) {
		this.contaThreads.add(contaThreads);
	}
	
	//Metodos usados para receber o estado dos pedidos
	public void adicionaEstado(int idCliente) {
		this.estadoAtual.add(idCliente);
	}

	public int estadoTotal() {
		return this.estadoAtual.size();
	}

	public int getEstadoAtual(int posicao) {
		return this.estadoAtual.get(posicao);
	}
}
