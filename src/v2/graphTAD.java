package v2;

import java.util.List;

public interface graphTAD <E>{
    
    void addVertex(E data);
    void addArc(int o, int d,int dato) throws LimitException ;
    void deleteArc(int o, int d)throws LimitException ;
    
   
    int g(int x, int y)throws LimitException ;
    List<E> succersors(int v)throws LimitException ;
    E infoVertex(int v)throws LimitException ;
    int orderGraph();
    int inf=999999;

}


