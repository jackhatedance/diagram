package com.dingjianghao.diagram.mxgraph.layout;

import javax.swing.JFrame;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;
import com.mxgraph.view.mxGraph;

public class HelloWorld extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public HelloWorld()
	{
        super("Hello, World!");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {

            String edgeStyle = "";
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ORTHOGONAL);
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_DASHED, "1");
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_ROUNDED, "1");
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_ELBOW, mxConstants.ELBOW_VERTICAL);
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_EXIT_X, "1");
            edgeStyle = mxStyleUtils.setStyle(edgeStyle, mxConstants.STYLE_EXIT_Y, "0.5");

            String objectStyle = mxStyleUtils.setStyle("", mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);

            String dashStyle = mxStyleUtils.setStyle("", mxConstants.STYLE_DASHED, "1");

            String methodStyle = mxStyleUtils.setStyle("", mxConstants.STYLE_SHAPE, "process");

            Object viewLayer = graph.insertVertex(parent, null, "View Layer", 20, 20, 200, 200, objectStyle);
            Object servlet = graph.insertVertex(viewLayer, null, "Servlet", 0, 0, 50, 50);
            Object actionDispatcherServlet =
                            graph.insertVertex(viewLayer, null, "ActionDispatcherServlet", 0, 0, 200, 100, objectStyle);

            Object helloAction1 = graph.insertVertex(actionDispatcherServlet, null, "HelloAction1", 0, 0, 60, 40);
            Object helloAction2 = graph.insertVertex(actionDispatcherServlet, null, "HelloAction2", 0, 0, 70, 40);


            Object serviceLayer = graph.insertVertex(parent, null, "Service Layer", 240, 150, 200, 200, objectStyle);
            Object service1 = graph.insertVertex(serviceLayer, null, "Service1", 0, 0, 50, 50);
            Object userService = graph.insertVertex(serviceLayer, null, "UserService", 0, 0, 200, 100, objectStyle);

            Object userDaoRef = graph.insertVertex(userService, null, "userDao", 0, 0, 50, 10, dashStyle);
            Object loginMethod = graph.insertVertex(userService, null, "login()", 0, 0, 50, 10);


            Object daoLayer = graph.insertVertex(parent, null, "Dao Layer!", 240, 150, 200, 200, objectStyle);

            Object userDao = graph.insertVertex(daoLayer, null, "UserDao", 240, 150, 80, 30);
            Object accountDao = graph.insertVertex(daoLayer, null, "AccountDao", 240, 150, 80, 30);


            graph.insertEdge(parent, null, "1", helloAction1, userService, edgeStyle);
            graph.insertEdge(parent, null, "1", userDaoRef, userDao, edgeStyle);


            mxGraphLayout vlayout = new mxStackLayout(graph, false, 5);
            mxGraphLayout hlayout = new mxStackLayout(graph, true, 5);

            vlayout.execute(graph.getDefaultParent());
            hlayout.execute(viewLayer);
            hlayout.execute(serviceLayer);
            hlayout.execute(daoLayer);
            vlayout.execute(actionDispatcherServlet);
            vlayout.execute(userService);

        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		HelloWorld frame = new HelloWorld();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}



}
