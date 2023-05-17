import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class VerificacionInter {

    public static void main(String[] args) throws IOException {

        VerificacionInter algoritmo = new VerificacionInter();
        BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
        String num_vertices = lr.readLine();
        String cota = lr.readLine();
        String num_calles = lr.readLine();

        int[][] grafo1 = new int[Integer.parseInt(num_vertices)][Integer.parseInt(num_vertices)];
        for (int i = 0; i<Integer.parseInt(num_calles); i++){
            String[] calle = lr.readLine().split(",");
            grafo1[Integer.parseInt(calle[0])][Integer.parseInt(calle[1])] = 1;
            grafo1[Integer.parseInt(calle[1])][Integer.parseInt(calle[0])] = 1;
        }
        String[] vertices_ver_str = lr.readLine().split(",");
        Set<Integer> vertices_ver = new HashSet<Integer>();
        for (String v: vertices_ver_str){
            vertices_ver.add(Integer.parseInt(v));
        }
       
        
    System.out.println(algoritmo.VerificacionIntersecciones(grafo1, Integer.parseInt(cota), vertices_ver));
    }

    public boolean VerificacionIntersecciones(int[][] grafo, int x, Set<Integer> v){

        /*Se verifica que el numero de interesecciones sea menor o igual a la cota ingresada por parámetro
         * y que el el tamaño del conjunto de vertices sea menor o igual al numero de vértices del grafo de netrada para ver si es subgrafo, 
         * sino lo lo cumple se retorna false.
         */
        if (v.size() > x || v.size() > grafo.length){
            return false;
        }
        // Se verifica que todos los elementos del conjunto de vertices del subgrafo pertenezcan al grafo orginal 
        for (int in: v){
            if (in<0 || in>grafo.length-1){
                return false;
            }
        }
        /*
         * Se recorre todos los ejes del grafo verficando que para cada eje (pareja de vértices), al menos uno de los dos vértices que componen cada eje
         * este en el conjunto de intersecciones ingresado por parámetro.
         * Si un eje no cumple esto, es que no hay un vértice que lo cubra por lo que se retorna false. De lo contrario todos los ejes estan cubiertos
         * y se retoran true al final
         */
        for (int i = 0; i<grafo.length;i++){
            for (int j = 0; j<grafo[i].length;j++){
                if((grafo[i][j] == 1 && (!v.contains(i) && !v.contains(j))) && i != j){
                    return false;
                }
            }
        }
        return true;
    }

}
