package br.com.pucminas.controledebiblioteca.dtos;


import br.com.pucminas.controledebiblioteca.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddClienteRequestDTO {
    private String cpf;
    private String curso;
    private String interesses;
    private String nome;

    public AddClienteRequestDTO(Cliente cliente){
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.curso = cliente.getCurso();
        this.interesses = cliente.getInteressesCliente().get(0).getGenero();
    }
}
