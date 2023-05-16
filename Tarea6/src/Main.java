import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Set<Integer> A = new HashSet<>(); 
    static Set<int[]> p = new HashSet<>();
    static Set<Integer>[] s = (Set<Integer>[]) new Set<?>[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] amigos = line.split(" ");
        for(String amigo: amigos){
        	A.add(Integer.parseInt(amigo));
        }
        
        line = br.readLine();
        int cantParejas = Integer.parseInt(line);
        for(int i = 0; i < cantParejas; i++){
        	line = br.readLine();
        	int[] pareja = new int[2];
        	String[] par = line.split(" ");
            pareja[0] = Integer.parseInt(par[0]);
            pareja[1] = Integer.parseInt(par[1]);
            p.add(pareja);
        }

        line = br.readLine();
        String[] g1 = line.split(" ");
        Set<Integer> grupo1 = new HashSet<>();
        for(String elemento: g1){
        	grupo1.add(Integer.parseInt(elemento));
        }
        s[0] = grupo1;

        line = br.readLine();
        String[] g2 = line.split(" ");
        Set<Integer> grupo2 = new HashSet<>();
        for(String elemento: g2){
        	grupo2.add(Integer.parseInt(elemento));
        }
        s[1] = grupo2;

        line = br.readLine();
        int num = Integer.parseInt(line);
        br.close();
        boolean respuesta = verificacionParejas(A, p, s, num);
        System.out.println(respuesta);
    }


    public static boolean verificacionParejas(Set<Integer> A, Set<int[]> p, Set<Integer>[] s, int num){
        boolean b = false;
        int count = 0;
        //Se verifica que la respuesta sea válida
        for(Integer amigo: A){
            boolean primero = false;
            for(Set<Integer> grupo: s){
                if(grupo.contains(amigo) && !primero){
                    primero = true;
                } else if (grupo.contains(amigo) && primero){
                    return false;
                }
            }
        }
        if(A.size() == (s[0].size() + s[1].size())){
            //Se recorre cada pareja con conflicto
            for(int[] pareja: p){
                //Se busca la pareja en cada grupo
                for(Set<Integer> grupo: s){
                    //Si ambos amigos están en el mismo grupo, se aumenta el valor de count.
                    if((grupo.contains(pareja[0])) && (grupo.contains(pareja[1]))){
                        count++;
                    }
                }
            }
            //Se compara count con num
            if(count <= num){
                b = true;
            }
        }
        return b;
    }
}
