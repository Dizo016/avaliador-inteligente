    package com.ucsal.avaliador_inteligente.controller;

    import com.ucsal.avaliador_inteligente.dto.ProvaRequestDTO;
    import com.ucsal.avaliador_inteligente.dto.ProvaIARequestDTO;
    import com.ucsal.avaliador_inteligente.model.Prova;
    import com.ucsal.avaliador_inteligente.model.Usuario;
    import com.ucsal.avaliador_inteligente.service.ProvaService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/provas")
    @RequiredArgsConstructor
    public class ProvaController {

        private final ProvaService provaService;

        @PostMapping
        public ResponseEntity<Void> criarProva(@RequestBody ProvaRequestDTO dto) {
            provaService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @GetMapping
        public ResponseEntity<List<Prova>> listarProvas() {
            return ResponseEntity.ok(provaService.listarTodas());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Prova> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(provaService.buscarPorId(id));
        }

        @PostMapping("/gerar-ia")
        public ResponseEntity<Prova> gerarProvaComIA(@RequestBody ProvaIARequestDTO dto) {

            System.out.println("DTO recebido: " + dto);
            Prova prova = provaService.gerarProvaComGabarito(dto.getTitulo(), dto.getTema(), dto.getCriadorId());
            return ResponseEntity.ok(prova);
        }


        @GetMapping("/{id}/pdf")
        public ResponseEntity<byte[]> baixarPdfProva(@PathVariable Long id) {
            byte[] pdf = provaService.gerarPdfProva(id);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=prova.pdf")
                    .header("Content-Type", "application/pdf")
                    .body(pdf);
        }

        @GetMapping("/{id}/gabarito/pdf")
        public ResponseEntity<byte[]> baixarPdfGabarito(@PathVariable Long id) {
            Prova prova = provaService.buscarPorId(id);
            byte[] pdf = prova.getGabarito().getArquivoPdf();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=gabarito.pdf")
                    .header("Content-Type", "application/pdf")
                    .body(pdf);
        }

    }
