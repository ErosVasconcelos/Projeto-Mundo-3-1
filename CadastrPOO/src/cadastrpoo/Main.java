package CadastrPOO;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        
        repo1.inserir(new PessoaFisica(1, "João Silva", "123.456.789-00", 30));
        repo1.inserir(new PessoaFisica(2, "Maria Souza", "987.654.321-00", 25));
        
        
        try {
            repo1.persistir("pessoasFisicas.dat");
        } catch (IOException e) {
            System.out.println("Erro ao persistir dados: " + e.getMessage());
        }
        
        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar("pessoasFisicas.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }

        List<PessoaFisica> pessoasFisicas = repo2.obterTodos();
        System.out.println("Pessoas Físicas recuperadas:");
        for (PessoaFisica pf : pessoasFisicas) {
            pf.exibir();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        
        repo3.inserir(new PessoaJuridica(1, "Empresa X", "12.345.678/0001-99"));
        repo3.inserir(new PessoaJuridica(2, "Empresa Y", "98.765.432/0001-88"));
        
        try {
            repo3.persistir("pessoasJuridicas.dat");
        } catch (IOException e) {
            System.out.println("Erro ao persistir dados: " + e.getMessage());
        }
        
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar("pessoasJuridicas.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
        
        List<PessoaJuridica> pessoasJuridicas = repo4.obterTodos();
        System.out.println("Pessoas Jurídicas recuperadas:");
        for (PessoaJuridica pj : pessoasJuridicas) {
            pj.exibir();
        }
    }
}
