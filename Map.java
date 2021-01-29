import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import distribucion.Administradora;
import distribucion.CentroDistribucion;
import distribucion.Cliente;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Map {

	private JFrame frame;
	private JMapViewer map;
	private JPanel panelMap;
	private JPanel panelControles;
	private Administradora administradora;
	private JPanel panelMap_1;
	private JTextField textField;
	private JSlider slider;
	private JLabel lblNewLabel;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map window = new Map();
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
	public Map() {
		administradora = new Administradora();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 847, 756);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MapaDistribucion");
		
		panelMap_1 = new JPanel();
		panelMap_1.setBounds(241, 130, 500, 500);
		
		frame.getContentPane().add(panelMap_1);
		
		panelControles = new JPanel();
		panelControles.setBounds(457, 11, 700, 700);
		panelControles.setLayout(null);
		
		map = new JMapViewer();
		map.setLocation(26, 5);
		map.setSize(597, 503);
		map.setZoomContolsVisible(true);
		
		//nos posicionamos en..
		

		Coordinate coordinate = new Coordinate(-35.521,-58.719);
		map.setDisplayPosition(coordinate, 12);
		
		//agregamos un marcador
		
		/*	
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(-34.521, -58.708));
		coordenadas.add(new Coordinate(-34.546, -58.719));
		coordenadas.add(new Coordinate(-34.559, -58.721));
		coordenadas.add(new Coordinate(-34.533, -58.78));
		coordenadas.add(new Coordinate(-34.521, -58.70));
		
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		map.addMapPolygon(polygon);
		*/
		panelMap_1.setLayout(null);
		panelMap_1.add(map);
		map.setLayout(null);
		
		JButton btnNewButton = new JButton("Agregar Clientes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String path = getJsonFilePath();
					if(path != null) { 
						try {
							administradora.cargarJsonCliente(path);
							pintarMapa(administradora, map);
							lblNewLabel.setText(administradora.distanciaMedia() + "");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		});
		btnNewButton.setBounds(650, 35, 175, 60);
		panelMap_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar Centros");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = getJsonFilePath();
				if(path != null) { 
					try {
						administradora.cargarJsonCentroDistribucion(path);
						slider.setMaximum(administradora.cantidadDeCentros());
						pintarMapa(administradora, map);
						lblNewLabel.setText(administradora.distanciaMedia() + "");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		btnNewButton_1.setBounds(650, 105, 175, 60);
		panelMap_1.add(btnNewButton_1);
		
		JLabel lblCantidadEstaciones = new JLabel("Cantidad estaciones:");
		lblCantidadEstaciones.setBounds(650, 195, 170, 15);
		panelMap_1.add(lblCantidadEstaciones);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(650, 229, 114, 29);
		panelMap_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblDistanciaMedia = new JLabel("Distancia Media:");
		lblDistanciaMedia.setBounds(650, 340, 175, 15);
		panelMap_1.add(lblDistanciaMedia);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(650, 367, 175, 45);
		panelMap_1.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(36, 526, 789, 159);
		panelMap_1.add(textArea);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int k = slider.getValue();
				textField.setText( k + "");
				administradora.setK(k);
				pintarMapa(administradora, map);
				lblNewLabel.setText(administradora.distanciaMedia() + "");
			}
		});
		slider.setMinorTickSpacing(1);
		slider.setMaximum(0);
		slider.setValue(0);
		slider.setBounds(635, 280, 200, 16);
		panelMap_1.add(slider); 
	}
	
	private String getJsonFilePath() { 
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(frame);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           String direccion = file.getAbsolutePath();
           
           String extension = "";
           if(direccion.lastIndexOf(".")>=0) {
        		extension = direccion.substring(direccion.lastIndexOf("."));
           }
           
           if(".json".equalsIgnoreCase(extension)) {
        	   return direccion;
           }
           
           return null;
        }
        return null;
	}
	
	private void pintarMapa(Administradora administradora, JMapViewer map) { 
		administradora.calcularSolucion();
		textArea.setText(administradora.getExtraInfo());
		
		map.removeAllMapMarkers();
		
		for (Cliente c : administradora.getClientes()) {
			Coordinate coordena = new Coordinate(c.getLatitud(), c.getLongitud());
			MapMarker marker2 = new MapMarkerDot(c.getNombre(), coordena);
			marker2.getStyle().setBackColor(Color.blue);
			marker2.getStyle().setColor(Color.blue);
			map.addMapMarker(marker2);
		}
		
		boolean first = true;
		for (CentroDistribucion c : administradora.getCentrosSeleccionados()) {

			Coordinate coordena = new Coordinate(c.getLatitud(), c.getLongitud());
			MapMarker marker1 = new MapMarkerDot( c.getNombre() , coordena);
			marker1.getStyle().setBackColor(Color.red);
			marker1.getStyle().setColor(Color.red);
			map.addMapMarker(marker1);
			
			if(first) { 
				map.setDisplayPosition(coordena, 6);
				first = false;
			}
		}
	}
	
}
