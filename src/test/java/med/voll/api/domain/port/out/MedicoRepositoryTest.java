package med.voll.api.domain.port.out;

import med.voll.api.adapter.FlywayCleaner;
import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.adapter.out.persistence.repository.MedicoRepositoryImpl;
import med.voll.api.domain.model.DadosMedicoDTO;
import med.voll.api.domain.model.DadosPacienteDTO;
import med.voll.api.domain.model.EnderecoDTO;
import med.voll.api.domain.model.Especialidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@Import({MedicoRepositoryImpl.class,FlywayCleaner.class})
class MedicoRepositoryTest {


    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FlywayCleaner flywayCleaner;

    @BeforeEach void limpaDados(){
        flywayCleaner.limparBanco();
    }


    @Test
    @DisplayName("Deve Retornar Nulo Quando Unico Medico Cadastrado Nao Esta Disponivel Na Data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {

        //Given
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //When
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA.name(),proximaSegundaAs10);

        //Then
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deve Retornar Medico Quando Estiver Disponivel Na Data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {

        //Given
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        //When
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //Then
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA.name(),proximaSegundaAs10);

        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente, LocalDateTime data) {
        testEntityManager.persist(new ConsultaEntity(null, medico, paciente, data));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoEntity(dadosMedico(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private PacienteEntity cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteEntity(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
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