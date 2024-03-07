public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            // Chama o método menu da classe 'Auxiliar' para iniciar o programa.
            Auxiliar.menu();
        }
        catch (Exception e)
        {
            // Em caso de exceção, imprime a pilha de erro no console.
            e.printStackTrace();
        }
    }

}
