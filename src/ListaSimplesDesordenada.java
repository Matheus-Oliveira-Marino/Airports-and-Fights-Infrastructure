import java.lang.reflect.*;

/** 
* Classe que representa uma lista simplesmente encadeada desordenada.
* @param <X> Tipo de dado armazenado na lista.
*/
public class ListaSimplesDesordenada <X>
{

    /**
     * Classe interna que representa um nó da lista.
     */
    private class No
    {
        private X  info;
        private No prox;


        /**
         * Construtor da classe No.
         * @param i Dado a ser armazenado no nó.
         * @param p Próximo nó na lista.
         */
        public No (X i, No p)
        {
            // Atribui o dado passado como argumento ao campo 'info' do nó.
            this.info = i;

             // Atribui o próximo nó passado como argumento ao campo 'prox' do nó.
            this.prox = p;
        }


        /**
         * Construtor da classe No.
         * @param i Dado a ser armazenado no nó.
         */
        public No (X i)
        {
            this.info = i;
            this.prox = null;
        }


        /**
         * Obtém o dado armazenado no nó.
         * @return Dado armazenado no nó.
         */
        public X getInfo ()
        {
            return this.info;
        }


        /**
         * Obtém o próximo nó na lista.
         * @return Próximo nó na lista.
         */
        public No getProx ()
        {
            return this.prox;
        }

        /**
         * Define o dado a ser armazenado no nó.
         * @param i Dado a ser armazenado no nó.
         */
        public void setInfo (X i)
        {
            this.info = i;
        }


        /**
         * Define o próximo nó na lista.
         * @param p Próximo nó na lista.
         */
        public void setProx (No p)
        {
            this.prox = p;
        }
    } //fim da classe No

    // Variáveis representanddo o primeiro e último nó, respectivamente.
    private No primeiro, ultimo;


     /**
     * Construtor da classe {@code ListaSimplesDesordenada}.
     */
    public ListaSimplesDesordenada ()
    {
        this.primeiro = this.ultimo = null;
    }


