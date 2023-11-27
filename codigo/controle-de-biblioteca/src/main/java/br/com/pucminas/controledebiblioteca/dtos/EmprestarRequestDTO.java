package br.com.pucminas.controledebiblioteca.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmprestarRequestDTO {
    private String titulo;
    private String inicio;
    private String fim;
    private String cpf;
}
