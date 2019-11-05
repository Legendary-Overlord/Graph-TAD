package v2;

import java.util.ArrayList;
import java.util.List;

public class Graph1<E> implements graphTAD<E> {
    List<E> vertex;
    int edges [][];
    
    public Graph1()
    {
        vertex = new ArrayList<>();
        edges = new int[100][100];
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if(i!=j)
                    edges[i][j] = inf;
                //De resto se llena automáticamente de ceros(en este caso la diag ppal)
                    
            }
        }
    }

    @Override
    public void addVertex(E data) {
        vertex.add(data);
                
    }

    @Override
    public void addArc(int o, int d, int data) throws LimitException {
        if(o>= orderGraph() || d>= orderGraph()) {
            throw new LimitException("Te pasaste");
        }
        edges[o][d] = data;
    }

    @Override
    public void deleteArc(int o, int d) throws LimitException {
        if(o>= orderGraph() || d>= orderGraph()) {
            throw new LimitException("Te pasaste");
        }
        edges[o][d] = inf;
    }

    @Override
    public int g(int x, int y) throws LimitException {
        if(x>= orderGraph() || y>= orderGraph())
            throw new LimitException("Te pasaste");
        return edges[x][y];
    }

    @Override
    public List<E> succersors(int v) throws LimitException {
        List<E> res = new ArrayList<>();
        if(v>= orderGraph())
            throw new LimitException("WHY");
        for (int i = 0; i < orderGraph(); i++) {
            if(i!=v && edges[v][i]!= inf)
                res.add(infoVertex(i));
                           
        }
        return res;
    }

    @Override
    public E infoVertex(int v) throws LimitException {
        return vertex.get(v);
    }

    @Override
    public int orderGraph() {
        return vertex.size();
    }
    

}

