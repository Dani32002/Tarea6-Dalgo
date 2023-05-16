import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DiasAprox {


    /*
     * n
     * m
     */
    public static void main(String[] args) throws IOException {
        DiasAprox algoritmo = new DiasAprox();
        BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
        String[] preN = lr.readLine().split(" ");
        Set<Integer> n = new HashSet<>();
        for (int i = 0; i < preN.length; i++) n.add(Integer.parseInt(preN[i]));
        String set = lr.readLine();
        Set<Set<Integer>> m = new HashSet<>();
        while (!set.equals("")){
            Set<Integer> set2 = new HashSet<>();
            String[] preSet = set.split(" ");
            for (int i = 0; i < preSet.length; i++) set2.add(Integer.parseInt(preSet[i]));
            m.add(set2);
            set = lr.readLine();
        }
        Set<Set<Integer>> res = algoritmo.algoAprox(m, n);
        for (Set<Integer> setF: res){
            System.out.print("[");
            for (Integer i : setF){
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }



    public Set<Set<Integer>> algoAprox (Set<Set<Integer>> m, Set<Integer> n){
        Set<Set<Integer>> res = new HashSet<>();
        // Itera agarrando el conjunto que este en m con mayor intersección con n.
        // Se borran esos elementos de n
        // Se añade a la respuesta el conjunto elegido
        // iteradamente vacia el n y cubre con los conjuntos en res el conjunto n.
        while (!n.isEmpty()){
            Set<Integer> elegido = hallarMaxInter(m, n);
            n.removeAll(elegido);
            res.add(elegido);
        }
        return res;
    }

    public Set<Integer> hallarMaxInter(Set<Set<Integer>> m, Set<Integer> n){
        int max = 0;
        Set<Integer> maxSet = new HashSet<>();
        // Revisa todos los sets y busca el de mayor intersección con n.
        for (Set<Integer> set: m){
            int suma = 0;
            for (Integer i : set) if (n.contains(i)) suma++;
            if (suma >= max) {
                max = suma;
                maxSet = set;
            } 
        }
        return maxSet;
    }
}
