package com.efficace.componente.main;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.efficace.componente.campo.Campo;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exemplo extends JFrame {
	public Exemplo() {
		setTitle("Exemplo Formata\u00E7\u00E3o de JTextField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 73, 46, 14);
		getContentPane().add(lblProduto);
		
		campoAlfaNumerico = new Campo();
		campoAlfaNumerico.setQuantidadeDigitos(20);
		campoAlfaNumerico.setMascara("A");
		campoAlfaNumerico.setBounds(10, 91, 303, 20);
		getContentPane().add(campoAlfaNumerico);
		campoAlfaNumerico.setColumns(10);
		
		campo = new Campo();
		campo.setMascara("#,##");
		campo.setBounds(323, 91, 101, 20);
		getContentPane().add(campo);
		campo.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(323, 73, 101, 14);
		getContentPane().add(lblValor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(produto.toString());
			}
		});
		btnOk.setBounds(335, 163, 89, 23);
		getContentPane().add(btnOk);
		
		Campo campo_1 = new Campo();
		campo_1.setMascara("#");
		campo_1.setColumns(10);
		campo_1.setBounds(10, 142, 101, 20);
		getContentPane().add(campo_1);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 122, 101, 14);
		getContentPane().add(lblQuantidade);
		initDataBindings();
	}

	private static final long serialVersionUID = 5204265467726273675L;
	private Campo campoAlfaNumerico;
	private Campo campo;
	private Produto produto = new Produto();
	
	public static void main(String[] args){
		Exemplo e = new Exemplo();
		e.setSize(450, 250);
		e.setVisible(true);
	}
	protected void initDataBindings() {
		BeanProperty<Campo, String> jTextFieldBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty = BeanProperty.create("nome");
		AutoBinding<Campo, String, Produto, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campoAlfaNumerico, jTextFieldBeanProperty, produto, produtoModelBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<Campo, String> campoBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty_1 = BeanProperty.create("valor");
		AutoBinding<Campo, String, Produto, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campo, campoBeanProperty, produto, produtoModelBeanProperty_1);
		autoBinding_1.bind();
	}
}
