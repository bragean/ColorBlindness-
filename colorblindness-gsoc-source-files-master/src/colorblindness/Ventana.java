package colorblindness;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Ventana {

	private JFrame frame;
	String ruta="";
	String rutaS="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(Color.LIGHT_GRAY);
		label_1.setBounds(411, 178, 336, 233);
		frame.getContentPane().add(label_1);
		
		JLabel lblColorblindnessProjectGoogle = new JLabel("ColorBlindness Project Google Summer of Code");
		lblColorblindnessProjectGoogle.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblColorblindnessProjectGoogle.setBackground(Color.WHITE);
		lblColorblindnessProjectGoogle.setForeground(new Color(139, 0, 0));
		lblColorblindnessProjectGoogle.setBounds(166, 35, 503, 44);
		frame.getContentPane().add(lblColorblindnessProjectGoogle);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(28, 178, 336, 233);
		frame.getContentPane().add(label);
		Button button = new Button("Abrir");
		button.setBackground(Color.ORANGE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser Buscar = new JFileChooser();
				FileNameExtensionFilter extension = new FileNameExtensionFilter("seleccionar imagen", "jpg","png");
				Buscar.setFileFilter(extension);
				
				if(Buscar.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					Toolkit tool = Toolkit.getDefaultToolkit();
					ruta = Buscar.getSelectedFile().toString();

					
					Image imagen=tool.createImage(ruta);
					rutaS = Salida(ruta);
					
					
					label.setIcon(new ImageIcon(imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_AREA_AVERAGING)));
					System.out.println(ruta);
					System.out.println(rutaS);
					

					
				}
			}
		});
		button.setBounds(148, 435, 70, 22);
		frame.getContentPane().add(button);
		
		
		
	
		
		JLabel lblImagenOriginal = new JLabel("Imagen Original");
		lblImagenOriginal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImagenOriginal.setForeground(new Color(70, 130, 180));
		lblImagenOriginal.setBounds(100, 121, 127, 18);
		frame.getContentPane().add(lblImagenOriginal);
		
		JLabel lblImagenProcesada = new JLabel("Imagen Procesada");
		lblImagenProcesada.setForeground(new Color(0, 128, 0));
		lblImagenProcesada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImagenProcesada.setBounds(530, 121, 139, 18);
		frame.getContentPane().add(lblImagenProcesada);
		
		JRadioButton t3 = new JRadioButton("Tritanopia");
		t3.setBounds(317, 503, 109, 23);
		frame.getContentPane().add(t3);
		
		JRadioButton t1 = new JRadioButton("Protanopia");
		t1.setBounds(317, 451, 109, 23);
		frame.getContentPane().add(t1);
		
		JRadioButton t2 = new JRadioButton("Deuteranopia");
		t2.setBounds(317, 477, 109, 23);
		frame.getContentPane().add(t2);
		
		JLabel lblTipoDeDalt = new JLabel("type of color blindness");
		lblTipoDeDalt.setForeground(new Color(70, 130, 180));
		lblTipoDeDalt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoDeDalt.setBounds(281, 422, 174, 18);
		frame.getContentPane().add(lblTipoDeDalt);
		ButtonGroup group = new ButtonGroup();
		group.add(t1);
		group.add(t2);
		group.add(t3);
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setBackground(new Color(30, 144, 255));
		btnProcesar.setForeground(Color.DARK_GRAY);
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// boton Procesar
				
				if(t2.isSelected())
				{
					label_1.setIcon(new ImageIcon());
					colorblindness.daltonize(2,ruta,rutaS,false);

				}
				else if(t3.isSelected())
				{
					label_1.setIcon(null);
					colorblindness.daltonize(3,ruta,rutaS,false);

				}
				else
				{
					label_1.setIcon(null);
					colorblindness.daltonize(1,ruta,rutaS,true);

				}
				Toolkit tool = Toolkit.getDefaultToolkit();
				Image imagen=tool.createImage(rutaS);
				label_1.setIcon(new ImageIcon(imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_AREA_AVERAGING)));
			}
		});
		
		btnProcesar.setBounds(491, 477, 89, 23);
		frame.getContentPane().add(btnProcesar);
		
		JButton bclean = new JButton("Clean");
		bclean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_1.setIcon(null);
			}
		});
		bclean.setForeground(Color.DARK_GRAY);
		bclean.setBackground(new Color(30, 144, 255));
		bclean.setBounds(138, 491, 89, 23);
		frame.getContentPane().add(bclean);
		
		
	}
	public String Salida(String ruta)
	{
		String salida = ruta.replace(".jpg", "-exe");
		String salidaF = salida +".jpg";
		return salidaF;
		
	}
}
