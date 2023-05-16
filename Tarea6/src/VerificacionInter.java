import java.util.HashSet;
import java.util.Set;

public class VerificacionInter {

    public static void main(String[] args) {

    int[][] grafo = new int[5][5];

    int[] l0 = new int[5];
    l0[0] = 1;
    l0[1] = 0;
    l0[2] = 0;
    l0[3] = 1;
    l0[4] = 1;

    int[] l1 = new int[5];
    l1[0] = 0;
    l1[1] = 1;
    l1[2] = 1;
    l1[3] = 1;
    l1[4] = 0;

    int[] l2 = new int[5];
    l2[0] = 0;
    l2[1] = 1;
    l2[2] = 1;
    l2[3] = 0;
    l2[4] = 0;

    int[] l3 = new int[5];
    l3[0] = 1;
    l3[1] = 1;
    l3[2] = 0;
    l3[3] = 1;
    l3[4] = 0;

    int[] l4 = new int[5];
    l4[0] = 1;
    l4[1] = 0;
    l4[2] = 0;
    l4[3] = 0;
    l4[4] = 1;

    grafo[0] = l0;
    grafo[1] = l1;
    grafo[2] = l2;
    grafo[3] = l3;
    grafo[4] = l4;


    int x = 10;

    Set<Integer> v = new HashSet<Integer>();

    v.add(0);
    v.add(1);
    v.add(2);
    v.add(3);
    v.add(5);
    


    boolean resp = VerificacionIntersecciones(grafo, x, v);

    System.out.println(resp);
    }

    public static boolean VerificacionIntersecciones(int[][] grafo, int x, Set<Integer> v){

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
