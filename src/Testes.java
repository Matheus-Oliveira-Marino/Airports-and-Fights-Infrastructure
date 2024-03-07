/**
 * A classe {@code Testes} é usada para realizar testes e demonstrações de funcionalidades do sistema.
 * Ela contém um método main como ponto de entrada do programa Java.
 */

public class Testes
{

    /**
     * O método 'main' é o ponto de entrada do programa Java.
     * Ele cria instâncias de {@code Aeroporto}, realiza testes e demonstrações de funcionalidades do sistema.
     * e imprime texto colorido no console usando códigos de cores 'ANSI'.
     *
     * @param args Os argumentos da linha de comando.
     */
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        try
        {
            Aeroporto brasilia = new Aeroporto(1, "BSB", "Brasilia");
            Aeroporto bHorizonte = new Aeroporto(2, "cnf", "Belo Horizonte");
            Aeroporto rio = new Aeroporto(3,"GIG","Rio");
            Aeroporto saoPaulo = new Aeroporto(4,"GRU","São Paulo");
            Aeroporto salvador = new Aeroporto(5,"SSA", "Salvador");

            // Criando a lista encadeada de aeroportos
//            Aeroportos.adicionarAeroporto(brasilia);
//            Aeroportos.adicionarAeroporto(bHorizonte);
//            Aeroportos.adicionarAeroporto(rio);
//            Aeroportos.adicionarAeroporto(saoPaulo);
//            Aeroportos.adicionarAeroporto(salvador);

//            Aeroportos.adicionarVoo(1,5,107);
//            Aeroportos.adicionarVoo(2,5,214);
//            Aeroportos.adicionarVoo(2,3,555);
//            Aeroportos.adicionarVoo(2,4,101);
//            Aeroportos.adicionarVoo(3,2,554);
//            Aeroportos.adicionarVoo(3,4,90);
//            Aeroportos.adicionarVoo(4,1,50);
//            Aeroportos.adicionarVoo(4,3,89);
//            Aeroportos.adicionarVoo(4,2,102);
//            Aeroportos.adicionarVoo(5,2,215);

  String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String magenta = "\u001B[35m";
        String cyan = "\u001B[36m";
        String white = "\u001B[37m";
        String reset = "\u001B[0m";

        // Exemplo de uso
        System.out.println(red + "Texto em vermelho" + reset);
        System.out.println(green + "Texto em verde" + reset);
        System.out.println(yellow + "Texto em amarelo" + reset);
        System.out.println(blue + "Texto em azul" + reset);
        System.out.println(magenta + "Texto em magenta" + reset);
        System.out.println(cyan + "Texto em ciano" + reset);
        System.out.println(white + "Texto em branco" + reset);


            //System.out.println(Aeroportos.aeroportos);

          // Aeroportos.getVooPorNumero(89);

//            aeroportos.removerVoo(89);
//

            //Aeroportos.totalDeVoo(bHorizonte);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
