/**
 *  A classe Aeroporto representa um simples modelo de um aeroporto
 *  Contendo uma identificação, sigla e o nome
 *
 * @author Gabriel Caumo,
 * @author Matheus Oliveira.
 */

public class Aeroporto
{
    private int codigo;
    private String sigla;
    private  String nomeCidade;
    private ListaSimplesDesordenada<Voos> voos;

    /**
     * Construtor da classe Aeroporto para representar um aeroporto
     * @param c Codigo que representa a identificação do aeroporto
     * @param sigla Sigla que representa a sigla do aeroporto
     * @param nome Nome que representa o nome do aeroporto
     *
     * @throws Exception Caso o codigo for menor ou igual a zero, ou o tamanho da sigla for diferente de 3
     *      * e por fim se a sigla contiver numeros
     *
     */
    public Aeroporto(int c, String sigla, String nome) throws Exception
    {
        // Verifica se o tamanho da sigla é diferente de 3.
        if (sigla.length() != 3)
        {
            throw new IllegalArgumentException("Sigla deve conter até 3 letras.");
        }

        // Verifica se o código é menor ou igual a zero.
        if(c <= 0)
        {
            throw new Exception("Codigo deve ser um valor positivo");
        }

        // Verifica se a sigla contém apenas letras.
        if (!sigla.matches("[a-z A-Z]+"))
        {
            throw new IllegalArgumentException("Sigla não pode conter números.");
        }

        // Inicializa os atributos com os valores passados.
        this.codigo = c;
        this.sigla = sigla.toUpperCase();
        this.nomeCidade = nome;
        this.voos = new ListaSimplesDesordenada<>();
    }


    // Método que retorna o código.
    public int getCodigo() {return codigo;}


    /**
     * Define o código do aeroporto.
     *
     * @param codigo O novo código do aeroporto.
     */
    public void setCodigo(int codigo) {this.codigo = codigo;}


    /**
     * Retorna a sigla do aeroporto.
     *
     * @return A sigla do aeroporto.
     */
    public String getSigla() {return sigla;}


    /**
     * Define a sigla do aeroporto.
     *
     * @param sigla A nova sigla do aeroporto.
     */
    public void setSigla(String sigla) {this.sigla = sigla;}


    /**
     * Retorna o nome da cidade do aeroporto.
     *
     * @return O nome da cidade do aeroporto.
     */
    public String getNomeCidade() {return nomeCidade;}


    /**
     * Define o nome da cidade do aeroporto.
     *
     * @param nomeCidade O novo nome da cidade do aeroporto.
     */
    public void setNomeCidade(String nomeCidade) {this.nomeCidade = nomeCidade;}


    /**
     * Retorna a lista de voos associada ao aeroporto.
     *
     * @return A lista de voos associada ao aeroporto.
     */
    public ListaSimplesDesordenada<Voos> getVoos() {return voos;}


    /**
     * Define a lista de voos associada ao aeroporto.
     *
     * @param voos A nova lista de voos associada ao aeroporto.
     */
    public void setVoos(ListaSimplesDesordenada<Voos> voos) {this.voos = voos;}

    /**
     * Retorna uma representação em String do objeto Aeroporto.
     *
     * @return Uma String contendo os valores dos atributos do Aeroporto.
     */
    @Override
    public String toString() {
        return " Codigo: " + codigo +
                ", sigla: " + sigla +
                ", Cidade: " + nomeCidade +
                ", voos: " + voos + "\n";
    }


    /**
     * Calcula o código de hash do objeto Aeroporto.
     *
     * @return O código de hash calculado.
     */
    @Override
    public int hashCode()
    {
        int ret = 13;


        ret = 13 * ret + Integer.valueOf(this.codigo).hashCode();
        ret = 13 * ret + String.valueOf(this.sigla).hashCode();
        ret = 13 * ret + String.valueOf(this.nomeCidade).hashCode();


        if (ret < 0)  ret = -ret;
        return ret;
    }

    /**
     * Verifica se o objeto Aeroporto é igual a outro objeto.
     *
     * @param o O objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o)
    {
        // Compara o chamante do método(this) com o objeto. Retorna 'true' se verdadeiro.
        if (this == o) return true;

        // Verifica se o objeto passado é nulo ou se não é uma instância da classe 'Aeroporto'. 
        // Se sim, não são iguais.
        if (o == null || getClass() != o.getClass()) return false;

         // Verifica se o chammante do método(this) e o objeto são de classes diferentes. 
         // Se sim, não são iguais.
        if(this.getClass() != o.getClass()) return false;

        // Converte o objeto passado para a classe Aeroporto para comparar seus atributos.
        Aeroporto aeroporto = (Aeroporto) o;

        // Compara os códigos dos aeroportos. Se diferentes, não são iguais.
        if(this.codigo != (aeroporto.codigo)) return false;

        // Compara as siglas dos aeroportos. Se diferentes, não são iguais.
        if(this.sigla != (aeroporto.sigla)) return false;

        // Compara os nomes das cidades dos aeroportos. Se diferentes, não são iguais.
        if(this.nomeCidade != (aeroporto.nomeCidade)) return false;

        // Compara as listas de voos dos aeroportos. Se diferentes, não são iguais.
        if(this.voos != (aeroporto.voos)) return false;

        // Se todas as comparações anteriores passaram, os objetos são iguais.
        return true;
    }

    /**
     Construtor de cópia para se fazer cópias de aeroporto. Reimplementando método obrigatório.
     @param aeroporto parâmetro para se fazer cópias, ao chamar o método 'clone()' para algum objeto.
     @throws Exception caso um objeto da mesma classe não exista.
     */
    public Aeroporto(Aeroporto aeroporto) throws Exception
    {
        if(aeroporto == null)
            throw new Exception("Valor ausente");

        this.codigo = aeroporto.codigo;
        this.sigla = aeroporto.sigla;
        this.nomeCidade = aeroporto.nomeCidade;
        this.voos = aeroporto.voos;
    }

    /**
     Criando uma cópia de um aeroporto. Reimplementando método obrigatório.
     @return retorna Object, por padrão.
     */
    @Override
    public Object clone() // Deve retornar um object.
    {
        Aeroporto ret = null;
        try
        {
            ret = new Aeroporto(this);
        }
        catch (Exception erro)
        {}
        return ret;
    }
}
