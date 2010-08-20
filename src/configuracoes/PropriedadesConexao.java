package configuracoes;

import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/** Classe interna que representa os dados de Conexao.properties. */
public class PropriedadesConexao {
    
    private String ip;
    
    private String porta;
    
    private String baseDados;
    
    public static final String ARQUIVO_CONEXAO = "Configuracoes"+File.separator+"conexao";
    
    /** Construtor. */
    public PropriedadesConexao() throws FileNotFoundException, IOException {
        
        //Gera um InputStream para o arquivo de conexao
        File conexao = new File(ARQUIVO_CONEXAO);
        BufferedReader bfr = new BufferedReader(new FileReader(conexao));
        
        // Lê as propriedades de conexão do arquivo.
        setIp(bfr.readLine());
        setPorta(bfr.readLine());
        setBaseDados(bfr.readLine());
        
        bfr.close();
        
    }
    
    /** Utiliza experessão regular para verificar se a string de entrada 
     *corresponde a um endereço de IP.
     *
     *@Retorno:
     *  - True: a string representa um IP
     *  - False: a string não representa um IP
     *
     */
    public static boolean validarIP(String parIP){
        
        String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String ip = "^(?:" + _255 + "\\.){3}" + _255 + "$";
        
        return Pattern.matches(ip, parIP);

    }
    
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    public String getPorta() {
        return porta;
    }
    
    public void setPorta(String porta) {
        this.porta = porta;
    }
    
        
    public String getBaseDados() {
        return baseDados;
    }
    
    public void setBaseDados(String baseDados) {
        this.baseDados = baseDados;
    }
    
    public void salvar() throws IOException {
        File file = new File(ARQUIVO_CONEXAO);
        file.delete();
        
        PrintStream printer = new PrintStream(ARQUIVO_CONEXAO);
        printer.println(ip);
        printer.println(porta);
        printer.println(baseDados);
        printer.close();
    }
    
    
}
