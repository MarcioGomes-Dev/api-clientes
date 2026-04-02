package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody ClienteRequest request) {
        try{
            var clienteService = new ClienteService();
            clienteService.cadastrarCliente(request);

            return ResponseEntity.status(201).body("Cliente " + request.nome() + ", cadastrado com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("consultar")
    public ResponseEntity<?> consultar(@RequestParam String nome) {
        try{
            var clienteService = new ClienteService();
            var lista = clienteService.pesquisarClientes(nome);

            return ResponseEntity.status(200).body(lista);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
