package colorblindness;

import java.awt.EventQueue;
import java.awt.Image;

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
		lblColorblindnessProjectGoogle.setBounds(166, 35, 534, 44);
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
		
		
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setBackground(new Color(30,144,255));
		btnProcesar.setForeground(Color.DARK_GRAY);
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// boton Procesar
				colorblindness.daltonize(1,ruta,rutaS,false);
				Toolkit tool = Toolkit.getDefaultToolkit();
				Image imagen=tool.createImage(rutaS);
				label_1.setIcon(new ImageIcon(imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_AREA_AVERAGING)));
			}
		});
		btnProcesar.setBounds(327, 499, 109, 23);
		frame.getContentPane().add(btnProcesar);
		
		JLabel lblImagenOriginal = new JLabel("Imagen Original");
		lblImagenOriginal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImagenOriginal.setForeground(new Color(70, 130, 180));
		lblImagenOriginal.setBounds(100, 121, 127, 18);
		frame.getContentPane().add(lblImagenOriginal);
		
		JLabel lblImagenProcesada = new JLabel("Imagen Procesada");
		lblImagenProcesada.setForeground(new Color(0, 128, 0));
		lblImagenProcesada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImagenProcesada.setBounds(530, 121, 170, 18);
		frame.getContentPane().add(lblImagenProcesada);
		
		
	}
	public String Salida(String ruta)
	{
		String salida = ruta.replace(".jpg", "-exe");
		String salidaF = salida +".jpg";
		return salidaF;
		
	}
}