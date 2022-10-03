package edu.pe.fcfs;

import java.util.Scanner;

public class FCFS {
    String name;
    double arrivetime;  // Hora de llegada del proceso
    double servicetime;  // Duración del tiempo de ejecución del proceso (tiempo de servicio)
    double starttime; // Hora de inicio del proceso de ejecución
    double finishtime; // Tiempo de finalización de la ejecución del proceso
    double zztime; // Tiempo de respuesta
    double dqzztime; // Tiempo de respuesta ponderado
    public FCFS(){ }
    
    // Constructor de la clase
    public FCFS(String name, double arrivetime, double servicetime) {
        this.name = name;
        this.arrivetime = arrivetime;
        this.servicetime = servicetime;
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============== Algoritmo de programación por orden de llegada ========================");
        System.out.println("Ingrese el número de procesos:");
        int num = scanner.nextInt();

        // Crear objeto de matriz de proceso
        FCFS[] p = new FCFS[num];
        System.out.println("Cree un objeto de proceso, introduzca el nombre del proceso, la hora de llegada, la hora de servicio <por ejemplo: p1 0 20>");
        for (int i= 0; i<p.length; i++){
            System.out.println("Por favor ingrese el proceso: " +(i+1)+" Información: ");
            p[i] = new FCFS(scanner.next(), scanner.nextDouble(),scanner.nextDouble());
        }
        OS_FCFS(p); // Llamar al algoritmo por orden de llegada
        scanner.close();
    }


    // Algoritmo por orden de llegada
    private static void OS_FCFS(FCFS[] p) {
        sort(p); //Ordenar
        run(p); // Ejecuta el proceso
        print(p); // UI

        double Attime=0 ,AQttime = 0;
        for (int k=0; k<p.length;k++){
            Attime += p[k].zztime;
            AQttime += p[k].dqzztime;
        }
        Attime = Attime/p.length;
        AQttime = AQttime/p.length;

        System.out.println("El tiempo de respuesta promedio para las llamadas por orden de llegada es: "+Attime);
        //  System.out.printf("%.3f\n",AQttime);
        System.out.println("El tiempo de respuesta promedio ponderado para llamar por orden de llegada es: "+AQttime);
    }

    // Algoritmo de clasificación (método de clasificación de burbujas)
    public static void sort(FCFS[] p){
        for (int i=0;i<p.length;i++){
            for (int j=i+1;j<p.length;j++){
                if (p[i].arrivetime>p[j].arrivetime){
                    FCFS temp;
                    temp = p[i];
                    p[i] = p[j];
                    p[j]= temp;
                }
            }
        }
    }


    // Ejecución del proceso
    private static void run(FCFS[] p) {
        for(int k=0; k<p.length;k++){
            if (k==0){
                p[k].starttime = p[k].arrivetime;
                p[k].finishtime = p[k].arrivetime+p[k].servicetime;
            }else{
                p[k].starttime = p[k-1].finishtime;
                p[k].finishtime = p[k-1].finishtime+p[k].servicetime;
            }
        }
        for (int k=0; k<p.length;k++){
            p[k].zztime = p[k].finishtime-p[k].arrivetime;  // Calcula el tiempo de respuesta del proceso
            p[k].dqzztime=p[k].zztime/p[k].servicetime;  // Calcula el tiempo de respuesta correcto del proceso
        }
    }


    // Resultado echo
    private static void print(FCFS[] p) {
        System.out.println("Llame al algoritmo por orden de llegada y luego la secuencia de operación del proceso es:");
        for (int k=0;k<p.length;k++){
            System.out.print("-->"+p[k].name);
        }
        System.out.println("");
        System.out.println("Información de programación específica:");
        System.out.println("Nombre del proceso: Hora de llegada: Hora de servicio: Hora de inicio: Hora de finalización: Tiempo de respuesta: Tiempo de respuesta ponderado:");
        for(int k =0;k<p.length;k++){
            System.out.printf("%10s",p[k].name);
            System.out.printf("%20.3f",p[k].arrivetime);
            System.out.printf("%18.3f",p[k].servicetime);
            System.out.printf("%17.3f",p[k].starttime);
            System.out.printf("%20.3f",p[k].finishtime);
            System.out.printf("%20.3f",p[k].zztime);
            System.out.printf("%25.3f\n",p[k].dqzztime);
        }
    }
}
