public class Main {

    public static void main(String[] args) throws Exception{

        ListaSimplesDesordenada<String> lista = new ListaSimplesDesordenada<String>();
        lista.guardeUmItemNoInicio("Caio");
        lista.guardeUmItemNoInicio("Chiqueto");
        lista.guardeUmItemNoInicio("Caio");
        lista.guardeUmItemNoInicio("Caio");
        lista.guardeUmItemNoInicio("Caio");

        lista.qtdElementos("chiqueto");
    }
    
}
