import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Dias {

    /*
     * Entrada:
     * n
     * m
     * 
     * p
     * 
     * z
     */

    public static void main(String[] args) throws IOException {
        Dias algoritmo = new Dias();
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
        String set3 = lr.readLine();
        Set<Set<Integer>> P = new HashSet<>();
        while (!set3.equals("")){
            Set<Integer> set2 = new HashSet<>();
            String[] preSet = set3.split(" ");
            for (int i = 0; i < preSet.length; i++) set2.add(Integer.parseInt(preSet[i]));
            P.add(set2);
            set3 = lr.readLine();
        }
        int z = Integer.parseInt(lr.readLine());
        System.out.println(algoritmo.verificacionDias(m, n, z, P));
        
    }


    public boolean verificacionDias(Set<Set<Integer>> m, Set<Integer> n, int z, Set<Set<Integer>> P) {
        Set<Integer> U = new HashSet<>();
        // Revisa que los dias elegidos sean de los proporcionados al inicio. 
        // Adicionalmente, crea una union U con todos los elementos proporcionados en la posible respuesta
        for (Set<Integer> dia: P){
           if (!m.contains(dia)) return false;
           U.addAll(dia);
        }
        // Revisa que la union sea igual a n
        if (!U.equals(n)) return false;
        //Revisa que se cumpla la cota del problema
        return P.size() <= z;
        

    }

}


