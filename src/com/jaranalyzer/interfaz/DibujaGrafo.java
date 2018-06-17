package com.jaranalyzer.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.Pane;

public class DibujaGrafo extends JFrame implements ActionListener{
	
	private static DibujaGrafo instance;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem zoomIn, zoomOut;
	private String vertex = "";
	
	private DibujaGrafo() {};
	
	public static DibujaGrafo getInstance() {
		if(instance == null) {
			instance = new DibujaGrafo();
		}
		return instance;
	}
	
	public void DibujarGrafo(Graph g) {
		
		menuBar = new JMenuBar();
		menu = new JMenu("Zoom");
		zoomIn = new JMenuItem("In");
		zoomOut = new JMenuItem("Out");
		menu.add(zoomIn);
		menu.addSeparator();
		menu.add(zoomOut);
		
		menuBar.add(menu);

		this.setBounds(300,100,600,500);
		this.setTitle("Grafo");
		zoomIn.addActionListener(this);
		zoomOut.addActionListener(this);
		
		Layout<Integer, String> layout = new CircleLayout(g);
		layout.setSize(new Dimension(300,300)); 
		VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
		vv.setPreferredSize(new Dimension(350,350));
		
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == zoomIn) {
			System.out.println(vertex);
		}
		else if(e.getSource() == zoomOut) {
			System.out.println(vertex);
		}
	}
}
