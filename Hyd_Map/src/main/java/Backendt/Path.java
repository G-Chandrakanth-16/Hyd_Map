package Backendt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Backendt.Hyd_Map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/**
 * Servlet implementation class Path
 */
public class Path extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String src;
       String dest;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Path() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		doGet(request, response);
		PrintWriter out=response.getWriter();
		src=request.getParameter("source");
		dest=request.getParameter("dest");
		out.println(src+" "+dest);
		Hyd_Map g=new Hyd_Map();
		g.createMap(g);
		String path="";
		if(!g.vrtces.contains(dest) || !g.vrtces.contains(src))
        {
        	request.setAttribute("key1", 0);
            request.setAttribute("key2", "Kindly input the accurate spellings!!");
         out.println("You entered the places wrong please kindly enter the correct spelling!!");
        }
//		else if(src.equals(dest)) {
//			request.setAttribute("key1", 0);
//            request.setAttribute("key2", src);
//		}
        else{
         int p=g.shortestPath(src,dest,path);
         request.setAttribute("key1", p);
         request.setAttribute("key2",g.paths.get(dest));
         out.println("Shortest path between "+src+" and "+dest+" is: "+p+"kms.");
         out.println("Shortest path is :"+g.paths.get(dest));
        }
		request.getRequestDispatcher("Redirect.jsp").forward(request, response);
	}

}

class Vertex{
        HashMap<String,Integer> nbrs =new HashMap<>();
    }
