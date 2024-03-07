
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * A classe {@code Aeroportos} representa o gerenciamento dos aeroportos e seus voos associados.
 * Permite adicionar, remover aeroportos e voos, além de visualizar informações sobre eles.
 */
public class Aeroportos
{
    /** Lista de aeroportos cadastrados. */
    protected static ListaSimplesDesordenada<Aeroporto> aeroportos = new ListaSimplesDesordenada<>();
    
    /** BufferedReader para leitura de entrada do usuário. */
    protected static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Exibe os números de voo disponíveis para seleção.
     */
    public static void selecionarNumero() 
    {
        try 
        {
            // Exibe a mensagem para o usuário selecionar o número do voo
            System.out.println("\u001B[36mSelecione o numero do voo:\u001B[0m");
            
            // Itera sobre todos os aeroportos cadastrados
            for (int i = 1; i <= Aeroportos.aeroportos.totalDaLista(); i++)
             {
                // Obtém o aeroporto atual na iteração.
                Aeroporto aeroporto = Aeroportos.aeroportos.get(i);
                
                // Itera sobre todos os voos do aeroporto atual
                for(int j = 1; j <= aeroporto.getVoos().totalDaLista(); j++) 
                {
                    // Obtém o voo atual na iteração.
                    Voos voo = aeroporto.getVoos().get(j);
                    
                    // Exibe o número do voo atual.
                    System.out.println("\u001B[36mNumero do Voo: " + voo.getNumero() + "\u001B[0m");
                }
            }
        } catch (Exception e) 
        {
            // Se ocorrer uma exceção durante o processo, exibe uma mensagem de erro.
            System.out.println("Erro ao mostrar os numeros do voos");
        }
    }

    /**
     * Exibe a lista de aeroportos disponíveis para seleção.
     */
    public static void selecionarAeroporto() {
        try 
        {
            // Exibe a mensagem para o usuário selecionar um aeroporto.
            System.out.println("\u001B[36mSelecione um aeroporto:\u001B[0m");
            
            // Itera sobre todos os aeroportos cadastrados
            for (int i = 1; i <= Aeroportos.aeroportos.totalDaLista(); i++) 
            {
                // Obtém o aeroporto atual na iteração.
                Aeroporto aeroporto = Aeroportos.aeroportos.get(i);
                
                // Exibe o código e o nome da cidade do aeroporto atual.
                System.out.println("\u001B[36m" + aeroporto.getCodigo() + " - " + aeroporto.getNomeCidade() + "\u001B[0m");
            }
        } catch (Exception e) {
            // Se ocorrer uma exceção durante o processo, 
            // exibe uma mensagem de erro e retorna ao menu de voos.
            System.out.println("Erro ao mostrar os aeroportos");
            Auxiliar.menuVoo();
        }
    }
    

