public class Main {

    static String texto = "4;5;7;8;4";

    public static void main(String[] args) {

        int suma = 0;

        String[] separados = texto.split(";");

        for(String separado : separados)
            suma += Integer.parseInt(separado);

        System.out.println("El total es: " + suma);

    }
}