package com.jaranalyzer.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.jaranalyzer.adapter.Adapter;
import com.jaranalyzer.dependencias.Dependencia;
import com.jaranalyzer.dependencias.DependenciaInterna;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;


@SuppressWarnings("serial")
public class DibujaGrafo extends JFrame {

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem zoomIn, zoomOut;
	private String vertex = "";
	private Dependencia dependencia;
	private Graph grafo;

	public DibujaGrafo() {

	}
	
	public void DibujarGrafo(Dependencia dependencia) {

		this.dependencia = dependencia;

		grafo = Adapter.grafoJung(dependencia);

		menuBar = new JMenuBar();
		zoomOut = new JMenuItem("Out");
		zoomIn = new JMenuItem("In");
		zoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DependenciaInterna di = new DependenciaInterna(dependencia.getJar(),
							dependencia.obtenerDependenciaInterna(vertex));
					if (di.getDependenciasInternas() != null) {
						DibujarGrafo(adapterDI(di));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}			
		});
		
		zoomOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}});
		menu = new JMenu("Zoom");
		menu.add(zoomIn);
		menu.addSeparator();
		menu.add(zoomOut);
		menuBar.add(menu);
		
		
		this.setBounds(300, 100, 600, 500);
		this.setTitle("Grafo");

		Layout<Integer, String> layout = new CircleLayout(grafo);
		layout.setSize(new Dimension(300, 300));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(350, 350));

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		vv.addKeyListener(gm.getModeKeyListener());

		vv.setGraphMouse(gm);

		final PickedState<Integer> pickedState = vv.getPickedVertexState();
		pickedState.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object subject = e.getItem();
				vertex = (String) subject;
			}
		});

		this.setLayout(new BorderLayout());
		this.add(menuBar, BorderLayout.NORTH);
		this.getContentPane().add(vv);
		this.pack();
		this.setVisible(true);

	}

	private Dependencia adapterDI(DependenciaInterna di) {
		Dependencia d = new Dependencia();
		d.setGrafoJars(di.getDependenciasInternas());
		d.setJar(di.getJarInterno());
		return d;
	}
	
}
