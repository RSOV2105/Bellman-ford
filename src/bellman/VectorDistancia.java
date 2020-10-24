package bellman;
public class VectorDistancia {
    static int[] Copiar_Arreglo(int[] arr) {
        int temp[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }
    static boolean Relajacion(int U, int V, int W, int[] recor) {
        if (recor[U] + W < recor[V]) {
            recor[V] = recor[U] + W;
            return true;
        }
        return false;
    }
    static void Minimo_Recorrido_Unidireccional(int matriz[][], int V, int L, int N){
        int[] dist = new int[V];
        int[] prev = new int[V];
        for (int i = 0; i < V; i++){
            dist[i] = 99999999;
            prev[i] = -1;
        }
        dist[N] = 0; 
        prev[N] = 0;
        for (int i = 0; i < V-1; i++)  
        { 
            for (int j = 0; j < L; j++)  
            { 
                
                if(Relajacion(matriz[j][0], matriz[j][1], matriz[j][2], dist)){
                    prev[matriz[j][1]]= matriz[j][0];
            }
            }
        }
        for (int j = 0; j < L; j++)  
        {
            int[] temp = Copiar_Arreglo(dist);
            if(Relajacion(matriz[j][0], matriz[j][1], matriz[j][2], temp)){
                System.out.println("Existe ciclo negativo");
                break;
            }
        }
        
        System.out.print("\n\t");
        for(int i = 0; i < V; i++){
            System.out.print("N"+i+ "\t");
        }
        System.out.println("");
        System.out.print("Recor: "+"\t");
        for(int j = 0; j< V; j++){
                System.out.print(dist[j]+ "\t");
            }
        System.out.println("");
        System.out.print("Prev: "+"\t");
        for(int j = 0; j< V; j++){
                System.out.print("N"+prev[j]+ "\t");
            }
    }
     static void ImprimirGrafoInicial(int matriz[][], int V, int L){
        System.out.print("\t");
        for(int i = 0; i < V; i++){
            System.out.print("N"+i+ "\t");
        }
        System.out.println("");
        for(int i = 0; i< V; i++){
            System.out.print("N"+i+ "\t");
            for(int j = 0; j< V; j++){
                int cont = 0;
                for(int k = 0; k< L; k++){
                    if(matriz[k][0]==i && matriz[k][1]==j){
                        System.out.print(matriz[k][2]+ "\t");
                        cont++;
                    } 
                }
                if(cont==0 && i!=j){
                    System.out.print("-" +"\t");
                }
                if(cont==0 && i==j){
                        System.out.print(0+ "\t");
                }
                
            }
            System.out.println("");
        }
    }
     
    static int[][] ConvertirDatos(String[] datos, int L){
        
        int matriz[][] = new int[L][3];

        for(int i=0; i<L;i++){
            String[] temp = datos[i+3].split(" ");
            matriz[i][0]= Integer.parseInt(temp[0]);
            matriz[i][1]= Integer.parseInt(temp[1]);
            matriz[i][2]= Integer.parseInt(temp[2]);
        }
        
        return matriz;
    }

    public static void main(String[] args) {
        

       
        //Requiere que enlaces sean ingresados en orden de numero de nodo de menor a mayor*/
        
        
        //Usando archivo de texto
        Archivo text = new Archivo();
        String[] datos = text.leerTxT("C:\\Users\\RSOV\\Desktop\\TrabajoRedes\\Escenario4.txt");
        //String[] datos = text.leerTxT("C:\\Users\\RSOV\\Desktop\\TrabajoRedes\\Escenario2.txt");
        //String[] datos = text.leerTxT("C:\\Users\\RSOV\\Desktop\\TrabajoRedes\\Escenario3.txt");
        int Nodos = Integer.parseInt(datos[0]); //Primera linea define el numero de nodos
        int Aristas = Integer.parseInt(datos[1]); // Segunda linea define numero de aristas
        int Nodo_Inicial = Integer.parseInt(datos[2]); // Tercera linea define nodo de origen
        int datosNodos[][] = ConvertirDatos(datos, Aristas); // Demas lineas definen aristas (Nodo inicial/Nodo destino/Peso)
        //Nota: Nodos deben ser colocados en orden numerico (0/X/X) (1/X/X) (1/X/X) (2/X/X)
        
        
        ImprimirGrafoInicial(datosNodos, Nodos, Aristas);
        Minimo_Recorrido_Unidireccional(datosNodos, Nodos, Aristas, Nodo_Inicial);

    }
}
