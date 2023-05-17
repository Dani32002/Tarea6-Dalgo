import java.io.*;
import java.util.*;
public class DiasGrafos {

    /*
     * n
     * m
     */
    
    public static void main(String[] args) throws IOException {
        DiasGrafos algoritmo = new DiasGrafos();
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
        int min = 0;
        int max = m.size();
        Set<Set<Integer>> res = new HashSet<>();
        Set<Set<Integer>> posible = new HashSet<>();
        while (min < max) {
            int mitad = (min + max)/2;
            posible = algoritmo.recorrido(m, n, mitad);
            if (posible == null){
                min = mitad + 1;
            } else {
                res = posible;
                max = mitad - 1;
            }
        }
        for (Set<Integer> setF: res){
            System.out.print("[");
            for (Integer i : setF){
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }

 
    public Set<Set<Integer>> recorrido(Set<Set<Integer>> m, Set<Integer> n , int z) {
        Set<Set<Integer>> inicio = new HashSet<>();
        Queue<Set<Set<Integer>>> fila = new LinkedList<Set<Set<Integer>>>();
        fila.add(inicio);
        // Hace un recorrido con una fila donde revisa si el primero de la fila cumple, sino revisa los sucesores y añade
        // los que sean viables. Hace este proceso hasta vaciar la fila.
        while (!fila.isEmpty()) {
            Set<Set<Integer>> actual = fila.poll();
            if (sat(m, n, actual, z)) return actual;
            List<Set<Set<Integer>>> sucesores = sucesores(actual, m);
            for (Set<Set<Integer>> s: sucesores) {
                if (viable(s, z)) fila.add(s);
            }
        }

        return null;
    }


    public boolean sat(Set<Set<Integer>> m, Set<Integer> n, Set<Set<Integer>> actual, int z) {
        return valid(m, n, actual) && viable(actual, z);
    }

    // Esta función encuentra todos los sucesores a un estado dado, busca los conjuntos que todavia no tiene en la respuesta y
    // crea una lista de vertices con los vertices iguales al estado pero añadiendo uno de los conjuntos que no se tiene aun.
    public List<Set<Set<Integer>>> sucesores(Set<Set<Integer>> x, Set<Set<Integer>> m) {
        List<Set<Integer>> posibles = new ArrayList<>();
        List<Set<Set<Integer>>> res = new ArrayList<>();
        for (Set<Integer> set: m) {
            if (!x.contains(set)) posibles.add(set);
        }
        for (int i = 0; i < m.size() - x.size(); i++){
            Set<Set<Integer>> nuevo = new HashSet<>(x);
            nuevo.add(posibles.get(i));
            res.add(nuevo);
        }
        return res;
    }

    // La función revisa si una respuesta es valida al verificar que sean conjuntos validos y que la union de n.
    public boolean valid(Set<Set<Integer>> m, Set<Integer> n, Set<Set<Integer>> dias) {
        Set<Integer> u = new HashSet<>();
        for (Set<Integer> set: dias) {
            if (!m.contains(set)) return false;
            u.addAll(set);
        }
        if (!u.equals(n)) return false;
        return true;
    }


    // Revisa la viabilidad del estado al verificar que el tamaño siga dentro de la cota.
    public boolean viable(Set<Set<Integer>> x, int z) {
        return x.size() <= z;
    }
}
