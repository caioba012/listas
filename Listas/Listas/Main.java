public class Main {

    public static void main(String[] args) throws Exception{

        try{
            ListaSimplesDesordenada<String> lista = new ListaSimplesDesordenada<String>();
            lista.guardeUmItemNoInicio("Caio");
            lista.guardeUmItemNoInicio("Chiqueto");
            lista.guardeUmItemNoInicio("Caio");
            lista.guardeUmItemNoInicio("Caio");
            lista.guardeUmItemNoInicio("Caio");

            System.out.println(lista.inverteListaSemNew(lista));
            
        }
        catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }
    }
    
}