    /**
     * Método privado para clonar um objeto do tipo 'X', se implementar a interface {@code Cloneable}.
     * @param x Objeto a ser clonado.
     * @return Cópia do objeto clonado.
     */
    private X meuCloneDeX (X x)
    {
        // Declaração e inicialização de uma variável 'ret' 
        // que armazenará o objeto clonado.
        X ret=null; 

        try
        {
            // Obtém a classe do objeto passado como argumento.
            Class<?> classe         = x.getClass();  

            // Declara um array de Classes 
            // que representará os parâmetros do método de clone.
            Class<?>[] tipoDosParms = null;

            // Obtém o método 'clone' da classe do objeto.
            Method metodo           = classe.getMethod("clone",tipoDosParms);

            // Declara um array de objetos 
            // que representará os parâmetros do método de clone.
            Object[] parms          = null;

            // Invoca o método 'clone' do objeto 
            // e atribui o resultado à variável 'ret'.
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {} // Captura exceção caso o método 'clone' não seja encontrado.
        catch(IllegalAccessException erro)
        {} // Captura exceção caso não seja possível acessar o método 'clone'.
        catch(InvocationTargetException erro)
        {}  // Captura exceção caso ocorra um erro de invocação do método 'clone'.

        // Retorna a cópia do objeto clonado.
        return ret;
    }


    /**
     * Adiciona um item no início da lista.
     * @param i Item a ser adicionado.
     * @throws Exception se o item for nulo.
     */
    public void guardeUmItemNoInicio (X i) throws Exception
    {
        // Verifica se o item passado como argumento é nulo.
        if (i==null)
            throw new Exception ("Informacao ausente");

        // Declara uma variável 'inserir' para armazenar o item a ser inserido
        X inserir = null;
    
        // Verifica se o item passado implementa a interface Cloneable
        if (i instanceof Cloneable)

        // Se implementar, chama o método 'meuCloneDeX' para clonar o item
        inserir = (X) meuCloneDeX(i);
        
        // Caso contrário, apenas atribui o item original.
        else inserir = i;

        // Cria um novo nó com o item a ser inserido e o coloca no início da lista encadeada.
        this.primeiro = new No(inserir, this.primeiro);

        // Verifica se o último elemento está vazio e, se sim, 
        // atualiza o último elemento para o recém-inserido.
        if (this.ultimo == null)
            this.ultimo = this.primeiro;
    }


    /**
     * Adiciona um item no final da lista.
     * @param i Item a ser adicionado.
     * @throws Exception se o item for nulo.
     */
    public void guardeUmItemNoFinal (X i) throws Exception
    {
        // Verifica se o item passado como argumento é nulo.
        if (i == null)
        throw new Exception("Informacao ausente");

        // Declara uma variável 'inserir' para armazenar o item a ser inserido.
        X inserir = null;

        // Verifica se o item passado implementa a interface 'Cloneable'.
        if (i instanceof Cloneable)
            // Se implementar, chama o método 'meuCloneDeX' para clonar o item.
            inserir = (X) meuCloneDeX(i);
        else
            // Caso contrário, apenas atribui o item original.
            inserir = i;

        // Verifica se a lista está vazia.
        if (this.ultimo == null) 
        {
            // Se estiver vazia, cria um novo nó com o item a ser inserido 
            // e define tanto o primeiro quanto o último nó como este novo nó.
            this.ultimo = new No(inserir);
            this.primeiro = this.ultimo;
        } else 
        {
            // Se não estiver vazia, adiciona um novo nó contendo o item ao final da lista, 
            // e atualiza o último nó para ser este novo nó.
            this.ultimo.setProx(new No(inserir));
            this.ultimo = this.ultimo.getProx();
        }
    }
    


    /**
     * Recupera o item no início da lista.
     * @return Item no início da lista.
     * @throws Exception se a lista estiver vazia.
     */
    public X recupereItemDoInicio () throws Exception
    {
        if (this.primeiro==null/*&&this.fim==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);

        return ret;
    }


    /**
     * Recupera o item no final da lista.
     * @return Item no final da lista.
     * @throws Exception se a lista estiver vazia.
     */
    public X recupereItemDoFinal () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);

        return ret;
    }


    /**
     * Remove o item no início da lista.
     * @throws Exception se a lista estiver vazia.
     */
    public void removaItemDoInicio () throws Exception
    {
        if (this.primeiro==null /*&& this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
    }


    /**
     * Remove o item no final da lista.
     * @throws Exception se a lista estiver vazia.
     */
    public void removaItemDoFinal () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        No atual;
        for (atual=this.primeiro; atual.getProx()!=this.ultimo; atual=atual.getProx())/*comando vazio*/;


        atual.setProx(null);
        this .ultimo=atual;
    }


    /**
     * Obtém a quantidade de elementos na lista.
     * @return Quantidade de elementos na lista.
     */
    public int getQuantidade ()
    {
        No  atual=this.primeiro;
        int ret  =0;

        while (atual!=null)
        {
            ret++;
            atual = atual.getProx();
        }

        return ret;
    }


    /**
     * Verifica se a lista contém um item específico.
     * @param i O item a ser verificado.
     * @return true se o item está na lista, false caso contrário.
     * @throws Exception se o item passado for nulo.
     */
    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        No atual=this.primeiro;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo()))
                return true;

