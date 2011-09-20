package com.efficace.components.main;

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

import com.efficace.components.extendedjtable.Tabela;
import com.efficace.components.extendedjtextfield.ExtendedJTextField;

public class Exemplo extends JFrame {
	
	private static final long serialVersionUID = 5204265467726273675L;
	private ExtendedJTextField campoAlfaNumerico;
	private ExtendedJTextField campoValor;
	private ExtendedJTextField campoQuantidade;
	private ExtendedJTextField campoOutro;
	private Tabela tabela;
	private ProdutoModel modelo = new ProdutoModel();
	private Integer[] tamanhoColunas = new Integer[] { 300, 100 };
	private Integer[] alinhamentoColunas = new Integer[] { SwingConstants.LEADING, SwingConstants.TRAILING };
	
	public Exemplo() {
		setResizable(false);
		setTitle("Exemplo Formatação de JTextField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 11, 46, 14);
		getContentPane().add(lblProduto);

		campoAlfaNumerico = new ExtendedJTextField(45, "A");
		campoAlfaNumerico.setBounds(10, 29, 335, 20);
		getContentPane().add(campoAlfaNumerico);
		campoAlfaNumerico.setColumns(10);
		
		campoValor = new ExtendedJTextField(10, "#,##");
		campoValor.setHorizontalAlignment(SwingConstants.TRAILING);
		campoValor.setBounds(355, 29, 101, 20);
		getContentPane().add(campoValor);
		campoValor.setColumns(10);
		
		campoQuantidade = new ExtendedJTextField(10, "#.0");
		campoQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		campoQuantidade.setColumns(10);
		campoQuantidade.setBounds(10, 80, 109, 20);
		getContentPane().add(campoQuantidade);
		
		campoOutro = new ExtendedJTextField(8, "#####-###");
		campoOutro.setColumns(10);
		campoOutro.setBounds(129, 80, 77, 20);
		getContentPane().add(campoOutro);
		

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(355, 11, 101, 14);
		getContentPane().add(lblValor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(modelo.getProduto().getNome().equals(campoAlfaNumerico.getMask()) && 
						modelo.getProduto().getValor().equals(campoValor.getMask()))) {
					modelo.addProduto(modelo.getProduto());
					tabela.repaint();
					incicializaCampos();
				}
			}
		});
		btnOk.setBounds(367, 229, 89, 23);
		getContentPane().add(btnOk);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 60, 77, 14);
		getContentPane().add(lblQuantidade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 446, 106);
		getContentPane().add(scrollPane);
		
		tabela = new Tabela(alinhamentoColunas, tamanhoColunas);
		
		scrollPane.setViewportView(tabela);
		initDataBindings();
		tabela.ajustaTabela();
		incicializaCampos();

	}
	
	public static void main(String[] args){
		Exemplo e = new Exemplo();
		e.setSize(480, 300);
		e.setVisible(true);
	}
	
	protected void initDataBindings() {
		
		BeanProperty<ExtendedJTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty = BeanProperty.create("nome");
		AutoBinding<ExtendedJTextField, String, Produto, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campoAlfaNumerico, jTextFieldBeanProperty, modelo.getProduto(), produtoModelBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ExtendedJTextField, String> campoBeanProperty = BeanProperty.create("text");
		BeanProperty<Produto, String> produtoModelBeanProperty_1 = BeanProperty.create("valor");
		AutoBinding<ExtendedJTextField, String, Produto, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, campoValor, campoBeanProperty, modelo.getProduto(), produtoModelBeanProperty_1);
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
	
	private void incicializaCampos() {
		campoAlfaNumerico.setText("");
		campoValor.setText(campoValor.getMask());
		campoQuantidade.setText(campoQuantidade.getMask());
	}
	
}
