package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /*
        HTTP POST /api/cliente/criar
        Operação na API para cadastrar um cliente
     */
    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody ClienteRequest request) {
        try {

            clienteService.cadastrarCliente(request);

            //HTTP 201 - CREATED
            return ResponseEntity.status(201).body("Cliente " + request.nome() + ", cadastrado com sucesso!");
        }
        catch (IllegalArgumentException e) {
            //HTTP 400 - BAD REQUEST
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            //HTTP 500 - INTERNAL SERVER ERROR
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /*
        HTTP GET /api/cliente/consulttar
        Operação na API para consultar os clientes
     */
    @GetMapping("consultar")
    public ResponseEntity<?> consultar(@RequestParam String nome) {
        try {

            var lista = clienteService.pesquisarClientes(nome);

            //HTTP 200 - OK
            return ResponseEntity.status(200).body(lista);
        }
        catch (IllegalArgumentException e) {
            //HTTP 400 - BAD REQUEST
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            //HTTP 500 - INTERNAL SERVER ERROR
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /*
       HTTP DELETE /api/cliente/excluir/{id}
       Operação na API para excluir o cliente
    */
    @DeleteMapping("excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            clienteService.excluirCliente(id);
            //HTTP 200 - OK
            return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
        }
        catch (IllegalArgumentException e) {
            //HTTP 400 - BAD REQUEST
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            //HTTP 500 - INTERNAL SERVER ERROR
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
