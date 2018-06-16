package com.jaranalyzer.interfaz;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

public class DibujaGrafo {
	
	public static void DibujarGrafo(Graph g) {
		
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
					String vertex = (String) subject;
					System.out.println(vertex);
				}		
			});
			
		 JFrame frame = new JFrame();	
		 frame.getContentPane().add(vv);
		 frame.pack();
		 frame.setVisible(true); 

    }
}
