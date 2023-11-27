package br.com.pucminas.controledebiblioteca.controllers;


import br.com.pucminas.controledebiblioteca.dtos.*;
import br.com.pucminas.controledebiblioteca.entities.Biblioteca;
import br.com.pucminas.controledebiblioteca.entities.Cliente;
import br.com.pucminas.controledebiblioteca.entities.ItemBiblioteca;
import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;
import br.com.pucminas.controledebiblioteca.utils.MockItens;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/biblioteca")
public class BibliotecaController {

    private Biblioteca service;
    private MockItens mockItens;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BibliotecaController() throws IOException {
        this.service = Biblioteca.getInstance();
        this.mockItens = new MockItens(service);
        mockItens.preencheMockItens();
        mockItens.preencheMockClientes();
    }

    @PostMapping("/add-cliente")
    public ResponseEntity<AddClienteResponseDTO> createCliente(@RequestBody AddClienteRequestDTO requestDTO){
            service.criarCliente(requestDTO.getNome(), requestDTO.getCpf(), requestDTO.getCurso(), requestDTO.getInteresses());
            AddClienteResponseDTO resp = new AddClienteResponseDTO("Cliente criado com sucesso");
            return ResponseEntity.ok().body(resp);
    }

    @GetMapping(value = "/get-cliente/{cpf}")
    public ResponseEntity<AddClienteRequestDTO> obterCliente(@PathVariable String cpf){
        Cliente cliente = service.findClienteByCpf(cpf);
        AddClienteRequestDTO dto = new AddClienteRequestDTO(cliente);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/add-item")
    public ResponseEntity<AddTituloResponseDTO> criarTitulo(@RequestBody AddTituloResquestDTO resquestDTO){
        List<Generos> generosList = new ArrayList<>();
        for(String genStr : resquestDTO.getGenero()){
            Generos genero = Generos.valueOf(genStr);
            generosList.add(genero);
        }
        Itens tipo = Itens.valueOf(resquestDTO.getItens());
        this.service.criarItemBiblioteca(resquestDTO.getNome(), resquestDTO.getNomeAutor(), LocalDate.parse(resquestDTO.getDataPublicacao(), formatter), resquestDTO.getQuantidade(), tipo, generosList);
        AddTituloResponseDTO responseDTO = new AddTituloResponseDTO("Item da biblioteca criado com sucesso");
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/get-item")
    public ResponseEntity<AddTituloResquestDTO> obterTitulo(@RequestBody GetItemBibliotecaDTO reqDto){
        ItemBiblioteca item = service.getItemByNome(reqDto.getTitulo());
        AddTituloResquestDTO dtoResponse = new AddTituloResquestDTO(item.getNome(), item.getNomeAutor(), String.valueOf(item.getDataPublicacao()), item.getQuantidade(), item.getGeneros(), item.getTipo().getTipo());
        return ResponseEntity.ok().body(dtoResponse);
    }

    @PostMapping("/emprestar-item")
    public ResponseEntity<?> emprestarTitulo(@RequestBody EmprestarRequestDTO reqDto){
        String response = service.emprestar(LocalDate.parse(reqDto.getInicio(), formatter), LocalDate.parse(reqDto.getFim(), formatter), reqDto.getCpf(), reqDto.getTitulo());
        AddTituloResponseDTO responseDTO = new AddTituloResponseDTO(response);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/sugestao/{cpf}")
    public ResponseEntity<List<AddTituloResquestDTO>> recomendarTitulos(@PathVariable String cpf){
        List<ItemBiblioteca> itens = service.sugerirItens(cpf);
        List<AddTituloResquestDTO> response = new ArrayList<>();
        for(ItemBiblioteca item : itens){
            response.add(new AddTituloResquestDTO(item.getNome(), item.getNomeAutor(), String.valueOf(item.getDataPublicacao()), item.getQuantidade(), item.getGeneros(), item.getTipo().getTipo()));
        }
        return ResponseEntity.ok().body(response);
    }
}
