/**
    A classe {@code Voos} representa os voos da classe {@code Aeroporto} entre dois aeroportos.

    @author Gabriel Caumo,
    @author Matheus Oliveira.
*/
public class Voos
{
    private int codigo;
    private int numero;


    /**
        Construtor da classe {@code Voos}.
        @param codigo representado por o codigo do {@code Aeroporto} de destino.
        @param numero uma representação/indentificação entre os dois aeroportos.
     */
    public Voos(int codigo, int numero)
    {
        this.codigo = codigo;  // Define o código do aeroporto de destino.
        this.numero = numero; // Define o número de identificação do voo.
    }


    /**
     * Retorna o codigo do voo.
     * @return Retorna um inteiro.
     */
    public int getCodigo()
    {
        return codigo; // Retorna o código do voo.
    }


    /**
     * Armazena/Altera o codigo pelo parametro obtido.
     * @param codigo altera o valor do 'codigo'.
     */
    public void setCodigo(int codigo)
    {
        this.codigo = codigo; // Define o código do voo pelo parâmetro fornecido.
    }


    /**
     * Retorna o número do voo.
     * @return Retorna um inteiro.
     */
    public int getNumero()
    {
        return numero; // Retorna o número do voo.
    }


    /**
     * Armazena/Altera o número pelo parametro obtido.
     * @param numero altera o valor do 'numero'.
     */
    public void setNumero(int numero)
    {
        this.numero = numero; // Define o número do voo pelo parâmetro fornecido.
    }


    /**
     * Representa um voo com os seus respectivos atributos.
     * @return Retorna uma {@code String}.
     */
    @Override
    public String toString() 
    {
        // Retorna uma string contendo os atributos do objeto 'voos'.
        return "" +
                "codigo: " + codigo +
                ", numero: " + numero;
    }


    /**
     Criando {@code Hashcode}. Reimplementando método obrigatório.
     @return  retorna um inteiro.
     */
    @Override
    public int hashCode()
    {

         // Calcula o 'hashcode' com base nos atributos do objeto 'voos'.
        int ret = 13;


        ret = 13 * ret + Integer.valueOf(this.codigo).hashCode();
        ret = 13 * ret + Integer.valueOf(this.numero).hashCode();


        if (ret < 0)  ret = -ret;
        return ret;
    }


    /**
     Verifica se dois objetos de mesma classe são iguais, ou seja, se duas coordenadas são iguais ou distintas.
     @return retorna um boolean.
     @param o objeto que será comparado na chamada do método e virá como parâmetro.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(this.getClass() != o.getClass()) return false;
        Voos voos = (Voos) o;

        if(this.codigo != (voos.codigo)) return false;
        if(this.numero != (voos.numero)) return false;

        return true;
    }

    /**
     Construtor de cópia para se fazer cópias de voos. Reimplementando método obrigatório.
     @param voos parâmetro para se fazer cópias, ao chamar o método 'clone' para algum objeto.
     @throws Exception caso um objeto da mesma classe não exista.
     */
    public Voos(Voos voos) throws Exception
    {
        // Verifica se o objeto passado é nulo.
        if(voos == null)
            throw new Exception("Modelo ausente");


        // Copia os atributos do objeto 'voos' passado.    
        this.codigo = voos.codigo;
        this.numero = voos.numero;
    }

    /**
     Criando uma cópia de um voo. Reimplementando método obrigatório.
     @return retorna {@code Object}, por padrão.
     */
    @Override
    public Object clone()
    {
        // Cria uma cópia do objeto 'voos'.
        Voos ret = null;
        try
        {
            ret = new Voos(this);
        }
        catch (Exception e)
        {} // Sei que não ocorrerá erro.
        return ret;
    }
}
