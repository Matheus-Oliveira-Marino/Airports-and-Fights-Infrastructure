import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A classe {@code Auxiliar} representa a interface do usuário.
 * Ela fornece métodos para interação com o usuário, como obter entrada de dados e exibir menus.
 *
 * @author Gabriel Caumo,
 * @author Matheus Oliveira.
 */
public class Auxiliar 
{
    protected static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Método para obter um número inteiro da entrada do usuário.
     *
     * @param bufferedReader O leitor de entrada para ler os dados do usuário.
     * @param mensagem       A mensagem exibida ao usuário para solicitar entrada.
     * @return Retorna um número inteiro inserido pelo usuário.
     * @throws IOException Se houver um erro de entrada/saída ao ler os dados do usuário.
     */
    protected static int obterInteiro(BufferedReader bufferedReader, String mensagem) throws IOException
     {
        while (true)
        {
            System.out.print(mensagem);

            // Lê uma linha de texto da entrada do usuário
            // e armazena numa variável 'input'.
            String input = bufferedReader.readLine();  
            try
            {
                // Tenta converter a entrada do usuário em um número inteiro.
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                // Se a entrada não for um número inteiro, exibe uma mensagem de erro 
                // e solicita novamente.
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }


    /**
     * Mostra a lista de aeroportos cadastrados.
     */
    public static void mostrar()
    {
        // Verifica se a lista de aeroportos está vazia e imprime apropriadamente.
        if(Aeroportos.aeroportos.isVazia())
            System.out.println("Aeroportos Vazio");
        else
        {
             // Se não estiver vazia, imprime a lista de aeroportos formatada.
             System.out.println("\u001B[36m" + Aeroportos.aeroportos + "\033[0m");
             System.out.println();
        }
    }

    /**
     * Exibe o menu principal para interação com os aeroportos.
     */
    public static void menu()
    {
        int op;

        try
        {
            // Loop para manter o menu em execução até que o usuário escolha sair (opção 0).
            do
            {
                // Exibe as opções do menu.
                System.out.println("\nMenu de Aeroporto:");
                System.out.println("1. Adicionar aeroporto");
                System.out.println("2. Remover aeporto");
                System.out.println("3. Visualizar todos os aeroportos");
                System.out.println("4. Voos");
                System.out.println("0. Sair");


                // Obtém a escolha do usuário.
                op = obterInteiro(bufferedReader, "Escolha uma opção: ");

                // Executa a ação correspondente à escolha do usuário.
                switch (op)
                {
                    case 1:
                        // Exibe a lista de aeroportos.
                        // e chama o método para adicionar um novo aeroporto.
                        Auxiliar.mostrar();
                        Aeroportos.adicionarAeroporto();
                        break;

                    case 2:
                         // Exibe a lista de aeroportos.
                         // e chama o método para remover um aeroporto existente.
                        mostrar();

                        // Verifica se a lista de aeroportos está vazia.
                        if(Aeroportos.aeroportos.isVazia())
                        {
                            System.out.println("\u001B[31mNão existe aeroporto para ser excluido\u001B[0m");
                            break;
                        }

                        // Solicita o usuário a inserir um ID a ser removido.
                        int ID = obterInteiro(bufferedReader,"Digite o id a ser removido: ");
                        Aeroportos.removerAeroporto(ID);
                        break;

                    case 3:
                        // Limpa o terminal e exibe 
                        // a lista de todos os aeroportos cadastrados.
                        limpar();
                        System.out.println("Todos os aeroportos cadastrados: ");
                        System.out.println(Aeroportos.aeroportos);
                        break;

                    case 4:
                        // Limpa o terminal e exibe o menu de interação com os voos.
                        limpar();
                        menuVoo();
                        break;

                    case 0:
                        // Encerra o programa.
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        // Se a opção escolhida não for válida, exibe uma mensagem de erro.
                        System.out.println("Opção inválida! Tente novamente.");
                }

            }
            while (op != 0);
        }
        catch (Exception e)
        {
            // Em caso de exceção, imprime a pilha de erro no console.
            e.printStackTrace();
        }
    }

    /**
     * Exibe o menu para interação com os voos.
     */
    public static void menuVoo()
    {
        int ope;

        try
        {
             // Loop para manter o menu de voos em execução 
             // até que o usuário escolha sair (opção 0).
            do
            {
                // Exibe as opções do menu de voos.
                System.out.println("\nMenu de Voo:");
                System.out.println("1. Adicionar Voo");
                System.out.println("2. Remover Voo");
                System.out.println("3. Visualizar todos os voos");
                System.out.println("4. Aeroporto");
                //System.out.println("0. Sair");

                // Obtém a escolha do usuário.
                ope = obterInteiro(bufferedReader, "Escolha uma opção: ");

                // Executa a ação correspondente à escolha do usuário.
                switch (ope)
                {
                    case 1:

                        // Chama o método para adicionar um novo voo.
                        Aeroportos.adicionarVoo();
                        break;

                    case 2:

                        // Chama o método para remover um voo existente.
                        if(Aeroportos.aeroportos.isVazia())
                        {
                            System.out.println("Não ha o que remover");
                            break;
                        }

                        // Mostra o número do voo ao usuário para que ele 
                        // selecione a mesma.
                        Aeroportos.selecionarNumero();

                        // Solicita ao usuário para digitar o número do voo a ser excluido.
                        int id = obterInteiro(bufferedReader,"Digite o numero do voo a ser excluido ");

                        // Remove o voo selecionado da lista de voos.
                        Aeroportos.removerVoo(id);
                        break;

                    case 3:

                        // Verifica se a lista de aeroportos está vazia.  
                        if(Aeroportos.aeroportos.isVazia())
                        {
                            System.out.println("Não ha aeroporto");
                            break;
                        }

                        //  Mostra e solicita ao usuário para escolher um aeroporto
                        Aeroportos.selecionarAeroporto();
                        int ID = obterInteiro(bufferedReader,"Digite o id do aeroporto para ver todos os voos ");
                        
                        // Mostra todos os voos com base no ID do aeroporto selecionado.
                        Aeroportos.totalDeVoo(ID);
                        menuVoo();

                    
                    case 4:
                        // Limpa o terminal e exibe o menu principal.    
                        limpar();
                        menu();
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
            while (ope != 0);
        }
        catch (Exception e)
        {
            // Em caso de exceção, imprime a pilha de erro no console.
            e.printStackTrace();
        }
    }

   

    /**
     * Limpa o terminal.
     */
    public final static void limpar()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
