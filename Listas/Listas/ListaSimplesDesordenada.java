import java.lang.reflect.*;
import java.util.*;

public class ListaSimplesDesordenada <X>
{
    private class No
    {
        private X  info;
        private No prox;

        public No (X i, No p) 
        {
            this.info = i;
            this.prox = p;
        }

        public No (X i)
        {
            this.info = i;
            this.prox = null;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
        }
    } //fim da classe No

    private No primeiro, ultimo;
    private int tamanho = 0;

    public ListaSimplesDesordenada ()
    {
        this.primeiro=this.ultimo=null;
    }

    private X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }

    public void guardeUmItemNoInicio (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        this.primeiro = new No (inserir,this.primeiro);

        if (this.ultimo==null)
            this.ultimo=this.primeiro;

        tamanho++;
    }

    public void guardeUmItemNoFinal (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        if (this.ultimo==null) // && this.primeiro==null
        {
            this.ultimo   = new No (inserir);
            this.primeiro = this.ultimo;
        }
        else
        {
            this.ultimo.setProx (new No (inserir));
            this.ultimo = this.ultimo.getProx();
        }

        tamanho++;
    }
    
    public X recupereItemDoInicio () throws Exception
    {
        if (this.primeiro==null)//&&this.fim==null
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public X getIezimo (int i) throws Exception{

        if(i < 0)
            throw new Exception("Posicao invalida");
        
        No atual = this.primeiro;
        int posicao = 0;

        for(;;) // for ever | ou seja ele só parará com um break, return ou com uma Exception
        {
            if (atual == null){
                throw new Exception("Posicao invalida");
            }

            if (posicao == i){
                if(atual.getInfo() instanceof Cloneable)
                return meuCloneDeX(atual.getInfo());
            }
            else{
                return atual.getInfo();
            }

            posicao++; 
            atual = atual.getProx();
        }        
    }    

    public X recupereItemDoFinal () throws Exception
    {
        if (this.primeiro==null)//&&this.ultimo==null
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }   

    public void removaItemDoInicio () throws Exception
    {
        if (this.primeiro==null)//&& this.ultimo==null
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        this.primeiro = this.primeiro.getProx();

        tamanho--;
    }    

    public void removaItemDoFinal () throws Exception
    {
        if (this.primeiro==null)//&&this.ultimo==null
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        No atual;
        for (atual=this.primeiro;
             atual.getProx()!=this.ultimo;
             atual=atual.getProx())
             //comando vazio;

        atual.setProx(null);
        this .ultimo=atual;

        tamanho--;
    }

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
	
	public void removaItemIndicado (X i) throws Exception
	{
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removeu=false;

        for(;;) // FOR EVER (repete até break)
        {
            if (this.primeiro==null)//&&this.ultimo==null
                break;

            if (!i.equals(this.primeiro.getInfo()))
                break;
                
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro=this.primeiro.getProx();

            removeu=true;
        }

        if (this.primeiro!=null)//&&this.ultimo!=null
        {
            No atual=this.primeiro;

            forever:for(;;) // repete ate break
            {
                if (atual.getProx()==null)
                    break;

                while (i.equals(atual.getProx().getInfo()))
                {
                    if (this.ultimo==atual.getProx())
                        this.ultimo=atual;

                    atual.setProx(atual.getProx().getProx());

                    removeu=true;

                    if (atual==this.ultimo)
                        break forever;
                }

                atual=atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception ("Informacao inexistente");
	}

    public boolean isVazia ()
    {
        return this.primeiro==null;//&&this.ultimo==null
    }
    
    public String toString ()
    {
        String ret="[";

        No atual=this.primeiro;

        while (atual!=null)
        {
            ret=ret+atual.getInfo();

            if (atual!=this.ultimo)
                ret=ret+",";

            atual=atual.getProx();
        }

        return ret+"]";
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaSimplesDesordenada<X> lista =
       (ListaSimplesDesordenada<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=null && atualLista!=null)
        {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }

        if (atualThis!=null  /* && atualLista==null */)
            return false;

        if (atualLista!=null /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }
    
    public int hashCode ()
    {
        final int PRIMO = 13; // qualquer número primo serve
        
        int ret=12; // qualquer inteiro positivo serve

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
             ret = PRIMO*ret + atual.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }
    
    // construtor de copia
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

    //exercicio 1
    public int qtdElementos(X encontrar) throws Exception
    {  
        No atual = this.primeiro;                
        int contador = 0;       
        
        while(atual!=null)
        {           
            if(atual.getInfo() == encontrar){                                               
                contador++;
            }      
            atual = atual.getProx();
        }
       return contador;            
    }

    //exercicio 2
    public int getTamanho(){
        //com uma variavel criada e sendo acrescentada +1 quando adiciona valor 
        //-1 quando remove um valor
        return this.tamanho;
    }
    
    //exercicio 3
    public ListaSimplesDesordenada<X> inverteLista () throws Exception
    {
        No atual = this.primeiro;
        ListaSimplesDesordenada<X> listaInversa = new ListaSimplesDesordenada<X>();        

        while(atual!=null){
            listaInversa.guardeUmItemNoFinal(atual.getInfo());
            atual = atual.getProx();
        }
        return listaInversa;
    }

    //exercicio 4 - com erro
    public ListaSimplesDesordenada<X> inverteListaSemNew(ListaSimplesDesordenada lista) throws Exception
    {        
        No atual = this.primeiro;
        No anterior=null;  
        No prox=null;

        while(atual!=null){
            prox = atual.getProx();
            
            atual.setProx(anterior);
            
            anterior = atual;
            
            atual = prox;
            
            lista.guardeUmItemNoInicio(anterior.getInfo());
            
        }
        this.primeiro = anterior;
 
        return lista;
        
    }

    //exercicio 5

}