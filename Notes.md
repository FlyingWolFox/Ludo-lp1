* public class Casa

variables:
- struct private Casas{
    Casa casaSeguinte;
    Casa casaSegura;
    Casa casaAnterior;
  };
- private Guarita guarita;   -> guarita da casa?
- private Peca peca          -> peça presente na casa;
- private int qtddePecas     -> para casas finais;
- private final String cor;

Constructor:
- Casa(String cor, Casa anterior);                 -> default
- Casa(String cor, Casa anterior, Guarita guarita) -> used

methods:
    public Peca getPeca();
    public Peca setPeca(Peca peca) -> null tira
    public int getQuantidadePecas()
    public void setQuantidadePecas(int quantidade) -> casas finais
    public void setCasaSeguinte(Casa seguinte)
    public void setCasaAnterior(Casa anterior)
    public void setCasaSegura(Casa casa) -> usada na entrada
    public Casa getCasaSeguinte()
    public Casa getCasaAnterior()
    public Casa getCasaSegura()
    public boolean ehEntradaZonaSegura()
    public boolean ehCasaFinal() 
    public boolean pertenceGuarita()
    public Guarita getGuarita()
    public String getCor()

* public class Dado -> dados
void rolar();   
int getValor();

* public class Guarita
    public Guarita(String cor)        _> construtor
    public Casa getCasa(int indice)   
    public int getNumeroDePecas()
    public Casa[] getTodasAsCasas()
    public String getCor() 

* public class Peca
  public Peca(String cor)               _>
    public String getCor()              -> Cor da peça
    public Casa getCasa()               -> Casa que está
    public void mover(Casa casaDestino) -> mover peça

* public class Tabuleiro

    // Armazena as casas de início das diferentes cores.
    private Casa casaInicioAmarelo;
    private Casa casaInicioAzul;
    private Casa casaInicioVerde;
    private Casa casaInicioVermelho;    
    
    // Armazena as guaritas do tabuleiro.
    private Guarita guaritaAmarelo;
    private Guarita guaritaAzul;
    private Guarita guaritaVerde;
    private Guarita guaritaVermelho;
    
    public Tabuleiro() {
        guaritaAmarelo = new Guarita("AMARELO");
        guaritaAzul = new Guarita("AZUL");
        guaritaVerde = new Guarita("VERDE");
        guaritaVermelho = new Guarita("VERMELHO");
        
        // Inicializamos um tabuleiro de Ludo
        
        // Casas de Inicio
        casaInicioAmarelo = new Casa("AMARELO");
        casaInicioAzul = new Casa("AZUL");
        casaInicioVerde = new Casa("VERDE");
        casaInicioVermelho = new Casa("VERMELHO");
        
        
        // Casas comuns
        // Usaremos uma espécie de lista encadeada informal para guardar as casas.
        popularCasas(casaInicioAmarelo, casaInicioVerde);
        popularCasas(casaInicioVerde, casaInicioVermelho);
        popularCasas(casaInicioVermelho, casaInicioAzul);
        popularCasas(casaInicioAzul, casaInicioAmarelo);
    }
    
    private void popularCasas(Casa primeiraCasa, Casa ultimaCasa)
    private void criarCasasZonaSegura(Casa casaEntradaZonaSegura, String cor)
    public Guarita getGuarita(String cor)
    public Casa getCasaInicio(String cor)

* public class Jogo
