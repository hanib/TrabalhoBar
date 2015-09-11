package meuBar;

import java.util.ArrayList;

//Objeto compartilhado
public class Pedido {
	
	private ArrayList<Integer> armazenaPedido;
	private ArrayList<Integer> idCliente;
	
	//Criando construtor do pedido
	public Pedido(int idPedido){
		this.idCliente = new ArrayList<Integer>();
		this.armazenaPedido = new ArrayList <Integer>();
	}
	
	
	public ArrayList<Integer> getArmazenaPedido() {
		return armazenaPedido;
	}

	public void setArmazenaPedido(int armazenaPedido) {
		this.armazenaPedido.add(armazenaPedido);
	}


	public int getIdPedido(int posicao) {
		return armazenaPedido.get(posicao);
	}


	public void setIdPedido(int armazenaPedido) {
		this.armazenaPedido.add(armazenaPedido);
	}


	public int getIdCliente(int posicao) {
		return idCliente.get(posicao);
	}


	public void setIdCliente(int idCliente) {
		this.idCliente.add(idCliente);
	}

}
