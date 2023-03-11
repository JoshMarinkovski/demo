package com.example.demo.unicorn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unicorns")
public class UnicornController {

    private final UnicornService unicornService;

    public UnicornController(UnicornService unicornService) {
        this.unicornService = unicornService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createUnicorn(@RequestBody Unicorn unicorn) {
        // create a new unicorn and return its ID
        Unicorn savedUnicorn = unicornService.createUnicorn(unicorn);

        Long unicornId = savedUnicorn.getId();

        Map<String, Long> response = new HashMap<>();
        response.put("unicornId", unicornId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Unicorn>> getAllUnicorns() {
        // get a list of all unicorns
        List<Unicorn> unicorns = unicornService.getAllUnicorns();
        return ResponseEntity.ok(unicorns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unicorn> getUnicornById(@PathVariable Long id) {
        // get a unicorn by its ID
        Unicorn unicorn = unicornService.getUnicornById(id);
        return ResponseEntity.ok(unicorn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unicorn> updateUnicorn(@PathVariable Long id, @RequestBody Unicorn unicorn) {
        // update a unicorn with a given ID
        Unicorn updatedUnicorn = unicornService.updateUnicorn(id, unicorn);
        return ResponseEntity.ok(updatedUnicorn);
    }
}
