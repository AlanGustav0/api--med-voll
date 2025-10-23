package med.voll.api.adapter.in;

import med.voll.api.adapter.FlywayCleaner;
import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.adapter.out.persistence.repository.ConsultaRepositoryImpl;
import med.voll.api.adapter.out.persistence.repository.MedicoRepositoryImpl;
import med.voll.api.adapter.out.persistence.repository.PacienteRepositoryImpl;
import med.voll.api.application.service.ConsultaService;
import med.voll.api.domain.model.*;
import med.voll.api.domain.model.response.DetalhamentoConsultaResponse;
import med.voll.api.domain.port.out.ConsultaRepository;
import med.voll.api.domain.port.out.MedicoRepository;
import med.voll.api.domain.port.out.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsultaDTO> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DetalhamentoConsultaResponse> dadosDetalhamentoConsultaJson;

    @MockitoBean
    private ConsultaService consultaService;


    @Test
    @DisplayName("Deve retornar erro http 400 quando informações são inválidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/api/consultas/agendar"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar erro http 200 quando informações são válidas")
    @WithMockUser
    void agendarCenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        when(consultaService.agendar(any())).thenReturn(new DetalhamentoConsultaResponse(null,2l,5l,data));

        var response = mvc.perform(post("/api/consultas/agendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson
                                .write(new DadosAgendamentoConsultaDTO(2l,5l,data,especialidade)
                                ).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonDetalhamento = dadosDetalhamentoConsultaJson.write(
                new DetalhamentoConsultaResponse(null,2l,5l,data)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonDetalhamento);
    }

    private DadosMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosMedicoDTO(
                nome,
                email,
                "999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosPacienteDTO(nome,
                email,
                "999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "Bairro Teste",
                "00000000",
                "Cidade Teste",
                "ET",
                "TS",
                null,
                null
        );
    }
}