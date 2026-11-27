public class Questao6Lista7 {

    static int converter(String texto) {
        try {
            return Integer.parseInt(texto); // lança NumberFormatException
        } catch (NumberFormatException e) {
            // relança nova exceção, mantendo a original como causa
            throw new IllegalStateException("Falha ao converter o valor: " + texto, e);
        }
    }

    public static void main(String[] args) {
        try {
            converter("abc");
        } catch (IllegalStateException e) {
            System.out.println("Exceção capturada: " + e.getMessage());

            Throwable causa = e.getCause();
            if (causa != null) {
                System.out.println("Causa original: " + causa.getClass().getName());
                System.out.println("Mensagem da causa: " + causa.getMessage());
            }
        }
    }
}
