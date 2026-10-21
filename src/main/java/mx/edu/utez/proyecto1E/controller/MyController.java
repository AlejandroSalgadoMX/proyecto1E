package mx.edu.utez.proyecto1E.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.dtos.RequestBodyDTO;
import mx.edu.utez.proyecto1E.controller.dtos.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1E.controller.dtos.ResponseCalculadoraDTO;
import mx.edu.utez.proyecto1E.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"}) //Permitir cualquier origen
@RequestMapping("/my-services")
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping
    public String miPrimerServicio(){return "Hola";}

    @GetMapping("/segundo")
    public String segundoServicio(){return "Este es el servicio";}

    @PostMapping
    public String tercerServicio(){return "Tercero";}

    @GetMapping("/path/{id}")
    public String pathVariable(@PathVariable String id){return "path variable: "+id;}

    @PostMapping("/body")
    public ResponseEntity<RequestBodyDTO> body(@RequestBody @Valid RequestBodyDTO body) {

        System.out.println(body.getEdad());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(body);
    }

    @PostMapping("/calculadora")
    public ResponseEntity<ResponseCalculadoraDTO> calculadora (@RequestBody @Valid RequestCalculadoraDTO payload){

        return ResponseEntity
                .status(200)
                .body(
                        myService.calculadora(payload)
                );
    }
}
