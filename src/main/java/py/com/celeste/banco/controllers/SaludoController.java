package py.com.celeste.banco.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public Map<String, String> saludo(){
        return Map.of("Mensaje", "El Banco Celeste tá ON ");
    }

    @GetMapping("saludo-personalizado")
    public Map<String, String> saludoPersonalizado(@RequestParam String nombre){
        return Map.of("mensaje", "Bienvenido a Banco Celeste, " + nombre);
    }

    @GetMapping("/old/clientes/{id}")
    public Map<String, Object> obtenerCliente(@PathVariable Long id) {
        return Map.of(
                "id", id,
                "estado", "ACTIVO"
        );
    }

    @GetMapping("/old/clientes/{id}/estado")
    public ResponseEntity<Map<String, Object>> estadoCliente(@PathVariable Long id) {

        if (id <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "ID inválido"));
        }

        return ResponseEntity.ok(
                Map.of(
                        "id", id,
                        "estado", "ACTIVO"
                )
        );
    }


}
