package com.efficace.componente.main;

import java.util.ArrayList;
import java.util.List;

import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

public class ProdutoModel extends AbstractModelObject {
	private ObservableList<Produto> produtos = ObservableCollections.observableList(new ArrayList<Produto>());
	private Produto produto = new Produto();

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		List<Produto> oldProdutos = new ArrayList<Produto>(produtos);
		this.produtos = (ObservableList<Produto>)produtos;
		firePropertyChange("produtos", oldProdutos, this.produtos);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		Produto oldProduto = this.produto; 
		this.produto = produto;
		firePropertyChange("produto", oldProduto, this.produto);
	}
	
	public void addProduto(Produto produto) {
		produtos.add(produto);
	}
	
	public void removeProdutro(Produto produto) {
		produtos.remove(produto);
	}
	
}