    /**
     * Adiciona um novo voo ao sistema.
     *
     * @throws Exception Lançada caso ocorra um erro durante a adição do voo.
     */
    public static void adicionarVoo() throws Exception {
        try {
            // Verifica se não há aeroportos cadastrados.
            if (aeroportos.isVazia()) 
            {
                System.out.println();
                System.out.println("\u001B[31mNão existe aeroporto\u001B[0m");
                return;
            }
    
            // Verifica se há apenas um aeroporto cadastrado.
            if (aeroportos.totalDaLista() <= 1) 
            {
                System.out.println();
                System.out.println("\u001B[31mImpossível criar voo, há apenas um aeroporto\u001B[0m");
                return;
            }
    
            // Solicita ao usuário que selecione o aeroporto de origem e destino e o número do voo.
            selecionarAeroporto();
            int codigoOrigem = Auxiliar.obterInteiro(bufferedReader, "Digite o código do aeroporto de origem: ");
            int codigoDestino = Auxiliar.obterInteiro(bufferedReader, "Digite o código do aeroporto de destino: ");
            int numeroVoo = Auxiliar.obterInteiro(bufferedReader, "Digite o número do voo: ");
    
            // Inicializa variáveis para armazenar os aeroportos de origem e destino
            Aeroporto aeroportoOrigem = null;
            Aeroporto aeroportoDestino = null;
    
            // Itera sobre todos os aeroportos cadastrados para encontrar os de origem e destino
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) {
                Aeroporto aeroporto = aeroportos.get(i);
    
                if (aeroporto.getCodigo() == codigoOrigem)
                    aeroportoOrigem = aeroporto;
    
                if (aeroporto.getCodigo() == codigoDestino)
                    aeroportoDestino = aeroporto;
            }
    
            // Verifica se os aeroportos de origem e destino foram encontrados e se são diferentes.
            if (aeroportoOrigem == null || aeroportoDestino == null || aeroportoOrigem == aeroportoDestino)
                System.out.println("\u001B[31mNão foi possível criar voo, verifique os códigos dos aeroportos\u001B[0m");
    
            // Obtém a lista de voos do aeroporto de origem
            ListaSimplesDesordenada<Voos> voosOrigem = aeroportoOrigem.getVoos();
    
            // Verifica se o número do voo já existe no aeroporto de origem.
            for (int i = 1; i <= voosOrigem.totalDaLista(); i++) 
            {
                Voos vooExistente = voosOrigem.get(i);
    
                if (vooExistente.getNumero() == numeroVoo) 
                {
                    System.out.println("\u001B[31mO número de voo já existe no aeroporto de origem\u001B[0m");
                }
            }
    
            // Verifica se o código de origem é igual ao de destino.
            if (codigoOrigem == codigoDestino) 
            {
                System.out.println("\u001B[31mNão foi possível criar voo, verifique os códigos dos aeroportos\u001B[0m");
                Auxiliar.menuVoo();
            }
    
            // Cria um novo voo e o adiciona ao aeroporto de origem.
            Voos voo = new Voos(aeroportoDestino.getCodigo(), numeroVoo);
            aeroportoOrigem.getVoos().guardeUmItemNoFinal(voo);
    
            // Limpa a tela e exibe uma mensagem de sucesso.
            Auxiliar.limpar();
            System.out.println();
            System.out.println("\u001B[32mVoo adicionado com sucesso\u001B[0m");
        } 
        catch (Exception e) 
        {
            // Em caso de exceção, exibe uma mensagem de erro e retorna ao menu de voos.
            System.out.println("Erro ao adicionar voo tente novamente");
            Auxiliar.menuVoo();
        }
    }
    /**
     * Remove um voo do sistema pelo número do voo.
     *
     * @param numero O número do voo a ser removido.
     * @throws Exception Lançada caso ocorra um erro durante a remoção do voo.
     */
    public static void removerVoo(int numero) throws Exception {
        try {
            boolean removido = false;
    
            // Itera sobre todos os aeroportos cadastrados.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                Aeroporto aeroporto = aeroportos.get(i);
    
                // Itera sobre todos os voos do aeroporto atual.
                for (int j = 1; j <= aeroporto.getVoos().totalDaLista(); j++)
                 {
                    Voos voo = aeroporto.getVoos().get(j);
    
                    // Verifica se o número do voo atual é igual ao número fornecido.
                    if (voo.getNumero() == numero) 
                    {
                        // Remove o voo da lista de voos do aeroporto.
                        aeroporto.getVoos().removaItemIndice(j);
                        removido = true;
    
                        // Limpa a tela e exibe uma mensagem de sucesso.
                        Auxiliar.limpar();
                        System.out.println();
                        System.out.println("\u001B[32mVoo excluido\u001B[0m");
                        break;
                    }
                }
            }
    
            // Verifica se o voo foi removido.
            if (!removido) 
            {
                System.out.println();
                System.out.println("\u001B[31mVoo não encontrado\u001B[0m");
            }
    
        } 
        catch (Exception e) 
        {
            // Em caso de exceção, exibe uma mensagem de erro e retorna ao menu de voos.
            System.out.println();
            System.out.println("Não foi possível encontrar o número do voo");
            Auxiliar.menuVoo();
        }
    }


    /**
     * Adiciona um novo aeroporto ao sistema.
     *
     * @throws Exception Lançada caso ocorra um erro durante a adição do aeroporto.
     */
    public static void adicionarAeroporto() throws Exception 
    {
        try 
        {
            // Solicita ao usuário o ID, a sigla e o nome do aeroporto.
            int id = Auxiliar.obterInteiro(bufferedReader, "Digite o id (DEVE SER UM NUMERO INTEIRO POSITIVO): ");
            System.out.println("Digite a sigla (DEVE CONTER ATÉ 3 LETRAS) do aeroporto: ");
            String sigla = bufferedReader.readLine();
            System.out.println("Digite o nome da cidade: ");
            String nome = bufferedReader.readLine();
            
            // Cria um novo objeto Aeroporto com os dados fornecidos.
            Aeroporto aeroporto = new Aeroporto(id, sigla, nome);
    
            // Verifica se já existe um aeroporto com o mesmo código ou sigla.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                Aeroporto atual = aeroportos.get(i);
                
                if (atual.getCodigo() == aeroporto.getCodigo()) 
                {
                    throw new Exception("Já existe este código");
                }
                
                if (atual.getSigla().equals(aeroporto.getSigla())) 
                {
                    throw new Exception("Já existe esta sigla");
                }
            }
    
            // Adiciona o novo aeroporto à lista de aeroportos
            aeroportos.guardeUmItemNoFinal(aeroporto);
    
            // Limpa a tela e exibe uma mensagem de sucesso 
            // junto com os detalhes do aeroporto adicionado.
            System.out.println();
            Auxiliar.limpar();
            System.out.println("\u001B[32mAdicionado com sucesso!\u001B[0m");
            System.out.println(aeroporto);
        } catch (Exception e) 
        {
            // Em caso de exceção, exibe uma mensagem de erro 
            // e pergunta ao usuário se deseja voltar ao menu.
            System.out.println("[31mInsira os dados corretamente[0m");
            System.out.println("[31mVerifique se já existe aeroporto com esses dados[0m");
            System.out.println("Deseja voltar ao menu? Digite [S] para voltar");
            try 
            {
                String input = bufferedReader.readLine();
                if (input.toUpperCase().equals("S")) {
                    Auxiliar.limpar();
                    Auxiliar.menu();
                }
            } catch (IOException ex) 
            {
                throw new RuntimeException(ex);
            }
            // Chama recursivamente o método 'adicionarAeroporto()'
            // para tentar adicionar o aeroporto novamente.
            adicionarAeroporto();
        }
    }
    /**
     * Remove um aeroporto do sistema pelo ID.
     *
     * @param id O ID do aeroporto a ser removido.
     */
    public static void removerAeroporto(int id) 
    {
        try 
        {
            // Inicializa uma variável para armazenar o aeroporto a ser removido.
            Aeroporto aeroportoRemover = null;
            
            // Itera sobre a lista de aeroportos para encontrar o aeroporto com o 'ID' fornecido.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) {
                Aeroporto aeroporto = aeroportos.get(i);
                if (aeroporto.getCodigo() == id) 
                {
                    // Exibe um aviso sobre a exclusão do aeroporto e lista os voos associados a ele.
                    System.out.println("\u001B[31mAVISO!!! Ao excluir esse aeroporto os seguintes voos vão ser excluídos!!!\u001B[0m");
                    getVooPorAeroporto(aeroporto); // Função para listar os voos associados a um aeroporto.
                    aeroportoRemover = aeroporto;
                    break;
                }
            }
    
            // Verifica se o aeroporto a ser removido foi encontrado.
            if (aeroportoRemover != null) 
            {
                // Verifica se o aeroporto possui voos associados e os lista.
                if (aeroportoRemover.getVoos().totalDaLista() >= 1) {
                    for (int i = 1; i <= aeroportoRemover.getVoos().totalDaLista(); i++) {
                        Voos voos = aeroportoRemover.getVoos().get(i);
                        System.out.println("Número do voo: " + voos.getNumero());

                        // Função para obter o nome da cidade de destino de um voo
                        System.out.println("Cidade de destino: " + obterNomeCidadeDestino(voos)); 
                        System.out.println("---------------------------------------");
                    }
                }
    
                // Solicita confirmação ao usuário para excluir o aeroporto.
                System.out.println("Tem certeza que quer excluir esse aeroporto? [S] para confirmar");
                String confirmação = bufferedReader.readLine();
    
                if (confirmação.toUpperCase().equals("S")) {
                    // Remove os voos associados ao aeroporto a ser removido.
                    for (int i = 1; i <= aeroportos.totalDaLista(); i++)
                     {
                        Aeroporto aeroporto = aeroportos.get(i);
                        for (int j = aeroporto.getVoos().totalDaLista(); j >= 1; j--)
                         {
                            Voos voos = aeroporto.getVoos().get(j);
    
                            if (voos.getCodigo() == id) 
                            {
                                aeroporto.getVoos().removaItemIndicado(voos);
                            }
                        }
                    }
    
                    // Remove o aeroporto da lista de aeroportos.
                    aeroportos.removaItemIndicado(aeroportoRemover);
                    Auxiliar.limpar();
                    System.out.println("\u001B[32mAeroporto removido com sucesso!\u001B[0m");
                } else 
                {
                    // Caso o usuário não confirme a exclusão, volta ao menu principal.
                    Auxiliar.limpar();
                    System.out.println("OK Voltando ao menu..");
                    Auxiliar.menu();
                }
            } else {
                // Se o aeroporto não for encontrado, exibe uma mensagem de erro.
                Auxiliar.limpar();
                System.out.println();
                System.out.println("\u001B[31mAeroporto não encontrado!\u001B[0m");
            }
        } catch (Exception e) 
        {
            // Em caso de exceção, lança uma 'RuntimeException'.
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Obtém os voos de um aeroporto específico.
     *
     * @param aeroporto O aeroporto para o qual deseja obter os voos.
     */
    public static void getVooPorAeroporto(Aeroporto aeroporto) 
    {
        try {
            // Itera sobre todos os aeroportos na lista de aeroportos.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                Aeroporto aero = aeroportos.get(i);
    
                // Itera sobre todos os voos do aeroporto atual.
                for (int j = 1; j <= aero.getVoos().totalDaLista(); j++) 
                {
                    Voos voos = aero.getVoos().get(j);
    
                    // Verifica se o código do aeroporto do voo corresponde 
                    // ao código do aeroporto fornecido.
                    if (voos.getCodigo() == aeroporto.getCodigo()) 
                    {
                        // Se corresponder, exibe o número do voo e a cidade de destino
                        System.out.println("Número do voo: " + voos.getNumero());

                        // Função para obter o nome da cidade de destino de um voo
                        System.out.println("Cidade de destino: " + obterNomeCidadeDestino(voos)); 
                        System.out.println("---------------------------------------");
                    }
                }
            }
        } catch (Exception e) 
        {
            // Em caso de exceção, imprime o rastreamento da pilha de erro.
            e.printStackTrace();
        }
    }

     /**
     * Obtém um aeroporto pelo 'ID'.
     *
     * @param indice O 'ID' do aeroporto a ser obtido.
     * @return O objeto Aeroporto correspondente ao 'ID' especificado.
     * @throws Exception Lançada caso o aeroporto não seja encontrado.
     */
    public static Aeroporto getAeroportoPorID(int indice) throws Exception 
    {
        try 
        {
            // Itera sobre todos os aeroportos na lista de aeroportos.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                Aeroporto aeroporto = aeroportos.get(i);
    
                // Verifica se o código do aeroporto corresponde ao índice fornecido.
                if (aeroporto.getCodigo() == indice)
                    return aeroporto; // Retorna o aeroporto se o código corresponder.
            }
        } catch (Exception e)
         {
            // Em caso de exceção, imprime o rastreamento da pilha de erro.
            e.printStackTrace();
        }
    
        // Se nenhum aeroporto for encontrado com o índice fornecido, lança uma exceção
        throw new Exception("Aeroporto não encontrado");
    }


    
    /**
     * Obtém o total de voos saindo de um aeroporto específico.
     *
     * @param codigoAeroporto O código do aeroporto para o qual deseja obter os voos.
     */
    public static void totalDeVoo(int codigoAeroporto) {
        try 
        {
            // Obtém o aeroporto pelo código fornecido.
            Aeroporto aeroporto = obterAeroportoPeloCodigo(codigoAeroporto);
    
            // Verifica se o aeroporto foi encontrado.
            if (aeroporto == null) {
                System.out.println();
                System.out.println("\u001B[31mAeroporto não encontrado! Verifique o código do aeroporto\u001B[0m");
                return;
            }
    
            // Verifica se não há voos no aeroporto.
            if (aeroporto.getVoos().isVazia()) 
            {
                System.out.println("\u001B[31mNão há voos\u001B[0m");
                return;
            }
    
            // Imprime os voos saindo do aeroporto.
            System.out.println("Voos saindo do Aeroporto " + aeroporto.getNomeCidade() + ":");
    
            // Obtém a lista de voos do aeroporto.
            ListaSimplesDesordenada<Voos> voos = aeroporto.getVoos();
            // Itera sobre a lista de voos
            for (int i = 1; i <= voos.totalDaLista(); i++) 
            {
                Voos voo = voos.get(i); // Obtém o voo atual.

                // Imprime o número do voo e a cidade de destino.
                System.out.println("\u001B[35mNúmero do voo: " + voo.getNumero() + "\u001B[0m");
                System.out.println("\u001B[35mCidade de destino: " + obterNomeCidadeDestino(voo) + "\u001B[0m");
                System.out.println("\u001B[35m---------------------------------------\u001B[0m");
            }
        } catch (Exception e) 
        {
            // Em caso de exceção, imprime a mensagem de erro.
            System.out.println("Erro ao obter voos: " + e.getMessage());
        }
    }
    
    /**
     * Obtém o nome da cidade de destino de um voo.
     *
     * @param voo O objeto Voos do qual deseja obter a cidade de destino.
     * @return O nome da cidade de destino do voo.
     */
    private static String obterNomeCidadeDestino(Voos voo) 
    {
        String nome = ""; // Inicializa a variável de retorno.
    
        try {
            // Itera sobre a lista de aeroportos.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                // Obtém o aeroporto atual.
                Aeroporto aeroporto = aeroportos.get(i); 
    
                // Itera sobre a lista de voos do aeroporto atual.
                for (int j = 1; j <= aeroporto.getVoos().totalDaLista(); j++) 
                {
                    // Obtém o voo atual do aeroporto.
                    Voos vooAtual = aeroporto.getVoos().get(j); 
    
                    // Verifica se o voo atual é o mesmo que o voo fornecido.
                    if (vooAtual.getCodigo() == voo.getCodigo()) 
                    {
                        // Obtém o aeroporto de destino do voo atual.
                        Aeroporto aeroportoDestino = obterAeroportoPeloCodigo(vooAtual.getCodigo());
    
                        // Verifica se o aeroporto de destino foi encontrado.
                        if (aeroportoDestino != null) 
                        {   
                            // Obtém o nome da cidade de destino
                            nome = aeroportoDestino.getNomeCidade(); 
                            break; // Sai do loop interno.
                        }
                    }
                }
    
                // Verifica se o nome da cidade de destino foi encontrado.
                if (!nome.isEmpty()) {
                    break; // Sai do loop externo.
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o rastreamento da pilha em caso de exceção.
        }
    
        return nome; // Retorna o nome da cidade de destino.
    }
    /**
     * Obtém um aeroporto pelo código.
     *
     * @param codigo O código do aeroporto a ser obtido.
     * @return O objeto 'Aeroporto' correspondente ao código especificado, ou null caso não seja encontrado.
     */
    private static Aeroporto obterAeroportoPeloCodigo(int codigo) 
    {
        // Itera sobre a lista de aeroportos.
        for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
        {
            Aeroporto aeroporto = null; // Inicializa a variável de aeroporto como nula.
    
            try 
            {
                aeroporto = aeroportos.get(i); // Obtém o aeroporto atual da lista.
            } 
            catch (Exception e) 
            {
                throw new RuntimeException(e); // Lança uma exceção em caso de erro ao obter o aeroporto.
            }
    
            // Verifica se o código do aeroporto atual corresponde ao código fornecido.
            if (aeroporto.getCodigo() == codigo)
            {
                return aeroporto; // Retorna o aeroporto se o código corresponder.
            }
        }
        
        // Retorna 'null' se nenhum aeroporto com o código fornecido for encontrado.
        return null; 
    }
    /**
     * Obtém um aeroporto pelo 'ID' e exibe as suas informações.
     */
    public static void getAeroportoPorID() 
    {
        try 
        {
            // Solicita ao usuário que insira o ID do aeroporto que deseja visualizar.
            int aeropoID = Auxiliar.obterInteiro(bufferedReader, "Digite o id que quer visualizar ");
            
            // Obtém o aeroporto com base no ID fornecido e imprime suas informações.
            System.out.println();
            System.out.println(Aeroportos.getAeroportoPorID(aeropoID));
        } 
        catch (Exception e) 
        {
            // Manipula a exceção se o aeroporto não for encontrado com o ID especificado.
            System.out.println("\u001B[31mAeroporto não encontrado com o id especificado\u001B[0m");
            System.out.println("Deseja voltar ao menu? Digite [S] para voltar");
            try 
            {
                // Verifica se o usuário deseja voltar ao menu.
                String input = bufferedReader.readLine();

                if (input.toUpperCase().equals("S")) 
                {
                    // Volta ao menu principal.
                    Auxiliar.menu(); 
                }
            } 
            catch (IOException ex) 
            {
                // Lança uma exceção se ocorrer um erro de 'E/S'.
                throw new RuntimeException(ex); 
            }

            // Chama recursivamente o método se o usuário optar por voltar ao menu.
            getAeroportoPorID(); 
        }
    }

    /**
     * Retorna o voo com o número especificado.
     *
     * @param num o número do voo a ser procurado
     * @throws Exception se ocorrer uma exceção durante a busca do voo
     */
    public static void getVooPorNumero(int num) throws Exception 
    {
        Voos voo = null; // Inicializa uma variável para armazenar o voo encontrado.
    
        try {
            // Itera sobre todos os aeroportos na lista de aeroportos.
            for (int i = 1; i <= aeroportos.totalDaLista(); i++) 
            {
                // Obtém o aeroporto atual.
                Aeroporto aeroporto = aeroportos.get(i); 
    
                // Itera sobre todos os voos no aeroporto atual.
                for (int j = 1; j <= aeroporto.getVoos().totalDaLista(); j++)
                 {
                    // Verifica se o número do voo corresponde ao número fornecido.

                    if (aeroporto.getVoos().get(j).getNumero() == num) 
                    {
                        // Armazena o voo encontrado.
                        voo = aeroporto.getVoos().get(j); 
                        break; // Sai do loop interno, pois o voo foi encontrado.
                    }
                }
    
                if (voo != null) 
                {
                    break; // Sai do loop externo, pois o voo foi encontrado
                }
            }
    
            // Verifica se o voo foi encontrado e imprime suas informações.
            if (voo == null) 
            {
                System.out.println();
                System.out.println("\u001B[31mVoo não encontrado com esse número\u001B[0m");
            } else 
            {
                System.out.println("Voo encontrado: " + voo);
            }
        } 
        catch (Exception e)
        {
            // Imprime a pilha de chamadas de método se ocorrer uma exceção
            e.printStackTrace(); 
        }
    }


    /**
     * Retorna a representação em {@code String} da lista de aeroportos.
     *
     * @return Retorna uma {@code String}.
     */
    @Override
    public String toString()
    {
        return aeroportos.toString();
    }

}
