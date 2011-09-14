package com.efficace.componente.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.efficace.componente.campo.Campo;
import com.efficace.componente.tabela.Tabela;

public class Exemplo extends JFrame {
	
	private static final long serialVersionUID = 5204265467726273675L;
	private Campo campoAlfaNumerico;
	private Campo campo;
	private Tabela tabela;
	private ProdutoModel modelo = new ProdutoModel();
	private Integer[] tamanhoColunas = new Integer[] { 300, 100 };
	private Integer[] alinhamentoColunas = new Integer[] { SwingConstants.LEADING, SwingConstants.TRAILING };
	
	public Exemplo() {
		setResizable(false);
		setTitle("Exemplo Formata\u00E7\u00E3o de JTextField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 11, 46, 14);
		getContentPane().add(lblProduto);
		
		campoAlfaNumerico = new Campo();
		campoAlfaNumerico.setQuantidadeDigitos(20);
		campoAlfaNumerico.setMascara("A");
		campoAlfaNumerico.setBounds(10, 29, 303, 20);
		getContentPane().add(campoAlfaNumerico);
		campoAlfaNumerico.setColumns(10);
		
		campo = new Campo();
		campo.setMascara("#,##");
		campo.setBounds(323, 29, 101, 20);
		getContentPane().add(campo);
		campo.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(323, 11, 101, 14);
		getContentPane().add(lblValor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.addProduto(modelo.getProduto());
				tabela.repaint();
			}
		});
		btnOk.setBounds(335, 228, 89, 23);
		getContentPane().add(btnOk);
		
		Campo campo_1 = new Campo();
		campo_1.setMascara("#");
		campo_1.setColumns(10);
		campo_1.setBounds(10, 80, 109, 20);
		getContentPane().add(campo_1);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 60, 77, 14);
		getContentPane().add(lblQuantidade);
		
		Campo campo_2 = new Campo();
		campo_2.setMascara("##.###-###");
		campo_2.setColumns(10);
		campo_2.setBounds(129, 80, 67, 20);
		getContentPane().add(campo_2);
		
		Campo campo_3 = new Campo();
		campo_3.setMascara("##.###.###/####-##");
		campo_3.setColumns(10);
		campo_3.setBounds(206, 80, 119, 20);
		getContentPane().add(campo_3);
		
		Campo campo_4 = new Campo();
		campo_4.setMascara("(##) ####-####");
		campo_4.setColumns(10);
		campo_4.setBounds(335, 80, 89, 20);
		getContentPane().add(campo_4);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(129, 60, 67, 14);
		getContentPane().add(lblCep);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(206, 60, 46, 14);
		getContentPane().add(lblCnpj);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(333, 60, 46, 14);
		getContentPane().add(lblTelefone);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 414, 106);
		getContentPane().add(scrollPane);
		
		tabela = new Tabela(alinhamentoColunas, tamanhoColunas);
		
		scrollPane.setViewportView(tabela);
		initDataBindings();
		tabela.ajustaTabela();
	}
	
	public static void main(String[] args){
		Exemplo e = new Exemplo();
		e.setSize(450, 300);
		e.setVisible(true);
	}
	
	protected void initDataBindings() {
		
		BeanProperty<Campo, String> jTextFieldBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty = BeanProperty.create("nome");
		AutoBinding<Campo, String, Produto, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campoAlfaNumerico, jTextFieldBeanProperty, modelo.getProduto(), produtoModelBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<Campo, String> campoBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty_1 = BeanProperty.create("valor");
		AutoBinding<Campo, String, Produto, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campo, campoBeanProperty, modelo.getProduto(), produtoModelBeanProperty_1);
		autoBinding_1.bind();
		//
		JTableBinding<Produto, List<Produto>, JTable> bindings = 
				   SwingBindings.createJTableBinding(
				      UpdateStrategy.READ_WRITE, 
				      getAll(), tabela);
		
		Property<Produto, String> property = BeanProperty.create("nome");
		bindings.addColumnBinding(property).setColumnName("Nome");
		
		Property<Produto, String> property_2 = BeanProperty.create("valor"); 
		bindings.addColumnBinding(property_2).setColumnName("Valor");

		bindings.bind();		
	}
	
	private List<Produto> getAll() {
		
		Produto p = new Produto();
		p.setNome("ARROZ PATOENSE 30 X 1KG");
		p.setValor("48,00");
		modelo.addProduto(p);
		
		p = new Produto();
		p.setNome("CAFE PATOENSE 20 X 250GR");
		p.setValor("20,00");
		modelo.addProduto(p);
		
		return modelo.getProdutos();
	}
	
}
