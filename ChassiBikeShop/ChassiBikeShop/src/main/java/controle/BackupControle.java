/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.IncrementoBKP;
import facade.BackupFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author marcos-ladeira
 */
@Named
@ViewAccessScoped
public class BackupControle implements Serializable {
        private IncrementoBKP backupBancoDados;
        @Inject
        private BackupFacade backupFacade;
        
    public void realizaBackup() throws IOException, InterruptedException, Exception {
        final List<String> comandos = new ArrayList<String>();
//        comandos.add("/opt/PostgreSQL/10/bin/pg_dump");
        comandos.add("/usr/lib/postgresql/11/bin/pg_dump");
        comandos.add("-h");
        comandos.add("localhost");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");
        comandos.add("/home/carlosgm/Documentos/Faculdade/TCC/Sistema/Backup/ciclismo.sql");
        comandos.add("ciclismo");
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "postgres");

        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();

            process.waitFor();
            realizaBackupRetaguarda();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage("Backup realizado com sucesso!"));
    }
    
     public void realizaBackupRetaguarda() throws IOException, InterruptedException, Exception {
        if (backupFacade.listaTodos().isEmpty()) {
            backupBancoDados = new IncrementoBKP();
            backupBancoDados.setId(1);
            backupBancoDados.setIncremento(1);
            backupFacade.salvar(backupBancoDados);
        }
        
        backupBancoDados = backupFacade.listaTodos().get(0);
        final List<String> comandos = new ArrayList<String>();
        comandos.add("/usr/lib/postgresql/11/bin/pg_dump");
        comandos.add("-h");
        comandos.add("localhost");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");
        comandos.add("/home/carlosgm/Documentos/Faculdade/TCC/Sistema/Backup/ciclismo"+backupBancoDados.getIncremento()+".sql");
        comandos.add("ciclismo");
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "postgres");
        
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while(line != null){
                System.err.println("line");
                line = r.readLine();
            }
            r.close();
            
            process.waitFor();
            backupBancoDados.setIncremento(1);
            backupFacade.salvar(backupBancoDados);
            
        } catch (IOException e) {
            e.printStackTrace();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
     
        public void realizaRestore() throws IOException, InterruptedException{
        final List<String> comandos = new ArrayList<String>();
//        comandos.add("/opt/PostgreSQL/10/bin/pg_restore");
        comandos.add("/usr/lib/postgresql/11/bin/pg_restore");
        comandos.add("-h");
        comandos.add("localhost");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-d");
        comandos.add("ciclismo");
        comandos.add("-c");
        comandos.add("-v");
        comandos.add("/home/carlosgm/Documentos/Faculdade/TCC/Sistema/Backup/ciclismo.sql");
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "postgres");
        
        try {
            final Process process = pb.start();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Backup restaurado com sucesso!"));
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while(line != null){
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            process.waitFor();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    
}