            atual = atual.getProx();
        }

        return false;
    }

    /**
     * Remove o item em um determinado índice na lista.
     * @param i O índice do item a ser removido.
     * @throws Exception se o índice for menor ou igual a zero, se a lista estiver vazia
     * ou se o índice for inválido.
     */
    public void removaItemIndice(int i) throws Exception
    {
        if (i <= 0)
        {
            // Lança uma exceção se o índice for menor ou igual a zero.
            throw new Exception("Informação ausente");
        }

        
        if (i == 1)
        {
            // Remoção do primeiro nó.
            if (primeiro == null)
            {   
                // Lança uma exceção se a lista estiver vazia.
                throw new Exception("Lista vazia");
            }

            // Atualiza o primeiro nó para o próximo nó.
            primeiro = primeiro.getProx();
        }
        
        else
        {
            
            No anterior = null; // Declaração de um nó anterior. 
            No atual = primeiro;  // Inicializa o nó atual como o primeiro nó da lista.
            int cont = 1; // Contador para acompanhar a posição do nó na lista.

            while (atual != null && cont != i)
            {
                anterior = atual; // Atualiza o nó anterior.
                atual = atual.getProx(); // Atualiza o nó atual para o próximo nó.
                cont++; // Incrementa o contador.
            }

            if (atual == null) {
                throw new Exception("Índice inválido"); // Lança uma exceção se o índice for inválido.
            }
    
            // Atualiza a referência do próximo nó do nó anterior para o próximo do nó atual.
            anterior.setProx(atual.getProx());  
        }
    }


    /**
     * Remove um item específico da lista.
     * @param i O item a ser removido.
     * @throws Exception se o item passado for nulo ou se ele não existir na lista.
     */
    public void removaItemIndicado (X i) throws Exception
    {
        if (i == null)
        throw new Exception("Informacao ausente"); // Lança uma exceção se o item passado for nulo.

    boolean removeu = false; // Flag para indicar se um item foi removido.

    // Loop infinito até encontrar o item ou a lista se tornar vazia.
    for (;;) {
        if (this.primeiro == null)
            break; // Sai do loop se a lista estiver vazia.

        // Verifica se o item é igual ao primeiro item da lista.
        if (!i.equals(this.primeiro.getInfo()))
            break; // Sai do loop se o item não for igual ao primeiro item.

        // Atualiza a referência do último se for o único item na lista.
        if (this.ultimo == this.primeiro)
            this.ultimo = null;

        // Atualiza a referência do primeiro para o próximo nó.
        this.primeiro = this.primeiro.getProx();

        removeu = true; // Indica que um item foi removido.
    }

    // Se ainda houver itens na lista.
    if (this.primeiro != null) 
    {
        No atual = this.primeiro;

        forever: for (;;) // Loop infinito para percorrer a lista.
        {
            if (atual.getProx() == null)
                break; // Sai do loop se atingir o final da lista.

            // Enquanto o próximo item for igual ao item a ser removido.
            while (i.equals(atual.getProx().getInfo())) 
            {
                // Atualiza a referência do último se o item a ser removido for o último.
                if (this.ultimo == atual.getProx())
                    this.ultimo = atual;

                // Atualiza a referência do próximo nó para o próximo do próximo nó.
                atual.setProx(atual.getProx().getProx());

                removeu = true; // Indica que um item foi removido.

                // Sai do loop infinito se o último nó for alcançado.
                if (atual == this.ultimo)
                    break forever;
            }

            atual = atual.getProx(); // Atualiza o nó atual para o próximo nó.
        }
    }

        // Lança uma exceção se nenhum item foi removido.
        if (!removeu)
            throw new Exception ("Informacao inexistente");
    }


    /**
     * Verifica se a lista está vazia.
     * @return {@code true} se a lista está vazia, {@code false} caso contrário.
     */
    public boolean isVazia ()
    {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }


    /**
     * Retorna uma representação em formato de string da lista.
     * @return Uma string contendo os elementos da lista.
     */
    public String toString() 
    {
        String ret = "["; // Inicializa a string de retorno com '['.
    
        No atual = this.primeiro; // Inicializa o nó atual com o primeiro nó da lista.
    
        // Loop para percorrer todos os nós da lista.
        while (atual != null) {
            ret = ret + atual.getInfo(); // Concatena o valor do nó atual à string de retorno.
    
            // Adiciona uma vírgula à string de retorno se o nó atual não for o último.
            if (atual != this.ultimo)
                ret = ret + ",";
    
            atual = atual.getProx(); // Atualiza o nó atual para o próximo nó da lista.
        }
    
        return ret + "]"; // Retorna a string de retorno com ']' no final.
    }


    /**
     * Verifica se esta lista é igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return true se o objeto passado é igual a esta lista, false caso contrário.
     */
    public boolean equals(Object obj) {
        if (this == obj) // Verifica se os objetos são idênticos (mesma referência na memória).
            return true;
    
        if (obj == null) // Verifica se o objeto passado é nulo.
            return false;
    
        if (this.getClass() != obj.getClass()) // Verifica se os objetos são de classes diferentes.
            return false;
    
        // Faz o 'cast' do objeto para 'ListaSimplesDesordenada'.    
        ListaSimplesDesordenada<X> lista = (ListaSimplesDesordenada<X>) obj; 
    
        No atualThis = this.primeiro; // Inicializa o nó atual da lista atual.
        No atualLista = lista.primeiro; // Inicializa o nó atual da lista passada como parâmetro.
    
        // Loop para percorrer os nós das duas listas e comparar os valores.
        while (atualThis != null && atualLista != null) 
        {
            // Verifica se os valores dos nós atuais são diferentes.
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;
    
            atualThis = atualThis.getProx(); // Avança para o próximo nó da lista atual.
            atualLista = atualLista.getProx(); // Avança para o próximo nó da lista passada como parâmetro.
        }
    
        // Verifica se ainda restam elementos em alguma das listas.
        if (atualThis != null || atualLista != null)
            return false;
    
        // Verifica se atualThis é nulo.    
        if (atualThis!=null)
            return false;
        
        // Verifica se atualLista é nulo.    
        if (atualLista!=null)
            return false;

        // Retorna true se as listas forem iguais em seus elementos.
        return true;
    }


    /**
     * Retorna o código hash da lista.
     * @return O código hash da lista.
     */
    public int hashCode ()
    {
        final int PRIMO = 13; // qualquer número primo serve

        int ret=666; // qualquer inteiro positivo serve

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
            ret = PRIMO*ret + atual.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }


    /**
     * Construtor de cópia. Cria uma nova instância da lista a partir de outra lista.
     * @param modelo A lista a ser copiada.
     * @throws Exception se a lista passada for nula.
     */
    public ListaSimplesDesordenada (ListaSimplesDesordenada<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=null)
        {
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        this.ultimo = atualDoThis;
    }


    /**
     * Cria e retorna uma cópia desta lista.
     * @return Uma cópia desta lista.
     */
    public Object clone ()
    {
        ListaSimplesDesordenada<X> ret=null;

        try
        {
            ret = new ListaSimplesDesordenada (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }


    /**
     * Retorna o item na posição especificada na lista.
     * @param i A posição do item desejado.
     * @return O item na posição especificada.
     * @throws Exception se a posição for menor ou igual a zero ou se a posição for inválida.
     */
    public X get(int i) throws Exception 
    {
        No atual = this.primeiro; // Inicializa o nó atual como o primeiro nó da lista.
        int cont = 0; // Inicializa o contador como zero.
    
        if (i <= 0) // Verifica se a posição é menor ou igual a zero.
            throw new Exception("Posicao inexistente"); // Lança uma exceção informando que a posição é inválida.
    
        // Loop para percorrer os nós da lista até encontrar o nó na posição desejada.
        while (atual != null) {
            // Verifica se o contador é igual à posição desejada menos um (devido ao índice começar em zero).
            if (cont == i - 1)
                return atual.getInfo(); // Retorna o item armazenado no nó atual.
    
            cont++; // Incrementa o contador.
            atual = atual.getProx(); // Avança para o próximo nó da lista.
        }
    
        throw new Exception("Posicao inexistente"); // Lança uma exceção se a posição não for encontrada.
    }

   
    /**
     * Retorna a quantidade de ocorrências de um elemento na lista.
     * @param valor O elemento a ser contado na lista.
     * @return O número de ocorrências do elemento na lista.
     * @throws Exception se o valor passado como parâmetro estiver nulo.
     */
    public int qtdElemento(X valor) throws Exception {
        No atual = this.primeiro; // Inicializa o nó atual como o primeiro nó da lista.
        int ret = 0; // Inicializa o contador de ocorrências como zero.
    
        if (valor == null) // Verifica se o valor passado como parâmetro é nulo.

            // Lança uma exceção informando que o valor é nulo.
            throw new Exception("Valor passado como parametro esta nulo"); 
    
        // Loop para percorrer os nós da lista e contar as ocorrências do elemento desejado.
        for (atual = this.primeiro; atual != null; atual = atual.getProx()) 
        {
            // Verifica se o item armazenado no nó atual é igual ao valor desejado.
            if (atual.getInfo() == valor)
                ret++; // Incrementa o contador de ocorrências.
        }
    
        return ret; // Retorna o número total de ocorrências do elemento na lista.
    }



    /**
     * Retorna o total de elementos na lista.
     * @return O número total de elementos na lista.
     */
    public int totalDaLista() 
    {
        No atual = this.primeiro; // Inicializa o nó atual como o primeiro nó da lista.
        int cont = 0; // Inicializa o contador de elementos como zero.
    
        // Loop para percorrer os nós da lista e contar os elementos.
        for (atual = this.primeiro; atual != null; atual = atual.getProx()) 
        {
            cont++; // Incrementa o contador de elementos.
        }
    
        return cont; // Retorna o número total de elementos na lista.
    }



    /**
     * Inverte a ordem dos elementos na lista.
     * @return Uma nova lista contendo os elementos invertidos.
     */
    public ListaSimplesDesordenada<X> inverte() 
    {

        No atual = this.primeiro; // Inicializa o nó atual como o primeiro nó da lista.

        // Cria uma nova lista vazia para armazenar os elementos invertidos.
        ListaSimplesDesordenada<X> novo = new ListaSimplesDesordenada<>(); 

        // Loop para percorrer os nós da lista 
        // e adicionar os elementos invertidos à nova lista.
        while (atual != null) 
        {
            try {
                    // Adiciona o elemento do nó atual no início da nova lista.
                    novo.guardeUmItemNoInicio(atual.getInfo()); 
                } 
            catch (Exception e) 
            {
                // Lança uma exceção caso ocorra um erro ao adicionar o elemento.
                throw new RuntimeException(e); 
            }
            atual = atual.getProx(); // Move para o próximo nó na lista original.
        }

        return novo; // Retorna a nova lista contendo os elementos invertidos.
    }



    /**
     * Inverte a ordem dos elementos na lista atual.
     */
    public void inverteSimples()
    {
        ListaSimplesDesordenada<X> novo = this.inverte();

        this.primeiro = novo.primeiro;
        this.ultimo = novo.ultimo;

    }


    /**
     * Adiciona os elementos de outra lista à lista atual.
     * @param lista A lista cujos elementos serão adicionados.
     * @throws Exception se ocorrer algum erro durante a adição.
     */
    public void adicionarLista(ListaSimplesDesordenada<X> lista) throws Exception
    {
        // Chama o método inverte para obter uma nova lista com os elementos invertidos.
        ListaSimplesDesordenada<X> novo = adicionarList(lista);

        // Atualiza o primeiro nó da lista atual com o primeiro nó da lista invertida.
        this.primeiro = novo.primeiro;

        // Atualiza o último nó da lista atual com o último nó da lista invertida.
        this.ultimo = novo.ultimo;
    }


    /**
     * Cria uma nova lista contendo os elementos desta lista e os elementos de outra lista.
     * @param lista A lista cujos elementos serão adicionados.
     * @return Uma nova lista contendo os elementos das duas listas.
     */
    public ListaSimplesDesordenada<X> adicionarList(ListaSimplesDesordenada<X> lista)
    {
        No atual = this.primeiro;
        No list;

        // Cria uma nova lista para armazenar os elementos combinados.
        ListaSimplesDesordenada<X> nova = new ListaSimplesDesordenada<>();

        try
        {
            while (atual != null)
            {
                // Adiciona os elementos da lista atual à nova lista.
                nova.guardeUmItemNoFinal(atual.getInfo());
                atual = atual.getProx();
            }

            for(list = lista.primeiro; list != null; list = list.getProx())
            {
                // Adiciona os elementos da lista passada como parâmetro à nova lista.
                nova.guardeUmItemNoFinal(list.getInfo());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nova;
    }


    /**
     * Retorna uma nova lista contendo os elementos que estão presentes em ambas as listas.
     * @param outra A outra lista a ser comparada.
     * @return Uma nova lista contendo os elementos comuns entre as duas listas.
     */
    public ListaSimplesDesordenada<X> exer7(ListaSimplesDesordenada<X> outra)
    {
        ListaSimplesDesordenada<X> nova = new ListaSimplesDesordenada<>();
        No atual = this.primeiro;
        No outro = outra.primeiro;

        try
        {
            while (outro != null)
            {
                // Verifica se o elemento da outra lista está presente nesta lista.
                if (this.tem(outro.getInfo()))

                    // Verifica se o elemento já foi adicionado à nova lista.
                    if(!nova.tem(outro.getInfo()))

                        // Adiciona o elemento à nova lista.
                        nova.guardeUmItemNoFinal(outro.getInfo());

                outro = outro.getProx();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return nova;
    }



    /**
     * Retorna uma sublista contendo os elementos entre os índices especificados.
     * @param i O índice inicial (inclusive) da sublista.
     * @param f O índice final (inclusive) da sublista.
     * @return Uma nova lista contendo os elementos da sublista.
     * @throws Exception se os índices forem inválidos.
     */
    public ListaSimplesDesordenada<X> subLista(int i, int f) throws Exception
    {
        // Cria uma nova lista para armazenar a sublista.
        ListaSimplesDesordenada<X> nova = new ListaSimplesDesordenada<>();

        No atual;
        int ret = 1; // Inicializa um contador para acompanhar o índice atual.

        // Verifica se os índices de início e fim são válidos.
        if(i == this.totalDaLista() + 1 || i <= 0 || i > f)
            throw new Exception("Valor inicial invalido");

        // Verifica se o valor do índice final f é inválido.     
        if(f == this.totalDaLista() + 1 || f <= 0 || f > i)
            throw new Exception("Valor final invalido");

        // Percorre a lista original.    
        for (atual = this.primeiro; atual != null; atual = atual.getProx())
        {
            // Verifica se o índice atual está dentro do intervalo especificado.
            if(ret >= i && ret <= f)
                nova.guardeUmItemNoFinal(atual.getInfo());  // Adiciona o elemento à sublista.

            // Incrementa o contador de índices.    
            ret++;
        }

        // Retorna a sublista.
        return nova;
    }


    /**
     * Duplica os elementos da lista atual em uma nova lista.
     * @return Uma nova lista contendo os elementos duplicados.
     */
    public ListaSimplesDesordenada<X> duplicar()
    {
        ListaSimplesDesordenada<X> novo = new ListaSimplesDesordenada<>();

        No atual = this.primeiro;
        No copia = this.primeiro;

        try
        {
            while (atual != null)
            {
                novo.guardeUmItemNoFinal(atual.getInfo()); // Adiciona o elemento à nova lista.
                atual = atual.getProx();
            }

            atual = copia;
            while (atual != null)
            {
                novo.guardeUmItemNoFinal(atual.getInfo()); // Adiciona o elemento duplicado à nova lista.
                atual = atual.getProx();
            }
        }
        catch (Exception e){e.printStackTrace();}

        return novo;
    }

}