class Hyd_Map {
    HashMap<String,Vertex> vertices;
    List<String> vrtces;
    HashMap<String,String> paths;
    public Hyd_Map() {
       vertices =new HashMap<>();
    }
    public void addVertex(String name){
        Vertex v=new Vertex();
        vertices.put(name,v);
    }
    public void addEdge(String vname1,String vname2,int dist)
    {
        Vertex vtx1 = vertices.get(vname1); 
		Vertex vtx2 = vertices.get(vname2); 
			if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)) {
				return;
			}

			vtx1.nbrs.put(vname2, dist);
			vtx2.nbrs.put(vname1, dist);
    }
    public void showStations(){
        List<String> stations=new ArrayList<>(vertices.keySet());
        int i=1;
        for(String p:stations)
        {
            System.out.println(i+"."+p);
            i++;
        }
    }
    public void showMap(){
        System.out.println("Hyderabad Map");
        List<String> Stations =new ArrayList<>(vertices.keySet());
        for(String st:Stations)
        {
            System.out.println(st+" ->");
            List<String> dest=new ArrayList<>(vertices.get(st).nbrs.keySet());
            int i=1;
            for(String d:dest)
            System.out.println((i++)+". "+d+" "+vertices.get(st).nbrs.get(d)+" Kms away!");
        }
    }
    public int shortestPath(String src,String dest,String path)
    {
        //vrtces=new ArrayList(vertices.keySet());
        HashMap<String,Integer> lengths=new HashMap<>();
        paths=new HashMap<>();
        for(int i=0;i<vrtces.size();i++)
        paths.put(vrtces.get(i),"");
        for(int i=0;i<vrtces.size();i++)
        {
            lengths.put(vrtces.get(i),Integer.MAX_VALUE);
        }
        lengths.put(src,0);
        paths.put(src,src);
        dijkstra(lengths,path+src);
        int l=lengths.get(dest);
        return l;
        // String lenghts[][]=new String[vertices.size()][2];
        // for(int i=0;i<vertices.size();i++)
        // {
        //     lengths[i][0]=
        // }
    }
     void dijkstra(HashMap<String,Integer> lengths,String path)
    {
        //HashMap<String,Boolean> visited=new HashMap<>();
        List<String> visited=new ArrayList<>();
        for(int i=0;i<lengths.size()-1;i++)
        {
            String min=findMin(lengths,visited);
            //path=path+" "+min;
            visited.add(min);
            for(int j=0;j<lengths.size();j++)
            {
                if(!visited.contains(vrtces.get(j)) && (vertices.get(min)!=null) && vertices.get(min).nbrs.containsKey(vrtces.get(j)))
                {
                    if(lengths.get(vrtces.get(j))>(lengths.get(min)+ vertices.get(min).nbrs.get(vrtces.get(j))))
                    {
                        lengths.put(vrtces.get(j),lengths.get(min)+ vertices.get(min).nbrs.get(vrtces.get(j)));
                        paths.put(vrtces.get(j),paths.get(min)+" "+vrtces.get(j));
                    }
                }
            }
        }
    }
    public String findMin(HashMap<String,Integer> lengths,List<String> visited)
    {
        int x=Integer.MAX_VALUE;
        String min="";
        for (int i = 0; i < lengths.size(); i++) {
            String currentVertex = vrtces.get(i);
            if (vertices.containsKey(currentVertex)) {
                int currentLength = lengths.get(currentVertex);
                if (currentLength < x && !visited.contains(currentVertex)) {
                    x = currentLength;
                    min = currentVertex;
                }
            }
        }
        return min;
    }
    public static void createMap(Hyd_Map g){
        
        g.addVertex("Charminar");
        g.addVertex("BirlaTemple");
        g.addVertex("HussainSagar");
        g.addVertex("NehruZoologicalPark");
        g.addVertex("QutbShahiTombs");
        g.addVertex("SalarJungMuseum");
        g.addVertex("RamojiFilmCity");
        g.addVertex("Golconda");
        g.addVertex("LumbiniPark");
        g.addVertex("BirlaScienceMuseum");
        g.addVertex("Shilparamam");

        g.addEdge("HussainSagar", "Golconda", 15);
        g.addEdge("HussainSagar", "LumbiniPark", 5);
        g.addEdge("BirlaScienceMuseum", "NehruZoologicalPark", 8);
        g.addEdge("BirlaScienceMuseum", "Shilparamam", 10);
        g.addEdge("SalarJungMuseum", "BirlaScienceMuseum", 4);
        g.addEdge("Charminar", "BirlaTemple", 4);
        g.addEdge("Charminar", "HussainSagar", 8);
        g.addEdge("BirlaTemple", "Nehru ZoologicalPark", 5);
        g.addEdge("HussainSagar", "NehruZoologicalPark", 12);
        g.addEdge("HussainSagar", "QutbShahiTombs", 10);
        g.addEdge("NehruZoologicalPark", "QutbShahiTombs", 5);
        g.addEdge("NehruZoologicalPark", "SalarJungMuseum", 9);
        g.addEdge("QutbShahiTombs", "SalarJungMuseum", 16);
        g.addEdge("QutbShahiTombs", "RamojiFilmCity", 15);
        g.addEdge("SalarJungMuseum", "RamojiFilmCity", 25);
            // g.addEdge("E", "Golconda", 2);
             //g.addEdge("F", "Golconda", 6);
        
            // ... (continue adding more edges as needed)
        g.vrtces=new ArrayList<>(g.vertices.keySet());
    }
    public static void main(String[] args) {
        Hyd_Map g=new Hyd_Map();
        createMap(g);
        System.out.println("WELCOME TO HYDERABAD MAPS");
        while(true)
        {
            System.out.println("List of Options");
            System.out.println("1.Show all the staions");
            System.out.println("2.Show the Map");
            System.out.println("3.Find the shortest distace b/t two places:");
            System.out.println("4.Exit");
            System.out.println("Enter you choice:");
            Scanner s=new Scanner(System.in);
            int t=s.nextInt();
            switch (t) {
                case 1:
                    g.showStations();
                    break;
                case 2:
                    g.showMap();
                    break;
                case 3:
                    System.out.println("Enter your source and destination:");
                    String src=s.next();
                    //s.nextLine();
                    String des=s.next();
                    String path="";
                   if(!g.vrtces.contains(des) || !g.vrtces.contains(src))
                   {
                    System.out.println("You entered the places wrong please kindly enter the correct spelling!!");
                   }
                   else{
                    int p=g.shortestPath(src,des,path);
                    System.out.println("Shortest path between "+src+" and "+des+" is: "+p+"kms.");
                    System.out.println("Shortest path is :"+g.paths.get(des));
                   }
                    break;
                case 4:
                   System.exit(0);
                default:
                    System.exit(0);
            }
        }
       // System.out.println("chndu");
    }
}

