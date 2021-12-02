package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioDoacoesDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioDoadorDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioOngDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import br.com.artemis.poctcc.repository.DoadorRepository;
import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.ItemRepository;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.service.CalculadorEntidadesCadastradasService;
import br.com.artemis.poctcc.service.geradorRelatorio.GeradorRelatorioService;
import br.com.artemis.poctcc.service.geradorRelatorio.RelatorioDoacoesService;
import br.com.artemis.poctcc.service.geradorRelatorio.RelatorioDoadorService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/relatorios")
@AllArgsConstructor
public class RelatorioController {

    private InstituicaoRepository instituicaoRepository;
    private DoadorRepository doadorRepository;
    private ItemRepository itemRepository;
    private CalculadorEntidadesCadastradasService calculadorEntidadesCadastradasService;


    @PostMapping("/ong")
    public ResponseEntity<InputStreamResource> criarRelatorioOng() {
        // TODO busca base de dados das ongs

        List<Instituicao> instituicaos = StreamSupport
                .stream(instituicaoRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        ResumoCadastroDataDTO resumoCadastroDataDTO = calculadorEntidadesCadastradasService.calcular(instituicaos);
        // TODO mapear para dto do pdf
        List<RelatorioOngDTO> relatorioOngDTOS = instituicaos.stream()
                .map(instituicao -> RelatorioOngDTO
                        .builder()
                        .id(instituicao.getId())
                        .nomeOng(instituicao.getNomeFantasia())
                        .categoria(instituicao.getFocoInstitucional())
                        .razaoSocial(instituicao.getRazaoSocial())
                        .cnpj(instituicao.getCnpj())
                        .build())
                .collect(Collectors.toList());
        ByteArrayInputStream pdf = GeradorRelatorioService.montarRelatorioOngs(relatorioOngDTOS, resumoCadastroDataDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=ongs.pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @PostMapping("/doador")
    public ResponseEntity<InputStreamResource> criarRelatorioDoador() {
        List<Doador> doadors = StreamSupport
                .stream(doadorRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        ResumoCadastroDataDTO resumoCadastroDataDTO = calculadorEntidadesCadastradasService.calcularDoadores(doadors);

        List<RelatorioDoadorDTO> relatorioDoadorDTOS = doadors.stream()
                .map(doador -> RelatorioDoadorDTO
                        .builder()
                        .id(doador.getId())
                        .nomeDoador(doador.getNome())
                        .dtNasc(LocalDateTime.parse(doador.getDtNasc()))
                        .cpf(doador.getCpf())
                        .build())
                .collect(Collectors.toList());

        ByteArrayInputStream pdf2 = RelatorioDoadorService.montarRelatorioDoador(relatorioDoadorDTOS, resumoCadastroDataDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=ongs.pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf2));
    }

    @PostMapping("/doacao")
    public ResponseEntity<InputStreamResource> criarRelatorioDoacao() {
        List<Item> items = StreamSupport
                .stream(itemRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        ResumoCadastroDataDTO resumoCadastroDataDTO = calculadorEntidadesCadastradasService.calcularDoacoes(items);

        List<RelatorioDoacoesDTO> relatorioDoacoesDTOS = items.stream()
                .map(item -> RelatorioDoacoesDTO
                        .builder()
                        .id(item.getId())
                        .nomeItem(item.getNome())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());

        ByteArrayInputStream pdfDoacoes = RelatorioDoacoesService.montarRelatorioDoacoes(relatorioDoacoesDTOS, resumoCadastroDataDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=ongs.pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfDoacoes));
    }
}
